package vehiculos.moto;

import vehiculos.Persona;
import vehiculos.Vehiculo;
import static vehiculos.Utilidades.sc;

public abstract class Moto extends Vehiculo {
    private int cilindrada;
    int peso;
    double altura;
    private final TipoTransmision tipoTransmision;

    public Moto(String matricula, String marca, String modelo, int anoFabricacion,
                int kilometraje, double precio, boolean isDisponible, Persona propietario,
                int cilindrada, int peso, double altura, TipoTransmision tipoTransmision) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, isDisponible, propietario);
        this.cilindrada = cilindrada;
        this.peso = peso;
        this.altura = altura;
        this.tipoTransmision = tipoTransmision;
    }
    // Vehiculo disponible y sin propietario
    public Moto(String matricula, String marca, String modelo, int anoFabricacion,
                int kilometraje, double precio,
                int cilindrada, int peso, double altura, TipoTransmision tipoTransmision) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio);
        this.cilindrada = cilindrada;
        this.peso = peso;
        this.altura = altura;
        this.tipoTransmision = tipoTransmision;
    }
    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public static TipoTransmision pedirValidarTipoTransmision(String mensaje) {
        System.out.println(mensaje);
        TipoTransmision valor = null;
        boolean valido = false;
        while (!valido) {
            try {
                valor = TipoTransmision.valueOf(sc.nextLine().trim().toUpperCase());
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Debe teclear uno de los siguientes valores: MANUAL, AUTOMATICA");
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
                    MOTO
                    Cilindrada: %d cc
                    Peso: %d kg
                    Altura asiento: %,.2f cm
                    Tipo transmisión: %s"""
                ,imprimirDatosComunes(),cilindrada, peso, altura, tipoTransmision.toString()
        );
    }

}
