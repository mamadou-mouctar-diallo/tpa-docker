package uca.tpa.concess.services.hive;

import uca.tpa.concess.entities.*;
import uca.tpa.concess.services.connection.HiveConnectionService;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HiveService {
    private static Statement statement;

    static class ImmatriculationService implements ICommun<ImmatriculationEntity> {
        @Override
        public void create(ImmatriculationEntity immatriculationEntity) {
            try {
                HiveService.statement.executeUpdate("INSERT INTO immatriculation VALUES ("
                        + immatriculationEntity.getImmatriculation() + ", "
                        + immatriculationEntity.getMarque() + ", "
                        + immatriculationEntity.getNom() + ", "
                        + immatriculationEntity.getPuissance() + ", "
                        + immatriculationEntity.getLongueur() + ", "
                        + immatriculationEntity.getNbPlaces() + ", "
                        + immatriculationEntity.getNbPortes() + ", "
                        + immatriculationEntity.getCouleur() + ", "
                        + immatriculationEntity.isOccasion() + ", "
                        + immatriculationEntity.getPrix()
                        + ")");
                System.out.println("Immatriculation " + immatriculationEntity.getImmatriculation() + " créée");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la création de l'immatriculation " + immatriculationEntity.getImmatriculation(), e);
            }
        }

        @Override
        public void update(ImmatriculationEntity immatriculationEntity) {
        }

        @Override
        public void delete(ImmatriculationEntity immatriculationEntity) {
        }

        @Override
        public ImmatriculationEntity get(ImmatriculationEntity immatriculationEntity) {
            return null;
        }

        @Override
        public List<ImmatriculationEntity> getAll() {
            return null;
        }

        @Override
        public void createMany(List<ImmatriculationEntity> immatriculationEntities) {
            immatriculationEntities.forEach(this::create);
        }
    }

    static class MarketingService implements ICommun<MarketingEntity> {
        @Override
        public void create(MarketingEntity marketingService) {
        }

        @Override
        public void update(MarketingEntity marketingService) {
        }

        @Override
        public void delete(MarketingEntity marketingService) {
        }

        @Override
        public MarketingEntity get(MarketingEntity marketingService) {
            return null;
        }

        @Override
        public List<MarketingEntity> getAll() {
            return null;
        }

        @Override
        public void createMany(List<MarketingEntity> marketingEntities) {

        }
    }

    static class CatalogueService implements ICommun<CatalogueEntity> {
        @Override
        public void create(CatalogueEntity catalogueService) {
        }

        @Override
        public void update(CatalogueEntity catalogueService) {
        }

        @Override
        public void delete(CatalogueEntity catalogueService) {
        }

        @Override
        public CatalogueEntity get(CatalogueEntity catalogueService) {
            return null;
        }

        @Override
        public List<CatalogueEntity> getAll() {
            return null;
        }

        @Override
        public void createMany(List<CatalogueEntity> catalogueEntities) {
            catalogueEntities.forEach(this::create);
        }
    }

    static class ClientService implements ICommun<ClientEntity> {
        @Override
        public void create(ClientEntity clientService) {
        }

        @Override
        public void update(ClientEntity clientService) {
        }

        @Override
        public void delete(ClientEntity clientService) {
        }

        @Override
        public ClientEntity get(ClientEntity clientService) {
            return null;
        }

        @Override
        public List<ClientEntity> getAll() {
            return null;
        }

        @Override
        public void createMany(List<ClientEntity> clientEntities) {
            clientEntities.forEach(this::create);
        }
    }

    static class Co2Service implements ICommun<Co2Entity> {
        @Override
        public void create(Co2Entity co2Service) {
        }

        @Override
        public void update(Co2Entity co2Service) {
        }

        @Override
        public void delete(Co2Entity co2Service) {
        }

        @Override
        public Co2Entity get(Co2Entity co2Service) {
            return null;
        }

        @Override
        public List<Co2Entity> getAll() {
            return null;
        }

        @Override
        public void createMany(List<Co2Entity> co2Entities) {
            co2Entities.forEach(this::create);
        }
    }

    static interface ICommun<T> {
        public void create(T t);

        public void update(T t);

        public void delete(T t);

        public T get(T t);

        public List<T> getAll();
        void createMany(List<T> tList);
    }

    private static void createTables() {
        // Create the tables
        try {
            createDataBaseAndUseIt();
            // Create the immatriculation table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS immatriculation ("
                    + "immatriculation STRING, "
                    + "marque STRING, "
                    + "nom STRING, "
                    + "puissance INT, "
                    + "longueur STRING, "
                    + "couleur STRING, "
                    + "occasion BOOLEAN, "
                    + "prix DOUBLE"
                    + ")");
            System.out.println("Table immatriculation créée");
            // Create the marketing table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS marketing ("
                    + "age INT, "
                    + "sexe STRING, "
                    + "taux DOUBLE, "
                    + "situationfamiliale STRING, "
                    + "nbenfantsacharge INT, "
                    + "deuxiemevoiture BOOLEAN"
                    + ")");
            System.out.println("Table marketing créée");
            // Create the catalogue table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS catalogue ("
                    + "marque STRING, "
                    + "nom STRING, "
                    + "puissance INT, "
                    + "longueur STRING, "
                    + "nbplaces INT, "
                    + "nbportes INT, "
                    + "couleur STRING, "
                    + "occasion BOOLEAN, "
                    + "prix DOUBLE"
                    + ")");
            System.out.println("Table catalogue créée");
            // Create the client table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS client ("
                    + "age INT, "
                    + "sexe STRING, "
                    + "taux DOUBLE, "
                    + "situationfamiliale STRING, "
                    + "nbenfantsacharge INT, "
                    + "deuxiemevoiture BOOLEAN, "
                    + "immatriculation STRING"
                    + ")");
            System.out.println("Table client créée");
            // Create the co2 table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS co2 ("
                    + "marque_modele STRING, "
                    + "bonus_malus STRING, "
                    + "rejets_co2 INT, "
                    + "cout_energie DOUBLE"
                    + ")");
            System.out.println("Table co2 créée");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création des tables", e);
        }
    }

    private static void createDataBaseAndUseIt() {
        // Create the database
        try {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS concessionnaire");
            System.out.println("Base de données concessionnaire créée");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la base de données", e);
        }
        // Use the database
        try {
            statement.executeUpdate("USE concessionnaire");
            System.out.println("Base de données concessionnaire utilisée");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'utilisation de la base de données", e);
        }
    }

    public static void hiveLaunch() {
        // Create the connection to Hive
        HiveConnectionService hiveConnectionService = new HiveConnectionService();
        // Create the statement
        HiveService.statement = hiveConnectionService.startHQStatement();
        // Create the tables
        createTables();
        // Create the services
        ImmatriculationService immatriculationService = new ImmatriculationService();
        MarketingService marketingService = new MarketingService();
        CatalogueService catalogueService = new CatalogueService();
        ClientService clientService = new ClientService();
        Co2Service co2Service = new Co2Service();
    }
}
