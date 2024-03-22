package org.cyberpsych.services;
import org.cyberpsych.BotConfig;
import java.util.List;
import java.util.Random;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ToD {
    Random r = new Random();
    public void getRandom(SendMessage str){
        String randomString = "";
        if(r.nextInt(2)==0) {
            randomString = getRandomDare();
            str.setText("Dare - " + randomString);
        }
        else {
            randomString = getRandomTruth();
            str.setText("Truth - " + randomString);
        }
    }

    public String getRandomTruth(){
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017/");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase(BotConfig.DB_NAME);
        MongoCollection<Document> collection = database.getCollection(BotConfig.COLLECTION_NAME);
        FindIterable<Document> documents = collection.find();
        String truth = "";
        FindIterable<Document> docs = collection.find();
        for (Document doc:docs) {
            List<String> truth_list = (List<String>) doc.get("truth");
            int randomIndex = new Random().nextInt(truth_list.size());
            truth = truth_list.get(randomIndex);
        }
        return truth;
    }
    public String getRandomDare(){
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017/");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase(BotConfig.DB_NAME);
        MongoCollection<Document> collection = database.getCollection(BotConfig.COLLECTION_NAME);
        FindIterable<Document> documents = collection.find();
        String dare = "";
        FindIterable<Document> docs = collection.find();
        for (Document doc:docs) {
            List<String> truth_list = (List<String>) doc.get("dare");
            int randomIndex = new Random().nextInt(truth_list.size());
            dare = truth_list.get(randomIndex);
        }
        return dare;
    }
}
