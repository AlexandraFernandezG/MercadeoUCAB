package ucab.dsw.comando.Subcategoria;

import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ListarPreguntasSubCategoriaComando extends ComandoBase {

    public long id;
    public JsonArrayBuilder preguntas = Json.createArrayBuilder();

    public ListarPreguntasSubCategoriaComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);
        List<PreguntaEncuesta> listarPreguntas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);
        List<PreguntaEncuesta> listaPreguntasSubcategoria = new ArrayList<PreguntaEncuesta>();
        JsonObject pregunta;

        for (PreguntaEncuesta preguntaEncuesta: listarPreguntas){

            if(preguntaEncuesta.get_subcategoria().get_id() == id){

                listaPreguntasSubcategoria.add(preguntaEncuesta);
            }
        }

        for(PreguntaEncuesta obj: listaPreguntasSubcategoria){

            pregunta = Json.createObjectBuilder().add("id", obj.get_id())
                    .add("descripcion", obj.get_descripcion())
                    .add("tipoPregunta", obj.get_tipoPregunta())
                    .add("estatus", obj.get_estatus()).build();

            preguntas.add(pregunta);

        }
    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Preguntas", preguntas).build();

        return resultado;
    }
}
