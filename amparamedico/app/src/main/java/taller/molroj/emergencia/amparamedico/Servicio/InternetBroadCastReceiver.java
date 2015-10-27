package taller.molroj.emergencia.amparamedico.Servicio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import taller.molroj.emergencia.amparamedico.Utils.USocket;


public class InternetBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION) || (intent.getAction().equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION))) {
            NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected() && !USocket.estaCorriendoMiServicio(Servicio.class, context)) {
                Log.i(USocket.class.getName(), "Se conecto el wifi");
                context.startService(new Intent(context, Servicio.class));
            } else {
                if (USocket.estaCorriendoMiServicio(Servicio.class, context)) {
                    Log.i(USocket.class.getName(), "Se desconecto el wifi");
                    context.stopService(new Intent(context, Servicio.class));
                }
            }
        }
    }

}
