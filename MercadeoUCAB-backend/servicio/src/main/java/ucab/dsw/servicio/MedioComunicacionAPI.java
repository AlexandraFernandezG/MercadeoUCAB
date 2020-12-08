package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoMedioComunicacion;
import ucab.dsw.dtos.MedioComunicacionDto;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.MedioComunicacion;
import ucab.dsw.entidades.SolicitudEstudio;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/medioComunicacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class MedioComunicacionAPI extends AplicacionBase{

    // Listar medios
    @GET
    @Path("/allMedioComunicacion")
    public List<MedioComunicacion> listarMedioComunicacion(){
        DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
        return daoMedioComunicacion.findAll(MedioComunicacion.class);
    }

    // Consultar un medio
    @GET
    @Path("/consultarMedioComunicacion/{id}")
    public MedioComunicacion consultarMedioComunicacion(@PathParam("id") long id){
        DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
        return daoMedioComunicacion.find(id, MedioComunicacion.class);
    }

    //Agregar un medio
    @POST
    @Path("/addMedioComunicacion")
    public MedioComunicacion addMedioComunicacion(MedioComunicacionDto medioComunicacionDto){

        DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
        MedioComunicacion medioComunicacion = new MedioComunicacion();

            medioComunicacion.setTipoDeMedio(medioComunicacionDto.get_tipoDeMedio());
            medioComunicacion.set_estatus(medioComunicacionDto.get_estatus());
            Informacion informacion = new Informacion(medioComunicacionDto.get_informacionDto().getId());
            SolicitudEstudio solicitudEstudio = new SolicitudEstudio(medioComunicacionDto.get_solicitudEstudioDto().getId());
            medioComunicacion.setInformacion(informacion);
            medioComunicacion.setSolicitudEstudio(solicitudEstudio);
            daoMedioComunicacion.insert(medioComunicacion);

            return medioComunicacion;
    }

    //Actualizar un medio
    @PUT
    @Path("/updateMedioComunicacion/{id}")
    public Response updateMedioComunicacion(@PathParam("id") long id, MedioComunicacionDto medioComunicacionDto){

        DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
        MedioComunicacion medioComunicacion_modificar = daoMedioComunicacion.find(id, MedioComunicacion.class);

        if(medioComunicacion_modificar != null){

            medioComunicacion_modificar.setTipoDeMedio(medioComunicacionDto.get_tipoDeMedio());
            medioComunicacion_modificar.set_estatus(medioComunicacionDto.get_estatus());
            daoMedioComunicacion.update(medioComunicacion_modificar);
            return Response.ok().entity(medioComunicacion_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Eliminar un medio
    @DELETE
    @Path("/deleteMedioComunicacion/{id}")
    public Response eliminarMedioComunicacion(@PathParam("id") long id){

        DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
        MedioComunicacion medioComunicacion_eliminar = daoMedioComunicacion.find(id, MedioComunicacion.class);

        if (medioComunicacion_eliminar != null) {

            daoMedioComunicacion.delete(medioComunicacion_eliminar);
            return Response.ok().entity(medioComunicacion_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
