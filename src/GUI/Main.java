package GUI;

import javax.swing.JOptionPane;

import BLL.Cliente;
import BLL.Empleado;
import BLL.Hotel;
import DLL.ControllerEmpleado;
import viejo.GestionCliente;
import viejo.GestionHabitacion;
import viejo.GestionReserva;

public class Main {

    public static void main(String[] args) {
        Hotel hotel1 = new Hotel(Hotel.Localidad.RECOLETA);
        
        // Pantalla de inicio para registrar o iniciar sesión
        String[] options = {"Registrar Empleado", "Iniciar sesión"};
        int choice = JOptionPane.showOptionDialog(null, 
                "¿Qué deseas hacer?", 
                "Menú Principal", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, 
                options[0]);

        if (choice == 0) {
            // Registro de empleado
            String mailr = JOptionPane.showInputDialog("Ingrese mail para registro");
            String contr = JOptionPane.showInputDialog("Ingrese contraseña para registro");
            String nombre = JOptionPane.showInputDialog("Ingrese nombre");
            String apellido = JOptionPane.showInputDialog("Ingrese apellido");
            String dni = JOptionPane.showInputDialog("Ingrese DNI");
            String rol = JOptionPane.showInputDialog("Ingrese rol (ADMIN, GERENTE, LIMPIEZA)");

            Empleado empleado2 = new Empleado(nombre, apellido, dni, mailr, contr, rol);
            ControllerEmpleado.agregarEmpleado(empleado2);

        } else if (choice == 1) {
            // Opción de login
            String mail = JOptionPane.showInputDialog("Ingrese mail");
            String cont = JOptionPane.showInputDialog("Ingrese contraseña");
            hotel1.setEmpleados(ControllerEmpleado.mostrarEmpleados());
            Empleado encontrado = Empleado.Login(mail, cont);

            if (encontrado != null) {
                JOptionPane.showMessageDialog(null, "Empleado encontrado: " + encontrado.getUser());
                if (encontrado.getRol().equals("ADMIN")) {
                    mostrarMenuAdmin(hotel1); 
                } else {
                    JOptionPane.showMessageDialog(null, "Acceso no permitido para este usuario");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Empleado no encontrado");
            }
        }
    }

    // Método para mostrar el menú administrador con opciones de gestión
    public static void mostrarMenuAdmin(Hotel hotel) {
        String[] menuOptions = {"Gestionar clientes", "Gestionar habitaciones", "Gestionar limpieza", "Gestionar reserva", "JFrame", "Salir"};
        boolean continueMenu = true;

        while (continueMenu) {
            int selectedOption = JOptionPane.showOptionDialog(null,
                    "Selecciona una opción:",
                    "Menú Administrador",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    menuOptions,
                    menuOptions[0]);

            switch (selectedOption) {
                case 0:
                    GestionCliente.mostrarMenu();
                    break;
                case 1:
                    GestionHabitacion.mostrarMenu();
                    break;
                case 2:
                	break;
                case 3:
                	GestionReserva.mostrarMenu();
                	break;
                case 4:
                    PantallaRegistro frame = new PantallaRegistro(hotel);
                    frame.setVisible(true);
                	break;
                case 5:
                    continueMenu = false;
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    break;
                default:
                    continueMenu = false;
                    break;
            }
        }
    }
}