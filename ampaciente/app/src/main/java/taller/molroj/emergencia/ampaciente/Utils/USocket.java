package taller.molroj.emergencia.ampaciente.Utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import taller.molroj.emergencia.ampaciente.R;
import taller.molroj.emergencia.ampaciente.Servicio.Servicio;

/**
 * Created by edwin molina cardena on 05/05/2015.
 */
public class USocket {

    public static boolean estaCorriendoMiServicio(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i(USocket.class.getName(), "El servicio ya esta corriendo");
                return true;
            }
        }
        Log.i(USocket.class.getName(), "El servicio no esta corriendo");
        return false;
    }


    public static boolean estaDisponibleWifi(Context context) {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean validarSocket(Context context) {
        boolean valor=false;
        if (!estaCorriendoMiServicio(Servicio.class, context) && !estaDisponibleWifi(context) && Estatica.clienteSocket == null){
            Toast.makeText(context, R.string.verificarConexion , Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
