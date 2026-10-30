package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

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

	    boolean resultado = negocio.agregarMaquina(
	        "Taladro", "Taladro electrico", 10.50, "M-10"
	    );

	    assertFalse(resultado);
	}
}
