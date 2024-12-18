package BLL;
import java.util.LinkedList;

import DLL.ControllerEmpleado;

public class Empleado extends Persona {

	private String user;
	private String pass;
	//public enum Rol {ADMIN, GERENTE, LIMPIEZA};
	private String rol;

	public Empleado(String nombre, String apellido, String dni, String user, String pass, String rol) {
		super(nombre, apellido, dni);
		this.user = user;
		this.pass = pass;
		this.rol = rol;
	}
	
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public static Empleado Login( String mail, String cotrasena ) {
		LinkedList<Empleado> empleados = ControllerEmpleado.mostrarEmpleados();
		for (Empleado empleado : empleados) {
			if (empleado.getUser().equals(mail) && empleado.getPass().equals(cotrasena)) {
				return empleado;
			}
		}
		return null;
	}
	
	
	public static Empleado Register(String nombre, String apellido, String dni, String user, String pass, String rol, Hotel hotel) {
		//comparar que el usuario no exista actualmente
		Empleado empleado = new Empleado(nombre, apellido, dni, user, pass, rol);
		hotel.agregarEmpleado(empleado);
		return empleado;
	}

	
	

	
}
