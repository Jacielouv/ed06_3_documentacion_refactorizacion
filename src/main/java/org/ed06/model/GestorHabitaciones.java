package org.ed06.model;

import java.util.*;

/**
 * La clase {@code GestorHabitaciones} gestiona el registro, la búsqueda
 * y la visualización de habitaciones en el hotel.
 * <p>
 * Almacena una lista de habitaciones y permite consultar disponibilidad por tipo.
 * </p>
 *
 * @author José Cielo Fernández Caballero
 */
public class GestorHabitaciones {

    private final List<Habitacion> habitaciones = new ArrayList<>();

    /**
     * Registra múltiples habitaciones en el sistema.
     * Cada habitación se crea con un número consecutivo basado en el tamaño actual de la lista.
     *
     * @param tipos        lista de tipos de habitaciones (por ejemplo, "DOBLE", "SUITE")
     * @param preciosBase  lista de precios base correspondientes a cada tipo
     * @throws IndexOutOfBoundsException si las listas no son del mismo tamaño
     */
    public void registrarHabitaciones(List<String> tipos, List<Double> preciosBase) {
        for (int i = 0; i < tipos.size(); i++) {
            Habitacion habitacion = new Habitacion(habitaciones.size() + 1, tipos.get(i), preciosBase.get(i));
            habitaciones.add(habitacion);
        }
    }

    /**
     * Muestra por consola todas las habitaciones disponibles, incluyendo
     * su número, tipo y precio base.
     */
    public void listarHabitacionesDisponibles() {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.isDisponible()) {
                System.out.println("Habitación #" + habitacion.getNumero() + " - Tipo: " + habitacion.getTipo() +
                        " - Precio base: " + habitacion.getPrecioBase());
            }
        }
    }

    /**
     * Busca una habitación disponible del tipo especificado.
     *
     * @param tipo el tipo de habitación deseado
     * @return una habitación disponible que coincida con el tipo, o {@code null} si no hay disponibles
     */
    public Habitacion buscarDisponiblePorTipo(String tipo) {
        for (Habitacion h : habitaciones) {
            if (h.getTipo().equalsIgnoreCase(tipo) && h.isDisponible()) {
                return h;
            }
        }
        return null;
    }

    /**
     * Obtiene una habitación por su número identificador.
     *
     * @param numero el número de la habitación
     * @return la habitación correspondiente, o {@code null} si no se encuentra
     */
    public Habitacion getHabitacion(int numero) {
        return habitaciones.stream()
                .filter(h -> h.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    /**
     * Devuelve la lista completa de habitaciones registradas.
     *
     * @return lista de todas las habitaciones
     */
    public List<Habitacion> getTodasHabitaciones() {
        return habitaciones;
    }
}
