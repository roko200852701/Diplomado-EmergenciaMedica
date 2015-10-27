package taller.molroj.emergencia.ampaciente.Negocio;

import taller.molroj.emergencia.ampaciente.Dato.DPaciente;
import taller.molroj.emergencia.ampaciente.Utils.Constantes;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class NPaciente  {
    private DPaciente paciente;

    public NPaciente() {
       paciente=new DPaciente();
    }

    public void guardarPaciente(String nombre, String matricula, String ci, String telefono){
        paciente=new DPaciente(nombre,matricula,ci,telefono, Constantes.tipoPaciente);
        paciente.guardar();
    }

    public DPaciente obtenerPaciente(){
        if(paciente.getId()==0){
            DPaciente pacienteAux=paciente.obtener();
            if(pacienteAux!=null)
                return pacienteAux;
        }
        return paciente;
    }
    public DPaciente obtenerPacientePorMactricula(String sMatricula){
        if(paciente.getId()==0){
            DPaciente pacienteAux=paciente.obtenerPorMatricula(sMatricula);
            if(pacienteAux!=null)
                return pacienteAux;
        }
        return paciente;
    }
}
