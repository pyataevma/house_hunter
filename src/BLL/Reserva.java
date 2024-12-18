package BLL;
import java.time.LocalDate;

public class Reserva implements Pagable {

    //public static final String ControllerReserva = null;
	//private Habitacion habitacion;
    private int idHabitacion;
    private int idCliente; 
    private LocalDate fingreso;
    private LocalDate fsalida;
    //private Cliente cliente;
    //public enum Estado {ACTIVA, VENCIDA};
    //private Estado estado;
    private int id;

    // Constructor completo con todos los parámetros, incluyendo el estado
    public Reserva(int idHabitacion, LocalDate fingreso, LocalDate fsalida, int idCliente, int id) {
        //this.habitacion = habitacion;
        this.fingreso = fingreso;
        this.fsalida = fsalida;
        this.idHabitacion = idHabitacion;
        this.idCliente = idCliente;
        //this.cliente = cliente;
        //this.estado = Estado.ACTIVA;
        this.id = id;
    }

    // Constructor alternativo con estado especificado
//    public Reserva(Habitacion habitacion, LocalDate fingreso, LocalDate fsalida, Cliente cliente, Estado estado) {
//        this.habitacion = habitacion;
//        this.fingreso = fingreso;
//        this.fsalida = fsalida;
//        this.cliente = cliente;
//        this.estado = estado;
//    }

    // Nuevo constructor con la firma que causaba error
//    public Reserva(Habitacion habitacion, LocalDate fingreso, LocalDate fsalida, Cliente cliente) {
//        this.habitacion = habitacion;
//        this.fingreso = fingreso;
//        this.fsalida = fsalida;
//        this.cliente = cliente;
//        this.estado = Estado.ACTIVA; // Estado predeterminado
//    }

    @Override
    public String toString() {
        return "Reserva [habitacion=" + idHabitacion + ",\n fingreso=" + fingreso + ",\n fsalida=" + fsalida + ",\n cliente=" + idCliente + "]";
    }

    public String toStringShort() {
        return "Habitacion: ID = " + idHabitacion + ", de " + fingreso + " a " + fsalida + ", Cliente: ID = " + idCliente;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
//
//    public Estado getEstado() {
//        return estado;
//    }
//
//    public void setEstado(Estado estado) {
//        this.estado = estado;
//    }
//
//    public Habitacion getHabitacion() {
//        return habitacion;
//    }
//
//    public void setHabitacion(Habitacion habitacion) {
//        this.habitacion = habitacion;
//    }

    public LocalDate getFingreso() {
        return fingreso;
    }

    public void setFingreso(LocalDate fingreso) {
        this.fingreso = fingreso;
    }

    public LocalDate getFsalida() {
        return fsalida;
    }

    public void setFsalida(LocalDate fsalida) {
        this.fsalida = fsalida;
    }

    // Método para crear una reserva aleatoria
//    public static Reserva crearRandom(Habitacion habitacion, LocalDate inicialFecha, Cliente cliente) {
//        LocalDate fingreso = inicialFecha.plusDays((int)(Math.random() * 30)); 
//        int duracion = (int)(Math.random() * 14 + 1);
//        return new Reserva(habitacion, fingreso, fingreso.plusDays(duracion), cliente);
//    }

    public int getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	// Métodos de la interfaz Pagable
    @Override
    public void procesarPago(double cantidad) {
        // Implementación pendiente
    }

    @Override
    public double calcularImporte(double... importes) {
        double total = 0;
        for (double importe : importes) {
            total += importe;
        }
        return total;
    }
}