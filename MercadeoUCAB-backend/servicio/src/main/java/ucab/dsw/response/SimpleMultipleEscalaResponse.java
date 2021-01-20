package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleMultipleEscalaResponse {

    private String pregunta;
    private String opcion;
    private int cantidad;

    public SimpleMultipleEscalaResponse(String pregunta, String opcion, int cantidad) {
        this.pregunta = pregunta;
        this.opcion = opcion;
        this.cantidad = cantidad;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
