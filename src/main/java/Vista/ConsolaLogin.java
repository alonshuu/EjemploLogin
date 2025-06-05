package Vista;

import Controlador.Login;
import Controlador.SesionActiva;
import Modelo.DatosLogin;
import Modelo.Usuario;

import java.util.Scanner;
import java.util.List;

/**
 * Vista principal del sistema.
 * Interactúa con el usuario mediante consola.
 */
public class ConsolaLogin {

    private final Scanner scanner;
    private final DatosLogin datosLogin;
    private final Login login;

    public ConsolaLogin(String archivoUsuarios) {
        this.scanner = new Scanner(System.in);
        this.datosLogin = new DatosLogin(archivoUsuarios);
        this.login = new Login();
    }

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

    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void manejarLogin() {
        System.out.print("Ingrese usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese clave: ");
        String clave = scanner.nextLine();

        List<Usuario> listaUsuarios = datosLogin.getUsuarios();
        boolean autenticado = login.autenticar(usuario, clave, listaUsuarios);

        if (autenticado) {
            System.out.println("Inicio de sesión exitoso.");
            SesionActiva sesion = new SesionActiva(usuario);
            sesion.menuSesion();
        } else {
            System.out.println("Usuario o clave incorrectos.");
        }
    }
}
