package taller.molroj.emergencia.amparamedico.Utils;

/**
 * Created by edwin molina cardena on 12/05/2015.
 */
public class Cordenada {
    private String matricula;
    private String nombre;
    private String tipoSangre;
    private double latitud;
    private double longitud;

    public Cordenada(String matricula, String nombre, String tipoSangre, double latitud, double longitud) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.tipoSangre = tipoSangre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
