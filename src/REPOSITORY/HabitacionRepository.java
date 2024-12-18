
package REPOSITORY;

import java.util.LinkedList;

import BLL.Habitacion;


public interface HabitacionRepository {
	void agregarHabitacion(Habitacion habitacion);
	LinkedList<Habitacion> mostrarHabitacions();
	Habitacion buscarHabitacion(int id);
	void eliminarHabitacion(int id);
	void actualizarHabitacion(Habitacion Habitacion);
}


