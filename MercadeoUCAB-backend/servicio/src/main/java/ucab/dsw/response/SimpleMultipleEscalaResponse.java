package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleMultipleEscalaResponse {

    private String pregunta;
    private String tipo;
    private String opcion;
    private int cantidad;

    public SimpleMultipleEscalaResponse(String pregunta, String tipo, String opcion, int cantidad) {
        this.pregunta = pregunta;
        this.tipo = tipo;
        this.opcion = opcion;
        this.cantidad = cantidad;
    }
}
