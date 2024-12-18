package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BLL.Empleado;
import BLL.Hotel;
import DLL.ControllerEmpleado;

public class PantallaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpCorreo;
    private JPasswordField pswContrasena;
    private JTextField inpNombre;
    private JTextField inpApellido;
    private JTextField inpDNI;
    private JComboBox<String> comboRol;
    private JLabel lblError;
    private Hotel hotel;

    public PantallaRegistro(Hotel hotel) {
        this.hotel = hotel;

        setTitle("Registro de Empleado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Registro de Empleado");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(150, 20, 250, 30);
        contentPane.add(lblTitulo);

        // Correo
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 70, 100, 20);
        contentPane.add(lblCorreo);

        inpCorreo = new JTextField();
        inpCorreo.setBounds(140, 70, 300, 20);
        contentPane.add(inpCorreo);

        // Contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 110, 100, 20);
        contentPane.add(lblContrasena);

        pswContrasena = new JPasswordField();
        pswContrasena.setBounds(140, 110, 300, 20);
        contentPane.add(pswContrasena);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 150, 100, 20);
        contentPane.add(lblNombre);

        inpNombre = new JTextField();
        inpNombre.setBounds(140, 150, 300, 20);
        contentPane.add(inpNombre);

        // Apellido
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30, 190, 100, 20);
        contentPane.add(lblApellido);

        inpApellido = new JTextField();
        inpApellido.setBounds(140, 190, 300, 20);
        contentPane.add(inpApellido);

        // DNI
        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setBounds(30, 230, 100, 20);
        contentPane.add(lblDNI);

        inpDNI = new JTextField();
        inpDNI.setBounds(140, 230, 300, 20);
        contentPane.add(inpDNI);

        // Rol
        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(30, 270, 100, 20);
        contentPane.add(lblRol);

        comboRol = new JComboBox<>(new String[]{"ADMIN", "GERENTE", "LIMPIEZA"});
        comboRol.setBounds(140, 270, 300, 20);
        contentPane.add(comboRol);

        // Mensaje de error
        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setBounds(140, 300, 300, 20);
        contentPane.add(lblError);

        // Botón de registro
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(140, 330, 100, 30);
        contentPane.add(btnRegistrar);

        // Acción del botón de registro
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = inpCorreo.getText().trim();
                String contrasena = new String(pswContrasena.getPassword()).trim();
                String nombre = inpNombre.getText().trim();
                String apellido = inpApellido.getText().trim();
                String dni = inpDNI.getText().trim();
                String rol = comboRol.getSelectedItem().toString();

                if (correo.isEmpty() || contrasena.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                    lblError.setText("Todos los campos son obligatorios.");
                } else {
                    try {
                        Empleado nuevoEmpleado = new Empleado(nombre, apellido, dni, correo, contrasena, rol);
                        ControllerEmpleado.agregarEmpleado(nuevoEmpleado);
                        JOptionPane.showMessageDialog(null, "Empleado registrado exitosamente.");
                        new PantallaLogin(hotel).setVisible(true); // Redirigir al login
                        dispose(); // Cierra la ventana de registro
                    } catch (Exception ex) {
                        lblError.setText("Error al registrar empleado: " + ex.getMessage());
                    }
                }
            }
        });

        // Botón de volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(250, 330, 100, 30);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PantallaLogin(hotel).setVisible(true); // Abre la pantalla de login
                dispose(); // Cierra la pantalla de registro
            }
        });
    }

    // Método principal para probar la GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Hotel hotel = new Hotel(null); // Crear instancia de Hotel
            PantallaRegistro frame = new PantallaRegistro(hotel);
            frame.setVisible(true);
        });
    }
}
