package taller.molroj.emergencia.amparamedico.Dato;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class DEspecialidad {
    private Integer id;
    private String nombre;

    public DEspecialidad(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
