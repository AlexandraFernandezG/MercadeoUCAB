package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteMFResponse {

    private String porcentajeMasculino;
    private float porcentajeMasculinoFloat;
    private String porcentajeFemenino;
    private float porcentajeFemeninoFloat;

    public ReporteMFResponse(String porcentajeMasculino, float porcentajeMasculinoFloat, String porcentajeFemenino, float porcentajeFemeninoFloat) {
        this.porcentajeMasculino = porcentajeMasculino;
        this.porcentajeMasculinoFloat = porcentajeMasculinoFloat;
        this.porcentajeFemenino = porcentajeFemenino;
        this.porcentajeFemeninoFloat = porcentajeFemeninoFloat;
    }
}
