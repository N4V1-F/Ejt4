package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* ------------------------------------------------------------
   DAO PRÉSTAMOS
------------------------------------------------------------ */
class PrestamoDAO {
    MongoCollection<Document> col = MongoConnection.getDatabase().getCollection("prestamos");

    public void crear(ObjectId idUsuario, ObjectId idLibro) {
        Document d = new Document()
                .append("idUsuario", idUsuario)
                .append("idLibro", idLibro)
                .append("fechaPrestamo", new Date())
                .append("fechaDevolucion", null)
                .append("estado", "activo");
        col.insertOne(d);
    }

    public List<Document> activos() {
        return col.find(Filters.eq("estado", "activo")).into(new ArrayList<>());
    }

    public List<Document> porUsuario(ObjectId idUsuario) {
        List<Document> lista = col.find(Filters.eq("idUsuario", idUsuario))
                .sort(new Document("estado", -1))
                .into(new ArrayList<>());
        return lista;
    }

    public void devolver(String id) {
        col.updateOne(Filters.eq("_id", new ObjectId(id)),
                new Document("$set",
                        new Document("estado", "devuelto")
                                .append("fechaDevolucion", new Date())));
    }
}
