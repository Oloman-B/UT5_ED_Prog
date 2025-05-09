/** Clase vehiculos.Utilidades
 *  Métodos para solicitar y validar
 *  diferentes tipos de datos
 *
 *  @author Manolo Sánchez
 */

package vehiculos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;



public final class Utilidades {
    public static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatoEsp = DateTimeFormatter.ofPattern("ddMMyyyy");

    // Evitar instancias
    private Utilidades() {
    }

    /**
     * Solicita un String y repite solicitud hasta que no esté vacío
     *
     * @param mensaje, texto que se mostrará en pantalla para pedir dato
     * @return cadena, el valor aceptado
     */
    public static Boolean pedirValidarBoolean(String mensaje) {
        System.out.println(mensaje);
        boolean valor = false;
        boolean valido = false;
        String entrada;
        while (!valido) {
            try {
                entrada = sc.nextLine().trim().toLowerCase();
                if (entrada.equals("true") || entrada.equals("s")) {
                    valor = true;
                    valido = true;
                } else if (entrada.equals("false") || entrada.equals("n")) {
                    // valor = false;
                    valido = true;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Sólo 'S' o 'N'.");
            }
        }
        return valor;
    }

    /**
     * Solicita un String y repite solicitud hasta que no esté vacío
     *
     * @param mensaje, texto que se mostrará en pantalla para pedir dato
     * @return cadena, el valor aceptado
     */
    public static String pedirValidarString(String mensaje) {
        System.out.println(mensaje);
        String valor = null;
        boolean valido = false;
        while (!valido) {
            try {
                valor = sc.nextLine().trim();
                if (valor.isEmpty()) {
                    throw new InputMismatchException();
                }
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Cadena vacía.");
            }
        }
        return valor;
    }

    /**
     * Solicita un entero y repite solicitud
     * hasta que sea válido y esté entre min y max (inclusive)
     *
     * @param min,     valor mínimo a validar
     * @param max,     valor máximo a validar
     * @param mensaje, texto que se mostrará en pantalla para pedir dato
     * @return valor, el valor aceptado
     */
    public static int pedirValidarEntero(int min, int max, String mensaje) {
        System.out.println(mensaje);
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                if (sc.hasNextInt()) {
                    valor = sc.nextInt();
                    if (valor >= min && valor <= max) {
                        valido = true;
                    } else {
                        throw new InputMismatchException();
                    }
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Debe teclear un número entero (" + min + "-" + max + ").");
            }
            sc.nextLine();
        }
        return valor;
    }

    /**
     * Solicita un double y repite solicitud
     * hasta que sea válido y no esté por debajo de min
     *
     * @param min,     valor mínimo a validar
     * @param mensaje, texto que se mostrará en pantalla para pedir dato
     * @return valor, el valor aceptado
     */
    public static double pedirValidarDouble(double min, String mensaje) {
        System.out.println(mensaje);
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                if (sc.hasNextDouble()) {
                    valor = sc.nextDouble();
                    if (valor >= min) {
                        valido = true;
                    } else {
                        throw new InputMismatchException();
                    }
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Debe teclear un número válido (mín: " + min + ").");
            }
            sc.nextLine();
        }
        return valor;
    }


    /**
     * Solicita varias cadenas homogéneas y devuelve un array con ellas
     * Se solicita previamente la cantidad de items a almacenar
     *
     * @param min,     cantidad mínima de cadenas a almacenar
     * @param max,     cantidad máxima de cadenas a almacenar
     * @param tipoDato, nombre del tipo de dato para mostrar en pantalla al pedirlo
     * @return valor, el array con todas las cadenas
     */
    public static String[] pedirValidarArrayStrings(int min,int max,String tipoDato) {
        int numItems = pedirValidarEntero(min,max,"Teclee cantidad de "+tipoDato+" a grabar ("+min+"-"+max+"): ");
        String[] valor = new String[numItems];
        int contador = 0;
        while (numItems > 0) {
            valor[contador] = pedirValidarString("Teclee item "+ (contador+1)+":");
            contador++;
            numItems--;
        }
        return valor;
    }


    public static boolean validarFechaMenorHoy(LocalDate fecha) {
        if (!fecha.isBefore(LocalDate.now())) {
            System.out.println("* La fecha no puede ser posterior a la de hoy");
        }
        return fecha.isBefore(LocalDate.now());
    }

    /**
     * Solicita una Fecha String y repite solicitud
     * hasta que su formato sea ddmmaaaa
     * y la fecha OK según calendario gregoriano
     *
     * @return valor, NIF con letra
     */
    public static LocalDate pedirValidarFechaddmmaaaa(String mensaje) {
        System.out.println(mensaje);
        LocalDate fechaDate = null;
        String fechaTexto;
        boolean valido = false;
        while(!valido) {
            try {
                fechaTexto = pedirValidarString("").replaceAll("[\\s-/]+", "").toUpperCase();
                fechaDate = LocalDate.parse(fechaTexto, formatoEsp);
                // Comprueba que LocalDate no haya redondeado fechas imposibles a válidas
                if (fechaTexto.equals(fechaDate.format(formatoEsp))) {
                    // fechaDate.isBefore(LocalDate.now()) para ver si es anterior a hoy
                    valido = true;
                } else {
                    System.out.println("Error: Fecha incorrecta");
                }
            } catch (DateTimeParseException var6) {
                System.out.println("Error: Formato fecha incorrecto");
            }
        }
        return fechaDate;
    }
}