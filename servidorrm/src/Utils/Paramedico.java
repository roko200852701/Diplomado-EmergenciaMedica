/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import sslib.negocio.HiloCliente;

/**
 *
 * @author edwin molina cardena
 */
public class Paramedico {
    private HiloCliente hiloCliente;    
    private boolean estado;

    public Paramedico(HiloCliente hiloCliente, boolean estado) {
        this.hiloCliente = hiloCliente;
        this.estado = estado;
    }
     

    public HiloCliente getHiloCliente() {
        return hiloCliente;
    }

    public void setHiloCliente(HiloCliente hiloCliente) {
        this.hiloCliente = hiloCliente;
    }

 
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
