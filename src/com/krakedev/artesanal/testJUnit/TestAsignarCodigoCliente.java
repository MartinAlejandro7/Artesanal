package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Negocio;

public class TestAsignarCodigoCliente {
	@Test
	public void asignarCodigo() {
		
		Negocio barDeMoe = new Negocio();
		
		Cliente simsong = new Cliente("Simsong", "17587814");
			
		Cliente bart = new Cliente("Bart", "175944"); 
		
		barDeMoe.asignarCodigoCliente(simsong);
		barDeMoe.asignarCodigoCliente(bart);
		
		assertEquals(100, simsong.getCodigo());
		assertEquals(101, bart.getCodigo());
	}
}
