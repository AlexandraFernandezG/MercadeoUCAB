package ucab.dsw.comando.Funciones;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.UsuarioEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * Este método permite insertar los usuarios a un estudio sin dto
     * @author Emanuel Di Cristofaro
     * @throws Exception Captura cualquier excepcion encontrada.
     * @param idEnc el id del usuario que se quiere insertar.
     * @param idE el id del estudio que se le asignaran las preguntas.
     */
    public void addUsuarioEstudio(long idE, long idEnc) {

        try {


        DaoUsuarioEstudio daoUsuarioEstudio = new DaoUsuarioEstudio();
        UsuarioEstudio usuarioEstudio = new UsuarioEstudio();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoEstudio daoEstudio = new DaoEstudio();

        usuarioEstudio.set_estatus("En proceso");
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
     * Este método permite insertar los usuarios a un estudio sin dto
     * @author Emanuel Di Cristofaro
     * @throws Exception Captura cualquier excepcion encontrada.
     * @param idPR el id de la pregunta que se quiere insertar.
     * @param idE el id del estudio que se le asignaran las preguntas.
     */
    public void addPreguntaEstudio(long idE, long idPR){

        try {

            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            PreguntaEstudio preguntaEstudio = new PreguntaEstudio();
            DaoEstudio daoEstudio = new DaoEstudio();
            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

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
