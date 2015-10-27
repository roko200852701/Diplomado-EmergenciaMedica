package Utils;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public final class  Constantes {
    public static final String selectfrom = "select * from ";
    public static final String where=" where ";
    public static final String insertInto="INSERT INTO ";
    public static final String parentesisIn="(";
    public static final String parentesisOut=")";
    public static final String parentesisfinal="' )";
    public static final String parentesisfinalSinComilla=" )";
    public static final String values=") VALUES ('";
    public static final String coma="','";
    public static final String comaNormal=",";
    public static final String comaNormalComilla=",'";
    public static final String comaSimple=", ";
    public static final String comaSimpleComilla="', ";
    public static final String igual=" = ";
    public static final String id="id";
    public static final String and=" and ";
    public static final int tipoPaciente=1;
    public static final int tipoMedico=2;
    public static final int tipoMedicoPaciente=3;   
    public static final int tipoParamedico=4;
    public static final String nulo="NULL";
    public static final String comilla="'";
            
            
    public static final String tablaPersona="Persona";
    public static final String cNombre="nombre";
    public static final String cMatricula="matricula";
    public static final String cCi="ci";
    public static final String cTelefono="telefono";
    public static final String cTipo="tipo";
    public static final String cidEspecialidad="idEspecialidad";

    //Mensaje
    public static final String tablaMensaje="Mensaje";
    public static final String cOrigenDestino="origenDestino";
    public static final String cTexto="texto";
    public static final String cFecha="fecha";
    public static final String cidPaciente="idPaciente";
    public static final String cidMedico="idMedico";

    //Especialidad
    public static final String tablaEspecialidad="Especialidad";

    //Receta
    public static final String tablaReceta="Receta";
    public static final String cEstado="estado";

    //Medicamento
    public static final String tablaMedicamento="Medicamento";
    public static final String cDescripcion="descripcion";
    public static final String cCantidad="cantidad";
    public static final String cHoraInicio="horaInicio";
    public static final String cTiempo="tiempo";
    public static final String cCantidadConsumida="cantidadConsumida";
    public static final String cidReceta="idReceta";

    //Nosocomio
    public static final String tablaInstitucion="Insitucion";
    public static final String clatitud="latitud";
    public static final String clongitud="longitud";
    
    
    public static final String ip = "192.168.43.155";//"192.168.0.166";
    public static final int puerto = 1234;
}


