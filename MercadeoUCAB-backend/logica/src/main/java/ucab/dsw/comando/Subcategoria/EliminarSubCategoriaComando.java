package ucab.dsw.comando.Subcategoria;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class EliminarSubCategoriaComando extends ComandoBase {

    public long id;
    public JsonObject subcategoriaObj;

    public EliminarSubCategoriaComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
        Subcategoria subcategoria_eliminar = daoSubcategoria.find(id, Subcategoria.class);

        daoSubcategoria.delete(subcategoria_eliminar);

        subcategoriaObj = Json.createObjectBuilder().add("id", subcategoria_eliminar.get_id())
                .add("nombre", subcategoria_eliminar.get_nombre())
                .add("descripcion", subcategoria_eliminar.get_descripcion())
                .add("estatus", subcategoria_eliminar.get_estatus()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Eliminada la subcategoria escogida")
                .add("Categoria", subcategoriaObj).build();

        return resultado;
    }
}
