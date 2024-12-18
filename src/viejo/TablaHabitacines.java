package viejo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Habitacion;
import BLL.Persona;
import BLL.TipoHabitacion;
import DLL.ControllerHabitacion;
import DLL.ControllerPersona;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;


public class TablaHabitacines extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaHabitacines frame = new TablaHabitacines();
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
	public TablaHabitacines() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 100, 791, 582);
		setTitle("Administracion de habitaciones");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Habitaciones");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ocultar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setVisible(false);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CardLayout cardLayout = new CardLayout(0, 0);
		
		JPanel panel_data = new JPanel();
		panel_data.setBounds(20, 22, 722, 307);
		contentPane.add(panel_data);
		
		String[] columnNames = { "ID", "Numero", "Piso", "Precio", "Tipo" };
		model = new DefaultTableModel(columnNames, 0);
        model.addTableModelListener(e -> {
            int row = e.getFirstRow();  // Get the row of the change
            int column = e.getColumn();  // Get the column of the change
            if (e.getType() == TableModelEvent.UPDATE) { // Check if it's an update
                Object newValue = model.getValueAt(row, column);
                System.out.println("Cell updated at row " + row + ", column " + column +
                                   " with value: " + newValue);
                Habitacion habitacion = getHabitacion(row);
                ControllerHabitacion.actualizarHabitacion(habitacion);
                actualizarTabla();
            }
        });
		table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ListSelectionModel selectionModel = table.getSelectionModel();

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(34, 37, 691, 291);
		panel_data.add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		

		JPanel panel_container = new JPanel();
		panel_container.setBounds(34, 339, 691, 171);
		contentPane.add(panel_container);
		panel_container.setLayout(cardLayout);
		
		JPanel panel_basico = new JPanel();
		panel_basico.setLayout(null);
		panel_container.add(panel_basico, "name_128098835485300");
		
		JPanel panel_agregar = new JPanel();
		panel_agregar.setLayout(null);
		panel_container.add(panel_agregar, "name_128098827551200");
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel_container, "name_128098835485300");
			}
		});		
		btnNewButton.setBounds(546, 137, 135, 23);
		panel_agregar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.setBounds(383, 137, 152, 23);
		panel_agregar.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(110, 11, 86, 20);
		panel_agregar.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Numero");
		lblNewLabel.setBounds(35, 14, 46, 14);
		panel_agregar.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 42, 86, 20);
		panel_agregar.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Piso");
		lblNewLabel_1.setBounds(35, 45, 46, 14);
		panel_agregar.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(421, 11, 86, 20);
		panel_agregar.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(365, 14, 46, 14);
		panel_agregar.add(lblNewLabel_2);
		
		JComboBox<String> comboBox = new JComboBox<String>(TipoHabitacion.getNombres());
		comboBox.setBounds(421, 41, 30, 22);
		panel_agregar.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo");
		lblNewLabel_3.setBounds(365, 45, 46, 14);
		panel_agregar.add(lblNewLabel_3);
		
				
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel_container, "name_128098835485300");
			}
		});
		
		JButton btnNewButton_eliminar = new JButton("Eliminar habitacion");
		btnNewButton_eliminar.setBounds(191, 16, 135, 23);
		panel_basico.add(btnNewButton_eliminar);
		
		JButton btnNewButton_agregar = new JButton("Agregar habitacion");
		btnNewButton_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_agregar.setBounds(10, 16, 152, 23);
		panel_basico.add(btnNewButton_agregar);
		
		actualizarTabla();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
