package com.krakedev.peaje.test;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.servicios.EstacionPeaje;
import com.krakedev.peaje.util.ImpresorUtil;

public class TestImpresor {

	public static void main(String[] args) {

		EstacionPeaje estacion = new EstacionPeaje();

		// PRIMER CONDUCTOR
		Conductor conductor = new Conductor(
				"1234567890",
				"Juan",
				"Perez"
		);

		// PRIMER VEHICULO
		Vehiculo vehiculo = estacion.registrarVehiculo(
				"ABC1234",
				"L",
				conductor
		);

		// RECARGAR Y COBRAR PEAJE
		estacion.recargarTag(vehiculo.getTag(), 10.00);
		estacion.cobrarPeaje(vehiculo);


		// SEGUNDO CONDUCTOR
		Conductor conductor2 = new Conductor(
				"0987654321",
				"Pedro",
				"Lopez"
		);

		// SEGUNDO VEHICULO
		Vehiculo vehiculo2 = estacion.registrarVehiculo(
				"XYZ5678",
				"P",
				conductor2
		);

		// RECARGAR SEGUNDO TAG
		estacion.recargarTag(vehiculo2.getTag(), 5.00);

		// TRANSFERIR $3 DEL PRIMER TAG AL SEGUNDO
		estacion.transferirSaldoTag(
				vehiculo.getTag(),
				vehiculo2.getTag(),
				3.00
		);


		// IMPRIMIR VEHICULOS
		ImpresorUtil impresor = new ImpresorUtil();

		impresor.imprimirVehiculo(vehiculo);
		impresor.imprimirVehiculo(vehiculo2);
	}
}