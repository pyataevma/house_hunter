package GUI;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BLL.Reserva;
import BLL.TipoHabitacion;
import BLL.Cliente;
import BLL.Habitacion;
import DLL.ControllerReserva;
import DLL.ControllerCliente;
import DLL.ControllerHabitacion;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;

public class PantallaReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private JTable table;
	private JLabel lblStatus;
	private JTable table_1;
	private Cliente cliente;
	private Habitacion habitacion;
	private Date selectedDate1;
	private Date selectedDate2;
	private JDateChooser dateChooser1;
	private JDateChooser dateChooser2;
	private JTable table_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaReserva frame = new PantallaReserva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaReserva() {
		
		// ------------------------------------- Panel de reservas -------------------------------------
		
		setTitle("Administración de reservaes");
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

		String[] columnNames = {"ID", "Nombre", "Apellido", "Numero", "Tipo", "Precio", "Ingreso", "Salida"};
		model = new DefaultTableModel(columnNames, 0);
        model.addTableModelListener(e -> {
            int row = e.getFirstRow();  
            int column = e.getColumn(); 
            if (e.getType() == TableModelEvent.UPDATE) { 
                Object newValue = model.getValueAt(row, column);
                //System.out.println("Cell updated at row " + row + ", column " + column +  " with value: " + newValue);
                Reserva reserva = getReserva(row);
                ControllerReserva.actualizarReserva(reserva);
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
                        Reserva selectedReserva = getReserva(selectedRow);
                        lblStatus.setText("Reserva seleccionada " + selectedReserva.toStringShort());
                    } else {
                    	lblStatus.setText("Ninguna reserva está seleccionada");
                    }
                }
            }

        };

		table = new JTable(model); // ----------------- tebla de reservas -------------------------
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
		
		lblStatus = new JLabel("Ninguna reserva está seleccionada");
		lblStatus.setBounds(38, 411, 700, 14);
		panel.add(lblStatus);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(38, 448, 700, 92);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Agregar reserva");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla1();
				cliente=null;
				cardLayout.show(contentPane, "name_346579908562500");
			}
		});
		btnNewButton.setBounds(62, 58, 150, 23);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar reserva");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "No hay reservas seleccionadas!");
				} else {
					Reserva reserva = getReserva(row);
					int confirmacion = JOptionPane.showConfirmDialog(null, "Queres eliminar el reserva " + reserva.toStringShort()+" ?");
					if (confirmacion == 0) {
						ControllerReserva.eliminarReserva(reserva.getId());
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
		
		JLabel lblNewLabel_5 = new JLabel("La lista de reservas. ¡Solo se puede cambiar las fechas!");
		lblNewLabel_5.setBounds(0, 0, 774, 14);
		panel.add(lblNewLabel_5);		
		
		// ------------------------------------- Panel de fechas -------------------------------------
		
		JPanel panel_select_fecha = new JPanel();
		contentPane.add(panel_select_fecha, "name_227941756905600");
		panel_select_fecha.setLayout(null);
	
		JPanel panel_inp_reserva = new JPanel();
		panel_inp_reserva.setBounds(41, 52, 706, 248);
		panel_select_fecha.add(panel_inp_reserva);
		panel_inp_reserva.setLayout(null);
		
		dateChooser1 = new JDateChooser();
        dateChooser1.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                selectedDate1 = dateChooser1.getDate();
                if (selectedDate1 != null) {
                    Calendar calendar = Calendar.getInstance();
                    //System.out.println(selectedDate1);
                    calendar.setTime(selectedDate1);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
            		dateChooser2.setMinSelectableDate(calendar.getTime());
            		dateChooser2.setEnabled(true);	
                }
            }
        });
        dateChooser1.setBounds(176, 11, 88, 20);
        dateChooser1.setDateFormatString("dd/MM/yyyy");

        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, 1); 
        dateChooser1.setMinSelectableDate(today.getTime());
		panel_inp_reserva.add(dateChooser1);
		
		dateChooser2 = new JDateChooser();
		
//		dateChooser2.getCalendarButton().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//                selectedDate2 = dateChooser2.getDate();
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(selectedDate1);
//                calendar.add(Calendar.DAY_OF_MONTH, 1);
//        		dateChooser1.setMaxSelectableDate(calendar.getTime());
//			}
//		});
		
        dateChooser2.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                selectedDate2 = dateChooser2.getDate();
                if (selectedDate2 != null) {
                    Calendar calendar = Calendar.getInstance();
                    //System.out.println(selectedDate2);
                    calendar.setTime(selectedDate2);
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
            		dateChooser1.setMaxSelectableDate(calendar.getTime());	
                }
            }
        });
		
		dateChooser2.setDateFormatString("dd/MM/yyyy");
		dateChooser2.setBounds(490, 11, 88, 20);
		dateChooser2.setEnabled(false);
		panel_inp_reserva.add(dateChooser2);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de entrada");
		lblNewLabel_2.setBounds(34, 17, 118, 14);
		panel_inp_reserva.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha de salida");
		lblNewLabel_3.setBounds(362, 17, 118, 14);
		panel_inp_reserva.add(lblNewLabel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(41, 404, 706, 136);
		panel_select_fecha.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(41, 311, 706, 14);
		panel_select_fecha.add(lblError);
		
		JButton btnGuardar = new JButton("Guardar fechas");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if (selectedDate1 == null || selectedDate2 == null) {
                    lblError.setText("Hay que elegir ambos fechas.");
                } else {
                    try {
                    	cardLayout.show(contentPane, "name_50958916584200");
                    	habitacion=null;
                    	mostrarDesponibles();
                    } catch (Exception ex) {
                        lblError.setText("Error al agregar reserva: " + ex.getMessage());
                    }
                }
			}
		});
		btnGuardar.setBounds(135, 102, 150, 23);
		panel_4.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
				cardLayout.show(contentPane, "name_227913444135700");
			}			
		});
		btnCancelar.setBounds(420, 102, 150, 23);
		panel_4.add(btnCancelar);
		
		JLabel lblNewLabel_4 = new JLabel("Al principio elija fecha de entrada, después fecha de salida");
		lblNewLabel_4.setBounds(47, 26, 700, 14);
		panel_select_fecha.add(lblNewLabel_4);
		
		//-----------------------Panel del cliente -----------------------------------
		
		JPanel panel_select_cliente = new JPanel();
		contentPane.add(panel_select_cliente, "name_346579908562500");
		panel_select_cliente.setLayout(null);
		
		JLabel lblClienteElegido = new JLabel("Cliente seleccionado:");
		lblClienteElegido.setBounds(20, 378, 744, 14);
		panel_select_cliente.add(lblClienteElegido);		
		
        ListSelectionListener selectionListener1 = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table_1.getSelectedRow();
                    int selectedColumn = table_1.getSelectedColumn();
                    if (selectedRow != -1 && selectedColumn != -1) {
                        cliente = getCliente(selectedRow);
                        lblClienteElegido.setText("Cliente seleccionado: " + cliente.toStringShort());
                    } else {
                    	lblClienteElegido.setText("Ninguno cliente está seleccionado");
                    }
                }
            }
        };
        
		String[] clienteTablaColumns = {"ID", "Nombre", "Apellido", "DNI", "Email", "Telefono", "Nivel" }; 
		model1 = new DefaultTableModel(clienteTablaColumns, 0);
		
		table_1 = new JTable(model1); // Tabla de clientes ---------------------------
 		table_1.setVisible(true);
		table_1.setCellSelectionEnabled(true);
		table_1.getColumnModel().getColumn(0).setMinWidth(0);
		table_1.getColumnModel().getColumn(0).setMaxWidth(0);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(0);
		table_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ListSelectionModel selectionModel1 = table_1.getSelectionModel();
		selectionModel1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.getSelectionModel().addListSelectionListener(selectionListener1);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(10, 63, 754, 304);
		panel_select_cliente.add(scrollPane_1);
		
		JLabel lblNewLabel_6 = new JLabel("Por favor, elija uno de los clientes");
		lblNewLabel_6.setBounds(10, 11, 754, 41);
		panel_select_cliente.add(lblNewLabel_6);		
		
        table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JButton btnNewButton_3 = new JButton("Guardar cliente");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (cliente!=null) {
        			dateChooser1.setMinSelectableDate(today.getTime());
            		dateChooser2.setEnabled(false);
            		dateChooser1.setDate(null);
            		dateChooser2.setDate(null);
            		selectedDate1=null;
            		selectedDate2=null;
    				cardLayout.show(contentPane, "name_227941756905600");
        		} else {
        			JOptionPane.showMessageDialog(null, "Hay que elegir un cliente");
        		}

        	}
        });
        btnNewButton_3.setBounds(191, 517, 150, 23);
        panel_select_cliente.add(btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("Cancelar");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		actualizarTabla();
        		cardLayout.show(contentPane, "name_227913444135700");
        	}
        });
        btnNewButton_4.setBounds(427, 517, 150, 23);
        panel_select_cliente.add(btnNewButton_4);
        
        //----------------------------Panel de habitaciones ---------------------------------- 
        
        JPanel panel_select_habitacion = new JPanel();
        panel_select_habitacion.setLayout(null);
        contentPane.add(panel_select_habitacion, "name_50958916584200");
        
        JPanel panel_inp_reserva_1 = new JPanel();
        panel_inp_reserva_1.setBounds(41, 51, 706, 248);
        panel_select_habitacion.add(panel_inp_reserva_1);
        panel_inp_reserva_1.setLayout(null);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(0, 0, 706, 248);
        panel_inp_reserva_1.add(scrollPane_2);
        
        JLabel lbHabitacionElegida = new JLabel("Habitación elegida: ");
        lbHabitacionElegida.setForeground(new Color(0, 0, 0));
        lbHabitacionElegida.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbHabitacionElegida.setBounds(41, 310, 706, 14);
        panel_select_habitacion.add(lbHabitacionElegida);
        
        ListSelectionListener selectionListener2 = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table_2.getSelectedRow();
                    int selectedColumn = table_2.getSelectedColumn();
                    if (selectedRow != -1 && selectedColumn != -1) {
                        habitacion = getHabitacion(selectedRow);
                        lbHabitacionElegida.setText("Habitacion seleccionado: " + habitacion.toString());
                    } else {
                    	lbHabitacionElegida.setText("Ninguna habitacion está seleccionada");
                    }
                }
            }
        };
        
        String[] habitacionColumns =  { "ID", "Numero", "Piso", "Precio", "Tipo" }; 
		model2 = new DefaultTableModel(habitacionColumns, 0);
		
		table_2 = new JTable(model2); //--------------Tabla de las habitaciones ----------------
		table_2.setVisible(true);
		table_2.setCellSelectionEnabled(true);
		table_2.getColumnModel().getColumn(0).setMinWidth(0);
		table_2.getColumnModel().getColumn(0).setMaxWidth(0);
		table_2.getColumnModel().getColumn(0).setPreferredWidth(0);
		table_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ListSelectionModel selectionModel2 = table_2.getSelectionModel();
		selectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.getSelectionModel().addListSelectionListener(selectionListener2);

        scrollPane_2.setViewportView(table_2);
        
        JPanel panel_4_1 = new JPanel();
        panel_4_1.setLayout(null);
        panel_4_1.setBounds(41, 404, 706, 136);
        panel_select_habitacion.add(panel_4_1);
        
        JButton btnGuardar_1 = new JButton("Guardar reserva");
        btnGuardar_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (habitacion!=null) {
	        		LocalDate fIngreso = selectedDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        	 	LocalDate fSalida = selectedDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        	 	Reserva reserva = new Reserva(habitacion.getId(), fIngreso, fSalida, cliente.getId(), 0);
	        	 	ControllerReserva.agregarReserva(reserva);  
	        	 	actualizarTabla();
	        	 	cardLayout.show(contentPane, "name_227913444135700");
        		} else {
        			JOptionPane.showMessageDialog(null, "Hay que elegir una habitacion o pulsar Cancelar");
        		}
        	}        	
        });
        btnGuardar_1.setBounds(135, 102, 150, 23);
        panel_4_1.add(btnGuardar_1);
        
        JButton btnCancelar_1 = new JButton("Cancelar");
        btnCancelar_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		actualizarTabla();
        		cardLayout.show(contentPane, "name_227913444135700");
        	}
        });
        btnCancelar_1.setBounds(420, 102, 150, 23);
        panel_4_1.add(btnCancelar_1);
        
        
        JLabel lblNewLabel_4_1 = new JLabel("Lista de habitaciones desponibles");
        lblNewLabel_4_1.setBounds(47, 26, 700, 14);
        panel_select_habitacion.add(lblNewLabel_4_1);

		actualizarTabla();
	}
	
	private void actualizarTabla() {
		model.setRowCount(0);
		List<Reserva> reservas = ControllerReserva.mostrarReservas();
		for (Reserva reserva : reservas) {
			Habitacion habitacion = ControllerHabitacion.buscarHabitacion(reserva.getIdHabitacion());
			Cliente cliente1 = ControllerCliente.buscarCliente(reserva.getIdCliente());
			//{"ID", "Nombre", "Apellido", "Numero", "Tipo", "Precio", "Ingreso", "Salida"};
			model.addRow(new Object[] {reserva.getId(), cliente1.getNombre(), cliente1.getApellido(),
										habitacion.getNro(), habitacion.getTipo().getNombre(), habitacion.getPrecio(), 
										reserva.getFingreso().toString(), reserva.getFsalida().toString()});
		}
	}
	
	private void actualizarTabla1() {
		model1.setRowCount(0);
		List<Cliente> clientes = ControllerCliente.mostrarClientes();
		for (Cliente cliente : clientes) {
			model1.addRow(new Object[] {cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getDni(),
					cliente.getEmail(), cliente.getTelefono(), cliente.getNivel()});
		}
	}
	
	private void mostrarDesponibles() {
		LocalDate fIngreso = selectedDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	 	LocalDate fSalida = selectedDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		model2.setRowCount(0);
		List<Reserva> reservas = ControllerReserva.mostrarReservas();
		List<Habitacion> habitaciones = ControllerHabitacion.mostrarHabitaciones();
		for (Reserva reserva : reservas) {
			//int id = reserva.getIdHabitacion();
			for (Habitacion habitacion : habitaciones) {
				if (habitacion.getId() == reserva.getIdHabitacion()) {
					habitacion.agregarReserva(reserva);
				}
			}
		}
		for (Habitacion habitacion : habitaciones) {
			if (habitacion.estaDisponible(fIngreso,fSalida)) {
				model2.addRow(new Object[] {habitacion.getId(), habitacion.getNro(), habitacion.getPiso(), habitacion.getPrecio(), habitacion.getTipo()});
			}
		}
	}
	
	private Reserva getReserva(int row) {
		 	int id = Integer.parseInt(model.getValueAt(row, 0).toString());
		 	Reserva reserva = ControllerReserva.buscarReserva(id);
		    LocalDate fingreso = LocalDate.parse(model.getValueAt(row, 6).toString());
		    LocalDate fsalida = LocalDate.parse(model.getValueAt(row, 7).toString());
		    reserva.setFingreso(fingreso);
		    reserva.setFsalida(fsalida);
			return reserva;
	}
	
	private Cliente getCliente(int row) {
	 	int id = Integer.parseInt(model1.getValueAt(row, 0).toString());
	 	String nombre = model1.getValueAt(row, 1).toString();
	 	String apellido = model1.getValueAt(row, 2).toString();
	 	String dni = model1.getValueAt(row, 3).toString();
	 	String email = model1.getValueAt(row, 4).toString();
	 	int telefono = Integer.parseInt(model1.getValueAt(row, 5).toString());
	 	int nivel = Integer.parseInt(model1.getValueAt(row, 6).toString());
		Cliente cliente = new Cliente (nombre, apellido, dni, email, telefono, nivel, id);
		return cliente;
	}
	
	private Habitacion getHabitacion(int row) {
	 	int id = Integer.parseInt(model2.getValueAt(row, 0).toString());
	 	int nro = Integer.parseInt(model2.getValueAt(row, 1).toString());
	 	int piso = Integer.parseInt(model2.getValueAt(row, 2).toString());
	 	double precio = Double.parseDouble(model2.getValueAt(row, 3).toString());
	 	TipoHabitacion tipo = new TipoHabitacion(model2.getValueAt(row, 4).toString());
	 	Habitacion habitacion = new Habitacion(nro, piso, precio, tipo, id);
		return habitacion;
	}
}
