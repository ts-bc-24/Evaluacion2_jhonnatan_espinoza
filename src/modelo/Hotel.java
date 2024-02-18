package modelo;

public class Hotel extends Hospederia{

    // Atributos
    protected boolean conDesayuno;

    /**
     * Constructor
     *
     * @param valorBaseNoche Precio base por noche.
     * @param cantidadNoches Cantidad de noches que se va a alojar.
     * @param tipoTemporada Tipo de temporada que prefiere el cliente (BAJA, MEDIA, ALTA).
     * @param datosCliente Datos del cliente que se va a alojar.
     * @param capacidad Capacidad de personas que se van a alojar.
     * @param esFumador Indica si el cliente es fumador o no.
     * @param conDesayuno Indica si el cliente quiere desayuno o no.
     */
    public Hotel(double valorBaseNoche, int cantidadNoches, TipoTemporada tipoTemporada, DatosCliente datosCliente, int capacidad, boolean esFumador, boolean conDesayuno) {
        super(valorBaseNoche, cantidadNoches, tipoTemporada, datosCliente, capacidad, esFumador);
        this.conDesayuno = conDesayuno;
    }

    // Accesores y Mutadores
    public boolean isConDesayuno() {
        return conDesayuno;
    }

    public void setConDesayuno(boolean conDesayuno) {
        this.conDesayuno = conDesayuno;
    }

    // Metodos
    /**
     * Calcula el adicional a pagar por el cliente.
     * Si el cliente quiere desayuno y es fumador, se le aplica un 30% de recargo.
     *
     * @return Adicional a pagar por el cliente.
     */
    public double adicional(){
        if(conDesayuno && esFumador){
            return subtotal() * 0.3;
        }
        return 0;
    }

    // Metodos Abstractos
    /**
     * Calcula el valor a cancelar por el cliente.
     * Se le suma el adicional si es que tiene.
     * Se le descuenta el bono por temporada si es que tiene.
     *
     * @return Valor a cancelar por el cliente.
     */
    @Override
    public double valorACancelar() {
        return subtotal() + adicional() - bonoDescuento();
    }

}
