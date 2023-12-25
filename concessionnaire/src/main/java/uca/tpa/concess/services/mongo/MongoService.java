package uca.tpa.concess.services.mongo;

import com.mongodb.client.MongoCollection;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.bson.Document;
import uca.tpa.concess.services.connection.MongodbConnectionService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MongoService {
    private static MongoCollection<Document> collection;
    private static final MongodbConnectionService mongoConnectionService = new MongodbConnectionService();

    private static int batchSize = 1000;

    public static void readFileAndLoadData(String path) {
        File repertoire = new File(path);
        System.out.println("Lecture du répertoire " + repertoire.listFiles().length + " fichiers");
        if (repertoire.isDirectory()) {
            File[] fichiers = repertoire.listFiles();
            for (File fichier : fichiers) {
                if (fichier.isFile()) {
                    List<String[]> data = null;
                    try (CSVReader reader = new CSVReader(new FileReader(fichier))) {
                        data = reader.readAll();
                        int numberThreads = Runtime.getRuntime().availableProcessors();
                        ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);
                        System.out.println("Lancement de " + numberThreads + " threads");
                        for (int i = 0; i < data.size(); i += batchSize) {
                            List<String[]> batchData = data.subList(i, Math.min(i + batchSize, data.size()));
                            executorService.submit(() -> processBatch(batchData, fichier.getName()));
                        }

                    } catch (IOException | CsvException e) {
                        throw new RuntimeException("Erreur lors de la lecture du fichier " + path, e);
                    }

                }
            }
        }

    }

    private static void processBatch(List<String[]> batch, String name) {
        mongoConnectionService.startConnection();
        System.out.println("Traitement du batch " + name);
        String nameWithoutExtension = name.substring(0, name.lastIndexOf('.'));
        switch (nameWithoutExtension.toUpperCase()) {
            case "MARKETING":
                System.out.println("Traitement du batch marketing");
                persisteMarketing(batch);
                break;
            case "CO2":
                System.out.println("Traitement du batch co2");
                persisteCo2(batch);
                break;
            case "IMMATRICULATIONS":
                System.out.println("Traitement du batch immatriculation");
                persisteImmatriculation(batch);
                break;
            case "CATALOGUE":
                System.out.println("Traitement du batch catalogue");
                persisteCatalogue(batch);
                break;
            case "CLIENTS":
                System.out.println("Traitement du batch clients");
                persisteClients(batch);
                break;
            default:
                throw new RuntimeException("Le fichier " + name + " n'est pas pris en charge");
        }
        mongoConnectionService.stopConnection();
    }

    public static void mongoLaunch() {
        readFileAndLoadData("src/main/resources/data");


    }

    public static void insertManyDocuments(List<Document> data) {
        collection.insertMany(data);
        System.out.println("Documents insérés");
    }

    public static Iterable<Document> readMarketings() {
        return collection.find();
    }

    public static void persisteMarketing(List<String[]> data) {
        System.out.println("obtention de la collection marketing");
        collection = mongoConnectionService.getMongoDatabase().getCollection("marketing");
        for (int i = 1; i < data.size(); i++) {
            collection.insertOne(new Document("age", data.get(i)[0])
                    .append("sexe", data.get(i)[1])
                    .append("taux", data.get(i)[2])
                    .append("situationFamiliale", data.get(i)[3])
                    .append("nbEnfantsAcharge", data.get(i)[4])
                    .append("deuxiemeVoiture", data.get(i)[5]));
        }
        System.out.println("insertion des documents marketing");
    }

    private static void persisteImmatriculation(List<String[]> data) {
        System.out.println("obtention de la collection immatriculations");
        collection = mongoConnectionService.getMongoDatabase().getCollection("immatriculations");
        for (int i = 1; i < data.size(); i++) {
            collection.insertOne(new Document("immatriculation", data.get(i)[0])
                    .append("marque", data.get(i)[1])
                    .append("nom", data.get(i)[2])
                    .append("puissance", data.get(i)[3])
                    .append("longueur", data.get(i)[4])
                    .append("nbPlaces", data.get(i)[5])
                    .append("nbPortes", data.get(i)[6])
                    .append("couleur", data.get(i)[7])
                    .append("occasion", data.get(i)[8])
                    .append("prix", data.get(i)[9]));
        }
        System.out.println("insertion des documents immatriculations");
    }

    private static void persisteCo2(List<String[]> data) {
        System.out.println("obtention de la collection co2");
        collection = mongoConnectionService.getMongoDatabase().getCollection("co2");

        for (int i = 1; i < data.size(); i++) {
            collection.insertOne(new Document("modele", data.get(i)[0])
                    .append("bonusMalus", data.get(i)[1])
                    .append("rejetsCO2", data.get(i)[2])
                    .append("coutEnergie", data.get(i)[3]));
        }
        System.out.println("insertion des documents co2");
    }

    private static void persisteCatalogue(List<String[]> data) {
        System.out.println("obtention de la collection catalogue");
        collection = mongoConnectionService.getMongoDatabase().getCollection("catalogue");
        for (int i = 1; i < data.size(); i++) {
            collection.insertOne(new Document("marque", data.get(i)[0])
                    .append("nom", data.get(i)[1])
                    .append("puissance", data.get(i)[2])
                    .append("longueur", data.get(i)[3])
                    .append("nbPlaces", data.get(i)[4])
                    .append("nbPortes", data.get(i)[5])
                    .append("couleur", data.get(i)[6])
                    .append("occasion", data.get(i)[7])
                    .append("prix", data.get(i)[8]));
        }
        System.out.println("insertion des documents catalogue");
    }

    private static void persisteClients(List<String[]> data) {
        System.out.println("obtention de la collection clients");
        collection = mongoConnectionService.getMongoDatabase().getCollection("clients");
        for (int i = 1; i < data.size(); i++) {
            collection.insertOne(new Document("age", data.get(i)[0])
                    .append("sexe", data.get(i)[1])
                    .append("taux", data.get(i)[2])
                    .append("situationFamiliale", data.get(i)[3])
                    .append("nbEnfantsACharge", data.get(i)[4])
                    .append("deuxiemeVoiture", data.get(i)[5])
                    .append("immatriculation", data.get(i)[6]));
        }
        System.out.println("insertion des documents clients");
    }
}
