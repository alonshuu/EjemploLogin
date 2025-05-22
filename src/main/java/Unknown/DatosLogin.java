package Unknown;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DatosLogin {
    public ArrayList<String> credenciales = new ArrayList<>();



    public DatosLogin() {
        cargarUsuarios("login.txt");
    }


    public void cargarUsuarios(String archivo) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(archivo)))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty() && linea.contains(";")){
                    credenciales.add(linea);
                }
            }
        } catch (IOException | NullPointerException e){
            System.err.println("No se pudo cargar el archivo login.txt");
        }

    }
 }
