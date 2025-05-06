package org.ed06.model;

import java.time.LocalDate;
import java.util.*;

public class GestorReservas {
    private final Map<Integer, List<Reserva>> reservasPorHabitacion = new HashMap<>();

    public void inicializarReservas(List<Habitacion> habitaciones) {
        for (Habitacion habitacion : habitaciones) {
            reservasPorHabitacion.put(habitacion.getNumero(), new ArrayList<>());
        }
    }

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

    private int generarIdReserva() {
        return reservasPorHabitacion.values().stream()
                .mapToInt(List::size).sum() + 1;
    }

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