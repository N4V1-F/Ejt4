package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/* ------------------------------------------------------------
   DAO LIBROS
------------------------------------------------------------ */
class LibroDAO {
    MongoCollection<Document> col = MongoConnection.getDatabase().getCollection("libros");

    public void crear(Libro l) {
        Document d = new Document()
                .append("titulo", l.titulo)
                .append("autor", l.autor)
                .append("isbn", l.isbn)
                .append("categoria", l.categoria)
                .append("descripcion", l.descripcion);
        col.insertOne(d);
    }

    public List<Document> listar() {
        return col.find().into(new ArrayList<>());
    }

    public List<Document> buscar(String txt) {
        return col.find(Filters.or(
                Filters.regex("titulo", txt, "i"),
                Filters.regex("autor", txt, "i"),
                Filters.regex("categoria", txt, "i")
        )).into(new ArrayList<>());
    }

    public void actualizar(String id, Libro l) {
        col.updateOne(Filters.eq("_id", new ObjectId(id)),
                new Document("$set",
                        new Document("titulo", l.titulo)
                                .append("autor", l.autor)
                                .append("isbn", l.isbn)
                                .append("categoria", l.categoria)
                                .append("descripcion", l.descripcion)));
    }

    public void eliminar(String id) {
        col.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }
}
