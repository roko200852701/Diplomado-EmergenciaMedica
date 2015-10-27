package taller.molroj.emergencia.amparamedico.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import taller.molroj.emergencia.amparamedico.R;
import taller.molroj.emergencia.amparamedico.Servicio.Servicio;
import taller.molroj.emergencia.amparamedico.Utils.Constantes;
import taller.molroj.emergencia.amparamedico.Utils.Estatica;
import taller.molroj.emergencia.amparamedico.Utils.Instruccion;
import taller.molroj.emergencia.amparamedico.Utils.USocket;

public class Opciones extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lopciones);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (USocket.estaDisponibleWifi(this) && !USocket.estaCorriendoMiServicio(Servicio.class, this)) {
            startService(new Intent(this, Servicio.class));
        }
        Estatica.interprete.setContext(this);
    }

    public void emergenciaProceso(View view) {
        if (USocket.validarSocket(view.getContext())) {
            if (Estatica.cordenadaPaciente != null) {
                startActivity(new Intent(view.getContext(), AtencionEmergencia.class));
            } else {
                Estatica.estado=Constantes.emergenciaProceso;
                Estatica.interprete.interpretar(Instruccion.dameCordenadaPendientePaciente);
            }
        } else {
            Toast.makeText(view.getContext(), R.string.verificarConexion, Toast.LENGTH_LONG).show();
        }
    }


    public void emergencia(View view) {
        if (USocket.validarSocket(view.getContext())) {
            if (Estatica.cordenadaPaciente != null) {
                startActivity(new Intent(view.getContext(), Ubicar.class));
            } else {
                Estatica.estado=Constantes.ubicar;
                Estatica.interprete.interpretar(Instruccion.dameCordenadaPendientePaciente);
            }
        } else {
            Toast.makeText(view.getContext(), R.string.verificarConexion, Toast.LENGTH_LONG).show();
        }
    }
}
