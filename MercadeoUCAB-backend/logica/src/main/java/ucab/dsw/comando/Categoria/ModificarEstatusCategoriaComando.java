package ucab.dsw.comando.Categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class ModificarEstatusCategoriaComando extends ComandoBase {

    public long id;
    public Categoria categoria;
    public JsonObject categoriaObj;

    public ModificarEstatusCategoriaComando(long id, Categoria categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    @Override
    public void execute() throws PruebaExcepcion {

        DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
        Categoria categoria_modificar = daoCategoria.find(id, Categoria.class);

            categoria_modificar.set_estatus(categoria.get_estatus());
            daoCategoria.update(categoria_modificar);
            DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);

                if (categoria_modificar.get_estatus().equals("Inactivo")) {

                    List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

                    for (Subcategoria subcategoria : listaSubcategorias) {

                        if (subcategoria.get_categoria().get_id() == id) {
                            subcategoria.set_estatus("Inactivo");
                            daoSubcategoria.update(subcategoria);
                        }
                    }

                } else if (categoria_modificar.get_estatus().equals("Activo")) {

                    List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

                    for (Subcategoria subcategoria : listaSubcategorias) {

                        if (subcategoria.get_categoria().get_id() == id) {
                            subcategoria.set_estatus("Activo");
                            daoSubcategoria.update(subcategoria);
                        }
                    }
                }

        categoriaObj = Json.createObjectBuilder().add("id", categoria_modificar.get_id())
                .add("nombre", categoria_modificar.get_nombre())
                .add("descripcion", categoria_modificar.get_descripcion())
                .add("estatus", categoria_modificar.get_estatus()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Modificadas las subcategorias de la categoria escogida")
                .add("categoria", categoriaObj).build();

        return resultado;
    }
}
