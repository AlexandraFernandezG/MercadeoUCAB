package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Rol;


import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/rol" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RolAPI {
    @GET
    @Path("/allRoles")
    public List<Rol> listarRoles(){

        DaoRol daoRol = new DaoRol();
        return daoRol.findAll(Rol.class);
    }

    @GET
    @Path("/consultarRol/{id}")
    public Rol consultarRol(@PathParam("id") long id){
        DaoRol daoRol = new DaoRol();
        return daoRol.find(id, Rol.class);
    }

    @POST
    @Path("/addRol")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public RolDto addCategoria(RolDto rolDto)
    {
        RolDto resultado = new RolDto();
        try {

            DaoRol dao = new DaoRol();
            Rol rol = new Rol();

            rol.set_nombre(rolDto.getNombre());
            rol.set_estatus(rol.get_estatus());
            Rol resul = dao.insert(rol);
            resultado.setId(resul.get_id());
        }
        catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/updateRol/{id}")
    public Response updateRol(@PathParam("id") long id, RolDto rolDto){

        DaoRol daoRol = new DaoRol();
        Rol rol_modificar = daoRol.find(id, Rol.class);

        if(rol_modificar != null){

            rol_modificar.set_nombre(rolDto.getNombre());
            rolDto.setEstatus(rolDto.getEstatus());
            return Response.ok().entity(rol_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteRol/{id}")
    public Response deleteRol(@PathParam("id") long id){

        DaoRol daoRol = new DaoRol();
        Rol rol_eliminar = daoRol.find(id, Rol.class);

        if(rol_eliminar != null){

            daoRol.delete(rol_eliminar);
            return Response.ok().entity(rol_eliminar).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }


}
