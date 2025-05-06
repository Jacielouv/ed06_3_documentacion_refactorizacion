package org.ed06.model;

import java.util.*;

public class GestorClientes {
    private final Map<Integer, Cliente> clientes = new HashMap<>();

    public void registrarCliente(String nombre, String email, String dni, boolean esVip) {
        Cliente cliente = new Cliente(clientes.size() + 1, nombre, dni, email, esVip);
        clientes.put(cliente.id, cliente);
    }

    public Cliente obtenerCliente(int id) {
        return clientes.get(id);
    }

    public void listarClientes() {
        for (Cliente cliente : clientes.values()) {
            System.out.println("Cliente #" + cliente.id + " - Nombre: " + cliente.nombre +
                    " - DNI: " + cliente.dni + " - VIP: " + cliente.esVip);
        }
    }

    public Collection<Cliente> getTodos() {
        return clientes.values();
    }
}