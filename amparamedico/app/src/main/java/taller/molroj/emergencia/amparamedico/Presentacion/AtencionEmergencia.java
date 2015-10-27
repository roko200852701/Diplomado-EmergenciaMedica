package taller.molroj.emergencia.amparamedico.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import taller.molroj.emergencia.amparamedico.R;
import taller.molroj.emergencia.amparamedico.Utils.Estatica;
import taller.molroj.emergencia.amparamedico.Utils.Instruccion;
import taller.molroj.emergencia.amparamedico.Utils.USocket;

public class AtencionEmergencia extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latencionemergencia);
        Estatica.interprete.setContext(this);
        TextView matricula=(TextView)findViewById(R.id.tMatriculaPaciente);
        TextView nombre=(TextView)findViewById(R.id.tNombrePaciente);

        matricula.setText(Estatica.cordenadaPaciente.getMatricula());
        nombre.setText(Estatica.cordenadaPaciente.getNombre());
    }

    public void atenderEmergencia(View view){
        if (USocket.validarSocket(view.getContext())) {
            Estatica.interprete.interpretar(Instruccion.atenderEmergencia);
            Estatica.cordenadaPaciente=null;
            Toast.makeText(view.getContext(), getString(R.string.emergenciaAtendida), Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(view.getContext(), R.string.verificarConexion, Toast.LENGTH_LONG).show();
        }
    }


    public void mostrarRuta(View view){
        if (USocket.validarSocket(view.getContext())) {
            startActivity(new Intent(view.getContext(),Ubicar.class));
        }else{
            Toast.makeText(view.getContext(), R.string.verificarConexion, Toast.LENGTH_LONG).show();
        }
    }


}
