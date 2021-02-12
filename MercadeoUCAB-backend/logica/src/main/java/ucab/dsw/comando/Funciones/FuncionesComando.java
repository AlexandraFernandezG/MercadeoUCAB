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

    public String devolverDisponibilidadEnLinea(long idSE){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(idSE, SolicitudEstudio.class);

        String disponibilidadEnLinea = solicitudEstudio.get_disponibilidadEnLinea();

        return  disponibilidadEnLinea;
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

    public void update(long encuestado){

        DaoUsuarioEstudio daoUsuarioEstudio = new DaoUsuarioEstudio();

        UsuarioEstudio usuarioEstudio_modificar = daoUsuarioEstudio.find(encuestado, UsuarioEstudio.class);
        usuarioEstudio_modificar.set_estatus("Respondido");
        daoUsuarioEstudio.update(usuarioEstudio_modificar);
    }

    public void cambiarEstadoEstudio(long id){

        int cantidadRespuestaTotal = 0;
        int cantidadEncuestadosRespondido = 0;
        DaoEstudio daoEstudio = new DaoEstudio();

        try {

            DaoUsuarioEstudio daoUsuarioEstudio = new DaoUsuarioEstudio();
            //Obtener los encuestados de un estudio
            List<UsuarioEstudio> listaUsuarioEstudio = daoUsuarioEstudio.findAll(UsuarioEstudio.class);
            List<UsuarioEstudio> encuestadosEstudio = new ArrayList<>();

            for (UsuarioEstudio encuestado : listaUsuarioEstudio) {

                if (encuestado.get_estudio().get_id() == id)
                    encuestadosEstudio.add(encuestado);
            }

            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            //Obtener las preguntas del estudio
            List<PreguntaEstudio> listaPreguntas = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
            List<PreguntaEstudio> listaPreguntasEstudio = new ArrayList<>();

            for (PreguntaEstudio pregunta : listaPreguntas) {

                if (pregunta.get_estudio().get_id() == id)
                    listaPreguntasEstudio.add(pregunta);
            }

            //Operacion para cambiar el estatus
            int cantidadPreguntas = listaPreguntasEstudio.size();
            List<Long> cantidadRespuestas = new ArrayList<>();

            for (UsuarioEstudio encuestados : encuestadosEstudio) {

                cantidadRespuestas = daoUsuarioEstudio.cantidadRespuestas(encuestados.get_usuario().get_id(), id);
                cantidadRespuestaTotal = cantidadRespuestas.size();

                if (cantidadPreguntas == cantidadRespuestaTotal) {

                    //daoUsuarioEstudio.updateEstadoEstudio(encuestados.get_id());
                    update(encuestados.get_id());
                }

            }

            //Verificar que todos los encuestados respondieron la encuesta en el estudio
            for (UsuarioEstudio verificarRespondido : listaUsuarioEstudio) {

                if (verificarRespondido.get_estatus().equals("Respondido") && verificarRespondido.get_estudio().get_id() == id) {
                    cantidadEncuestadosRespondido = cantidadEncuestadosRespondido + 1;
                }

            }

            if(encuestadosEstudio.size() != 0) {
                //Finalmente cambiamos el estado del estudio acorde al conteo
                if (encuestadosEstudio.size() == cantidadEncuestadosRespondido) {
                    Estudio estudio = daoEstudio.find(id, Estudio.class);
                    estudio.set_estado("Finalizado");
                    daoEstudio.update(estudio);
                }
            }

        } catch (Exception ex){

            ex.getMessage();
        }
    }
}
