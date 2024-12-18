package viejo;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import BLL.Cliente;
import DLL.ControllerCliente;

public class GestionCliente {

    public static void mostrarMenu() {
        String[] menuOptions = {"Ver clientes", "Agregar cliente", "Modificar cliente", "Eliminar cliente", "Volver"};
        boolean continueMenu = true;
        LinkedList<Cliente> clientes;
        Cliente cliente;
        int n = 0, id = 0;
        String nombre = null, apellido = null, dni = null;
        int nivel = 0;

        while (continueMenu) {
            int selectedOption = JOptionPane.showOptionDialog(null,
                    "Selecciona una opción:", "Gestión de clientes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, menuOptions, menuOptions[0]);

            switch (selectedOption) {
                case 0:
                    // Ver lista de clientes
                    clientes = ControllerCliente.mostrarClientes();
                    String lista = "Lista de clientes:\n";
                    for (Cliente c : clientes) {
                        lista += c.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, lista);
                    break;

                case 1:
                    // Agregar un nuevo cliente
                    boolean cancelado = false; 
                    try {
                        nombre = JOptionPane.showInputDialog("Ingrese nombre del cliente");
                        apellido = JOptionPane.showInputDialog("Ingrese apellido del cliente");
                        dni = JOptionPane.showInputDialog("Ingrese DNI del cliente");
                        nivel = InterfazUsuario.pedirInt("Ingrese nivel del cliente (0-2)");
                    } catch (Exception e) {
                        cancelado = true;
                    }
                    if (!cancelado) {
                        cliente = new Cliente(nombre, apellido, dni, "email", nivel, selectedOption, id);
                        ControllerCliente.agregarCliente(cliente);
                        JOptionPane.showMessageDialog(null, "Cliente agregado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Agregación de cliente fue cancelada.");
                    }
                    break;

                case 2:
                    // Modificar un cliente existente
                    clientes = ControllerCliente.mostrarClientes();
                    cancelado = false;
                    try {
                        n = InterfazUsuario.elegirOpcion(clientes);
                        id = clientes.get(n).getId();
                        nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre del cliente", clientes.get(n).getNombre());
                        apellido = JOptionPane.showInputDialog("Ingrese nuevo apellido del cliente", clientes.get(n).getApellido());
                        dni = JOptionPane.showInputDialog("Ingrese nuevo DNI del cliente", clientes.get(n).getDni());
                        nivel = InterfazUsuario.pedirInt("Ingrese nuevo nivel del cliente (0-2)", clientes.get(n).getNivel());
                    } catch (Exception e) {
                        cancelado = true;
                    }
                    if (!cancelado) {
                        cliente = new Cliente(nombre, apellido, dni, "email", nivel, selectedOption, id);
                        cliente.setId(id); // Asignar el ID existente al cliente modificado
                        ControllerCliente.actualizarCliente(cliente);
                        JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Modificación cancelada.");
                    }
                    break;

                case 3:
                    // Eliminar un cliente
                    clientes = ControllerCliente.mostrarClientes();
                    cancelado = false;
                    try {
                        n = InterfazUsuario.elegirOpcion(clientes);
                        id = clientes.get(n).getId();
                        JOptionPane.showMessageDialog(null, n+" "+ id);
                        ControllerCliente.eliminarCliente(id);
                        JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
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