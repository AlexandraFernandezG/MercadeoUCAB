package ucab.dsw.comando.Categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class EliminarCategoriaComando extends ComandoBase {

    public long id;
    public JsonObject categoriaObj;

    public EliminarCategoriaComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws PruebaExcepcion {

        DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);

        Categoria categoria_eliminar = daoCategoria.find(id, Categoria.class);
        daoCategoria.delete(categoria_eliminar);

        categoriaObj = Json.createObjectBuilder().add("id", categoria_eliminar.get_id())
                .add("nombre", categoria_eliminar.get_nombre())
                .add("descripcion", categoria_eliminar.get_descripcion())
                .add("estatus", categoria_eliminar.get_estatus()).build();
    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Eliminada la categoria escogida")
                .add("Categoria", categoriaObj).build();

        return resultado;
    }
}
