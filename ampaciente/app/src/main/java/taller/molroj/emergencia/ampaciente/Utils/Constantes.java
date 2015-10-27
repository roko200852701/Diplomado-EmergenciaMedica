package taller.molroj.emergencia.ampaciente.Utils;

/**
 * Created by edwin molina cardena on 25/04/2015.
 */
public final class Constantes {
    public static final String bd = "paciente.sqlite";
    public static final int version = 1;
    public static final String id="_id";
    public static final String createTable="create table ";
    public static final String primaryKey=" id integer primary key, ";
    public static final String textNotNull=" TEXT NOT NULL, ";
    public static final String idIntegerPrimaryKeyAutoincrement="(_id integer primary key AUTOINCREMENT,";
    public static final String foreignKeyReferences ="FOREIGN KEY (_id) REFERENCES ";
    public static final String onUpdateCascadeOnDeleteCascade =" ON UPDATE CASCADE ON DELETE CASCADE, ";
    public static final String idParentesis="(_id)";
    public static final String onUpdateCascadeOnDeleteCascadeFinal=" ON UPDATE CASCADE ON DELETE CASCADE);";
    public static final String intNotNullFinal=" INT NOT NULL); ";
    public static final String intNotNull=" INT NOT NULL, ";
    public static final String intNull=" INT, ";
    public static final String textNotNullFinal=" TEXT NOT NULL); ";
    public static final String dateTimeNotNull=" DATETIME NOT NULL, ";
    public static final String foreignKey="FOREIGN KEY (";
    public static final String references=") REFERENCES ";
    public static final String time=" TIME, ";
    public static final String timeNotNull=" TIME NOT NULL, ";
    public static final String dropTable="DROP TABLE IF EXISTS ";
    public static final String doubleNotNull="DOUBLE NOT NULL, ";
    public static final String is=" is ";
    public static String nulo="NULL ";
    public static String text=" TEXT, ";
    public static String igual=" = ";
    public static String uno="1";
    public static String comilla="'";


    //Persona
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


    //
    public static final int tipoPaciente=1;
    public static final int tipoMedico=2;
    public static final int tipoMedicoPaciente=3;
    public static final int tipoParamedico=4;


    public static final int codeGPS=100;

}

