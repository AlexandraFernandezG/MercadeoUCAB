package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudioEncuestadoResponse {
    private long idUsuario;
    private String correo;
    private String nombreUsuario;

    public EstudioEncuestadoResponse(long idUsuario, String correo, String nombreUsuario) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
    }
}
