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

import BLL.Habitacion;
import BLL.TipoHabitacion;
import DLL.ControllerHabitacion;

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

public class PantallaHabitacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JLabel lblStatus;
	private JTextField inpNumero;
	private JTextField inpPiso;
	private JTextField inpPrecio;

public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaHabitacion frame = new PantallaHabitacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaHabitacion() {
		setTitle("Administración de habitaciones");
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

		String[] columnNames = { "ID", "Numero", "Piso", "Precio", "Tipo" };
		model = new DefaultTableModel(columnNames, 0);
        model.addTableModelListener(e -> {
            int row = e.getFirstRow();  
            int column = e.getColumn(); 
            if (e.getType() == TableModelEvent.UPDATE) { // Check if it's an update
                Object newValue = model.getValueAt(row, column);
                System.out.println("Cell updated at row " + row + ", column " + column +
                                   " with value: " + newValue);
                Habitacion habitacion = getHabitacion(row);
                ControllerHabitacion.actualizarHabitacion(habitacion);
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
                        Habitacion selectedHabitacion = getHabitacion(selectedRow);
                        lblStatus.setText("Seleccionada " + selectedHabitacion.toStringShort());
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
		
		lblStatus = new JLabel("Ninguna habitación seleccionada");
		lblStatus.setBounds(38, 411, 700, 14);
		panel.add(lblStatus);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(38, 448, 700, 92);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Agregar habitacion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "name_227941756905600");	
			}
		});
		btnNewButton.setBounds(62, 58, 150, 23);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar habitacion");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "No hay habitación seleccionado!");
				} else {
					Habitacion habitacion = getHabitacion(row);
					int confirmacion = JOptionPane.showConfirmDialog(null, "Queres eliminar la habitacion " + habitacion.toStringShort()+" ?");
					if (confirmacion == 0) {
						ControllerHabitacion.eliminarHabitacion(habitacion.getId());
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
		
		JLabel lblNewLabel_5 = new JLabel("La lista de habitaciones");
		lblNewLabel_5.setBounds(0, 0, 774, 14);
		panel.add(lblNewLabel_5);
		
	
		JPanel panel_agregar = new JPanel();
		contentPane.add(panel_agregar, "name_227941756905600");
		panel_agregar.setLayout(null);
		
		JPanel panel_inp_habitacion = new JPanel();
		panel_inp_habitacion.setBounds(41, 52, 706, 194);
		panel_agregar.add(panel_inp_habitacion);
		panel_inp_habitacion.setLayout(null);
		
		inpNumero = new JTextField();
		inpNumero.setBounds(158, 22, 86, 20);
		panel_inp_habitacion.add(inpNumero);
		inpNumero.setColumns(10);
		
		inpPiso = new JTextField();
		inpPiso.setBounds(158, 64, 86, 20);
		panel_inp_habitacion.add(inpPiso);
		inpPiso.setColumns(10);
		
		inpPrecio = new JTextField();
		inpPrecio.setBounds(158, 106, 86, 20);
		panel_inp_habitacion.add(inpPrecio);
		inpPrecio.setColumns(10);
		
		JComboBox<String> selectorTipo = new JComboBox<String>(TipoHabitacion.getNombres());
		selectorTipo.setBounds(158, 148, 94, 22);
		panel_inp_habitacion.add(selectorTipo);
		
		JLabel lblNewLabel = new JLabel("Numero");
		lblNewLabel.setBounds(34, 25, 46, 14);
		panel_inp_habitacion.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Piso");
		lblNewLabel_1.setBounds(34, 67, 46, 14);
		panel_inp_habitacion.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(34, 109, 46, 14);
		panel_inp_habitacion.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo");
		lblNewLabel_3.setBounds(34, 152, 46, 14);
		panel_inp_habitacion.add(lblNewLabel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(41, 404, 706, 136);
		panel_agregar.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(41, 257, 706, 14);
		panel_agregar.add(lblError);
		
		JButton btnNewButton_3 = new JButton("Guardar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String stNumero = inpNumero.getText().trim();
                String stPiso = inpPiso.getText().trim();
                String stPrecio = inpPrecio.getText().trim();
                String stTipo = selectorTipo.getSelectedItem().toString();
                if (stNumero.isEmpty() || stPiso.isEmpty() || stPrecio.isEmpty() || stTipo.isEmpty()) {
                    lblError.setText("Todos los campos son obligatorios.");
                } else {
                    try {
            		 	int nro = Integer.parseInt(stNumero);
            		 	int piso = Integer.parseInt(stPiso);
            		 	double precio = Double.parseDouble(stPrecio);
            		 	TipoHabitacion tipo = new TipoHabitacion(stTipo);
            			Habitacion habitacion = new Habitacion (nro, piso, precio, tipo, 0);
                        ControllerHabitacion.agregarHabitacion(habitacion);
                        actualizarTabla();
                    	cardLayout.show(contentPane, "name_227913444135700");
                    } catch (Exception ex) {
                        lblError.setText("Error al registrar empleado: " + ex.getMessage());
                    }
                }
			}
		});
		btnNewButton_3.setBounds(135, 102, 150, 23);
		panel_4.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Cancelar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "name_227913444135700");	
			}
			
		});
		btnNewButton_4.setBounds(420, 102, 150, 23);
		panel_4.add(btnNewButton_4);
		
		JLabel lblNewLabel_4 = new JLabel("Agregar habitacion nueva");
		lblNewLabel_4.setBounds(47, 26, 700, 14);
		panel_agregar.add(lblNewLabel_4);
		
		actualizarTabla();
	}
	
	private void actualizarTabla() {
		model.setRowCount(0);
		List<Habitacion> habitacioness = ControllerHabitacion.mostrarHabitaciones();
		for (Habitacion habitacion : habitacioness) {
			model.addRow(new Object[] {habitacion.getId(), habitacion.getNro(), habitacion.getPiso(), habitacion.getPrecio(), habitacion.getTipo()});
		}
	}

	private Habitacion getHabitacion(int row) {
		 	int id = Integer.parseInt(model.getValueAt(row, 0).toString());
		 	int nro = Integer.parseInt(model.getValueAt(row, 1).toString());
		 	int piso = Integer.parseInt(model.getValueAt(row, 2).toString());
		 	double precio = Double.parseDouble(model.getValueAt(row, 3).toString());
		 	TipoHabitacion tipo = new TipoHabitacion(model.getValueAt(row, 4).toString());
			Habitacion habitacion = new Habitacion (nro, piso, precio, tipo, id);
			return habitacion;
	}
}
