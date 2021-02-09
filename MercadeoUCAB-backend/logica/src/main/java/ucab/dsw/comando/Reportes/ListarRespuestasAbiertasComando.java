package ucab.dsw.comando.Reportes;

import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.Response.RespuestasAbiertasResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ListarRespuestasAbiertasComando extends ComandoBase {

    public long id;
    public JsonArrayBuilder respuestas = Json.createArrayBuilder();

    public ListarRespuestasAbiertasComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);
        JsonObject respuesta;
        List<Object[]> respuestasAbiertas = daoPreguntaEncuesta.obtenerPreguntasAbiertas(id);

        List<RespuestasAbiertasResponse> ResponseListUpdate = new ArrayList<>(respuestasAbiertas.size());

        for (Object[] r : respuestasAbiertas) {
            ResponseListUpdate.add(new RespuestasAbiertasResponse((long)r[0], (String)r[1], (String)r[2]));
        }

        for (RespuestasAbiertasResponse obj: ResponseListUpdate){

            respuesta = Json.createObjectBuilder().add("id", obj.getId())
                    .add("pregunta", obj.getPregunta())
                    .add("respuestaAbierta", obj.getRespuestaAbierta()).build();


            respuestas.add(respuesta);
        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Respuestas", respuestas).build();

        return resultado;
    }
}
