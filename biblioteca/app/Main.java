package biblioteca.app;

import biblioteca.modelo.Libro;
import biblioteca.modelo.Usuario;
import biblioteca.servicios.Biblioteca;
import biblioteca.excepciones.LibroNoEncontradoException;
import biblioteca.excepciones.LibroYaPrestadoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        do {
            System.out.println("\n--- Menú Biblioteca ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Mostrar libros");
            System.out.println("4. Prestar libro");
            System.out.println("5. Mostrar usuarios");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1:
                        System.out.print("Título del libro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Autor del libro: ");
                        String autor = scanner.nextLine();
                        biblioteca.agregarLibro(new Libro(titulo, autor));
                        System.out.println("Libro agregado exitosamente.");
                        break;

                    case 2:
                        System.out.print("RUT del usuario: ");
                        String rut = scanner.nextLine();
                        System.out.print("Nombre del usuario: ");
                        String nombre = scanner.nextLine();
                        biblioteca.agregarUsuario(new Usuario(rut, nombre));
                        System.out.println("Usuario registrado exitosamente.");
                        break;

                    case 3:
                        biblioteca.mostrarLibros();
                        break;

                    case 4:
                        System.out.print("RUT del usuario: ");
                        String rutUsuario = scanner.nextLine();
                        if (!biblioteca.usuarioExiste(rutUsuario)) {
                            System.out.println("El usuario no está registrado. Regístrate primero.");
                            break;
                        }
                        System.out.print("Título del libro a prestar: ");
                        String libroTitulo = scanner.nextLine();
                        try {
                            biblioteca.prestarLibro(libroTitulo);
                        } catch (LibroNoEncontradoException | LibroYaPrestadoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        biblioteca.mostrarUsuarios();
                        break;

                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número.");
                scanner.nextLine(); // limpiar input incorrecto
            }

        } while (opcion != 6);
    }
}