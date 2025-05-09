package vehiculos;

import java.time.LocalDate;

import static vehiculos.Dni.pedirValidarNif;
import static vehiculos.Utilidades.*;

public class Persona {
    private String nombre;
    private String apellidos;
    private Dni dni;
    private int anoNacimiento;
    private int anoCarnet;

    public Persona(String nombre, String apellidos, Dni dni, int anoNacimiento, int anoCarnet) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.anoNacimiento = anoNacimiento;
        this.anoCarnet = anoCarnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Dni getDni() {
        return dni;
    }

    public void setDni(Dni dni) {
        this.dni = dni;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public int getAnoCarnet() {
        return anoCarnet;
    }

    public void setAnoCarnet(int anoCarnet) {
        this.anoCarnet = anoCarnet;
    }

    /** Método para solicitar todos los datos de Persona
     *
     * @return persona, objeto Persona con todos sus datos
     */
    public static Persona solicitarDatosPersona() {
        Persona persona;
        persona = new Persona(
                pedirValidarString("Teclee nombre: ").trim().replaceAll("\\s+", " ").toUpperCase(),
                pedirValidarString("Teclee apellidos: ").trim().replaceAll("\\s+", " ").toUpperCase(),
                new Dni(pedirValidarNif("Teclee NIF: ")),
                pedirValidarEntero(1900,LocalDate.now().getYear(), "Teclee año nacimiento: "),
                pedirValidarEntero(1900,LocalDate.now().getYear(), "Teclee año carnet: ")
        );
        return persona;
    }

    /**  Formato de salida datos Persona
     */
    @Override
    public String toString() {
        return String.format("%s - %s %s\nA. nacimiento: %d\nA. carnet: %s",
                dni.dniToString(), nombre, apellidos,anoNacimiento,anoCarnet);
    }

}
