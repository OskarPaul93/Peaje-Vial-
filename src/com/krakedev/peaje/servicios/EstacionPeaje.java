package com.krakedev.peaje.servicios;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.TagElectronico;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.util.ValidadorUtil;

public class EstacionPeaje {
	
	private int codigoEstacion = 500;
	private double tarifaLiviano = 1.00;
	private double tarifaPesado = 2.50;
	
	
	
	public EstacionPeaje() {
		
	}
	
	
	public int getCodigoEstacion() {
		return codigoEstacion;
	}
	public void setCodigoEstacion(int codigoEstacion) {
		this.codigoEstacion = codigoEstacion;
	}
	public double getTarifaLiviano() {
		return tarifaLiviano;
	}
	public void setTarifaLiviano(double tarifaLiviano) {
		this.tarifaLiviano = tarifaLiviano;
	}
	public double getTarifaPesado() {
		return tarifaPesado;
	}
	public void setTarifaPesado(double tarifaPesado) {
		this.tarifaPesado = tarifaPesado;
	}
	
	
	public Vehiculo registrarVehiculo(String placa, String tipo, Conductor conductor) {

		ValidadorUtil validador = new ValidadorUtil();

		if (validador.esTipoValido(tipo)) {

			Vehiculo vehiculo = new Vehiculo(placa);
			vehiculo.setTipo(tipo);
			vehiculo.setPropietario(conductor);

			TagElectronico tag = new TagElectronico("TAG-" + placa);
			vehiculo.setTag(tag);

			return vehiculo;

		} else {
			System.out.println("Tipo de vehiculo no valido");
			return null;
		}
	}
	
	
	public void recargarTag(TagElectronico tag, double monto) {

		ValidadorUtil validador = new ValidadorUtil();

		if (validador.esMontoValido(monto)) {

			tag.setSaldo(tag.getSaldo() + monto);

		} else {
			System.out.println("Monto no valido");
		}
	}
	
	public void cobrarPeaje(Vehiculo vehiculo) {

		double tarifa;

		if (vehiculo.getTipo().equals("L")) {
			tarifa = tarifaLiviano;
		} else {
			tarifa = tarifaPesado;
		}

		double saldo = vehiculo.getTag().getSaldo();

		if (saldo >= tarifa) {

			vehiculo.getTag().setSaldo(saldo - tarifa);

			System.out.println("Peaje cobrado correctamente");

		} else {

			System.out.println("Saldo insuficiente");

		}
	}
	
	
	public void transferirSaldoTag(TagElectronico origen, TagElectronico destino, double monto) {

		ValidadorUtil validador = new ValidadorUtil();

		if (validador.esMontoValido(monto)) {

			double saldoOrigen = origen.getSaldo();

			if (saldoOrigen >= monto) {

				origen.setSaldo(saldoOrigen - monto);
				destino.setSaldo(destino.getSaldo() + monto);

				System.out.println("Transferencia realizada correctamente");

			} else {
				System.out.println("Saldo insuficiente");
			}

		} else {
			System.out.println("Monto no valido");
		}
	}
	
	
	
	
	

}
