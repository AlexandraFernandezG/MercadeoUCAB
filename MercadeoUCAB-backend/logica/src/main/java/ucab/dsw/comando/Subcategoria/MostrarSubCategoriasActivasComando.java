package ucab.dsw.comando.Subcategoria;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class MostrarSubCategoriasActivasComando extends ComandoBase {

    public JsonArrayBuilder subcategorias = Json.createArrayBuilder();

    @Override
    public void execute() throws Exception {

        DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
        List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);
        List<Subcategoria> listaSubCategoriasActivas = new ArrayList<Subcategoria>();

        for (Subcategoria subcategoria : listaSubcategorias) {

            if (subcategoria.get_estatus().equals("Activo")) {
                listaSubCategoriasActivas.add(subcategoria);
            }
        }

        for(Subcategoria obj: listaSubCategoriasActivas){

            JsonObject categoria = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre", obj.get_nombre())
                    .add("descripcion", obj.get_descripcion())
                    .add("estatus", obj.get_estatus()).build();

            subcategorias.add(categoria);
        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder().add("mensaje","Todas las subcategorias activas")
                .add("estado",200)
                .add("SubCategorias", subcategorias).build();

        return resultado;
    }
}
