package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Usuario;

public class MapperProducto {

    /**
     * Esta clase mapper se encargara de ejecutar las tranformaciones de dto a entity
     * para las operaciones de insert, uptade de producto
     * @author Emanuel Di Cristofaro
     */

    public static Producto mapDtoToEntityInsert(ProductoDto productoDto){

        Producto productoEntity = new Producto();
        DaoMarca daoMarca = new DaoMarca();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoUsuario daoUsuario = new DaoUsuario();

        productoEntity.set_nombre(productoDto.getNombre());
        productoEntity.set_descripcion(productoDto.getDescripcion());
        productoEntity.set_estatus(productoDto.getEstatus());
        Marca marca = daoMarca.find(productoDto.getMarcaDto().getId(), Marca.class);
        Subcategoria subcategoria = daoSubcategoria.find(productoDto.getSubcategoriaDto().getId(), Subcategoria.class);
        Usuario usuario = daoUsuario.find(productoDto.getUsuarioDto().getId(), Usuario.class);
        productoEntity.set_marca(marca);
        productoEntity.set_subcategoria(subcategoria);
        productoEntity.set_usuario(usuario);

        return productoEntity;

    }

    public static ProductoDto mapEntityToDto(Producto productoEntity){

        ProductoDto productoDto = new ProductoDto();

        productoDto.setNombre(productoEntity.get_nombre());
        productoDto.setDescripcion(productoEntity.get_descripcion());
        productoDto.setEstatus(productoEntity.get_estatus());

        return productoDto;
    }
}
