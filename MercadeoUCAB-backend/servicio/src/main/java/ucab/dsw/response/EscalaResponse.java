package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscalaResponse {

    private String pregunta;
    private int cantidadUno;
    private int cantidadDos;
    private int cantidadTres;
    private int cantidadCuatro;
    private int cantidadCinco;

    public EscalaResponse(String pregunta, int cantidadUno, int cantidadDos, int cantidadTres, int cantidadCuatro, int cantidadCinco) {
        this.pregunta = pregunta;
        this.cantidadUno = cantidadUno;
        this.cantidadDos = cantidadDos;
        this.cantidadTres = cantidadTres;
        this.cantidadCuatro = cantidadCuatro;
        this.cantidadCinco = cantidadCinco;
    }
}
