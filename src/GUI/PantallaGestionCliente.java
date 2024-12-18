package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import BLL.Cliente;
import DLL.ControllerCliente;

public class PantallaGestionCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public PantallaGestionCliente() {
        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 619, 498);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("GESTIÓN DE CLIENTES");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setBounds(200, 20, 250, 30);
        contentPane.add(lblTitle);

        JButton btnVerClientes = new JButton("VER CLIENTES");
        btnVerClientes.setBounds(212, 80, 200, 30);
        contentPane.add(btnVerClientes);

        JButton btnAgregarCliente = new JButton("AGREGAR CLIENTE");
        btnAgregarCliente.setBounds(212, 130, 200, 30);
        contentPane.add(btnAgregarCliente);

        JButton btnModificarCliente = new JButton("MODIFICAR CLIENTE");
        btnModificarCliente.setBounds(212, 180, 200, 30);
        contentPane.add(btnModificarCliente);

        JButton btnEliminarCliente = new JButton("ELIMINAR CLIENTE");
        btnEliminarCliente.setBounds(212, 230, 200, 30);
        contentPane.add(btnEliminarCliente);

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(212, 330, 200, 30);
        contentPane.add(btnVolver);

        // Botón "Ver Clientes"
        btnVerClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LinkedList<Cliente> clientes = ControllerCliente.mostrarClientes();
                if (clientes.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
                } else {
                    StringBuilder lista = new StringBuilder("Lista de Clientes:\n");
                    for (Cliente cliente : clientes) {
                        lista.append(cliente).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, lista.toString());
                }
            }
        });

        // Botón "Agregar Cliente"
        btnAgregarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = JOptionPane.showInputDialog("Ingrese nombre del cliente:");
                    String apellido = JOptionPane.showInputDialog("Ingrese apellido del cliente:");
                    String dni = JOptionPane.showInputDialog("Ingrese DNI del cliente:");
                    int nivel = Integer.parseInt(JOptionPane.showInputDialog("Ingrese nivel del cliente (0-2):"));

                    Cliente cliente = new Cliente(nombre, apellido, dni, "email", nivel, 0, 0);
                    ControllerCliente.agregarCliente(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente agregado con éxito.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada o datos inválidos.");
                }
            }
        });

        // Botón "Modificar Cliente"
        btnModificarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LinkedList<Cliente> clientes = ControllerCliente.mostrarClientes();
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay clientes para modificar.");
                        return;
                    }

                    String[] opciones = clientes.stream()
                            .map(Cliente::toString)
                            .toArray(String[]::new);
                    int seleccion = JOptionPane.showOptionDialog(null, "Seleccione un cliente para modificar:",
                            "Modificar Cliente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, opciones, opciones[0]);

                    if (seleccion != -1) {
                        Cliente clienteSeleccionado = clientes.get(seleccion);
                        String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", clienteSeleccionado.getNombre());
                        String nuevoApellido = JOptionPane.showInputDialog("Nuevo apellido:", clienteSeleccionado.getApellido());
                        String nuevoDni = JOptionPane.showInputDialog("Nuevo DNI:", clienteSeleccionado.getDni());
                        int nuevoNivel = Integer.parseInt(JOptionPane.showInputDialog("Nuevo nivel (0-2):", clienteSeleccionado.getNivel()));

                        clienteSeleccionado.setNombre(nuevoNombre);
                        clienteSeleccionado.setApellido(nuevoApellido);
                        clienteSeleccionado.setDni(nuevoDni);
                        clienteSeleccionado.setNivel(nuevoNivel);

                        ControllerCliente.actualizarCliente(clienteSeleccionado);
                        JOptionPane.showMessageDialog(null, "Cliente modificado con éxito.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada o datos inválidos.");
                }
            }
        });

        // Botón "Eliminar Cliente"
        btnEliminarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LinkedList<Cliente> clientes = ControllerCliente.mostrarClientes();
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay clientes para eliminar.");
                        return;
                    }

                    String[] opciones = clientes.stream()
                            .map(Cliente::toString)
                            .toArray(String[]::new);
                    int seleccion = JOptionPane.showOptionDialog(null, "Seleccione un cliente para eliminar:",
                            "Eliminar Cliente", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, opciones, opciones[0]);

                    if (seleccion != -1) {
                        Cliente clienteSeleccionado = clientes.get(seleccion);
                        ControllerCliente.eliminarCliente(clienteSeleccionado.getId());
                        JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada o datos inválidos.");
                }
            }
        });

        // Botón "Volver"
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaInicioAdmin pantallaInicioAdmin = new PantallaInicioAdmin();
                pantallaInicioAdmin.setVisible(true);
                dispose();
            }
        });
    }
}

