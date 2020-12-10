package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.Telefono;

import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/telefono" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TelefonoAPI extends AplicacionBase{

    @GET
    @Path("/allTelefono")
    public List<Telefono> listarTelefonos(){
        DaoTelefono daoTelefono = new DaoTelefono();
        return daoTelefono.findAll(Telefono.class);
    }

    @GET
    @Path("/consultarTelefono/{id}")
    public Telefono consultarTelefono(@PathParam("id") long id){
        DaoTelefono daoTelefono = new DaoTelefono();
        return daoTelefono.find(id, Telefono.class);
    }

    @POST
    @Path("/addTelefono")
    public Telefono addTelefono(TelefonoDto telefonoDto){

        DaoTelefono daoTelefono = new DaoTelefono();
        Telefono telefono = new Telefono();

            telefono.set_numero(telefonoDto.getNumero());
            telefono.set_estatus(telefonoDto.getEstatus());
            Informacion informacion = new Informacion(telefonoDto.getInformacion().getId());
            telefono.set_informacion(informacion);
            daoTelefono.insert(telefono);

            return telefono;
    }

    @PUT
    @Path("/updateTelefono/{id}")
    public Response updateTelefono(@PathParam("id") long id, TelefonoDto telefonoDto){

        DaoTelefono daoTelefono = new DaoTelefono();
        Telefono telefono_modificar = daoTelefono.find(id, Telefono.class);

        if (telefono_modificar != null){

            telefono_modificar.set_numero(telefonoDto.getNumero());
            telefono_modificar.set_estatus(telefonoDto.getEstatus());
            daoTelefono.update(telefono_modificar);
            return Response.ok().entity(telefono_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteTelefono/{id}")
    public Response eliminarTelefono(@PathParam("id") long id){

        DaoTelefono daoTelefono = new DaoTelefono();
        Telefono telefono_eliminar = daoTelefono.find(id, Telefono.class);

        if (telefono_eliminar != null){

            daoTelefono.delete(telefono_eliminar);
            return Response.ok().entity(telefono_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
