/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author edwin molina cardena
 */
public class Paquete {
    private String origen;
    private String destino;
    private String dato;
    private int dirigidoA;    
    private int instruccion;

    
    public Paquete(String destino, String dato, int dirigidoA, int instruccion) {
        this.destino = destino;
        this.dato = dato;
        this.dirigidoA = dirigidoA;
        this.instruccion = instruccion;
    }
    
    
    
    
    
}
