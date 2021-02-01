package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestasAbiertasResponse {

    private long id;
    private String respuestaAbierta;
    private String pregunta;

    public RespuestasAbiertasResponse(){
        super();
    }

    public RespuestasAbiertasResponse(long id, String respuestaAbierta, String pregunta) {
        this.id = id;
        this.respuestaAbierta = respuestaAbierta;
        this.pregunta = pregunta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRespuestaAbierta() {
        return respuestaAbierta;
    }

    public void setRespuestaAbierta(String respuestaAbierta) {
        this.respuestaAbierta = respuestaAbierta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
