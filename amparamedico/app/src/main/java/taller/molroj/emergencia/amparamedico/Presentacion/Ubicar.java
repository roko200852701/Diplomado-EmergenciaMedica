package taller.molroj.emergencia.amparamedico.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import taller.molroj.emergencia.amparamedico.R;
import taller.molroj.emergencia.amparamedico.Servicio.Servicio;
import taller.molroj.emergencia.amparamedico.Utils.Constantes;
import taller.molroj.emergencia.amparamedico.Utils.Estatica;
import taller.molroj.emergencia.amparamedico.Utils.Instruccion;
import taller.molroj.emergencia.amparamedico.Utils.MyLocationListener;
import taller.molroj.emergencia.amparamedico.Utils.USocket;

public class Ubicar extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lubicar);
        Estatica.myLocationListener = new MyLocationListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Estatica.myLocationListener.setContext(this);
        if (USocket.estaDisponibleWifi(this) && !USocket.estaCorriendoMiServicio(Servicio.class, this)) {
            startService(new Intent(this, Servicio.class));
        } else {
            if (USocket.validarSocket(this)) {
                if (Estatica.cordenadaPaciente != null) {
                    if (Estatica.myLocationListener.verificarEstadoGPS()) {
                        Estatica.myLocationListener.buscandoSenalGPS();
                    }
                } else {
                    Estatica.interprete.interpretar(Instruccion.dameCordenadaPendientePaciente);
                }
            } else {
                Toast.makeText(this, R.string.verificarConexion, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Estatica.myLocationListener.onPause();
    }

    /**
     * This is the fragment-orientated version of {@link #onResume()} that you
     * can override to perform operations in the Activity at the same point
     * where its fragments are resumed.  Be sure to always call through to
     * the super-class.
     */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constantes.codeGPS && Estatica.myLocationListener.verificarEstadoGPS()) {
            Estatica.myLocationListener.buscandoSenalGPS();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
