package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import BLL.Cliente;

public class ControllerCliente {

    private static Connection con = Conexion.getInstance().getConection();

    // Método para agregar un nuevo cliente a la base de datos
    //Cliente(nombre, apellido, dni, email, int telefono, int nivel, int id )
    public static void agregarCliente(Cliente cliente) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO `cliente`(`nombre`, `apellido`, `dni`, `email`, `telefono`, `nivel`) VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getDni());
            statement.setString(4, cliente.getEmail());
            statement.setInt(5, cliente.getTelefono());
            statement.setInt(6, cliente.getNivel());
            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente agregado con éxito");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar cliente: " + e.getMessage());
        }
    }

    // Método para mostrar todos los clientes en la base de datos
    public static LinkedList<Cliente> mostrarClientes() {
        LinkedList<Cliente> clientes = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `cliente`");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = new Cliente(
                		  resultSet.getString("nombre"),
                          resultSet.getString("apellido"),
                          resultSet.getString("dni"),
                          resultSet.getString("email"),
                          resultSet.getInt("telefono"),
                          resultSet.getInt("nivel"),
                          resultSet.getInt("id_cliente")
                );
                clientes.add(cliente);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar clientes: " + e.getMessage());
        }
        return clientes;
    }

    // Método para buscar un cliente en la base de datos por su ID
    public static Cliente buscarCliente(int id) {
        Cliente cliente = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `cliente` WHERE id_cliente = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cliente = new Cliente(
                  		    resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("dni"),
                            resultSet.getString("email"),
                            resultSet.getInt("telefono"),
                            resultSet.getInt("nivel"),
                            resultSet.getInt("id_cliente")
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar cliente: " + e.getMessage());
        }
        return cliente;
    }

    // Método para eliminar un cliente de la base de datos por su ID
    public static void eliminarCliente(int id) {
        try {
        	JOptionPane.showMessageDialog(null, id);
            PreparedStatement statement = con.prepareStatement("DELETE FROM `cliente` WHERE id_cliente = ?");
            statement.setInt(1, id);
            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());
        }
    }

    // Método para actualizar un cliente en la base de datos
    public static void actualizarCliente(Cliente cliente) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE `cliente` SET `nombre`=?, `apellido`=?, `dni`=?, `email`=?,  `telefono`=?, `nivel`=? WHERE id_cliente = ?"
            );
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getDni());
            statement.setString(4, cliente.getEmail());
            statement.setInt(5, cliente.getTelefono());
            statement.setInt(6, cliente.getNivel());
            statement.setInt(7, cliente.getId());          
            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar cliente: " + e.getMessage());
        }
    }
}
