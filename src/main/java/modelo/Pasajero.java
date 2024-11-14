package modelo;

public class Pasajero extends Viaje {

    private String nroBoleto;
    private String nombres;
    private int nroAsiento;
    private String tipo;
    private double pago;

    public String getNroBoleto() {
        return nroBoleto;
    }

    public void setNroBoleto(String nroBoleto) {
        this.nroBoleto = nroBoleto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getNroAsiento() {
        return nroAsiento;
    }

    public void setNroAsiento(int nroAsiento) {
        this.nroAsiento = nroAsiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

}
