package vehiculos.coche;

import vehiculos.Persona;

public class CocheSuv extends Coche {
    private boolean capacidadOffroad;
    private double alturaLibreSuelo;

    public CocheSuv(String matricula, String marca, String modelo, int anoFabricacion,
                    int kilometraje, double precio, boolean isDisponible,
                    Persona propietario, int numPuertas, String tipoCombustible,
                    int capacidadMaletero, Traccion traccion,
                    boolean capacidadOffroad, double alturaLibreSuelo) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, isDisponible,
                propietario, numPuertas, tipoCombustible, capacidadMaletero, traccion);
        this.capacidadOffroad = capacidadOffroad;
        this.alturaLibreSuelo = alturaLibreSuelo;
    }

    // Constructor para Vehiculo disponible y sin propietario
    public CocheSuv(String matricula, String marca, String modelo, int anoFabricacion,
                    int kilometraje, double precio, int numPuertas, String tipoCombustible,
                    int capacidadMaletero, Traccion traccion,
                    boolean capacidadOffroad, double alturaLibreSuelo) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio,
                numPuertas, tipoCombustible, capacidadMaletero, traccion);
        this.capacidadOffroad = capacidadOffroad;
        this.alturaLibreSuelo = alturaLibreSuelo;
    }

    public boolean isCapacidadOffroad() {
        return capacidadOffroad;
    }

    public void setCapacidadOffroad(boolean capacidadOffroad) {
        this.capacidadOffroad = capacidadOffroad;
    }

    public double getAlturaLibreSuelo() {
        return alturaLibreSuelo;
    }

    public void setAlturaLibreSuelo(double alturaLibreSuelo) {
        this.alturaLibreSuelo = alturaLibreSuelo;
    }

    public String calcularRiesgo() {
        int anos = calcularAntiguedad();
        String riesgo;

        if (capacidadOffroad && anos >= 3) {
            riesgo = "Bajo riesgo";
        } else if (anos < 3) {
            riesgo = "Alto riesgo";
        } else {
            riesgo = "Medio riesgo";
        }

        return riesgo;
    }
    public double calcularDepreciacion() {
        double factorKilometraje = capacidadOffroad? 1.05 : 0.75;
        return  precio *  calcularDepreciacionEdad(0.9) * calcularDepreciacionKilometraje(factorKilometraje);

    }

    @Override
    public String imprimirDatosVehiculo() {
        return String.format(
                """
                        
                        %s
                        SUV
                        Off Road: %s
                        Altura libre: %,.2f cm"""
                , super.imprimirDatosVehiculo(), capacidadOffroad, alturaLibreSuelo
        );
    }
}
