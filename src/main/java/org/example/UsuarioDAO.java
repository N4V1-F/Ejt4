package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/* ------------------------------------------------------------
   DAO USUARIOS
------------------------------------------------------------ */
class UsuarioDAO {
    MongoCollection<Document> col = MongoConnection.getDatabase().getCollection("usuarios");

    public void crear(Usuario u) {
        Document d = new Document()
                .append("nombre", u.nombre)
                .append("apellidos", u.apellidos)
                .append("correo", u.correo)
                .append("contraseña", u.contraseña);
        col.insertOne(d);
    }

    public List<Document> listar() {
        return col.find().into(new ArrayList<>());
    }

    public void actualizar(String id, Usuario u) {
        col.updateOne(Filters.eq("_id", new ObjectId(id)),
                new Document("$set",
                        new Document("nombre", u.nombre)
                                .append("apellidos", u.apellidos)
                                .append("correo", u.correo)));
    }

    public void eliminar(String id) {
        col.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }
}
