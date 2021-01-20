package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerdaderoFalsoResponse {

    private String pregunta;
    private int verdadero;
    private int falso;

    public VerdaderoFalsoResponse(String pregunta, int verdadero, int falso) {
        this.pregunta = pregunta;
        this.verdadero = verdadero;
        this.falso = falso;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getVerdadero() {
        return verdadero;
    }

    public void setVerdadero(int verdadero) {
        this.verdadero = verdadero;
    }

    public int getFalso() {
        return falso;
    }

    public void setFalso(int falso) {
        this.falso = falso;
    }
}
