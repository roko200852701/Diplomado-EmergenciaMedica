package taller.molroj.emergencia.ampaciente.Dato;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class DReceta {
    private int id;
    private String nombre;
    private Date fecha;
    private String estado;
    private int idMedico;
    private int idPaciente;
    private List<DMedicamento> medicamentos;

    public DReceta() {

    }

    public DReceta(int id, String nombre, Date fecha, String estado, int idMedico, int idPaciente, DMedicamento medicamento) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.estado = estado;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.medicamentos = new ArrayList<>();
        this.medicamentos.add(medicamento);
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public DMedicamento getMedicamento(int pos) {
       return medicamentos.get(pos);
    }

    public void addMedicamentos(DMedicamento medicamento) {
        this.medicamentos.add(medicamento);
    }
}
