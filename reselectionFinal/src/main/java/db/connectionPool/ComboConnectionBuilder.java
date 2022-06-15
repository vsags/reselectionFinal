package db.connectionPool;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import db.config.ConfigNew;

//класс, отвечающий за многопоточность

public class ComboConnectionBuilder implements ConnectionBuilder {
	
private ComboPooledDataSource dataSource;
	
	public ComboConnectionBuilder() {
		try {
			dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(ConfigNew.getProperty("db.driver.class"));
            dataSource.setJdbcUrl(ConfigNew.getProperty("db.url"));
            dataSource.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
       }
	}

	//@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
