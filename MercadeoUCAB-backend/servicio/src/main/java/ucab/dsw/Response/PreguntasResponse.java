package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntasResponse {

    private long idPregunta;
    private String descripcionPregunta;
    private String tipoPregunta;
    private String estatusPregunta;

    public PreguntasResponse(long idPregunta, String descripcionPregunta, String tipoPregunta, String estatusPregunta){
            this.idPregunta = idPregunta;
            this.descripcionPregunta = descripcionPregunta;
            this.tipoPregunta = tipoPregunta;
            this.estatusPregunta = estatusPregunta;
    }

    public long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public String getEstatusPregunta() {
        return estatusPregunta;
    }

    public void setEstatusPregunta(String estatusPregunta) {
        this.estatusPregunta = estatusPregunta;
    }
}
