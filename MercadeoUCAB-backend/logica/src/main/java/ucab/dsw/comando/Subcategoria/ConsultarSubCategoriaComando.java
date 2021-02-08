package ucab.dsw.comando.Subcategoria;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarSubCategoriaComando extends ComandoBase {

    public long id;
    public JsonObject subcategoriaObj;

    public ConsultarSubCategoriaComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);

        Subcategoria subcategoria = daoSubcategoria.find(id, Subcategoria.class);

        subcategoriaObj = Json.createObjectBuilder().add("id", subcategoria.get_id())
                .add("nombre", subcategoria.get_nombre())
                .add("descripcion", subcategoria.get_descripcion())
                .add("estatus", subcategoria.get_estatus()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("Categoria", subcategoriaObj).build();

        return resultado;
    }
}
