package taller.molroj.emergencia.ampaciente.DB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import taller.molroj.emergencia.ampaciente.Utils.Constantes;

public class BDHelper extends SQLiteOpenHelper {


    private static final String CREATE_TABLE_PERSONA =
            Constantes.createTable + Constantes.tablaPersona + Constantes.idIntegerPrimaryKeyAutoincrement +
                    Constantes.cNombre + Constantes.textNotNull +
                    Constantes.cMatricula + Constantes.textNotNull +
                    Constantes.cCi + Constantes.textNotNull +
                    Constantes.cTelefono + Constantes.text +
                    Constantes.cTipo+Constantes.intNotNull+
                    Constantes.cidEspecialidad + Constantes.intNull +
                    Constantes.foreignKey +Constantes.cidEspecialidad+Constantes.references+Constantes.tablaEspecialidad + Constantes.idParentesis + Constantes.onUpdateCascadeOnDeleteCascadeFinal;


    private static final String CREATE_TABLE_MENSAJE =
            Constantes.createTable + Constantes.tablaMensaje + Constantes.idIntegerPrimaryKeyAutoincrement +
                    Constantes.cOrigenDestino + Constantes.intNotNull +
                    Constantes.cTexto + Constantes.textNotNull +
                    Constantes.cFecha + Constantes.dateTimeNotNull +
                    Constantes.cidMedico + Constantes.intNotNull +
                    Constantes.cidPaciente + Constantes.intNotNull +
                    Constantes.foreignKey + Constantes.cidMedico + Constantes.references + Constantes.tablaPersona + Constantes.idParentesis + Constantes.onUpdateCascadeOnDeleteCascade +
                    Constantes.foreignKey + Constantes.cidPaciente + Constantes.references + Constantes.tablaPersona + Constantes.idParentesis + Constantes.onUpdateCascadeOnDeleteCascadeFinal;


    private static final String CREATE_TABLE_ESPECIALIDAD =
            Constantes.createTable + Constantes.tablaEspecialidad + Constantes.idIntegerPrimaryKeyAutoincrement +
                    Constantes.cNombre + Constantes.textNotNullFinal;


    private static final String CREATE_TABLE_RECETA =
            Constantes.createTable + Constantes.tablaReceta + Constantes.idIntegerPrimaryKeyAutoincrement +
                    Constantes.cNombre + Constantes.textNotNull +
                    Constantes.cFecha + Constantes.dateTimeNotNull +
                    Constantes.cEstado + Constantes.textNotNull +
                    Constantes.cidMedico + Constantes.intNotNull +
                    Constantes.cidPaciente + Constantes.intNotNull +
                    Constantes.foreignKey + Constantes.cidMedico + Constantes.references + Constantes.tablaPersona + Constantes.idParentesis + Constantes.onUpdateCascadeOnDeleteCascade +
                    Constantes.foreignKey + Constantes.cidPaciente + Constantes.references + Constantes.tablaPersona + Constantes.idParentesis + Constantes.onUpdateCascadeOnDeleteCascadeFinal;

    private static final String CREATE_TABLE_MEDICAMENTO =
            Constantes.createTable + Constantes.tablaMedicamento + Constantes.idIntegerPrimaryKeyAutoincrement +
                    Constantes.cidReceta + Constantes.intNotNull +
                    Constantes.cNombre + Constantes.textNotNull +
                    Constantes.cDescripcion + Constantes.textNotNull +
                    Constantes.cCantidad + Constantes.intNotNull +
                    Constantes.cHoraInicio + Constantes.time+
                    Constantes.cTiempo + Constantes.timeNotNull +
                    Constantes.cCantidadConsumida + Constantes.intNotNull +
                    Constantes.cidMedico + Constantes.intNotNull +
                    Constantes.cidPaciente + Constantes.intNotNull +
                    Constantes.foreignKey + Constantes.cidReceta + Constantes.references + Constantes.tablaReceta + Constantes.idParentesis + Constantes.onUpdateCascadeOnDeleteCascadeFinal;

    public static final String CREATE_TABLE_INSTITUCION =
            Constantes.createTable + Constantes.tablaInstitucion + Constantes.idIntegerPrimaryKeyAutoincrement +
                    Constantes.cNombre + Constantes.textNotNull +
                    Constantes.clatitud + Constantes.doubleNotNull +
                    Constantes.clongitud + Constantes.doubleNotNull +
                    Constantes.cTelefono + Constantes.textNotNullFinal;

    public BDHelper(Context context) {
        super(context, Constantes.bd, null, Constantes.version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_INSTITUCION);
        database.execSQL(CREATE_TABLE_ESPECIALIDAD);
        database.execSQL(CREATE_TABLE_PERSONA);
        database.execSQL(CREATE_TABLE_MENSAJE);
        database.execSQL(CREATE_TABLE_RECETA);
        database.execSQL(CREATE_TABLE_MEDICAMENTO);
        Log.w(BDHelper.class.getSimpleName(),"bd correctamente");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BDHelper.class.getSimpleName(),
                "Actualizando base de datos de la version " + oldVersion
                        + " a la " + newVersion
                        + ", que eliminara todos los datos"
        );
        db.execSQL(Constantes.dropTable + CREATE_TABLE_INSTITUCION);
        db.execSQL(Constantes.dropTable + CREATE_TABLE_ESPECIALIDAD);
        db.execSQL(Constantes.dropTable + CREATE_TABLE_PERSONA);
        db.execSQL(Constantes.dropTable + CREATE_TABLE_MENSAJE);
        db.execSQL(Constantes.dropTable + CREATE_TABLE_RECETA);
        db.execSQL(Constantes.dropTable + CREATE_TABLE_MEDICAMENTO);
        onCreate(db);
    }


}


