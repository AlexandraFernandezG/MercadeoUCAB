package ucab.dsw.comando.Funciones;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.UsuarioEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuncionesComando {

    /**
     * Este método permite transformar la fecha de date a string debido a una exigencia del Json
     *
     * @param fecha Parsear la fecha de date a string para poder enviar el Json.
     * @author Emanuel Di Cristofaro
     */
    public String devolverFecha(Date fecha) {

        String fecha_estudio = "";

        if (fecha != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fecha_estudio = sdf.format(fecha);

        } else {

            fecha_estudio = "";
        }

        return fecha_estudio;
    }

    public int devolverEdad(Date fechaNacimiento){

        int edad = 0;

        //Primero pasamos la fecha de nacimiento a string
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_nac = sdf.format(fechaNacimiento);

        //Formato de la fecha para la operacion de la edad
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //Parseamos la fecha y obtener la fecha actual.
        LocalDate fechaNac = LocalDate.parse(fecha_nac, fmt);
        LocalDate ahora = LocalDate.now();

        //Calcular la edad
        Period periodo = Period.between(fechaNac, ahora);

        //Edad de la persona
        edad = periodo.getYears();

        return edad;
    }

    public String devolverDisponibilidadEnLinea(long idSE){

        DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(idSE, SolicitudEstudio.class);

        String disponibilidadEnLinea = solicitudEstudio.get_disponibilidadEnLinea();

        return  disponibilidadEnLinea;
    }

    public String devolverTelefono(long id){

        DaoUsuarioEstudio daoUsuarioEstudio = Fabrica.crear(DaoUsuarioEstudio.class);
        List<Object[]> telefonoString = daoUsuarioEstudio.traerTelefono(id);
        String telefono = "";

        for (Object[] tel: telefonoString){

            telefono = (String)tel[1];
        }

        return telefono;
    }

    /**
     * Este método permite insertar los usuarios a un estudio sin dto
     * @author Emanuel Di Cristofaro
     * @throws Exception Captura cualquier excepcion encontrada.
     * @param idEnc el id del usuario que se quiere insertar.
     * @param idE el id del estudio que se le asignaran las preguntas.
     */
    public void addUsuarioEstudio(long idE, long idEnc) {

        try {

        DaoUsuarioEstudio daoUsuarioEstudio = Fabrica.crear(DaoUsuarioEstudio.class);
        UsuarioEstudio usuarioEstudio = Fabrica.crear(UsuarioEstudio.class);
        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);

        usuarioEstudio.set_estatus("En espera");
        Usuario usuario = daoUsuario.find(idEnc, Usuario.class);
        usuarioEstudio.set_usuario(usuario);
        Estudio estudio = daoEstudio.find(idE, Estudio.class);
        usuarioEstudio.set_estudio(estudio);
        daoUsuarioEstudio.insert(usuarioEstudio);

        } catch (Exception ex){

            ex.getMessage();
        }

    }

    /**
     * Este método permite insertar las preguntas a un estudio sin dto
     * @author Emanuel Di Cristofaro
     * @throws Exception Captura cualquier excepcion encontrada.
     * @param idPR el id de la pregunta que se quiere insertar.
     * @param idE el id del estudio que se le asignaran las preguntas.
     */
    public void addPreguntaEstudio(long idE, long idPR){

        try {

            DaoPreguntaEstudio daoPreguntaEstudio = Fabrica.crear(DaoPreguntaEstudio.class);
            PreguntaEstudio preguntaEstudio = Fabrica.crear(PreguntaEstudio.class);
            DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
            DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);

            preguntaEstudio.set_estatus("Activo");
            Estudio estudio = daoEstudio.find(idE, Estudio.class);
            preguntaEstudio.set_estudio(estudio);
            PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(idPR, PreguntaEncuesta.class);
            preguntaEstudio.set_preguntaEncuesta(preguntaEncuesta);
            daoPreguntaEstudio.insert(preguntaEstudio);

        } catch (Exception ex){

            ex.getMessage();
        }

    }
}
