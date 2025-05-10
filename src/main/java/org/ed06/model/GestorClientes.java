package org.ed06.model;

import java.util.*;

/**
 * La clase {@code GestorClientes} se encarga de gestionar las operaciones relacionadas
 * con los clientes de un hotel, como registrar, obtener y listar clientes.
 * <p>
 * Utiliza un {@link HashMap} para almacenar a los clientes, donde la clave es el ID del cliente.
 * </p>
 *
 * @author José Cielo Fernández Caballero
 */
public class GestorClientes {

    private final Map<Integer, Cliente> clientes = new HashMap<>();

    /**
     * Registra un nuevo cliente en el sistema. El ID se asigna automáticamente
     * como el siguiente número entero disponible.
     *
     * @param nombre nombre del cliente
     * @param email  correo electrónico del cliente
     * @param dni    documento nacional de identidad del cliente
     * @param esVip  indica si el cliente es VIP
     * @throws IllegalArgumentException si alguno de los datos proporcionados no es válido
     */
    public void registrarCliente(String nombre, String email, String dni, boolean esVip) {
        Cliente cliente = new Cliente(clientes.size() + 1, nombre, dni, email, esVip);
        clientes.put(cliente.id, cliente);
    }

    /**
     * Obtiene un cliente a partir de su ID.
     *
     * @param id ID del cliente
     * @return el cliente correspondiente, o {@code null} si no existe
     */
    public Cliente obtenerCliente(int id) {
        return clientes.get(id);
    }

    /**
     * Muestra por consola la lista de todos los clientes registrados, incluyendo
     * su ID, nombre, DNI y estado VIP.
     */
    public void listarClientes() {
        for (Cliente cliente : clientes.values()) {
            System.out.println("Cliente #" + cliente.id + " - Nombre: " + cliente.nombre +
                    " - DNI: " + cliente.dni + " - VIP: " + cliente.esVip);
        }
    }

    /**
     * Devuelve una colección con todos los clientes registrados.
     *
     * @return colección de todos los clientes
     */
    public Collection<Cliente> getTodos() {
        return clientes.values();
    }
}
