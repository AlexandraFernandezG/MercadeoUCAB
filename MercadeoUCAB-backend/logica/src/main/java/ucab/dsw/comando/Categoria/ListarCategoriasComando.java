package ucab.dsw.comando.Categoria;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ListarCategoriasComando extends ComandoBase {

    public JsonArrayBuilder categorias = Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
        List<Categoria> listaCategorias = daoCategoria.findAll(Categoria.class);

        for(Categoria obj: listaCategorias){

            JsonObject categoria = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre", obj.get_nombre())
                    .add("descripcion", obj.get_descripcion())
                    .add("estatus", obj.get_estatus()).build();

            categorias.add(categoria);
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject resultado = Json.createObjectBuilder().add("mensaje","Todas las categorias listadas")
                .add("estado",200)
                .add("categorias", categorias).build();

        return resultado;
    }
}
