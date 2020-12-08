package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.entidades.SolicitudEstudio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/ocupacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OcupacionAPI extends AplicacionBase {

    //Listar ocupaciones
    @GET
    @Path("/allOcupacion")
    public List<Ocupacion> listarOcupacion(){
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        return daoOcupacion.findAll(Ocupacion.class);
    }

    // Consultar una ocupacion
    @GET
    @Path("/consultarOcupacion/{id}")
    public Ocupacion consultarOcupacion(@PathParam("id") long id){
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        return daoOcupacion.find(id, Ocupacion.class);
    }

    // Agregar una ocupacion
    @POST
    @Path("/addOcupacion")
    public Ocupacion addOcupacion(OcupacionDto ocupacionDto){

        DaoOcupacion daoOcupacion = new DaoOcupacion();
        Ocupacion ocupacion = new Ocupacion();

            ocupacion.setNombre(ocupacionDto.get_nombre());
            ocupacion.set_estatus(ocupacionDto.get_estatus());
            daoOcupacion.insert(ocupacion);

            return ocupacion;
    }

    // Actualizar una ocupacion
    @PUT
    @Path("/updateOcupacion/{id}")
    public Response updateOcupacion(@PathParam("id") long id, OcupacionDto ocupacionDto){

        DaoOcupacion daoOcupacion = new DaoOcupacion();
        Ocupacion ocupacion_modificar = daoOcupacion.find(id, Ocupacion.class);

        if (ocupacion_modificar != null){

            ocupacion_modificar.setNombre(ocupacionDto.get_nombre());
            ocupacion_modificar.set_estatus(ocupacionDto.get_estatus());
            daoOcupacion.update(ocupacion_modificar);
            return Response.ok().entity(ocupacion_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Eliminar una ocupacion
    @DELETE
    @Path("/deleteOcupacion/{id}")
    public Response eliminarOcupacion(@PathParam("id") long id){

        DaoOcupacion daoOcupacion = new DaoOcupacion();
        Ocupacion ocupacion_eliminar = daoOcupacion.find(id, Ocupacion.class);

        if (ocupacion_eliminar!= null){

            daoOcupacion.delete(ocupacion_eliminar);
            return Response.ok().entity(ocupacion_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
