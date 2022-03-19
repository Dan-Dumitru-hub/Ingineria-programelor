package mad.backend.endpoints.controllers;

import mad.backend.database.DbConnection;
import mad.backend.endpoints.datamodel.MedicationAssignment;
import mad.backend.endpoints.datamodel.UserWithoutCredentials;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {
	@GetMapping("personal-data")
	public UserWithoutCredentials personalData() {
		final String username = findUsername();

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM Utilizatori u " +
							"WHERE nume_utilizator = ?");
			statement.setString(1, username);

			final ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			final UserWithoutCredentials patient = new UserWithoutCredentials(
					resultSet.getString("cnp"),
					resultSet.getString("nume_utilizator"),
					resultSet.getString("nume"),
					resultSet.getString("prenume"),
					resultSet.getString("email"),
					resultSet.getString("telefon"),
					resultSet.getString("tip_utilizator")
			);

			resultSet.close();
			statement.close();

			return patient;
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return null;
	}

	@GetMapping("nutrition")
	public List<String> nutrition() {
		final String username = findUsername();

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"SELECT alimentatie FROM Alimentatii_per_pacienti " +
							"WHERE cnp_pacient = (SELECT cnp FROM Utilizatori WHERE nume_utilizator = ?)");
			statement.setString(1, username);

			final ResultSet resultSet = statement.executeQuery();

			final List<String> nutrition = new LinkedList<>();
			while (resultSet.next()) {
				nutrition.add(resultSet.getString("alimentatie"));
			}

			resultSet.close();
			statement.close();

			return nutrition;
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return null;
	}

	@GetMapping("medication")
	public List<MedicationAssignment> medication() {
		final String username = findUsername();

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM Medicatii_per_pacienti " +
							"WHERE cnp_pacient = (SELECT cnp FROM Utilizatori WHERE nume_utilizator = ?)");
			statement.setString(1, username);

			final ResultSet resultSet = statement.executeQuery();

			final List<MedicationAssignment> medicationAssignments = new LinkedList<>();
			while (resultSet.next()) {
				medicationAssignments.add(new MedicationAssignment(
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4)
				));
			}

			resultSet.close();
			statement.close();

			return medicationAssignments;
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return null;
	}

	@GetMapping("medics")
	public List<UserWithoutCredentials> medics() {
		final String username = findUsername();

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM Utilizatori " +
							"WHERE cnp_pacient = (SELECT cnp FROM Utilizatori WHERE nume_utilizator = ?)");
			statement.setString(1, username);

			final ResultSet resultSet = statement.executeQuery();

			final List<UserWithoutCredentials> nutritions = new LinkedList<>();
			while (resultSet.next()) {
				nutritions.add(new UserWithoutCredentials(
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

			return nutritions;
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return null;
	}

	private String findUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
