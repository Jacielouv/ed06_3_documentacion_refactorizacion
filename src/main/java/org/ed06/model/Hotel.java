package org.ed06.model;

import java.time.LocalDate;

/**
 * La clase {@code Hotel} representa un hotel que gestiona clientes, habitaciones y reservas
 * a través de sus respectivos gestores. Permite realizar reservas de habitaciones.
 * <p>
 * Cada hotel tiene un nombre, dirección y número de teléfono asociados.
 * </p>
 *
 * @author José Cielo Fernández Caballero
 */
public class Hotel {

    /** Gestor de clientes del hotel. */
    public final GestorClientes gestorClientes = new GestorClientes();

    /** Gestor de habitaciones del hotel. */
    public final GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();

    /** Gestor de reservas del hotel. */
    public final GestorReservas gestorReservas = new GestorReservas();

    private final String nombre;
    private final String direccion;
    private final String telefono;

    /**
     * Crea una nueva instancia del hotel con la información básica.
     *
     * @param nombre    nombre del hotel
     * @param direccion dirección física del hotel
     * @param telefono  número de contacto del hotel
     */
    public Hotel(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;

        gestorReservas.inicializarReservas(gestorHabitaciones.getTodasHabitaciones());

    }

    /**
     * Intenta reservar una habitación del tipo especificado para un cliente registrado.
     *
     * @param clienteId ID del cliente que realiza la reserva
     * @param tipo      tipo de habitación solicitada
     * @param entrada   fecha de entrada
     * @param salida    fecha de salida
     * @return el ID de la reserva si es exitosa, -1 si no hay habitaciones disponibles,
     *         o -3 si el cliente no existe
     */
    public int reservarHabitacion(int clienteId, String tipo, LocalDate entrada, LocalDate salida) {
        Cliente cliente = gestorClientes.obtenerCliente(clienteId);
        if (cliente == null) {
            System.out.println("No existe el cliente con id " + clienteId);
            return -3;
        }

        Habitacion habitacion = gestorHabitaciones.buscarDisponiblePorTipo(tipo);
        if (habitacion == null) {
            System.out.println("No hay habitaciones disponibles del tipo " + tipo);
            return -1;
        }

        return gestorReservas.reservarHabitacion(habitacion, cliente, entrada, salida);
    }
}
