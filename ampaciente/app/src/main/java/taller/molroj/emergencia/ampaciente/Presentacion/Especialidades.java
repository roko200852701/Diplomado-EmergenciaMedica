package taller.molroj.emergencia.ampaciente.Presentacion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import taller.molroj.emergencia.ampaciente.R;

/**
 * Created by PERSONAL on 11/06/2015.
 */
public class Especialidades extends Activity {
    private Button notesButton;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lespecialidades);

        notesButton = (Button) findViewById(R.id.buttonMedicinaGral);
        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Medicos.class);
                startActivity(i);
            }
        });
        ShowRingDialog();

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private void ShowRingDialog(){
        final ProgressDialog ringProgresDialog = ProgressDialog.show(Especialidades.this,"Espere","Descargando...",true);
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
