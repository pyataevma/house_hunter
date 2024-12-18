package BLL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import DLL.Conexion;

public class Usuario {
	private String nombre;
	private String password;
	private String rol;
	private int id;
	private static Connection con = (Connection) Conexion.getInstance().getConection();

	public Usuario(String nombre, String password, String rol, int id) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
		this.id = id;
	}
	public Usuario(String nombre, String password, String rol) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", password=" + password + ",\n rol=" + rol + 
				", id=" + id + "]";
	}
	
	public static void crearUsuario(Usuario usuario) {
		try {
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("INSERT INTO `persona`(`nombre`, `password`, `rol`) VALUES (?,?,?)");
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getPassword());
			statement.setString(3, usuario.getRol());
			
			int fila = statement.executeUpdate();
			if (fila>0) {
				JOptionPane.showMessageDialog(null, "Se guardó con exito!");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error "+e);		
			}
	}
	public static LinkedList<Usuario> MostraUsuario() {
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		try {
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `persona`");
			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarios.add(new Usuario(
						resultados.getString("nombre"),
						resultados.getString("password"),
						resultados.getString("rol"),
						resultados.getInt("id")
						));
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e);		
			}
		return usuarios;
	}
	public static Usuario BuscarUsuario(int id) {
		Usuario usuario = null;
		try {
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `persona` WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuario = new Usuario(resultados.getString("nombre"),
						resultados.getString("password"),
						resultados.getString("rol"),
						resultados.getInt("id"));
			}
			
			return usuario;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e);		
			}
		return usuario;
	}
	public static boolean ActualizarUsuario(Usuario usuario) {
		try {
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("UPDATE `persona` SET `nombre`=?,`password`=?,`rol`=? WHERE id = ?");
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getPassword());
			statement.setString(3, usuario.getRol());
			statement.setInt(4, usuario.getId());
			
			int fila = statement.executeUpdate();
			if (fila>0) {
				JOptionPane.showMessageDialog(null, "Se actualizó!");
				return true;

			} 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e);		
			}
		return false;

	}
	public static boolean BorrarUsuario(int id) {
		try {
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("DELETE FROM `persona` WHERE id = ?");
			
			statement.setInt(1, id);
			
			int fila = statement.executeUpdate();
			
			if (fila>0) {
				JOptionPane.showMessageDialog(null, "Se Borró!");
				return true;

			} 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error");		
			}
		return false;

	}
}
