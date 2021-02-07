package ucab.dsw.comando.Sugerencias;

import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.response.PreguntasResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ListarPreguntasEstudioRecomendadasComando extends ComandoBase {

    public long id;
    public JsonArrayBuilder preguntas = Json.createArrayBuilder();

    public ListarPreguntasEstudioRecomendadasComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);
        JsonObject pregunta;
        List<Object[]> listaPreguntas = daoPreguntaEncuesta.listarPreguntasEstudio(id);

        List<PreguntasResponse> listaPreguntasRecomendadas = new ArrayList<>(listaPreguntas.size());

        for (Object[] pre: listaPreguntas){

            listaPreguntasRecomendadas.add(new PreguntasResponse((long)pre[0], (String)pre[1], (String)pre[2], (String)pre[3], (String)pre[4]));

        }

        for (PreguntasResponse obj: listaPreguntasRecomendadas){

            pregunta = Json.createObjectBuilder().add("id", obj.getId())
                    .add("descripcion", obj.getDescripcion())
                    .add("tipoPregunta", obj.getTipoPregunta())
                    .add("estatus", obj.getEstatus())
                    .add("nombreSubcategoria", obj.getNombreSubcategoria()).build();

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
