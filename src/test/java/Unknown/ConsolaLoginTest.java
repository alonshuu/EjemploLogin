package Unknown;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsolaLoginTest {
    private DatosLogin datos;
    private Login login;
    private ConsolaLogin clogin;

    @BeforeEach
    void setup() {
        datos = new DatosLogin();
        login = new Login();
        clogin = new ConsolaLogin();
    }

    @Test
    public void LoginValido(){
        assertTrue(login.auntenticar("xiao","ola123",datos));
    }


    @Test
    public void LoginInvalido() {
        assertFalse(login.auntenticar("testusuario","testclave",datos));
    }

}