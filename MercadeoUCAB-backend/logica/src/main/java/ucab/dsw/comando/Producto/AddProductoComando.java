package ucab.dsw.comando.Producto;

import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperProducto;

import javax.json.Json;
import javax.json.JsonObject;

public class AddProductoComando extends ComandoBase {

    public Producto producto;
    public JsonObject productoObj;

    @Override
    public void execute() throws Exception {

        DaoProducto daoProducto = Fabrica.crear(DaoProducto.class);
        Producto productoInsertar = Fabrica.crear(Producto.class);
        DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
        DaoMarca daoMarca = Fabrica.crear(DaoMarca.class);
        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);

        productoInsertar.set_nombre(producto.get_nombre());
        productoInsertar.set_descripcion(producto.get_descripcion());
        productoInsertar.set_estatus(producto.get_estatus());
        Usuario usuario = daoUsuario.find(producto.get_usuario().get_id(), Usuario.class);
        Subcategoria subcategoria = daoSubcategoria.find(producto.get_subcategoria().get_id(),Subcategoria.class);
        Marca marca = daoMarca.find(producto.get_marca().get_id() ,Marca.class);
        productoInsertar.set_usuario(usuario);
        productoInsertar.set_subcategoria(subcategoria);
        productoInsertar.set_marca(marca);
        Producto resul = daoProducto.insert(productoInsertar);

        ProductoDto resultado = MapperProducto.mapEntityToDto(resul);

        productoObj = Json.createObjectBuilder().add("id", resultado.getId()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Producto registrado exitosamente")
                .add("id", productoObj).build();

        return resultado;
    }
}
