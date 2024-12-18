package BLL;

public interface Pagable {

	public void procesarPago(double cantidad);
    
	public double calcularImporte(double... importes);
}
