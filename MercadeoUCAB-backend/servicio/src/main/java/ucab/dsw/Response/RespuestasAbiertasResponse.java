package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestasAbiertasResponse {

    private long id;
    private String respuestaAbierta;
    private String pregunta;

    public RespuestasAbiertasResponse(long id, String respuestaAbierta, String pregunta) {
        this.id = id;
        this.respuestaAbierta = respuestaAbierta;
        this.pregunta = pregunta;
    }
}
