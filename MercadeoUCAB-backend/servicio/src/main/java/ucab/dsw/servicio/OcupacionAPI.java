package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Ocupacion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class OcupacionAPI {

    @GET
    @Path("/allOcupaciones")
    public List<Ocupacion> listarOcupaciones(){

        DaoOcupacion daoOcupacion = new DaoOcupacion();
        return daoOcupacion.findAll(Ocupacion.class);
    }

    @GET
    @Path("/consultarOcupacion/{id}")
    public Ocupacion consultarOcupacion(@PathParam("id") long id){
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        return daoOcupacion.find(id, Ocupacion.class);
    }

    //Agregar una ocupación
    @POST
    @Path("/addOcupacion")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public OcupacionDto addOcupacion(OcupacionDto ocupacionDto)
    {
        OcupacionDto resultado = new OcupacionDto();
        try {

            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion ocupacion = new Ocupacion();
            ocupacion.set_nombre(ocupacionDto.getNombre());
            Ocupacion resul = dao.insert(ocupacion);
            resultado.setId(resul.get_id());
        }
        catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/updateOcupacion/{id}")
    public Response updateOcupacion(@PathParam("id") long id, OcupacionDto ocupacionDto){

        DaoOcupacion daoOcupacion = new DaoOcupacion();
        Ocupacion ocupacion_modificar = daoOcupacion.find(id, Ocupacion.class);

        if(ocupacion_modificar != null){

            ocupacion_modificar.set_nombre(ocupacionDto.getNombre());
            ocupacion_modificar.set_estatus("Activo");
            daoOcupacion.update(ocupacion_modificar);
            return Response.ok().entity(ocupacion_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteOcupacion/{id}")
    public Response deleteOcupacion(@PathParam("id") long id){

        DaoOcupacion daoOcupacion = new DaoOcupacion();
        Ocupacion ocupacion_eliminar = daoOcupacion.find(id, Ocupacion.class);

        if(ocupacion_eliminar != null){

            daoOcupacion.delete(ocupacion_eliminar);
            return Response.ok().entity(ocupacion_eliminar).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
    
}
