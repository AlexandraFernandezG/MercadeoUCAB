package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/informacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class InformacionServicio extends AplicacionBase {

    /**
     * Este método permite obtener la información correspondiente a una cédula.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de la información consultado y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/consultarCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInformacionCedula(@PathParam("cedula") int cedula) {
        JsonObject dataObject;
        DaoInformacion daoInformacion = new DaoInformacion();

        try {
            List<Informacion> informacion_consultada = daoInformacion.obtenerInformacion(cedula);
            return Response.status(Response.Status.OK).entity(informacion_consultada).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
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
     * Este método permite obtener todas las informaciones.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de informaciones y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/listarInformacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarInformacionEncuestados() {

        DaoInformacion daoInformacion = new DaoInformacion();
        JsonObject dataObject;

        try {
            List<Informacion> listaInformaciones = daoInformacion.findAll(Informacion.class);
            return Response.status(Response.Status.OK).entity(listaInformaciones).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener una información.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la información consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la información que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarInformacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInformacion(@PathParam("id") long id) throws NullPointerException {

        DaoInformacion daoInformacion = new DaoInformacion();
        JsonObject dataObject;

        try {
            Informacion informacion_consultada = daoInformacion.find(id, Informacion.class);
            return Response.status(Response.Status.OK).entity(informacion_consultada).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la información: " + ex.getMessage())
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

     /** Este método permite insertar una información
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la información insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una información duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param informacionDto el objeto información que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addInformacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInformacion(InformacionDto informacionDto) {

        InformacionDto resultado = new InformacionDto();
        JsonObject dataObject;

        try {

            DaoInformacion daoInformacion = new DaoInformacion();
            Informacion informacion = new Informacion();
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoOcupacion daoOcupacion = new DaoOcupacion();
            DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
            DaoLugar daoLugar = new DaoLugar();

            informacion.set_cedula(informacionDto.getCedula());
            informacion.set_primerNombre(informacionDto.getPrimerNombre());
            informacion.set_segundoNombre(informacionDto.getSegundoNombre());
            informacion.set_primerApellido(informacionDto.getPrimerApellido());
            informacion.set_segundoApellido(informacionDto.getSegundoApellido());
            informacion.set_genero(informacionDto.getGenero());
            informacion.set_fechaNacimiento(informacionDto.getFechaNacimiento());
            informacion.set_estadoCivil(informacionDto.getEstadoCivil());
            informacion.set_disponibilidadEnLinea(informacionDto.getDisponibilidadEnLinea());
            informacion.set_cantidadPersonas(informacionDto.getCantidadPersonas());
            informacion.set_estatus(informacionDto.getEstatus());

            //FKS

            Lugar lugar = daoLugar.find(informacionDto.getLugarDto().getId(), Lugar.class);
            informacion.set_lugar(lugar);

            NivelEconomico nivelEconomico = daoNivelEconomico.find(informacionDto.getNivelEconomicoDto().getId(), NivelEconomico.class);
            informacion.set_nivelEconomico(nivelEconomico);

            NivelAcademico nivelAcademico = daoNivelAcademico.find(informacionDto.getNivelAcademicoDto().getId(), NivelAcademico.class);
            informacion.set_nivelAcademico(nivelAcademico);

            Ocupacion ocupacion = daoOcupacion.find(informacionDto.getOcupacionDto().getId(), Ocupacion.class);
            informacion.set_ocupacion(ocupacion);

            Usuario usuario = daoUsuario.find(informacionDto.getUsuarioDto().getId(), Usuario.class);
            informacion.set_usuario(usuario);

            Informacion resul = daoInformacion.insert(informacion);
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
                    .add("excepcion", "No se ha insertado la información: " + ex.getMessage())
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
     * Este método permite modificar una categoria
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la información modificada y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepción se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una información duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param informacionDto el objeto información que el sistema desea modificar.
     * @param id el id de la información a modificar
     */
    @PUT
    @Path("/updateInformacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarInformacion(@PathParam("id") long id, InformacionDto informacionDto) {

        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion_modificar = daoInformacion.find(id, Informacion.class);
        JsonObject dataObject;

        try {

            DaoUsuario daoUsuario = new DaoUsuario();
            DaoOcupacion daoOcupacion = new DaoOcupacion();
            DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
            DaoLugar daoLugar = new DaoLugar();

            informacion_modificar.set_cedula(informacionDto.getCedula());
            informacion_modificar.set_primerNombre(informacionDto.getPrimerNombre());
            informacion_modificar.set_segundoNombre(informacionDto.getSegundoNombre());
            informacion_modificar.set_primerApellido(informacionDto.getPrimerApellido());
            informacion_modificar.set_segundoApellido(informacionDto.getSegundoApellido());
            informacion_modificar.set_genero(informacionDto.getGenero());
            informacion_modificar.set_fechaNacimiento(informacionDto.getFechaNacimiento());
            informacion_modificar.set_estadoCivil(informacionDto.getEstadoCivil());
            informacion_modificar.set_disponibilidadEnLinea(informacionDto.getDisponibilidadEnLinea());
            informacion_modificar.set_cantidadPersonas(informacionDto.getCantidadPersonas());
            informacion_modificar.set_estatus(informacionDto.getEstatus());

            Lugar lugar = daoLugar.find(informacionDto.getLugarDto().getId(), Lugar.class);
            informacion_modificar.set_lugar(lugar);

            NivelEconomico nivelEconomico = daoNivelEconomico.find(informacionDto.getNivelEconomicoDto().getId(), NivelEconomico.class);
            informacion_modificar.set_nivelEconomico(nivelEconomico);

            NivelAcademico nivelAcademico = daoNivelAcademico.find(informacionDto.getNivelAcademicoDto().getId(), NivelAcademico.class);
            informacion_modificar.set_nivelAcademico(nivelAcademico);

            Ocupacion ocupacion = daoOcupacion.find(informacionDto.getOcupacionDto().getId(), Ocupacion.class);
            informacion_modificar.set_ocupacion(ocupacion);

            Usuario usuario = daoUsuario.find(informacionDto.getUsuarioDto().getId(), Usuario.class);
            informacion_modificar.set_usuario(usuario);

            daoInformacion.update(informacion_modificar);
            return Response.status(Response.Status.OK).entity(informacion_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la información: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }

    }

    /**
     * Este método permite eliminar una información
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la categoria a eliminar
     */
    @DELETE
    @Path("/deleteInformacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInformacion(@PathParam("id") long id) {

        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion_eliminar = daoInformacion.find(id, Informacion.class);
        JsonObject dataObject;

        try {

            daoInformacion.delete(informacion_eliminar);

            return Response.status(Response.Status.OK).entity(informacion_eliminar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la información: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }
}