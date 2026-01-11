package ar.edu.unlam.pb1.PBI_Grupo14;

import java.util.Scanner;

public class InterfazCallCenter {
	static MenuOpciones opciones[] = MenuOpciones.values();
	static MenuBuscador opcionesBuscador[] = MenuBuscador.values();
	static MenuOperador opcionesSesionOperador[] = MenuOperador.values();
	static Empresa oesteCableColor = new Empresa("Oeste Cable Color");
	static String fechaActual = "";
	static Operador operadorActual = null;
	static Scanner teclado = new Scanner(System.in);

	public static void main(String args[]) {
		MenuOpciones opcionMenu = MenuOpciones.CERRAR_SESION;
		MenuOperador opcionSesionOperador = MenuOperador.SALIR;
		System.out.println("Bienvenido al Call center " + oesteCableColor.getNombreEmpresa() + "\n");

		do {
			menuOperador();
			opcionSesionOperador = ingresoOperadorActual(opcionSesionOperador);

			if (operadorActual != null) {
				ingresarFecha();
				do {
					menu();
					opcionMenu = seleccionarOpcion(opcionMenu);
				} while (opcionMenu != MenuOpciones.CERRAR_SESION);

			}

		} while (opcionSesionOperador != MenuOperador.SALIR);
		teclado.close();
	}

	private static void ingresarFecha() { // dd/mm/aaaa
		Fecha hoy = null;
		do {
			System.out.println("Por favor ingrese la fecha de hoy (dd/mm/aaaa):");
			fechaActual = teclado.next();
			
			if (fechaActual.length() != 10) {
				break;
			} else {
				String dias = fechaActual.substring(0, 2); // 0-1
				String mes = fechaActual.substring(3, 5); // 3-4
				String anio = fechaActual.substring(6, 10); // 6-9
				hoy = new Fecha(dias, mes, anio);
				
			}
		} while ( hoy == null || !hoy.validarFecha());
	}

	// MENU OPERADORES
	private static void menuOperador() {
		System.out.println("******Menú sesión operadores*******");
		for(int i =0; i<opcionesSesionOperador.length;i++) {
			System.out.println((i+1)+"- "+opcionesSesionOperador[i]);
		}
		System.out.println("****************************");
	}

	private static MenuOperador ingresoOperadorActual(MenuOperador opcionSesion) {
		int opcionElegida = teclado.nextInt();
		for (int i = 0; i < opcionesSesionOperador.length; i++) {
			if ((i+1) == opcionElegida) {
				opcionSesion = opcionesSesionOperador[i];
			}
		}
		
		switch (opcionSesion) {
		case INGRESAR:
			iniciarSesionOperador();
			break;
		case REGISTRARSE:
			registrarNuevoOperador();
			break;
		case SALIR:
			System.out.println("Finalizando...");
			break;
		}
		return opcionSesion;
	}

	private static void iniciarSesionOperador() {
		System.out.println("Por favor ingrese su id de operador:");
		int id = teclado.nextInt();
		System.out.println("Ingrese su contraseña");
		String contrasenia = teclado.next();
		operadorActual = oesteCableColor.loguearOperador(id, contrasenia);
		if (operadorActual != null) {
			System.out.println("Se logueo correctamente");
		} else {
			System.err.println("Error: usuario o contraseña invalido");
		}
	}

	private static void registrarNuevoOperador() {
		System.out.println("Ingrese su nombre:");
		System.out.println("Minimo 3 caracteres y únicamente alfabéticos");
		String nombre = teclado.next();
		System.out.println("Ingrese su apellido:");
		System.out.println("Minimo 3 caracteres y únicamente alfabéticos");
		String apellido = teclado.next();
		System.out.println("Ingrese su DNI:");
		System.out.println("Minimo 8 caracteres y únicamente numericos");
		String dni = teclado.next();
		System.out.println("Ingrese su contraseña:");
		System.out.println("La contraseña debe tener al menos 8 caracteres, incluyendo como mínimo 2 números, 1 caracter especial y 1 letra mayúscula.");
		String pass = teclado.next();
		int resp = oesteCableColor.registrarNuevoOperador(nombre, apellido, dni, pass);
		if (resp >= 0) {
			System.out.println("Se ha registrado correctamente.");
			System.out.println("Su id de operador es: " + resp + "\n");
			System.out.println("Inicie sesión:");
			iniciarSesionOperador();
		} else {
			System.err.println("No se pudo registra al operador");
		}
	}

	// MENU GENERAL
	private static void menu() {
		System.out.println("******" + oesteCableColor.getNombreEmpresa() + "*******");
		System.out.println("Menu de opciones");
		for (int i = 0; i < opciones.length; i++) {
			System.out.println(opciones[i]);
		}
		System.out.println("************************");

	}

	private static MenuOpciones seleccionarOpcion(MenuOpciones opcionMenu) {
		int opcionElegida = teclado.nextInt();
		for (int i = 0; i < opciones.length; i++) {
			if (opciones[i].getValor() == opcionElegida) {
				opcionMenu = opciones[i];
			}
		}

		switch (opcionMenu) {
		case INCORPORAR_ZONA_COBERTURA:
			incorporarZonaDeCobertura();
			break;
		case ALTA_NUEVO_CONTACTO:
			darDeAltaNuevoContacto();
			break;
		case REALIZAR_LLAMADA:
			realizarNuevaLlamada();
			break;
		case VER_INFORMACION_DE_CONTACTO:
			Contacto contactoBuscado = buscardorContactos();
			if (contactoBuscado != null) {
				verInformacionDelContacto(contactoBuscado);
			}else {
				System.out.println("Verificar si existen contactos dentro de la zonas de coberturas.");
			}
			break;
		case VER_INFORMACION_DE_OPERADOR:
			System.out.println(operadorActual);
			break;
		case CERRAR_SESION:
			if (oesteCableColor.desloguearOperador(operadorActual.getIdNumeroOperador())) {
				operadorActual = null;
				System.out.println("Cerrando sesión...");
			} else {
				System.err.println("No se puedo cerrar la sesión.");
			}
			break;
		}
		return opcionMenu;
	}

	private static void incorporarZonaDeCobertura() {
		System.out.println("Por favor ingrese el código postal correspondiente a la zona:");
		int cp = teclado.nextInt();
		boolean resp = oesteCableColor.agregarNuevaZonaDeCobertura(cp);
		if (resp) {
			System.out.println("Zona de cobertura añadida exitosamente");
		} else {
			System.err.println("La zona no puede ser añadaida. Por favor verificar "
					+ "si esta ya existe dentro de la cobertura acutal");
		}
		System.out.println();
	}

	private static void darDeAltaNuevoContacto() {
		System.out.println("Por favor ingrese los datos del contacto");
		System.out.println("Ingrese el nombre:");
		String nombre = teclado.next();
		System.out.println("Ingrese el apellido:");
		String apellido = teclado.next();
		String celular;
		boolean celularValido = false;
		do {
			celular = "+54-";
			System.out.println("Ingrese el código de area de número de celular:");
			celular += teclado.next() + "-";
			System.out.println("Ingrese el número de celular sin el código de area:");
			celular += teclado.next();
			celularValido = Contacto.esNumeroDeCelularValido(celular);
			if (!celularValido) {
				System.err.println("Error formato telefonico. Intentelo nuevamente \n");
				System.out.println(celular);
			}
		} while (!celularValido);
		System.out.println("Ingrese el correo electrónico: ");
		String correo = teclado.next();
		System.out.println("Ingrese el código postal:");
		int cp = teclado.nextInt();
		System.out.println("Ingrese la localidad:");
		teclado.nextLine();
		String localidad = teclado.nextLine();
		System.out.println("Ingrese la provincia:");
		Provincia provinciaElegida = seleccionarProvincia();
		boolean respuesta = oesteCableColor
				.agregarNuevoContacto(new Contacto(nombre, apellido, celular, correo, cp, localidad, provinciaElegida));
		if (respuesta) {
			System.out.println("Contacto añadido exitosamente.");
		} else {
			System.err.println("Fallo al añadir el contacto.");
		}
	}

	private static Provincia seleccionarProvincia() {
		Provincia[] listaProvincia = Provincia.values();
		for (int i = 0; i < listaProvincia.length; i++) {
			System.out.println((i + 1) + "-" + listaProvincia[i]);
		}
		int indexProvincia = teclado.nextInt();
		teclado.nextLine();
		return listaProvincia[indexProvincia - 1];
	}

	private static void realizarNuevaLlamada() {
		boolean volverALlamar = true;
		Contacto nuevoCandidato = oesteCableColor.buscarCandidato();
		if(nuevoCandidato == null) {
			System.err.println("No se ha encontrado un contacto disponible para realizar la llamada."
					+ " Verifique que existan contactos registrados en su agenda o que estén dentro de la zona de cobertura.");
			return;
		}
		System.out.println("Llamar al siguiente contacto:");
		verInformacionDelContacto(nuevoCandidato);

		boolean llamadaFueExitosa = validarExitoLlamada();
		
		if(!llamadaFueExitosa) {
			volverALlamar = elContactoSolicitaOtraLlamada();
		}
		
		System.out.println("Ingrese las observaciones con respecto a la llamada:");
		String observaciones = teclado.nextLine();

		Llamada llamadaRealizada = new Llamada(llamadaFueExitosa ,observaciones);
		
		llamadaRealizada.setFechaRealizada(fechaActual);
		llamadaRealizada.setVolverALlamar(volverALlamar);
	
		operadorActual.incrementarCantidadDeLlamadasRealizadas();
		boolean seRegistroLlmada = nuevoCandidato.registrarNuevaLlamada(llamadaRealizada, operadorActual);
		
		if (llamadaFueExitosa) {
			 operadorActual.agregarCliente(nuevoCandidato);
		}else if(!volverALlamar) {
			 nuevoCandidato.noVolverALlamar();
		};
		
		if (seRegistroLlmada) {
			System.out.println("Los datos de la llamada fueron guardados con exito.");
		} else {
			System.err.println("Error al guardar los datos de la llamada.");
		}
	}

	private static boolean elContactoSolicitaOtraLlamada() {
		char opcionVolverLlamar;
		boolean volverALlamar=true;
		do {
			System.out.println("¿Desea el contacto que lo vuelvan a llamar? (S/N):");
			opcionVolverLlamar = teclado.next().toUpperCase().charAt(0);
			teclado.nextLine();
		}while(opcionVolverLlamar != 'S' && opcionVolverLlamar != 'N');
		volverALlamar = opcionVolverLlamar != 'N';
		return volverALlamar;
	}

	private static boolean validarExitoLlamada() {
		char opcionLlamadaExitosa;
		do {
			System.out.println("¿La llamada fue exitosa? (S/N):");
			opcionLlamadaExitosa = teclado.next().toUpperCase().charAt(0);
			teclado.nextLine();
		} while (opcionLlamadaExitosa != 'S' && opcionLlamadaExitosa != 'N');

		boolean llamadaFueExitosa = opcionLlamadaExitosa == 'S';
		return llamadaFueExitosa;
	}

	private static void verInformacionDelContacto(Contacto contacto) {

		if (contacto != null) {
			System.out.println(contacto);
			if (!contacto.getRegistrosLlamadas().isEmpty()) {
				System.out.println("Ultima atención realiza por operador");
				System.out.println(contacto.getAtendidoPor());
				System.out.println("Registros de llamadas: ");
				System.out.println(contacto.getRegistrosLlamadas());
			}
		} else {
			System.err.println("No es posible visualizar los datos del contacto. Intente nuevamente. ");
		}

	}

	// SUBMENU BUSCADOR
	
	private static Contacto buscardorContactos() {
		Contacto contactoBuscado = null;
		MenuBuscador opcionSeleccionada = MenuBuscador.VOLVER;
		do {
			subMenuBuscadorContactos();
			opcionSeleccionada = seleccionarOpcionBuscador();
		} while (opcionSeleccionada == null);

		switch (opcionSeleccionada) {
		case BUSCAR_POR_CRITERIO:
			System.out.println("Ingrese el dato (email, apellido o tel) del contacto: ");
			String emailBuscado = teclado.next();
			contactoBuscado = oesteCableColor.buscarContacto(emailBuscado);
			break;
		case BUSCAR_PRIMERO_COINCIDENTE_POR_CP:
			System.out.println("Ingrese el codigo postal del contacto: ");
			int CodigoPostalBuscado = teclado.nextInt();
			contactoBuscado = oesteCableColor.buscarPorCodigoPostal(CodigoPostalBuscado);
			break;
		case VOLVER:
			break;
		}
		return contactoBuscado;
	}
	
	private static MenuBuscador seleccionarOpcionBuscador() {
		int opcionElegida = teclado.nextInt();
		for (int i = 0; i < opcionesBuscador.length; i++) {
			if ((i + 1) == opcionElegida) {
				return opcionesBuscador[i];
			}
		}
		return null;
	}

	private static void subMenuBuscadorContactos() {
		System.out.println("*********Buscador de contactos*******");
		for (int i = 0; i < opcionesBuscador.length; i++) {
			System.out.println((i + 1) + "-" + opcionesBuscador[i]);
		}
		System.out.println("***********************************");
		System.out.println("Ingrese la opción deseada");

	}
	
}
