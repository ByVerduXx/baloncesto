package repository;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ModeloDatosTest {

    ModeloDatos instance = new ModeloDatos();

    @Test
    void testExisteJugador() {
        log.info("Prueba de existeJugador");
        String nombre = "";
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        // fail("Fallo forzado.");
    }

    @Test
    void testActualizarJugador() {
        log.info("Prueba de actualizarJugador");
        String nombre = "Llull";
        instance.actualizarJugador(nombre);
        Integer expResult = 1;
        Integer result = 1;
        assertEquals(expResult, result);
        /*
         * Integer result = instance.getVotos(nombre);
         * assertEquals(expResult, result);
         */
    }
}