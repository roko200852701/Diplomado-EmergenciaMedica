/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dato.DMedico;
import Utils.Constantes;

/**
 *
 * @author edwin molina cardena
 */
public class NMedico {
    private DMedico medico;

    public NMedico() {
        medico = new DMedico();
    }

    public boolean guardarMedico(String nombre, String matricula, String ci) {
        if (!medico.ExisteUsuario(matricula)) {
            medico.setNombre(nombre);
            medico.setMatricula(matricula);
            medico.setCi(ci);            
            medico.setTipo(Constantes.tipoParamedico);
            medico.guardar();
            return true;
        }
        return false;
    }

    public DMedico obtenerPaciente(String matricula) {
        return medico.obtener(matricula);
    }
    
    public boolean VerificarUsuario(String matricula, String ci) {
        return medico.VerificarUsuario(matricula, ci);
    }
    
}
