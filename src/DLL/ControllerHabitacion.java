package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Habitacion;
import BLL.TipoHabitacion;

public class ControllerHabitacion {
    
	private static Connection con = Conexion.getInstance().getConection();

	private static String tableName="habitacion";
	
	// Método para agregar un habitacion

	public static void agregarHabitacion(Habitacion habitacion) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO `habitacion`(`nro`, `piso`, `precio`, `tipo`) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, habitacion.getNro());
            statement.setInt(2, habitacion.getPiso());
            statement.setDouble(3, habitacion.getPrecio());
            statement.setString(4, habitacion.getTipo().getNombre());
           
            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Habitacion agregado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar habitacion: " + e.getMessage());
            e.printStackTrace();
        }
    }

	
	public static LinkedList<Habitacion> mostrarHabitaciones() {
        LinkedList<Habitacion> habitacions = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `" +tableName + "`");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Habitacion habitacion = new Habitacion(
                	resultSet.getInt("nro"),
                    resultSet.getInt("piso"),
                	resultSet.getDouble("precio"),
                	//TipoHabitacion.valueOf(resultSet.getString("tipo")),
                	new TipoHabitacion(resultSet.getString("tipo")),
                	resultSet.getInt("id_habitacion"));

                habitacions.add(habitacion);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar habitacions: " + e.getMessage());
            e.printStackTrace();
        }
        return habitacions;
    }

    // Método para buscar un habitacion por ID
	public static Habitacion buscarHabitacion(int id) {
        Habitacion habitacion = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + tableName + "` WHERE id_habitacion = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               habitacion = new Habitacion(
                    	resultSet.getInt("nro"),
                        resultSet.getInt("piso"),
                    	resultSet.getDouble("precio"),
                    	//TipoHabitacion.valueOf(resultSet.getString("tipo")),
                    	new TipoHabitacion(resultSet.getString("tipo")),
                    	resultSet.getInt("id_habitacion"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar habitacion: " + e.getMessage());
            e.printStackTrace();
        }
        return habitacion;
    }
	
    public static void eliminarHabitacion(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM `habitacion` WHERE id_habitacion = ?");
            statement.setInt(1, id);
            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Habitacion eliminado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar habitacion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para actualizar los datos de un habitacion

    public static void actualizarHabitacion(Habitacion habitacion) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE `habitacion` SET `nro`=?, `piso`=?, `precio`=?, `tipo`=? WHERE id_habitacion = ?"
            );
            statement.setInt(1, habitacion.getNro());
            statement.setInt(2, habitacion.getPiso());
            statement.setDouble(3, habitacion.getPrecio());
            statement.setString(4, habitacion.getTipo().getNombre());
            statement.setInt(5, habitacion.getId()); 
            
            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Habitacion actualizado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar habitacion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
