package ucab.dsw.Response;

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

    public EstudioEncuestadoResponse(){
        super();
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
