package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncuestaResponse {

    private long idPreguntaEncuesta;
    private String descripcion;
    private String tipoPregunta;
    private long idPreguntaEstudio;

    public EncuestaResponse(){

        super();
    }

    public EncuestaResponse(long idPreguntaEncuesta, String descripcion, String tipoPregunta, long idPreguntaEstudio) {
        this.idPreguntaEncuesta = idPreguntaEncuesta;
        this.descripcion = descripcion;
        this.tipoPregunta = tipoPregunta;
        this.idPreguntaEstudio = idPreguntaEstudio;
    }

    public long getIdPreguntaEncuesta() {
        return idPreguntaEncuesta;
    }

    public void setIdPreguntaEncuesta(long idPreguntaEncuesta) {
        this.idPreguntaEncuesta = idPreguntaEncuesta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public long getIdPreguntaEstudio() {
        return idPreguntaEstudio;
    }

    public void setIdPreguntaEstudio(long idPreguntaEstudio) {
        this.idPreguntaEstudio = idPreguntaEstudio;
    }
}