package vista;

import controlador.ControladorReservas;
import modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        int opcion;

        ControladorReservas controlador = new ControladorReservas(new ArrayList<>());

        // Datos de prueba
        controlador.ingresarCarpa(new Carpa(10000, 5, TipoTemporada.ALTA, new DatosCliente("Juan", 1000001), 4));
        controlador.ingresarCarpa(new Carpa(20000, 1, TipoTemporada.BAJA, new DatosCliente("Jose", 1000002), 2));
        controlador.ingresarCarpa(new Carpa(30000, 2, TipoTemporada.MEDIA, new DatosCliente("Ana", 1000003), 3));
        controlador.ingresarHotel(new Hotel(50000, 3, TipoTemporada.MEDIA, new DatosCliente("Maria", 1000004), 2, true, false));
        controlador.ingresarHotel(new Hotel(10000, 2, TipoTemporada.ALTA, new DatosCliente("Pedro", 1000005), 4, true, true));
        controlador.ingresarHotel(new Hotel(15000, 1, TipoTemporada.BAJA, new DatosCliente("Carlos", 1000006), 6, true, true));
        controlador.ingresarCabagna(new Cabagna(8000, 4, TipoTemporada.ALTA, new DatosCliente("Luis", 1000007), 5, true, true));
        controlador.ingresarCabagna(new Cabagna(12000, 2, TipoTemporada.BAJA, new DatosCliente("Lorena", 1000008), 3, false, false));
        controlador.ingresarCabagna(new Cabagna(16000, 3, TipoTemporada.MEDIA, new DatosCliente("Sofia", 1000009), 6, true, true));

        do{
            opcion = menu();
            switch (opcion){
                case 1:
                    // Datos requeridos en MedioDeAlojamiento
                    DatosCliente cliente = ingresarCliente(); // Datos del cliente
                    if(controlador.buscarCliente(cliente.getRut()) != -1){
                        System.out.println("El cliente ya se encuentra registrado.");
                        break;
                    }
                    double valorBaseNoche = ingresarValorBaseNoche();
                    int cantidadNoches = ingresarCantidadNoches();
                    TipoTemporada tipoTemporada = ingresarTipoTemporada();
                    // Datos requeridos en Alojamiento (Carpa, Hotel, Cabaña)
                    int tipoAlojamiento = SeleccionarTipoAlojamiento();
                    if ( tipoAlojamiento == 1 ){
                        // Datos requeridos en Carpa
                        int cantidadPersonas = ingresarCantidadPersonas();
                        // Crear objeto Carpa
                        Carpa carpa = new Carpa(valorBaseNoche, cantidadNoches, tipoTemporada, cliente, cantidadPersonas);
                        controlador.ingresarCarpa(carpa);
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Carpa ingresada correctamente...");
                        System.out.println("-----------------------------------------------------");
                    } else if ( tipoAlojamiento == 2 || tipoAlojamiento == 3 ) {
                        // Datos requeridos en Hospederia
                        int capacidad = ingresarCapacidad();
                        boolean fumador = ingresarFumador();
                        if(tipoAlojamiento == 2){
                            // Datos requeridos en Hotel
                            boolean desayuno = ingresarDesayuno();
                            // Crear objeto Hotel
                            Hotel hotel = new Hotel(valorBaseNoche, cantidadNoches, tipoTemporada, cliente, capacidad, fumador, desayuno);
                            controlador.ingresarHotel(hotel);
                            System.out.println("-----------------------------------------------------");
                            System.out.println("Hotel ingresado correctamente...");
                            System.out.println("-----------------------------------------------------");
                        } else {
                            // Datos requeridos en Cabaña
                            boolean chimenea = ingresarChimenea();
                            // Crear objeto Cabaña
                            Cabagna cabagna = new Cabagna(valorBaseNoche, cantidadNoches, tipoTemporada, cliente, capacidad, fumador, chimenea);
                            controlador.ingresarCabagna(cabagna);
                            System.out.println("-----------------------------------------------------");
                            System.out.println("Cabaña ingresada correctamente...");
                            System.out.println("-----------------------------------------------------");
                        }
                    }
                    break;
                case 2:
                    if (controlador.getReservas().isEmpty()){
                        System.out.println("\n-----------------------------------------------------");
                        System.out.println("No hay reservas ingresadas.");
                        System.out.println("-----------------------------------------------------");
                        break;
                    }
                    if(controlador.cantidadReservasCarpa() > 0){
                        System.out.println("\n-----------------------------------------------------");
                        System.out.println("Reservas de Carpas");
                        System.out.println("-----------------------------------------------------");
                        controlador.mostrarReservasCarpa();
                    }

                    if(controlador.cantidadReservasHotel() > 0){
                        System.out.println("\n-----------------------------------------------------");
                        System.out.println("Reservas de Hoteles");
                        System.out.println("-----------------------------------------------------");
                        controlador.mostrarReservasHotel();
                    }

                    if(controlador.cantidadReservasCabagna() > 0){
                        System.out.println("\n-----------------------------------------------------");
                        System.out.println("Reservas de Cabañas");
                        System.out.println("-----------------------------------------------------");
                        controlador.mostrarReservasCabagna();
                    }
                    break;
                case 3:
                    // TODO - Buscar datos de un cliente
                    System.out.println("Buscar datos de un cliente");
                    break;
                case 4:
                    System.out.println("\nTotal adicional: " + controlador.totalAdicional());
                    break;
                case 5:
                    System.out.println("\nTotal bono descuento: " + controlador.bonoDescuento());
                    break;
                case 6:
                    int nCarpa = controlador.cantidadReservasCarpa();
                    int nHotel = controlador.cantidadReservasHotel();
                    int nCabagna = controlador.cantidadReservasCabagna();
                    System.out.println("\nReservas de Carpas: " + nCarpa);
                    System.out.println("Reservas de Hoteles: " + nHotel);
                    System.out.println("Reservas de Cabañas: " + nCabagna);
                    System.out.println("-----------------------------------------------------");
                    System.out.println("Total de reservas: " + (nCarpa + nHotel + nCabagna));
                    break;
                case 7:
                    // TODO - Valor a cancelar por un cliente
                    System.out.println("Valor a cancelar por un cliente.");
                    break;
            }
        }while (opcion != 8);
    }

    public static int menu(){
        System.out.println("\n-------------- Reserva de Alojamiento ---------------");
        System.out.println("1.\tIngresar Medio de Alojamiento");
        System.out.println("2.\tMostrar medios de alojamiento");
        System.out.println("3.\tBuscar datos de un cliente");
        System.out.println("4.\tTotal adicional.");
        System.out.println("5.\tTotal bono descuento.");
        System.out.println("6.\tCantidad medios de alojamiento por tipo.");
        System.out.println("7.\tValor a cancelar por un cliente.");
        System.out.println("8.\tSalir.");
        System.out.println("-----------------------------------------------------");
        System.out.println("Favor ingrese una opción para continuar...");
        System.out.println("-----------------------------------------------------");
        return Leer.datoInt();
    }

    public static int SeleccionarTipoAlojamiento(){
        int opcion;
        do{
            System.out.println("\n--------- Seleccione el tipo de alojamiento ---------");
            System.out.println("1.\tCarpa");
            System.out.println("2.\tHotel");
            System.out.println("3.\tCabaña");
            System.out.println("-----------------------------------------------------");
            System.out.println("Favor ingrese una opción para continuar...");
            System.out.println("-----------------------------------------------------");
            opcion = Leer.datoInt();
        }while (opcion < 1 || opcion > 3);
        return opcion;
    }

    public static DatosCliente ingresarCliente(){
        System.out.println("\n-------------- Ingresar nuevo cliente  --------------");
        System.out.println("Ingrese el nombre del cliente: ");
        String nombre = Leer.dato();
        System.out.println("Ingrese el rut del cliente: ");
        int rut = Leer.datoInt();
        return new DatosCliente(nombre, rut);
    }

    public static double ingresarValorBaseNoche(){
        System.out.println("\nIngrese el valor base por noche: ");
        return Leer.datoDouble();
    }

    public static int ingresarCantidadNoches(){
        System.out.println("\nIngrese la cantidad de noches que se va a alojar: ");
        return Leer.datoInt();
    }

    public static TipoTemporada ingresarTipoTemporada(){
        TipoTemporada tipoTemporada = null;
        int opcion;
        do{
            System.out.println("\n----------------- Tipo de Temporada -----------------");
            System.out.println("Seleccione el tipo de temporada: ");
            System.out.println("1.\tBAJA");
            System.out.println("2.\tMEDIA");
            System.out.println("3.\tALTA");
            System.out.println("-----------------------------------------------------");
            System.out.println("Favor ingrese una opción para continuar...");
            System.out.println("-----------------------------------------------------");
            opcion = Leer.datoInt();
            if (opcion == 1){
                tipoTemporada = TipoTemporada.BAJA;
            } else if (opcion == 2){
                tipoTemporada = TipoTemporada.MEDIA;
            } else if (opcion == 3){
                tipoTemporada = TipoTemporada.ALTA;
            }
        } while (opcion < 1 || opcion > 3);
        return tipoTemporada;
    }

    public static int ingresarCantidadPersonas(){
        System.out.println("\nIngrese la cantidad de personas que se van a alojar: ");
        return Leer.datoInt();
    }

    public static int ingresarCapacidad(){
        System.out.println("\nIngrese la capacidad de personas que se van a alojar: ");
        return Leer.datoInt();
    }

    public static boolean ingresarFumador(){
        int opcion;
        do{
            System.out.println("\n------------------- ¿Es fumador? -------------------");
            System.out.println("Seleccione si el cliente es fumador: ");
            System.out.println("1.\tSI");
            System.out.println("2.\tNO");
            System.out.println("-----------------------------------------------------");
            System.out.println("Favor ingrese una opción para continuar...");
            System.out.println("-----------------------------------------------------");
            opcion = Leer.datoInt();
            if (opcion == 1){
                return true;
            }
        } while (opcion < 1 || opcion > 2);
        return false;
    }

    public static boolean ingresarDesayuno(){
        int opcion;
        do{
            System.out.println("\n------------------ ¿Desea desayuno? -----------------");
            System.out.println("Seleccione si el cliente desea desayuno: ");
            System.out.println("1.\tSI");
            System.out.println("2.\tNO");
            System.out.println("-----------------------------------------------------");
            System.out.println("Favor ingrese una opción para continuar...");
            System.out.println("-----------------------------------------------------");
            opcion = Leer.datoInt();
            if (opcion == 1){
                return true;
            }
        } while (opcion < 1 || opcion > 2);
        return false;
    }

    public static boolean ingresarChimenea(){
        int opcion;
        do{
            System.out.println("\n------------------ ¿Desea chimenea? -----------------");
            System.out.println("Seleccione si el cliente desea chimenea: ");
            System.out.println("1.\tSI");
            System.out.println("2.\tNO");
            System.out.println("-----------------------------------------------------");
            System.out.println("Favor ingrese una opción para continuar...");
            System.out.println("-----------------------------------------------------");
            opcion = Leer.datoInt();
            if (opcion == 1){
                return true;
            }
        } while (opcion < 1 || opcion > 2);
        return false;
    }

}