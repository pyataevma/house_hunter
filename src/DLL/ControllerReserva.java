package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Reserva;
//import BLL.Habitacion; // Asegúrate de importar Habitacion
//import BLL.Cliente; // Asegúrate de importar Cliente
import java.sql.Date; // Asegúrate de importar java.sql.Date

public class ControllerReserva {
    
    private static Connection con = Conexion.getInstance().getConection();

    private static String tableName = "reserva";
    
    // Método para agregar una reserva

    public static void agregarReserva(Reserva reserva) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO reserva(fecha_entrada, fecha_salida, id_habitacion, id_cliente) VALUES (?, ?, ?, ?)"
            );

            // Convertir LocalDate a java.sql.Date
            statement.setDate(1, Date.valueOf(reserva.getFingreso()));
            statement.setDate(2, Date.valueOf(reserva.getFsalida()));
            // Convertir el enum Estado a String
          // statement.setString(3, reserva.getEstado().name());
            // Obtener el ID de la habitación
            statement.setInt(3, reserva.getIdHabitacion()); // Obtener ID de la habitación
            // Obtener el ID del cliente
            statement.setInt(4, reserva.getIdCliente()); // Asegúrate de que esto sea correcto
           //JOptionPane.showInternalMessageDialog(null, statement);
            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Reserva agregado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static LinkedList<Reserva> mostrarReservas() { 
        LinkedList<Reserva> reservas = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + tableName + "");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Habitacion habitacion = obtenerHabitacionPorId(resultSet.getInt("id_habitacion")); // Método hipotético
                //Cliente cliente = obtenerClientePorId(resultSet.getInt("id_cliente")); // Método hipotético

                Reserva reserva = new Reserva(
                	resultSet.getInt("id_habitacion"),
                    resultSet.getDate("fecha_entrada").toLocalDate(),
                    resultSet.getDate("fecha_salida").toLocalDate(),
                    resultSet.getInt("id_cliente"),
                    resultSet.getInt("id_reserva")                    
                );

                reservas.add(reserva);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar reservas: " + e.getMessage());
            e.printStackTrace();
        }
        return reservas;
    }

    // Método para buscar una reserva por ID
    public static Reserva buscarReserva(int id) {
        Reserva reserva = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id_reserva = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
//                Habitacion habitacion = obtenerHabitacionPorId(resultSet.getInt("id_habitacion")); // Método hipotético
//                Cliente cliente = obtenerClientePorId(resultSet.getInt("id_cliente")); // Método hipotético

                reserva = new Reserva(
                	resultSet.getInt("id_habitacion"),
                    resultSet.getDate("fecha_entrada").toLocalDate(),
                    resultSet.getDate("fecha_salida").toLocalDate(),
                    resultSet.getInt("id_cliente"),
                    resultSet.getInt("id_reserva")
                );
            }
        } catch (Exception e) {
            System.out.println("Error al buscar reserva: " + e.getMessage());
            e.printStackTrace();
        }
        return reserva;
    }
    
    public static void eliminarReserva(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM reserva WHERE id_reserva = ?");
            statement.setInt(1, id);
            //JOptionPane.showMessageDialog(null, statement);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Reserva eliminado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para actualizar los datos de una reserva

    public static void actualizarReserva(Reserva reserva) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE reserva SET fecha_entrada=?, fecha_salida=? WHERE id_reserva = ?"
            );
            statement.setDate(1, Date.valueOf(reserva.getFingreso()));
            statement.setDate(2, Date.valueOf(reserva.getFsalida()));
            // Convertir el enum Estado a String
            //statement.setString(3, reserva.getEstado().name());
            statement.setInt(3, reserva.getId()); 

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Reserva actualizado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    // Métodos hipotéticos para obtener Habitacion y Cliente por ID
//    public static Habitacion obtenerHabitacionPorId(int id) {
//        // Implementa la lógica para obtener una habitación por ID
//        return new Habitacion(id, id, id, null, id); // Cambia esto por la implementación real
//    }
//
//    public static Cliente obtenerClientePorId(int id) {
//        // Implementa la lógica para obtener un cliente por ID
//        return new Cliente(tableName, tableName, tableName, "email", id, id, id); // Cambia esto por la implementación real
//    }
}