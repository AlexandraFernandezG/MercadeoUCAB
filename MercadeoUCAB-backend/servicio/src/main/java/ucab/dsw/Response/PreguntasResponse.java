package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntasResponse {

    private long idPregunta;
    private String descripcionPregunta;
    private String tipoPregunta;
    private String estatusPregunta;
    private String nombreSubcategoria;

    public PreguntasResponse(long idPregunta, String descripcionPregunta, String tipoPregunta, String estatusPregunta, String nombreSubcategoria){
            this.idPregunta = idPregunta;
            this.descripcionPregunta = descripcionPregunta;
            this.tipoPregunta = tipoPregunta;
            this.estatusPregunta = estatusPregunta;
            this.nombreSubcategoria = nombreSubcategoria;
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

    public String getNombreSubcategoria() {
        return nombreSubcategoria;
    }

    public void setNombreSubcategoria(String nombreSubcategoria) {
        this.nombreSubcategoria = nombreSubcategoria;
    }
}
