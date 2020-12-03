package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "presentacion" )
public class Presentacion extends EntidadBase{
    
    //Columnas
    
    @Column( name = "nombre" )
    private String _nombre;
    
    @Column( name = "caracteristicas" )
    private String _caracteristicas;
    
    //Relaciones
    @ManyToOne
    @JoinColumn(name="fk_producto")
    private Producto _producto;
    
    //Getters, Setters, y otros metodos.

    public String getNombre() {
        return _nombre;
    }

    public String getCaracteristicas() {
        return _caracteristicas;
    }

    public Producto getProducto() {
        return _producto;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setCaracteristicas(String _caracteristicas) {
        this._caracteristicas = _caracteristicas;
    }

    public void setProducto(Producto _producto) {
        this._producto = _producto;
    }
    
    public Presentacion (long id){
        super(id);
    }

    public Presentacion (String estatus) {
        super(estatus);
    }
    
    public Presentacion(){
        super();
    }

    @Override
    public String toString() {
        return "Presentacion{" +
                "_nombre='" + _nombre + '\'' +
                ", _caracteristicas='" + _caracteristicas + '\'' +
                ", _producto=" + _producto +
                '}';
    }
}
