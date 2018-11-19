package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerConnectionFactory extends ConnectionFactory {

	private String url;
    
	public DriverManagerConnectionFactory(String url) {
		this.url = url;
	}

	@Override
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url);
	}
		
}
