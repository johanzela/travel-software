package modelo;

public class Viaje extends Ruta {

    private String nroViaje;
    private String fecha;
    private String hora;
    private double costo;

    public String getNroViaje() {
        return nroViaje;
    }

    public void setNroViaje(String nroViaje) {
        this.nroViaje = nroViaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

}
