package vista;

import controlador.ControladorReservas;
import modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        int opcion;

        ControladorReservas controlador = new ControladorReservas(new ArrayList<>());

        // Datos ingresados por defecto para pruebas
        controlador.ingresarCarpa(new Carpa(10000, 5, TipoTemporada.ALTA, new DatosCliente( 1000001,"Juan"), 4));
        controlador.ingresarHotel(new Hotel(50000, 3, TipoTemporada.MEDIA, new DatosCliente(1000002,"Maria"), 2, true, false));
        controlador.ingresarHotel(new Hotel(10000, 2, TipoTemporada.ALTA, new DatosCliente(1000003,"Pedro"), 4, true, true));
        controlador.ingresarCabagna(new Cabagna(8000, 4, TipoTemporada.ALTA, new DatosCliente(1000004,"Luis"), 5, true, true));
        controlador.ingresarCabagna(new Cabagna(12000, 2, TipoTemporada.BAJA, new DatosCliente(1000005,"Lorena"), 3, false, false));
        controlador.ingresarCabagna(new Cabagna(16000, 3, TipoTemporada.MEDIA, new DatosCliente(1000006, "Sofia"), 6, true, true));

        do{
            opcion = menu(); // Mostrar menú de opciones
            switch (opcion){
                case 1: // Ingresar Medio de Alojamiento
                    // Datos requeridos en la clase MedioDeAlojamiento
                    DatosCliente cliente; // Solicitar datos del cliente
                    do{
                        System.out.println("\nNuevo cliente");
                        int rut = solicitarRUT();
                        String nombre = solicitarNombre();
                        cliente = new DatosCliente(rut, nombre);
                        if (controlador.buscarCliente(cliente.getRut()) != -1){
                            System.out.println("El cliente ya se encuentra registrado.");
                        }else {
                            System.out.println("Cliente ingresado correctamente.\n");
                            break;
                        }
                    }while(true);

                    double valorBaseNoche = solicitarValorBaseNoche(); // Solicitar valor base por noche

                    int cantidadNoches = solicitarCantidadNoches(); // Solicitar cantidad de noches

                    TipoTemporada tipoTemporada = null; // Solicitar tipo de temporada
                    int seleccionTemporada;
                    do{
                        seleccionTemporada = solicitarTemporada();
                        if (seleccionTemporada == 1){
                            tipoTemporada = TipoTemporada.BAJA;
                        } else if (seleccionTemporada == 2){
                            tipoTemporada = TipoTemporada.MEDIA;
                        } else if (seleccionTemporada == 3){
                            tipoTemporada = TipoTemporada.ALTA;
                        } else {
                            System.out.println("Opción no válida. Favor ingrese una opción válida.");
                        }
                    }while (seleccionTemporada < 1 || seleccionTemporada > 3);

                    int tipoAlojamiento = 0;
                    do{
                        tipoAlojamiento = solicitarTipoAlojamiento();
                        if ( tipoAlojamiento == 1 ){
                            // Datos requeridos en Carpa
                            int cantidadPersonas = solicitarCantidadPersonas(); // Solicitar cantidad de personas

                            // Crear objeto Carpa
                            Carpa carpa = new Carpa(valorBaseNoche, cantidadNoches, tipoTemporada, cliente, cantidadPersonas);
                            controlador.ingresarCarpa(carpa); // Agregar objeto Carpa a la lista de reservas
                            System.out.println("Reserva de Carpa ingresada correctamente.");
                            presioneEnter();
                            break;
                        } else if (tipoAlojamiento >= 2 && tipoAlojamiento <= 3) {
                            // Datos requeridos en Hospederia
                            int capacidad = solicitarCapacidad();// Solicitar capacidad de personas

                            boolean fumador = false; // Solicitar si el cliente es fumador
                            int seleccionFumador;
                            do{
                                seleccionFumador = solicitarFumador();
                                if( seleccionFumador == 1 || seleccionFumador == 2 ) {
                                    fumador = seleccionFumador == 1;
                                } else {
                                    System.out.println("Opción no válida. Favor ingrese una opción válida.");
                                }
                            }while (seleccionFumador < 1 || seleccionFumador > 2);

                            if(tipoAlojamiento == 2){
                                // Datos requeridos en Hotel
                                boolean desayuno = false; // Solicitar si el cliente desea desayuno
                                int seleccionDesayuno;
                                do{
                                    seleccionDesayuno = solicitarDesayuno();
                                    if( seleccionDesayuno == 1 || seleccionDesayuno == 2 ) {
                                        desayuno = seleccionDesayuno == 1;
                                    } else {
                                        System.out.println("Opción no válida. Favor ingrese una opción válida.");
                                    }
                                }while (seleccionDesayuno < 1 || seleccionDesayuno > 2);

                                // Crear objeto Hotel
                                Hotel hotel = new Hotel(valorBaseNoche, cantidadNoches, tipoTemporada, cliente, capacidad, fumador, desayuno);
                                controlador.ingresarHotel(hotel);
                                System.out.println("Reserva de Hotel ingresada correctamente.");
                                presioneEnter();
                                break;
                            } else {
                                // Datos requeridos en Cabaña
                                boolean chimenea = false; // Solicitar si el cliente desea chimenea
                                int seleccionChimenea;
                                do{
                                    seleccionChimenea = solicitarChimenea();
                                    if( seleccionChimenea == 1 || seleccionChimenea == 2 ) {
                                        chimenea = seleccionChimenea == 1;
                                    } else {
                                        System.out.println("Opción no válida. Favor ingrese una opción válida.");
                                    }
                                }while (seleccionChimenea < 1 || seleccionChimenea > 2);

                                // Crear objeto Cabaña
                                Cabagna cabagna = new Cabagna(valorBaseNoche, cantidadNoches, tipoTemporada, cliente, capacidad, fumador, chimenea);
                                controlador.ingresarCabagna(cabagna);
                                System.out.println("Reserva de Cabaña ingresada correctamente.");
                                presioneEnter();
                                break;
                            }
                        } else {
                            System.out.println("Opción no válida. Favor ingrese una opción válida.");
                        }
                    }while (true);
                    break;
                case 2: // Mostrar medios de alojamiento
                    // Mensaje si no hay reservas ingresadas
                    if (controlador.getReservas().isEmpty()){
                        System.out.println("\nNo hay reservas ingresadas.");
                        break;
                    }
                    // Mostrar reservas de Carpas
                    if(controlador.cantidadReservasCarpa() > 0){
                        System.out.println("\nReservas de Carpas\n---");
                        controlador.mostrarReservasCarpa();
                    }
                    // Mostrar reservas de Hoteles
                    if(controlador.cantidadReservasHotel() > 0){
                        System.out.println("\nReservas de Hoteles\n---");
                        controlador.mostrarReservasHotel();
                    }
                    // Mostrar reservas de Cabañas
                    if(controlador.cantidadReservasCabagna() > 0){
                        System.out.println("\nReservas de Cabañas\n---");
                        controlador.mostrarReservasCabagna();
                    }
                    presioneEnter();
                    break;
                case 3: // Buscar datos de un cliente
                    System.out.println("\nBuscar datos de un cliente\n---");
                    int rut = solicitarRUT();
                    int index = controlador.buscarCliente(rut);
                    if (index != -1){
                        System.out.println("\nCliente encontrado.\n");
                        System.out.println("Rut: " + rut);
                        System.out.println("Nombre: " + controlador.getReservas().get(index).getDatosCliente().getNombre());
                        System.out.println("Valor base por noche: " + controlador.getReservas().get(index).getValorBaseNoche());
                        System.out.println("Cantidad de noches: " + controlador.getReservas().get(index).getCantidadNoches());
                        System.out.println("Tipo de temporada: " + controlador.getReservas().get(index).getTipoTemporada());
                        if (controlador.getReservas().get(index) instanceof Carpa){
                            System.out.println("Tipo de alojamiento: Carpa");
                            System.out.println("Cantidad de personas: " + ((Carpa) controlador.getReservas().get(index)).getCantidadPersonas());
                        } else if (controlador.getReservas().get(index) instanceof Hotel){
                            System.out.println("Tipo de alojamiento: Hotel");
                            System.out.println("Capacidad: " + ((Hotel) controlador.getReservas().get(index)).getCapacidad());
                            System.out.println("Fumador: " + (((Hotel) controlador.getReservas().get(index)).isFumador() ? "SI" : "NO"));
                            System.out.println("Desayuno: " + (((Hotel) controlador.getReservas().get(index)).isConDesayuno() ? "SI" : "NO"));
                        } else {
                            System.out.println("Tipo de alojamiento: Cabaña");
                            System.out.println("Capacidad: " + ((Cabagna) controlador.getReservas().get(index)).getCapacidad());
                            System.out.println("Fumador: " + (((Cabagna) controlador.getReservas().get(index)).isFumador() ? "SI" : "NO"));
                            System.out.println("Chimenea: " + (((Cabagna) controlador.getReservas().get(index)).isChimenea() ? "SI" : "NO"));
                        }
                    }else {
                        System.out.println("\nCliente no encontrado.\n");
                    }
                    presioneEnter();
                    break;
                case 4: // Total adicional
                    System.out.println("\nTotal adicional: " + controlador.totalAdicional());
                    presioneEnter();
                    break;
                case 5: // Total bono descuento
                    System.out.println("\nTotal bono descuento: " + controlador.bonoDescuento());
                    presioneEnter();
                    break;
                case 6: // Cantidad medios de alojamiento por tipo
                    int nCarpa = controlador.cantidadReservasCarpa();
                    int nHotel = controlador.cantidadReservasHotel();
                    int nCabagna = controlador.cantidadReservasCabagna();
                    System.out.println("\nCantidad de reservas por tipo de alojamiento\n---");
                    System.out.println("Reservas de Carpas: " + nCarpa);
                    System.out.println("Reservas de Hoteles: " + nHotel);
                    System.out.println("Reservas de Cabañas: " + nCabagna);
                    System.out.println("Total de reservas: " + (nCarpa + nHotel + nCabagna));
                    presioneEnter();
                    break;
                case 7: // Valor a cancelar por un cliente
                    System.out.println("\nValor a cancelar por un cliente.\n---");
                    int rutACancelar = solicitarRUT();
                    int indexACancelar = controlador.buscarCliente(rutACancelar);
                    if (indexACancelar != -1){
                        System.out.println("\nCliente encontrado.\n");
                        System.out.println("Rut: " + rutACancelar);
                        System.out.println("Nombre: " + controlador.getReservas().get(indexACancelar).getDatosCliente().getNombre());
                        System.out.println("Valor a cancelar: " + controlador.getReservas().get(indexACancelar).valorACancelar());
                    }else {
                        System.out.println("\nCliente no encontrado.\n");
                    }
                    presioneEnter();
                    break;
                case 8: // Aplicar incremento del valor base
                    System.out.println("\nAplicar incremento a Cabañas con capacidad mayor a 5\n---");
                    controlador.aplicarIncrementoCabagna();
                    System.out.println("Valor base incrementado correctamente.");
                    presioneEnter();
                    break;
            }
        }while (opcion != 9);
    }

    public static void presioneEnter(){
        System.out.println("Presione Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static int menu(){
        System.out.println("\nMenú de Opciones\n---");
        System.out.println("1.\tIngresar Medio de Alojamiento");
        System.out.println("2.\tMostrar medios de alojamiento");
        System.out.println("3.\tBuscar datos de un cliente");
        System.out.println("4.\tTotal adicional.");
        System.out.println("5.\tTotal bono descuento.");
        System.out.println("6.\tCantidad medios de alojamiento por tipo.");
        System.out.println("7.\tValor a cancelar por un cliente.");
        System.out.println("8.\tAplicar incremento del valor base.");
        System.out.println("9.\tSalir.");
        System.out.print("Favor ingrese una opción para continuar: ");
        return Leer.datoInt();
    }

    public static int solicitarRUT(){
        System.out.print("1.\tIngrese el rut del cliente: ");
        return Leer.datoInt();
    }

    public static String solicitarNombre(){
        System.out.print("2.\tIngrese el nombre del cliente: ");
        return Leer.dato();
    }

    public static double solicitarValorBaseNoche(){
        System.out.print("Ingrese el valor base por noche: ");
        return Leer.datoDouble();
    }

    public static int solicitarCantidadNoches(){
        System.out.print("Ingrese la cantidad de noches que se va a alojar: ");
        return Leer.datoInt();
    }

    public static int solicitarTemporada(){
        System.out.println("\nSeleccione el tipo de temporada");
        System.out.println("1.\tBAJA");
        System.out.println("2.\tMEDIA");
        System.out.println("3.\tALTA");
        System.out.print("Favor ingrese una opción para continuar: ");
        return Leer.datoInt();
    }

    public static int solicitarTipoAlojamiento(){
        System.out.println("\nSeleccione el tipo de alojamiento");
        System.out.println("1.\tCarpa");
        System.out.println("2.\tHotel");
        System.out.println("3.\tCabaña");
        System.out.print("Favor ingrese una opción para continuar: ");
        return Leer.datoInt();
    }

    public static int solicitarCantidadPersonas(){
        System.out.print("Ingrese la cantidad de personas que se van a alojar: ");
        return Leer.datoInt();
    }

    public static int solicitarCapacidad(){
        System.out.print("Ingrese la capacidad de personas que se van a alojar: ");
        return Leer.datoInt();
    }

    public static int solicitarFumador(){
        System.out.println("\nSeleccione si el cliente es fumador");
        System.out.println("1.\tSI");
        System.out.println("2.\tNO");
        System.out.print("Favor ingrese una opción para continuar: ");
        return Leer.datoInt();
    }

    public static int solicitarDesayuno(){
        System.out.println("\nSeleccione si el cliente desea desayuno");
        System.out.println("1.\tSI");
        System.out.println("2.\tNO");
        System.out.print("Favor ingrese una opción para continuar: ");
        return Leer.datoInt();
    }

    public static int solicitarChimenea(){
        System.out.println("\nSeleccione si el cliente desea chimenea");
        System.out.println("1.\tSI");
        System.out.println("2.\tNO");
        System.out.print("Favor ingrese una opción para continuar: ");
        return Leer.datoInt();
    }

}