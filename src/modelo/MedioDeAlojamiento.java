package modelo;

public abstract class MedioDeAlojamiento {

    // Atributos
    protected double valorBaseNoche;
    protected int cantidadNoches;
    protected TipoTemporada tipoTemporada;
    protected DatosCliente datosCliente;

    /**
     * Constructor
     *
     * @param valorBaseNoche Precio base por noche.
     * @param cantidadNoches Cantidad de noches que se va a alojar.
     * @param tipoTemporada Tipo de temporada en la que se encuentra el alojamiento (BAJA, MEDIA, ALTA).
     * @param datosCliente Datos del cliente que se va a alojar.
     */
    public MedioDeAlojamiento(double valorBaseNoche, int cantidadNoches, TipoTemporada tipoTemporada, DatosCliente datosCliente) {
        this.valorBaseNoche = valorBaseNoche;
        this.cantidadNoches = cantidadNoches;
        this.tipoTemporada = tipoTemporada;
        this.datosCliente = datosCliente;
    }

    // Accesores y Mutadores
    public double getValorBaseNoche() {
        return valorBaseNoche;
    }

    public int getCantidadNoches() {
        return cantidadNoches;
    }

    public TipoTemporada getTipoTemporada() {
        return tipoTemporada;
    }

    public DatosCliente getDatosCliente() {
        return datosCliente;
    }

    public void setValorBaseNoche(double valorBaseNoche) {
        this.valorBaseNoche = valorBaseNoche;
    }

    public void setCantidadNoches(int cantidadNoches) {
        this.cantidadNoches = cantidadNoches;
    }

    public void setTipoTemporada(TipoTemporada tipoTemporada) {
        this.tipoTemporada = tipoTemporada;
    }

    public void setDatosCliente(DatosCliente datosCliente) {
        this.datosCliente = datosCliente;
    }

    // Metodos
    /**
     * Devolvera el valor base por la cantidad de noches.
     *
     * @return Subtotal del alojamiento.
     */
    public double subtotal() {
        return valorBaseNoche * cantidadNoches;
    }

    /**
     * Devolvera el bono de descuento segun el tipo de temporada.
     * Temporada baja: 25% de descuento.
     * Temporada media: 12.5% de descuento.
     * Temporada alta: 0% de descuento.
     *
     * @return Bono de descuento.
     */
    public double bonoDescuento() {
        switch (tipoTemporada) {
            case BAJA:
                return subtotal() * 0.25;
            case MEDIA:
                return subtotal() * 0.125;
            case ALTA:
                return subtotal() * 0;
        }
        return 0;
    }

    // Metodos abstractos
    /**
     * Calcula el valor a cancelar por el cliente.
     * Se le descuenta el bono por temporada si es que tiene.
     * Se le suma el adicional si es que tiene.
     *
     * @return Valor a cancelar por el cliente.
     */
    public abstract double valorACancelar();

}
