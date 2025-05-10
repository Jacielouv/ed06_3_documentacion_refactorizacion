package org.ed06.model;

/**
 * La clase {@code Validacion} proporciona métodos utilitarios para validar
 * datos comunes como nombres, correos electrónicos y DNI.
 * <p>
 * Cada método lanza una {@link IllegalArgumentException} si la validación falla.
 * Estos métodos pueden utilizarse para garantizar que los datos de entrada cumplen
 * con los requisitos básicos de formato y contenido.
 * </p>
 *
 * @author José Cielo Fernández Caballero
 */
public class Validacion {

    /**
     * Valida que el nombre no sea nulo, no esté vacío y tenga al menos tres caracteres
     * (ignorando espacios al inicio y al final).
     *
     * @param nombre el nombre a validar
     * @return {@code true} si el nombre es válido
     * @throws IllegalArgumentException si el nombre es nulo, está vacío o tiene menos de 3 caracteres
     */
    public static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.trim().length() < 3) {
            throw new IllegalArgumentException("El nombre no es válido");
        }
        return true;
    }

    /**
     * Valida que el correo electrónico tenga un formato válido.
     *
     * @param email el correo electrónico a validar
     * @return {@code true} si el correo electrónico es válido
     * @throws IllegalArgumentException si el correo electrónico no cumple con el formato estándar
     */
    public static boolean validarEmail(String email) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("El email no es válido");
        }
        return true;
    }

    /**
     * Valida que el DNI tenga un formato correcto: 8 dígitos seguidos de una letra mayúscula.
     *
     * @param dni el DNI a validar
     * @return {@code true} si el DNI es válido
     * @throws IllegalArgumentException si el DNI no cumple con el formato especificado
     */
    public static boolean validarDni(String dni) {
        if (!dni.matches("[0-9]{8}[A-Z]")) {
            throw new IllegalArgumentException("El DNI no es válido");
        }
        return true;
    }
}
