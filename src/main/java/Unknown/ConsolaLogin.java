package Unknown;

import java.util.Scanner;

/**
 * Clase responsable de interactuar con el usuario por consola.
 * Controla el menú principal y el flujo de login.
 */
public class ConsolaLogin {
    Scanner scanner = new Scanner(System.in);
    DatosLogin datos = new DatosLogin();
    Login login = new Login();

    /**
     * Controla el ciclo principal del menú del sistema.
     */
    public void menu() {
        mostrarOpciones();
        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();
        ejecutarOpcion(opcion);
    }

    /**
     * Muestra las opciones disponibles para el usuario.
     */
    private void mostrarOpciones() {
        System.out.println("[1] Iniciar sesión" );
        System.out.println("[2] Salir.");
    }

    /**
     * Ejecuta la opción seleccionada por el usuario.
     *
     * @param opcion opción ingresada por el usuario
     */
    private void ejecutarOpcion(String opcion) {
        switch (opcion) {
            case "1":
                manejarLogin();
                break;
            case "2":
                break;
            default:
                System.out.println("Opción no válida, por favor intente de nuevo.");
        }
    }

    /**
     * Solicita usuario y contraseña, y muestra el resultado.
     */
    public void manejarLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el usuario por favor");
        String usuario = scanner.next();
        System.out.println("Ingrese la clave por favor");
        String contrasena = scanner.next();

        if (login.auntenticar(usuario, contrasena, datos)){
            System.out.println("Login Exitoso!");
            return;
        }
        System.out.println("Usuario o clave incorrecta");
    }
}
