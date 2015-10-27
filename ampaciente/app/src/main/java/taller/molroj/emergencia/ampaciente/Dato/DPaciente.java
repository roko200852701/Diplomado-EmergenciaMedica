package taller.molroj.emergencia.ampaciente.Dato;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import taller.molroj.emergencia.ampaciente.DB.Manager;
import taller.molroj.emergencia.ampaciente.Utils.Constantes;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class DPaciente extends DPersona {
    private String telefono;

    public DPaciente() {
        super();
    }

    /**
     * @param nombre
     * @param matricula
     * @param ci
     * @param telefono
     * @param tipo
     */

    public DPaciente(String nombre, String matricula, String ci, String telefono, int tipo) {
        super(nombre, matricula, ci, tipo);
        this.telefono = telefono;
    }

    /**
     * @param id
     * @param nombre
     * @param matricula
     * @param ci
     * @param telefono
     * @param tipo
     */
    public DPaciente(int id, String nombre, String matricula, String ci, String telefono, int tipo) {
        super(id, nombre, matricula, ci, tipo);
        this.telefono = telefono;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public void guardar() {
        ContentValues valores = new ContentValues();
        valores.put(Constantes.cNombre, getNombre());
        valores.put(Constantes.cMatricula, getMatricula());
        valores.put(Constantes.cCi, getCi());
        valores.put(Constantes.cTelefono, getTelefono());
        valores.put(Constantes.cTipo, getTipo());
        Manager.abrirEscritura();
        Manager.getBaseDatos().insert(Constantes.tablaPersona, null, valores);
        Manager.close();
    }

    public DPaciente obtener() {
        Manager.abrirLectura();
        Cursor cursor = Manager.getBaseDatos().query(Constantes.tablaPersona, new String[]{
                        Constantes.id,
                        Constantes.cNombre,
                        Constantes.cMatricula,
                        Constantes.cCi,
                        Constantes.cTipo,
                        Constantes.cidEspecialidad},
                Constantes.cTipo + Constantes.igual + Constantes.comilla + Constantes.tipoPaciente + Constantes.comilla, null, null, null, null);
        DPaciente pacienteaux = null;
        if (cursor.moveToFirst()) {
            Log.w(DPaciente.class.getSimpleName(), "Paciente esta!!");
            pacienteaux = new DPaciente(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
            Log.w(DPaciente.class.getSimpleName(), "Paciente esta!! id "+String.valueOf(cursor.getInt(0)));
        }
        Manager.close();
        return pacienteaux;
    }

    public DPaciente obtenerPorMatricula(String sMatricula) {
        Manager.abrirLectura();
        Cursor cursor = Manager.getBaseDatos().query(Constantes.tablaPersona, new String[]{
                        Constantes.id,
                        Constantes.cNombre,
                        Constantes.cMatricula,
                        Constantes.cCi,
                        Constantes.cTipo,
                        Constantes.cidEspecialidad},
                Constantes.cMatricula + Constantes.igual + Constantes.comilla + sMatricula + Constantes.comilla, null, null, null, null);
        DPaciente pacienteaux = null;
        if (cursor.moveToFirst()) {
            Log.w(DPaciente.class.getSimpleName(), "Paciente esta!!");
            pacienteaux = new DPaciente(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
            Log.w(DPaciente.class.getSimpleName(), "Paciente esta!! id "+String.valueOf(cursor.getInt(0)));
        }
        Manager.close();
        return pacienteaux;
    }

}
