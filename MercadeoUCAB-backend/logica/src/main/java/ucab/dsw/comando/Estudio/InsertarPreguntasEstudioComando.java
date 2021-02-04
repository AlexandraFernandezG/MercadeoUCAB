package ucab.dsw.comando.Estudio;

import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.response.PreguntasResponse;

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

        ucab.dsw.servicio.PreguntasEstudioServicio servicio = new ucab.dsw.servicio.PreguntasEstudioServicio();

        //Recorremos e insertamos las preguntas con el estudio
        PreguntaEstudioDto preguntaEstudioDto = new PreguntaEstudioDto();

        for(PreguntasResponse preguntaEncuesta: listaPreguntas){

            preguntaEstudioDto.setEstatus("Activo");
            EstudioDto idEstudio2 = new EstudioDto(id);
            preguntaEstudioDto.setEstudioDto(idEstudio2);
            PreguntaEncuestaDto idPregunta = new PreguntaEncuestaDto(preguntaEncuesta.getId());
            preguntaEstudioDto.setPreguntaEncuestaDto(idPregunta);
            servicio.addPreguntaEstudio(preguntaEstudioDto);
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
