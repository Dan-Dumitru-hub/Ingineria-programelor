package mad.backend.endpoints.security;

import mad.backend.database.DbConnection;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		final String name = authentication.getName();
		final String password = authentication.getCredentials().toString();

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;

		try {
			final Connection connection = DbConnection.getInstance().getConnection();

			final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Utilizatori WHERE" +
					" nume_utilizator=? AND parola=?");
			statement.setString(1, name);
			statement.setString(2, password);

			final ResultSet resultSet = statement.executeQuery();

			List<GrantedAuthority> authorities;
			if (resultSet.next()) {
				authorities = List.of(new SimpleGrantedAuthority(resultSet.getString("tip_utilizator")));
				usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(name, password, authorities);
			}

			resultSet.close();
			statement.close();
		} catch (final SQLException sqlException) {
			sqlException.printStackTrace();
		}

		if (null == usernamePasswordAuthenticationToken) {
			throw new AuthenticationCredentialsNotFoundException("");
		}

		return usernamePasswordAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
