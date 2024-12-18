package BLL;

public class TipoHabitacion {
	
	final private static String[] nombres = {"PAREJA", "FAMILIAR", "VIP"};
	int index;
	
	public TipoHabitacion(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public TipoHabitacion(String nombre) {
		this.index = buscarIndex(nombre);
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	public String getNombre() {
		return nombres[index];
	}

	public static int buscarIndex(String nombre) {
		for (int i=0; i < nombres.length; i++) {
			if (nombre.equals(nombres[i])) {
				return i;
			}
		}
		return -1;
	}

	public static String buscarNombre(int index) {
		return nombres[index];
	}
	
	public static String[] getNombres() {
		return nombres;
	}	

}
