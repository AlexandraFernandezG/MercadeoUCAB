package ucab.dsw.servicio;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class EstudioAPI extends AplicacionBase {

    @GET
    @Path("/allEstudio")
    public List<Estudio> listarEstudios(){
        DaoEstudio daoEstudio = new DaoEstudio();
        return daoEstudio.findAll(Estudio.class);
    }

    @GET
    @Path("/consultarEstudio/{id}")
    public Estudio consultarEstudio(@PathParam("id") long id){
        DaoEstudio daoEstudio = new DaoEstudio();
        return daoEstudio.find(id, Estudio.class);
    }

    @GET
    @Path("/mostrarEstudiosActivos")
    public List<Estudio> mostrarEstudiosActivos(){
        DaoEstudio daoEstudio = new DaoEstudio();
        List<Estudio> listaEstudios = daoEstudio.findAll(Estudio.class);
        List<Estudio> listaEstudiosActivos = null;

        for (Estudio estudio: listaEstudios){

            if(estudio.get_estatus() == "Activo"){
                listaEstudiosActivos.add(estudio);
            }
        }
        return listaEstudiosActivos;
    }

    @POST
    @Path("/addEstudio")
    public Estudio addEstudios(EstudioDto estudioDto){

        DaoEstudio daoEstudio = new DaoEstudio();
        Estudio estudio = new Estudio();

        estudio.setNombre(estudioDto.get_nombre());
        estudio.setTipoInstrumento(estudioDto.get_tipoInstrumento());
        estudio.setFechaInicio(estudioDto.get_fechaInicio());
        estudio.setFechaFin(estudioDto.get_fechaFin());
        estudio.set_estatus(estudioDto.get_estatus());
        SolicitudEstudio solicitudEstudio = new SolicitudEstudio(estudioDto.get_solicitudEstudioDto().getId());
        Usuario usuario = new Usuario(estudioDto.get_usuarioDto().getId());
        estudio.setSolicitudEstudio(solicitudEstudio);
        estudio.setUsuario(usuario);
        daoEstudio.insert(estudio);

        return estudio;

    }

    @PUT
    @Path("/updateEstudio/{id}")
    public Response modificarEstudio(@PathParam("id") long id, EstudioDto estudioDto){

        DaoEstudio daoEstudio = new DaoEstudio();
        Estudio estudio_modificar = daoEstudio.find(id, Estudio.class);

        if(estudio_modificar != null){

            estudio_modificar.setNombre(estudioDto.get_nombre());
            estudio_modificar.setTipoInstrumento(estudioDto.get_tipoInstrumento());
            estudio_modificar.setFechaInicio(estudioDto.get_fechaInicio());
            estudio_modificar.setFechaFin(estudioDto.get_fechaFin());
            estudio_modificar.set_estatus(estudioDto.get_estatus());
            daoEstudio.update(estudio_modificar);
            return Response.ok().entity(estudio_modificar).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteEstudio/{id}")
    public Response eliminarEstudio(@PathParam("id") long id){

        DaoEstudio daoEstudio = new DaoEstudio();
        Estudio estudio_eliminar = daoEstudio.find(id, Estudio.class);

        if(estudio_eliminar != null){
            daoEstudio.delete(estudio_eliminar);
            return Response.ok().entity(estudio_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
