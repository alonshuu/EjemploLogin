package Modelo;

import java.io.*;

/**
 * Permite registrar nuevos usuarios en el sistema.
 */
public class GestorUsuarios {

    private final String archivoUsuarios;

    public GestorUsuarios(String archivoUsuarios) {
        this.archivoUsuarios = archivoUsuarios;
        verificarOCrearArchivo();
    }

    /**
     * Verifica si el archivo de usuarios existe, y si no, lo crea vacío.
     */
    private void verificarOCrearArchivo() {
        File archivo = new File(archivoUsuarios);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println("Archivo de usuarios creado: " + archivoUsuarios);
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de usuarios: " + e.getMessage());
            }
        }
    }
    public boolean registrar(String usuario, String clave) {
        if (usuarioExiste(usuario)) {
            return false;
        }
        try (FileWriter fw = new FileWriter(archivoUsuarios, true)) {
            fw.write(usuario + ";" + clave + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    private boolean usuarioExiste(String usuario) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || !linea.contains(";")) continue;

                String[] partes = linea.split(";");
                if (partes.length == 2 && partes[0].equals(usuario)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios: " + e.getMessage());
        }
        return false;
    }
}










