package Launcher;

import Vista.ConsolaLogin;

/**
 * Punto de entrada principal del sistema.
 */
public class Inicio {
    public static void main(String[] args) {
        String archivoUsuarios = "login.txt";

        ConsolaLogin consola = new ConsolaLogin(archivoUsuarios);
        consola.menu();
    }
}

