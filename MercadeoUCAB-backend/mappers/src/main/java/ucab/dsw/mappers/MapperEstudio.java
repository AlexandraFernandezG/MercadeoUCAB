package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;

public class MapperEstudio {

    /**
     * Esta clase mapper se encargara de ejecutar las tranformaciones de dto a entity
     * para las operaciones de insert, uptade de estudio
     * @author Emanuel Di Cristofaro
     */

    public static Estudio mapDtoToEntityInsert(EstudioDto estudioDto)
    {
        Estudio entityEstudio = new Estudio();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();

        entityEstudio.set_nombre(estudioDto.getNombre());
        entityEstudio.set_tipoInstrumento(estudioDto.getTipoInstrumento());
        entityEstudio.set_observaciones(estudioDto.getObservaciones());
        entityEstudio.set_fechaInicio(estudioDto.getFechaInicio());
        entityEstudio.set_fechaFin(estudioDto.getFechaFin());
        entityEstudio.set_estado(estudioDto.getEstado());
        entityEstudio.set_estatus(estudioDto.getEstatus());
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(estudioDto.getSolicitudEstudioDto().getId(), SolicitudEstudio.class);
        Usuario usuario = daoUsuario.find(estudioDto.getUsuarioDto().getId(), Usuario.class);
        entityEstudio.set_solicitudEstudio(solicitudEstudio);
        entityEstudio.set_usuario(usuario);

        return entityEstudio;
    }

    public static Estudio mapDtoToEntityUpdate(long id, EstudioDto estudioDto )
    {
        DaoEstudio daoEstudio = new DaoEstudio();

        Estudio entityEstudio = daoEstudio.find(id, Estudio.class);

        entityEstudio.set_nombre(estudioDto.getNombre());
        entityEstudio.set_tipoInstrumento(estudioDto.getTipoInstrumento());
        entityEstudio.set_observaciones(estudioDto.getObservaciones());
        entityEstudio.set_fechaInicio(estudioDto.getFechaInicio());
        entityEstudio.set_fechaFin(estudioDto.getFechaFin());
        entityEstudio.set_estado(estudioDto.getEstado());
        entityEstudio.set_estatus(estudioDto.getEstatus());

        return entityEstudio;

    }

    public static EstudioDto mapEntityToDto( Estudio entityEstudio ) throws PruebaExcepcion {

        EstudioDto estudioDto = new EstudioDto();

        estudioDto.setId(entityEstudio.get_id());
        estudioDto.setNombre(entityEstudio.get_nombre());
        estudioDto.setTipoInstrumento(entityEstudio.get_tipoInstrumento());
        estudioDto.setObservaciones(entityEstudio.get_observaciones());
        estudioDto.setFechaInicio(entityEstudio.get_fechaInicio());
        estudioDto.setFechaFin(entityEstudio.get_fechaFin());
        estudioDto.setEstado(entityEstudio.get_estado());
        entityEstudio.set_estatus(entityEstudio.get_estatus());

        return estudioDto;
    }
}
