package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaPreguntaResponse {
    private long fkPregunta;
    private String pregunta;

    public RespuestaPreguntaResponse(long fkPregunta, String pregunta) {
        this.fkPregunta = fkPregunta;
        this.pregunta = pregunta;
    }
}