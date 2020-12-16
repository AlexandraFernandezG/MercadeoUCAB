package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path( "/registro" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RegistroEncuestadoServicio extends AplicacionBase {
    @POST
    @Path("/nuevoEncuestado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UsuarioDto addEncuestado(UsuarioDto usuarioDto, InformacionDto informacionDto) {
        UsuarioDto resultado = new UsuarioDto();
        try {

            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = new Usuario();

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


        } catch (javax.persistence.PersistenceException ex) {
            String problema = ex.getMessage();
            System.out.print("Usuario ya registrado");

        } catch (Exception ex) {

            String problema = ex.getMessage();
            System.out.print(problema);
        }
        return resultado;
    }


}
