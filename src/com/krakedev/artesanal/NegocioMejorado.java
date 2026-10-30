package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {

	private ArrayList<Maquina> maquinas = new ArrayList<Maquina>();
	private ArrayList<Cliente> clientes;
	
	private int ultimoCodigo = 0;

	public ArrayList<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public String generarCodigo() {
		int numero = (int) (Math.random() * 100);
		return "M-" + numero;
	}

	public boolean agregarMaquina(String nombreCerveza, String descripcion, double precioMl, String codigo) {

		if (recuperarMaquina(codigo) != null) {
			return false;
		}
		Maquina maquina = new Maquina(nombreCerveza, descripcion, precioMl, codigo);

		maquinas.add(maquina);

		return true;

	}

	public void cargarMaquina() {

		for (int i = 0; i < maquinas.size(); i++) {
			maquinas.get(i).llenarMaquina();
		}

	}

	public Maquina recuperarMaquina(String codigo) {

		for (int i = 0; i < maquinas.size(); i++) {
			if (maquinas.get(i).getCodigo().equals(codigo)) {
				return maquinas.get(i);
			}
		}
		return null;
	}

	public void registrarCliente(String nombre, String cedula) {
		ultimoCodigo ++;
		String codigo = "C-" + ultimoCodigo;

	    Cliente cliente = new Cliente(nombre, cedula, codigo);

	    clientes.add(cliente);
	}

}
