package taller.molroj.emergencia.ampaciente.Utils;

public class Instruccion {

    //variables estaticas para servidor-cliente
    public static final int autenticarPaciente=1;
    public static final int enviarMensaje=2;
    public static final int registrarPaciente=3;
    public static final int pacienteRegistrado=4;
    public static final int pacienteYaRegistrado=5;
    public static final int datosPacienteNoValidos=6;
    public static final int enviarCordenadaPaciente=7;
    public static final int paramedicoEnCamino=8;
    public static final int paramedicoNoDisponible=9;
    public static final int yaTieneEmergenciaProceso=10;

    /////----- Chat's ------/////
    public static final int MENSAJEMEDICO = 90;
    public static final int RECIBIRMENSAJEMEDICO = 91;

    public static final int MENSAJEPACIENTE = 92;
    public static final int RECIBIRMENSAJEPACIENTE = 93;

    //INSTRUCCIONES PARA EL SERVIDOR
    public static final int autenticar=5002;
}
