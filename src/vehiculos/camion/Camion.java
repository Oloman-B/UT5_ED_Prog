package vehiculos.camion;

import vehiculos.Persona;
import vehiculos.Vehiculo;


public class Camion extends Vehiculo {
    private double capacidadCarga;
    private int numEjes;
    private double longitudRemolque;
    private int potenciaMotor;

    public Camion(String matricula, String marca, String modelo, int anoFabricacion,
                  int kilometraje, double precio, boolean isDisponible,Persona propietario,
                  double capacidadCarga, int numEjes, double longitudRemolque, int potenciaMotor) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, isDisponible, propietario);
        this.capacidadCarga = capacidadCarga;
        this.numEjes = numEjes;
        this.longitudRemolque = 0;
        this.potenciaMotor = 0;
    }
    // Constructor para Vehiculo disponible y sin propietario
    public Camion(String matricula, String marca, String modelo, int anoFabricacion,
                  int kilometraje, double precio,
                  double capacidadCarga, int numEjes, double longitudRemolque, int potenciaMotor) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio);
        this.capacidadCarga = capacidadCarga;
        this.numEjes = numEjes;
        this.longitudRemolque = longitudRemolque;
        this.potenciaMotor = potenciaMotor;
    }
    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public int getNumEjes() {
        return numEjes;
    }

    public void setNumEjes(int numEjes) {
        this.numEjes = numEjes;
    }

    public double getLongitudRemolque() {
        return longitudRemolque;
    }

    public void setLongitudRemolque(int longitudRemolque) {
        this.longitudRemolque = longitudRemolque;
    }

    public int getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(int potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }

    public String calcularRiesgo() {
        int anos = calcularAntiguedad();
        String riesgo;

        if (anos < 5) {
            riesgo = "No apto (mínimo 5 años de experiencia)";
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
        return  precio *  calcularDepreciacionEdad(0.9) * calcularDepreciacionKilometraje(0.6);

    }

    @Override
    public String imprimirDatosVehiculo() {
        return String.format(
                """     
                    
                    %s
                    CAMIÓN
                    Carga máx: %,.2f Tm
                    Nº. ejes: %d
                    Longitud remolque: %,.2f m
                    Potencia motor: %d"""
                ,imprimirDatosComunes(),capacidadCarga,numEjes,longitudRemolque,potenciaMotor
        );
    }
}
