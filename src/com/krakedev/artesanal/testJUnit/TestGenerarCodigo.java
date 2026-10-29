package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.NegocioMejorado;

public class TestGenerarCodigo {
	
	 @Test
	    public void probarGenerarCodigo() {
	        NegocioMejorado negocio = new NegocioMejorado();

	        String codigo = negocio.generarCodigo();

	        assertTrue(codigo.contains("M-"));
	    }

}	
