package Negocio;


import Utils.Instruccion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sslib.eventos.MensajeEventoServidor;
import sslib.eventos.ServidorExcepcion;
import sslib.interfaz.IAppServerSocket;
import sslib.negocio.Servidor;

/**
 *
 * Created by edwin molina cardena on 25/04/2015.
 */
public class NServidorChat implements IAppServerSocket {

    private Servidor servidor;
    private Interprete interprete;
    
    

    public NServidorChat() {
        servidor = new Servidor(this, 1234);
        interprete=new Interprete();
    }    

    @Override
    public void nuevoCliente(MensajeEventoServidor mensaje) {
        servidor.eliminarClienteDeArreglo(mensaje.getHiloCliente().getIdConexion());
        interprete.interpretar(Instruccion.nuevoCliente,mensaje);        
    }

    @Override
    public void mensajeRecibido(MensajeEventoServidor mensaje) {
        interprete.interpretar(mensaje.getPaquete(),mensaje.getHiloCliente());
    }

    @Override
    public void servidorIniciado(MensajeEventoServidor mensaje) {
        System.out.println("Servidor iniciado......... OK");
    }

    @Override
    public void servidorFinalizado(MensajeEventoServidor mensaje) {
        throw new UnsupportedOperationException("Not supported yet.");
        
    }

    @Override
    public void clienteFinalizoConexion(MensajeEventoServidor mensaje) {
        String id=mensaje.getHiloCliente().getIdConexion();        
        servidor.eliminarClienteDeArreglo(id);
        interprete.interpretar(Instruccion.finalizaConexionCliente,mensaje);            
    }
   

    public void iniciarServidor() {
        try {
            servidor.iniciar();
        } catch (ServidorExcepcion ex) {
            Logger.getLogger(NServidorChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminar() {
        try {
            servidor.finalizar();
        } catch (ServidorExcepcion ex) {
            Logger.getLogger(NServidorChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
