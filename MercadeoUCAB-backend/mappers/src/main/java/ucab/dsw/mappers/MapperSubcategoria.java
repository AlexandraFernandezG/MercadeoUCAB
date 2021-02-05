package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;

public class MapperSubcategoria {

    /**
     * Esta clase mapper se encargara de ejecutar las tranformaciones de dto a entity
     * para las operaciones de insert, uptade de subcategoria
     * @author Emanuel Di Cristofaro
     */

    public static Subcategoria mapDtoToEntityInsert(SubcategoriaDto subcategoriaDto){

        Subcategoria entitySubcategoria = new Subcategoria();
        DaoCategoria daoCategoria = new DaoCategoria();

        entitySubcategoria.set_nombre(subcategoriaDto.getNombre());
        entitySubcategoria.set_descripcion(subcategoriaDto.getDescripcion());
        entitySubcategoria.set_estatus(subcategoriaDto.getEstatus());
        Categoria categoria = daoCategoria.find(subcategoriaDto.getCategoriaDto().getId(), Categoria.class);
        entitySubcategoria.set_categoria(categoria);

        return entitySubcategoria;

    }

    public static Subcategoria mapDtoToEntityUpdate(long id, SubcategoriaDto subcategoriaDto){

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        Subcategoria entitySubcategoria = new Subcategoria();

        entitySubcategoria.set_nombre(subcategoriaDto.getNombre());
        entitySubcategoria.set_descripcion(subcategoriaDto.getDescripcion());
        entitySubcategoria.set_estatus(subcategoriaDto.getEstatus());

        return entitySubcategoria;
    }

    public static SubcategoriaDto mapEntityToDto(Subcategoria entitySubcategoria) throws PruebaExcepcion {

        SubcategoriaDto subcategoriaDto = new SubcategoriaDto();

        subcategoriaDto.setId(entitySubcategoria.get_id());
        subcategoriaDto.setNombre(entitySubcategoria.get_nombre());
        subcategoriaDto.setDescripcion(entitySubcategoria.get_descripcion());
        subcategoriaDto.setEstatus(entitySubcategoria.get_estatus());

        return subcategoriaDto;

    }
}
