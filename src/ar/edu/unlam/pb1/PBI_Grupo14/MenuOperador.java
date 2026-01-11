package ar.edu.unlam.pb1.PBI_Grupo14;

public enum MenuOperador {
		INGRESAR("Inciar sesión"),
		REGISTRARSE("Registrase"),
		SALIR("Finalizar programa");
	
	private String detalle;
	
	private MenuOperador(String detalle) {
		this.detalle = detalle;
	}
	
	public String toString() {
		return this.detalle;
	}
}
