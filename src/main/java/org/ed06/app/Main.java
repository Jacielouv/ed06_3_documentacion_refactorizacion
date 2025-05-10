package org.ed06.app;

import org.ed06.model.Habitacion;
import org.ed06.model.Hotel;
import org.ed06.model.Validacion;

import java.time.LocalDate;
import java.util.*;

/**
 * La clase Main es la clase principal que gestiona la interacción con el usuario.
 * Esta clase permite al usuario registrar habitaciones, registrar clientes, reservar habitaciones,
 * listar las habitaciones disponibles, las reservas realizadas y los clientes registrados.
 *
 * @author José Cielo Fernández Caballero
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);

    // Definimos constantes para las diferentes opciones del menú
    private static final int REGISTRAR_HABITACION = 1;
    private static final int LISTAR_HABITACIONES_DISPONIBLES = 2;
    private static final int RESERVAR_HABITACION = 11;
    private static final int LISTAR_RESERVAS = 12;
    private static final int LISTAR_CLIENTES = 21;
    private static final int REGISTRAR_CLIENTE = 22;
    private static final int SALIR = 0;

    private static final String MENSAJE_ERROR_VALIDACION = "Dato no válido. Inténtalo de nuevo.";
    private static final String MENSAJE_REGISTRO_HABITACION = "Habitación registrada: ";
    private static final String MENSAJE_REGISTRO_CLIENTE = "Cliente registrado: ";

    static Hotel hotel = new Hotel("El mirador", "Calle Entornos de Desarrollo 6", "123456789");

    /**
     * Método principal que gestiona la ejecución de la aplicación.
     * Muestra un menú al usuario y gestiona las opciones seleccionadas.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {

        rellenarDummy();  // Rellenamos el Hotel con datos de prueba

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case REGISTRAR_HABITACION:
                    registrarHabitacion();
                    break;
                case LISTAR_HABITACIONES_DISPONIBLES:
                    hotel.gestorHabitaciones.listarHabitacionesDisponibles();
                    break;
                case RESERVAR_HABITACION:
                    reservarHabitacion();
                    break;
                case LISTAR_RESERVAS:
                    hotel.gestorReservas.listarReservas();
                    break;
                case LISTAR_CLIENTES:
                    hotel.gestorClientes.listarClientes();
                    break;
                case REGISTRAR_CLIENTE:
                    registrarCliente();
                    break;
                case SALIR:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    /**
     * Muestra el menú principal con las opciones disponibles para el usuario.
     */
    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Registrar habitación");
        System.out.println("2. Listar habitaciones disponibles");
        System.out.println("11. Reservar habitación");
        System.out.println("12. Listar reservas");
        System.out.println("21. Listar clientes");
        System.out.println("22. Registrar cliente");
        System.out.println("0. Salir");
    }

    /**
     * Permite registrar una nueva habitación en el hotel. Solicita al usuario el tipo
     * y el precio base de la habitación.
     */
    private static void registrarHabitacion() {
        System.out.println("Introduce el tipo de habitación (SIMPLE, DOBLE, SUITE): ");
        String tipo = scanner.nextLine();
        System.out.println("Introduce el precio base de la habitación: ");
        double precioBase = scanner.nextDouble();
        scanner.nextLine();

        hotel.gestorHabitaciones.registrarHabitaciones(List.of(tipo), List.of(precioBase));
        System.out.println(MENSAJE_REGISTRO_HABITACION + tipo + " - Precio base: " + precioBase);
    }

    /**
     * Permite reservar una habitación en el hotel para un cliente.
     * Solicita al usuario el id del cliente, el tipo de habitación y las fechas de entrada y salida.
     */
    private static void reservarHabitacion() {
        System.out.println("Introduce el id del cliente: ");
        int clienteId = scanner.nextInt();
        System.out.println("Introduce el tipo de habitación (SIMPLE, DOBLE, SUITE, LITERAS): ");
        String tipo = pedirTipoHabitacionValido();
        LocalDate fechaEntrada = pedirFecha("entrada");
        LocalDate fechaSalida = pedirFecha("salida");

        int numeroHabitacion = hotel.reservarHabitacion(clienteId, tipo, fechaEntrada, fechaSalida);
        Habitacion habitacion = hotel.gestorHabitaciones.getHabitacion(numeroHabitacion);

        System.out.println("Habitación #" + habitacion.getNumero() + " - Tipo: " + habitacion.getTipo()
                + " - Precio base: " + habitacion.getPrecioBase());
        System.out.println("Número de habitación reservada: " + numeroHabitacion);
    }

    /**
     * Pide al usuario un tipo de habitación válido. Solo se permiten los tipos:
     * SIMPLE, DOBLE, SUITE, LITERAS.
     *
     * @return El tipo de habitación elegido por el usuario.
     */
    private static String pedirTipoHabitacionValido() {
        List<String> tiposValidos = Arrays.asList("SIMPLE", "DOBLE", "SUITE", "LITERAS");
        String tipo;
        while (true) {
            tipo = scanner.nextLine().toUpperCase();
            if (tiposValidos.contains(tipo)) {
                return tipo;
            } else {
                System.out.println("Tipo de habitación no válido. Los valores posibles son: SIMPLE, DOBLE, SUITE, LITERAS.");
            }
        }
    }

    /**
     * Pide al usuario una fecha (ya sea de entrada o de salida) en formato año, mes, día.
     *
     * @param tipoFecha El tipo de fecha (entrada o salida) que se está solicitando.
     * @return La fecha introducida por el usuario como un objeto LocalDate.
     */
    private static LocalDate pedirFecha(String tipoFecha) {
        System.out.println("Introduce la fecha de " + tipoFecha + " (año): ");
        int anio = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce la fecha de " + tipoFecha + " (mes): ");
        int mes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce la fecha de " + tipoFecha + " (día): ");
        int dia = scanner.nextInt();
        scanner.nextLine();
        return LocalDate.of(anio, mes, dia);
    }

    /**
     * Registra un nuevo cliente en el sistema. Solicita al usuario los datos del cliente,
     * como el nombre, el email, el DNI y si es VIP.
     */
    private static void registrarCliente() {
        String nombre = pedirDatoValido("nombre", Validacion::validarNombre);
        String email = pedirDatoValido("email", Validacion::validarEmail);
        String dni = pedirDatoValido("DNI", Validacion::validarDni);

        System.out.println("¿Es VIP? (true/false): ");
        boolean esVip = scanner.nextBoolean();

        hotel.gestorClientes.registrarCliente(nombre, email, dni, esVip);
        System.out.println(MENSAJE_REGISTRO_CLIENTE + nombre);
    }

    /**
     * Pide un dato válido al usuario (como nombre, email, o DNI) y lo valida usando la función
     * proporcionada por el parámetro `validacion`. Si el dato no es válido, se repite la solicitud.
     *
     * @param tipoDato El tipo de dato que se va a pedir (nombre, email, DNI).
     * @param validacion La función de validación que se aplicará al dato introducido.
     * @return El dato introducido por el usuario.
     */
    private static String pedirDatoValido(String tipoDato, ValidacionInterface validacion) {
        String dato;
        while (true) {
            try {
                System.out.println("Introduce el " + tipoDato + " del cliente: ");
                dato = scanner.next();
                validacion.validar(dato);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(MENSAJE_ERROR_VALIDACION + " Detalle: " + e.getMessage());
            }
        }
        return dato;
    }

    /**
     * Método que rellena el hotel con datos de prueba (habitaciones y clientes).
     */
    private static void rellenarDummy() {
        List<String> tiposHabitaciones = Arrays.asList("SIMPLE", "DOBLE", "SUITE", "LITERAS");
        List<Double> preciosHabitaciones = Arrays.asList(50.0, 80.0, 120.0, 200.0, 65.0, 100.0, 150.0, 250.0);
        hotel.gestorHabitaciones.registrarHabitaciones(tiposHabitaciones, preciosHabitaciones);

        hotel.gestorClientes.registrarCliente("Daniel", "daniel@daniel.com", "12345678A", true);
        hotel.gestorClientes.registrarCliente("Adrián", "adrian@adrian.es", "87654321B", false);
    }

    /**
     * Interfaz funcional para las validaciones de datos de los clientes.
     */
    interface ValidacionInterface {
        void validar(String dato) throws IllegalArgumentException;
    }
}
