package ucab.dsw.servicio;

import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.entidades.*;
import ucab.dsw.accesodatos.DaoInformacion;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/informacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class InformacionAPI extends AplicacionBase{

    //Consultar una información
    @GET
    @Path("/consultarInformacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Informacion consultarInformacion(@PathParam("id") long id) throws NullPointerException{

        DaoInformacion daoInformacion = new DaoInformacion();

        try {
            return daoInformacion.find(id, Informacion.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Agregar una información
    @POST
    @Path("/addInformacion")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public InformacionDto addInformacion(InformacionDto informacionDto){

        InformacionDto resultado = new InformacionDto();

        try {
            DaoInformacion daoInformacion = new DaoInformacion();
            Informacion informacion = new Informacion();

            informacion.set_cantidadPersonas(informacionDto.getCantidadPersonas());
            informacion.set_cedula(informacionDto.getCedula());
            informacion.set_disponibilidadEnLinea(informacionDto.getDisponibilidadEnLinea());
            informacion.set_estadoCivil(informacionDto.getEstadoCivil());
            informacion.set_fechaNacimiento(informacionDto.getFechaNacimiento());
            informacion.set_primerApellido(informacionDto.getPrimerApellido());
            informacion.set_primerNombre(informacionDto.getPrimerNombre());
            informacion.set_segundoApellido(informacionDto.getSegundoApellido());
            informacion.set_segundoNombre(informacionDto.getSegundoNombre());
            informacion.set_estatus(informacionDto.getEstatus());
            Usuario usuario = new Usuario(informacionDto.getUsuarioDto().getId());
            informacion.set_usuario(usuario);
            Ocupacion ocupacion = new Ocupacion(informacionDto.getOcupacionDto().getId());
            informacion.set_ocupacion(ocupacion);
            NivelAcademico nivelAcademico = new NivelAcademico(informacionDto.getNivelAcademicoDto().getId());
            informacion.set_nivelAcademico(nivelAcademico);
            NivelEconomico nivelEconomico = new NivelEconomico(informacionDto.getNivelEconomicoDto().getId());
            informacion.set_nivelEconomico(nivelEconomico);
            Lugar lugar = new Lugar(informacionDto.getLugarDto().getId());
            informacion.set_lugar(lugar);
            Informacion resul = daoInformacion.insert(informacion);
            resultado.setId(resul.get_id());

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);

        }

        return resultado;
    }

    //Actualizar Producto
    @PUT
    @Path("/updateInformacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarInformacion(@PathParam("id") long id, InformacionDto informacionDto){

        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion_modificar = daoInformacion.find(id, Informacion.class);

        if (informacion_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            informacion_modificar.set_cantidadPersonas(informacionDto.getCantidadPersonas());
            informacion_modificar.set_cedula(informacionDto.getCedula());
            informacion_modificar.set_disponibilidadEnLinea(informacionDto.getDisponibilidadEnLinea());
            informacion_modificar.set_estadoCivil(informacionDto.getEstadoCivil());
            informacion_modificar.set_fechaNacimiento(informacionDto.getFechaNacimiento());
            informacion_modificar.set_primerApellido(informacionDto.getPrimerApellido());
            informacion_modificar.set_primerNombre(informacionDto.getPrimerNombre());
            informacion_modificar.set_segundoApellido(informacionDto.getSegundoApellido());
            informacion_modificar.set_segundoNombre(informacionDto.getSegundoNombre());
            informacion_modificar.set_estatus(informacionDto.getEstatus());
            Ocupacion ocupacion = new Ocupacion(informacionDto.getOcupacionDto().getId());
            informacion_modificar.set_ocupacion(ocupacion);
            NivelAcademico nivelAcademico = new NivelAcademico(informacionDto.getNivelAcademicoDto().getId());
            informacion_modificar.set_nivelAcademico(nivelAcademico);
            NivelEconomico nivelEconomico = new NivelEconomico(informacionDto.getNivelEconomicoDto().getId());
            informacion_modificar.set_nivelEconomico(nivelEconomico);
            Lugar lugar = new Lugar(informacionDto.getLugarDto().getId());
            informacion_modificar.set_lugar(lugar);
            daoInformacion.update(informacion_modificar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(informacion_modificar).build();

    }

    // Elimina una información
    @DELETE
    @Path("/deleteInformacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteInformacion(@PathParam("id") long id){

        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion_eliminar = daoInformacion.find(id, Informacion.class);

        if(informacion_eliminar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            daoInformacion.delete(informacion_eliminar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(informacion_eliminar).build();

    }

    //Actualizar estatus de una información
    @PUT
    @Path("/estatusInformacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusInformacion(@PathParam("id") long id, InformacionDto informacionDto){

        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion_modificar = daoInformacion.find(id, Informacion.class);

        if (informacion_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            informacion_modificar.set_estatus(informacionDto.getEstatus());
            daoInformacion.update(informacion_modificar);


        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(informacion_modificar).build();

    }


}