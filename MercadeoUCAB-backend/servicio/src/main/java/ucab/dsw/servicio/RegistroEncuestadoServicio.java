package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path( "/registro" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RegistroEncuestadoServicio extends AplicacionBase {

    private static Logger logger = LoggerFactory.getLogger(RegistroEncuestadoServicio.class);

    /**
     * Este método permite insertar un encuestado
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el encuestado insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una categoria duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param usuarioDto el objeto usuario que se desee insertar.
     * @param informacionDto el objeto informacion que se desee insertar.
     */
    @POST
    @Path("/nuevoEncuestado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEncuestado(UsuarioDto usuarioDto, InformacionDto informacionDto) {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un nuevo usuario");
        UsuarioDto resultado = Fabrica.crear(UsuarioDto.class);
        JsonObject dataObject;

        try {

            DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
            Usuario usuario = Fabrica.crear(Usuario.class);

            usuario.set_nombre(usuarioDto.getNombreUsuario());
            usuario.set_correoelectronico(usuarioDto.getCorreo());
            usuario.set_estatus(usuarioDto.getEstatus());
            Rol rol = new Rol(4);
            usuario.set_rol(rol);
            usuario.set_codigoRecuperacion(usuarioDto.getCodigoRecuperacion());
            Usuario resul = dao.insert(usuario);
            long idUsuario = resul.get_id();
            resultado.setId(resul.get_id());
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.addEntryToLdap(usuarioDto);

            //Insertar la información del encuestado

            InformacionDto resultadoInformacion = new InformacionDto();
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
            Usuario usuarioInformacion = new Usuario(idUsuario);
            informacion.set_usuario(usuarioInformacion);
            //Ocupacion ocupacion = new Ocupacion(informacionDto.getOcupacionDto().getId());
            //informacion.set_ocupacion(ocupacion);
            NivelAcademico nivelAcademico = new NivelAcademico(informacionDto.getNivelAcademicoDto().getId());
            informacion.set_nivelAcademico(nivelAcademico);
            NivelEconomico nivelEconomico = new NivelEconomico(informacionDto.getNivelEconomicoDto().getId());
            informacion.set_nivelEconomico(nivelEconomico);
            Lugar lugar = new Lugar(informacionDto.getLugarDto().getId());
            informacion.set_lugar(lugar);
            Informacion resulInformacion = daoInformacion.insert(informacion);
            resultado.setId(resulInformacion.get_id());

            logger.debug("Saliendo del método que agrega un nuevo usuario");
            return Response.status(Response.Status.OK).entity(resultado).build();

        } catch (PersistenceException | DatabaseException ex){

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (PruebaExcepcion ex) {

            logger.error("Código de error: " + 402 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 402).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }


}
