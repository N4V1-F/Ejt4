package org.example;

import org.bson.types.ObjectId;

public class Libro {
    ObjectId id;
    String titulo, autor, isbn, categoria, descripcion;

    public Libro(String t, String a, String i, String c, String d) {
        titulo = t; autor = a; isbn = i; categoria = c; descripcion = d;
    }
}