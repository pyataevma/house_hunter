package BLL;
import java.time.LocalDate;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Hotel {

    public enum Localidad {RECOLETA, URQUIZA};
    private Localidad localidad;
    private LinkedList<Habitacion> habitaciones = new LinkedList<>();
    private LinkedList<Cliente> clientes = new LinkedList<>();
    private LinkedList<Empleado> empleados = new LinkedList<>();
    private LinkedList<Reserva> reservas = new LinkedList<>();

    // Constructor
    public Hotel(Localidad localidad) {
        this.localidad = localidad;
    }

    // Getters y setters
    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public LinkedList<Habitacion> getHabitaciones() {
        return new LinkedList<>(habitaciones); // Retorna copia de la lista
    }

    public void setHabitaciones(LinkedList<Habitacion> habitaciones) {
        this.habitaciones = new LinkedList<>(habitaciones);
    }

    public LinkedList<Cliente> getClientes() {
        return new LinkedList<>(clientes); // Retorna copia de la lista
    }

    public void setClientes(LinkedList<Cliente> clientes) {
        this.clientes = new LinkedList<>(clientes);
    }

    public LinkedList<Empleado> getEmpleados() {
        return new LinkedList<>(empleados); // Retorna copia de la lista
    }

    public void setEmpleados(LinkedList<Empleado> empleados) {
        this.empleados = new LinkedList<>(empleados);
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    // Fin Getters y setters
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    // Métodos --------------------------------------------------------------

    public void verClientes() {
        StringBuilder s = new StringBuilder();
        int num = 1;
        for (Cliente cliente : clientes) {
            s.append(num).append(". ").append(cliente.toString()).append("\n");
            num++;
        }
        JOptionPane.showMessageDialog(null, "Los clientes son: \n" + s);
    }

    public void verHabitaciones() {
        StringBuilder s = new StringBuilder();
        int num = 1;
        for (Habitacion habitacion : habitaciones) {
            s.append(num).append(". ").append(habitacion.toStringShort()).append("\n");
            num++;
        }
        JOptionPane.showMessageDialog(null, "Las habitaciones son: \n" + s);
    }

    

    public void llenarHabitaciones(int cantidadPisos, int cantidadEnPiso) {
        double precio;
        TipoHabitacion tipo;
        for (int i = 0; i < cantidadPisos; i++) { // Cambiado a i=0
            for (int j = 0; j < cantidadEnPiso; j++) {
                if (j == 0) {
                    tipo = new TipoHabitacion("FAMILIAR");
                    precio = 40000;
                } else if (j == 1) {
                    tipo = new TipoHabitacion("VIP");
                    precio = 90000;
                } else {
                    tipo = new TipoHabitacion("PAREJA");
                    precio = 30000;
                }
                agregarHabitacion(new Habitacion(i * 100 + j + 1, i, precio, tipo, 0));
            }
        }
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

    // Obtener habitaciones disponibles para un rango de fechas
    public LinkedList<Habitacion> getHabitacionesDisponibles(LocalDate ingreso, LocalDate salida) {
        LinkedList<Habitacion> disponibles = new LinkedList<>();
        for (Habitacion habitacion : habitaciones) {
            Reserva nueva = new Reserva(habitacion.getId(), ingreso, salida, 0, 0); // No requiere cliente ficticio
            if (estaDisponible(nueva)) {
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }

    // Registrar reserva de un cliente
    public void registrarReserva(Cliente cliente, LocalDate ingreso, LocalDate salida) {
        LinkedList<Habitacion> disponibles = getHabitacionesDisponibles(ingreso, salida);
        if (disponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay habitaciones disponibles para las fechas seleccionadas.");
            return;
        }

        Habitacion habitacionSeleccionada = disponibles.get(0); // Selecciona la primera disponible
        Reserva nuevaReserva = new Reserva(habitacionSeleccionada.getId(), ingreso, salida, cliente.getId(),0);
        reservas.add(nuevaReserva);

        JOptionPane.showMessageDialog(null, "Reserva registrada:\nCliente: " + cliente.toString() +
                "\nHabitación: " + habitacionSeleccionada.toStringShort() +
                "\nFechas: " + ingreso + " a " + salida);
    }
}