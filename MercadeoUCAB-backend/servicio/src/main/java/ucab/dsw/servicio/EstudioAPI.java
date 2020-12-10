package ucab.dsw.servicio;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.*;

import java.util.ArrayList;
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
    public List<Estudio> estudiosActivos(){
        DaoEstudio daoEstudio = new DaoEstudio();
        List<Estudio> listaEstudios = daoEstudio.findAll(Estudio.class);
        List<Estudio> listaEstudiosActivos = new ArrayList<Estudio>();

        for (Estudio estudio: listaEstudios){

            if(estudio.get_estatus().equals("Activo")){
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

        estudio.set_nombre(estudioDto.getNombre());
        estudio.set_tipoInstrumento(estudioDto.getTipoInstrumento());
        estudio.set_fechaInicio(estudioDto.getFechaInicio());
        estudio.set_fechaFin(estudioDto.getFechaFin());
        estudio.set_estatus(estudioDto.getEstatus());
        SolicitudEstudio solicitudEstudio = new SolicitudEstudio(estudioDto.getSolicitudEstudioDto().getId());
        Usuario usuario = new Usuario(estudioDto.getUsuarioDto().getId());
        estudio.set_solicitudEstudio(solicitudEstudio);
        estudio.set_usuario(usuario);
        daoEstudio.insert(estudio);

        return estudio;

    }

    @PUT
    @Path("/updateEstudio/{id}")
    public Response modificarEstudio(@PathParam("id") long id, EstudioDto estudioDto){

        DaoEstudio daoEstudio = new DaoEstudio();
        Estudio estudio_modificar = daoEstudio.find(id, Estudio.class);

        if(estudio_modificar != null){

            estudio_modificar.set_nombre(estudioDto.getNombre());
            estudio_modificar.set_tipoInstrumento(estudioDto.getTipoInstrumento());
            estudio_modificar.set_fechaInicio(estudioDto.getFechaInicio());
            estudio_modificar.set_fechaFin(estudioDto.getFechaFin());
            estudio_modificar.set_estatus(estudioDto.getEstatus());
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

            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            List<PreguntaEstudio> listaPreguntaEstudio = daoPreguntaEstudio.findAll(PreguntaEstudio.class);

            for (PreguntaEstudio preguntaEstudio: listaPreguntaEstudio){

                if(preguntaEstudio.get_estudio().get_id() == id){
                    daoPreguntaEstudio.delete(preguntaEstudio);
                }
            }

            daoEstudio.delete(estudio_eliminar);
            return Response.ok().entity(estudio_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
