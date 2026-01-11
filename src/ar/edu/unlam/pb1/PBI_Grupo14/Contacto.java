package ar.edu.unlam.pb1.PBI_Grupo14;

public class Contacto {
	private static final int PRIMER_INDICE_ASCII_NUMERICO = 48;
	private static final int ULTIMO_INDICE_ASCII_NUMERICO = 57;
	private static final int[] COD_ASCII_CARACTERES_ESPECIALES = { 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45,
			46, 47, 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96 };
	private final int MAXIMO_DE_LLAMADAS_POR_ANIO = 100;
	private String nombre;
	private String apellido;
	private String celular;
	private String email;
	private int codigoPostal;
	private String localidad;
	private Provincia provincia;
	private boolean esCliente;
	private boolean volverALlamar;
	private Llamada[] registrosLlamadas;
	private Operador atendidoPor;
	/*
	 * Se deben incorporar los atributos necesarios.
	 * 
	 * � Nombre y Apellido: No se aceptan n�meros. � Celular: Compuesto del c�digo
	 * de pa�s + c�digo de �rea + n�mero de celular. � E-Mail: Debe contener al
	 * menos el s�mbolo �@� y el caracter �.�. � Direcci�n: Valor alfanum�rico. �
	 * C�digo Postal: Valor num�rico. � Localidad: Valor alfanum�rico. � Provincia.
	 * Enumerador que contenga las 23 provincias argentinas. � Es cliente (Si o No):
	 * Inicialmente se carga en �No�. � Desea ser llamado nuevamente (Si o No):
	 * Inicialmente se carga en �Si�.
	 */

	public Contacto(String nombre, String apellido, String celular, String email, int cp, String localidad,
			Provincia provincia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.email = email;
		this.codigoPostal = cp;
		this.localidad = localidad;
		this.provincia = provincia;
		this.esCliente = false;
		this.volverALlamar = true;
		this.registrosLlamadas = new Llamada[MAXIMO_DE_LLAMADAS_POR_ANIO];
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCelular() {
		return celular;
	}

	public String getEmail() {
		return email;
	}

	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	public boolean getVolverALlamar() {
		return volverALlamar;
	}

	public boolean esCliente() {
		return this.esCliente;
	}

	public void noVolverALlamar() {
		this.volverALlamar = false;
	}

	public String getAtendidoPor() {
		String nombreOperador = this.atendidoPor.getNombre();
		String apellidoOperador = this.atendidoPor.getApellido();
		int idOperador = this.atendidoPor.getIdNumeroOperador();
		return "[Id operador: "+idOperador+ "]: "+nombreOperador+" "+apellidoOperador;
	}
	
	public String getRegistrosLlamadas() {
		String registros = "";
		for(int i=0;i<registrosLlamadas.length;i++) {
			if(registrosLlamadas[i]!=null) {
				registros += registrosLlamadas[i].toString()+"\n";
			}
		}
		return registros;
	}

	public boolean registrarNuevaLlamada(Llamada nueva, Operador operador) {

		for (int i = 0; i < registrosLlamadas.length; i++) {
			if (registrosLlamadas[i] == null) {
				registrosLlamadas[i] = nueva;
				this.atendidoPor = operador;
				if (nueva.isFueExitosa()) {
					this.esCliente = true;
					this.volverALlamar = false;
				}
				return true;
			}
		}	
		return false;
	}

	// MÉTODOS ESTÁTICOS
	public static boolean esEmailValido(String eMail) {
		if (eMail == null || eMail.isEmpty())
			return false;

		String[] emailDescompuesto = eMail.split("@");

		if (emailDescompuesto.length != 2)
			return false;

		String dominio = emailDescompuesto[1];

		for (int j = 0; j < dominio.length(); j++) {
			if (dominio.charAt(j) == '.') {
				return true;
			}
		}

		return false;
	}

	public static boolean esNumeroDeCelularValido(String celular) {
		String codigoArea = celular.split("-")[1];
		String numeroCelular = celular.split("-")[2];
		if (codigoArea.length() > 4 || numeroCelular.length() > 8) {
			return false;
		}
		if (!caracteresNumericoValido(codigoArea)) {
			return false;
		}
		if (!caracteresNumericoValido(numeroCelular)) {
			return false;
		}

		return true;
	}

	private static boolean caracteresNumericoValido(String numero) {
		int cantidadCarateresValidos = 0;
		for (int i = 0; i < numero.length(); i++) {
			for (int j = PRIMER_INDICE_ASCII_NUMERICO; j <= ULTIMO_INDICE_ASCII_NUMERICO; j++) {
				if (numero.charAt(i) == (char) j) {
					cantidadCarateresValidos++;
				}
			}
		}
		return cantidadCarateresValidos == numero.length();
	}

	public static boolean validarNombres(String nombre) {
		for (int i = 0; i < nombre.length(); i++) {
			for (int j = 0; j < COD_ASCII_CARACTERES_ESPECIALES.length; j++) {

				if (nombre.charAt(i) == (COD_ASCII_CARACTERES_ESPECIALES[j])) {
					return false;
				}
			}
		}
		return true;
	}

	// ESTADO DEL OBJETO
	public String toString() {
		/*
		 * Muestra los datos de un contacto, entre los que se debe incluir el registro
		 * de las llamadas realizadas.
		 */
		String datosBasicos = "*** Información de contacto *** \n-Nombre: " + nombre + "\n-Apellido: " + apellido + "\n-Celular: " + celular
				+ "\n-Correo: " + email + "\n-Código Postal: " + codigoPostal + "\n-Localidad: " + localidad
				+ "\n-Provincia: " + provincia + "\n-Es cliente: ";
		datosBasicos += esCliente ? "Si" : "No";
		datosBasicos += "\n-Volver a llamar: " + (volverALlamar ? "Si" : "NO");
		return datosBasicos;
	}

}
