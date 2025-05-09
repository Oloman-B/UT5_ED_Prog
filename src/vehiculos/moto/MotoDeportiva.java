package vehiculos.moto;

public class MotoDeportiva extends Moto {
    private double velocidadMaxima;
    private boolean tieneQuickShifter;

    public MotoDeportiva(String matricula, String marca, String modelo, int anoFabricacion,
                         int kilometraje, double precio, boolean isDisponible, vehiculos.Persona propietario,
                         int cilindrada, int peso, double altura, TipoTransmision tipoTransmision,
                         double velocidadMaxima, boolean tieneQuickShifter ) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, cilindrada, peso, altura, tipoTransmision);
        this.velocidadMaxima = velocidadMaxima;
        this.tieneQuickShifter = tieneQuickShifter;
    }
    // Vehiculo disponible sin propietario
    public MotoDeportiva(String matricula, String marca, String modelo, int anoFabricacion,
                         int kilometraje, double precio,
                         int cilindrada, int peso, double altura, TipoTransmision tipoTransmision,
                         double velocidadMaxima, boolean tieneQuickShifter ) {
        super(matricula, marca, modelo, anoFabricacion, kilometraje, precio, cilindrada, peso, altura, tipoTransmision);
        this.velocidadMaxima = velocidadMaxima;
        this.tieneQuickShifter = tieneQuickShifter;
    }
    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public boolean isTieneQuickShifter() {
        return tieneQuickShifter;
    }

    public void setTieneQuickShifter(boolean tieneQuickShifter) {
        this.tieneQuickShifter = tieneQuickShifter;
    }

    public String calcularRiesgo() {
        int anos = calcularAntiguedad();
        String riesgo;
        if (anos < 3 || velocidadMaxima > 250) {
            riesgo = "Alto riesgo";
        } else if (anos >= 3 && anos <= 10 || velocidadMaxima > 250) {
            riesgo = "Medio riesgo";
        } else {
            riesgo = "Bajo riesgo";
        }
        return riesgo;
    }

    public double calcularDepreciacion() {
        double factorKilometraje = getCilindrada() > 400? 1.05 : 0.95;
        return  precio *  calcularDepreciacionEdad(0.9) * calcularDepreciacionKilometraje(factorKilometraje);
    }

    @Override
    public String imprimirDatosVehiculo() {
        return String.format(
                """
                   
                   %s
                   DEPORTIVA
                   Velocidad máx.: %,.2f km/h
                   QuickShifter: %s"""
                ,super.imprimirDatosVehiculo(),velocidadMaxima,tieneQuickShifter ? "Sí" : "No"
        );
    }
}
