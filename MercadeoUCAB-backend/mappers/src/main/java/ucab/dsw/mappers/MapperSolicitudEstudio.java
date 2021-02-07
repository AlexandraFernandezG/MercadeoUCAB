package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

public class MapperSolicitudEstudio {

    /**
     * Esta clase mapper se encargara de ejecutar las tranformaciones de dto a entity
     * para las operaciones de insert, uptade de solicitud de estudio
     * @author Emanuel Di Cristofaro
     */
    
    public static SolicitudEstudio mapDtoToEntityInsert(SolicitudEstudioDto solicitudEstudioDto){

       SolicitudEstudio solicitudEstudioEntity = new SolicitudEstudio();

        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoProducto daoProducto = new DaoProducto();
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();

        solicitudEstudioEntity.set_descripcion(solicitudEstudioDto.getDescripcion());
        solicitudEstudioEntity.set_genero(solicitudEstudioDto.getGenero());
        solicitudEstudioEntity.set_edadMaxima(solicitudEstudioDto.getEdadMaxima());
        solicitudEstudioEntity.set_edadMinima(solicitudEstudioDto.getEdadMinima());
        solicitudEstudioEntity.set_estadoCivil(solicitudEstudioDto.getEstadoCivil());
        solicitudEstudioEntity.set_disponibilidadEnLinea(solicitudEstudioDto.getDisponibilidadEnLinea());
        solicitudEstudioEntity.set_cantidadPersonas(solicitudEstudioDto.getCantidadPersonas());
        solicitudEstudioEntity.set_cantidadHijos(solicitudEstudioDto.getCantidadHijos());
        solicitudEstudioEntity.set_generoHijos(solicitudEstudioDto.getGeneroHijos());
        solicitudEstudioEntity.set_edadMinimaHijos(solicitudEstudioDto.getEdadMinimaHijos());
        solicitudEstudioEntity.set_edadMaximaHijos(solicitudEstudioDto.getEdadMaximaHijos());
        solicitudEstudioEntity.set_estado(solicitudEstudioDto.getEstado());
        solicitudEstudioEntity.set_estatus(solicitudEstudioDto.getEstatus());
        NivelEconomico nivelEconomico = daoNivelEconomico.find(solicitudEstudioDto.getNivelEconomicoDto().getId(), NivelEconomico.class);
        Usuario usuario = daoUsuario.find(solicitudEstudioDto.getUsuarioDto().getId(), Usuario.class);
        Producto producto = daoProducto.find(solicitudEstudioDto.getProductoDto().getId(), Producto.class);
        Ocupacion ocupacion = daoOcupacion.find(solicitudEstudioDto.getOcupacionDto().getId(), Ocupacion.class);
        NivelAcademico nivelAcademico = daoNivelAcademico.find(solicitudEstudioDto.getNivelAcademicoDto().getId(), NivelAcademico.class);
        solicitudEstudioEntity.set_nivelEconomico(nivelEconomico);
        solicitudEstudioEntity.set_usuario(usuario);
        solicitudEstudioEntity.set_producto(producto);
        solicitudEstudioEntity.set_ocupacion(ocupacion);
        solicitudEstudioEntity.set_nivelAcademico(nivelAcademico);

        return solicitudEstudioEntity;
    }

    public static SolicitudEstudioDto mapEntityToDto( SolicitudEstudio solicitudEstudioEntity ) throws PruebaExcepcion {

        SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();

        solicitudEstudioDto.setId(solicitudEstudioEntity.get_id());
        solicitudEstudioDto.setGenero(solicitudEstudioEntity.get_genero());
        solicitudEstudioDto.setEdadMaxima(solicitudEstudioEntity.get_edadMaxima());
        solicitudEstudioDto.setEdadMinima(solicitudEstudioEntity.get_edadMinima());
        solicitudEstudioDto.setEstadoCivil(solicitudEstudioEntity.get_estadoCivil());
        solicitudEstudioDto.setDisponibilidadEnLinea(solicitudEstudioEntity.get_disponibilidadEnLinea());
        solicitudEstudioDto.setCantidadPersonas(solicitudEstudioEntity.get_cantidadPersonas());
        solicitudEstudioDto.setCantidadHijos(solicitudEstudioEntity.get_cantidadHijos());
        solicitudEstudioDto.setGeneroHijos(solicitudEstudioEntity.get_generoHijos());
        solicitudEstudioDto.setEdadMinimaHijos(solicitudEstudioEntity.get_edadMinimaHijos());
        solicitudEstudioDto.setEdadMaximaHijos(solicitudEstudioEntity.get_edadMaximaHijos());
        solicitudEstudioDto.setEstado(solicitudEstudioEntity.get_estado());
        solicitudEstudioDto.setEstatus(solicitudEstudioEntity.get_estatus());

        return solicitudEstudioDto;
    }
}
