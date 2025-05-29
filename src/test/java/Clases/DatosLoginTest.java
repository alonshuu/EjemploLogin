package Clases;

import Modelo.DatosLogin;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DatosLoginTest {

    @Test
    public void testCargarUsuarios_ARCHIVONOEXISTE() {
        ByteArrayOutputStream error = new ByteArrayOutputStream();
        System.setErr(new PrintStream(error));

        DatosLogin datos = new DatosLogin();
        datos.cargarUsuarios("archivotxt");

        String salidaError = error.toString();
        assertTrue(salidaError.contains("No se pudo cargar el archivo login.txt"));
    }

}