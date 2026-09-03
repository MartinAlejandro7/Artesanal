package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.Negocio;

public class TestConsumoCliente {
	@Test
	public void probarConsumo() {

		Maquina maquinaA = new Maquina("Pilsener", "Rubia", 0.002, 8000, "17524");

		Negocio barDeMoe = new Negocio("BARDEMOE", maquinaA);

		Cliente cliente = new Cliente("Homero", "17587814");

		barDeMoe.cargarMaquinaA();

		barDeMoe.consumirCervezaMaquinaA(cliente, 100);

		assertEquals(7700, maquinaA.getCantidadActual(),0.0001);

		assertEquals(0.2, cliente.getTotalConsumido(),0.0001);

		barDeMoe.consumirCervezaMaquinaA(cliente, 200);

		assertEquals(7500, maquinaA.getCantidadActual(),0.0001);

		assertEquals(0.6, cliente.getTotalConsumido(),0.0001);
		
		barDeMoe.consumirCervezaMaquinaA(cliente, 300);

		assertEquals(7200, maquinaA.getCantidadActual(),0.0001);

		assertEquals(1.2, cliente.getTotalConsumido(),0.0001);

	}
}
