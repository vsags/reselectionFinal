package db.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.config.ConfigNew;

public class SimpleConnectionBuilder implements ConnectionBuilder {
	public SimpleConnectionBuilder() {
		try {
			Class.forName(ConfigNew.getProperty("db.driver.class"));
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
				}
		}
	        
	@Override
	public Connection getConnection() throws SQLException {
		String url = ConfigNew.getProperty("db.url");
		return DriverManager.getConnection(url);
		}
}
