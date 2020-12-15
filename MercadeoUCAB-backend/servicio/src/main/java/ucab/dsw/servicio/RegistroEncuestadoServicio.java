package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/registro" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RegistroEncuestadoServicio extends AplicacionBase {
    @POST
    @Path("/addUsuarioEncuestado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UsuarioDto addEncuestado(UsuarioDto usuarioDto) {
        UsuarioDto resultado = new UsuarioDto();
        try {

            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = new Usuario();

            usuario.set_nombre(usuarioDto.getNombreUsuario());
            usuario.set_correoelectronico(usuarioDto.getCorreo());
            usuario.set_estatus(usuarioDto.getEstatus());
            Rol rol = new Rol(usuarioDto.getRol().getId());
            usuario.set_rol(rol);
            usuario.set_codigoRecuperacion(usuarioDto.getCodigoRecuperacion());
            Usuario resul = dao.insert(usuario);
            resultado.setId(resul.get_id());
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.addEntryToLdap(usuarioDto);
        } catch (Exception ex) {

            String problema = ex.getMessage();
            System.out.print(problema);
        }
        return resultado;
    }

    //Agregar una información
    @POST
    @Path("/{id}/addInformacionEncuestado")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public InformacionDto addInformacion(InformacionDto informacionDto, @PathParam("id") long id){

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
            Usuario usuario = new Usuario(id);
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

}
