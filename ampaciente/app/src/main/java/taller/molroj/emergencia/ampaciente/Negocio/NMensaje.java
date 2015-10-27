package taller.molroj.emergencia.ampaciente.Negocio;

import java.util.ArrayList;
import java.util.List;

import taller.molroj.emergencia.ampaciente.Dato.DMensaje;
import taller.molroj.emergencia.ampaciente.Utils.Constantes;

/**
 * Created by PERSONAL on 26/05/2015.
 */
public class NMensaje {
    private DMensaje mensaje;

    public NMensaje(){ mensaje = new DMensaje();}

    /*
    private int id;
    private int origenDestino;
    private String texto;
    private String fecha;
    private int idMedico;
    private int idPaciente;
    */
    public void guardarMensaje(int id, int origenDestino,String texto, String fecha,int idMedico,int idPaciente){
        mensaje = new DMensaje(id,origenDestino,texto,fecha,idMedico,idPaciente);
        mensaje.guardar();
    }

    public List<DMensaje> obtenerAllMensaje(String ciMedico,String ciPaciente){
        List<DMensaje> var =new ArrayList<DMensaje>();// null;// por si no exite nada
        List<DMensaje> mensajeAux = mensaje.obtener(ciMedico,ciPaciente);
        int size = mensajeAux.size();
        if( size >= 0) {
            return mensajeAux;
        }else{
            return var;
        }
    }
}
