package ar.edu.unlam.pb1.PBI_Grupo14;

public enum MenuBuscador {
//	BUSCAR_POR_APELLIDO("Busqueda por número de celular"),
//	BUSCAR_POR_CELULAR("Busqueda por apellido"),
//	BUSCAR_POR_EMAIL("Busqueda por email"),
	BUSCAR_POR_CRITERIO("Buscar por celular, email, apellido"),
	BUSCAR_PRIMERO_COINCIDENTE_POR_CP("Buscar la primer coincidencia de contacto por CP"),
	VOLVER("Volver al menú anterior");
	
	private String detalle;
	
	private MenuBuscador(String detalle) {
		this.detalle = detalle ;
	}
	
	public String toString() {
		return this.detalle;
	}
}
