package viejo;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Cliente;
import BLL.Habitacion;
import BLL.Reserva;
import DLL.ControllerCliente;
import DLL.ControllerHabitacion;
import DLL.ControllerReserva;

public class GestionReserva {

	public static void mostrarMenu() {
        String[] menuOptions = {"Ver reservas", "Agregar reserva", "Modificar reserva", "Eliminar reserva", "Volver"};
        boolean continueMenu = true;
        LinkedList<Reserva> reservas;
        Reserva reserva;
        int n, id;
    	//double estado=0;        
        while (continueMenu) {
            int selectedOption = JOptionPane.showOptionDialog(null,
                    "Selecciona una opción:", "Gestión de reservas",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, menuOptions, menuOptions[0]);
     
            switch (selectedOption) {
            case 0:
                reservas = ControllerReserva.mostrarReservas();
                String lista = "La lista tiene " + reservas.size() + " reservas:\n";
                for (Reserva unaReserva : reservas) {
                    lista += unaReserva.toString() + "\n";
                }
                JOptionPane.showMessageDialog(null, lista);
                break;

            case 1:
                boolean cancelado = false; 
                Habitacion habitacion = null; // Inicializa la variable Habitacion
                Cliente cliente = null; // Inicializa la variable Cliente
                LocalDate fingreso1 = null; // Inicializa la fecha de ingreso
                LocalDate fsalida1 = null; // Inicializa la fecha de salida

                try {
                    fingreso1 = InterfazUsuario.pedirFecha("Ingrese fecha de ingreso (YYYY-MM-DD)"); // Método para pedir fecha
                } catch (Exception e) {
                    cancelado = true;
                }

                if (!cancelado) {
                    try {
                        fsalida1 = InterfazUsuario.pedirFecha("Ingrese fecha de salida (YYYY-MM-DD)"); // Método para pedir fecha
                    } catch (Exception e) {
                        cancelado = true;
                    }
                }

                if (!cancelado) {
                    try {
                        int idHabitacion = InterfazUsuario.pedirInt("Ingrese ID de la habitación");
                        habitacion = ControllerHabitacion.buscarHabitacion(idHabitacion); // Obtiene la Habitacion por ID
                        if (habitacion == null) {
                            JOptionPane.showMessageDialog(null, "Habitación no encontrada.");
                            cancelado = true;
                        }
                    } catch (Exception e) {
                        cancelado = true;
                    }
                }

                if (!cancelado) {
                    try {
                        int idCliente = InterfazUsuario.pedirInt("Ingrese ID del cliente");
                        cliente = ControllerCliente.buscarCliente(idCliente); // Obtiene el Cliente por ID
                        if (cliente == null) {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                            cancelado = true;
                        }
                    } catch (Exception e) {
                        cancelado = true;
                    }
                }

                if (!cancelado) {
                    reserva = new Reserva(habitacion.getId(), fingreso1, fsalida1, cliente.getId(),0);
                    ControllerReserva.agregarReserva(reserva);
                } else {
                    JOptionPane.showMessageDialog(null, "Agregación de la reserva fue cancelada");
                }
                break;


            case 2:
                reservas = ControllerReserva.mostrarReservas();
                n = InterfazUsuario.elegirOpcion(reservas);
                cancelado = false;

                // Inicializa las variables necesarias
                Habitacion habitacion1 = null; 
                Cliente cliente1 = null; 
                LocalDate fingreso11 = null; 
                LocalDate fsalida11 = null;

                if (!cancelado) {
                    try {
                        // Obtiene la habitación y el cliente
                        int idHabitacion = InterfazUsuario.pedirInt("Ingrese ID de la habitación");
                        habitacion1 = ControllerHabitacion.buscarHabitacion(idHabitacion);
                        if (habitacion1 == null) {
                            JOptionPane.showMessageDialog(null, "Habitación no encontrada.");
                            cancelado = true;
                        }
                    } catch (Exception e) {
                        cancelado = true;
                    }
                }

                if (!cancelado) {
                    try {
                        fingreso11 = InterfazUsuario.pedirFecha("Ingrese fecha de ingreso (YYYY-MM-DD)"); // Método para pedir fecha
                    } catch (Exception e) {
                        cancelado = true;
                    }
                }

                if (!cancelado) {
                    try {
                        fsalida11 = InterfazUsuario.pedirFecha("Ingrese fecha de salida (YYYY-MM-DD)"); // Método para pedir fecha
                    } catch (Exception e) {
                        cancelado = true;
                    }
                }

                if (!cancelado) {
                    try {
                        int idCliente = InterfazUsuario.pedirInt("Ingrese ID del cliente");
                        cliente1 = ControllerCliente.buscarCliente(idCliente);
                        if (cliente1 == null) {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                            cancelado = true;
                        }
                    } catch (Exception e) {
                        cancelado = true;
                    }
                }

                if (!cancelado) {
                    reserva = new Reserva(habitacion1.getId(), fingreso11, fsalida11, cliente1.getId(),0);
                    ControllerReserva.actualizarReserva(reserva);
                } else {
                    JOptionPane.showMessageDialog(null, "La modificación de la reserva fue cancelada");
                }
                break;

            case 3:
                reservas = ControllerReserva.mostrarReservas();
                n = InterfazUsuario.elegirOpcion(reservas);
                
                if (n >= 0 && n < reservas.size()) {
                    id = reservas.get(n).getId();
                    ControllerReserva.eliminarReserva(id);
                    JOptionPane.showMessageDialog(null, "Reserva eliminada con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "Selección no válida");
                }
                break;
                case 4:
                    continueMenu = false;
                    break;
                default:
                    continueMenu = false;
                    break;
            }
        }
	}
}