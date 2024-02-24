package repository;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ModeloDatosTest {

    @Test
    void testExisteJugador() {
        log.info("Prueba de existeJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        // fail("Fallo forzado.");
    }

    @Test
    void testActualizarJugador() {
        log.info("Prueba de actualizarJugador");
        String nombre = "Llull";
        ModeloDatos instance = new ModeloDatos();
        instance.actualizarJugador(nombre);
        Integer expResult = 1;
        Integer result = instance.getVotos(nombre); // he tenido que crear este método para poder hacer la prueba
        assertEquals(expResult, result);
    }
}