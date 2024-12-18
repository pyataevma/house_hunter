package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaInicioAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaInicioAdmin frame = new PantallaInicioAdmin();
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
    public PantallaInicioAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 680, 521);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("BIENVENIDO AL INICIO");
        lblNewLabel.setBounds(236, 72, 191, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("¿Qué desea hacer?");
        lblNewLabel_1.setBounds(236, 113, 191, 14);
        contentPane.add(lblNewLabel_1);

        // Botón Administrar Clientes
        JButton btnNewButton = new JButton("ADMINISTRAR CLIENTES");
        btnNewButton.setBounds(236, 154, 191, 23);
        contentPane.add(btnNewButton);

        // Acción del botón Administrar Clientes
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirigir a PantallaGestionCliente
                PantallaCliente pantallaCliente = new PantallaCliente();
                pantallaCliente.setVisible(true);
                dispose(); // Cierra la pantalla de inicio admin al abrir la siguiente ventana
            }
        });

        JButton btnNewButton_1 = new JButton("ADMINISTRAR HABITACIONES");
        btnNewButton_1.setBounds(236, 204, 191, 23);
        contentPane.add(btnNewButton_1);

        // Acción del botón Administrar Habitaciones
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirigir a TablaHabitacines
            	PantallaHabitacion pantallaHabitacion = new PantallaHabitacion();
            	pantallaHabitacion.setVisible(true);
                dispose(); // Cierra la pantalla de inicio admin al abrir la siguiente ventana
            }
        });
 
        JButton btnNewButton_2 = new JButton("ADMINISTRAR RESERVAS");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PantallaReserva pantallaReserva = new PantallaReserva();
            	pantallaReserva.setVisible(true);
                dispose();
        	}
        });
        btnNewButton_2.setBounds(236, 254, 191, 23);
        contentPane.add(btnNewButton_2);
        

        JButton btnNewButton_3 = new JButton("SALIR");
        btnNewButton_3.setBounds(236, 304, 191, 23);
        contentPane.add(btnNewButton_3);

        // Acción del botón SALIR
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el programa
                System.exit(0);
            }
        });
    }
}
