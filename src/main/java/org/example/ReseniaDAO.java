package org.example;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReseniaDAO {
        MongoCollection<Document> col = MongoConnection.getDatabase().getCollection("resenias");

        public void crear(Resenia r) {
        Document d = new Document()
                .append("idLibro", r.idLibro)
                .append("idUsuario", r.idUsuario)
                .append("calificacion", r.calificacion)
                .append("comentario", r.comentario)
                .append("fechaCreacion", new Date());
        col.insertOne(d);
    }

        public List<Document> porLibro(ObjectId idLibro) {
        return col.find(Filters.eq("idLibro", idLibro)).into(new ArrayList<>());
    }

        public void actualizar(String id, int calif, String com) {
        col.updateOne(Filters.eq("_id", new ObjectId(id)),
                new Document("$set",
                        new Document("calificacion", calif)
                                .append("comentario", com)));
    }

        public void eliminar(String id) {
        col.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

}
