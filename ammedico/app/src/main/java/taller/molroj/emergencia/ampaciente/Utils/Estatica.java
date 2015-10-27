package taller.molroj.emergencia.ampaciente.Utils;

import android.content.Context;
import android.location.LocationManager;
import android.widget.ArrayAdapter;

import com.Paquete;
import com.clientesocketlib.negocio.ClienteSocket;
import com.google.gson.Gson;

import java.util.ArrayList;

import taller.molroj.emergencia.ampaciente.Dato.DMedico;
import taller.molroj.emergencia.ampaciente.Dato.DMensaje;
import taller.molroj.emergencia.ampaciente.Dato.DPaciente;
import taller.molroj.emergencia.ampaciente.Negocio.Interprete;
import taller.molroj.emergencia.ampaciente.Negocio.NMedico;
import taller.molroj.emergencia.ampaciente.Negocio.NMensaje;
import taller.molroj.emergencia.ampaciente.Negocio.NPaciente;

/**
 * Created by edwin molina cardena on 26/10/2014.
 */
public final class Estatica {
    public static ClienteSocket clienteSocket;
    public static Gson gson;
    public static Paquete paquete;
    public static Interprete interprete;
    public static String ip = "192.168.1.183";//"192.168.0.166";
    public static int puerto = 1234;
    public static NPaciente nPaciente=new NPaciente();
    public static DPaciente me=nPaciente.obtenerPaciente();
    public static Cordenada cordenada=null;

    public static MyLocationListener myLocationListener=null;
    public static LocationManager locationManager = null;

    //Rojo estaticas
    public static ArrayList<String> listItems = new ArrayList<String>();
    public static ArrayAdapter<String> adapter;
    public static NMedico nMedico = new NMedico();
    public static DMedico medico = new DMedico();
    public static NMensaje nMensaje = new NMensaje();
    public static DMensaje mensaje = new DMensaje();

    public static Context context;
    public static String sMatriculaMedico = "";
    public static boolean ListActivado = false;
    public static Notificacion notificacion;

    ///-------Chat--------///
    public static boolean onResumenChat = false;
    public static boolean onCreateChat = false;



}
