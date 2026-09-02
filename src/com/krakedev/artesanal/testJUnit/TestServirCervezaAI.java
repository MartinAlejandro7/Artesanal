package com.krakedev.artesanal.testJUnit;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

class TestServirCervezaAI {

    // Tolerancia para comparaciones de valores double
    private static final double TOLERANCIA = 0.001;

    // --------------------------------------------------------------------
    // Caso 1: Sirve cantidad exacta disponible → descuenta y retorna valor
    // Valida: Cuando hay suficiente cerveza, se resta correctamente y se
    // retorna el precio = cantidad × precioPorMl
    // --------------------------------------------------------------------
    @Test
    void testServirCantidadExactaDisponible() {
        // Usamos constructor con capacidad personalizada
        Maquina maq = new Maquina("Pilsen", "Suave", 0.05, 5000, "7895");
        maq.llenarMaquina(); // deja cantidadActual = 4900 ml

        double cantidadSolicitada = 1000;
        double valorEsperado = cantidadSolicitada * maq.getPrecioPorMl(); // 1000 × 0.05 = 50
        double cantidadEsperadaDespues = maq.getCantidadActual() - cantidadSolicitada; // 4900 - 1000 = 3900

        // Ejecutar el método
        double valorRetornado = maq.servirCerveza(cantidadSolicitada);

        // Verificaciones
        assertEquals(valorEsperado, valorRetornado, TOLERANCIA, "El valor a pagar es incorrecto");
        assertEquals(cantidadEsperadaDespues, maq.getCantidadActual(), TOLERANCIA, "No se descontó la cantidad correcta");
    }

    // --------------------------------------------------------------------
    // Caso 2: Sirve cantidad menor a la disponible → descuenta y retorna valor
    // Valida: Cantidad solicitada menor a lo disponible → operación exitosa
    // --------------------------------------------------------------------
    @Test
    void testServirCantidadMenorDisponible() {
        // Usamos constructor con capacidad por defecto (10000 ml)
        Maquina maq = new Maquina("IPA", "Amarga", 0.08, "1234");
        maq.recargarCerveza(2000); // carga 2000 ml

        double cantidadSolicitada = 800;
        double valorEsperado = cantidadSolicitada * maq.getPrecioPorMl(); // 800 × 0.08 = 64
        double cantidadEsperada = 2000 - 800; // 1200 ml restantes

        double resultado = maq.servirCerveza(cantidadSolicitada);

        assertEquals(valorEsperado, resultado, TOLERANCIA, "Valor calculado incorrecto");
        assertEquals(cantidadEsperada, maq.getCantidadActual(), TOLERANCIA, "Cantidad restante incorrecta");
    }

    // --------------------------------------------------------------------
    // Caso 3: Cantidad solicitada MAYOR a la disponible → NO sirve, retorna 0
    // Valida: Cuando no hay suficiente cerveza → no modifica cantidad y retorna 0
    // --------------------------------------------------------------------
    @Test
    void testCantidadInsuficienteRetornaCero() {
        Maquina maq = new Maquina("Stout", "Oscura", 0.06, 3000, "7564");
        maq.recargarCerveza(500); // disponible: 500 ml

        double cantidadSolicitada = 600; // mayor a lo que hay
        double cantidadAntes = maq.getCantidadActual(); // 500 ml

        double resultado = maq.servirCerveza(cantidadSolicitada);

        assertEquals(0, resultado, TOLERANCIA, "Debe retornar 0 cuando no hay suficiente");
        assertEquals(cantidadAntes, maq.getCantidadActual(), TOLERANCIA, "No debe modificarse la cantidad disponible");
    }

    // --------------------------------------------------------------------
    // Caso 4: Maquina VACÍA → cualquier cantidad retorna 0 y no cambia nada
    // Valida: Estado inicial (cantidadActual = 0) → nunca sirve
    // --------------------------------------------------------------------
    @Test
    void testMaquinaVaciaRetornaCero() {
        // Constructor sin llenar → cantidadActual = 0
        Maquina maq = new Maquina("Trigo", "Clara", 0.07, 8000,"4569");

        double resultado = maq.servirCerveza(100);

        assertEquals(0, resultado, TOLERANCIA, "Máquina vacía debe retornar 0");
        assertEquals(0, maq.getCantidadActual(), TOLERANCIA, "La cantidad debe seguir en 0");
    }

    // --------------------------------------------------------------------
    // Caso 5: Solicita CERO mililitros → comportamiento según lógica:
    // Al cumplirse cantidadActual ≥ 0 → descuenta 0 y retorna 0
    // Valida: Límite con cantidad = 0
    // --------------------------------------------------------------------
    @Test
    void testServirCantidadCero() {
        Maquina maq = new Maquina("Roja", "Tostada", 0.10,"2589");
        maq.recargarCerveza(1500);
        double cantidadEsperada = maq.getCantidadActual(); // sigue igual

        double resultado = maq.servirCerveza(0);

        assertEquals(0, resultado, TOLERANCIA, "Servir 0 ml debe retornar 0");
        assertEquals(cantidadEsperada, maq.getCantidadActual(), TOLERANCIA, "La cantidad no debe modificarse");
    }

    // --------------------------------------------------------------------
    // Caso 6: Prueba con constructor de DOS parámetros y capacidad por defecto
    // Valida: El segundo constructor funciona correctamente al servir
    // --------------------------------------------------------------------
    @Test
    void testServirConConstructorDosParametros() {
        // Constructor: nombre, descripcion, precio → capacidad 10000 por defecto
        Maquina maq = new Maquina("Rubia", "Suave", 0.04,"1478");
        maq.llenarMaquina(); // capacidad = 10000 - 100 = 9900 ml

        double cantidad = 9800; // sirve TODO lo disponible
        double valorEsperado = cantidad * 0.04; // 396.0

        double resultado = maq.servirCerveza(cantidad);

        assertEquals(valorEsperado, resultado, TOLERANCIA, "Valor total incorrecto al servir todo");
        assertEquals(0, maq.getCantidadActual(), TOLERANCIA, "La máquina debe quedar vacía");
    }
}
