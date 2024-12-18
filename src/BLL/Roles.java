package BLL;
public enum Roles {
    ADMIN("ADMIN"),
    GERENTE("GERENTE"),
    LIMPIEZA("LIMPIEZA");

    private String rol;

    // Constructor para el enum con el valor de cadena
    Roles(String rol) {
        this.rol = rol;
    }

    // Método getter para obtener el valor de cadena
    public String getRol() {
        return rol;
    }
}