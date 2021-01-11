package ucab.dsw.servicio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/informacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class InformacionServicio extends AplicacionBase {

    //Consultar persona con la cedula
    @GET
    @Path("/consultarCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Informacion> consultarInformacionCedula(@PathParam("cedula") int cedula) throws NullPointerException{

        DaoInformacion daoInformacion = new DaoInformacion();

        try {
            return daoInformacion.obtenerInformacion(cedula);

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Listar todos los encuestados
    @GET
    @Path("/listarInformacion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Informacion> listarInformacionEncuestados() throws NullPointerException{

        DaoInformacion daoInformacion = new DaoInformacion();

        try {
            return daoInformacion.findAll(Informacion.class);

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Consultar una información
    @GET
    @Path("/consultarInformacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Informacion consultarInformacion(@PathParam("id") long id) throws NullPointerException {

        DaoInformacion daoInformacion = new DaoInformacion();

        try {
            return daoInformacion.find(id, Informacion.class);

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Agregar una información
    @POST
    @Path("/addInformacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public InformacionDto addInformacion(InformacionDto informacionDto) {

        InformacionDto resultado = new InformacionDto();

        try {

            DaoInformacion daoInformacion = new DaoInformacion();
            Informacion informacion = new Informacion();
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoOcupacion daoOcupacion = new DaoOcupacion();
            DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
            DaoLugar daoLugar = new DaoLugar();

            informacion.set_cedula(informacionDto.getCedula());
            informacion.set_primerNombre(informacionDto.getPrimerNombre());
            informacion.set_segundoNombre(informacionDto.getSegundoNombre());
            informacion.set_primerApellido(informacionDto.getPrimerApellido());
            informacion.set_segundoApellido(informacionDto.getSegundoApellido());
            informacion.set_genero(informacionDto.getGenero());
            informacion.set_fechaNacimiento(informacionDto.getFechaNacimiento());
            informacion.set_estadoCivil(informacionDto.getEstadoCivil());
            informacion.set_disponibilidadEnLinea(informacionDto.getDisponibilidadEnLinea());
            informacion.set_cantidadPersonas(informacionDto.getCantidadPersonas());
            informacion.set_estatus(informacionDto.getEstatus());

            //FKS

            Lugar lugar = daoLugar.find(informacionDto.getLugarDto().getId(), Lugar.class);
            informacion.set_lugar(lugar);

            NivelEconomico nivelEconomico = daoNivelEconomico.find(informacionDto.getNivelEconomicoDto().getId(), NivelEconomico.class);
            informacion.set_nivelEconomico(nivelEconomico);

            NivelAcademico nivelAcademico = daoNivelAcademico.find(informacionDto.getNivelAcademicoDto().getId(), NivelAcademico.class);
            informacion.set_nivelAcademico(nivelAcademico);

            Ocupacion ocupacion = daoOcupacion.find(informacionDto.getOcupacionDto().getId(), Ocupacion.class);
            informacion.set_ocupacion(ocupacion);

            Usuario usuario = daoUsuario.find(informacionDto.getUsuarioDto().getId(), Usuario.class);
            informacion.set_usuario(usuario);

            Informacion resul = daoInformacion.insert(informacion);
            resultado.setId(resul.get_id());

        } catch (Exception ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);

        }

        return resultado;
    }

    //Actualizar Producto
    @PUT
    @Path("/updateInformacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarInformacion(@PathParam("id") long id, InformacionDto informacionDto) {

        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion_modificar = daoInformacion.find(id, Informacion.class);

        if (informacion_modificar == null) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            DaoUsuario daoUsuario = new DaoUsuario();
            DaoOcupacion daoOcupacion = new DaoOcupacion();
            DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
            DaoLugar daoLugar = new DaoLugar();

            informacion_modificar.set_cedula(informacionDto.getCedula());
            informacion_modificar.set_primerNombre(informacionDto.getPrimerNombre());
            informacion_modificar.set_segundoNombre(informacionDto.getSegundoNombre());
            informacion_modificar.set_primerApellido(informacionDto.getPrimerApellido());
            informacion_modificar.set_segundoApellido(informacionDto.getSegundoApellido());
            informacion_modificar.set_genero(informacionDto.getGenero());
            informacion_modificar.set_fechaNacimiento(informacionDto.getFechaNacimiento());
            informacion_modificar.set_estadoCivil(informacionDto.getEstadoCivil());
            informacion_modificar.set_disponibilidadEnLinea(informacionDto.getDisponibilidadEnLinea());
            informacion_modificar.set_cantidadPersonas(informacionDto.getCantidadPersonas());
            informacion_modificar.set_estatus(informacionDto.getEstatus());

            Lugar lugar = daoLugar.find(informacionDto.getLugarDto().getId(), Lugar.class);
            informacion_modificar.set_lugar(lugar);

            NivelEconomico nivelEconomico = daoNivelEconomico.find(informacionDto.getNivelEconomicoDto().getId(), NivelEconomico.class);
            informacion_modificar.set_nivelEconomico(nivelEconomico);

            NivelAcademico nivelAcademico = daoNivelAcademico.find(informacionDto.getNivelAcademicoDto().getId(), NivelAcademico.class);
            informacion_modificar.set_nivelAcademico(nivelAcademico);

            Ocupacion ocupacion = daoOcupacion.find(informacionDto.getOcupacionDto().getId(), Ocupacion.class);
            informacion_modificar.set_ocupacion(ocupacion);

            Usuario usuario = daoUsuario.find(informacionDto.getUsuarioDto().getId(), Usuario.class);
            informacion_modificar.set_usuario(usuario);

            daoInformacion.update(informacion_modificar);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(informacion_modificar).build();

    }

    // Elimina una información
    @DELETE
    @Path("/deleteInformacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInformacion(@PathParam("id") long id) {

        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion_eliminar = daoInformacion.find(id, Informacion.class);

        if (informacion_eliminar == null) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            daoInformacion.delete(informacion_eliminar);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(informacion_eliminar).build();

    }
}