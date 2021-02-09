package ucab.dsw.comando.Estudio;

import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.Response.PreguntasResponse;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class InsertarPreguntasEstudioComando extends ComandoBase {

    public JsonObject dataObject;
    public long id;
    public List<PreguntasResponse> listaPreguntas;

    public InsertarPreguntasEstudioComando(long id, List<PreguntasResponse> listaPreguntas) {
        this.id = id;
        this.listaPreguntas = listaPreguntas;
    }

    @Override
    public void execute() throws Exception {

        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);

        for(PreguntasResponse preguntaEncuesta: listaPreguntas){

            servicio.addPreguntaEstudio(id, preguntaEncuesta.getId());
        }

        dataObject = Json.createObjectBuilder()
                .add("estado", "Se han insertado las preguntas correctamente")
                .add("codigo", 200).build();

    }

    @Override
    public JsonObject getResult() {

        return dataObject;
    }
}
