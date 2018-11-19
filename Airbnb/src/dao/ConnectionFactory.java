package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionFactory {
	
	//Actually should be read from a properties file
	private static final String URL = "jdbc:sqlserver://George\\SQLEXPRESS:5171;databaseName=Airbnb;integratedSecurity=true;";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    //Singleton pattern: Connection factory instance is created only once
    private static ConnectionFactory instance = null;
    
    protected ConnectionFactory() {}
    
    public static synchronized ConnectionFactory getInstance(boolean pool)  {
     
        if (instance == null)
        {
        
	        if (!pool) {
	            try {
	                Class.forName(DRIVER);
	            }
	            catch (ClassNotFoundException e) {
	                System.err.println(e.getMessage());
	            }
	            instance = new DriverManagerConnectionFactory(URL);
	        }
	
	        // Else lookup datasource in the JNDI.
	        else {
	            DataSource dataSource = null;
	            try {
	                
	            	Context context = new InitialContext();
	            	Context envctx = (Context)context.lookup("java:comp/env");
	            	dataSource = (DataSource) envctx.lookup("jdbc");
	            } 
	            catch (NamingException e) {
	            	System.err.println(e.getMessage());
	            }
	            instance = new DataSourceConnectionFactory(dataSource);
	        }
        }

        return instance;
    }

    abstract Connection getConnection() throws SQLException;
}
