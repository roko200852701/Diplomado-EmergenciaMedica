package Utils;

/**
 * Created by edwin molina cardena on 26/10/2014.
 */
public final class Instruccion {

    
    //INSTRUCCIONES PARA EL PACIENTE
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
    
    
    //INSTRUCCIONES PARA EL PARAMEDICO
    public static final int autenticarParamedico=1000;
    public static final int registrarParamedico=1001;
    public static final int paramedicoRegistrado=1002;
    public static final int paramedicoYaRegistrado=1003;
    public static final int datosParamedicoNoValidos=1004;
    public static final int dameCordenadaPendientePaciente=1005;
    public static final int atenderEmergencia=1006;
    
    /////------ Registrar Paciente------/////
    public static final int AUTENTICARMEDICO = 21;
    public static final int REGISTRARMEDICO = 23;
    public static final int MEDICOREGISTRADO = 24;
    public static final int MEDICOYAREGISTRADO = 25;
    public static final int DATOSMEDICONOVALIDOS = 26;
    
    /////----- Chat's ------/////
    public static final int MENSAJEMEDICO = 90;
    public static final int RECIBIRMENSAJEMEDICO = 91;

    public static final int MENSAJEPACIENTE = 92;
    public static final int RECIBIRMENSAJEPACIENTE = 93;
           
    
    //INSTRUCCIONES PARA EL SERVIDOR
    public static final int nuevoCliente=5000;
    public static final int finalizaConexionCliente=5001;    
    public static final int autenticar=5002;
    

}
