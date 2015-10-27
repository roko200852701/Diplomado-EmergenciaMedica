package Dato;

import Conexion.Conexion;
import Utils.Constantes;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class DPaciente extends DPersona {   
    private String telefono;
    public DPaciente() {
        super();
    }

    /**
     *
     * @param nombre
     * @param matricula
     * @param ci
     * @param telefono
     * @param tipo
     */

    public DPaciente(String nombre, String matricula, String ci, String telefono,int tipo) {
        super(nombre, matricula, ci, tipo);
        this.telefono = telefono;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param matricula
     * @param ci
     * @param telefono
     * @param tipo
     */
    public DPaciente(int id, String nombre, String matricula, String ci, String telefono, int tipo) {
        super(id, nombre, matricula, ci, tipo);
        this.telefono = telefono;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    public void guardar() {
        try {            
            Conexion.query = Constantes.insertInto + Constantes.tablaPersona + Constantes.parentesisIn + Constantes.cNombre + Constantes.comaSimple + Constantes.cMatricula + Constantes.comaSimple
                    + Constantes.cCi + Constantes.comaSimple + Constantes.cTelefono + Constantes.comaSimple+Constantes.cTipo+Constantes.comaSimple + Constantes.cidEspecialidad
                    + Constantes.values + getNombre()+Constantes.coma+getMatricula() + Constantes.coma+ getCi() + Constantes.coma + getTelefono() + Constantes.coma
                    +getTipo()+Constantes.comilla+Constantes.comaNormal+null+Constantes.parentesisfinalSinComilla;
            Conexion.abrirConexion();
            Conexion.st = Conexion.getConexion().createStatement();
            Conexion.st.executeUpdate(Conexion.query);
            Conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DPaciente obtener(String matricula) {
        try {
            Conexion.query = Constantes.selectfrom + Constantes.tablaPersona + Constantes.where + Constantes.cMatricula + Constantes.igual + matricula;
            Conexion.abrirConexion();
            Conexion.st = Conexion.getConexion().createStatement();
            Conexion.rs = Conexion.st.executeQuery(Conexion.query);
            if (Conexion.rs.next()) {
                return new DPaciente(Conexion.rs.getInt(Constantes.id), Conexion.rs.getString(Constantes.cNombre),
                        matricula, Conexion.rs.getString(Constantes.cTelefono), Conexion.rs.getString(Constantes.cTelefono),Conexion.rs.getInt(Constantes.cTipo));
            }
            Conexion.cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(DPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public boolean ExisteUsuario(String matricula) {
        try {
            Conexion.query = Constantes.selectfrom + Constantes.tablaPersona + Constantes.where + Constantes.cMatricula + Constantes.igual + matricula;
            Conexion.abrirConexion();
            Conexion.st = Conexion.getConexion().createStatement();
            Conexion.rs = Conexion.st.executeQuery(Conexion.query);
            if (Conexion.rs.next()) {
                return true;
            }
            Conexion.cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(DPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean VerificarUsuario(String matricula, String ci) {
        try {
            Conexion.query = Constantes.selectfrom + Constantes.tablaPersona + Constantes.where + Constantes.cMatricula + Constantes.igual + matricula+
                    Constantes.and+Constantes.cidPaciente+Constantes.igual+ci;
            Conexion.abrirConexion();
            Conexion.st = Conexion.getConexion().createStatement();
            Conexion.rs = Conexion.st.executeQuery(Conexion.query);
            if (Conexion.rs.next()) {
                return true;
            }
            Conexion.cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(DPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
