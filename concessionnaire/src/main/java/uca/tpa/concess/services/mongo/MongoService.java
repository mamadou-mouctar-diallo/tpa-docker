package uca.tpa.concess.services.mongo;

import com.mongodb.client.MongoCollection;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.bson.Document;
import uca.tpa.concess.services.connection.MongodbConnectionService;

import javax.print.Doc;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.Arrays.asList;

public class MongoService {
    private static MongoCollection<Document> collection;
    private static final MongodbConnectionService mongoConnectionService = new MongodbConnectionService();

    private static int batchSize = 10000;

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
                        for (int i = 228648; i < data.size(); i += batchSize) {
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
            Document document = new Document("age", data.get(i)[0])
                    .append("sexe", data.get(i)[1])
                    .append("taux", data.get(i)[2])
                    .append("situationFamiliale", data.get(i)[3])
                    .append("nbEnfantsACharge", data.get(i)[4])
                    .append("deuxiemeVoiture", data.get(i)[5]);
            Document documentValidated = marketingValidation(document);
            if (documentValidated != null) {
                collection.insertOne(documentValidated);
            }
        }
        System.out.println("insertion des documents marketing");
    }

    private static void persisteImmatriculation(List<String[]> data) {

        System.out.println("obtention de la collection immatriculations");
        collection = mongoConnectionService.getMongoDatabase().getCollection("immatriculations");
        for (int i = 1; i < data.size(); i++) {
            Document document = new Document("immatriculation", data.get(i)[0])
                    .append("marque", data.get(i)[1])
                    .append("nom", data.get(i)[2])
                    .append("puissance", data.get(i)[3])
                    .append("longueur", data.get(i)[4])
                    .append("nbPlaces", data.get(i)[5])
                    .append("nbPortes", data.get(i)[6])
                    .append("couleur", data.get(i)[7])
                    .append("occasion", data.get(i)[8])
                    .append("prix", data.get(i)[9]);
            Document documentValidated = immatValidation(document);
            if (documentValidated != null) {
                collection.insertOne(documentValidated);
            }
        }
        System.out.println("insertion des documents immatriculations");
    }

    private static void persisteCo2(List<String[]> data) {
        System.out.println("obtention de la collection co2");
        collection = mongoConnectionService.getMongoDatabase().getCollection("co2");

        for (int i = 1; i < data.size(); i++) {
            Document document = new  Document("modele", data.get(i)[0])
                    .append("bonusMalus", data.get(i)[1])
                    .append("rejetsCO2", data.get(i)[2])
                    .append("coutEnergie", data.get(i)[3]);
            Document documentValidated = co2Validation(document);
            if (documentValidated != null) {
                collection.insertOne(documentValidated);
            }
        }
        System.out.println("insertion des documents co2");
    }

    private static void persisteCatalogue(List<String[]> data) {
        System.out.println("obtention de la collection catalogue");
        collection = mongoConnectionService.getMongoDatabase().getCollection("catalogue");
        for (int i = 1; i < data.size(); i++) {
            Document document = new Document("marque", data.get(i)[0])
                    .append("nom", data.get(i)[1])
                    .append("puissance", data.get(i)[2])
                    .append("longueur", data.get(i)[3])
                    .append("nbPlaces", data.get(i)[4])
                    .append("nbPortes", data.get(i)[5])
                    .append("couleur", data.get(i)[6])
                    .append("occasion", data.get(i)[7])
                    .append("prix", data.get(i)[8]);
            Document documentValidated = catalogueValidation(document);
            if (documentValidated != null) {
                collection.insertOne(documentValidated);
            }
        }
        System.out.println("insertion des documents catalogue");
    }

    private static void persisteClients(List<String[]> data) {
        System.out.println("obtention de la collection clients");
        collection = mongoConnectionService.getMongoDatabase().getCollection("clients");
        for (int i = 1; i < data.size(); i++) {
            Document documet = new Document("age", data.get(i)[0])
                    .append("sexe", data.get(i)[1])
                    .append("taux", data.get(i)[2])
                    .append("situationFamiliale", data.get(i)[3])
                    .append("nbEnfantsACharge", data.get(i)[4])
                    .append("deuxiemeVoiture", data.get(i)[5])
                    .append("immatriculation", data.get(i)[6]);
            Document documentValidated = clientValidation(documet);
            if (documentValidated != null) {
                collection.insertOne(documentValidated);
            }
        }
        System.out.println("insertion des documents clients");
    }

    private static Document immatValidation(Document document) {
        if (document.get("immatriculation") == " "
                || document.get("marque") == " "
                || document.get("nom") == " "
                || document.get("puissance") == " "
                || document.get("longueur") == " "
                || document.get("nbPlaces") == " "
                || document.get("nbPortes") == " "
                || document.get("couleur") == " "
                || document.get("occasion") == " "
                || document.get("prix") == " ") {
            return null;
        }
        if (document.get("immatriculation").equals("?")) {
            document.put("immatriculation", "0000 XX 00");
        }
        if (document.get("marque").equals("?")) {
            document.put("marque", "marque inconnue");
        }
        if (document.get("nom").equals("?")) {
            document.put("nom", "nom inconnu");
        }
        if (document.get("puissance").equals("?")) {
            document.put("puissance", "0");
        }
        if (document.get("longueur").equals("?")) {
            document.put("longueur", "0");
        }
        if (document.get("nbPlaces").equals("?")) {
            document.put("nbPlaces", "0");
        }
        if (document.get("nbPortes").equals("?")) {
            document.put("nbPortes", "0");
        }
        if (document.get("couleur").equals("?")) {
            document.put("couleur", "couleur inconnue");
        }
        if (document.get("occasion").equals("?")) {
            document.put("occasion", "false");
        }
        if (document.get("prix").equals("?")) {
            document.put("prix", "0");
        }

        return document;
    }
    private static Document marketingValidation(Document document){
        if (document.get("age") == " "
                || document.get("sexe") == " "
                || document.get("taux") == " "
                || document.get("situationFamiliale") == " "
                || document.get("nbEnfantsACharge") == " "
                || document.get("deuxiemeVoiture") == " ") {
            return null;
        }
        if (document.get("age").equals("?")) {
            document.put("age", "0");
        }
        if (document.get("sexe").equals("?")) {
            document.put("sexe", "sexe inconnu");
        }
        if (document.get("taux").equals("?")) {
            document.put("taux", "0");
        }
        if (document.get("situationFamiliale").equals("?")) {
            document.put("situationFamiliale", "situation familiale inconnue");
        }
        if (document.get("nbEnfantsACharge").equals("?")) {
            document.put("nbEnfantsACharge", "0");
        }
        if (document.get("deuxiemeVoiture").equals("?")) {
            document.put("deuxiemeVoiture", "false");
        }
        return document;
    }

    private static Document co2Validation(Document document){
        if (document.get("modele") == " "
                || document.get("bonusMalus") == " "
                || document.get("rejetsCO2") == " "
                || document.get("coutEnergie") == " ") {
            return null;
        }
        if (document.get("modele").equals("?")) {
            document.put("modele", "modele inconnu");
        }
        if (document.get("bonusMalus").equals("?")) {
            document.put("bonusMalus", "0");
        }
        if (document.get("rejetsCO2").equals("?")) {
            document.put("rejetsCO2", "0");
        }
        if (document.get("coutEnergie").equals("?")) {
            document.put("coutEnergie", "0");
        }
        return document;
    }

    private static Document catalogueValidation(Document document){

        if (document.get("marque") == " "
                || document.get("nom") == " "
                || document.get("puissance") == " "
                || document.get("longueur") == " "
                || document.get("nbPlaces") == " "
                || document.get("nbPortes") == " "
                || document.get("couleur") == " "
                || document.get("occasion") == " "
                || document.get("prix") == " ") {
            return null;
        }
        if (document.get("marque").equals("?")) {
            document.put("marque", "marque inconnue");
        }
        if (document.get("nom").equals("?")) {
            document.put("nom", "nom inconnu");
        }
        if (document.get("puissance").equals("?")) {
            document.put("puissance", "0");
        }
        if (document.get("longueur").equals("?")) {
            document.put("longueur", "0");
        }
        if (document.get("nbPlaces").equals("?")) {
            document.put("nbPlaces", "0");
        }
        if (document.get("nbPortes").equals("?")) {
            document.put("nbPortes", "0");
        }
        if (document.get("couleur").equals("?")) {
            document.put("couleur", "couleur inconnue");
        }
        if (document.get("occasion").equals("?")) {
            document.put("occasion", "false");
        }
        if (document.get("prix").equals("?")) {
            document.put("prix", "0");
        }
        return document;
    }

    private static Document clientValidation(Document document){
        if (document.get("age") == " "
                || document.get("sexe") == " "
                || document.get("taux") == " "
                || document.get("situationFamiliale") == " "
                || document.get("nbEnfantsACharge") == " "
                || document.get("deuxiemeVoiture") == " "
                || document.get("immatriculation") == " ") {
            return null;
        }
        if (document.get("age").equals("?")) {
            document.put("age", "0");
        }
        if (document.get("sexe").equals("?")) {
            document.put("sexe", "sexe inconnu");
        }
        if (document.get("taux").equals("?")) {
            document.put("taux", "0");
        }
        if (document.get("situationFamiliale").equals("?")) {
            document.put("situationFamiliale", "situation familiale inconnue");
        }
        if (document.get("nbEnfantsACharge").equals("?")) {
            document.put("nbEnfantsACharge", "0");
        }
        if (document.get("deuxiemeVoiture").equals("?")) {
            document.put("deuxiemeVoiture", "false");
        }
        if (document.get("immatriculation").equals("?")) {
            document.put("immatriculation", "0000 XX 00");
        }
        return document;
    }
}
