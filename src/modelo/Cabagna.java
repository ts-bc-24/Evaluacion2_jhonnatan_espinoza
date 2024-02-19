package modelo;

public class Cabagna extends Hospederia{

    // Atributos
    protected boolean chimenea;

    /**
     * Constructor
     *
     * @param valorBaseNoche Precio base por noche.
     * @param cantidadNoches Cantidad de noches que se va a alojar.
     * @param tipoTemporada Tipo de temporada que prefiere el cliente (BAJA, MEDIA, ALTA).
     * @param datosCliente Datos del cliente que se va a alojar.
     * @param capacidad Capacidad de personas que se van a alojar.
     * @param esFumador Indica si el cliente es fumador o no.
     * @param chimenea Indica si el cliente quiere chimenea o no.
     */
    public Cabagna(double valorBaseNoche, int cantidadNoches, TipoTemporada tipoTemporada, DatosCliente datosCliente, int capacidad, boolean esFumador, boolean chimenea) {
        super(valorBaseNoche, cantidadNoches, tipoTemporada, datosCliente, capacidad, esFumador);
        this.chimenea = chimenea;
    }

    // Accesores y Mutadores

    public boolean isChimenea() {
        return chimenea;
    }

    public void setChimenea(boolean chimenea) {
        this.chimenea = chimenea;
    }

    // Metodos
    /**
     * Incrementa el valor base si la capacidad es mayor a 5.
     * Se le incrementa un 18% al valor base.
     *
     * @return Valor a incrementar al valor base.
     */
    public double incrementaValorBase(){
        if(capacidad > 5){
            return valorBaseNoche * 0.18;
        }
        return 0;
    }

    // Metodos Abstractos
    /**
     * Calcula el valor a cancelar por el cliente.
     * Se le descuenta el bono por temporada si es que tiene.
     *
     * @return Valor a cancelar por el cliente.
     */
    @Override
    public double valorACancelar() {
        return subtotal() - bonoDescuento();
    }

}
