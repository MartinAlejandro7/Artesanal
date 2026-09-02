package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestServir {

	public static void main(String[] args) {
		
		Maquina rubia = new Maquina ("Pilsener","Cerveza Caliente",0.02, 8000);
		
		System.out.println("********Estado INICIAL*********");
		
		rubia.imprimir();
		
		System.out.println("********Llenando Maquina*********");
		rubia.llenarMaquina();
		rubia.imprimir();
		
		System.out.println("********Servir 1000 ML*********");
		double valor ;
		valor = rubia.servirCerveza(10000);
		rubia.imprimir();
		System.out.println("VALOR A PAGAR: " + valor);
	}
	

}
