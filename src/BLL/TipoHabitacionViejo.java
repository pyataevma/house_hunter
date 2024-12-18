package BLL;

public enum TipoHabitacionViejo {
	PAREJA("Pareja") , 
	FAMILIAR("Familiar"), 
	VIP("VIP");
	
	private String nombre;

	TipoHabitacionViejo(String nombre) {
		this.nombre = nombre;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public static String[] devolverArray() {
		String[] opciones = new String[3];
		
		opciones[0] ="PAREJA";
		opciones[1] = "FAMILIAR";
		opciones[2] = "VIP";
		return opciones;
	}
};
