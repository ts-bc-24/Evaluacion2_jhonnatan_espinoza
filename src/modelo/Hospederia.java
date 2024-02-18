package modelo;

public abstract class Hospederia extends MedioDeAlojamiento{

    // Atributos
    protected int capacidad;
    protected boolean esFumador;

    /**
     * Constructor
     *
     * @param valorBaseNoche Precio base por noche.
     * @param cantidadNoches Cantidad de noches que se va a alojar.
     * @param tipoTemporada Tipo de temperatura que prefiere el cliente (BAJA, MEDIA, ALTA).
     * @param datosCliente Datos del cliente que se va a alojar.
     * @param capacidad Capacidad de personas que se van a alojar.
     * @param esFumador Indica si el cliente es fumador o no.
     */
    public Hospederia(double valorBaseNoche, int cantidadNoches, TipoTemporada tipoTemporada, DatosCliente datosCliente, int capacidad, boolean esFumador) {
        super(valorBaseNoche, cantidadNoches, tipoTemporada, datosCliente);
        this.capacidad = capacidad;
        this.esFumador = esFumador;
    }

    // Accesores y Mutadores
    public int getCapacidad() {
        return capacidad;
    }

    public boolean isFumador() {
        return esFumador;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setFumador(boolean esFumador) {
        this.esFumador = esFumador;
    }

}
