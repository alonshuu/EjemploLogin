package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DatosLogin {

    private final String archivoUsuarios;
    private final ArrayList<Usuario> usuarios;

    public DatosLogin(String archivoUsuarios) {
        this.archivoUsuarios = archivoUsuarios;
        this.usuarios = new ArrayList<>();
        verificarOCrearArchivo();
        cargarUsuarios();
    }

    private void verificarOCrearArchivo() {
        File archivo = new File(archivoUsuarios);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println("Archivo " + archivoUsuarios + " creado.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    private void cargarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(archivoUsuarios)))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty() && linea.contains(";")) {
                    String[] partes = linea.split(";");
                    if (partes.length == 2) {
                        Usuario usuario = new Usuario(partes[0], partes[1]);
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No se pudo encontrar el archivo " + archivoUsuarios);
        }

    }

    /**
     * Retorna la lista de usuarios cargados.
     *
     * @return lista de usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
