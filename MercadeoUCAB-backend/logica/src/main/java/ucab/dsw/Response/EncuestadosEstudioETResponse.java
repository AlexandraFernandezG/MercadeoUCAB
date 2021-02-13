package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncuestadosEstudioETResponse {

    private long id;
    private String nombre;
    private String codigoRecuperacion;
    private String correo;
    private String estatus;
    private String estado;

    public EncuestadosEstudioETResponse(){
        super();
    }

    public EncuestadosEstudioETResponse(long id, String nombre, String codigoRecuperacion, String correo, String estatus, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.codigoRecuperacion = codigoRecuperacion;
        this.correo = correo;
        this.estatus = estatus;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoRecuperacion() {
        return codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String codigoRecuperacion) {
        this.codigoRecuperacion = codigoRecuperacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
