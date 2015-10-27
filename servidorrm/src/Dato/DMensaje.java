/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dato;

/**
 *
 * @author andrea
 */
public class DMensaje {
    private int id;
    private int origenDestino;
    private String texto;
    private String fecha;
    private int idMedico;
    private int idPaciente;
    
    public DMensaje(){ }

    public DMensaje(int id, int origenDestino, String texto, String fecha, int idMedico, int idPaciente) {
        this.id = id;
        this.origenDestino = origenDestino;
        this.texto = texto;
        this.fecha = fecha;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrigenDestino() {
        return origenDestino;
    }

    public void setOrigenDestino(int origenDestino) {
        this.origenDestino = origenDestino;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
    
}
