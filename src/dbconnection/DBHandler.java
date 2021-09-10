package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs {
    private Connection connection;

    public Connection getConnection() {
        String connectionUrl = String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true&useSSL=false", Configs.dbHost,
                Configs.dbPort, Configs.dbName);

        try {
            connection = DriverManager.getConnection(connectionUrl, Configs.dbUser, Configs.dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
