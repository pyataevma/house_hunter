package viejo;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Habitacion;
import BLL.TipoHabitacion;
import DLL.ControllerHabitacion;

public class GestionHabitacion {

	public static void mostrarMenu() {
        String[] menuOptions = {"Ver habitaciones", "Agregar habitación", "Modificar habitación", "Eliminar habitación", "Volver"};
        boolean continueMenu = true;
        LinkedList<Habitacion> habitaciones;
        Habitacion habitacion;
        int n = 0, nro = 0, piso = 0, id = 0;
    	double precio = 0;
    	TipoHabitacion tipo = null;
        while (continueMenu) {
            int selectedOption = JOptionPane.showOptionDialog(null,
                    "Selecciona una opción:", "Gestión de habitaciones",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, menuOptions, menuOptions[0]);
     
            switch (selectedOption) {
                case 0:
                	habitaciones = ControllerHabitacion.mostrarHabitaciones();
                	String lista="La lista tiene " + habitaciones.size() + " habitaciones:\n";
                	for (Habitacion unaHabitacion: habitaciones) {
                		lista += unaHabitacion.toString() + "\n";
                	}
                    JOptionPane.showMessageDialog(null, lista);
                    break;
                case 1:
                	boolean cancelado= false; 
                	try {
                		nro = InterfazUsuario.pedirInt("Ingrese numero de la habitacion");
                		} catch(Exception e) {
                			cancelado = true;
                		}
                	if (!cancelado) {
                    	try {
                    		piso = InterfazUsuario.pedirInt("Ingrese piso de la habitacion");
                        	} catch(Exception e) {
                        		cancelado= true;
                        	}
                	}
                	if (!cancelado) {
                    	try {
                        	precio = InterfazUsuario.pedirDouble("Ingrese precio de la habitacion");
                    		} catch(Exception e) {
                        		cancelado= true;
                        	}
                	}
                	if (!cancelado) {
	                   	 String elegido =(String)JOptionPane.showInputDialog(null, "Elegi una habitacion para editar", "Habitaciones", 
	                         		JOptionPane.QUESTION_MESSAGE, null, TipoHabitacion.getNombres(), TipoHabitacion.getNombres()[0]);
	                   	 if (elegido == null) {
	                   		 cancelado=true;
	                   	 } else {
	                   		 tipo = new TipoHabitacion(elegido);
	                   	 }
                	}
                	if (!cancelado) {
                    	habitacion = new Habitacion(nro, piso, precio, tipo, 0);
                        ControllerHabitacion.agregarHabitacion(habitacion);
                	} else {
                		JOptionPane.showMessageDialog(null, "Agregacion de la habitación fue cancelada");
                	}
                	
            	    			                    
                    //Habitacion.AgregarHabitacion();
                    break;
                case 2:
                    habitaciones = ControllerHabitacion.mostrarHabitaciones();
                    cancelado = false;
                    try {
                    	n = InterfazUsuario.elegirOpcion(habitaciones);
                    } catch(Exception e) {
                    	cancelado= true;
                    }
                	if (!cancelado) {
                		id = habitaciones.get(n).getId();
	                	try {
	                		nro = InterfazUsuario.pedirInt("Ingrese numero de la habitacion", habitaciones.get(n).getNro());
	                	} catch(IllegalArgumentException e) {
	                			cancelado = true;
	                		
	                		} catch(Exception e) {
	                			cancelado = true;
	                		}
	                	
                	}
                	if (!cancelado) {
                    	try {
                    		piso = InterfazUsuario.pedirInt("Ingrese piso de la habitacion", habitaciones.get(n).getPiso());
                        	} catch(Exception e) {
                        		cancelado= true;
                        	}
                	}
                	if (!cancelado) {
                    	try {
                        	precio = InterfazUsuario.pedirDouble("Ingrese precio de la habitacion", habitaciones.get(n).getPrecio());
                    		} catch(Exception e) {
                        		cancelado= true;
                        	}
                	}
                	if (!cancelado) {
	                   	 String elegido =(String)JOptionPane.showInputDialog(null, "Elegi una habitacion para editar", "Habitaciones", 
	                         		JOptionPane.QUESTION_MESSAGE, null, TipoHabitacion.getNombres(), TipoHabitacion.getNombres()[0]);
	                   	 if (elegido == null) {
	                   		 cancelado=true;
	                   	 } else {
	                   		 tipo = new TipoHabitacion(elegido);
	                   	 }
                	}
                	if (!cancelado) {
                    	habitacion = new Habitacion(nro, piso, precio, tipo, id);
                        ControllerHabitacion.actualizarHabitacion(habitacion);
                	} else {
                		JOptionPane.showMessageDialog(null, "La modificacion de la habitación fue cancelada");
                	}                	
                    break;
                case 3:
                    habitaciones = ControllerHabitacion.mostrarHabitaciones();
                    cancelado = false;
                    try {
                    	n = InterfazUsuario.elegirOpcion(habitaciones);
                    } catch(Exception e) {
                    	cancelado= true;
                    }
                	if (!cancelado) {
                        id = habitaciones.get(n).getId();
                        ControllerHabitacion.eliminarHabitacion(id);
                	} else {
                		JOptionPane.showMessageDialog(null, "La eliminación de la habitación fue cancelada");
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
