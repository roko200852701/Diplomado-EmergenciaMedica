package taller.molroj.emergencia.amparamedico.DB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public final class Manager {

    private static SQLiteDatabase baseDatos;
    private static BDHelper bdHelper;
    private static Manager manager = null;

    public static void abrirEscritura() throws SQLException {
        baseDatos = bdHelper.getWritableDatabase();
    }

    public static void abrirLectura() throws SQLException {
        baseDatos = bdHelper.getReadableDatabase();
    }

    public static void close() {
        bdHelper.close();
    }

    public static SQLiteDatabase getBaseDatos() {
        return baseDatos;
    }

    private Manager() {
    }

    private static void createInstance() {
        if (manager == null) {
            synchronized (Manager.class) {
                if (manager == null) {
                    manager = new Manager();
                }
            }
        }
    }


    public static void start(Context context) {
        if (manager == null) {
            createInstance();
            bdHelper = new BDHelper(context);
        }
    }


}
