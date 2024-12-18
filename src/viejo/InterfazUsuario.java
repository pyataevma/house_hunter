package viejo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class InterfazUsuario {
	public static int pedirInt(String mensaje) {
		boolean ok;
		int n = 0;
		String s;
		do {
			ok = true;
			s=null;
			s = JOptionPane.showInputDialog(null, mensaje, "Se solicita un numero entero", JOptionPane.QUESTION_MESSAGE);
			if (s != null) {
				try { 
					n = Integer.parseInt(s);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "¡No es numero intero!\nPor favor, ingrese numero intero.\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
					ok = false;
				}
			} else {
				throw new IllegalArgumentException("Usuario canceló la operacion");
			
			}
		} while (!ok);
		return n;
	}
	

	public static int pedirInt(String mensaje, int porDefecto) {
		boolean ok;
		int n = 0;
		String s;
		do {
			ok = true;
			s=null;
			s = JOptionPane.showInputDialog(null, mensaje, porDefecto);
			if (s != null) {
				try { 
					n = Integer.parseInt(s);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "¡No es numero intero!\nPor favor, ingrese numero intero.\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
					ok = false;
				}
			} else {
				throw new IllegalArgumentException("Usuario canceló la operacion");
			}
		} while (!ok);
		return n;
	}
	
	public static double pedirDouble(String mensaje) {
		boolean ok;
		double d = 0;
		String s;
		do {
			ok = true;
			s=null;
			s = JOptionPane.showInputDialog(null, mensaje, "Se solicita un numero", JOptionPane.QUESTION_MESSAGE);
			if (s != null) {
				try { 
					d = Double.parseDouble(s);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "¡No es numero!\nPor favor, ingrese un numero.\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
					ok = false;
				}
			} else {
				throw new IllegalArgumentException("Usuario canceló la operacion");
			}
		} while (!ok);
		return d;
	}
	
	public static double pedirDouble(String mensaje, double porDefecto) {
		boolean ok;
		double d = 0;
		String s;
		do {
			ok = true;
			s=null;
			s = JOptionPane.showInputDialog(null, mensaje, porDefecto);
			if (s != null) {
				try { 
					d = Double.parseDouble(s);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "¡No es numero!\nPor favor, ingrese un numero.\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
					ok = false;
				}
			} else {
				throw new IllegalArgumentException("Usuario canceló la operacion");
			}
		} while (!ok);
		return d;
	}
	
	
	public static <T> int elegirOpcion(LinkedList<T> objetos) {
		String[] options = new String[objetos.size()]; 
		int numero = 0; 
		for (int i = 0; i<objetos.size(); i++) {
        	options[i]=(i+1)+" "+objetos.get(i).toString();
        }
        String elegido =(String)JOptionPane.showInputDialog(null, "Elegi una habitacion para editar", "Habitaciones", 
        		JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (elegido == null) {
        	throw new IllegalArgumentException("Usuario canceló la operacion");
        } else {
        	numero = Integer.parseInt(elegido.split(" ")[0])-1;
        } 
		return numero;
	}
	
	public static LocalDate pedirFecha(String mensaje) {
	    boolean ok;
	    String s;
	    LocalDate fecha = null;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de fecha deseado

	    do {
	        ok = true;
	        s = JOptionPane.showInputDialog(null, mensaje, "Se solicita una fecha", JOptionPane.QUESTION_MESSAGE);
	        
	        if (s != null) {
	            try {
	                fecha = LocalDate.parse(s, formatter); // Intenta analizar la fecha
	            } catch (DateTimeParseException e) {
	                JOptionPane.showMessageDialog(null, "¡Formato de fecha inválido!\nPor favor, ingrese una fecha en formato YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
	                ok = false;
	            }
	        } else {
	            throw new IllegalArgumentException("Usuario canceló la operación");
	        }
	    } while (!ok);
	    
	    return fecha;
	}

}
