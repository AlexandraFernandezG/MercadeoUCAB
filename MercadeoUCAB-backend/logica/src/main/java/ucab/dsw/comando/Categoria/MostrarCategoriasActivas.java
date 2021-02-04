package ucab.dsw.comando.Categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class MostrarCategoriasActivas extends ComandoBase {

    public JsonArrayBuilder categorias = Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
        List<Categoria> listaCategorias = daoCategoria.findAll(Categoria.class);
        List<Categoria> listaCategoriasActivas = new ArrayList<Categoria>();

        for (Categoria categoria : listaCategorias) {

            if (categoria.get_estatus().equals("Activo")) {
                listaCategoriasActivas.add(categoria);
            }
        }

        for(Categoria obj: listaCategoriasActivas){

            JsonObject categoria = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre", obj.get_nombre())
                    .add("descripcion", obj.get_descripcion())
                    .add("estatus", obj.get_estatus()).build();

            categorias.add(categoria);
        }


    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder().add("mensaje","Todas las categorias activas")
                .add("estado",200)
                .add("Categorias", categorias).build();

        return resultado;
    }
}
