package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

public class MapperInformacion {

    /**
     * Esta clase mapper se encargara de ejecutar las tranformaciones de dto a entity
     * para las operaciones de insert, uptade de Informacion
     * @author Emanuel Di Cristofaro
     */

    public static Informacion mapDtoToEntityInsert(InformacionDto informacionDto){

        Informacion informacionEntity = new Informacion();
        DaoLugar daoLugar = new DaoLugar();
        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        DaoUsuario daoUsuario = new DaoUsuario();

        informacionEntity.set_cedula(informacionDto.getCedula());
        informacionEntity.set_primerNombre(informacionDto.getPrimerNombre());
        informacionEntity.set_segundoNombre(informacionDto.getSegundoNombre());
        informacionEntity.set_primerApellido(informacionDto.getPrimerApellido());
        informacionEntity.set_segundoApellido(informacionDto.getSegundoApellido());
        informacionEntity.set_genero(informacionDto.getGenero());
        informacionEntity.set_fechaNacimiento(informacionDto.getFechaNacimiento());
        informacionEntity.set_estadoCivil(informacionDto.getEstadoCivil());
        informacionEntity.set_disponibilidadEnLinea(informacionDto.getDisponibilidadEnLinea());
        informacionEntity.set_cantidadPersonas(informacionDto.getCantidadPersonas());
        informacionEntity.set_estatus(informacionDto.getEstatus());
        Lugar lugar = daoLugar.find(informacionDto.getLugarDto().getId(), Lugar.class);
        informacionEntity.set_lugar(lugar);
        NivelEconomico nivelEconomico = daoNivelEconomico.find(informacionDto.getNivelEconomicoDto().getId(), NivelEconomico.class);
        informacionEntity.set_nivelEconomico(nivelEconomico);
        NivelAcademico nivelAcademico = daoNivelAcademico.find(informacionDto.getNivelAcademicoDto().getId(), NivelAcademico.class);
        informacionEntity.set_nivelAcademico(nivelAcademico);
        Ocupacion ocupacion = daoOcupacion.find(informacionDto.getOcupacionDto().getId(), Ocupacion.class);
        informacionEntity.set_ocupacion(ocupacion);
        Usuario usuario = daoUsuario.find(informacionDto.getUsuarioDto().getId(), Usuario.class);
        informacionEntity.set_usuario(usuario);

        return informacionEntity;
    }

    public static InformacionDto mapEntityToDto(Informacion informacionEntity) throws PruebaExcepcion {

        InformacionDto informacionDto = new InformacionDto();

        informacionDto.setId(informacionEntity.get_id());
        informacionDto.setCedula(informacionEntity.get_cedula());
        informacionDto.setPrimerNombre(informacionEntity.get_primerNombre());
        informacionDto.setSegundoNombre(informacionEntity.get_segundoNombre());
        informacionDto.setPrimerNombre(informacionEntity.get_primerNombre());
        informacionDto.setSegundoApellido(informacionEntity.get_segundoApellido());
        informacionDto.setGenero(informacionEntity.get_genero());
        informacionDto.setFechaNacimiento(informacionEntity.get_fechaNacimiento());
        informacionDto.setEstadoCivil(informacionEntity.get_estadoCivil());
        informacionDto.setDisponibilidadEnLinea(informacionEntity.get_disponibilidadEnLinea());
        informacionDto.setCantidadPersonas(informacionEntity.get_cantidadPersonas());
        informacionDto.setEstatus(informacionEntity.get_estatus());

        return informacionDto;
    }
}
