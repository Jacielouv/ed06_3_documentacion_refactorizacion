package org.ed06.model;

import java.util.*;

public class GestorHabitaciones {
    private final List<Habitacion> habitaciones = new ArrayList<>();

    public void registrarHabitaciones(List<String> tipos, List<Double> preciosBase) {
        for (int i = 0; i < tipos.size(); i++) {
            Habitacion habitacion = new Habitacion(habitaciones.size() + 1, tipos.get(i), preciosBase.get(i));
            habitaciones.add(habitacion);
        }
    }

    public void listarHabitacionesDisponibles() {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.isDisponible()) {
                System.out.println("Habitación #" + habitacion.getNumero() + " - Tipo: " + habitacion.getTipo() +
                        " - Precio base: " + habitacion.getPrecioBase());
            }
        }
    }

    public Habitacion buscarDisponiblePorTipo(String tipo) {
        for (Habitacion h : habitaciones) {
            if (h.getTipo().equalsIgnoreCase(tipo) && h.isDisponible()) {
                return h;
            }
        }
        return null;
    }

    public Habitacion getHabitacion(int numero) {
        return habitaciones.stream().filter(h -> h.getNumero() == numero).findFirst().orElse(null);
    }

    public List<Habitacion> getTodasHabitaciones() {
        return habitaciones;
    }
}