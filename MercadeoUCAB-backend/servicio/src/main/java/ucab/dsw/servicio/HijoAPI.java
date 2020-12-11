package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.Ocupacion;


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

    //Agregar una ocupación
    @POST
    @Path("/addHijo")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public HijoDto addHijo(HijoDto hijoDto)
    {
        HijoDto resultado = new HijoDto();
        try {

            DaoHijo dao = new DaoHijo();
            Hijo hijo = new Hijo();
            hijo.set_fechaNacimiento(hijoDto.getFechaNacimiento());
            hijo.set_genero(hijoDto.getGenero());
            hijo.set_estatus(hijoDto.getEstatus());
            Hijo resul = dao.insert(hijo);
            resultado.setId(resul.get_id());
        }
        catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return resultado;
    }


    @PUT
    @Path("/updateHijo/{id}")
    public Response updateHijo(@PathParam("id") long id, HijoDto hijoDto){

        DaoHijo daoHijo = new DaoHijo();
        Hijo hijo_modificar = daoHijo.find(id, Hijo.class);

        if(hijo_modificar != null){

            hijo_modificar.set_fechaNacimiento(hijoDto.getFechaNacimiento());
            hijo_modificar.set_genero(hijoDto.getGenero());
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
