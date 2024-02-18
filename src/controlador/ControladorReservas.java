package controlador;

import modelo.Carpa;
import modelo.DatosCliente;
import modelo.Hotel;
import modelo.MedioDeAlojamiento;

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
     * @param nombre Nombre del cliente.
     * @param rut Rut del cliente.
     * @return Posicion del cliente en la lista de reservas. Si no se encuentra, retorna -1.
     */
    public int buscarCliente(String nombre, int rut){
        for (int i = 0; i < reservas.size(); i++) {
            if(reservas.get(i).getDatosCliente().getNombre().equalsIgnoreCase(nombre) && reservas.get(i).getDatosCliente().getRut() == rut){
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
    public void agregarReservaCarpa(Carpa carpa){
        if ( buscarCliente(carpa.getDatosCliente().getNombre(), carpa.getDatosCliente().getRut()) == -1) {
            reservas.add(carpa);
        }
    }

    /**
     * Agrega una reserva de tipo Hotel si el cliente no se encuentra en la lista de reservas.
     *
     * @param hotel Hotel que se va a reservar.
     */
    public void agregarReservaHotel(Hotel hotel){
        if ( buscarCliente(hotel.getDatosCliente().getNombre(), hotel.getDatosCliente().getRut()) == -1) {
            reservas.add(hotel);
        }
    }

    /** Agrega una reserva de tipo Cabaña si el cliente no se encuentra en la lista de reservas.
     *
     * @param cabagna Cabaña que se va a reservar.
     */
    public void agregarReservaCabagna(modelo.Cabagna cabagna){
        if (buscarCliente(cabagna.getDatosCliente().getNombre(), cabagna.getDatosCliente().getRut()) == -1) {
            reservas.add(cabagna);
        }
    }

    /**
     * Muestra las reservas de tipo Carpa.
     */
    public void mostrarReservasCarpa(){
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Carpa){
                System.out.println(alojamiento);
            }
        }
    }

    /**
     * Muestra las reservas de tipo Hotel.
     */
    public void mostrarReservasHotel(){
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof Hotel){
                System.out.println(alojamiento);
            }
        }
    }

    /**
     * Muestra las reservas de tipo Cabaña.
     */
    public void mostrarReservasCabagna(){
        for (MedioDeAlojamiento alojamiento : reservas) {
            if(alojamiento instanceof modelo.Cabagna){
                System.out.println(alojamiento);
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
            if(alojamiento instanceof Hotel){
                total += ((Hotel) alojamiento).adicional();
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
     * Calcula el valor a cancelar de todas las reservas.
     *
     * @return Valor a cancelar de todas las reservas.
     */
    public int cantidadReservas(){
        return reservas.size();
    }

    /**
     * Calcula el valor a cancelar de un cliente en particular.
     *
     * @param nombre Nombre del cliente.
     * @param rut Rut del cliente.
     * @return Valor a cancelar del cliente.
     */
    public double valorACancelarCliente(String nombre, int rut){
        int posicion = buscarCliente(nombre, rut);
        if(posicion != -1){
            return reservas.get(posicion).valorACancelar();
        }
        return 0;
    }

}
