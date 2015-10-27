package taller.molroj.emergencia.ampaciente.Dato;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import taller.molroj.emergencia.ampaciente.DB.Manager;
import taller.molroj.emergencia.ampaciente.Negocio.NMensaje;
import taller.molroj.emergencia.ampaciente.Utils.Constantes;

/**
 * Created by edwin molina cardena on 25/04/2015.
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

    public void guardar() {
        ContentValues valores = new ContentValues();
        valores.put(Constantes.cOrigenDestino, getOrigenDestino());
        valores.put(Constantes.cTexto, getTexto());
        valores.put(Constantes.cFecha, getFecha());
        valores.put(Constantes.cidPaciente, getIdPaciente());
        valores.put(Constantes.cidMedico, getIdMedico());
        Manager.abrirEscritura();
        Manager.getBaseDatos().insert(Constantes.tablaMensaje, null, valores);
        Manager.close();
    }

    public List<DMensaje> obtener(String ciMedico,String ciPaciente) {

        String valoresA = Constantes.cidMedico + Constantes.igual + Constantes.comilla + ciMedico + Constantes.comilla;
        String valoresB = Constantes.cidPaciente + Constantes.igual + Constantes.comilla + ciPaciente + Constantes.comilla;
        String valoresAB = valoresA +" AND "+valoresB;
        Manager.abrirLectura();
        Cursor cursor = Manager.getBaseDatos().query(Constantes.tablaMensaje, new String[]{
                        Constantes.id,
                        Constantes.cOrigenDestino,
                        Constantes.cTexto,
                        Constantes.cFecha,
                        Constantes.cidPaciente,
                        Constantes.cidMedico},
                valoresAB, null, null, null, null);
        List<DMensaje> mensajeAux = new ArrayList<DMensaje>();
        if(cursor.moveToFirst()){
            do {
                // Log.w(DPaciente.class.getSimpleName(), "Mensaje esta!!");
                DMensaje dMensaje = new DMensaje(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
                mensajeAux.add(dMensaje);
                //Log.w(DPaciente.class.getSimpleName(), "Paciente esta!! id "+String.valueOf(cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        Manager.close();
        return mensajeAux;
    }
    public List<DMensaje> obtenerTodosLosMensajes() {
        Manager.abrirLectura();
        Cursor cursor = Manager.getBaseDatos().query(Constantes.tablaMensaje, new String[]{
                        Constantes.id,
                        Constantes.cOrigenDestino,
                        Constantes.cTexto,
                        Constantes.cFecha,
                        Constantes.cidPaciente,
                        Constantes.cidMedico},
                        null, null, null, null, null);
        List<DMensaje> mensajeAux = new ArrayList<DMensaje>();
        if(cursor.moveToFirst()){
            do {
                // Log.w(DPaciente.class.getSimpleName(), "Mensaje esta!!");
                DMensaje dMensaje = new DMensaje(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
                mensajeAux.add(dMensaje);
                //Log.w(DPaciente.class.getSimpleName(), "Paciente esta!! id "+String.valueOf(cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        Manager.close();
        return mensajeAux;
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
