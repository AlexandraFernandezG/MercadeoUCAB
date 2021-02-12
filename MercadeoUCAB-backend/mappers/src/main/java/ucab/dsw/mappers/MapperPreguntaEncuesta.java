package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;

public class MapperPreguntaEncuesta {

    /**
     * Esta clase mapper se encargara de ejecutar las tranformaciones de dto a entity
     * para las operaciones de insert, uptade de preguntaEncuesta
     * @author Emanuel Di Cristofaro
     */

    public static PreguntaEncuesta mapDtoToEntityInsert(PreguntaEncuestaDto preguntaEncuestaDto){

        PreguntaEncuesta preguntaEncuestaEntity = new PreguntaEncuesta();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();

        preguntaEncuestaEntity.set_descripcion(preguntaEncuestaDto.getDescripcion());
        preguntaEncuestaEntity.set_tipoPregunta(preguntaEncuestaDto.getTipoPregunta());
        preguntaEncuestaEntity.set_estatus(preguntaEncuestaDto.getEstatus());
        Usuario usuario = daoUsuario.find(preguntaEncuestaDto.getUsuarioDto().getId(), Usuario.class);
        preguntaEncuestaEntity.set_usuario(usuario);
        Subcategoria subcategoria = daoSubcategoria.find(preguntaEncuestaDto.getSubcategoriaDto().getId(), Subcategoria.class);
        preguntaEncuestaEntity.set_subcategoria(subcategoria);

        return preguntaEncuestaEntity;

    }

    public static PreguntaEncuestaDto mapEntityToDto(PreguntaEncuesta preguntaEncuestaEntity) throws PruebaExcepcion {

        PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto();

        preguntaEncuestaDto.setId(preguntaEncuestaEntity.get_id());
        preguntaEncuestaDto.setDescripcion(preguntaEncuestaEntity.get_descripcion());
        preguntaEncuestaDto.setTipoPregunta(preguntaEncuestaEntity.get_tipoPregunta());
        preguntaEncuestaDto.setEstatus(preguntaEncuestaEntity.get_estatus());

        return preguntaEncuestaDto;
    }
}
