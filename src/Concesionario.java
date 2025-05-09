import vehiculos.Vehiculo;

import static vehiculos.Persona.solicitarDatosPersona;

public class Concesionario {
    private final Vehiculo[] stockVehiculos;
    private static int contadorVehiculos;

    public Concesionario(int capacidad) {
        stockVehiculos = new Vehiculo[capacidad];
        contadorVehiculos = 0;
    }

    public Vehiculo[] getstockVehiculos() {
        return stockVehiculos;
    }

    private static int getcontadorVehiculos() {
        return contadorVehiculos;
    }

    /** Método set para asignar valores a un vehiculo
     *  @param vehiculo, clase Vehiculo
     *  @param posicion, la posicion a la que se quiere asignar valor
     */
    public void setVehiculo(Vehiculo vehiculo, int posicion) {
        if (esPosicionValida(posicion)) {
            stockVehiculos[posicion] = vehiculo;
        } else {
            System.out.println("ERROR: No se puede acceder al vehiculo [" + posicion + "].");
        }
    }

    /** Método get para obtener un elemento del array de vehiculos
     **  @param posicion, la posicion de un vehiculo concreta
     **  @return vehiculo, un vehiculo concreta
     */
    public Vehiculo getVehiculo(int posicion) {
        Vehiculo vehiculo = null;
        if (esPosicionValida(posicion)) {
            vehiculo = stockVehiculos[posicion];
        } else {
            System.out.println("ERROR: No se puede obtener el vehiculo [" + posicion + "].");
        }
        return vehiculo;
    }

    /** Método para almacenar nuevos vehiculos
     * @param vehiculo, un objeto Vehiculo
     * @return true si la operación se hizo con éxito
     */
    public boolean insertarVehiculo(Vehiculo vehiculo) {
        boolean valido = false;
        if (esAlmacenLleno() != 1) {
            try {
                setVehiculo(vehiculo, contadorVehiculos);
                contadorVehiculos++;
                valido = true;
            } catch (Exception e) {
                System.out.println("ERROR: Algo no ha debido salir bien\n"
                        + e.getMessage());
            }
        } else {
            System.out.println("** LÍMITE DE VEHÍCULOS ALCANZADO **");
        }
        return valido;
    }

    /** Método para verificar situación de stockVehiculos
     *  @return valor, -1 Almacen vacío, 0 Almacen con algún valor, 1 si Almacen está lleno
     */
    public int esAlmacenLleno() {
        int valor =0;
        if (contadorVehiculos == 0)
            valor = -1;
        else if (contadorVehiculos >= stockVehiculos.length)
            valor = 1;
        return (valor);
    }

    /** Método para validar que una posición no supera los límites del array
     *
     *  @param posicion, posición del elemento dentro del array
     *  @return esPosicionValida, true si la posición es viable
     */
    private boolean esPosicionValida(int posicion) {
        boolean esPosicionValida = true;
        if ((posicion < 0) || (posicion >= stockVehiculos.length)) {
            esPosicionValida = false; // Posición no válida
        }
        return esPosicionValida;
    }

    /** Método para listar la info básica de todas las vehiculos
     *  @return listado, array con la info de todas las vehiculos
     */
    public String[] listadoVehiculos() {
        String[] listado = new String[contadorVehiculos];
        for (int i = 0; i < contadorVehiculos; i++) {
            listado[i] = stockVehiculos[i].imprimirDatosComunes();
        }
        return listado;
    }

    /** Método para listar la info completa de todas las vehiculos
     *  @return listado, array con toda la info de todas las vehiculos
     */
    public String[] listadoVehiculosDetalle() {
        String[] listado = new String[contadorVehiculos];
        for (int i = 0; i < contadorVehiculos; i++) {
            listado[i] = stockVehiculos[i].imprimirDatosVehiculo();
        }
        return listado;
    }

    /** Método para mostrar la info completa de un vehiculo
     * @param matricula, la matrícula de un vehiculo concreta
     * @return datos, cadena con la info del vehiculo o null si no existe
     */
    public String informacionVehiculo(String matricula) {
        String datos = null;
        int posicion = buscarVehiculo(matricula);
        if (esPosicionValida(posicion))
            datos = stockVehiculos[posicion].imprimirDatosVehiculo();
        else
            System.out.println("ERROR: Vehiculo no encontrada");
        return datos;
    }

    public void calcularRiesgoFinal(String matricula) {
        String datos = null;
        int posicion = buscarVehiculo(matricula);
        if (esPosicionValida(posicion)) {
            if (stockVehiculos[posicion].getPropietario() != null) {
                datos = stockVehiculos[posicion].calcularRiesgo();
                System.out.printf("Nivel de riesgo: %s", datos);
            } else {
                System.out.println("ERROR: Antes debe asignar propietario");
            }
        } else
            System.out.println("ERROR: Vehiculo no encontrada");
    }

    public void calcularDepreciacionFinal(String matricula) {
        double depreciacion;
        int posicion = buscarVehiculo(matricula);
        if (esPosicionValida(posicion)) {
            depreciacion = stockVehiculos[posicion].calcularDepreciacion();
            System.out.printf("Valor actual: %,.2f", depreciacion >= 0 ? depreciacion : 0 );
        } else
            System.out.println("ERROR: Vehiculo no encontrada");
    }

    /** Método para asignar un vehiculo
     * @param matricula, la matrícula de un vehiculo concreta
     * @return devuelve true si se ha asignado, false caso contrario
     */
    public boolean asignarPropietario(String matricula) {
        boolean asignado = false;
        int posicion = buscarVehiculo(matricula);
        if ((esPosicionValida(posicion)) && stockVehiculos[posicion].isDisponible()) {
            stockVehiculos[posicion].setPropietario(solicitarDatosPersona());
            stockVehiculos[posicion].setIsDisponible(false);
            asignado = true;
        } else {
            System.out.println("ERROR: Vehiculo no encontrado/disponible");
        }
        return asignado;
    }

    /** Método para ver encontrar un vehiculo por su matrícula
     * @param matricula, la matrícula de un vehiculo concreta
     * @return el índice del vehiculo encontrada o -1 si no se encuentra
     */
    public int buscarVehiculo(String matricula) {
        int indice =-1;
        int i = 0;
        while (i < contadorVehiculos && indice == -1) {
            if (stockVehiculos[i].getMatricula().equals(matricula)) {
                indice = i;
            }
            i++;
        }
        return indice;
    }
}
