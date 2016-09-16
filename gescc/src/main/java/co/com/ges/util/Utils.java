package co.com.ges.util;

import java.util.Date;

public class Utils {
	
	public Utils() {
		super();
	}

	public Boolean validarFechas(Date fechaIni, Date fechaFin, Date fechaSuscripcion) {
		boolean fecVal = false;
		if (fechaIni.before(fechaSuscripcion)) {
			if ((fechaFin.after(fechaIni)) && (fechaFin.before(fechaSuscripcion))) {
				fecVal = true;
			} else if ((fechaFin.compareTo(fechaIni) == 0) && (fechaFin.before(fechaSuscripcion))) {
				fecVal = true;
			} else if ((fechaFin.compareTo(fechaIni) == 0) && (fechaFin.compareTo(fechaSuscripcion) == 0)) {
				fecVal = true;
			} else if ((fechaFin.compareTo(fechaIni) == 0) && (fechaFin.after(fechaSuscripcion))) {
				fecVal = true;
			} else {
				fecVal = false;
			}
		} else if (fechaIni.compareTo(fechaSuscripcion) == 0) {
			if ((fechaFin.after(fechaIni)) && (fechaFin.after(fechaSuscripcion))) {
				fecVal = true;
			} else if ((fechaFin.compareTo(fechaIni) == 0) && (fechaFin.before(fechaSuscripcion))) {
				fecVal = true;
			} else if ((fechaFin.compareTo(fechaIni) == 0) && (fechaFin.compareTo(fechaSuscripcion) == 0)) {
				fecVal = true;
			} else if ((fechaFin.compareTo(fechaIni) == 0) && (fechaFin.after(fechaSuscripcion))) {
				fecVal = true;
			} else {
				fecVal = false;
			}
		} else if (fechaIni.after(fechaSuscripcion)) {
			if ((fechaFin.after(fechaIni)) && (fechaFin.before(fechaSuscripcion))) {
				fecVal = true;
			} else if ((fechaFin.compareTo(fechaIni) == 0) && (fechaFin.before(fechaSuscripcion))) {
				fecVal = true;
			} else if ((fechaFin.compareTo(fechaIni) == 0) && (fechaFin.compareTo(fechaSuscripcion) == 0)) {
				fecVal = true;
			} else if ((fechaFin.compareTo(fechaIni) == 0) && (fechaFin.after(fechaSuscripcion))) {
				fecVal = true;
			} else {
				fecVal = false;
			}
		} else {
			fecVal = false;
		}
		return Boolean.valueOf(fecVal);
	}
}
