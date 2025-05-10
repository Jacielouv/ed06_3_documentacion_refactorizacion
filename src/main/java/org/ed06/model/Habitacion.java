package org.ed06.model;

/**
 * La clase {@code Habitacion} representa una habitación de un hotel con un número identificador,
 * un tipo (por ejemplo: "SIMPLE", "DOBLE", "SUITE", "LITERAS") y un precio base.
 * <p>
 * También proporciona información sobre disponibilidad y capacidad máxima de huéspedes según el tipo.
 * </p>
 *
 * @author José Cielo Fernández Caballero
 */
public class Habitacion {
    private final int numero;
    private final String tipo; // "SIMPLE", "DOBLE", "SUITE", etc.
    private final double precioBase;

    // TODO: Gestionar disponibilidad en función de las fechas y reservas reales
    private boolean disponible;

    /**
     * Crea una nueva habitación con los datos especificados.
     *
     * @param numero     número identificador de la habitación
     * @param tipo       tipo de habitación (por ejemplo: "SIMPLE", "DOBLE", "SUITE")
     * @param precioBase precio base por noche de la habitación
     */
    public Habitacion(int numero, String tipo, double precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.disponible = true;
    }

    /**
     * @return el número identificador de la habitación
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @return el tipo de la habitación (por ejemplo: "SIMPLE", "DOBLE", "SUITE")
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return el precio base por noche de la habitación
     */
    public double getPrecioBase() {
        return precioBase;
    }

    /**
     * @return {@code true} si la habitación está disponible, {@code false} en caso contrario
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Determina el número máximo de huéspedes que puede alojar la habitación
     * en función de su tipo.
     *
     * @return el número máximo de huéspedes permitido
     */
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

    /**
     * Marca la habitación como reservada.
     * <p>
     * Actualmente, este método solo cambia el estado de disponibilidad a {@code true}
     * y muestra un mensaje si ya lo estaba. Esta lógica parece incompleta o incorrecta,
     * ya que no marca la habitación como no disponible.
     * </p>
     */
    public void reservar() {
        if (disponible) {
            System.out.println("Habitación #" + numero + " ya reservada");
        }
        disponible = true; // Nota: probablemente deba ser `false` para marcar como no disponible
    }
}
