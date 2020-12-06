package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Informacion;


import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/hijo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class HijoAPI extends AplicacionBase{
    
    @GET
    @Path("/allHijos")
    public List<Hijo> listarHijos(){

        DaoHijo daoHijo = new DaoHijo();
        return daoHijo.findAll(Hijo.class);
    }
    
    @GET
    @Path("/consultarHijo/{id}")
    public Hijo consultarHijo(@PathParam("id") long id){
        DaoHijo daoHijo = new DaoHijo();
        return daoHijo.find(id, Hijo.class);
    }
    
    @POST
    @Path("/addHijo")
    public Hijo addHijo(HijoDto hijoDto){

        DaoHijo daoHijo = new DaoHijo();
        Hijo hijo = new Hijo();

            hijo.setFechaNacimiento(hijoDto.get_fechaNacimiento());
            hijo.setGenero(hijoDto.get_genero());
            hijo.set_estatus("Activo");
            Informacion informacion = new Informacion(hijoDto.get_informacionDto().getId());
            hijo.setInformacion(informacion);

            return hijo;
    }

    @PUT
    @Path("/updateHijo/{id}")
    public Response updateHijo(@PathParam("id") long id, HijoDto hijoDto){

        DaoHijo daoHijo = new DaoHijo();
        Hijo hijo_modificar = daoHijo.find(id, Hijo.class);

        if(hijo_modificar != null){

            hijo_modificar.setFechaNacimiento(hijoDto.get_fechaNacimiento());
            hijo_modificar.setGenero(hijoDto.get_genero());
            hijo_modificar.set_estatus("Activo");
            daoHijo.update(hijo_modificar);
            return Response.ok().entity(hijo_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteHijo/{id}")
    public Response deleteHijo(@PathParam("id") long id){

        DaoHijo daoHijo = new DaoHijo();
        Hijo hijo_eliminar = daoHijo.find(id, Hijo.class);

        if(hijo_eliminar != null){

            daoHijo.delete(hijo_eliminar);
            return Response.ok().entity(hijo_eliminar).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
