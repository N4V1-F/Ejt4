package org.example;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

/* ------------------------------------------------------------
   MODELOS
------------------------------------------------------------ */



/* ------------------------------------------------------------
   DAO RESEÑAS
------------------------------------------------------------ */

/* ------------------------------------------------------------
   SERVICIO CON MENÚ
------------------------------------------------------------ */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static UsuarioDAO uDao = new UsuarioDAO();
    static LibroDAO lDao = new LibroDAO();
    static PrestamoDAO pDao = new PrestamoDAO();
    static ReseniaDAO rDao = new ReseniaDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== MENÚ BIBLIOTECA ===");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Agregar libro");
            System.out.println("3. Mostrar libros");
            System.out.println("4. Buscar libros");
            System.out.println("5. Registrar préstamo");
            System.out.println("6. Mostrar préstamos activos");
            System.out.println("7. Ver préstamos de un usuario");
            System.out.println("8. Devolver libro");
            System.out.println("9. Agregar reseña");
            System.out.println("10. Ver reseñas de un libro");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            int op = Integer.parseInt(sc.nextLine());

            try {
                switch (op) {
                    case 1 -> registrarUsuario();
                    case 2 -> agregarLibro();
                    case 3 -> mostrarLibros();
                    case 4 -> buscarLibro();
                    case 5 -> registrarPrestamo();
                    case 6 -> mostrarPrestamosActivos();
                    case 7 -> prestamosPorUsuario();
                    case 8 -> devolverLibro();
                    case 9 -> agregarResenia();
                    case 10 -> verResenias();
                    case 0 -> System.exit(0);
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /* ------------------- FUNCIONES DEL MENÚ ------------------- */

    static void registrarUsuario() {
        System.out.print("Nombre: ");
        String n = sc.nextLine();
        System.out.print("Apellidos: ");
        String a = sc.nextLine();
        System.out.print("Correo: ");
        String c = sc.nextLine();
        System.out.print("Contraseña: ");
        String p = sc.nextLine();

        uDao.crear(new Usuario(n, a, c, p));
        System.out.println("Usuario registrado.");
    }

    static void agregarLibro() {
        System.out.print("Título: "); String t = sc.nextLine();
        System.out.print("Autor: "); String a = sc.nextLine();
        System.out.print("ISBN: "); String i = sc.nextLine();
        System.out.print("Categoría: "); String c = sc.nextLine();
        System.out.print("Descripción: "); String d = sc.nextLine();

        lDao.crear(new Libro(t, a, i, c, d));
        System.out.println("Libro agregado.");
    }

    static void mostrarLibros() {
        for (Document doc : lDao.listar()) {
            System.out.println(doc.toJson());
        }
    }

    static void buscarLibro() {
        System.out.print("Buscar: ");
        String txt = sc.nextLine();
        for (Document doc : lDao.buscar(txt)) {
            System.out.println(doc.toJson());
        }
    }

    static void registrarPrestamo() {
        System.out.print("ID Usuario: ");
        ObjectId u = new ObjectId(sc.nextLine());
        System.out.print("ID Libro: ");
        ObjectId l = new ObjectId(sc.nextLine());

        pDao.crear(u, l);
        System.out.println("Préstamo registrado.");
    }

    static void mostrarPrestamosActivos() {
        for (Document d : pDao.activos()) {
            System.out.println(d.toJson());
        }
    }

    static void prestamosPorUsuario() {
        System.out.print("ID Usuario: ");
        ObjectId id = new ObjectId(sc.nextLine());
        for (Document d : pDao.porUsuario(id)) {
            System.out.println(d.toJson());
        }
    }

    static void devolverLibro() {
        System.out.print("ID Préstamo: ");
        String id = sc.nextLine();
        pDao.devolver(id);
        System.out.println("Libro devuelto.");
    }

    static void agregarResenia() {
        System.out.print("ID Libro: ");
        ObjectId idLibro = new ObjectId(sc.nextLine());
        System.out.print("ID Usuario: ");
        ObjectId idUsuario = new ObjectId(sc.nextLine());
        System.out.print("Calificación (1-5): ");
        int cal = Integer.parseInt(sc.nextLine());
        System.out.print("Comentario: ");
        String com = sc.nextLine();

        Resenia r = new Resenia();
        r.idLibro = idLibro;
        r.idUsuario = idUsuario;
        r.calificacion = cal;
        r.comentario = com;
        r.fechaCreacion = new Date();

        rDao.crear(r);
        System.out.println("Reseña agregada.");
    }

    static void verResenias() {
        System.out.print("ID Libro: ");
        ObjectId idLibro = new ObjectId(sc.nextLine());
        for (Document d : rDao.porLibro(idLibro)) {
            System.out.println(d.toJson());
        }
    }
}
