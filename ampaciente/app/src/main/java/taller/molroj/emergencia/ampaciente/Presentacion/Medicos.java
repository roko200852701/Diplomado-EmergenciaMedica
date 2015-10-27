package taller.molroj.emergencia.ampaciente.Presentacion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import taller.molroj.emergencia.ampaciente.R;
import taller.molroj.emergencia.ampaciente.Utils.Estatica;

/**
 * Created by PERSONAL on 11/06/2015.
 */
public class Medicos extends Activity {
    private Button bMedico;
    private Button bMedico2;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lmedicos);
        bMedico =(Button) findViewById(R.id.buttonRoberto);
        bMedico2 =(Button) findViewById(R.id.buttonAlfredo);
        bMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Estatica.sMatriculaMedico = "55555";
                Intent i = new Intent(getApplicationContext(),Chat_Medico.class);
                startActivity(i);
            }
        });
        bMedico2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Estatica.sMatriculaMedico = "66666";
                Intent i = new Intent(getApplicationContext(),Chat_Medico.class);
                startActivity(i);
            }
        });
        ShowRingDialog();
    }

    private void ShowRingDialog(){
        final ProgressDialog ringProgresDialog = ProgressDialog.show(Medicos.this,"Espere","Descargando...",true);
        ringProgresDialog.setCancelable(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ringProgresDialog.dismiss();
            }
        }).start();

    }
}
