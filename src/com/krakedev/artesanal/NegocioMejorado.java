package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {

	private ArrayList<Maquina> maquinas = new ArrayList<Maquina>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	private int ultimoCodigo = 100;

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
		Cliente cliente = new Cliente(nombre, cedula, "C-" + ultimoCodigo);

	    ultimoCodigo++;

	    clientes.add(cliente);
	}

	public Cliente buscarClientePorCedula(String cedula) {

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCedula().equals(cedula))
				return clientes.get(i);
		}

		return null;
	}

	public Cliente buscarClientePorCodigo(int codigo) {

		for (int i = 0; i < clientes.size(); i++) {

			if (clientes.get(i).getCodigo() == codigo) {
				return clientes.get(i);
			}

		}

		return null;
	}

	public void consumirCerveza(int codigoCliente, int codigoMaquina, double cantidad) {

	    Maquina maquina = recuperarMaquina("M-" + codigoMaquina);

	    Cliente cliente = buscarClientePorCodigo(codigoCliente);

	    double resultado = maquina.servirCerveza(cantidad);

	    registrarConsumo(cliente, resultado);

	}
	
	public void registrarConsumo(Cliente cliente, double valor) {

	    double total = cliente.getTotalConsumido();

	    total = total + valor;

	    cliente.setTotalConsumido(total);

	}
	
	public double consultarValorVendido() {
		double totalVendido = 0;
		
		for(int i = 0; i < clientes.size(); i++) {
			totalVendido = totalVendido + clientes.get(i).getTotalConsumido();
		}
		return totalVendido;
	}
}
