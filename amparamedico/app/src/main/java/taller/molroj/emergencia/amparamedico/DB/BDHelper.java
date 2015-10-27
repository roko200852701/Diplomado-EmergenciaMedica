package taller.molroj.emergencia.amparamedico.DB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import taller.molroj.emergencia.amparamedico.Utils.Constantes;

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



    private static final String CREATE_TABLE_ESPECIALIDAD =
            Constantes.createTable + Constantes.tablaEspecialidad + Constantes.idIntegerPrimaryKeyAutoincrement +
                    Constantes.cNombre + Constantes.textNotNullFinal;



    public BDHelper(Context context) {
        super(context, Constantes.bd, null, Constantes.version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_ESPECIALIDAD);
        database.execSQL(CREATE_TABLE_PERSONA);
        Log.w(BDHelper.class.getSimpleName(),"bd correctamente");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BDHelper.class.getSimpleName(),
                "Actualizando base de datos de la version " + oldVersion
                        + " a la " + newVersion
                        + ", que eliminara todos los datos"
        );

        db.execSQL(Constantes.dropTable + CREATE_TABLE_ESPECIALIDAD);
        db.execSQL(Constantes.dropTable + CREATE_TABLE_PERSONA);
        onCreate(db);
    }


}


