package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestGenerarCodigo {

	@Test
	public void probarGenerarCodigo() {
		NegocioMejorado negocio = new NegocioMejorado();

		String codigo = negocio.generarCodigo();

		assertTrue(codigo.contains("M-"));
	}

	@Test
	public void probarRecuperarMaquina() {
		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Taladro", "Taladro eléctrico", 10.50, "M-10");

		Maquina maquina = negocio.recuperarMaquina(negocio.getMaquinas().get(0).getCodigo());

		assertNotNull(maquina);
	}

	@Test
	public void probarMaquinaDuplicada() {
		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Taladro", "Taladro electrico", 10.50, "M-10");

		boolean resultado = negocio.agregarMaquina("Taladro", "Taladro electrico", 10.50, "M-10");

		assertFalse(resultado);
	}

	@Test
	public void probarConsumoCerveza() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.registrarCliente("Alejandro", "1723456789");

		negocio.agregarMaquina("Artesanal", "Cerveza artesanal", 0.05, "M-1");

		negocio.cargarMaquina();

		Cliente cliente = negocio.buscarClientePorCodigo(100);

		Maquina maquina = negocio.recuperarMaquina("M-1");

		double cantidadInicial = maquina.getCantidadActual();

		negocio.consumirCerveza(100, 1, 100);

		assertEquals(5.0, cliente.getTotalConsumido(), 0.01);

		assertEquals(cantidadInicial - 100, maquina.getCantidadActual(), 0.01);
	}

	@Test
	public void probarConsultarValorVendido() {
		NegocioMejorado negocio = new NegocioMejorado();

		negocio.registrarCliente("Alejandro", "1723456789");
		negocio.registrarCliente("Carlos", "1723456788");

		Cliente cliente1 = negocio.buscarClientePorCodigo(100);
		Cliente cliente2 = negocio.buscarClientePorCodigo(101);

		cliente1.setTotalConsumido(5.0);
		cliente2.setTotalConsumido(10.0);

		double total = negocio.consultarValorVendido();

		assertEquals(15.0, total, 0.01);
	}
}
