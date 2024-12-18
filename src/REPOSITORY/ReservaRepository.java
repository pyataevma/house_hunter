package REPOSITORY;

import java.util.LinkedList;

import BLL.Cliente;

public interface ReservaRepository {
	public interface ClienteRepository {
		void agregarCliente(Cliente cliente);
		LinkedList<Cliente> mostrarClient();
		Cliente buscarCliente(int id);
		void eliminarCliente(int id);
		void actualizarCliente(Cliente Cliente);
	}


}
