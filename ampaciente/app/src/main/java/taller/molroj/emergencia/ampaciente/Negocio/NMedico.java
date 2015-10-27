package taller.molroj.emergencia.ampaciente.Negocio;

import taller.molroj.emergencia.ampaciente.Dato.DMedico;
import taller.molroj.emergencia.ampaciente.Utils.Constantes;

/**
 * Created by PERSONAL on 26/05/2015.
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

    public DMedico obterMedico(String ciMedico){
        if(medico.getId()==0){
            DMedico medicoaux = medico.obtener(ciMedico);
            if(medicoaux!=null)
                return medicoaux;
        }
        return medico;
    }
    public DMedico obterMedicoPorMatricula(String sMatricula){
        if(medico.getId()==0){
            DMedico medicoaux = medico.obtenerPorMatricula(sMatricula);
            if(medicoaux!=null)
                return medicoaux;
        }
        return medico;
    }
}
