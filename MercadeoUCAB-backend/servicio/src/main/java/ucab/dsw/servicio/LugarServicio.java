package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/lugar" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LugarServicio extends AplicacionBase{

    /**
     * Este método permite obtener todas los lugares.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de lugares y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allLugares")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarLugares() throws NullPointerException{

        DaoLugar daoLugar = new DaoLugar();
        JsonObject dataObject;

        try {
            List<Lugar> listaLugares = daoLugar.findAll(Lugar.class);
            return Response.status(Response.Status.OK).entity(listaLugares).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener un lugar.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la lugar consultado a y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del lugar que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarLugar(@PathParam("id") long id) throws NullPointerException {

        DaoLugar daoLugar = new DaoLugar();
        JsonObject dataObject;

        try {
            Lugar lugar_consultado = daoLugar.find(id, Lugar.class);

            return Response.status(Response.Status.OK).entity(lugar_consultado).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el lugar: " + ex.getMessage())
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
     * Este método permite obtener todas los países.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de los países y en dado caso obtener una excepción si aplica.
     */
    @GET
    @Path("/lugarPais")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPaises()  {

        DaoLugar daoLugar = new DaoLugar();
        JsonObject dataObject;
        List<Lugar> listaLugares = daoLugar.findAll(Lugar.class);
        List<Lugar> listaPaises = new ArrayList<Lugar>();

        try {

            for (Lugar lugar : listaLugares) {

                if (lugar.get_tipo().equals("Pais")) {

                    listaPaises.add(lugar);
                }
            }

            return Response.status(Response.Status.OK).entity(listaPaises).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el país: " + ex.getMessage())
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
     * Este método permite obtener todas los estados.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de los estados y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/lugarEstado")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstados() {

        DaoLugar daoLugar = new DaoLugar();
        JsonObject dataObject;
        List<Lugar> listaLugares = daoLugar.findAll(Lugar.class);
        List<Lugar> listaEstados = new ArrayList<Lugar>();

        try {

            for (Lugar lugar : listaLugares) {

                if (lugar.get_tipo().equals("Estado")) {

                    listaEstados.add(lugar);
                }
            }

            return Response.status(Response.Status.OK).entity(listaEstados).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado ningún estado: " + ex.getMessage())
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
     * Este método permite obtener todas las urbanizaciones.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de las urbanizaciones y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/lugarUrbanizacion")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarUrbanizaciones() {

        DaoLugar daoLugar = new DaoLugar();
        JsonObject dataObject;
        List<Lugar> listaLugares = daoLugar.findAll(Lugar.class);
        List<Lugar> listaUrbanizaciones = new ArrayList<Lugar>();

        try {

            for (Lugar lugar : listaLugares) {

                if (lugar.get_tipo().equals("Urbanizacion")) {

                    listaUrbanizaciones.add(lugar);
                }
            }

            return Response.status(Response.Status.OK).entity(listaUrbanizaciones).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el país: " + ex.getMessage())
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
     * Este método permite obtener todas los estados de un país o urbanizaciones de un estado.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de lugares y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/jerarquiaLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response jerarquiaLugar(@PathParam("id") long id) {

        DaoLugar daoLugar = new DaoLugar();
        JsonObject dataObject;
        List<Lugar> listaLugares = daoLugar.findAll(Lugar.class);
        List<Lugar> listaJerarquia = new ArrayList<Lugar>();

        try {

            for (Lugar lugar : listaLugares) {

            if(lugar.get_tipo().equals("Estado")) {

                if (id == lugar.get_lugar().get_id()) {

                    long idFK = lugar.get_id();
                    Lugar lugar_encontrado = daoLugar.find(idFK, Lugar.class);
                    listaJerarquia.add(lugar_encontrado);
                }

            } else if(lugar.get_tipo().equals("Urbanizacion")){

                if (id == lugar.get_lugar().get_id()) {

                    long idFK = lugar.get_id();
                    Lugar lugar_encontrado = daoLugar.find(idFK, Lugar.class);
                    listaJerarquia.add(lugar_encontrado);
                }
            }

            }

            return Response.status(Response.Status.OK).entity(listaJerarquia).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se han encontrado lugares: " + ex.getMessage())
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
     * Este método permite insertar un lugar
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el lugar insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un lugar duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param lugarDto el objeto lugar que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addLugar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addLugar(LugarDto lugarDto){

        LugarDto resultado = new LugarDto();
        JsonObject dataObject;

        try {

            DaoLugar daoLugar = new DaoLugar();
            Lugar lugar = new Lugar();
            lugar.set_nombre(lugarDto.getNombre());
            lugar.set_tipo(lugarDto.getTipo());
            lugar.set_categoriaSocioEconomica(lugarDto.getCategoriaSocioEconomica());
            lugar.set_estatus(lugarDto.getEstatus());
            Lugar lugar_fk = daoLugar.find(lugarDto.getLugar().getId(), Lugar.class);
            lugar.set_lugar(lugar_fk);
            Lugar resul = daoLugar.insert(lugar);
            resultado.setId(resul.get_id());
            return Response.status(Response.Status.OK).entity(resultado).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha agregado el lugar: " + ex.getMessage())
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
     * Este método permite modificar un lugar
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el lugar modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica un lugar duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param lugarDto el objeto lugar que el sistema desea modificar.
     * @param id el id del lugar a modificar
     */
    @PUT
    @Path("/updateLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateLugar(@PathParam("id") long id, LugarDto lugarDto){

        DaoLugar daoLugar = new DaoLugar();
        JsonObject dataObject;

        try {
            Lugar lugar_modificar = daoLugar.find(id, Lugar.class);
            lugar_modificar.set_nombre(lugarDto.getNombre());
            lugar_modificar.set_tipo(lugarDto.getTipo());
            lugar_modificar.set_categoriaSocioEconomica(lugarDto.getCategoriaSocioEconomica());
            lugar_modificar.set_estatus(lugarDto.getEstatus());
            daoLugar.update(lugar_modificar);

            return Response.status(Response.Status.OK).entity(lugar_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el lugar a modificar: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }

    }

    /**
     * Este método permite eliminar un lugar
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del lugar a eliminar
     */
    @DELETE
    @Path("/deleteLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteLugar(@PathParam("id") long id){

        DaoLugar daoLugar = new DaoLugar();
        JsonObject dataObject;

        try {
            Lugar lugar_eliminar = daoLugar.find(id, Lugar.class);
            daoLugar.delete(lugar_eliminar);
            return Response.status(Response.Status.OK).entity(lugar_eliminar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el lugar: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

}
