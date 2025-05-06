package org.ed06.model;

public class Habitacion {
    private final int numero;
    private final String tipo; // "SIMPLE", "DOBLE", "SUITE"
    private final double precioBase;

    //Todo pendiente cambiar la forma de gestionar la disponibilidad en base a las fechas de las reservas
    private boolean disponible;


    public Habitacion(int numero, String tipo, double precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public boolean isDisponible() {
        return disponible;
    }


    // Método que usa un switch para determinar el número máximo de huéspedes
    public double obtenerNumMaxHuespedes() {
        int NUM_MAX_SIMPLE = 1;
        int NUM_MAX_DOBLE = 3;
        int NUM_MAX_SUITE = 4;
        int NUM_MAX_LITERAS = 8;

        return switch (tipo) {
            case "DOBLE" -> NUM_MAX_DOBLE;
            case "SUITE" -> NUM_MAX_SUITE;
            case "LITERAS" -> NUM_MAX_LITERAS;
            default -> NUM_MAX_SIMPLE;
        };
    }


    public void reservar() {
        if (disponible) {
            System.out.println("Habitación #" + numero + " ya reservada");
        }
        disponible = true;
    }

}
