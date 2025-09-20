package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private static String host;
    private static String port;
    private static String name;
    private static String user;
    private static String password;
    private static String urlServer;
    private static String urlDataBase;
    private static String driver;
    private Properties props;

    public ConexionDB() {
        try (InputStream input =  ConexionDB.class.getResourceAsStream("/db.properties")) {
            props = new Properties();
            if (input == null) {
                throw new RuntimeException("No se pudo obtener el archivo db.properties");
            }

            props.load(input);
            host = props.getProperty("db.host");
            port = props.getProperty("db.port");
            name = props.getProperty("db.name");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

            urlServer = "jdbc:mysql://" + host + ":" + port + "/";
            urlDataBase = urlServer + name;

            Class.forName(driver);

        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public String getName() {
        return props.getProperty("db.name");
    }

    public static Connection getConnectionServer() throws SQLException {
        return DriverManager.getConnection(urlServer, user, password);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(urlDataBase, user, password);
    }
}
