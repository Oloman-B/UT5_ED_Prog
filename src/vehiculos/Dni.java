package vehiculos;

import static vehiculos.Utilidades.pedirValidarString;

/** Clase vehiculos.Dni
 *  Gestiona DNI y NIF,
 *  así como distintas utilidades relacionadas con los mismos
 *
 *  @author https://ead.murciaeduca.es/
 *  Modificado y completado por Manolo Sánchez
 */


public class Dni {
    private int numDni;
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    /** Constructor a partir de DNI
     *  @param dni, DNI (sin letra)
     */
    public Dni(int dni) {
        this.numDni = dni;
    }

    /** Constructor a partir de NIF
     *  @param nif, NIF (con letra)
     */
    public Dni(String nif) {
        this.numDni = extraerNumeroNif(nif);
    }

    /** Método get vehiculos.Dni
     */
    public int getDni() {
        return numDni;
    }

    /** Método set DNI (desde DNI)
     *  @param dni el DNI
     */
    public void setDni(int dni)  {
        // Comprobación de rangos
        if (dni > 0 && dni <= 99999999) {
            this.numDni = dni;
        } else {
            System.out.println("ERROR: DNI no  válido.");
        }
    }

    /** Método set DNI (desde NIF)
     *  @param nif el NIF
     */
    public void setDni(String nif)  {
        if (validarNif(nif)) {
            this.numDni = Dni.extraerNumeroNif(nif);
        } else {
            System.out.println("ERROR: NIF no  válido.");
        }
    }

    /** A partir de un NIF extrae la letra
     *  @param nif, el NIF completo
     *  @return la letra que le corresponde a ese número de DNI
     */
    private static char extraerLetraNif(String nif) {
        return nif.charAt(nif.length() - 1);
    }

    /** A partir de un NIF, extrae solo el número
     *  @param nif, el NIF completo
     *  @return la parte numérica del NIF
     */
    public static int extraerNumeroNif(String nif) {
        return Integer.parseInt(nif.substring(0, nif.length() - 1));
    }

    /** Calcula la letra que le corresponde a un DNI
     *  @param dni, el número de DNI
     *  @return la letra que le corresponde a ese número de DNI
     */
    private static char calcularLetraNif(int dni) {
        // Algoritmo letra NIF España
        return LETRAS_DNI.charAt(dni % 23);
    }

    /** Obtiene el NIF a partir de DNI
     *  @param dni el número del DNI
     *  @return el NIF completo
     */
    public static String obtenerNif(int dni) {
        String cadenaNif;
        char letraNif;      // Letra calculada
        letraNif = Dni.calcularLetraNif(dni);
        // Construcción de la cadena del DNI: número + letra
        cadenaNif = dni + String.valueOf(letraNif);
        return cadenaNif;
    }

    /**
     * Solicita un NIF y repite solicitud
     * hasta que su formato sea válido
     *
     * @return valor, NIF con letra
     */
    public static String pedirValidarNif(String mensaje) {
        System.out.println(mensaje);
        String valor=null;
        boolean valido = false;
        while (!valido) {
            valor = pedirValidarString("");
            if (validarNif(valor.replaceAll("[\\s-]+","").toUpperCase()))
                valido = true;
        }
        return valor;
    }

    /** Comprueba la validez de la letra de un NIF
     *  @param nif, el NIF completo
     *  @return true cuando sea Ok
     */
    public static boolean validarNif(String nif)  {
        char letra_calculada;
        char letra_leida;
        int dni_leido;
        boolean esValido = true;
        // Caso cadena nula o formato incorrecto
        if ((nif == null) || !(nif.matches("^[0-9]{1,8}[A-Z]$"))) {
            esValido = false;
            System.out.println("ERROR: Formato NIF no  válido.");
        } else {
            letra_leida = Dni.extraerLetraNif(nif);  // Extraemos la letra de NIF (letra)
            dni_leido = Dni.extraerNumeroNif(nif);  // Extraemos el número de DNI (int)
            letra_calculada = Dni.calcularLetraNif(dni_leido);  // Calculamos la letra de NIF a partir del número extraído
            if (letra_leida != letra_calculada) {   // Comparamos la letra extraída con la calculada
                esValido = false;
                System.out.println("ERROR: NIF no  válido.");
            }
        }
        return esValido;
    }

    /**  Formato salida NIF a partir de numDni
     *   @return la parte numérica del NIF
     */
    public String dniToString() {
        return obtenerNif(numDni);
    }
}
