package vehiculos.coche;

import vehiculos.Persona;

public class CocheDeportivo extends Coche {
    private double aceleracion0a100;
    private int potenciaCv;
            private boolean tieneModoPista;

    public CocheDeportivo(String matricula, String marca, String modelo, int anoFabricacion,
                          int kilometraje, double precio, boolean isDisponible,
                          Persona propietario, int numPuertas, String tipoCombustible,
                          int capacidadMaletero, Traccion traccion, double aceleracion0a100,
                          int potenciaCv, boolean tieneModoPista) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, isDisponible, propietario,
                numPuertas, tipoCombustible, capacidadMaletero, traccion);
        this.aceleracion0a100 = aceleracion0a100;
        this.potenciaCv = potenciaCv;
    }

    // Constructor para Vehiculo disponible y sin propietario
    public CocheDeportivo(String matricula, String marca, String modelo, int anoFabricacion,
                          int kilometraje, double precio, int numPuertas, String tipoCombustible,
                          int capacidadMaletero, Traccion traccion, double aceleracion0a100,
                          int potenciaCv, boolean tieneModoPista) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio,
                numPuertas, tipoCombustible, capacidadMaletero, traccion);
        this.aceleracion0a100 = aceleracion0a100;
        this.potenciaCv = potenciaCv;
    }


    public double getAceleracion0a100() {
        return aceleracion0a100;
    }

    public void setAceleracion0a100(double aceleracion0a100) {
        this.aceleracion0a100 = aceleracion0a100;
    }

    public int getPotenciaCv() {
        return potenciaCv;
    }

    public void setPotenciaCv(int potenciaCv) {
        this.potenciaCv = potenciaCv;
    }

    public boolean isTieneModoPista() {
        return tieneModoPista;
    }

    public void setTieneModoPista(boolean tieneModoPista) {
        this.tieneModoPista = tieneModoPista;
    }

    public String calcularRiesgo() {
        int anos = calcularAntiguedad();
        String riesgo;

        if (anos < 5 && potenciaCv > 400) {
            riesgo = "Alto riesgo";
        } else if (anos < 3) {
            riesgo = "Alto riesgo";
        } else if (anos >= 3 && anos <= 10) {
            riesgo = "Medio riesgo";
        } else {
            riesgo = "Bajo riesgo";
        }

        return riesgo;
    }

    public double calcularDepreciacion() {
        double factorKilometraje = potenciaCv>400 ? 1.1 : 0.9;
        return  precio *  calcularDepreciacionEdad(0.9) * calcularDepreciacionKilometraje(factorKilometraje);

    }

    @Override
    public String imprimirDatosVehiculo() {
        return String.format(
                """     
                        
                        %s
                        DEPORTIVO
                        Aceleración: %,.2f seg
                        Potencia: %d CV
                        Modo pista: %s"""
                , super.imprimirDatosVehiculo(), aceleracion0a100, potenciaCv, tieneModoPista ? "Sí" : "No"
        );
    }
}
