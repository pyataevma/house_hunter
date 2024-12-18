package GUI;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import BLL.Cliente;
import DLL.ControllerCliente;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;

public class PantallaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JLabel lblStatus;
	private JTextField inpNombre;
	private JTextField inpApellido;
	private JTextField inpDni;
	private JTextField inpEmail;
	private JTextField inpTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaCliente frame = new PantallaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaCliente() {
		setTitle("Administración de clientees");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		CardLayout cardLayout = new CardLayout(0, 0);
		contentPane.setLayout(cardLayout);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "name_227913444135700");
		panel.setLayout(null);

		String[] columnNames = {"ID", "Nombre", "Apellido", "DNI", "Email", "Telefono","Nivel"};
		model = new DefaultTableModel(columnNames, 0);
        model.addTableModelListener(e -> {
            int row = e.getFirstRow();  
            int column = e.getColumn(); 
            if (e.getType() == TableModelEvent.UPDATE) { // Check if it's an update
                Object newValue = model.getValueAt(row, column);
                System.out.println("Cell updated at row " + row + ", column " + column +
                                   " with value: " + newValue);
                Cliente cliente = getCliente(row);
                ControllerCliente.actualizarCliente(cliente);
                actualizarTabla();
            }
        });
        ListSelectionListener selectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    int selectedColumn = table.getSelectedColumn();

                    if (selectedRow != -1 && selectedColumn != -1) {
                        Cliente selectedCliente = getCliente(selectedRow);
                        lblStatus.setText("Seleccionada " + selectedCliente.toString());
                    } else {
                    	lblStatus.setText("Ninguna habitación seleccionada");
                    }
                }
            }

        };

		table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(selectionListener); 
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 25, 774, 375);
		panel.add(scrollPane);
		
		lblStatus = new JLabel("Ninguno cliente seleccionado");
		lblStatus.setBounds(38, 411, 700, 14);
		panel.add(lblStatus);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(38, 448, 700, 92);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Agregar cliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "name_227941756905600");	
			}
		});
		btnNewButton.setBounds(62, 58, 150, 23);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar cliente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "No hay cliente seleccionado!");
				} else {
					Cliente cliente = getCliente(row);
					int confirmacion = JOptionPane.showConfirmDialog(null, "Queres eliminar el cliente " + cliente.toString()+" ?");
					if (confirmacion == 0) {
						ControllerCliente.eliminarCliente(cliente.getId());
						actualizarTabla();
					}
				}			
			}
		});
		btnNewButton_1.setBounds(274, 58, 150, 23);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Volver al menu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	PantallaInicioAdmin pantallaInicioAdmin = new PantallaInicioAdmin();
            	pantallaInicioAdmin.setVisible(true);
                dispose(); 
			}
		});
		btnNewButton_2.setBounds(486, 58, 150, 23);
		panel_2.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("La lista de clientees");
		lblNewLabel_5.setBounds(0, 0, 774, 14);
		panel.add(lblNewLabel_5);
		
	
		JPanel panel_agregar = new JPanel();
		contentPane.add(panel_agregar, "name_227941756905600");
		panel_agregar.setLayout(null);
		
		JPanel panel_inp_cliente = new JPanel();
		panel_inp_cliente.setBounds(41, 52, 706, 248);
		panel_agregar.add(panel_inp_cliente);
		panel_inp_cliente.setLayout(null);
		
		inpNombre = new JTextField();
		inpNombre.setBounds(158, 22, 200, 20);
		panel_inp_cliente.add(inpNombre);
		inpNombre.setColumns(10);
		
		inpApellido = new JTextField();
		inpApellido.setBounds(158, 53, 200, 20);
		panel_inp_cliente.add(inpApellido);
		inpApellido.setColumns(10);
		
		inpDni = new JTextField();
		inpDni.setBounds(158, 84, 200, 20);
		panel_inp_cliente.add(inpDni);
		inpDni.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(34, 25, 46, 14);
		panel_inp_cliente.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setBounds(34, 56, 46, 14);
		panel_inp_cliente.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DNI");
		lblNewLabel_2.setBounds(34, 87, 46, 14);
		panel_inp_cliente.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(34, 118, 46, 14);
		panel_inp_cliente.add(lblNewLabel_3);
		
		inpEmail = new JTextField();
		inpEmail.setBounds(158, 115, 200, 20);
		panel_inp_cliente.add(inpEmail);
		inpEmail.setColumns(10);
		
		inpTelefono = new JTextField();
		inpTelefono.setBounds(158, 146, 200, 20);
		panel_inp_cliente.add(inpTelefono);
		inpTelefono.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Telefono");
		lblNewLabel_6.setBounds(34, 149, 46, 14);
		panel_inp_cliente.add(lblNewLabel_6);
		
		JComboBox<String> comboNivel = new JComboBox<String>(new String[]{"0", "1", "2"});
		comboNivel.setBounds(158, 177, 87, 22);
		panel_inp_cliente.add(comboNivel);
		
		JLabel lblNewLabel_7 = new JLabel("Nivel");
		lblNewLabel_7.setBounds(34, 181, 46, 14);
		panel_inp_cliente.add(lblNewLabel_7);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(41, 404, 706, 136);
		panel_agregar.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(41, 257, 706, 14);
		panel_agregar.add(lblError);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nombre = inpNombre.getText().trim();
                String apellido = inpApellido.getText().trim();
                String dni = inpDni.getText().trim();
                String email = inpEmail.getText().trim();
                String stTelefono = inpTelefono.getText().trim();
                String stNivel = comboNivel.getSelectedItem().toString();
                if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || email.isEmpty() || stTelefono.isEmpty() || stNivel.isEmpty()) {
                    lblError.setText("Todos los campos son obligatorios.");
                } else {
                    try {
            		 	int telefono = Integer.parseInt(stTelefono);
            		 	int nivel = Integer.parseInt(stNivel);
            			Cliente cliente = new Cliente (nombre, apellido, dni, email, telefono, nivel, 0);
                        ControllerCliente.agregarCliente(cliente);
                        actualizarTabla();
                        cardLayout.show(contentPane, "name_227913444135700");
                    } catch (Exception ex) {
                        lblError.setText("Error al agregar cliente: " + ex.getMessage());
                    }
                }
			}
		});
		btnGuardar.setBounds(135, 102, 150, 23);
		panel_4.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "name_227913444135700");	
			}
			
		});
		btnCancelar.setBounds(420, 102, 150, 23);
		panel_4.add(btnCancelar);
		
		JLabel lblNewLabel_4 = new JLabel("Agregar nuevo cliente");
		lblNewLabel_4.setBounds(47, 26, 700, 14);
		panel_agregar.add(lblNewLabel_4);
		
		actualizarTabla();
	}
	
	private void actualizarTabla() {
		model.setRowCount(0);
		List<Cliente> clientes = ControllerCliente.mostrarClientes();
		for (Cliente cliente : clientes) {
			model.addRow(new Object[] {cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getDni(), cliente.getEmail(), cliente.getTelefono(), cliente.getNivel()});
		}
	}

	private Cliente getCliente(int row) {
		 	int id = Integer.parseInt(model.getValueAt(row, 0).toString());
		 	String nombre = model.getValueAt(row, 1).toString();
		 	String apellido = model.getValueAt(row, 2).toString();
		 	String dni = model.getValueAt(row, 3).toString();
		 	String email = model.getValueAt(row, 4).toString();
		 	int telefono = Integer.parseInt(model.getValueAt(row, 5).toString());
		 	int nivel = Integer.parseInt(model.getValueAt(row, 6).toString());
			Cliente cliente = new Cliente (nombre, apellido, dni, email, telefono, nivel, id);
			return cliente;
	}
}


