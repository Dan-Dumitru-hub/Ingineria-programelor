package mad.backend.endpoints.controllers;

import mad.backend.database.DbConnection;
import mad.backend.database.datamodel.Medication;
import mad.backend.endpoints.datamodel.MedicationAssignment;
import mad.backend.endpoints.datamodel.PatientFullData;
import mad.backend.endpoints.datamodel.UserWithoutCredentials;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("medic")
public class MedicController {
	@GetMapping("patients")
	public List<UserWithoutCredentials> patients() {
		final String username = findUsername();

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM Utilizatori u " +
							"WHERE cnp in " +
							"(SELECT cnp_pacient FROM Pacienti_per_medici WHERE cnp_medic = " +
							"(SELECT cnp FROM Utilizatori WHERE nume_utilizator = ?))");
			statement.setString(1, username);

			final ResultSet resultSet = statement.executeQuery();

			final List<UserWithoutCredentials> patients = new LinkedList<>();
			while (resultSet.next()) {
				patients.add(new UserWithoutCredentials(
						resultSet.getString("cnp"),
						resultSet.getString("nume_utilizator"),
						resultSet.getString("nume"),
						resultSet.getString("prenume"),
						resultSet.getString("email"),
						resultSet.getString("telefon"),
						resultSet.getString("tip_utilizator")
				));
			}

			resultSet.close();
			statement.close();

			return patients;
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return null;
	}

	@GetMapping("available-patients")
	public List<UserWithoutCredentials> availablePatients() {
		final String username = findUsername();

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM Utilizatori " +
							"WHERE tip_utilizator = 'pacient' " +
							"AND cnp NOT IN (" +
							"SELECT cnp_pacient FROM Pacienti_per_medici WHERE cnp_medic = (" +
							"SELECT cnp FROM Utilizatori WHERE nume_utilizator = ?))");
			statement.setString(1, username);

			final ResultSet resultSet = statement.executeQuery();

			final List<UserWithoutCredentials> patients = new LinkedList<>();
			while (resultSet.next()) {
				patients.add(new UserWithoutCredentials(
						resultSet.getString("cnp"),
						resultSet.getString("nume_utilizator"),
						resultSet.getString("nume"),
						resultSet.getString("prenume"),
						resultSet.getString("email"),
						resultSet.getString("telefon"),
						resultSet.getString("tip_utilizator")
				));
			}

			resultSet.close();
			statement.close();

			return patients;
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return null;
	}

	@GetMapping("patient-data/{cnp}")
	public PatientFullData patientData(@PathVariable("cnp") final String cnp) {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement1 = connection.prepareStatement(
					"SELECT alimentatie FROM Alimentatii_per_pacienti WHERE cnp_pacient = ?");
			statement1.setLong(1, Long.parseLong(cnp));
			final ResultSet resultSet1 = statement1.executeQuery();
			resultSet1.next();

			final String nutrition = resultSet1.getString("alimentatie");

			resultSet1.close();
			statement1.close();

			final PreparedStatement statement2 = connection.prepareStatement(
					"SELECT * FROM Medicatii_per_pacienti WHERE cnp_pacient = ?");
			statement2.setLong(1, Long.parseLong(cnp));
			final ResultSet resultSet2 = statement2.executeQuery();

			final List<MedicationAssignment> medication = new LinkedList<>();
			while (resultSet2.next()) {
				medication.add(new MedicationAssignment(
						resultSet2.getString("nume_medicatie"),
						resultSet2.getString("doza_medicatie"),
						resultSet2.getString("frecventa_administrare")
				));
			}

			resultSet2.close();
			statement2.close();

			final PreparedStatement statement3 = connection.prepareStatement(
					"SELECT * FROM Utilizatori WHERE cnp = ?");
			statement3.setLong(1, Long.parseLong(cnp));
			final ResultSet resultSet3 = statement3.executeQuery();
			resultSet3.next();

			final PatientFullData patientFullData = new PatientFullData(
					resultSet3.getString("cnp"),
					resultSet3.getString("nume_utilizator"),
					resultSet3.getString("nume"),
					resultSet3.getString("prenume"),
					resultSet3.getString("email"),
					resultSet3.getString("telefon"),
					medication,
					nutrition
			);

			resultSet3.close();
			statement3.close();

			return patientFullData;
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return null;
	}

	@GetMapping("medications")
	public List<Medication> medications() {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Medicatii");

			final ResultSet resultSet = statement.executeQuery();

			final List<Medication> medications = new LinkedList<>();
			while (resultSet.next()) {
				medications.add(new Medication(
						resultSet.getString("nume"),
						resultSet.getString("doza")
				));
			}

			resultSet.close();
			statement.close();

			return medications;
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return null;
	}

	@GetMapping("nutrition")
	public void nutrition() {

	}

	@PostMapping("take-patient/{cnp}")
	public void takePatient(@PathVariable("cnp") final String cnp) {
		final String username = findUsername();

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Pacienti_per_medici VALUES (" +
							"(SELECT cnp FROM Utilizatori WHERE nume_utilizator = ?), " +
							"(?))");
			statement.setString(1, username);
			statement.setString(2, cnp);
			statement.executeUpdate();
			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	@DeleteMapping("drop-patient/{cnp}")
	public void dropPatient(@PathVariable("cnp") final String cnp) {
		final String username = findUsername();

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM Pacienti_per_medici WHERE " +
							"cnp_medic = (SELECT cnp FROM Utilizatori WHERE nume_utilizator = ?) " +
							"AND " +
							"cnp_pacient = ?");
			statement.setString(1, username);
			statement.setString(2, cnp);
			statement.executeUpdate();
			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	@PostMapping("add-medication")
	public void addMedication(@RequestBody final Medication medication) {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Medicatii VALUES (?, ?)");
			statement.setString(1, medication.getNumeMedicatie());
			statement.setInt(2, Integer.parseInt(medication.getDozaMedicatie()));
			statement.executeUpdate();
			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	@DeleteMapping("delete-medication")
	public void deleteMedication(@RequestBody final Medication medication) {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement1 = connection.prepareStatement(
					"DELETE FROM Medicatii_per_pacienti WHERE nume_medicatie = ? AND doza_medicatie = ?");
			statement1.setString(1, medication.getNumeMedicatie());
			statement1.setInt(2, Integer.parseInt(medication.getDozaMedicatie()));
			statement1.executeUpdate();
			statement1.close();

			final PreparedStatement statement2 = connection.prepareStatement(
					"DELETE FROM Medicatii WHERE nume = ? AND doza = ?");
			statement2.setString(1, medication.getNumeMedicatie());
			statement2.setInt(2, Integer.parseInt(medication.getDozaMedicatie()));
			statement2.executeUpdate();
			statement2.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	@PostMapping("assign-medication-to-patient/{cnp}")
	public void assignMedicationToPatient(@PathVariable("cnp") final String cnp,
										  @RequestBody final MedicationAssignment medicationAssignment) {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Medicatii_per_pacienti VALUES (?, ?, ?, ?)");
			statement.setLong(1, Long.parseLong(cnp));
			statement.setString(2, medicationAssignment.getNumeMedicatie());
			statement.setInt(3, Integer.parseInt(medicationAssignment.getDozaMedicatie()));
			statement.setString(4, medicationAssignment.getFrecventaAdministrare());
			statement.executeUpdate();
			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	@DeleteMapping("remove-medication-from-patient/{cnp}")
	public void removeMedicationFromPatient(@PathVariable("cnp") final String cnp,
											@RequestBody final Medication medication) {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM Medicatii_per_pacienti " +
							"WHERE cnp_pacient = ? AND nume_medicatie = ? AND doza_medicatie = ?");
			statement.setLong(1, Long.parseLong(cnp));
			statement.setString(2, medication.getNumeMedicatie());
			statement.setInt(3, Integer.parseInt(medication.getDozaMedicatie()));
			statement.executeUpdate();
			statement.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	@PostMapping("assign-nutrition-to-patient/{cnp}")
	public void assignNutritionToPatient(@PathVariable("cnp") final String cnp,
										 @RequestBody String nutrition) {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			removeNutritionFromPatient(cnp);

			final PreparedStatement statement1 = connection.prepareStatement(
					"INSERT INTO Alimentatii_per_pacienti VALUES (?, ?)");
			statement1.setLong(1, Long.parseLong(cnp));
			statement1.setString(2, nutrition);
			statement1.executeUpdate();
			statement1.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	@DeleteMapping("remove-nutrition-from-patient/{cnp}")
	public void removeNutritionFromPatient(@PathVariable("cnp") final String cnp) {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM Alimentatii_per_pacienti WHERE cnp_pacient = ?");
			statement.setLong(1, Long.parseLong(cnp));
			statement.executeUpdate();
			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	private String findUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
