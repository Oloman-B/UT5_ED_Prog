package vehiculos.coche;


import vehiculos.Persona;

import static vehiculos.Utilidades.sc;

public class CocheSedan extends Coche {
    private double espacioInterior;
    private NivelConfort nivelConfort;

    public CocheSedan(String matricula, String marca, String modelo, int anoFabricacion,
                      int kilometraje, double precio, boolean isDisponible,
                      Persona propietario, int numPuertas, String tipoCombustible,
                      int capacidadMaletero, Traccion traccion, double espacioInterior,
                      NivelConfort nivelConfort) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, isDisponible, propietario,
                numPuertas, tipoCombustible, capacidadMaletero, traccion);
        this.espacioInterior = espacioInterior;
        this.nivelConfort = nivelConfort;
    }
    // Constructor para Vehiculo disponible y sin propietario
    public CocheSedan(String matricula, String marca, String modelo, int anoFabricacion,
                 int kilometraje, double precio, int numPuertas,String tipoCombustible,
                 int capacidadMaletero, Traccion traccion, double espacioInterior,
                 NivelConfort nivelConfort) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio,
                numPuertas, tipoCombustible, capacidadMaletero, traccion);
        this.espacioInterior = espacioInterior;
        this.nivelConfort = nivelConfort;
    }

    public double getEspacioInterior() {
        return espacioInterior;
    }

    public void setEspacioInterior(double espacioInterior) {
        this.espacioInterior = espacioInterior;
    }

    public NivelConfort getNivelConfort() {
        return nivelConfort;
    }

    public void setNivelConfort(NivelConfort nivelConfort) {
        this.nivelConfort = nivelConfort;
    }

    public static NivelConfort pedirValidarConfort(String mensaje) {
        System.out.println(mensaje);
        NivelConfort valor = null;
        boolean valido = false;
        while (!valido) {
            try {
                valor = NivelConfort.valueOf(sc.nextLine().trim().toUpperCase());
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Debe teclear uno de los siguientes valores: BASICO, MEDIO, PREMIUM.");
            }
        }
        return valor;
    }

    public String calcularRiesgo() {
        int anos = calcularAntiguedad();
        String riesgo;

        if (anos < 3) {
            riesgo = "Alto riesgo";
        } else if (anos >= 3 && anos <= 10) {
            riesgo = "Medio riesgo";
        } else {
            riesgo = "Bajo riesgo";
        }

        return riesgo;
    }

    public double calcularDepreciacion() {
        return  precio *  calcularDepreciacionEdad(0.85) * calcularDepreciacionKilometraje(0.8);

    }

    @Override
    public String imprimirDatosVehiculo() {
        return String.format(
                """     
                        
                        %s
                        SEDÁN
                        Espacio: %,.2f l
                        Confort: %s"""
                , super.imprimirDatosVehiculo(), espacioInterior, nivelConfort.toString()
        );
    }

}
