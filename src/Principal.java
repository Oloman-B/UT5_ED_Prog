
import vehiculos.coche.*;
import vehiculos.camion.Camion;
import vehiculos.moto.*;


import java.time.LocalDate;

import static vehiculos.Utilidades.*;
import static vehiculos.Vehiculo.pedirValidarMatricula;
import static vehiculos.coche.Coche.pedirValidarTraccion;
import static vehiculos.coche.CocheSedan.pedirValidarConfort;
import static vehiculos.moto.Moto.pedirValidarTipoTransmision;


public class Principal {
    public static void main(String[] args) {
        // Crear objeto concesionario con capacidad 100 cuentas
        Concesionario concesionario = new Concesionario(100);
        int opcion = 0;
        while (opcion != 7) {
            mostrarMenu();
            opcion = pedirValidarEntero(1, 7, "Teclee opción (1-7): ");
            if (concesionario.esAlmacenLleno() == -1 && opcion > 1 && opcion < 7) {
                System.out.println("** NO HAY VEHÍCULOS REGISTRADOS **");
            } else if (concesionario.esAlmacenLleno() == 1 && opcion == 1) {
                System.out.println("** LÍMITE DE ALMACÉN ALCANZADO **");
            } else {
                gestionarOpciones(opcion, concesionario);
            }
        }
        System.out.println("Salida del programa.");
    }

    /**
     * Visualizar menú principal
     */
    public static void mostrarMenu() {
        System.out.println("\nMenú de opciones");
        System.out.println("================");
        System.out.println("1. Registrar Vehículo.");
        System.out.println("2. Listar Vehículos.");
        System.out.println("3. Buscar Vehículo.");
        System.out.println("4. Asignar Vehículo.");
        System.out.println("5. Resumen riesgo.");
        System.out.println("6. Calcular depreciación de vehículo.");
        System.out.println("7. Salir de la aplicación.");
        System.out.println("--------------------------");
    }

    /**
     * Switch para geestionar opciones
     *
     * @param opcion
     * @param concesionario
     */
    public static void gestionarOpciones(int opcion, Concesionario concesionario) {
        switch (opcion) {
            case 1:
                // Alta vehículo
                crearVehiculo(concesionario);
                break;
            case 2:
                // Listado básico de vehículos
                for (String salida : concesionario.listadoVehiculos())
                    System.out.println(salida);
                break;
            case 3:
                // Obtener todos los datos de un vehículo
                String datos = concesionario.informacionVehiculo(pedirValidarMatricula("Teclee Matrícula: "));
                if (datos != null)
                    System.out.println(datos);
                break;
            case 4:
                // Asignar propietario
                if (concesionario.asignarPropietario(pedirValidarMatricula("Teclee matrícula: "))) {
                    System.out.println("Propietario asignado.");
                } else {
                    System.out.println("ERROR: Operación no realizada.");
                }
                break;
            case 5:
                // Resumen riesgo
                concesionario.calcularRiesgoFinal(pedirValidarMatricula("Teclee matrícula: "));
                break;
            case 6:
                // Calcular depreciación
                concesionario.calcularDepreciacionFinal(pedirValidarMatricula("Teclee matrícula: "));
                break;
            case 7:
                // Salir del programa
                break;
            default:
                // Posible caso no contemplado
                System.out.println("Opción no válida.");
                break;
        }
    }

    public static void crearVehiculo(Concesionario concesionario) {
        boolean insertado = false;
        String  matricula = pedirValidarMatricula("Teclee matrícula: ");
        // Comprobamos que el vehículo no exista previamente
        if (concesionario.buscarVehiculo(matricula) == -1) {
            String marca = pedirValidarString("Teclee marca: ").trim().replaceAll("\\s+", " ").toUpperCase();
            String modelo = pedirValidarString("Teclee modelo: ").trim().replaceAll("\\s+", " ").toUpperCase();
            int ano = pedirValidarEntero(1900, LocalDate.now().getYear(), "Teclee año fabricación"); //String.valueOf(int o double) --> transforma en cadena
            int kilometraje = pedirValidarEntero(0, 9999999, "Teclee kilometraje: ");
            double precio = pedirValidarDouble(0.0, "Teclee precio: ");

            int opcion = pedirValidarEntero(1, 6,
                    """
                            Teclee Tipo de vehículo:
                            1. Camión
                            2. Coche Sedán
                            3. Coche SUV
                            4. Coche Deportivo
                            5. Moto Scooter
                            6. Moto Deportiva"""
            );
            int numPuertas;
            String tipoCombustible;
            int capacidadMaletero;
            Traccion traccion;
            int cilindrada;
            int peso;
            double alturaAsiento;
            TipoTransmision tipoTransmision;
            switch (opcion) {
                case 1:
                    // Camión
                    double capacidadCarga = pedirValidarDouble(0, "Teclee capacidad de carga (Tm): ");
                    int numEjes = pedirValidarEntero(2, 8, "Teclee número de ejes: ");
                    double longitudRemolque = pedirValidarDouble(2, "Teclee longitud remolque (m): ");
                    int potenciaMotor = pedirValidarEntero(1, 9999, "Teclee potencia Motor: ");
                    insertado = concesionario.insertarVehiculo(new Camion(matricula, marca, modelo, ano, kilometraje, precio,
                            capacidadCarga, numEjes, longitudRemolque, potenciaMotor));
                    break;
                case 2:
                    // Sedán
                    numPuertas = pedirValidarEntero(3, 5, "Teclee número de puertas (3-5): ");
                    tipoCombustible = pedirValidarString("Teclee tipo combustible: ");
                    capacidadMaletero = pedirValidarEntero(0, 99999, "Teclee capacidad de maletero (l): ");
                    traccion = pedirValidarTraccion("Teclee tipo de tracción: ");
                    double espacioInterior = pedirValidarDouble(0, "Teclee espacio interior (m3): ");
                    NivelConfort nivelConfort = pedirValidarConfort("Teclee nivel de confort: ");
                    insertado = concesionario.insertarVehiculo(new CocheSedan(matricula, marca, modelo, ano, kilometraje, precio,
                            numPuertas, tipoCombustible, capacidadMaletero, traccion, espacioInterior, nivelConfort));
                    break;
                case 3:
                    // SUV
                    numPuertas = pedirValidarEntero(3, 5, "Teclee número de puertas (3-5): ");
                    tipoCombustible = pedirValidarString("Teclee tipo combustible: ");
                    capacidadMaletero = pedirValidarEntero(0, 99999, "Teclee capacidad de maletero (l): ");
                    traccion = pedirValidarTraccion("Teclee tipo de tracción: ");
                    boolean capacidadOffRoad = pedirValidarBoolean("¿Es Off-Road (s/n)?: ");
                    double alturaLibreSuelo = pedirValidarDouble(0, "Teclee altura libre sobre el suelo: ");
                    insertado = concesionario.insertarVehiculo(new CocheSuv(matricula, marca, modelo, ano, kilometraje, precio,
                            numPuertas, tipoCombustible, capacidadMaletero, traccion, capacidadOffRoad, alturaLibreSuelo));
                    break;
                case 4:
                    // Coche Deportivo
                    // SUV
                    numPuertas = pedirValidarEntero(3, 5, "Teclee número de puertas (3-5): ");
                    tipoCombustible = pedirValidarString("Teclee tipo combustible: ");
                    capacidadMaletero = pedirValidarEntero(0, 99999, "Teclee capacidad de maletero (l): ");
                    traccion = pedirValidarTraccion("Teclee tipo de tracción: ");
                    double aceleracion0a100 = pedirValidarDouble(0,"Teclee aceleración 0 a 100 (seg): ");
                    int potenciaCv = pedirValidarEntero(1,500,"Teclee potencia (CV): ");
                    boolean tieneModoPista = pedirValidarBoolean("¿Tiene modo pista (s/n)?: ");
                    insertado = concesionario.insertarVehiculo(new CocheDeportivo(matricula, marca, modelo, ano, kilometraje, precio,
                            numPuertas, tipoCombustible, capacidadMaletero,traccion,aceleracion0a100,potenciaCv,tieneModoPista));
                    break;
                case 5:
                    // Scooter
                    cilindrada = pedirValidarEntero(1, 3000, "Teclee cilindrada (cc): ");
                    peso = pedirValidarEntero(1,3000,"Teclee peso (kg): ");
                    alturaAsiento = pedirValidarDouble(1, "Teclee altura del Asiento (cm): ");
                    tipoTransmision = pedirValidarTipoTransmision("Teclee tipo de transmisión: ");
                    int espacioBajoAsiento = pedirValidarEntero(0,300, "Teclee espacio bajo el asiento (l): ");
                    insertado = concesionario.insertarVehiculo(new MotoScooter(matricula, marca, modelo, ano, kilometraje, precio,
                            cilindrada, peso, alturaAsiento, tipoTransmision, espacioBajoAsiento));
                    break;
                case 6:
                    // Moto deportiva
                    cilindrada = pedirValidarEntero(1, 3000, "Teclee cilindrada (cc): ");
                    peso = pedirValidarEntero(1,3000,"Teclee peso (kg): ");
                    alturaAsiento = pedirValidarDouble(1, "Teclee altura del Asiento (cm): ");
                    tipoTransmision = pedirValidarTipoTransmision("Teclee tipo de transmisión: ");
                    double velocidadMaxima = pedirValidarDouble(1, "Teclee velocidad máxima (km/h): ");
                    boolean tieneQuickShifter = pedirValidarBoolean("¿Tiene quickshifter (s/n)?: ");
                    insertado = concesionario.insertarVehiculo(new MotoDeportiva(matricula, marca, modelo, ano, kilometraje, precio,
                            cilindrada, peso, alturaAsiento, tipoTransmision, velocidadMaxima,tieneQuickShifter));
                    break;
                default:
                    // Caso no contemplado
                    System.out.println("ERROR: Algo no ha debido salir del todo bien.");
                    break;
            }
        } else {
            System.out.println("ERROR: Esa matrícula ya está asignada a otro vehículo.");
        }
        if (insertado) {
            System.out.println("""
                    ****************************
                    ** NUEVO VEHÍCULO AÑADIDO **
                    ****************************
                    """);
        } else {
            System.out.println("ERROR: EL VEHÍCULO NO HA PODIDO AÑADIRSE");
        }
    }
}