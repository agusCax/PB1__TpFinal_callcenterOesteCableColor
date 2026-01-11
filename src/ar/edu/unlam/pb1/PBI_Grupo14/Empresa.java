package ar.edu.unlam.pb1.PBI_Grupo14;

public class Empresa {
	private final int CANTIDAD_ZONAS_CUBIERTAS = 10;
	private final int OBJETIVOS_CONTACTOS_DIARIOS = 10;
	private final int OPERADORES_MAXIMOS_POR_EMPRESA = 100;
	private int zonasCobertura[];
	private Contacto[] contactos;
	private String nombreEmpresa;
	private Operador[] operadores;

	public Empresa() {
	}

	public Empresa(String nombreEmpresa) {
		this.zonasCobertura = new int[CANTIDAD_ZONAS_CUBIERTAS];
		this.contactos = new Contacto[OBJETIVOS_CONTACTOS_DIARIOS];
		this.nombreEmpresa = nombreEmpresa;
		this.operadores = new Operador[OPERADORES_MAXIMOS_POR_EMPRESA];
	}

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	// ZONA DE COBERTURA
	public boolean agregarNuevaZonaDeCobertura(int codigoPostal) {
		if (!elCodigoPostalEstaDentroDeLaZonaDeCobertura(codigoPostal)) {
			for (int i = 0; i < zonasCobertura.length; i++) {
				if (zonasCobertura[i] == 0) {
					zonasCobertura[i] = codigoPostal;
					return true;
				}
			}
		}

		return false;
	}


	private boolean elCodigoPostalEstaDentroDeLaZonaDeCobertura(int codigoPostal) {
		for (int i = 0; i < zonasCobertura.length; i++) {
			if (zonasCobertura[i] == codigoPostal) {
				return true;
			}
		}
		return false;
	}

	// CONTACTOS
	private boolean validarDatosContacto(Contacto nuevo) {
		if (!Contacto.esEmailValido(nuevo.getEmail())) {
			return false;
		}
		if (!Contacto.esNumeroDeCelularValido(nuevo.getCelular())) {
			return false;
		}
		if (!Contacto.validarNombres(nuevo.getNombre()) || !Contacto.validarNombres(nuevo.getApellido())) {
			return false;
		}

		return true;
	}

	public boolean agregarNuevoContacto(Contacto nuevo) {
		if (validarDatosContacto(nuevo)) {
			for (int i = 0; i < contactos.length; i++) {
				if (contactos[i] == null) {
					contactos[i] = nuevo;
					return true;
				}
			}
		}
		return false;
	}

	public Contacto buscarCandidato() {
		Contacto[] candidatos = filtrarContactosPorCandidatos();
		int cantidadDeCandidatos = candidatos.length;
		Contacto candidato = null;
		do {
			int numeroDeCandidatoSeleccionado = seleccionarAleatoriamenteCandidato(candidatos);
			candidato = candidatos[numeroDeCandidatoSeleccionado];
			--cantidadDeCandidatos;
		} while (candidato == null && cantidadDeCandidatos>0);
		return candidato;
	}

	private int seleccionarAleatoriamenteCandidato(Contacto[] candidatos) {
		int cantidadCandidatosExistentes = 0;
		for (int i = 0; i < candidatos.length; i++) {
			if (candidatos[i] != null) {
				cantidadCandidatosExistentes++;
			}
		}
		int numeroDeCandidatoSeleccionado = (int) (Math.random() * cantidadCandidatosExistentes);
		return numeroDeCandidatoSeleccionado;
	}

	private Contacto[] filtrarContactosPorCandidatos() {
		Contacto[] candidatos = new Contacto[contactos.length];
		for (int i = 0; i < contactos.length; i++) {

			if (contactos[i] != null && contactos[i].getVolverALlamar() && !contactos[i].esCliente()
					&& elCodigoPostalEstaDentroDeLaZonaDeCobertura(contactos[i].getCodigoPostal())) {

				for (int j = 0; j < candidatos.length; j++) {
					if (candidatos[j] == null) {
						candidatos[j] = contactos[i];
						break;
					}
				}

			}
		}
		return candidatos;
	}

	public Contacto buscarContacto(String condicion) {
		for (int i = 0; i < contactos.length; i++) {
			if (contactos[i] != null && deternimarCoincidencia(contactos[i], condicion)) {
				return contactos[i];
			}
		}
		return null;
	}

	private boolean deternimarCoincidencia(Contacto contacto, String condicionBuscada) {
		boolean respuesta = false;
		if (contacto.getApellido().equalsIgnoreCase(condicionBuscada)) {
			respuesta = true;
		}
		if (contacto.getCelular().equals(condicionBuscada)) {
			respuesta = true;
		}
		if (contacto.getEmail().equalsIgnoreCase(condicionBuscada)) {
			respuesta = true;
		}
		return respuesta;
	}

	public Contacto buscarPorCodigoPostal(int cp) {
	
		for (int i = 0; i < contactos.length; i++) {
			if (contactos[i] != null && contactos[i].getCodigoPostal() == cp) {
				return contactos[i];
			}
		}
		return null;
	}

	// OPERADORES
	public Operador[] getOperadores() {
		return this.operadores;
	}

	public int registrarNuevoOperador(String nombre, String apellido, String dni, String contrasenia) {

		if (validarDatosNuevoOperador(nombre, apellido, dni, contrasenia)) {
			Operador newOperador = new Operador(nombre, apellido, dni, contrasenia);

			for (int i = 0; i < operadores.length; i++) {
				if (operadores[i] == null) {
					operadores[i] = newOperador;

					return newOperador.getIdNumeroOperador();
				}
			}
		}

		return -1;

	}
	private boolean validarDatosNuevoOperador(String nombre, String apellido, String dni, String contrasenia) {
		return Operador.validarContrasenia(contrasenia) && Operador.validarDni(dni) 
				&& Operador.validarNombreOApellido(nombre) 
				&& Operador.validarNombreOApellido(apellido);
	}

	public Operador loguearOperador(int idOperador, String contrasenia) {

		for (int i = 0; i < operadores.length; i++) {
			if (operadores[i] != null && operadores[i].getIdNumeroOperador() == idOperador
					&& operadores[i].getContrasenia().equals(contrasenia)) {
				operadores[i].setEstaLogueado();
				return operadores[i];
			}
		}

		return null;
	}

	public boolean desloguearOperador(int idOperador) {
		for (int i = 0; i < operadores.length; i++) {
			if (operadores[i] != null && operadores[i].getIdNumeroOperador() == idOperador) {
				operadores[i].setEstaLogueado();
				return true;
			}
		}

		return false;
	}
}
