package ucab.dsw.comando.Subcategoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperSubcategoria;

import javax.json.Json;
import javax.json.JsonObject;

public class AddSubCategoriaComando extends ComandoBase {

    public Subcategoria subcategoria;
    public JsonObject subcategoriaObj;

    public AddSubCategoriaComando(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    @Override
    public void execute() throws Exception {

        DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
        Subcategoria subcategoriaInsertar = Fabrica.crear(Subcategoria.class);
        DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);

        subcategoriaInsertar.set_nombre(subcategoria.get_nombre());
        subcategoriaInsertar.set_descripcion(subcategoria.get_descripcion());
        subcategoriaInsertar.set_estatus(subcategoria.get_estatus());
        Categoria categoria = daoCategoria.find(subcategoria.get_categoria().get_id(), Categoria.class);
        subcategoriaInsertar.set_categoria(categoria);

        Subcategoria resul = daoSubcategoria.insert(subcategoriaInsertar);
        SubcategoriaDto resultado = MapperSubcategoria.mapEntityToDto(resul);

        subcategoriaObj = Json.createObjectBuilder().add("id", resultado.getId()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","SubCategoria insertada")
                .add("Id de la subcategoria", subcategoriaObj).build();

        return resultado;
    }
}
