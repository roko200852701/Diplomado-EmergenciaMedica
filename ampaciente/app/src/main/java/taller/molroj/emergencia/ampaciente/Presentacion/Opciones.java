package taller.molroj.emergencia.ampaciente.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import taller.molroj.emergencia.ampaciente.R;
import taller.molroj.emergencia.ampaciente.Servicio.Servicio;
import taller.molroj.emergencia.ampaciente.Utils.Constantes;
import taller.molroj.emergencia.ampaciente.Utils.Estatica;
import taller.molroj.emergencia.ampaciente.Utils.MyLocationListener;
import taller.molroj.emergencia.ampaciente.Utils.USocket;

public class Opciones extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lopciones);
        Estatica.myLocationListener = new MyLocationListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Estatica.myLocationListener.setContext(this);
        if (USocket.estaDisponibleWifi(this) && !USocket.estaCorriendoMiServicio(Servicio.class, this)) {
            startService(new Intent(this, Servicio.class));
        }
    }

    public void chatearConMedico(View view) {
        if (USocket.validarSocket(view.getContext())) {
            //startActivity(new Intent(view.getContext(), Chat_Medico.class));
            startActivity(new Intent(view.getContext(), MainActivity.class));
        } else {
            Toast.makeText(view.getContext(), R.string.verificarConexion, Toast.LENGTH_LONG).show();
        }
    }

    public void emergencia(View view) {
        if (USocket.validarSocket(view.getContext())) {
            if (Estatica.myLocationListener.verificarEstadoGPS()) {
                Estatica.myLocationListener.buscandoSenalGPS();
            }
        } else {
            Toast.makeText(view.getContext(), R.string.verificarConexion, Toast.LENGTH_LONG).show();
    }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constantes.codeGPS && Estatica.myLocationListener.verificarEstadoGPS()) {
            Estatica.myLocationListener.buscandoSenalGPS();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();

        Estatica.myLocationListener.onPause();

    }
}
