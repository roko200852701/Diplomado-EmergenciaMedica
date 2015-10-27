/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import sslib.negocio.HiloCliente;

/**
 *
 * @author andrea
 */
public class Paciente {
    private HiloCliente hiloCliente;  
    
    public Paciente(HiloCliente hiloCliente){
        this.hiloCliente = hiloCliente;
    }
    
    public HiloCliente getHiloCliente() {
        return hiloCliente;
    }

    public void setHiloCliente(HiloCliente hiloCliente) {
        this.hiloCliente = hiloCliente;
    }
}
