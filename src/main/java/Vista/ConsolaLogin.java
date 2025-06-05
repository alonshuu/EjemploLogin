package Vista;

import Controlador.Login;
import Controlador.SesionActiva;
import Modelo.DatosLogin;

import java.util.Scanner;

/**
 * Vista principal del sistema.
 * Interactúa con el usuario mediante consola.
 */
public class ConsolaLogin {
    private final Scanner scanner = new Scanner(System.in);
    private final DatosLogin datos = new DatosLogin();
    private final Login login = new Login();

    /**
     * Controla el ciclo principal del menú del sistema.
     */
    public void menu() {
        int opcion = -1;
        while (opcion != 2) {
            mostrarOpciones();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    manejarLogin();
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    /**
     * Solicita usuario y contraseña y maneja la autenticación.
     */
    private void manejarLogin() {
        System.out.print("Ingrese usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese clave: ");
        String clave = scanner.nextLine();

        boolean autenticado = login.auntenticar(usuario, clave, datos);
        if (autenticado) {
            System.out.println("Login exitoso.");
            SesionActiva sesion = new SesionActiva(usuario);
            sesion.menuSesion();
        } else {
            System.out.println("Usuario o clave incorrectos.");
        }
    }

    /**
     * Muestra el menú principal.
     */
    private void mostrarOpciones() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
