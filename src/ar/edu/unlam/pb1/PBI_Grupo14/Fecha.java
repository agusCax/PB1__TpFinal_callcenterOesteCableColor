package ar.edu.unlam.pb1.PBI_Grupo14;

public class Fecha {
	static final int FEBRERO = 2, ABRIL = 4, JUNIO = 6, SEPTIEMBRE = 9, NOVIEMBRE = 11;
	private final int ANIO_ACTUAL = 2025;
	private String dia;
	private String mes;
	private String anio;

	public Fecha() {
	}
//hardCodear año 2025
	public Fecha(String dia, String mes, String anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}

	public boolean validarFecha() {
		
		if (!caracteresNumericoValido(dia) || !caracteresNumericoValido(mes) || !caracteresNumericoValido(anio)) {
			return false;
		}
		
		int numeroDia = Integer.parseInt(dia);
		int numeroMes = Integer.parseInt(mes);
		int numeroAnio = Integer.parseInt(anio);
		
		if (numeroMes > 12 || numeroMes < 1) {
			return false;
		}
		
		if (numeroAnio < ANIO_ACTUAL || numeroAnio > ANIO_ACTUAL) {
			return false;
		}
		
		return validarNumeroDia(numeroDia, numeroMes);
	}

	private static boolean validarNumeroDia(int numeroDia, int numeroMes) {
		boolean esValido;
		switch (numeroMes) {
		case ABRIL:
		case JUNIO:
		case SEPTIEMBRE:
		case NOVIEMBRE:
			esValido = numeroDia >=1 && numeroDia <= 30;
			break;
		case FEBRERO:
			esValido =  numeroDia >=1 && numeroDia <= 29;
			break;
		default:
			esValido =  numeroDia >=1 && numeroDia <= 31;
			break;
		}
		return esValido;
	}

	private static boolean caracteresNumericoValido(String numero) {
		for (int i = 0; i < numero.length(); i++) {
			if (!Character.isDigit(numero.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return dia + "/" + mes + "/" + anio;
	}

}
