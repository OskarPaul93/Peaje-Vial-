package com.krakedev.peaje.util;

public class ValidadorUtil {
	public boolean esMontoValido(double monto) {
		if (monto>0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean esTipoValido(String tipo) {
		if ("L".equals(tipo) || "P".equals(tipo)) {
			return true;
		}else {
			return false;
		}
		
	}
	

}
