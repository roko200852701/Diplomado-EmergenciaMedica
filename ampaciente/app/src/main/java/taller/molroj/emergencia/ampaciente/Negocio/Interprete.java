package taller.molroj.emergencia.ampaciente.Negocio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.DirigidoA;
import com.Paquete;
import com.clientesocketlib.negocio.EventoClienteSocket;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import taller.molroj.emergencia.ampaciente.Dato.DMedico;
import taller.molroj.emergencia.ampaciente.Dato.DMensaje;
import taller.molroj.emergencia.ampaciente.Dato.DPaciente;
import taller.molroj.emergencia.ampaciente.Presentacion.Opciones;
import taller.molroj.emergencia.ampaciente.R;
import taller.molroj.emergencia.ampaciente.Utils.Estatica;
import taller.molroj.emergencia.ampaciente.Utils.Instruccion;
import taller.molroj.emergencia.ampaciente.Utils.Notificacion;

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
            case Instruccion.registrarPaciente:
                Estatica.paquete.setDato(Estatica.gson.toJson(Estatica.me));
                break;
            case Instruccion.enviarCordenadaPaciente:
                Estatica.paquete.setDato(Estatica.gson.toJson(Estatica.cordenada));
                break;
            case Instruccion.MENSAJEMEDICO:
                Estatica.paquete.setDato(Estatica.gson.toJson(Estatica.mensaje));
                break;
        }
        try {
            Estatica.clienteSocket.enviar(Estatica.paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void interpretar(EventoClienteSocket mensaje) {
        DPaciente paciente;
        DMensaje mensajeRecibido;
        if (mensaje.getPaquete() != null) {
            switch (mensaje.getPaquete().getComando()) {
                case Instruccion.autenticar:
                    if (Estatica.me.getId() == 1) {
                        Estatica.paquete = mensaje.getPaquete();
                        Estatica.paquete.setDato(Estatica.me.getMatricula());
                        Estatica.paquete.setComando(Instruccion.autenticarPaciente);
                        Estatica.paquete.setDirigidoA(DirigidoA.APLICACION_QUE_USA_SERVER_SOCKET);
                        try {
                            Estatica.clienteSocket.enviar(Estatica.paquete);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case Instruccion.pacienteRegistrado:
                    Estatica.gson = new Gson();
                    paciente = Estatica.gson.fromJson(mensaje.getPaquete().getDato(), DPaciente.class);
                    Estatica.nPaciente.guardarPaciente(paciente.getNombre(), paciente.getMatricula(), paciente.getCi(), paciente.getTelefono());
                    Toast.makeText(context, R.string.pacienteRegistrado,
                            Toast.LENGTH_LONG).show();
                    context.startActivity(new Intent(context, Opciones.class));
                    Estatica.me = Estatica.nPaciente.obtenerPaciente();
                    activity.finish();
                    break;
                case Instruccion.pacienteYaRegistrado:
                    Toast.makeText(context, R.string.pacienteYaRegistrado,
                            Toast.LENGTH_LONG).show();
                    break;
                case Instruccion.datosPacienteNoValidos:
                    Toast.makeText(context, R.string.datosInvalidos,
                            Toast.LENGTH_LONG).show();
                    break;
                case Instruccion.paramedicoNoDisponible:
                    Toast.makeText(context, R.string.paramedicoNoDisponible,
                            Toast.LENGTH_LONG).show();
                    break;
                case Instruccion.paramedicoEnCamino:
                    Toast.makeText(context, R.string.paramedicoEnCamino,
                            Toast.LENGTH_LONG).show();
                    break;
                case Instruccion.yaTieneEmergenciaProceso:
                    Toast.makeText(context, R.string.yaTieneEmergenciaProceso,
                            Toast.LENGTH_LONG).show();
                    break;
                case Instruccion.RECIBIRMENSAJEPACIENTE:// se recibe el mensaje del medico y se muestra en el dispositivo del paciente
                    Estatica.gson = new Gson();
                    //paciente = Estatica.gson.fromJson(mensaje.getPaquete().getDato(), DPaciente.class);
                    mensajeRecibido = Estatica.gson.fromJson(mensaje.getPaquete().getDato(), DMensaje.class);
                    DMedico NombrePaciente = new DMedico();
                    String MatriculaDoc = String.valueOf(mensajeRecibido.getIdPaciente());
                    NombrePaciente =  Estatica.nMedico.obterMedicoPorMatricula(MatriculaDoc);
                    if(Estatica.sMatriculaMedico.equals("")){
                        Estatica.sMatriculaMedico = MatriculaDoc;
                        //Guardamos el mensaje
                        Date now = new Date();
                        DateFormat df =  DateFormat.getDateInstance();
                        String sFecha =  df.format(now);//fecha actual dd/MM/yyyy
                        NMensaje datosMensaje = new NMensaje();
                        NPaciente datosPaciente= new NPaciente();
                        DPaciente dPaciente = datosPaciente.obtenerPaciente();
                        datosMensaje.guardarMensaje(1, 2, mensajeRecibido.getTexto(), sFecha, Integer.valueOf(Estatica.sMatriculaMedico), Integer.valueOf(dPaciente.getMatricula()));//origen destino sera 1 para el que envia y 2 para el que revice

                        Estatica.notificacion = new Notificacion(Estatica.context);
                        Estatica.notificacion.setNombre(NombrePaciente.getNombre());
                        Estatica.notificacion.setCodigo(Integer.parseInt(Estatica.sMatriculaMedico));
                        Estatica.notificacion.setMensajeCorto(mensajeRecibido.getTexto());
                        Estatica.notificacion.notificar();

                    }else {
                        if(Estatica.sMatriculaMedico.equals(MatriculaDoc)){
                            if(Estatica.onCreateChat == false || Estatica.onResumenChat == false ){
                                //Guardamos el mensaje
                                Date now = new Date();
                                DateFormat df =  DateFormat.getDateInstance();
                                String sFecha =  df.format(now);//fecha actual dd/MM/yyyy
                                NMensaje datosMensaje = new NMensaje();
                                NPaciente datosPaciente= new NPaciente();
                                DPaciente dPaciente = datosPaciente.obtenerPaciente();
                                datosMensaje.guardarMensaje(1, 2, mensajeRecibido.getTexto(), sFecha, Integer.valueOf(Estatica.sMatriculaMedico), Integer.valueOf(dPaciente.getMatricula()));//origen destino sera 1 para el que envia y 2 para el que revice

                                //Mostrar Notificacion
                                Estatica.notificacion = new Notificacion(Estatica.context);
                                Estatica.notificacion.setNombre(NombrePaciente.getNombre());
                                Estatica.notificacion.setCodigo(Integer.parseInt(Estatica.sMatriculaMedico));
                                Estatica.notificacion.setMensajeCorto(mensajeRecibido.getTexto());
                                Estatica.notificacion.notificar();

                            }else{
                                if(Estatica.onResumenChat == true){
                                    //Guardamos el mensaje
                                    Date now = new Date();
                                    DateFormat df =  DateFormat.getDateInstance();
                                    String sFecha =  df.format(now);//fecha actual dd/MM/yyyy
                                    NMensaje datosMensaje = new NMensaje();
                                    NPaciente datosPaciente= new NPaciente();
                                    DPaciente dPaciente = datosPaciente.obtenerPaciente();
                                    datosMensaje.guardarMensaje(1, 2, mensajeRecibido.getTexto(), sFecha, Integer.valueOf(Estatica.sMatriculaMedico), Integer.valueOf(dPaciente.getMatricula()));//origen destino sera 1 para el que envia y 2 para el que revice

                                    Estatica.listItems.add(NombrePaciente.getNombre()+ " : " + mensajeRecibido.getTexto());
                                    Estatica.adapter.notifyDataSetChanged();

                                }
                            }
                        }else{
                            if(!Estatica.sMatriculaMedico.equals(MatriculaDoc)){
                                if(Estatica.onCreateChat == false){
                                    Estatica.sMatriculaMedico = MatriculaDoc;
                                    //Guardamos el mensaje
                                    Date now = new Date();
                                    DateFormat df =  DateFormat.getDateInstance();
                                    String sFecha =  df.format(now);//fecha actual dd/MM/yyyy
                                    NMensaje datosMensaje = new NMensaje();
                                    NPaciente datosPaciente= new NPaciente();
                                    DPaciente dPaciente = datosPaciente.obtenerPaciente();
                                    datosMensaje.guardarMensaje(1, 2, mensajeRecibido.getTexto(), sFecha, Integer.valueOf(Estatica.sMatriculaMedico), Integer.valueOf(dPaciente.getMatricula()));//origen destino sera 1 para el que envia y 2 para el que revice

                                    //Mostrar Notificacion
                                    Estatica.notificacion = new Notificacion(Estatica.context);
                                    Estatica.notificacion.setNombre(NombrePaciente.getNombre());
                                    Estatica.notificacion.setCodigo(Integer.parseInt(Estatica.sMatriculaMedico));
                                    Estatica.notificacion.setMensajeCorto(mensajeRecibido.getTexto());
                                    Estatica.notificacion.notificar();

                                }else{
                                    if(Estatica.onResumenChat == false ){
                                        Estatica.sMatriculaMedico = MatriculaDoc;
                                        //Guardamos el mensaje
                                        Date now = new Date();
                                        DateFormat df =  DateFormat.getDateInstance();
                                        String sFecha =  df.format(now);//fecha actual dd/MM/yyyy
                                        NMensaje datosMensaje = new NMensaje();
                                        NPaciente datosPaciente= new NPaciente();
                                        DPaciente dPaciente = datosPaciente.obtenerPaciente();
                                        datosMensaje.guardarMensaje(1, 2, mensajeRecibido.getTexto(), sFecha, Integer.valueOf(Estatica.sMatriculaMedico), Integer.valueOf(dPaciente.getMatricula()));//origen destino sera 1 para el que envia y 2 para el que revice

                                        Estatica.listItems.add(NombrePaciente.getNombre()+ " : " + mensajeRecibido.getTexto());
                                        Estatica.adapter.notifyDataSetChanged();

                                    }else{
                                        //si esta en onResume, el medico esta conversando con el pasiente o esta leyendo lo que escribe el pasiente, solo se muestra alarma de nuevo chat
                                        Date now = new Date();
                                        DateFormat df =  DateFormat.getDateInstance();
                                        String sFecha =  df.format(now);//fecha actual dd/MM/yyyy
                                        NMensaje datosMensaje = new NMensaje();
                                        NPaciente datosPaciente= new NPaciente();
                                        DPaciente dPaciente = datosPaciente.obtenerPaciente();
                                        datosMensaje.guardarMensaje(1, 2, mensajeRecibido.getTexto(), sFecha, Integer.valueOf(MatriculaDoc), Integer.valueOf(dPaciente.getMatricula()));//origen destino sera 1 para el que envia y 2 para el que revice

                                        //Mostrar Notificacion
                                        Estatica.notificacion = new Notificacion(Estatica.context);
                                        Estatica.notificacion.setNombre(NombrePaciente.getNombre());
                                        Estatica.notificacion.setCodigo(Integer.parseInt(MatriculaDoc));
                                        Estatica.notificacion.setMensajeCorto(mensajeRecibido.getTexto());
                                        Estatica.notificacion.notificarNuevoChat();
                                    }
                                }
                            }
                        }
                    }
                    //Estatica.listItems.add(NombreMedico.getNombre()+ " : " + mensajeRecibido.getTexto());
                    //Estatica.adapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}