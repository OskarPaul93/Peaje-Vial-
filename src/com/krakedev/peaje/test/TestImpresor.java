package com.krakedev.peaje.test;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.servicios.EstacionPeaje;
import com.krakedev.peaje.util.ImpresorUtil;

public class TestImpresor {

	public static void main(String[] args) {

		EstacionPeaje estacion = new EstacionPeaje();

		Conductor conductor = new Conductor(
				"1234567890",
				"Juan",
				"Perez"
		);

		Vehiculo vehiculo = estacion.registrarVehiculo(
				"ABC1234",
				"L",
				conductor
		);

		estacion.recargarTag(vehiculo.getTag(), 10.00);

		ImpresorUtil impresor = new ImpresorUtil();
		impresor.imprimirVehiculo(vehiculo);
	}
}