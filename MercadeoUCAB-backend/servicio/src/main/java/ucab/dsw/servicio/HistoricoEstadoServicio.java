package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoHistoricoEstado;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.HistoricoEstadoDto;
import ucab.dsw.entidades.HistoricoEstado;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/historicoEstado" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class HistoricoEstadoServicio extends AplicacionBase{

    /**
     * Este método permite obtener todos los historicos
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de los historicos y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allHistoricoEstado")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarHistoricos() {

        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        JsonObject dataObject;

        try {
            List<HistoricoEstado> listaHistoricoEstados = daoHistoricoEstado.findAll(HistoricoEstado.class);
            return Response.status(Response.Status.OK).entity(listaHistoricoEstados).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }


    /**
     * Este método permite obtener un historico.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el historico consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del historico que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarHistorioEstado/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarHistorico(@PathParam("id") long id) {
        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        JsonObject dataObject;

        try {
            HistoricoEstado historico_consultado = daoHistoricoEstado.find(id, HistoricoEstado.class);
            return Response.status(Response.Status.OK).entity(historico_consultado).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el historico: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }

    }

    /**
     * Este método permite obtener todos las historicos activos.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de los historicos activos y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarHistoricosActivos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response historicosActivos() {

        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        List<HistoricoEstado> listaHistorico = daoHistoricoEstado.findAll(HistoricoEstado.class);
        List<HistoricoEstado> listaHistoricoActivos = new ArrayList<HistoricoEstado>();
        JsonObject dataObject;

        try {

            for (HistoricoEstado historicoEstado : listaHistorico) {

                if (historicoEstado.get_estatus().equals("Activos")) {
                    listaHistoricoActivos.add(historicoEstado);
                }
            }
            return Response.status(Response.Status.OK).entity(listaHistoricoActivos).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite insertar un historico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el historico insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un historico duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param historicoEstadoDto el objeto historico que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addHistorioEstado")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addHistoricoEstado(HistoricoEstadoDto historicoEstadoDto){

        HistoricoEstadoDto resultado = new HistoricoEstadoDto();
        JsonObject dataObject;

        try {

            DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
            HistoricoEstado historicoEstado = new HistoricoEstado();
            DaoUsuario daoUsuario = new DaoUsuario();

            historicoEstado.set_fechaInicio(historicoEstadoDto.getFechaInicio());
            historicoEstado.set_fechaFin(historicoEstado.get_fechaFin());
            historicoEstado.set_estatus(historicoEstadoDto.getEstatus());
            Usuario usuario = daoUsuario.find(historicoEstado.get_usuario().get_id(), Usuario.class);
            historicoEstado.set_usuario(usuario);
            HistoricoEstado resul = daoHistoricoEstado.insert(historicoEstado);
            resultado.setId(resul.get_id());

            return Response.status(Response.Status.OK).entity(resultado).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el historico: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite modificar un historico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el historico modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un historico duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param historicoEstadoDto el objeto categoria que el sistema desea modificar.
     * @param id el id de la categoria a modificar
     */
    @PUT
    @Path("/estatusHistorico/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusHistorico(@PathParam("id") long id, HistoricoEstadoDto historicoEstadoDto){

        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        JsonObject dataObject;

            try {
                HistoricoEstado historicoEstado_modificar = daoHistoricoEstado.find(id, HistoricoEstado.class);
                historicoEstadoDto.setEstatus(historicoEstadoDto.getEstatus());
                daoHistoricoEstado.update(historicoEstado_modificar);

                return Response.status(Response.Status.OK).entity(historicoEstado_modificar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado el historico: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }
    }

    /**
     * Este método permite eliminar un historico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe..
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del historico a eliminar
     */
    @DELETE
    @Path("/deleteHistoricoEstado/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarHistoricoEstado(@PathParam("id") long id){

        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        HistoricoEstado historicoEstado_eliminar = daoHistoricoEstado.find(id, HistoricoEstado.class);
        JsonObject dataObject;

            try {
                daoHistoricoEstado.delete(historicoEstado_eliminar);

                return Response.status(Response.Status.OK).entity(historicoEstado_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado el historico: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }
    }
}
