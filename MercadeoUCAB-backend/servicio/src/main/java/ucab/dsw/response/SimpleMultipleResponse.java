package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SimpleMultipleResponse {

    private List<SimpleMultipleEscalaResponse> cantidadesSimples;
    private List<SimpleMultipleEscalaResponse> cantidadesMultiples;

    public SimpleMultipleResponse(List<SimpleMultipleEscalaResponse> cantidadesSimples, List<SimpleMultipleEscalaResponse> cantidadesMultiples) {
        this.cantidadesSimples = cantidadesSimples;
        this.cantidadesMultiples = cantidadesMultiples;
    }
}
