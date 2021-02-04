package ucab.dsw.comando.Categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarCategoriaComando extends ComandoBase {

    public long id;
    public JsonObject categoriaObj;

    public ConsultarCategoriaComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
        Categoria categoria = daoCategoria.find(id, Categoria.class);

        categoriaObj = Json.createObjectBuilder().add("id", categoria.get_id())
                .add("nombre", categoria.get_nombre())
                .add("descripcion", categoria.get_descripcion())
                .add("estatus", categoria.get_estatus()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Categoria encontrada")
                .add("categoria", categoriaObj).build();

        return resultado;
    }
}
