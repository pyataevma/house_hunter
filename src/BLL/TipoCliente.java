package BLL;

public class TipoCliente {
	private String nombre;
    private String descripcion;

    // Constructor
    public TipoCliente(String nombre) {
        this.nombre = nombre;
        this.descripcion = obtenerDescripcion(nombre);
    }

    // Método para obtener una descripción del tipo de cliente según el nombre del tipo
    private String obtenerDescripcion(String nombre) {
        switch (nombre.toUpperCase()) {
            case "VIP":
                return "Cliente con beneficios especiales, acceso a promociones y descuentos exclusivos.";
            case "FAMILIAR":
                return "Cliente con acceso a habitaciones familiares y descuentos en reservas grupales.";
            case "PAREJA":
                return "Cliente que prefiere habitaciones de pareja y servicios personalizados.";
            default:
                return "Tipo de cliente estándar sin beneficios adicionales.";
        }
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.descripcion = obtenerDescripcion(nombre);  // Actualiza la descripción automáticamente
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoCliente{" +
               "nombre='" + nombre + '\'' +
               ", descripcion='" + descripcion + '\'' +
               '}';
    }
}
