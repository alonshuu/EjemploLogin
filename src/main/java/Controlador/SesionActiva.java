package Controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SesionActiva {
    private final String usuario;
    private final Scanner scanner = new Scanner(System.in);

    public SesionActiva(String usuario) {
        this.usuario = usuario;

    }

    public void menuSesion() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\nBienvenido " + usuario);
            System.out.println("1. Escribir tarea");
            if (esAdmin()) {
                System.out.println("2. Registrar nuevo usuario");
            }
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    escribirTarea();
                    break;
                case 2:
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


    public void escribirTarea() {
        System.out.print("Ingrese la tarea a registrar: ");
        String tarea = scanner.nextLine();

        String nombreArchivo = usuario + "_tareas.txt";
        try (FileWriter fw = new FileWriter(nombreArchivo, true)) {
            fw.write(tarea + "\n");
            System.out.println("Tarea registrada y guardada en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al escribir la tarea: " + e.getMessage());
        }
    }


    private void registrarUsuario() {
        System.out.print("Ingrese nuevo usuario: ");
        String nuevoUsuario = scanner.nextLine();
        System.out.print("Ingrese clave: ");
        String nuevaClave = scanner.nextLine();

        Modelo.GestorUsuarios gestor = new Modelo.GestorUsuarios();
        if (gestor.registrar(nuevoUsuario, nuevaClave)) {
            System.out.println("Usuario registrado con éxito.");
        } else {
            System.out.println("No se pudo registrar el usuario (puede que ya exista).");
        }
    }

    private boolean esAdmin() {
        try (Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("admin_todo.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.equals(usuario)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer admin_todo.txt");
        }
        return false;
    }



}
