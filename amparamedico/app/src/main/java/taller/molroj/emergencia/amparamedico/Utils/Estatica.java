package taller.molroj.emergencia.amparamedico.Utils;

import android.content.Context;

import com.Paquete;
import com.clientesocketlib.negocio.ClienteSocket;
import com.google.gson.Gson;

import taller.molroj.emergencia.amparamedico.Dato.DMedico;
import taller.molroj.emergencia.amparamedico.Negocio.Interprete;
import taller.molroj.emergencia.amparamedico.Negocio.NMedico;

/**
 * Created by edwin molina cardena on 26/10/2014.
 */
public final class Estatica {
    public static ClienteSocket clienteSocket;
    public static Gson gson;
    public static Paquete paquete;
    public static Interprete interprete;
    public static String ip = "192.168.1.183";
    public static int puerto = 1234;
    public static NMedico nMedico=new NMedico();
    public static DMedico me=nMedico.obterMedico();
    public static Cordenada cordenadaPaciente=null;
    public static Cordenada cordenadaParamedico=null;
    public static MyLocationListener myLocationListener=null;

    public static Context context;
    public static Notificacion notificacion;
    public static int estado;

}
