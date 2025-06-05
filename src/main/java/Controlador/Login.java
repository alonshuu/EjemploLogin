package Controlador;

import Modelo.Usuario;
import java.util.List;

/**
 * Controlador que gestiona la autenticación de usuarios.
 */
public class Login {

    /**
     * Autentica al usuario buscando coincidencia exacta en lista de usuarios.
     *
     * @param nombreUsuario nombre ingresado
     * @param claveIngresada clave ingresada
     * @param usuarios lista de usuarios cargados
     * @return true si la autenticación es exitosa, false si falla
     */
    public boolean autenticar(String nombreUsuario, String claveIngresada, List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario) &&
                    usuario.getClave().equals(claveIngresada)) {
                return true;
            }
        }
        return false;
    }
}

