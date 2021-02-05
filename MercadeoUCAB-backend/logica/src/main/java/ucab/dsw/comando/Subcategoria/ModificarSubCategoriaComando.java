package ucab.dsw.comando.Subcategoria;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class ModificarSubCategoriaComando extends ComandoBase {

    public long id;
    public Subcategoria subcategoria;
    public JsonObject subcategoriaObj;

    public ModificarSubCategoriaComando(long id, Subcategoria subcategoria) {
        this.id = id;
        this.subcategoria = subcategoria;
    }

    @Override
    public void execute() throws Exception {

        DaoSubcategoria dao = Fabrica.crear(DaoSubcategoria.class);
        Subcategoria subcategoria_modificar = dao.find(id, Subcategoria.class);

        subcategoria_modificar.set_nombre(subcategoria.get_nombre());
        subcategoria_modificar.set_descripcion(subcategoria.get_descripcion());
        subcategoria_modificar.set_estatus(subcategoria.get_estatus());
        dao.update(subcategoria_modificar);

        subcategoriaObj = Json.createObjectBuilder().add("id", subcategoria_modificar.get_id())
                .add("nombre", subcategoria_modificar.get_nombre())
                .add("descripcion", subcategoria_modificar.get_descripcion())
                .add("estatus", subcategoria_modificar.get_estatus()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Modificada la subcategoria escogida")
                .add("Categoria", subcategoriaObj).build();

        return resultado;
    }
}
