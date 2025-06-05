package Controlador;

import Modelo.DatosSesion;
import Modelo.GestorUsuarios;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;






public class SesionActiva {
    private final String usuario;
    private final Scanner scanner;
    private final DatosSesion datosSesion;

    public SesionActiva(String usuario) {
        this.usuario = usuario;
        this.scanner = new Scanner(System.in);
        this.datosSesion = new DatosSesion(usuario);
    }

    public void menuSesion() {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    agregarTarea();
                    break;
                case 2:
                    mostrarTareas();
                    break;
                case 3:
                    if (esAdmin()) {
                        registrarUsuario();
                    } else {
                        System.out.println("No tienes permisos para registrar usuarios.");
                    }
                    break;
                case 0:
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- SESIÓN DE: " + usuario + " ---");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Mostrar tareas");
        if (esAdmin()) {
            System.out.println("3. Registrar nuevo usuario");
        }
        System.out.println("0. Cerrar sesión");
        System.out.print("Seleccione una opción: ");
    }

    private void agregarTarea() {
        System.out.print("Ingrese la nueva tarea: ");
        String descripcion = scanner.nextLine();
        datosSesion.agregarTarea(descripcion);
    }

    private void mostrarTareas() {
        datosSesion.mostrarTareas();
    }

    private void registrarUsuario() {
        System.out.print("Ingrese nuevo usuario: ");
        String nuevoUsuario = scanner.nextLine();
        System.out.print("Ingrese clave: ");
        String nuevaClave = scanner.nextLine();

        GestorUsuarios gestor = new GestorUsuarios("login.txt");
        if (gestor.registrar(nuevoUsuario, nuevaClave)) {
            System.out.println("Usuario registrado con éxito.");
        } else {
            System.out.println("El usuario ya existe.");
        }
    }

    private boolean esAdmin() {
        File archivo = new File("admin_todo.txt");
        if (!archivo.exists()) {
            return false;
        }
        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.equals(usuario)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer admin_todo.txt: " + e.getMessage());
        }
        return false;
    }
}

