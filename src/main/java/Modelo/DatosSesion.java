package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Administra las tareas personales de un usuario.
 */
public class DatosSesion {
    private final String usuario;
    private final String archivoTareas;
    private final List<Tarea> tareas;

    /**
     * Constructor que inicializa el archivo y carga las tareas.
     *
     * @param usuario nombre del usuario activo
     */
    public DatosSesion(String usuario) {
        this.usuario = usuario;
        this.archivoTareas = usuario + "_todo.txt";
        this.tareas = new ArrayList<>();
        verificarOCrearArchivo();
        cargarTareas();
    }

    /**
     * Verifica si el archivo de tareas existe, y si no, lo crea vacío.
     */
    private void verificarOCrearArchivo() {
        File archivo = new File(archivoTareas);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println("Archivo de tareas creado: " + archivoTareas);
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de tareas: " + e.getMessage());
            }
        }
    }

    /**
     * Carga las tareas existentes desde el archivo.
     */
    private void cargarTareas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoTareas))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    tareas.add(new Tarea(linea));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer las tareas: " + e.getMessage());
        }
    }

    /**
     * Agrega una nueva tarea y la guarda en el archivo.
     *
     * @param descripcion contenido de la tarea
     */
    public void agregarTarea(String descripcion) {
        Tarea nueva = new Tarea(descripcion);
        tareas.add(nueva);
        guardarTareaEnArchivo(nueva);
        System.out.println("Tarea agregada correctamente.");
    }

    /**
     * Guarda una tarea nueva al final del archivo.
     *
     * @param tarea tarea a guardar
     */
    private void guardarTareaEnArchivo(Tarea tarea) {
        try (FileWriter fw = new FileWriter(archivoTareas, true)) {
            fw.write(tarea.getDescripcion() + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar la tarea: " + e.getMessage());
        }
    }

    /**
     * Muestra todas las tareas actuales del usuario.
     */
    public void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            System.out.println("\n--- TAREAS DE " + usuario + " ---");
            for (int i = 0; i < tareas.size(); i++) {
                System.out.println((i + 1) + ". " + tareas.get(i).getDescripcion());
            }
        }
    }
}
