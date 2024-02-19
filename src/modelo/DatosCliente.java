package modelo;

public class DatosCliente {
    private String nombre;
    private int rut;

    public DatosCliente(int rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRut() {
        return rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }
}
