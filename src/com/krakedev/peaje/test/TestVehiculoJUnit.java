package com.krakedev.peaje.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.TagElectronico;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.servicios.EstacionPeaje;

public class TestVehiculoJUnit {

	@Test
	public void probarComposicion() {

		Conductor conductor = new Conductor(
				"1234567890",
				"Oscar",
				"Jami"
		);

		Vehiculo vehiculo = new Vehiculo("PFI - 1234");

		TagElectronico tag = new TagElectronico("TAG001");

		vehiculo.setPropietario(conductor);
		vehiculo.setTag(tag);

		assertNotNull(vehiculo);
		assertNotNull(vehiculo.getPropietario());
		assertNotNull(vehiculo.getTag());

		assertEquals("PFI - 1234", vehiculo.getPlaca());
		assertEquals("L", vehiculo.getTipo());
		assertEquals("1234567890", vehiculo.getPropietario().getCedula());
		assertEquals("TAG001", vehiculo.getTag().getIdTag());
		assertEquals(0.0, vehiculo.getTag().getSaldo());
		assertTrue(vehiculo.getTag().isActivo());
	}


	@Test
	public void probarRegistrarVehiculo() {

		EstacionPeaje estacion = new EstacionPeaje();

		Conductor conductor = new Conductor(
				"1234567890",
				"Oscar",
				"Jami"
		);

		Vehiculo vehiculo = estacion.registrarVehiculo(
				"PBM - 1873",
				"P",
				conductor
		);

		assertNotNull(vehiculo);
		assertEquals("PBM - 1873", vehiculo.getPlaca());
		assertEquals("P", vehiculo.getTipo());
		assertNotNull(vehiculo.getPropietario());
		assertNotNull(vehiculo.getTag());
		assertEquals("TAG-PBM - 1873", vehiculo.getTag().getIdTag());
	}


	@Test
	public void probarRecargarTag() {

		EstacionPeaje estacion = new EstacionPeaje();

		TagElectronico tag = new TagElectronico("TAG001");

		estacion.recargarTag(tag, 10.00);

		assertEquals(10.00, tag.getSaldo());
	}


	@Test
	public void probarCobrarPeaje() {

		EstacionPeaje estacion = new EstacionPeaje();

		Conductor conductor = new Conductor(
				"1234567890",
				"Oscar",
				"Jami"
		);

		Vehiculo vehiculo = estacion.registrarVehiculo(
				"PBM - 1873",
				"P",
				conductor
		);

		estacion.recargarTag(
				vehiculo.getTag(),
				10.00
		);

		estacion.cobrarPeaje(vehiculo);

		assertEquals(7.50, vehiculo.getTag().getSaldo());
	}


	@Test
	public void probarTransferenciaSaldo() {

		EstacionPeaje estacion = new EstacionPeaje();

		TagElectronico origen = new TagElectronico("TAG001");
		TagElectronico destino = new TagElectronico("TAG002");

		origen.setSaldo(10.00);
		destino.setSaldo(5.00);

		estacion.transferirSaldoTag(
				origen,
				destino,
				2.00
		);

		assertEquals(8.00, origen.getSaldo());
		assertEquals(7.00, destino.getSaldo());
	}

}