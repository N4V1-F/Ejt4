package org.example;

import org.bson.types.ObjectId;

public class Usuario {
    ObjectId id;
    String nombre, apellidos, correo, contraseña;

    public Usuario(String nombre, String apellidos, String correo, String contraseña) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contraseña = contraseña;
    }
}
