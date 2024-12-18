package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BLL.Empleado;
import BLL.Hotel;
import DLL.ControllerEmpleado;

public class PantallaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpCorreo;
    private JPasswordField pswContrasena;
    private JLabel lblError;
    private Hotel hotel;

    public PantallaLogin(Hotel hotel) {
        this.hotel = hotel;

        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Inicio de Sesión");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(140, 20, 200, 30);
        contentPane.add(lblTitulo);

        // Correo electrónico
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 70, 100, 20);
        contentPane.add(lblCorreo);

        inpCorreo = new JTextField();
        inpCorreo.setBounds(140, 70, 200, 20);
        contentPane.add(inpCorreo);
        inpCorreo.setColumns(10);

        // Contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 110, 100, 20);
        contentPane.add(lblContrasena);

        pswContrasena = new JPasswordField();
        pswContrasena.setBounds(140, 110, 200, 20);
        contentPane.add(pswContrasena);

        // Mensaje de error
        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setBounds(140, 140, 200, 20);
        contentPane.add(lblError);

        // Botón de login
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(140, 170, 120, 30);
        contentPane.add(btnLogin);

        // Acción del botón de login
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = inpCorreo.getText().trim();
                String contrasena = new String(pswContrasena.getPassword()).trim();

                if (correo.isEmpty() || contrasena.isEmpty()) {
                    lblError.setText("Ingrese ambos campos.");
                } else {
                    // Intento de inicio de sesión del usuario
                    Empleado empleado = Empleado.Login(correo, contrasena);

                    if (empleado != null) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso: " + empleado.getNombre());
                        dispose(); // Cierra la ventana de login
                        // Redirige a la pantalla principal de administración
                        PantallaInicioAdmin pantallaAdmin = new PantallaInicioAdmin();
                        pantallaAdmin.setVisible(true);
                    } else {
                        lblError.setText("Correo o contraseña incorrectos.");
                    }
                }
            }
        });

        // Botón de registrarse
        JButton btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBounds(270, 170, 120, 30);
        contentPane.add(btnRegistrar);

        // Acción del botón de registrarse
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PantallaRegistro(hotel).setVisible(true); // Abre la pantalla de registro
                dispose(); // Cierra la pantalla de login
            }
        });
    }

    // Método principal para probar la GUI
    public static void main(String[] args) {
        Hotel hotel = new Hotel(null); // Crea una instancia de Hotel
        PantallaLogin frame = new PantallaLogin(hotel);
        frame.setVisible(true);
    }
}
