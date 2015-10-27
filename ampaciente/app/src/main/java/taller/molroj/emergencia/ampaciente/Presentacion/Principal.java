package taller.molroj.emergencia.ampaciente.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import taller.molroj.emergencia.ampaciente.DB.Manager;
import taller.molroj.emergencia.ampaciente.R;
import taller.molroj.emergencia.ampaciente.Servicio.Servicio;
import taller.molroj.emergencia.ampaciente.Utils.Estatica;
import taller.molroj.emergencia.ampaciente.Utils.USocket;


public class Principal extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lprincipal);
        Manager.start(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (USocket.estaDisponibleWifi(this) && !USocket.estaCorriendoMiServicio(Servicio.class, this)) {
            startService(new Intent(this, Servicio.class));
        }
    }

    public void ingresar(View view) {
        if (Estatica.me.getId() == 1) {
            startActivity(new Intent(view.getContext(), Opciones.class));
        } else {
            startActivity(new Intent(view.getContext(), Registrar.class));
        }
    }

}
