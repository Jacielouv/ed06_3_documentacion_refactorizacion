package org.ed06.model;

import java.time.LocalDate;

/**
 * La clase {@code Reserva} representa una reserva de habitación realizada por un cliente
 * para un intervalo de fechas determinado. Calcula el precio total de la reserva considerando
 * posibles descuentos.
 * <p>
 * Se aplican descuentos si el cliente es VIP o si la duración de la estancia supera cierto umbral.
 * </p>
 *
 * @author José Cielo Fernández Caballero
 */
public class Reserva {
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioTotal;

    // Descuentos aplicables
    private double DESCUENTO_VIP = 0.9;
    private double INTERVALO_APLICAR_DESCUENTO = 7;
    private double DESCUENTO_SUPERACION_INTERVALO = 0.95;

    /**
     * Crea una nueva reserva con los datos proporcionados y calcula el precio total.
     *
     * @param id          identificador único de la reserva
     * @param habitacion  habitación reservada
     * @param cliente     cliente que realiza la reserva
     * @param fechaInicio fecha de inicio de la reserva
     * @param fechaFin    fecha de fin de la reserva
     */
    public Reserva(int id, Habitacion habitacion, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = calcularPrecioFinal();
    }

    /**
     * @return el identificador único de la reserva
     */
    public int getId() {
        return id;
    }

    /**
     * @return la habitación asociada a la reserva
     */
    public Habitacion getHabitacion() {
        return habitacion;
    }

    /**
     * @return el cliente que ha realizado la reserva
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return la fecha de inicio de la reserva
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @return la fecha de fin de la reserva
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * @return el precio total de la reserva con descuentos aplicados
     */
    public double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Calcula el precio final de la reserva basado en:
     * <ul>
     *   <li>Precio base de la habitación por número de noches</li>
     *   <li>Descuento del 10% si el cliente es VIP</li>
     *   <li>Descuento adicional del 5% si la estancia supera 7 días</li>
     * </ul>
     *
     * @return el precio total de la reserva con descuentos aplicados
     */
    public double calcularPrecioFinal() {
        int n = fechaFin.getDayOfYear() - fechaInicio.getDayOfYear();
        double pf = habitacion.getPrecioBase() * n;

        if (cliente.esVip) {
            pf *= DESCUENTO_VIP;
        }

        if (n > INTERVALO_APLICAR_DESCUENTO) {
            pf *= DESCUENTO_SUPERACION_INTERVALO;
        }

        return pf;
    }

    /**
     * Muestra por consola los detalles de la reserva: ID, habitación, cliente, fechas y precio total.
     */
    public void mostrarReserva() {
        System.out.println("Reserva #" + id);
        System.out.println("Habitación #" + habitacion.getNumero() + " - Tipo: " + habitacion.getTipo() + " - Precio base: " + habitacion.getPrecioBase());
        System.out.println("Cliente: " + cliente.nombre);
        System.out.println("Fecha de inicio: " + fechaInicio.toString());
        System.out.println("Fecha de fin: " + fechaFin.toString());
        System.out.printf("Precio total: %.2f €\n", precioTotal);
    }
}
