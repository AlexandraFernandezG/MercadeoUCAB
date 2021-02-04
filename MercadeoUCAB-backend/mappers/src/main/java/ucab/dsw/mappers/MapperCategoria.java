package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;

public class MapperCategoria {

    /**
     * Esta clase mapper se encargara de ejecutar las tranformaciones de dto a entity
     * para las operaciones de insert, uptade de categoria
     * @author Emanuel Di Cristofaro
     */

    public static Categoria mapDtoToEntityInsert(CategoriaDto categoriaDto)
    {
        Categoria entityCategoria= new Categoria();

        entityCategoria.set_nombre(categoriaDto.getNombre());
        entityCategoria.set_descripcion(categoriaDto.getDescripcion());
        entityCategoria.set_estatus(categoriaDto.getEstatus());

        return entityCategoria;
    }

    public static Categoria mapDtoToEntityUpdate(long id, CategoriaDto categoriaDto )
    {
        DaoCategoria daoCategoria = new DaoCategoria();

        Categoria entityCategoria = daoCategoria.find(id, Categoria.class);

        entityCategoria.set_nombre(categoriaDto.getNombre());
        entityCategoria.set_descripcion(categoriaDto.getDescripcion());
        entityCategoria.set_estatus(categoriaDto.getEstatus());

        return entityCategoria;

    }

    public static CategoriaDto mapEntityToDto( Categoria entityCategoria ) throws PruebaExcepcion {
        CategoriaDto categoriaDto = new CategoriaDto();

        categoriaDto.setId(entityCategoria.get_id());
        categoriaDto.setNombre(entityCategoria.get_nombre());
        categoriaDto.setDescripcion(entityCategoria.get_descripcion());
        categoriaDto.setEstatus(entityCategoria.get_estatus());

        return categoriaDto;
    }
}
