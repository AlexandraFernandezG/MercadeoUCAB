package ucab.dsw.comando.Categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperCategoria;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class AddCategoriaComando extends ComandoBase {

    public Categoria categoria;
    public JsonObject categoriaObj;

    public AddCategoriaComando(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public void execute() {

        try {

            DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
            Categoria categoriaInsertar = Fabrica.crear(Categoria.class);

            categoriaInsertar.set_nombre(categoria.get_nombre());
            categoriaInsertar.set_descripcion(categoria.get_descripcion());
            categoriaInsertar.set_estatus(categoria.get_estatus());

            Categoria resul = daoCategoria.insert(categoriaInsertar);
            CategoriaDto resultado = MapperCategoria.mapEntityToDto(resul);

            categoriaObj = Json.createObjectBuilder().add("id", resultado.getId()).build();

        } catch (PruebaExcepcion ex) {

            ex.getMessage();
        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Categoria insertada")
                .add("id", categoriaObj).build();

        return resultado;
    }
}
