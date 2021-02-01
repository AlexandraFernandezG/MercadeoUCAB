package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntasResponse {

    private long id;
    private String descripcion;
    private String tipoPregunta;
    private String estatus;
    private String nombreSubcategoria;

    public PreguntasResponse(){
        super();
    }

    public PreguntasResponse(long id, String descripcion, String tipoPregunta, String estatus, String nombreSubcategoria){
            this.id = id;
            this.descripcion = descripcion;
            this.tipoPregunta = tipoPregunta;
            this.estatus = estatus;
            this.nombreSubcategoria = nombreSubcategoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombreSubcategoria() {
        return nombreSubcategoria;
    }

    public void setNombreSubcategoria(String nombreSubcategoria) {
        this.nombreSubcategoria = nombreSubcategoria;
    }
}
