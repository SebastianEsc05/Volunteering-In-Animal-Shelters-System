package structure;

import config.ConexionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private final ConexionDB config = new ConexionDB();

    public DatabaseInitializer() throws SQLException {
        createDatabase();
        createTables();
    }

    private void createDatabase() throws SQLException {
        String sql = "CREATE DATABASE IF NOT EXISTS " + config.getName();

        try (Connection conn = ConexionDB.getConnectionServer();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Base de datos creada.\n");

        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage() + "\n");

        }
    }

    private void createTables() throws SQLException {
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement()) {
            String sqlSheltersTable = "CREATE TABLE IF NOT EXISTS refugios(" +
                    "id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL UNIQUE, " +
                    "responsable VARCHAR(50), " +
                    "capacidad INT, " +
                    "ubicacion VARCHAR(100))";
            stmt.executeUpdate(sqlSheltersTable);
            System.out.println("Se ha creado la tabla 'refugios'");

            String sqlAnimalsTable = "CREATE TABLE IF NOT EXISTS animales(" +
                    "id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "edad INT NOT NULL, " +
                    "fecha_ingreso DATE NOT NULL, " +
                    "estado_salud VARCHAR(50), " +
                    "especie VARCHAR(100), " +
                    "id_refugio INT, " +
                    "FOREIGN KEY (id_refugio) REFERENCES refugios(id))";
            stmt.executeUpdate(sqlAnimalsTable);
            System.out.println("Se ha creado la tabla 'animales'");

            String sqlVolunteersTable = "CREATE TABLE IF NOT EXISTS voluntarios(" +
                    "id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "telefono VARCHAR(10) UNIQUE NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "fecha_nacimiento DATE NOT NULL, " +
                    "especialidad VARCHAR(100))";
            stmt.executeUpdate(sqlVolunteersTable);
            System.out.println("Se ha creado la tabla 'voluntarios'");

            String sqlAppoimentsTable = "CREATE TABLE IF NOT EXISTS asignaciones(" +
                    "id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
                    "observaciones VARCHAR(300), " +
                    "estado VARCHAR(20) NOT NULL, " +
                    "fecha_de_agenda DATETIME NOT NULL, " +
                    "fecha_realizacion DATETIME, " +
                    "id_animal INT, " +
                    "id_voluntario INT, " +
                    "actividad VARCHAR(100) NOT NULL, " +
                    "FOREIGN KEY (id_animal) REFERENCES animales(id), " +
                    "FOREIGN KEY (id_voluntario) REFERENCES voluntarios(id))";
            stmt.executeUpdate(sqlAppoimentsTable);
            System.out.println("Se ha creado la tabla 'asignaciones'");

        } catch (SQLException e) {
            System.out.println("Error al crear las tablas: " + e.getMessage() + "\n");

        }
    }
}
