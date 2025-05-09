package vehiculos;

import java.time.LocalDate;
import java.util.Scanner;


import static java.lang.Math.pow;


public abstract class Vehiculo implements Imprimible, Valorable, Asegurable {
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected int anoFabricacion;
    protected int kilometraje;
    protected double precio;
    protected boolean isDisponible;
    protected Persona propietario;

    public Vehiculo(String matricula, String marca, String modelo, int anoFabricacion,
                    int kilometraje, double precio, boolean isDisponible, Persona propietario) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacion = anoFabricacion;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.isDisponible = isDisponible;
        this.propietario = propietario;
    }
    // Constructor para Vehiculo disponible y sin propietario
    public Vehiculo(String matricula, String marca, String modelo, int anoFabricacion,
                    int kilometraje, double precio) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacion = anoFabricacion;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.isDisponible = true;
        this.propietario = null;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(int anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public void setIsDisponible(boolean isDisponible) {
        this.isDisponible = isDisponible;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

     /**
     * Solicita una matrícula  y repite solicitud hasta que sea válida
     *
     * @return valor, el valor aceptado
     */
    public static String pedirValidarMatricula(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.println(mensaje);
        String matricula="";
        boolean valido = false;
        while (!valido) {
            matricula = sc.nextLine().replaceAll("[\\s-]+", "").toUpperCase();
            valido = matricula.matches("^[0-9]{4}[A-Z]{3}$");
            if (!valido) {
                System.out.println("ERROR: La matrícula no es válida (" + matricula + ").");
            }
        }
        return matricula;
    }


    public abstract  String calcularRiesgo();
    /** AUXILIAR */
    protected int calcularAntiguedad() {
        return LocalDate.now().getYear() - propietario.getAnoCarnet();
    }

    public abstract double calcularDepreciacion();
    /** AUXILIAR */
    protected double calcularDepreciacionEdad(double factorEdad) {
        return pow(factorEdad, LocalDate.now().getYear() - anoFabricacion);
    }
    protected double calcularDepreciacionKilometraje(double factorKilometraje) {
        return 1-(kilometraje*factorKilometraje/100000);
    }

    public abstract String imprimirDatosVehiculo();
    /** AUXILIAR */
    public String imprimirDatosComunes(){
        String propietarioStr = propietario == null ? "Sin propietario" : propietario.toString();
        return String.format(
                """     
                    
                    ** MATRÍCULA %s
                    Marca: %s
                    Modelo: %s
                    Año fabric.: %d
                    Kilometraje: %d km
                    Precio: %,.2f €
                    Estado: %s
                    Propietario: %s
                    """,
                matricula,marca, modelo,anoFabricacion,kilometraje,
                precio,isDisponible ? "Sí" : "No",propietarioStr
        );
    }

}
