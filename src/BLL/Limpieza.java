package BLL;
import java.time.LocalTime;

public class Limpieza {

	private LocalTime hinicio;
	private LocalTime hterm;
	public enum Tipo {GENERAL, PARCIAL, ROPA_BLANCA}
	private Tipo tipo;

	
	public Limpieza(LocalTime hinicio, LocalTime hterm, Limpieza.Tipo tipo) {
		super();
		this.hinicio = hinicio;
		this.hterm = hterm;
		this.tipo = tipo;
	}	
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public LocalTime getHinicio() {
		return hinicio;
	}
	public void setHinicio(LocalTime hinicio) {
		this.hinicio = hinicio;
	}
	public LocalTime getHterm() {
		return hterm;
	}
	public void setHterm(LocalTime hterm) {
		this.hterm = hterm;
	}
}
