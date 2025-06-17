package biblioteca.servicios;

import biblioteca.modelo.Libro;
import biblioteca.modelo.Usuario;
import biblioteca.excepciones.LibroNoEncontradoException;
import biblioteca.excepciones.LibroYaPrestadoException;

import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {
    public boolean usuarioExiste(String rut) {
        return usuarios.containsKey(rut);
    }

    private ArrayList<Libro> libros;
    private HashMap<String, Usuario> usuarios;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new HashMap<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getRut(), usuario);
    }

    public void prestarLibro(String titulo) throws LibroNoEncontradoException, LibroYaPrestadoException {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                if (libro.isPrestado()) {
                    throw new LibroYaPrestadoException("El libro ya está prestado.");
                }
                libro.prestar();
                System.out.println("Libro prestado con éxito.");
                return;
            }
        }
        throw new LibroNoEncontradoException("Libro no encontrado en la biblioteca.");
    }

    public void mostrarLibros() {
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public void mostrarUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            System.out.println(usuario);
        }
    }
}