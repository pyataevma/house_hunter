package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import BLL.Empleado;

public class ControllerEmpleado{

    // Cambiar a java.sql.Connection
    private static Connection con = Conexion.getInstance().getConection();

    // Método para agregar un empleado
    public static void agregarEmpleado(Empleado empleado) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO `empleado`(`user`, `pass`, `rol`) VALUES (?, ?, ?)"
            );
            statement.setString(1, empleado.getUser());
            statement.setString(2, empleado.getPass());
            statement.setString(3, empleado.getRol());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Empleado agregado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para mostrar empleados
    public static LinkedList<Empleado> mostrarEmpleados() {
        LinkedList<Empleado> empleados = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `empleado`");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Empleado empleado = new Empleado("nombre","apellido","dni",
                    resultSet.getString("user"),
                    resultSet.getString("pass"),
                    resultSet.getString("rol"));
                empleados.add(empleado);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar empleados: " + e.getMessage());
            e.printStackTrace();
        }
        return empleados;
    }

    // Método para buscar un empleado por ID
    public static Empleado buscarEmpleado(int id) {
        Empleado empleado = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `empleado` WHERE id_empleado = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                empleado = new Empleado("nombre","apellido","dni",
                                resultSet.getString("user"),
                                resultSet.getString("pass"),
                                resultSet.getString("rol"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar empleado: " + e.getMessage());
            e.printStackTrace();
        }
        return empleado;
    }

    public static void eliminarEmpleado(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM `empleado` WHERE id_empleado = ?");
            statement.setInt(1, id);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para actualizar los datos de un empleado
    public static void actualizarEmpleado(Empleado empleado) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE `empleado` SET `user`=?, `pass`=?, `rol`=? WHERE id_empleado = ?"
            );
            statement.setString(1, empleado.getUser());
            statement.setString(2, empleado.getPass());
            statement.setString(3, empleado.getRol());
            

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Empleado actualizado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}