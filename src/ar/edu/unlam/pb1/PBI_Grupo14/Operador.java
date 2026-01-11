package ar.edu.unlam.pb1.PBI_Grupo14;

public class Operador {
	private int idNumeroOperador;
	private String nombre;
	private String apellido;
	private String dni;
	private String contrasenia;
	private boolean logueado;
	private int cantidadDeLlamadasRealizadas;
	private static int cantidadOperadoresRegistrados = 0;
	private Contacto[] clientes;
	final int OBJETIVOS_CONTACTOS_ANUALES = 1000;

	public Operador(String nombre, String apellido, String dni, String contrasenia) {
		cantidadOperadoresRegistrados++;
		this.idNumeroOperador = cantidadOperadoresRegistrados;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrasenia = contrasenia;
		this.logueado = false;
		this.cantidadDeLlamadasRealizadas = 0;
		this.clientes = new Contacto[OBJETIVOS_CONTACTOS_ANUALES];
	}

	public int getIdNumeroOperador() {
		return this.idNumeroOperador;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setEstaLogueado() {
		this.logueado = !this.logueado;
	}

	public boolean getEstaLogueado() {
		return this.logueado;
	}

	public void incrementarCantidadDeLlamadasRealizadas() {
		this.cantidadDeLlamadasRealizadas++;
	}

	public int getCantidadDeLlamadasRealizadas() {
		return this.cantidadDeLlamadasRealizadas;
	}

	public int getCantidadOperadoresRegistrados() {
		return cantidadOperadoresRegistrados;
	}

	public boolean agregarCliente(Contacto contacto) {
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] == null && contacto.esCliente()) {
				clientes[i] = contacto;
				return true;
			}
		}
		return false;
	}

	public int getCantidadClientesEfectivizados() {
		int clientesEfectivos = 0;
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null) {
				clientesEfectivos++;
			}
		}
		return clientesEfectivos;
	}
//VALIDACIONES DE DATOS

	public static boolean validarNombreOApellido(String nombre) {
		if (nombre.length() < 3) {
			return false;
		}
		for (int i = 0; i < nombre.length(); i++) {
			char caracter = nombre.toLowerCase().charAt(i);
			if (!Character.isLowerCase(caracter)) {
				return false;
			}
		}
		return true;
	}

	public static boolean validarDni(String dni) {
		if (dni.length() < 8) {
			return false;
		}
		for (int i = 0; i < dni.length(); i++) {
			char caracter = dni.toLowerCase().charAt(i);
			if (!Character.isDigit(caracter)) {
				return false;
			}
		}
		return true;
	}

	public static boolean validarContrasenia(String contrasenia) {
		if (contrasenia.length() < 8) {
			return false;
		}

		int mayusculas = 0;
		int numeros = 0;
		int especiales = 0;

		for (int i = 0; i < contrasenia.length(); i++) {
			char caracter = contrasenia.charAt(i);

			if (Character.isLowerCase(caracter)) {
				continue;
			} else if (Character.isUpperCase(caracter)) {
				mayusculas++;
			} else if (Character.isDigit(caracter)) {
				numeros++;
			} else {
				especiales++;
			}
		}

		return (mayusculas >= 1 && numeros >= 2 && especiales >= 1);

	}

	public String toString() {
		return "[Operador ID: " + this.idNumeroOperador + "]" + "\nCantidad de llamadas realizadas en día: "
				+ this.cantidadDeLlamadasRealizadas + "\nCantidad de clientes efectivos: "
				+ getCantidadClientesEfectivizados();
	}
}
