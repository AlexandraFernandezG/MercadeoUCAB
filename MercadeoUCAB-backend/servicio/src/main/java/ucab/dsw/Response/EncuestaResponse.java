package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncuestaResponse {

    private long idPreguntaEncuesta;
    private String descripcion;
    private String tipoPregunta;
    private long idPreguntaEstudio;

    public EncuestaResponse(long idPreguntaEncuesta, String descripcion, String tipoPregunta, long idPreguntaEstudio) {
        this.idPreguntaEncuesta = idPreguntaEncuesta;
        this.descripcion = descripcion;
        this.tipoPregunta = tipoPregunta;
        this.idPreguntaEstudio = idPreguntaEstudio;
    }
}