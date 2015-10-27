package Negocio;

import Dato.DPaciente;
import Utils.Constantes;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class NPaciente {

    private DPaciente paciente;

    public NPaciente() {
        paciente = new DPaciente();
    }

    public boolean guardarPaciente(String nombre, String matricula, String ci, String telefono) {
        if (!paciente.ExisteUsuario(matricula)) {
            paciente.setNombre(nombre);
            paciente.setMatricula(matricula);
            paciente.setCi(ci);
            paciente.setTelefono(telefono);
            paciente.setTipo(Constantes.tipoPaciente);
            paciente.guardar();
            return true;
        }
        return false;
    }

    public DPaciente obtenerPaciente(String matricula) {
        return paciente.obtener(matricula);
    }
    
    public boolean VerificarUsuario(String matricula, String ci) {
        return paciente.VerificarUsuario(matricula, ci);
    }
}
