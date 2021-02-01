package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaPreguntaResponse {
    private long fkPregunta;
    private String pregunta;

    public RespuestaPreguntaResponse(){
        super();
    }

    public RespuestaPreguntaResponse(long fkPregunta, String pregunta) {
        this.fkPregunta = fkPregunta;
        this.pregunta = pregunta;
    }

    public long getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(long fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}