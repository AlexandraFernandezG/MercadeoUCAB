package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponse {

        private long idProducto;
        private String nombreProducto;
        private String descripcionProducto;
        private String estatusProducto;

    public ProductoResponse(){
        super();
    }

    public ProductoResponse(long idProducto, String nombreProducto, String descripcionProducto, String estatusProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.estatusProducto = estatusProducto;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getEstatusProducto() {
        return estatusProducto;
    }

    public void setEstatusProducto(String estatusProducto) {
        this.estatusProducto = estatusProducto;
    }

}
