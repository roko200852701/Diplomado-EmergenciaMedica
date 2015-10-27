package taller.molroj.emergencia.amparamedico.Negocio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.DirigidoA;
import com.Paquete;
import com.clientesocketlib.negocio.EventoClienteSocket;
import com.google.gson.Gson;

import java.io.IOException;

import taller.molroj.emergencia.amparamedico.Dato.DMedico;
import taller.molroj.emergencia.amparamedico.Presentacion.AtencionEmergencia;
import taller.molroj.emergencia.amparamedico.Presentacion.Ubicar;
import taller.molroj.emergencia.amparamedico.R;
import taller.molroj.emergencia.amparamedico.Utils.Constantes;
import taller.molroj.emergencia.amparamedico.Utils.Cordenada;
import taller.molroj.emergencia.amparamedico.Utils.Estatica;
import taller.molroj.emergencia.amparamedico.Utils.Instruccion;
import taller.molroj.emergencia.amparamedico.Utils.Notificacion;

/**
 * Created by edwin molina cardena on 06/11/2014.
 */
public class Interprete {

    private Context context;
    private Activity activity;


   public void setContext(Context context) {
       this.context = context;
       this.activity=(Activity)context;
    }

    public Interprete(Context context) {
        this.context = context;
    }

    public void interpretar(int instruccion) {

        Estatica.paquete = new Paquete();
        Estatica.paquete.setDirigidoA(DirigidoA.APLICACION_QUE_USA_SERVER_SOCKET);
        Estatica.gson = new Gson();
        Estatica.paquete.setComando(instruccion);
        switch (instruccion) {
            case Instruccion.registrarParamedico:
                Estatica.paquete.setDato(Estatica.gson.toJson(Estatica.me));
                break;
            /*case Instruccion.dameCordenadaPendientePaciente:
                Estatica.paquete.setDato(Estatica.gson.toJson(Estatica.me.getMatricula()));
                break;
            case Instruccion.atenderEmergencia:
                 Estatica.paquete.setDato(Estatica.gson.toJson(Estatica.me.getMatricula()));
                break;*/
            default:
                Estatica.paquete.setDato(Estatica.gson.toJson(Estatica.me.getMatricula()));
                break;
        }
        try {
            Estatica.clienteSocket.enviar(Estatica.paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void interpretar(EventoClienteSocket mensaje) {
        DMedico medico;

        if (mensaje.getPaquete() != null) {

            switch (mensaje.getPaquete().getComando()) {
                case Instruccion.autenticar:
                    if (Estatica.me.getId() == 1) {
                        Estatica.paquete = mensaje.getPaquete();
                        Estatica.paquete.setDato(Estatica.me.getMatricula());
                        Estatica.paquete.setComando(Instruccion.autenticarParamedico);
                        Estatica.paquete.setDirigidoA(DirigidoA.APLICACION_QUE_USA_SERVER_SOCKET);
                        try {
                            Estatica.clienteSocket.enviar(Estatica.paquete);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case Instruccion.paramedicoRegistrado:
                    Estatica.gson = new Gson();
                    medico = Estatica.gson.fromJson(mensaje.getPaquete().getDato(), DMedico.class);
                    Estatica.nMedico.guardarMedico(medico.getNombre(), medico.getMatricula(), medico.getCi());
                    Toast.makeText(context, R.string.medicoRegistrado,
                            Toast.LENGTH_LONG).show();
                    Estatica.me = Estatica.nMedico.obterMedico();
                    activity.finish();
                    break;
                case Instruccion.paramedicoYaRegistrado:
                    Toast.makeText(context, R.string.medicoYaRegistrado,
                            Toast.LENGTH_LONG).show();
                    break;
                case Instruccion.datosParamedicoNoValidos:
                    Toast.makeText(context, R.string.datosInvalidos,
                            Toast.LENGTH_LONG).show();
                    break;
                case Instruccion.enviarCordenadaPaciente:
                    Estatica.gson = new Gson();
                    Estatica.cordenadaPaciente = Estatica.gson.fromJson(mensaje.getPaquete().getDato(), Cordenada.class);

                    Estatica.paquete.setComando(Instruccion.paramedicoEnCamino);
                    Estatica.paquete.setDirigidoA(DirigidoA.APLICACION_QUE_USA_SERVER_SOCKET);
                    Estatica.paquete.setDato(Estatica.gson.toJson(Estatica.cordenadaPaciente));
                    try {
                        Estatica.clienteSocket.enviar(Estatica.paquete);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Estatica.notificacion = new Notificacion(Estatica.context);
                    Estatica.notificacion.setNombre(Estatica.cordenadaPaciente.getNombre());
                    Estatica.notificacion.setCodigo(Integer.parseInt(Estatica.cordenadaPaciente.getMatricula()));
                    Estatica.notificacion.notificar();
                    break;
                case Instruccion.dameCordenadaPendientePaciente:
                    Estatica.gson = new Gson();
                    Estatica.cordenadaPaciente = Estatica.gson.fromJson(mensaje.getPaquete().getDato(), Cordenada.class);
                    if (Estatica.cordenadaPaciente != null) {
                        switch (Estatica.estado){
                            case Constantes.emergenciaProceso:
                                activity.startActivity(new Intent(context, AtencionEmergencia.class));
                                break;
                            case Constantes.ubicar:
                                activity.startActivity(new Intent(context, Ubicar.class));
                                break;
                        }
                    } else {
                        Toast.makeText(context,R.string.noTieneAtencionPendienteEnEstosMomentos, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }


}

