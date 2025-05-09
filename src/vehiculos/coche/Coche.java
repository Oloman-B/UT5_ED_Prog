package vehiculos.coche;

import vehiculos.Persona;
import vehiculos.Vehiculo;

import static vehiculos.Utilidades.sc;

public abstract class  Coche extends Vehiculo {
    private int numPuertas;
    private String tipoCombustible;
    private int capacidadMaletero;
    private Traccion traccion;

    public Coche(String matricula, String marca, String modelo, int anoFabricacion,
                 int kilometraje, double precio, boolean isDisponible,
                 Persona propietario, int numPuertas, String tipoCombustible,
                 int capacidadMaletero, Traccion traccion) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, isDisponible, propietario);
        this.numPuertas = numPuertas;
        this.tipoCombustible= tipoCombustible;
        this.capacidadMaletero=capacidadMaletero;
        this.traccion=traccion;
    }
    // Constructor para Vehiculo disponible y sin propietario
    public Coche(String matricula, String marca, String modelo, int anoFabricacion,
                 int kilometraje, double precio, int numPuertas,String tipoCombustible,
                 int capacidadMaletero, Traccion traccion) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio);
        this.numPuertas = numPuertas;
        this.tipoCombustible= tipoCombustible;
        this.capacidadMaletero=capacidadMaletero;
        this.traccion=traccion;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public int getCapacidadMaletero() {
        return capacidadMaletero;
    }

    public void setCapacidadMaletero(int capacidadMaletero) {
        this.capacidadMaletero = capacidadMaletero;
    }

    public Traccion getTraccion() {
        return traccion;
    }

    public void setTraccion(Traccion traccion) {
        this.traccion = traccion;
    }


    public static Traccion pedirValidarTraccion(String mensaje) {
        System.out.println(mensaje);
        Traccion valor = null;
        boolean valido = false;
        while (!valido) {
            try {
                valor = Traccion.valueOf(sc.nextLine().trim().toUpperCase());
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Debe teclear uno de los siguientes valores: DELANTERA, TRASERA, T4X4.");
            }
        }
        return valor;
    }
    public abstract String calcularRiesgo();

    public abstract double calcularDepreciacion();

    @Override
    public String imprimirDatosVehiculo() {
        return String.format(
                """
                        
                        %s
                        COCHE
                        Nº. puertas: %d
                        Combustible: %s
                        Maletero: %d l
                        Tracción: %s"""
                , imprimirDatosComunes(), numPuertas, tipoCombustible, capacidadMaletero, traccion
        );
    }
}
