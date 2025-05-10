package org.ed06.model;

import static org.ed06.model.Validacion.*;

/**
 * La clase {@code Cliente} representa a un cliente que puede realizar reservas.
 * Incluye información personal como nombre, DNI, correo electrónico y si es cliente VIP.
 * <p>
 * Durante la construcción del objeto, se validan los campos nombre, DNI y email utilizando
 * los métodos de validación de la clase {@link Validacion}.
 * </p>
 *
 * @author José Cielo Fernández Caballero
 */
public class Cliente {
    public int id;
    public String nombre;
    public String dni;
    public String email;
    public boolean esVip;

    /**
     * Crea un nuevo cliente con los datos proporcionados.
     * Valida el nombre, DNI y email utilizando la clase {@link Validacion}.
     *
     * @param id     identificador del cliente
     * @param nombre nombre del cliente
     * @param dni    documento nacional de identidad
     * @param email  correo electrónico del cliente
     * @param esVip  indica si el cliente es VIP
     * @throws IllegalArgumentException si alguno de los campos validados no es válido
     */
    public Cliente(int id, String nombre, String dni, String email, boolean esVip) {
        this.id = id;
        this.esVip = esVip;

        if (validarNombre(nombre)) {
            this.nombre = nombre;
        }
        if (validarDni(dni)) {
            this.dni = dni;
        }
        if (validarEmail(email)) {
            this.email = email;
        }
    }

    /**
     * @return el ID del cliente
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param id nuevo ID del cliente
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente sin validación.
     *
     * @param nombre nuevo nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return el DNI del cliente
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del cliente sin validación.
     *
     * @param dni nuevo DNI del cliente
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return el correo electrónico del cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del cliente sin validación.
     *
     * @param email nuevo correo electrónico
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return {@code true} si el cliente es VIP, {@code false} en caso contrario
     */
    public boolean isVip() {
        return esVip;
    }

    /**
     * Establece si el cliente es VIP.
     *
     * @param esVip {@code true} si el cliente debe marcarse como VIP
     */
    public void setVip(boolean esVip) {
        this.esVip = esVip;
    }
}
