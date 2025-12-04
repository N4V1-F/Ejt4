package org.example;

import org.bson.types.ObjectId;

import java.util.Date;

class Prestamo {
    ObjectId id;
    ObjectId idUsuario;
    ObjectId idLibro;
    Date fechaPrestamo;
    Date fechaDevolucion;
    String estado;
}
