package ar.edu.unlam.pb1.PBI_Grupo14;

public enum MenuOpciones {

	INCORPORAR_ZONA_COBERTURA("1 - Incorporar zona de cobertura",1),
	ALTA_NUEVO_CONTACTO("2 - Dar de alta un nuevo contacto",2),
	REALIZAR_LLAMADA("3 - Realizar nueva llamada",3),
	VER_INFORMACION_DE_CONTACTO("4 - Buscar información de un contacto",4),
	VER_INFORMACION_DE_OPERADOR("5 - Ver mis datos", 5),
	CERRAR_SESION("9 - Cerrar sesión",9);
	
	private String detalle;
	private int valorOpcion;
	
	private MenuOpciones(String detalle, int valorOpcion) {
		this.detalle = detalle ;
		this.valorOpcion = valorOpcion;
	}
	
	public String toString() {
		return this.detalle;
	}
	
	public int getValor() {
		return this.valorOpcion;
	}
	
}
