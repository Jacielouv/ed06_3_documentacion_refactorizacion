package org.ed06.model;

import java.time.LocalDate;
import java.util.*;

/**
 * La clase {@code GestorReservas} gestiona las reservas de las habitaciones
 * en el hotel. Permite realizar nuevas reservas, contar las reservas previas
 * de un cliente para determinar si se vuelve VIP, y mostrar todas las reservas
 * realizadas.
 * <p>
 * Utiliza un {@link HashMap} para almacenar las reservas de las habitaciones
 * organizadas por el número de habitación.
 * </p>
 *
 * @author José Cielo Fernández Caballero
 */
public class GestorReservas {

    /** Mapa que almacena las reservas de cada habitación, usando el número de habitación como clave. */
    private final Map<Integer, List<Reserva>> reservasPorHabitacion = new HashMap<>();

    /**
     * Inicializa las reservas para las habitaciones del hotel.
     * Crea una lista vacía de reservas para cada habitación registrada.
     *
     * @param habitaciones lista de habitaciones en el hotel
     */
    public void inicializarReservas(List<Habitacion> habitaciones) {
        for (Habitacion habitacion : habitaciones) {
            reservasPorHabitacion.put(habitacion.getNumero(), new ArrayList<>());
        }
    }

    /**
     * Realiza una reserva para una habitación, asegurando que la fecha de entrada
     * es anterior a la de salida y que la habitación está disponible.
     * Si el cliente ha realizado más de tres reservas en el último año, se le convierte en VIP.
     *
     * @param habitacion la habitación que se desea reservar
     * @param cliente el cliente que realiza la reserva
     * @param entrada fecha de entrada a la habitación
     * @param salida fecha de salida de la habitación
     * @return el número de la habitación reservada si la reserva fue exitosa,
     *         o un código de error (-1: habitación no disponible, -2: fecha incorrecta)
     */
    public int reservarHabitacion(Habitacion habitacion, Cliente cliente, LocalDate entrada, LocalDate salida) {
        if (entrada.isAfter(salida)) {
            System.out.println("La fecha de entrada es posterior a la fecha de salida");
            return -2;
        }

        if (!habitacion.isDisponible()) {
            System.out.println("La habitación no está disponible");
            return -1;
        }

        // Verificar si el cliente debe pasar a VIP
        int numReservasUltimoAnio = contarReservasEnUltimoAnio(cliente);
        if (numReservasUltimoAnio > 3 && !cliente.isVip()) {
            cliente.setVip(true);
            System.out.println("El cliente " + cliente.getNombre() + " ha pasado a ser VIP");
        }

        Reserva reserva = new Reserva(
                generarIdReserva(), habitacion, cliente, entrada, salida
        );
        reservasPorHabitacion.get(habitacion.getNumero()).add(reserva);
        habitacion.reservar();
        System.out.println("Reserva realizada con éxito");

        return habitacion.getNumero();
    }

    /**
     * Cuenta cuántas reservas ha realizado un cliente en el último año.
     *
     * @param cliente el cliente cuya cantidad de reservas se va a contar
     * @return el número de reservas del cliente en el último año
     */
    private int contarReservasEnUltimoAnio(Cliente cliente) {
        int count = 0;
        for (List<Reserva> lista : reservasPorHabitacion.values()) {
            for (Reserva r : lista) {
                if (r.getCliente().equals(cliente) &&
                        r.getFechaInicio().isAfter(LocalDate.now().minusYears(1))) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Genera un nuevo ID único para una reserva basándose en el número total de reservas existentes.
     *
     * @return un ID único para una nueva reserva
     */
    private int generarIdReserva() {
        return reservasPorHabitacion.values().stream()
                .mapToInt(List::size).sum() + 1;
    }

    /**
     * Muestra por consola todas las reservas realizadas, incluyendo el número de la habitación,
     * el cliente, la fecha de entrada y la fecha de salida.
     */
    public void listarReservas() {
        reservasPorHabitacion.forEach((numero, reservas) -> {
            System.out.println("Habitación #" + numero);
            for (Reserva r : reservas) {
                System.out.println("Reserva #" + r.getId() + " - Cliente: " + r.getCliente().getNombre()
                        + " - Entrada: " + r.getFechaInicio()
                        + " - Salida: " + r.getFechaFin());
            }
        });
    }
}
