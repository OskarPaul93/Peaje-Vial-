package com.krakedev.peaje.test;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.TagElectronico;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.servicios.EstacionPeaje;
import com.krakedev.peaje.util.ImpresorUtil;

public class TestVehiculo {

	public static void main(String[] args) {

		Conductor conductor = new Conductor(
				"1234567890",
				"Oscar",
				"Jami"
		);

		Vehiculo vehiculo = new Vehiculo("PFI - 1234");

		TagElectronico tag = new TagElectronico("TAG001");

		vehiculo.setPropietario(conductor);
		vehiculo.setTag(tag);

		vehiculo.imprimir();

		ImpresorUtil impresor = new ImpresorUtil();
		impresor.imprimirVehiculo(vehiculo);


		EstacionPeaje estacion = new EstacionPeaje();

		Vehiculo vehiculo2 = estacion.registrarVehiculo(
				"PBM - 1873",
				"P",
				conductor
		);

		estacion.recargarTag(
				vehiculo2.getTag(),
				10.00
		);

		estacion.cobrarPeaje(vehiculo2);


		Conductor conductor2 = new Conductor(
				"0987654321",
				"Bryan",
				"Lopez"
		);

		Vehiculo vehiculo3 = estacion.registrarVehiculo(
				"IPM -9999",
				"L",
				conductor2
		);

		estacion.recargarTag(
				vehiculo3.getTag(),
				5.00
		);

		estacion.transferirSaldoTag(
				vehiculo2.getTag(),
				vehiculo3.getTag(),
				2.00
		);

		impresor.imprimirVehiculo(vehiculo2);
		impresor.imprimirVehiculo(vehiculo3);
	}

}