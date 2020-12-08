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

    //Listar niveles academicos
    @GET
    @Path("/allNivelAcademico")
    public List<NivelAcademico> listarNivelAcademico(){
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        return daoNivelAcademico.findAll(NivelAcademico.class);
    }

    //Consultar un nivel academico
    @GET
    @Path("/consultarNivelAcademico/{id}")
    public NivelAcademico consultarNivelAcademico(@PathParam("id") long id){
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        return daoNivelAcademico.find(id, NivelAcademico.class);
    }

    //Agregar un nivel academico
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

    //Actualizar un nivel academico
    @PUT
    @Path("/updateNivelAcademico/{id}")
    public Response updateNivelAcademico(@PathParam("id") long id, NivelAcademicoDto nivelAcademicoDto){

        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        NivelAcademico nivelAcademico_modificar = daoNivelAcademico.find(id, NivelAcademico.class);

        if (nivelAcademico_modificar != null) {

                nivelAcademico_modificar.setDescripcion(nivelAcademicoDto.get_descripcion());
                nivelAcademico_modificar.set_estatus(nivelAcademicoDto.get_estatus());
                daoNivelAcademico.update(nivelAcademico_modificar);
                return Response.ok().entity(nivelAcademico_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Eliminar un nivel academico
    @DELETE
    @Path("/deleteNivelAcademico/{id}")
    public Response eliminarNivelAcademico(@PathParam("id") long id){

        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        NivelAcademico nivelAcademico_eliminar = daoNivelAcademico.find(id, NivelAcademico.class);

        if(nivelAcademico_eliminar != null){

            daoNivelAcademico.delete(nivelAcademico_eliminar);
            return Response.ok().entity(nivelAcademico_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();

        }

    }
}
