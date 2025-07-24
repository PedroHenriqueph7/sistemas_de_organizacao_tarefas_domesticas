package org.projeto.dataBase;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
    private static final String CONFIG = "/config.properties";
    private static Connection connection = null;

    private DB(){}
    
    public static Connection getConnection() throws IOException, SQLException {
            if(connection == null || connection.isClosed()){
                
            Properties properties = new Properties();

            try(InputStream input = DB.class.getResourceAsStream(CONFIG)) {
                properties.load(input);
            }

                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");
                
                connection = DriverManager.getConnection(url, user, password);   
            }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

}
