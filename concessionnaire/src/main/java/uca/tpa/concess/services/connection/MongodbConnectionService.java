package uca.tpa.concess.services.connection;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongodbConnectionService {
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    public void startConnection(){
        MongoClientURI uri = new MongoClientURI("mongodb://root:rootpassword@localhost:27017/?authSource=admin");
        this.mongoClient =  new MongoClient(uri);
        this.setMongoDatabase(this.mongoClient.getDatabase("concessionnaire"));
        String databaseName = this.getMongoDatabase().getName();
        System.out.println("Connexion établie à la base de données : " + databaseName);
    }

    public MongoDatabase getMongoDatabase(){
        return this.mongoDatabase;
    }

    private void setMongoDatabase(MongoDatabase concessionnaire) {
        this.mongoDatabase = concessionnaire;
    }

    public void stopConnection(){
        this.mongoClient.close();
        System.out.println("Connexion fermée");
    }
}
