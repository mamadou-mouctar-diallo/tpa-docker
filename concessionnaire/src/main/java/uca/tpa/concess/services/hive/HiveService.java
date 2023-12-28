package uca.tpa.concess.services.hive;

import org.bson.Document;
import uca.tpa.concess.entities.*;
import uca.tpa.concess.services.connection.HiveConnectionService;
import uca.tpa.concess.services.connection.MongodbConnectionService;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HiveService {
    private static Statement statement;

    static class ImmatriculationService implements ICommun<ImmatriculationEntity>, IFromMongo<Document> {

        @Override
        public void createFromMongo(Document document) {
            try {
                HiveService.statement.executeUpdate("INSERT INTO immatriculation VALUES ('"
                        + document.get("immatriculation") + "', '"
                        + document.get("marque") + "', '"
                        + document.get("nom") + "', "
                        + document.get("puissance") + ", '"
                        + document.get("longueur") + "', '"
                        + document.get("nbPlaces") + "', '"
                        + document.get("nbPortes") + "', '"
                        + document.get("couleur") + "', "
                        + document.get("occasion") + ", "
                        + document.get("prix")
                        + ")");
                System.out.println("Immatriculation " + document.get("immatriculation") + " créée");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la création de l'immatriculation " + document.get("immatriculation"), e);
            }
        }

        @Override
        public void createManyFromMongo(List<Document> documents) {
            documents.forEach(this::createFromMongo);
        }

        @Override
        public void create(ImmatriculationEntity immatriculationEntity) {
            try {
                HiveService.statement.executeUpdate("INSERT INTO immatriculation VALUES ('"
                        + immatriculationEntity.getImmatriculation() + "', '"
                        + immatriculationEntity.getMarque() + "', '"
                        + immatriculationEntity.getNom() + "', "
                        + immatriculationEntity.getPuissance() + ", '"
                        + immatriculationEntity.getLongueur() + "', '"
                        + immatriculationEntity.getCouleur() + "', "
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

        @Override
        public void deleteAll() {
            try {
                HiveService.statement.execute("Truncate Table immatriculation");
                System.out.println("Table immatriculation vidée");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la vidage de la table immatriculation", e);
            }
        }
    }

    static class MarketingService implements ICommun<MarketingEntity> , IFromMongo<Document> {


        @Override
        public void createFromMongo(Document document) {
            try {
                HiveService.statement.executeUpdate("INSERT INTO marketing VALUES ("
                        + document.get("age") + ", '"
                        + document.get("sexe") + "', "
                        + document.get("taux") + ", '"
                        + document.get("situationFamiliale") + "', "
                        + document.get("nbEnfantsAcharge") + ", "
                        + document.get("deuxiemeVoiture")
                        + ")");
                System.out.println("Marketing " + document.get("age") + " créé");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la création du marketing " + document.get("age"), e);
            }
        }

        @Override
        public void createManyFromMongo(List<Document> documents) {
            documents.forEach(this::createFromMongo);
        }

        @Override
        public void create(MarketingEntity marketingEntity) {
            try {
                HiveService.statement.executeUpdate("INSERT INTO marketing VALUES ("
                        + marketingEntity.getAge() + ", '"
                        + marketingEntity.getSexe() + "', "
                        + marketingEntity.getTaux() + ", '"
                        + marketingEntity.getSituationFamiliale() + "', "
                        + marketingEntity.getNbEnfantsAcharge() + ", "
                        + marketingEntity.isDeuxiemeVoiture()
                        + ")");
                System.out.println("Marketing " + marketingEntity.getAge() + " créé");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la création du marketing " + marketingEntity.getAge(), e);
            }
        }

        @Override
        public void update(MarketingEntity marketingEntity) {

        }

        @Override
        public void delete(MarketingEntity marketingEntity) {

        }

        @Override
        public MarketingEntity get(MarketingEntity marketingEntity) {
            return null;
        }

        @Override
        public List<MarketingEntity> getAll() {
            return null;
        }

        @Override
        public void createMany(List<MarketingEntity> marketingEntities) {
            marketingEntities.forEach(this::create);
        }

        @Override
        public void deleteAll() {
            try {
                HiveService.statement.executeUpdate("Truncate Table marketing");
                System.out.println("Table marketing vidée");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la vidage de la table marketing", e);
            }
        }
    }

    static class CatalogueService implements ICommun<CatalogueEntity>, IFromMongo<Document> {


        @Override
        public void createFromMongo(Document document) {
            try {
                HiveService.statement.executeUpdate("INSERT INTO catalogue VALUES ('"
                        + document.get("marque") + "', '"
                        + document.get("nom") + "', "
                        + document.get("puissance") + ", '"
                        + document.get("longueur") + "', "
                        + document.get("nbPlaces") + ", "
                        + document.get("nbPortes") + ", '"
                        + document.get("couleur") + "', "
                        + document.get("occasion") + ", "
                        + document.get("prix")
                        + ")");
                System.out.println("Catalogue " + document.get("marque") + " créé");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la création du catalogue " + document.get("marque"), e);
            }
        }

        @Override
        public void createManyFromMongo(List<Document> documents) {
            documents.forEach(this::createFromMongo);
        }

        @Override
        public void create(CatalogueEntity catalogueEntity) {
            try {
                HiveService.statement.executeUpdate("INSERT INTO catalogue VALUES ('"
                        + catalogueEntity.getMarque() + "', '"
                        + catalogueEntity.getNom() + "', "
                        + catalogueEntity.getPuissance() + ", '"
                        + catalogueEntity.getLongueur() + "', "
                        + catalogueEntity.getNbPlaces() + ", "
                        + catalogueEntity.getNbPortes() + ", '"
                        + catalogueEntity.getCouleur() + "', "
                        + catalogueEntity.isOccasion() + ", "
                        + catalogueEntity.getPrix()
                        + ")");
                System.out.println("Catalogue " + catalogueEntity.getMarque() + " créé");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la création du catalogue " + catalogueEntity.getMarque(), e);
            }
        }

        @Override
        public void update(CatalogueEntity catalogueEntity) {

        }

        @Override
        public void delete(CatalogueEntity catalogueEntity) {

        }

        @Override
        public CatalogueEntity get(CatalogueEntity catalogueEntity) {
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

        @Override
        public void deleteAll() {
            try {
                HiveService.statement.executeUpdate("Truncate Table catalogue");
                System.out.println("Table catalogue vidée");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la vidage de la table catalogue", e);
            }
        }
    }

    static class ClientService implements ICommun<ClientEntity>, IFromMongo<Document> {


        public void createFromMongo(Document document) {
            try {
                HiveService.statement.executeUpdate("INSERT INTO client VALUES ("
                        + document.get("age") + ", '"
                        + document.get("sexe") + "', "
                        + document.get("taux")+ ", '"
                        + document.get("situationFamiliale") + "', "
                        + document.get("nbEnfantsACharge") + ", "
                        + document.get("deuxiemeVoiture") + ", '"
                        + document.get("immatriculation")
                        + "')");
                System.out.println("Client " + document.get("age") + " créé");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la création du client " + document.get("age"), e);
            }
        }

        @Override
        public void createManyFromMongo(List<Document> documents) {
            documents.forEach(this::createFromMongo);
        }

        @Override
        public void create(ClientEntity clientEntity) {

        }

        @Override
        public void update(ClientEntity clientEntity) {

        }

        @Override
        public void delete(ClientEntity clientEntity) {

        }

        @Override
        public ClientEntity get(ClientEntity clientEntity) {
            return null;
        }

        @Override
        public List<ClientEntity> getAll() {
            return null;
        }

        @Override
        public void createMany(List<ClientEntity> clientEntities) {

        }

        @Override
        public void deleteAll() {
            try {
                HiveService.statement.executeUpdate("Truncate Table client");
                System.out.println("Table client vidée");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la vidage de la table client", e);
            }
        }
    }

    static class Co2Service implements ICommun<Co2Entity>, IFromMongo<Document> {

        @Override
        public void createFromMongo(Document document) {
            try {
                HiveService.statement.executeUpdate("INSERT INTO co2 VALUES ('"
                        + document.get("modele") + "', '"
                        + document.get("bonusMalus") + "', '"
                        + document.get("rejetsCO2") + "', '"
                        + document.get("coutEnergie")
                        + "')");
                System.out.println("Co2 " + document.get("modele") + " créé");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la création du co2 " + document.get("modele"), e);
            }

        }

        @Override
        public void createManyFromMongo(List<Document> documents) {
            documents.forEach(this::createFromMongo);
        }

        @Override
        public void create(Co2Entity co2Entity) {

        }

        @Override
        public void update(Co2Entity co2Entity) {

        }

        @Override
        public void delete(Co2Entity co2Entity) {

        }

        @Override
        public Co2Entity get(Co2Entity co2Entity) {
            return null;
        }

        @Override
        public List<Co2Entity> getAll() {
            return null;
        }

        @Override
        public void createMany(List<Co2Entity> co2Entities) {

        }

        @Override
        public void deleteAll() {
            try {
                HiveService.statement.executeUpdate("Truncate Table co2");
                System.out.println("Table co2 vidée");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la vidage de la table co2", e);
            }
        }
    }

    interface IFromMongo<T> {
        void createFromMongo(T t);
        void createManyFromMongo(List<T> tList);
    }

    interface ICommun<T> {
        void create(T t);

        void update(T t);

        void delete(T t);

        T get(T t);

        List<T> getAll();
        void createMany(List<T> tList);

        void deleteAll();
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
        MongodbConnectionService mongodbConnectionService = new MongodbConnectionService();
        mongodbConnectionService.startConnection();
        // Table immatriculation
//        Iterable<Document> immatriculation = mongodbConnectionService.getMongoDatabase().getCollection("immatriculations").find();
//        List<Document> immatriculationList = new ArrayList<>();
//        immatriculation.forEach(immatriculationList::add);
//        immatriculationService.deleteAll();
//        immatriculationService.createManyFromMongo(immatriculationList);
        //Table marketing
        List<Document> marketingList = new ArrayList<>();
        Iterable<Document> marketing = mongodbConnectionService.getMongoDatabase().getCollection("marketing").find();
        marketing.forEach(marketingList::add);
//        marketingService.deleteAll();
        marketingService.createManyFromMongo(marketingList);
        //Table catalogue
        List<Document> catalogueList = new ArrayList<>();
        Iterable<Document> catalogue = mongodbConnectionService.getMongoDatabase().getCollection("catalogue").find();
        catalogue.forEach(catalogueList::add);
        catalogueService.deleteAll();
        catalogueService.createManyFromMongo(catalogueList);
        //Table client
        List<Document> clientList = new ArrayList<>();
        Iterable<Document> client = mongodbConnectionService.getMongoDatabase().getCollection("clients").find();
        client.forEach(clientList::add);
        clientService.deleteAll();
        clientService.createManyFromMongo(clientList);
        //Table co2
        List<Document> co2List = new ArrayList<>();
        Iterable<Document> co2 = mongodbConnectionService.getMongoDatabase().getCollection("co2").find();
        co2.forEach(co2List::add);
        co2Service.deleteAll();
        co2Service.createManyFromMongo(co2List);
        // Close the connection to Hive
        hiveConnectionService.stopHQStatement();
        // Close the connection to MongoDB
        mongodbConnectionService.stopConnection();
    }
}
