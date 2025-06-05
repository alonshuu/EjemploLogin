package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatosSesion {
    private final String nombreArchivo;
    List<String> tareas = new ArrayList<>();

    public DatosSesion(String usuario){
        this.nombreArchivo = usuario + "_todo.txt";
    }



    private void crearArchivo() throws IOException {
        File file = new File(nombreArchivo);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null){
                    if (!line.trim().isEmpty()) {
                        tareas.add(line.trim());
                    }
                }
            }
        }
    }

    public boolean escribirTarea(String tarea) throws IOException {
        FileWriter filewriter = new FileWriter(nombreArchivo, true);
        filewriter.write(tarea+"n");
        filewriter.close();
        return false;
    }

    public void mostrarTareas() throws FileNotFoundException {
        FileReader reader = new FileReader(nombreArchivo);
        for (String tarea : tareas){
            System.out.println("-"+ tarea);
        }


    }




}
