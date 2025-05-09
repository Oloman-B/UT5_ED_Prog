package vehiculos.moto;

public class MotoScooter extends Moto{
    private int espacioBajoAsiento;

    public MotoScooter(String matricula, String marca, String modelo, int anoFabricacion,
                       int kilometraje, double precio, boolean isDisponible, vehiculos.Persona propietario,
                       int cilindrada, int peso, double altura, TipoTransmision tipoTransmision, int espacioBajoAsiento){
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, isDisponible, propietario,cilindrada, peso, altura, tipoTransmision);
        this.espacioBajoAsiento = espacioBajoAsiento;
    }
    // Disponible sin propietario
    public MotoScooter(String matricula, String marca, String modelo, int anoFabricacion,
                       int kilometraje, double precio,
                       int cilindrada, int peso, double altura, TipoTransmision tipoTransmision, int espacioBajoAsiento){
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, cilindrada, peso, altura, tipoTransmision);
        this.espacioBajoAsiento=espacioBajoAsiento;
    }
    public int getEspacioBajoAsiento() {
        return espacioBajoAsiento;
    }

    public void setEspacioBajoAsiento(int espacioBajoAsiento) {
        this.espacioBajoAsiento = espacioBajoAsiento;
    }

    public String calcularRiesgo() {
        int anos = calcularAntiguedad();
        String riesgo;

        if (anos < 5) {
            riesgo = "Alto riesgo";
        } else if (anos >= 3 && anos <= 10) {
            riesgo = "Medio riesgo";
        } else {
            riesgo = "Bajo riesgo";
        }

        return riesgo;
    }
    public double calcularDepreciacion() {
        return  precio *  calcularDepreciacionEdad(0.9) * calcularDepreciacionKilometraje(1.2);

    }
    @Override
    public String imprimirDatosVehiculo() {
        return String.format(
                """     
                   
                   %s
                   SCOOTER
                   Espacio asiento: %d l"""
                ,super.imprimirDatosVehiculo(),espacioBajoAsiento
        );
    }

}
