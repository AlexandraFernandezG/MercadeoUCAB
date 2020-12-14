package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Rol;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/rol" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RolAPI extends AplicacionBase{

    @GET
    @Path("/allRoles")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Rol> listarRoles() throws NullPointerException {

        DaoRol daoRol = new DaoRol();

        try {
            return daoRol.findAll(Rol.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    @GET
    @Path("/consultarRol/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Rol consultarRol(@PathParam("id") long id){

        DaoRol daoRol = new DaoRol();

        try {
            return daoRol.find(id, Rol.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
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
            System.out.print(problema);
        }
        return resultado;
    }

    @PUT
    @Path("/updateRol/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateRol(@PathParam("id") long id, RolDto rolDto){

        DaoRol daoRol = new DaoRol();
        Rol rol_modificar = daoRol.find(id, Rol.class);

        if(rol_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            rol_modificar.set_nombre(rolDto.getNombre());
            rol_modificar.set_estatus(rolDto.getEstatus());
            rolDto.setEstatus(rolDto.getEstatus());
            daoRol.update(rol_modificar);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(rol_modificar).build();

    }

    @DELETE
    @Path("/deleteRol/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteRol(@PathParam("id") long id){

        DaoRol daoRol = new DaoRol();
        Rol rol_eliminar = daoRol.find(id, Rol.class);

        if(rol_eliminar == null) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            daoRol.delete(rol_eliminar);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(rol_eliminar).build();

    }
}