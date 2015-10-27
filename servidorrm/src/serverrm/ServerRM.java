/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrm;

import Conexion.Conexion;
import Negocio.NServidorChat;

/**
 *
 * @author edwin molina cardena
 */
public class ServerRM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NServidorChat chat = new NServidorChat();
        Conexion.start();
        chat.iniciarServidor();
    }

}
