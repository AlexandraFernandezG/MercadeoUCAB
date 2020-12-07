package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoNivelAcademico;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.NivelAcademicoDto;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.NivelAcademico;
import ucab.dsw.entidades.SolicitudEstudio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/nivelAcademico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class NivelAcademicoAPI extends AplicacionBase{

    @GET
    @Path("/allNivelAcademico")
    public List<NivelAcademico> listarNivelAcademico(){
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        return daoNivelAcademico.findAll(NivelAcademico.class);
    }

    @GET
    @Path("/consultarNivelAcademico/{id}")
    public NivelAcademico consultarNivelAcademico(@PathParam("id") long id){
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        return daoNivelAcademico.find(id, NivelAcademico.class);
    }

    @POST
    @Path("/addNivelAcademico")
    public NivelAcademico addNivelAcademico(NivelAcademicoDto nivelAcademicoDto){

        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        NivelAcademico nivelAcademico = new NivelAcademico();

            nivelAcademico.setDescripcion(nivelAcademicoDto.get_descripcion());
            nivelAcademico.set_estatus(nivelAcademicoDto.get_estatus());
            daoNivelAcademico.insert(nivelAcademico);

            return nivelAcademico;
    }

    @PUT
    @Path("/updateNivelAcademico/{id}")
    public Response updateNivelAcademico(@PathParam("id") long id, NivelAcademicoDto nivelAcademicoDto){

        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        NivelAcademico nivelAcademico_modificar = daoNivelAcademico.find(id, NivelAcademico.class);

        if (nivelAcademico_modificar != null) {

                nivelAcademico_modificar.setDescripcion(nivelAcademicoDto.get_descripcion());
                nivelAcademico_modificar.set_estatus(nivelAcademicoDto.get_estatus());
                daoNivelAcademico.update(nivelAcademico_modificar);
                return Response.ok().entity(nivelAcademicoDto).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteNivelAcademico/{id}")
    public Response eliminarNivelAcademico(@PathParam("id") long id){

        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        NivelAcademico nivelAcademico_eliminar = daoNivelAcademico.find(id, NivelAcademico.class);

        if(nivelAcademico_eliminar != null){

            DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
            DaoInformacion daoInformacion = new DaoInformacion();
            List<SolicitudEstudio> listaSolicitudEstudio = daoSolicitudEstudio.findAll(SolicitudEstudio.class);
            List<Informacion> listaInformacion = daoInformacion.findAll(Informacion.class);

            for (SolicitudEstudio solicitudEstudio: listaSolicitudEstudio){

                //Las operacion del if puede cambiar
                if(solicitudEstudio.get_nivelAcademico().get_id() == id){
                    daoSolicitudEstudio.delete(solicitudEstudio);
                }
            }

            for (Informacion informacion: listaInformacion){

                //Las operacion del if puede cambiar
                if(informacion.getNivelAcademico().get_id() == id){
                    daoInformacion.delete(informacion);
                }

            }

            daoNivelAcademico.delete(nivelAcademico_eliminar);
            return Response.ok().entity(nivelAcademico_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();

        }

    }
}
