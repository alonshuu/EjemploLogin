package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class GestorUsuarios {
    private final String archivo = "login.txt";

    public GestorUsuarios() {
        File file = new File(archivo);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Archivo creado: " + archivo);
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    public boolean registrar(String usuario, String clave) {
        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(usuario + ";" + clave + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }
}

