package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {
	
	private ArrayList<Maquina> maquinas = new ArrayList<Maquina>();

	public ArrayList<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<Maquina> maquinas) {
		this.maquinas = maquinas;
	}
	
	public String generarCodigo() {
		int numero = (int)(Math.random()*100);
		return "M-" + numero;
	}
	
	public void agregarMaquina(String nombreCerveza, String descripcion, double precioMl) {
		String codigo = generarCodigo();
		Maquina maquina = new Maquina(nombreCerveza, descripcion, precioMl, codigo);
		maquinas.add(maquina);
		
	}
	
	public void cargarMaquina() {
		
		for(int i = 0; i<maquinas.size();i++) {
			maquinas.get(i).llenarMaquina();
		}
		
	}
	
	public Maquina recuperarMaquina(String codigo) {
		
		for(int i = 0; i<maquinas.size(); i++) {
			if(maquinas.get(i).getCodigo().equals(codigo)) {
				return maquinas.get(i);
			}
		}
		return null;
	}
	
}
