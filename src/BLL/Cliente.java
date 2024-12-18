package BLL;

public class Cliente extends Persona {
    
    private String email;
    private int telefono;
    private int nivel;
    private int id;

    public Cliente(String nombre, String apellido, String dni, String email, int telefono, int nivel, int id ) {
        super(nombre, apellido, dni);
        this.email = email;
        this.telefono = telefono;
        this.id = id;
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        //return "Cliente [nivel=" + nivel + ", " + super.toString() + "]";
    	return toStringShort();
    }
    
    public String toStringShort() {
    	return super.toStringShort();
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}

}
