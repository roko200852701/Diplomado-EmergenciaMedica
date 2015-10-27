package Dato;

import Conexion.Conexion;
import Utils.Constantes;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public class DMedico extends DPersona {
    
    private DEspecialidad especialidad;

    public DEspecialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(DEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @param id
     * @param nombre
     * @param matricula
     * @param ci
     * @param tipo
     */
    public DMedico(int id, String nombre, String matricula,String ci,int tipo) {
        super(id, nombre, matricula, ci,tipo);
    }


    public DMedico() {
        super();
    }

    /**
     * @param nombre
     * @param matricula
     * @param ci
     * @param tipo
     */

    public DMedico(String nombre, String matricula, String ci, int tipo) {
        super(nombre, matricula, ci, tipo);
    }

    
    public DMedico(String nombre, String matricula, String ci, int tipo, DEspecialidad especialidad) {
        super(nombre, matricula, ci, tipo);
        this.especialidad = especialidad;
    }

    public DMedico(int id, String nombre, String matricula, String ci, int tipo, DEspecialidad especialidad) {
        super(id, nombre, matricula, ci, tipo);
        this.especialidad = especialidad;
    }
    
     public void guardar() {
        try {            
            Conexion.query = Constantes.insertInto + Constantes.tablaPersona + Constantes.parentesisIn + Constantes.cNombre + Constantes.comaSimple + Constantes.cMatricula + Constantes.comaSimple
                    + Constantes.cCi+ Constantes.comaSimple + Constantes.cTelefono + Constantes.comaSimple +Constantes.cTipo+Constantes.comaSimple + Constantes.cidEspecialidad
                    + Constantes.values + getNombre()+Constantes.coma+getMatricula() + Constantes.coma+ getCi() + Constantes.comaSimpleComilla +null+ Constantes.comaNormalComilla
                    +getTipo()+Constantes.comilla+Constantes.comaNormal+null+Constantes.parentesisfinalSinComilla;
            Conexion.abrirConexion();
            Conexion.st = Conexion.getConexion().createStatement();
            Conexion.st.executeUpdate(Conexion.query);
            Conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DMedico obtener(String matricula) {
        try {
            Conexion.query = Constantes.selectfrom + Constantes.tablaPersona + Constantes.where + Constantes.cMatricula + Constantes.igual + matricula;
            Conexion.abrirConexion();
            Conexion.st = Conexion.getConexion().createStatement();
            Conexion.rs = Conexion.st.executeQuery(Conexion.query);
            if (Conexion.rs.next()) {
                return new DMedico(Conexion.rs.getInt(Constantes.id), Conexion.rs.getString(Constantes.cNombre),
                        matricula, Conexion.rs.getString(Constantes.cCi),Conexion.rs.getInt(Constantes.cTipo));
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
