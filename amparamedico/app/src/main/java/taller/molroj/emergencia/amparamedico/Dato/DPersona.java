package taller.molroj.emergencia.amparamedico.Dato;


/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class DPersona {
    private int id;
    private String nombre;
    private String matricula;
    private String ci;
    private int tipo;


    public DPersona() {
    }

    public DPersona(String nombre, String matricula, String ci, int tipo) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.ci = ci;
        this.tipo = tipo;
    }

    public DPersona(int id, String nombre, String matricula, String ci, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.ci = ci;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
}
