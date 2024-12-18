package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import BLL.Persona;

public class ControllerPersona {
	
	private static String tableName="persona";
    private static Connection con = Conexion.getInstance().getConection();

    // Método para agregar un persona
    public static void agregarPersona(Persona persona) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO `persona`(`nombre`, `apellido`, `dni`) VALUES (?, ?, ?)"
            );
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setNString(3, persona.getDni());

            int filas = statement. executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Persona agregado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar persona: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para mostrar personas
    public static LinkedList<Persona> mostrarPersonas() {
        LinkedList<Persona> personas = new LinkedList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `" +tableName + "`");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Persona persona = new Persona(
                	resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("dni")
                );
                personas.add(persona);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar personas: " + e.getMessage());
            e.printStackTrace();
        }
        return personas;
    }

    // Método para buscar un persona por ID
    public static Persona buscarPersona(int id) {
        Persona persona = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + tableName + "` WHERE id_persona = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                persona = new Persona(
                    	resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("dni")
                 );
            }
        } catch (Exception e) {
            System.out.println("Error al buscar persona: " + e.getMessage());
            e.printStackTrace();
        }
        return persona;
    }

    public static void eliminarPersona(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM `persona` WHERE id_persona = ?");
            statement.setInt(1, id);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Persona eliminado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para actualizar los datos de un persona
    public static void actualizarPersona(Persona persona) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE `persona` SET `nombre`=?, `apellido`=?, `dni`=? WHERE id_persona = ?"
            );
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setString(3, persona.getDni());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Persona actualizado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
