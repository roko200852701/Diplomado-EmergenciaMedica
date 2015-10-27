package taller.molroj.emergencia.amparamedico.Servicio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.clientesocketlib.negocio.ClienteSocket;
import com.clientesocketlib.negocio.EventoClienteSocket;
import com.clientesocketlib.negocio.IListenerClienteSocket;

import java.io.IOException;

import taller.molroj.emergencia.amparamedico.DB.Manager;
import taller.molroj.emergencia.amparamedico.Negocio.Interprete;
import taller.molroj.emergencia.amparamedico.Utils.Estatica;


/**
 * Created by edwin molina cardena on 04/11/2014.
 */
public class Servicio extends Service implements IListenerClienteSocket {

    @Override
    public void onCreate() {
        Manager.start(getApplicationContext());
        Estatica.interprete = new Interprete(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Estatica.clienteSocket = new ClienteSocket(Estatica.ip, Estatica.puerto, this);
        try {
            Estatica.clienteSocket.iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (Estatica.notificacion != null) {
            Estatica.notificacion.getNotificationManager().cancel(Integer.parseInt(Estatica.cordenadaPaciente.getMatricula()));
        }
        try {
            Estatica.clienteSocket.finalizar();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Estatica.clienteSocket = null;

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void conexionEstablecida(EventoClienteSocket eventoClienteSocket) {
        Estatica.interprete.interpretar(eventoClienteSocket);
    }

    @Override
    public void conexionFinalizada(EventoClienteSocket eventoClienteSocket) {

    }

    @Override
    public void nuevoMensaje(EventoClienteSocket eventoClienteSocket) {
        Estatica.context = getApplicationContext();
        Estatica.interprete.interpretar(eventoClienteSocket);
    }

    @Override
    public void tiempoDeIntentoDeConexionTerminado(EventoClienteSocket eventoClienteSocket) {

    }


}
