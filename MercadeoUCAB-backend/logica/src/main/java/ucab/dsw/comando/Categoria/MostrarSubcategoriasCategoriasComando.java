package ucab.dsw.comando.Categoria;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class MostrarSubcategoriasCategoriasComando extends ComandoBase {

    public JsonArrayBuilder subcategorias = Json.createArrayBuilder();
    public long id;

    public MostrarSubcategoriasCategoriasComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
        List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);
        List<Subcategoria> listaSubcategoriasCategoria = new ArrayList<Subcategoria>();

        for (Subcategoria subcategoria : listaSubcategorias) {

            if (subcategoria.get_categoria().get_id() == id) {
                listaSubcategoriasCategoria.add(subcategoria);
            }
        }

        for(Subcategoria obj: listaSubcategoriasCategoria){

            JsonObject subcategoria = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre", obj.get_nombre())
                    .add("descripcion", obj.get_descripcion())
                    .add("estatus", obj.get_estatus()).build();

            subcategorias.add(subcategoria);
        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Subcategorias", subcategorias).build();

        return resultado;
    }
}
