package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    private long id;
    private String nombre;
    private String codigoRecuperacion;
    private String correo;
    private String estatus;

    public UsuarioResponse(long id, String nombre, String codigoRecuperacion, String correo, String estatus) {
        this.id = id;
        this.nombre = nombre;
        this.codigoRecuperacion = codigoRecuperacion;
        this.correo = correo;
        this.estatus = estatus;
    }
}
