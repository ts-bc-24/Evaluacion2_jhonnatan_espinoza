package controlador;

import modelo.*;

import java.util.ArrayList;

public class ControladorReservas {

    // Atributos
    private ArrayList<MedioDeAlojamiento> reservas;

    /**
     * Constructor
     * @param reservas Lista de alojamientos reservados.
     */
    public ControladorReservas(ArrayList<MedioDeAlojamiento> reservas) {
        this.reservas = reservas;
    }

    // Accesores y Mutadores
    public ArrayList<MedioDeAlojamiento> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<MedioDeAlojamiento> reservas) {
        this.reservas = reservas;
    }

    // Metodos
    /**
     * Busca un cliente en la lista de reservas.
     *
     * @param rut Rut del cliente.
     * @return Posicion del cliente en la lista de reservas. Si no se encuentra, retorna -1.
     */
    public int buscarCliente(int rut){
        for (int i = 0; i < reservas.size(); i++) {
            if(reservas.get(i).getDatosCliente().getRut() == rut){
                return i;
            }
        }
        return -1;
    }

    /**
     * Agrega una reserva de tipo Carpa si el cliente no se encuentra en la lista de reservas.
     *
     * @param carpa Carpa que se va a reservar.
     */
    public void ingresarCarpa(Carpa carpa){
        if ( buscarCliente(carpa.getDatosCliente().getRut()) == -1) {
            reservas.add(carpa);
        }
    }

    /**
     * Agrega una reserva de tipo Hotel si el cliente no se encuentra en la lista de reservas.
     *
     * @param hotel Hotel que se va a reservar.
     */
    public void ingresarHotel(Hotel hotel){
        if ( buscarCliente(hotel.getDatosCliente().getRut()) == -1) {
            reservas.add(hotel);
        }
    }

    /** Agrega una reserva de tipo Cabaña si el cliente no se encuentra en la lista de reservas.
     *
     * @param cabagna Cabaña que se va a reservar.
     */
    public void ingresarCabagna(modelo.Cabagna cabagna){
        if (buscarCliente(cabagna.getDatosCliente().getRut()) == -1) {
            reservas.add(cabagna);
        }
    }

    /**
     * Muestra las reservas de tipo Carpa.
     */
    public void mostrarReservasCarpa(){
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Carpa carpa){
                System.out.println("Tipo de Alojamiento: Carpa");
                System.out.println("Valor Base Noche: " + carpa.getValorBaseNoche());
                System.out.println("Cantidad de Noches: " + carpa.getCantidadNoches());
                System.out.println("Tipo de Temporada: " + carpa.getTipoTemporada());
                System.out.println("Cliente: " + carpa.getDatosCliente().getRut() + " - " + carpa.getDatosCliente().getNombre());
                System.out.println("Cantidad de Personas: " + carpa.getCantidadPersonas());
                System.out.println("Subtotal: " + carpa.subtotal());
                System.out.println("Bono Descuento: " + carpa.bonoDescuento());
                System.out.println("Valor a Cancelar: " + carpa.valorACancelar());
                System.out.println();
            }
        }
    }

    /**
     * Muestra las reservas de tipo Hotel.
     */
    public void mostrarReservasHotel(){
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Hotel hotel){
                System.out.println("Tipo de Alojamiento: Hotel");
                System.out.println("Valor Base Noche: " + hotel.getValorBaseNoche());
                System.out.println("Cantidad de Noches: " + hotel.getCantidadNoches());
                System.out.println("Tipo de Temporada: " + hotel.getTipoTemporada());
                System.out.println("Cliente: " + hotel.getDatosCliente().getRut() + " - " + hotel.getDatosCliente().getNombre());
                System.out.println("Capacidad: " + hotel.getCapacidad());
                System.out.println("Fumador: " + (hotel.isFumador() ? "Si" : "No"));
                System.out.println("Con Desayuno: " + (hotel.isConDesayuno() ? "Si" : "No"));
                System.out.println("Subtotal: " + hotel.subtotal());
                System.out.println("Bono Descuento: " + hotel.bonoDescuento());
                System.out.println("Adicional: " + hotel.adicional());
                System.out.println("Valor a Cancelar: " + hotel.valorACancelar());
                System.out.println();
            }
        }
    }

    /**
     * Muestra las reservas de tipo Cabaña.
     */
    public void mostrarReservasCabagna(){
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Cabagna cabagna){
                System.out.println("Tipo de Alojamiento: Cabaña");
                System.out.println("Valor Base Noche: " + cabagna.getValorBaseNoche());
                System.out.println("Cantidad de Noches: " + cabagna.getCantidadNoches());
                System.out.println("Tipo de Temporada: " + cabagna.getTipoTemporada());
                System.out.println("Cliente: " + cabagna.getDatosCliente().getRut() + " - " + cabagna.getDatosCliente().getNombre());
                System.out.println("Capacidad: " + cabagna.getCapacidad());
                System.out.println("Fumador: " + (cabagna.isFumador() ? "Si" : "No"));
                System.out.println("Chimenea: " + (cabagna.isChimenea() ? "Si" : "No"));
                System.out.println("Subtotal: " + cabagna.subtotal());
                System.out.println("Bono Descuento: " + cabagna.bonoDescuento());
                System.out.println("Valor a Cancelar: " + cabagna.valorACancelar());
                System.out.println();
            }
        }
    }

    /**
     * Calcula el total adicional de todos los hoteles.
     *
     * @return Total adicional de todos los hoteles.
     */
    public double totalAdicional(){
        double total = 0;
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Hotel hotel){
                total += hotel.adicional();
            }
        }
        return total;
    }

    /**
     * Calcula el bono por temporada de todas las reservas.
     *
     * @return Bono por temporada de todas las reservas.
     */
    public double bonoDescuento(){
        double total = 0;
        for (MedioDeAlojamiento alojamiento : reservas) {
            total += alojamiento.bonoDescuento();
        }
        return total;
    }

    /**
     * Obtiene la cantidad de reservas de tipo Carpa.
     *
     * @return Cantidad de reservas de tipo Carpa.
     * */
    public int cantidadReservasCarpa(){
        int total = 0;
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Carpa){
                total++;
            }
        }
        return total;
    }

    /**
     * Obtiene la cantidad de reservas de tipo Hotel.
     *
     * @return Cantidad de reservas de tipo Hotel.
     */
    public int cantidadReservasHotel(){
        int total = 0;
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Hotel){
                total++;
            }
        }
        return total;
    }

    /**
     * Obtiene la cantidad de reservas de tipo Cabaña.
     *
     * @return Cantidad de reservas de tipo Cabaña.
     */
    public int cantidadReservasCabagna(){
        int total = 0;
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Cabagna){
                total++;
            }
        }
        return total;
    }

    /**
     * Calcula el valor a cancelar de un cliente en particular.
     *
     * @param nombre Nombre del cliente.
     * @param rut Rut del cliente.
     * @return Valor a cancelar del cliente.
     */
    public double valorACancelarCliente(String nombre, int rut){
        int posicion = buscarCliente(rut);
        if(posicion != -1){
            return reservas.get(posicion).valorACancelar();
        }
        return posicion;
    }

    /**
     * Aplica un incremento al valor base de todas las cabañas.
     */
    public void aplicarIncrementoCabagna(){
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Cabagna cabagna){
                double base = cabagna.getValorBaseNoche();
                double incremento = cabagna.incrementaValorBase();
                if (incremento > 0){
                    cabagna.setValorBaseNoche(base + incremento);
                    System.out.println("Valor Base: " + base);
                    System.out.println("Valor de incremento: " + incremento);
                    System.out.println("Nuevo valor Base: " + cabagna.getValorBaseNoche());
                    System.out.println("Capacidad: " + cabagna.getCapacidad());
                    System.out.println();
                }
            }
        }
    }

}
