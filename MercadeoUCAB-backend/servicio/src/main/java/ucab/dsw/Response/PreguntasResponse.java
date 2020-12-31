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
}
