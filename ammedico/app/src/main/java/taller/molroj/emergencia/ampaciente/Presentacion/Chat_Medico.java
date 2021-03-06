package taller.molroj.emergencia.ampaciente.Presentacion;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import taller.molroj.emergencia.ampaciente.Dato.DMedico;
import taller.molroj.emergencia.ampaciente.Dato.DMensaje;
import taller.molroj.emergencia.ampaciente.Dato.DPaciente;
//import taller.molroj.emergencia.ampaciente.Negocio.NMedico;
//import taller.molroj.emergencia.ampaciente.Negocio.NMensaje;
import taller.molroj.emergencia.ampaciente.Negocio.NMedico;
import taller.molroj.emergencia.ampaciente.Negocio.NMensaje;
import taller.molroj.emergencia.ampaciente.Negocio.NPaciente;
import taller.molroj.emergencia.ampaciente.R;
import taller.molroj.emergencia.ampaciente.Utils.Constantes;
import taller.molroj.emergencia.ampaciente.Utils.Estatica;
import taller.molroj.emergencia.ampaciente.Utils.Instruccion;

/**
 * Created by PERSONAL on 01/06/2015.
 */
public class Chat_Medico extends ListActivity {

    //ArrayList<String> listItems = new ArrayList<String>();


    //ArrayAdapter<String> adapter;

    public NPaciente datosPaciente;
    public NMedico datosMedico;
    public NMensaje datosMensaje;

    public TextView NombreMedico;
    private TextView textView;

    int clickCounter = 0;

    private StringBuffer limpiarEditText;

    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        setContentView(R.layout.lchat);
        Estatica.adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                Estatica.listItems);
        setListAdapter(Estatica.adapter);
        NombreMedico = (TextView) findViewById(R.id.txtnombre_medico);
        textView = (TextView) findViewById(R.id.txtMatricula);

        limpiarEditText = new StringBuffer("");
        datosMedico = new NMedico();
        //***** Crear Medico ***////
        DMedico dMedico = datosMedico.obterMedico("101010");
        if (dMedico.getCi() == null){
            datosMedico.guardarMedico("Marioly Quispe Choque","101010","101010");
            //dMedico = datosMedico.obterMedico("55555");
            datosMedico.guardarMedico("Javier Vaca Quiroga","202020","202020");
            //dMedico = datosMedico.obterMedico("66666");
            datosMedico.guardarMedico("Juan Carlos Peña Paz","303030","303030");
            //dMedico = datosMedico.obterMedico("77777");
            datosMedico.guardarMedico("Alejandra Suarez Moreno","404040","404040");
            //dMedico = datosMedico.obterMedico("88888");
        }
        //********/////
        Estatica.adapter.clear();
        dMedico = datosMedico.obterMedico(Estatica.sMatriculaMedico);//Obtengo al medico que fue selecciando en el layout Medico
        NombreMedico.setText(dMedico.getNombre());
        textView.setText(dMedico.getMatricula());
        Estatica.onCreateChat = true;
        RecibirMensaje();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Estatica.onCreateChat = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        Estatica.onResumenChat = true;
    }
    @Override
    public void onPause() {
        super.onPause();
        Estatica.onResumenChat = false;
    }

    //EnviarMensajes
    public void addItems(View v){

        datosPaciente= new NPaciente();
        DPaciente dPaciente = datosPaciente.obtenerPaciente();
        datosMensaje = new NMensaje();
        TextView textView = (TextView) findViewById(R.id.edit_text_out);
        String message = textView.getText().toString();

        if(!message.equals("")){
            datosMedico = new NMedico();
            //***** Crear Medico ***////
            DMedico dMedico = datosMedico.obterMedico("101010");
            if (dMedico.getCi() == null){
                datosMedico.guardarMedico("Marioly Quispe Choque","101010","101010");
                //dMedico = datosMedico.obterMedico("55555");
                datosMedico.guardarMedico("Javier Vaca Quiroga","202020","202020");
                //dMedico = datosMedico.obterMedico("66666");
                datosMedico.guardarMedico("Juan Carlos Peña Paz","303030","303030");
                //dMedico = datosMedico.obterMedico("77777");
                datosMedico.guardarMedico("Alejandra Suarez Moreno","404040","404040");
                //dMedico = datosMedico.obterMedico("88888");
            }
            //********/////
            Date now = new Date();
            DateFormat df =  DateFormat.getDateInstance();
            String sFecha =  df.format(now);//fecha actual dd/MM/yyyy
            //datosMensaje.guardarMensaje(1, 123, message, sFecha, Integer.valueOf(dMedico.getCi()), Integer.valueOf(dPaciente.getCi()));//Guardamos el mensaje

            EnviarMensaje(message,sFecha,Estatica.sMatriculaMedico,dPaciente.getMatricula());//enviamos el mensaje

            datosMensaje.guardarMensaje(1, 1, message, sFecha, Integer.valueOf(Estatica.sMatriculaMedico), Integer.valueOf(dPaciente.getMatricula()));//origen destino sera 1 para el que envia y 2 para el que revice

            Estatica.listItems.add("Yo : " + message);
            Estatica.adapter.notifyDataSetChanged();

            limpiarEditText.setLength(0);
            textView.setText(limpiarEditText);
        }

    }
    public void RecibirMensaje(){
        datosPaciente= new NPaciente();
        DPaciente dPaciente = datosPaciente.obtenerPaciente();
        datosMensaje = new NMensaje();
        List<DMensaje> dMensaje = datosMensaje.obtenerAllMensaje(Estatica.sMatriculaMedico,dPaciente.getMatricula());
        int cant = dMensaje.size();
        DMedico dMedico = datosMedico.obterMedico(Estatica.sMatriculaMedico);
        if(cant >= 0){
            for(int x=0;x<dMensaje.size();x++) {
                String Msj = dMensaje.get(x).getTexto();
                if(dMensaje.get(x).getOrigenDestino() == 1){
                    Estatica.listItems.add("Yo : " + Msj);
                }else{
                    Estatica.listItems.add(dMedico.getNombre()+" : " + Msj);
                }
            }
        }

        Estatica.adapter.notifyDataSetChanged();
    }

    public boolean EnviarMensaje(String M,String sFecha,String sMatriculaDoc,String sMatriculaPas) {//Por el Momento se esta enviando en el mismo objeto Paciente el mensaje en el campo SetNombre
        //datosMensaje.guardarMensaje(1, 123, message, sFecha, Integer.valueOf(dMedico.getCi()), Integer.valueOf(dPaciente.getCi()));//Guardamos el mensaje
        if (M.length() > 0) {
            Estatica.mensaje.setId(1);
            Estatica.mensaje.setOrigenDestino(1);
            Estatica.mensaje.setTexto(M);
            Estatica.mensaje.setFecha(sFecha);
            Estatica.mensaje.setIdMedico(Integer.valueOf(sMatriculaDoc));
            Estatica.mensaje.setIdPaciente(Integer.valueOf(sMatriculaPas));
            //Estatica.interprete.setActivity(this);
            Estatica.interprete.setContext(this);
            Estatica.interprete.interpretar(Instruccion.MENSAJEMEDICO);
            return true;
        } else {
            return false;
        }
    }
}
