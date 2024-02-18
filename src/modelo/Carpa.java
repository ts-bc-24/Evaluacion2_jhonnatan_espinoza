package modelo;

public class Carpa extends MedioDeAlojamiento{

    // Atributos
    protected int cantidadPersonas;

    /**
     * Constructor
     *
     * @param valorBaseNoche Precio base por noche.
     * @param cantidadNoches Cantidad de noches que se va a alojar.
     * @param tipoTemporada Tipo de temporada que prefiere el cliente (BAJA, MEDIA, ALTA).
     * @param datosCliente Datos del cliente que se va a alojar.
     * @param cantidadPersonas Cantidad de personas que se van a alojar.
     */
    public Carpa(double valorBaseNoche, int cantidadNoches, TipoTemporada tipoTemporada, DatosCliente datosCliente, int cantidadPersonas) {
        super(valorBaseNoche, cantidadNoches, tipoTemporada, datosCliente);
        this.cantidadPersonas = cantidadPersonas;
    }

    // Accesores y Mutadores
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
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
