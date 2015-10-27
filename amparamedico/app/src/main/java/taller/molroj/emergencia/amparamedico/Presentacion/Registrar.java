package taller.molroj.emergencia.amparamedico.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import taller.molroj.emergencia.amparamedico.R;
import taller.molroj.emergencia.amparamedico.Servicio.Servicio;
import taller.molroj.emergencia.amparamedico.Utils.Constantes;
import taller.molroj.emergencia.amparamedico.Utils.Estatica;
import taller.molroj.emergencia.amparamedico.Utils.Instruccion;
import taller.molroj.emergencia.amparamedico.Utils.USocket;

public class Registrar extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lregistrar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (USocket.estaDisponibleWifi(this) && !USocket.estaCorriendoMiServicio(Servicio.class, this)) {
            startService(new Intent(this, Servicio.class));
        }
    }

    public void registrar(View view) {
        EditText matricula = (EditText) findViewById(R.id.eMatricula);
        EditText ci = (EditText) findViewById(R.id.eCi);
        if (USocket.validarSocket(this)) {
            if (matricula.getText().length() > 0 && ci.getText().length() > 0) {
                Estatica.me.setMatricula(matricula.getText().toString().trim());
                Estatica.me.setCi(ci.getText().toString().trim());
                Estatica.me.setTipo(Constantes.tipoParamedico);
                Estatica.interprete.setContext(this);
                Estatica.interprete.interpretar(Instruccion.registrarParamedico);
            } else {
                Toast.makeText(view.getContext(), R.string.llenarTodosCampos, Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(view.getContext(), R.string.verificarConexion, Toast.LENGTH_LONG).show();
        }
    }

}
