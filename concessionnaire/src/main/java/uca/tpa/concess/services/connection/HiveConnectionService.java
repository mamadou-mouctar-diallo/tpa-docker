package uca.tpa.concess.services.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveConnectionService {
    private Connection connection = null;

    public HiveConnectionService() {
        this.loadDriver();
    }

    private void loadDriver() {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            this.openConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erreur lors du chargement du driver Hive JDBC", e);
        }
    }

    private void openConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:hive2://localhost:10000/", "hive", "");
            System.out.println("Connexion à Hive réussie");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion à Hive", e);
        }
    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la fermeture de la connexion à Hive", e);
        }
    }

    public Statement startHQStatement() {
        try {
            return this.connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la fermeture de la connexion à Hive", e);
        }
    }

    public void stopHQStatement() {
        try {
            System.out.println("Fermeture de la connexion à Hive");
            connection.close();
            this.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la fermeture de la connexion à Hive", e);
        }
    }
}
