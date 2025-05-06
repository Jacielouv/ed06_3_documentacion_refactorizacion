package org.ed06.model;
import java.time.LocalDate;

public class Hotel {

    public final GestorClientes gestorClientes = new GestorClientes();
    public final GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
    public final GestorReservas gestorReservas = new GestorReservas();

    private final String nombre;
    private final String direccion;
    private final String telefono;

    public Hotel(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

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