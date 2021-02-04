package ucab.dsw.comando.Categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class ModificarCategoriaComando extends ComandoBase {

    public long id;
    public Categoria categoria;
    public JsonObject categoriaObj;

    public ModificarCategoriaComando(long id, Categoria categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    @Override
    public void execute() throws PruebaExcepcion {

        DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);

        Categoria categoria_modificar = daoCategoria.find(id, Categoria.class);
        categoria_modificar.set_nombre(categoria.get_nombre());
        categoria_modificar.set_descripcion(categoria.get_descripcion());
        categoria_modificar.set_estatus(categoria.get_estatus());
        daoCategoria.update(categoria_modificar);

        categoriaObj = Json.createObjectBuilder().add("id", categoria_modificar.get_id())
                .add("nombre", categoria_modificar.get_nombre())
                .add("descripcion", categoria_modificar.get_descripcion())
                .add("estatus", categoria_modificar.get_estatus()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Modificada la categoria escogida")
                .add("Categoria", categoriaObj).build();

        return resultado;
    }
}
