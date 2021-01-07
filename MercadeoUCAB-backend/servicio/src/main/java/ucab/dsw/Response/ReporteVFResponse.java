package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteVFResponse {

    private String porcentajeVerdadero;
    private float porcentajeVerdaderoFloat;
    private String porcentajeFalso;
    private float porcentajeFalsoFloat;

    public ReporteVFResponse(String porcentajeVerdadero, float porcentajeVerdaderoFloat, String porcentajeFalso, float porcentajeFalsoFloat) {
        this.porcentajeVerdadero = porcentajeVerdadero;
        this.porcentajeVerdaderoFloat = porcentajeVerdaderoFloat;
        this.porcentajeFalso = porcentajeFalso;
        this.porcentajeFalsoFloat = porcentajeFalsoFloat;
    }
}
