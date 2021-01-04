package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestasAbiertasResponse {

    private long id;
    private String respuestaAbierta;

    public RespuestasAbiertasResponse(long id, String respuestaAbierta) {
        this.id = id;
        this.respuestaAbierta = respuestaAbierta;
    }
}
