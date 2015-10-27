package taller.molroj.emergencia.amparamedico.Negocio;

import taller.molroj.emergencia.amparamedico.Dato.DMedico;
import taller.molroj.emergencia.amparamedico.Utils.Constantes;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class NMedico {
    private DMedico medico;

    public NMedico() {
       medico=new DMedico();
    }

    public void guardarMedico(String nombre, String matricula, String ci){
        medico=new DMedico(nombre,matricula,ci, Constantes.tipoParamedico);
        medico.guardar();
    }

    public DMedico obterMedico(){
        if(medico.getId()==0){
            DMedico medicoaux=medico.obtener();
            if(medicoaux!=null)
                return medicoaux;
        }
        return medico;
    }
}
