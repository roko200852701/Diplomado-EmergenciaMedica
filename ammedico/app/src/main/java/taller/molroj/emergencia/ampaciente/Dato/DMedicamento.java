package taller.molroj.emergencia.ampaciente.Dato;

import java.util.Date;

/**
 * @author edwin molina cardena
 * @version 1.0
 * @created 25-abr-2015 03:49:21 p.m.
 */
public class DMedicamento {

    private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private Date horaInicio;
    private Date tiempo;
    private int cantidadConsumida;


    public DMedicamento(int id, String nombre, String descripcion, int cantidad, Date horaInicio, Date tiempo, int cantidadConsumida) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.horaInicio = horaInicio;
        this.tiempo = tiempo;
        this.cantidadConsumida=cantidadConsumida;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public int getCantidadConsumida() {
        return cantidadConsumida;
    }

    public void setCantidadConsumida(int cantidadConsumida) {
        this.cantidadConsumida = cantidadConsumida;
    }
}