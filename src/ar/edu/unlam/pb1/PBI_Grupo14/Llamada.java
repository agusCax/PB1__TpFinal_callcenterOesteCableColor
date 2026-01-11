package ar.edu.unlam.pb1.PBI_Grupo14;

public class Llamada {

	private boolean fueExitosa;
	private String observaciones;
	private boolean volverALlamar;
	private String fechaRealizada;
	
	public Llamada(boolean fueExitosa,String observaciones) {
		this.setFueExitosa(fueExitosa);
		this.setObservaciones(observaciones);
	}

	public String getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(String fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public boolean isFueExitosa() {
		return fueExitosa;
	}
	
	public void setFueExitosa(boolean fueExitosa) {
		this.fueExitosa = fueExitosa;
	}

	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public boolean isVolverALlamar() {
		return volverALlamar;
	}

	public void setVolverALlamar(boolean volverALlamar) {
		this.volverALlamar = volverALlamar;
	}

	public String toString() {
		String datosLlamada = "[" + this.fechaRealizada + "] " +" Observaciones: "+ observaciones ;
		if(isFueExitosa()) {
			datosLlamada+= " [fue exitosa]";
		}
		return datosLlamada;
	}
}
