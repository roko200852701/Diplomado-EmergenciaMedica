package taller.molroj.emergencia.amparamedico.Dato;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import taller.molroj.emergencia.amparamedico.DB.Manager;
import taller.molroj.emergencia.amparamedico.Utils.Constantes;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class DMedico extends DPersona {

    private DEspecialidad especialidad;

    public DEspecialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(DEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @param id
     * @param nombre
     * @param matricula
     * @param ci
     * @param tipo
     */
    public DMedico(int id, String nombre, String matricula,String ci,int tipo) {
        super(id, nombre, matricula, ci,tipo);
    }


    public DMedico() {
        super();
    }

    /**
     * @param nombre
     * @param matricula
     * @param ci
     * @param tipo
     */

    public DMedico(String nombre, String matricula, String ci, int tipo) {
        super(nombre, matricula, ci, tipo);

    }

    public DMedico(String nombre, String matricula, String ci, int tipo, DEspecialidad especialidad) {
        super(nombre, matricula, ci, tipo);
        this.especialidad = especialidad;
    }

    public DMedico(int id, String nombre, String matricula, String ci, int tipo, DEspecialidad especialidad) {
        super(id, nombre, matricula, ci, tipo);
        this.especialidad = especialidad;
    }

    public void guardar() {
        ContentValues valores = new ContentValues();
        valores.put(Constantes.cNombre, getNombre());
        valores.put(Constantes.cMatricula, getMatricula());
        valores.put(Constantes.cCi, getCi());
        valores.put(Constantes.cTipo, getTipo());
        //valores.put(Constantes.cidEspecialidad,especialidad.getId()); NO LO ESTAMOS HACIENDO POR FALTA DE TIEMPO
        Manager.abrirEscritura();
        Manager.getBaseDatos().insert(Constantes.tablaPersona, null, valores);
        Manager.close();
    }



    public DMedico obtener() {
        Manager.abrirLectura();
        Cursor cursor = Manager.getBaseDatos().query(Constantes.tablaPersona, new String[]{
                        Constantes.id,
                        Constantes.cNombre,
                        Constantes.cMatricula,
                        Constantes.cCi,
                        Constantes.cTipo,
                        Constantes.cidEspecialidad},
                Constantes.cTipo + Constantes.igual + Constantes.comilla + Constantes.tipoParamedico + Constantes.comilla, null, null, null, null);
        DMedico medicoaux = null;
        if (cursor.moveToFirst()) {
            Log.w(DMedico.class.getSimpleName(), "Medico esta!!");
            medicoaux = new DMedico(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            //POR FALTA DE TIEMPO NO ESTAMOS OBTENIENDO EL ID DE LA ESPECIALIDAD
            Log.w(DMedico.class.getSimpleName(), "Medico esta!! id "+String.valueOf(cursor.getInt(0)));
        }
        Manager.close();
        return medicoaux;
    }
}
