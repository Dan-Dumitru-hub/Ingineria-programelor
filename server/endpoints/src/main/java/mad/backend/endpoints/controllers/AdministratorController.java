package mad.backend.endpoints.controllers;

import mad.backend.database.DbConnection;
import mad.backend.database.datamodel.User;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@RestController()
@RequestMapping("admin")
public class AdministratorController {
	@GetMapping(path = "get-users", produces = "application/json")
	public List<User> getUsers() {
		final List<User> users = new LinkedList<>();

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT * FROM Utilizatori");

			while (resultSet.next()) {
				users.add(new User(
						resultSet.getString("cnp"),
						resultSet.getString("nume_utilizator"),
						resultSet.getString("nume"),
						resultSet.getString("prenume"),
						resultSet.getString("email"),
						resultSet.getString("telefon"),
						resultSet.getString("tip_utilizator"),
						resultSet.getString("parola"),
						resultSet.getString("salt")
				));
			}

			resultSet.close();
			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return users;
	}

	@PostMapping(path = "add-user", consumes = "application/json")
	public void addUser(@RequestBody final User user) {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement("INSERT INTO Utilizatori VALUES" +
					"(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setLong(1, Long.parseLong(user.getCnp()));
			statement.setString(2, user.getNumeUtilizator());
			statement.setString(3, user.getNume());
			statement.setString(4, user.getPrenume());
			statement.setString(5, user.getEmail());
			statement.setLong(6, Long.parseLong(user.getTelefon()));
			statement.setString(7, user.getTipUtilizator());
			statement.setString(8, user.getParola());
			statement.setString(9, user.getSalt());

			statement.execute();

			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	@DeleteMapping(path = "delete-user/{cnp}")
	public void deleteUser(@PathVariable("cnp") final String cnp) {
		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			PreparedStatement statement = connection.prepareStatement("DELETE FROM Pacienti_per_medici p " +
					"WHERE p.cnp_medic = ? OR p.cnp_pacient = ?");
			statement.setLong(1, Long.parseLong(cnp));
			statement.setLong(2, Long.parseLong(cnp));
			statement.executeUpdate();
			statement.close();

			statement = connection.prepareStatement("DELETE FROM Medicatii_per_pacienti m " +
					"WHERE m.cnp_pacient = ?");
			statement.setLong(1, Long.parseLong(cnp));
			statement.executeUpdate();
			statement.close();

			statement = connection.prepareStatement("DELETE FROM Alimentatii_per_pacienti a " +
					"WHERE a.cnp_pacient = ?");
			statement.setLong(1, Long.parseLong(cnp));
			statement.executeUpdate();
			statement.close();

			statement = connection.prepareStatement("DELETE FROM Utilizatori WHERE cnp = ?");
			statement.setLong(1, Long.parseLong(cnp));
			statement.executeUpdate();
			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
