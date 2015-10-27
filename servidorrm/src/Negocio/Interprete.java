package Negocio;

import Dato.DMedico;
import Dato.DMensaje;
import Dato.DPaciente;
import Utils.Constantes;
import Utils.Cordenada;
import com.Paquete;
import com.ServerDirigidoA;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import sslib.eventos.MensajeEventoServidor;
import sslib.negocio.HiloCliente;
import Utils.Estatica;
import Utils.Instruccion;
import Utils.Paciente;
import Utils.Paramedico;

/**
 *
 * @author edwin molina cardena
 */
public class Interprete {

    private Hashtable<String, HiloCliente> conectadosPaciente;
    private Hashtable<String, Paramedico> conectadosParamedico;
    private Hashtable<String, Cordenada> atencionEmergenciaProceso;
    private Gson gson;
    private DPaciente paciente;
    private DMensaje dmensaje;
    private DMedico medico;
    private Cordenada cordenada;
    private HiloCliente clienteAux;

    public Interprete() {
        conectadosPaciente = new Hashtable<>();
        conectadosParamedico = new Hashtable<>();
        atencionEmergenciaProceso = new Hashtable<>();
        gson = new Gson();
    }

    public void interpretar(Paquete paquete, HiloCliente cliente) {
        String nombre;
        try {
            switch (paquete.getComando()) {
                case Instruccion.autenticarPaciente:
                    cambiarKeyPaciente(paquete.getDato(), cliente);
                case Instruccion.enviarMensaje:// enviar mensaje a edwin                    
                    //se podria mandar un comando al cliente destino para confirmar si recibio el mensaje 

                    break;
                case Instruccion.registrarPaciente:
                    paciente = gson.fromJson(paquete.getDato(), DPaciente.class);
                    paquete.setComando(Instruccion.pacienteYaRegistrado);
                    switch (Integer.parseInt(paciente.getMatricula())) {
                        case 101010:
                            nombre = "Marioly Quispe Choque";
                            break;
                        case 202020:
                            nombre = "Javier Vaca Quiroga";
                            break;
                        case 303030:
                            nombre = "Juan Carlos Peña Paz";
                            break;
                        case 404040:
                            nombre = "Alejandra Suarez Moreno";
                            break; 
                        ///--------------- Medicos -----------------///
                        case 55555:
                            nombre = "Roberto Roca Romero";
                            break;
                        case 66666:
                            nombre = "Alfredo Hurtado Florez";
                            break;
                        case 77777:
                            nombre = "Ana Maria Dominguez Olmero";
                            break;
                        case 88888:
                            nombre = "Ricardo Montoya Mustafa";
                            break;
                        ///-----------------------------------------///
                            
                        default:
                            nombre = "Rafael Rojas Encinas";
                            break;
                    }
                    paciente.setNombre(nombre);
                    paciente.setTelefono("77081231");
                    if (Estatica.nPaciente.guardarPaciente(paciente.getNombre(), paciente.getMatricula(), paciente.getCi(), paciente.getTelefono())) {
                        paquete.setComando(Instruccion.pacienteRegistrado);
                    }
                    paquete.setDato(gson.toJson(paciente, DPaciente.class));
                    break;
                case Instruccion.REGISTRARMEDICO:
                    medico = gson.fromJson(paquete.getDato(), DMedico.class);
//                    paquete.setComando(Instruccion.pacienteYaRegistrado);
                    switch (Integer.parseInt(medico.getMatricula())) {                        
                        case 55555:
                            nombre = "Roberto Roca Romero";
                            break;
                        case 66666:
                            nombre = "Alfredo Hurtado Florez";
                            break;
                        case 77777:
                            nombre = "Ana Maria Dominguez Olmero";
                            break;
                        case 88888:
                            nombre = "Ricardo Montoya Mustafa";
                            break;
                        default:
                            nombre = "Ricardo Rojo Ortiz";
                            break;
                    }
                    medico.setNombre(nombre);
                    //medico.setTelefono("77081231");
                    if (Estatica.nMedico.guardarMedico(medico.getNombre(), medico.getMatricula(), medico.getCi())) {
                        paquete.setComando(Instruccion.pacienteRegistrado);
                    }
                    paquete.setDato(gson.toJson(medico, DMedico.class));
                    break;
                case Instruccion.autenticarParamedico:
                    cambiarKeyParamedico(paquete.getDato(), new Paramedico(cliente, false));
                    break;
                case Instruccion.registrarParamedico:
                    medico = gson.fromJson(paquete.getDato(), DMedico.class);
                    paquete.setComando(Instruccion.paramedicoYaRegistrado);
                    switch (Integer.parseInt(medico.getMatricula())) {
                        case 505050:
                            nombre = "Javier Segundo Cabrera";
                            break;
                        case 606060:
                            nombre = "Nelson Martinez Aguirre";
                            break;
                        case 707070:
                            nombre = "Silvio Jimenez Antezana";
                            break;
                        case 808080:
                            nombre = "Juan Ramos Terrazas";
                            break;
                        default:
                            nombre = "Carlos Eduardo Apaza Gutierrez";
                            break;
                    }
                    medico.setNombre(nombre);
                    if (Estatica.nMedico.guardarMedico(medico.getNombre(), medico.getMatricula(), medico.getCi())) {
                        paquete.setComando(Instruccion.paramedicoRegistrado);
                        cambiarKeyParamedico(medico.getMatricula(), new Paramedico(cliente, false));
                    }
                    paquete.setDato(gson.toJson(medico, DMedico.class));
                    break;
                case Instruccion.enviarCordenadaPaciente:
                    cordenada = gson.fromJson(paquete.getDato(), Cordenada.class);
                    paquete.setComando(Instruccion.yaTieneEmergenciaProceso);
                    if (!verificarEmergenciaSolicitada(cordenada.getMatricula())) {
                        clienteAux = buscarParamedicoDisponible(cordenada);
                        paquete.setComando(Instruccion.paramedicoNoDisponible);
                        if (clienteAux != null) {
                            cliente = clienteAux;
                            paquete.setComando(Instruccion.enviarCordenadaPaciente);
                        }
                    }
                    break;
                case Instruccion.paramedicoEnCamino:
                    cordenada = gson.fromJson(paquete.getDato(), Cordenada.class);
                    clienteAux = conectadosPaciente.get(cordenada.getMatricula());
                    if (clienteAux != null) {
                        cliente = clienteAux;
                    }
                    break;
                case Instruccion.dameCordenadaPendientePaciente:
                    paquete.setDato(gson.toJson(atencionEmergenciaProceso.get(paquete.getDato().substring(1, paquete.getDato().length() -1)), Cordenada.class));
                    break;
                case Instruccion.atenderEmergencia:
                    liberarAtencionParamedico(paquete.getDato().substring(1, paquete.getDato().length() -1));
                    break;
                    //RIcardo Rojo
                case Instruccion.MENSAJEMEDICO:
                    //paciente = gson.fromJson(paquete.getDato(), DPaciente.class);
                    dmensaje = gson.fromJson(paquete.getDato(), DMensaje.class);
                    paquete.setComando(Instruccion.RECIBIRMENSAJEPACIENTE);//enviar mensaje al medico
                    ///AQUI COLOCAR ACIA QUIEN VA DIRIGIDO paquete.setOrigen("???????");
                    //clienteAux = BuscarMedicoConectado(paquete.getOrigen(),"");
                    clienteAux = BuscarMedicoConectado(String.valueOf(dmensaje.getIdPaciente()),
                                                       String.valueOf(dmensaje.getIdMedico()));
                    cliente = clienteAux;
                    paquete.setComando(Instruccion.RECIBIRMENSAJEPACIENTE);//enviar mensaje al medico
                    //paquete.setComando(Instruccion.enviarCordenadaPaciente);
                    ////
                    int jjj = conectadosPaciente.size();
                    System.out.println("Pacientes Conectados :  " + jjj);
                    System.out.println("Paramedicos Conectados :  " + conectadosParamedico.size());
                    
                    //paquete.setOrigen("202020");///--------------------------------------------------------
                    //String mensaje = paciente.getNombre();                    
                    //paciente.setNombre("Ricardito");
                    //paciente.setTelefono("77081231");
                    paquete.setDato(gson.toJson(dmensaje, DMensaje.class));
                    break;
                
            }
            paquete.setDirigidoA(ServerDirigidoA.APLICACION_QUE_USA_CLIENTE_SOCKET);
            if (!validarInstruccion(paquete.getComando())) {
                cliente.enviar(paquete);
            }

        } catch (IOException ex) {
            Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void interpretar(int instruccion, MensajeEventoServidor mensaje) {
        Paquete paquete;
        switch (instruccion) {
            case Instruccion.nuevoCliente:
                paquete = new Paquete();
                paquete.setDirigidoA(ServerDirigidoA.APLICACION_QUE_USA_CLIENTE_SOCKET);
                paquete.setComando(Instruccion.autenticar);
                mensaje.setPaquete(paquete);
                try {
                    mensaje.getHiloCliente().enviar(mensaje.getPaquete());
                } catch (IOException ex) {
                    Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case Instruccion.finalizaConexionCliente:
                conectadosPaciente.remove(mensaje.getHiloCliente().getIdConexion());
                conectadosParamedico.remove(mensaje.getHiloCliente().getIdConexion());
                break;
        }
    }

    private void liberarAtencionParamedico(String matriculaParamedico) {
        atencionEmergenciaProceso.remove(matriculaParamedico);
        conectadosParamedico.get(matriculaParamedico).setEstado(false);
    }

    private void cambiarKeyParamedico(String nick, Paramedico paramedico) {
        if (conectadosParamedico.get(nick) != null) {
            if (atencionEmergenciaProceso.get(nick) != null) {
                paramedico.setEstado(true);
                /*paquete.setComando(Instruccion.enviarCordenadaPaciente);
                paquete.setDato(gson.toJson(atencionEmergenciaProceso.get(nick), Cordenada.class));*/
            }
            conectadosParamedico.remove(nick);
        }
        conectadosParamedico.put(nick, paramedico);
        paramedico.getHiloCliente().setIdConexion(nick);
    }

    private void cambiarKeyPaciente(String nick, HiloCliente paciente) {
        if (conectadosPaciente.get(nick) != null) {
            conectadosPaciente.remove(nick);
        }
        conectadosPaciente.put(nick, paciente);
        paciente.setIdConexion(nick);
    }

    private boolean validarInstruccion(int instruccion) {
        return Instruccion.autenticar == instruccion || Instruccion.autenticarParamedico == instruccion;
    }

    private HiloCliente buscarParamedicoDisponible(Cordenada cordenada) {
        Enumeration list = conectadosParamedico.elements();
        while (list.hasMoreElements()) {
            Paramedico paramedico = (Paramedico) list.nextElement();
            if (!paramedico.isEstado()) {
                paramedico.setEstado(true);
                atencionEmergenciaProceso.put(paramedico.getHiloCliente().getIdConexion(), cordenada);
                return paramedico.getHiloCliente();
            }
        }
        return null;
    }
    
    private HiloCliente BuscarMedicoConectado(String sOrigen, String sDestino) {
        /*
        private Hashtable<String, HiloCliente> conectadosPaciente;
        private Hashtable<String, Paramedico> conectadosParamedico;
        private Hashtable<String, Cordenada> atencionEmergenciaProceso;
        */
        Enumeration list = conectadosParamedico.elements();
        Enumeration listPaciente = conectadosPaciente.elements();
        int numConectados = conectadosPaciente.size();
        if(numConectados > 0){
        while (listPaciente.hasMoreElements()) {
            HiloCliente pacienteR = (HiloCliente) listPaciente.nextElement();
           if (pacienteR.getIdConexion().equals(sDestino)) {
               // paramedico.setEstado(true);
                //atencionEmergenciaProceso.put(paramedico.getHiloCliente().getIdConexion(), cordenada);
                return pacienteR;
           }
        }
        }
        return null;
        
    }

    private boolean verificarEmergenciaSolicitada(String matriculaPaciente) {
        Enumeration list;
        list = atencionEmergenciaProceso.elements();
        while (list.hasMoreElements()) {
            Cordenada cordenadaAux = (Cordenada) list.nextElement();
            if (cordenadaAux.getMatricula().equals(matriculaPaciente)) {
                return true;
            }
        }
        return false;
    }
}
