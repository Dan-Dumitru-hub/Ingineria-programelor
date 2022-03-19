package mad.backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:DB19c";
	private static final String DATABASE_USERNAME = "scott";
	private static final String DATABASE_PASSWORD = "tiger";
	private static DbConnection instance;

	private DbConnection() {
	}

	public static DbConnection getInstance() {
		if (null == instance) {
			instance = new DbConnection();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
	}
}
