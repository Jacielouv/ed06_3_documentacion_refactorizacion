package org.ed06.model;

import java.time.LocalDate;
import java.util.Date;

public class Reserva {
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioTotal;

    // Descuentos aplicables
    private double DESCUENTO_VIP=0.9;
    private double INTERVALO_APLICAR_DESCUENTO=7; // Intervalo de fechas a superar para aplicar descuento
    private double DESCUENTO_SUPERACION_INTERVALO=0.95;

    public Reserva(int id, Habitacion habitacion, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = calcularPrecioFinal();
    }

    public int getId() {
        return id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    // Calcula el precio total de la reserva. Para calcular el precio total, se debe calcular el precio base de la habitación por el número de noches de la reserva. En el caso de que el cliente sea vip, se aplicará un descuento del 10%. Además, si el intervalo de fechas es mayor a 7 días, se aplicará un descuento adicional del 5%.
    // Devuelve precio total de la reserva
    public double calcularPrecioFinal() {
        //calculamos los días de la reserva
        int n = fechaFin.getDayOfYear() - fechaInicio.getDayOfYear();

        // Calculamos el precio base de la habitación por el número de noches de la reserva
        double pf = habitacion.getPrecioBase() * n;

        // Si el cliente es VIP, aplicamos un descuento del 10%
        if (cliente.esVip) {
            pf *= DESCUENTO_VIP;
        }

        // Si el intervalo de fechas es mayor a 7 días, aplicamos un descuento adicional del 5%
        if (n > INTERVALO_APLICAR_DESCUENTO) {
            pf *= DESCUENTO_SUPERACION_INTERVALO;
        }

        // Devolvemos el precio final
        return pf;
    }

    public void mostrarReserva() {
        System.out.println("Reserva #" + id);
        System.out.println("Habitación #" + habitacion.getNumero() + " - Tipo: " + habitacion.getTipo() + " - Precio base: " + habitacion.getPrecioBase());
        System.out.println("Cliente: " + cliente.nombre);
        System.out.println("Fecha de inicio: " + fechaInicio.toString());
        System.out.println("Fecha de fin: " + fechaFin.toString());
        System.out.printf("Precio total: %.2f €\n", precioTotal);
    }
}
