package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/* ------------------------------------------------------------
   CONEXIÓN A MONGODB
------------------------------------------------------------ */
class MongoConnection {
    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "biblioteca";
    private static MongoDatabase db;

    public static MongoDatabase getDatabase() {
        if (db == null) {
            MongoClient client = MongoClients.create(URI);
            db = client.getDatabase(DB_NAME);
        }
        return db;
    }
}
