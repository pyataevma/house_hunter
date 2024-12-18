package BLL;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;


public class Habitacion {

	private int id;
	private int nro;
	private int piso;
    private	TipoHabitacion tipo;
	private double precio;
    private LinkedList<Beneficio> beneficios = new LinkedList<Beneficio>();
    private LinkedList<Limpieza> limpiezas = new LinkedList<Limpieza>();
    private LinkedList<Reserva> reservas = new LinkedList<Reserva>();

    public Habitacion(int nro, int piso, double precio, TipoHabitacion tipo, int id) {
        this.nro = nro;
        this.piso = piso;
        this.precio = precio;
        this.tipo = tipo;
        this.id = id;
    }

//    public boolean instanciarReserva(Cliente cliente, LocalDate fingreso, LocalDate fsalida) {
//        if (cliente == null || fingreso == null || fsalida == null) {
//            JOptionPane.showMessageDialog(null, "Error: Datos de reserva inválidos.");
//            return false;
//        }
//        Reserva reserva = new Reserva(this, fingreso, fsalida, cliente);
//        return agregarReserva(reserva);
//    }

    public boolean agregarReserva(Reserva reserva) {
        if (estaDisponible(reserva)) {
            reservas.add(reserva);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La habitación no está disponible para las fechas seleccionadas.");
            return false;
        }
    }

    public void verReservas() {
        StringBuilder s = new StringBuilder();
        int num = 1;
        for (Reserva reserva : reservas) {
            s.append(num).append(". ").append(reserva.toString()).append("\n");
            num++;
        }
        JOptionPane.showMessageDialog(null, "Las reservas son: \n" + s.toString());
    }

    public boolean estaDisponible(Reserva nueva) {
        for (Reserva reserva : reservas) {
            if (reserva.getIdHabitacion() == nueva.getIdHabitacion()) {
                if (!(reserva.getFingreso().isAfter(nueva.getFsalida()) || reserva.getFsalida().isBefore(nueva.getFingreso()))) {
                    return false; // Hay conflicto de fechas
                }
            }
        }
        return true; // Disponible
    }
    
    public boolean estaDisponible(LocalDate fIngreso, LocalDate fSalida) {
        for (Reserva reserva : reservas) {
        	if (!(reserva.getFingreso().isAfter(fSalida) || reserva.getFsalida().isBefore(fIngreso))) {
                return false; // Hay conflicto de fechas
            }
        }
        return true; // Disponible
    }

    @Override
    public String toString() {
        //return "Habitacion [nro=" + nro + ", piso=" + piso + ", precio=" + precio + " tipo=" + tipo + " ]";
    	return toStringShort();
    }

    public String toStringShort() {
        return tipo + " № " + nro + ", piso " + piso + ", $" + precio;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// Getters y Setters
    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public LinkedList<Beneficio> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(LinkedList<Beneficio> beneficios) {
        this.beneficios = beneficios;
    }

    public LinkedList<Limpieza> getLimpiezas() {
        return limpiezas;
    }

    public void setLimpiezas(LinkedList<Limpieza> limpiezas) {
        this.limpiezas = limpiezas;
    }

    public LinkedList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(LinkedList<Reserva> reservas) {
        this.reservas = reservas;
    }

	
}
