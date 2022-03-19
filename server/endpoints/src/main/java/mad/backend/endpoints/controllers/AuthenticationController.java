package mad.backend.endpoints.controllers;

import mad.backend.database.DbConnection;
import mad.backend.endpoints.datamodel.UserWithoutCredentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController()
public class AuthenticationController {
	@GetMapping(path = "/auth", produces = "application/json")
	public UserWithoutCredentials authentication() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserWithoutCredentials userWithoutCredentials = null;

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement("Select * FROM Utilizatori u" +
					" WHERE u.nume_utilizator = ?");
			statement.setString(1, auth.getName());

			final ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				userWithoutCredentials = new UserWithoutCredentials(
						resultSet.getString("cnp"),
						resultSet.getString("nume_utilizator"),
						resultSet.getString("nume"),
						resultSet.getString("prenume"),
						resultSet.getString("email"),
						resultSet.getString("telefon"),
						resultSet.getString("tip_utilizator"));
			}

			resultSet.close();
			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return userWithoutCredentials;
	}
}
