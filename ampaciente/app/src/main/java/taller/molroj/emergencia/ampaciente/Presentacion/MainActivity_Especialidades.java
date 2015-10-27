package taller.molroj.emergencia.ampaciente.Presentacion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import taller.molroj.emergencia.ampaciente.R;
import taller.molroj.emergencia.ampaciente.Utils.Estatica;

/**
 * Created by PERSONAL on 16/06/2015.
 */
public class MainActivity_Especialidades extends ActionBarActivity {
    private TextView lblEtiqueta;
    private ListView lstOpciones;

    private Titular[] datos =
            new Titular[]{
                    new Titular("Roberto Roca Romero", "55555"),
                    new Titular("Alfredo Hurtado Florez", "66666"),
                    new Titular("Ana Maria Dominguez Olmero", "77777")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidades);
        lblEtiqueta = (TextView)findViewById(R.id.LblEtiquetaMed);
        lstOpciones = (ListView)findViewById(R.id.LstOpcionesMed);

        //Cabecera
        //View header = getLayoutInflater().inflate(R.layout.list_header, null);
        //lstOpciones.addHeaderView(header);

        //Adaptador
        AdaptadorTitulares adaptador =
                new AdaptadorTitulares(this, datos);

        lstOpciones.setAdapter(adaptador);
        ShowRingDialog();

        //Eventos
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Estatica.sMatriculaMedico = ((Titular)a.getItemAtPosition(position)).getSubtitulo();
                startActivity(new Intent(v.getContext(), Chat_Medico.class));
                //Alternativa 1:
                //String opcionSeleccionada =
                //((Titular)a.getItemAtPosition(position)).getTitulo();

                //Alternativa 2:
                //String opcionSeleccionada =
                //		((TextView)v.findViewById(R.id.LblTitulo))
                //			.getText().toString();

                // lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });
    }
    private void ShowRingDialog(){
        final ProgressDialog ringProgresDialog = ProgressDialog.show(MainActivity_Especialidades.this,"Espere","Descargando Medicos...",true);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AdaptadorTitulares extends ArrayAdapter<Titular> {

        public AdaptadorTitulares(Context context, Titular[] datos) {
            super(context, R.layout.listitem_titular, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_titular, null);

            TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
            lblTitulo.setText(datos[position].getTitulo());

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
            lblSubtitulo.setText(datos[position].getSubtitulo());

            return(item);
        }
    }
}
