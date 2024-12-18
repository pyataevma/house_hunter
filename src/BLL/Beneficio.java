package BLL;
import java.time.LocalTime;
import java.util.LinkedList;

public class Beneficio {

	private LocalTime hinicio;
	private LocalTime hterm;
	private double importe;
	private LinkedList<Habitacion> habitaciones = new LinkedList<Habitacion>();
	public enum Tipo {MONTO, PORCENTAJE}
	private Tipo tipo; 

	public Beneficio(LocalTime hinicio, LocalTime hterm, double importe, Beneficio.Tipo tipo) {
		super();
		this.hinicio = hinicio;
		this.hterm = hterm;
		this.importe = importe;
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
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public LinkedList<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(LinkedList<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

}
