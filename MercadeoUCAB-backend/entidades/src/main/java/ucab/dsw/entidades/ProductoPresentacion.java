package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "producto_presentacion" )
public class ProductoPresentacion extends EntidadBase{

    //Relaciones

    @ManyToOne
    @JoinColumn(name = "fk_producto")
    private Producto _producto;

    @ManyToOne
    @JoinColumn(name = "fk_presentacion")
    private Presentacion _presentacion;

    //Getters, setters y otros metodos

    public Producto get_producto() {
        return _producto;
    }

    public void set_producto(Producto _producto) {
        this._producto = _producto;
    }

    public Presentacion get_presentacion() {
        return _presentacion;
    }

    public void set_presentacion(Presentacion _presentacion) {
        this._presentacion = _presentacion;
    }

    public ProductoPresentacion (long id ){
        super( id );
    }

    public ProductoPresentacion( String estatus){
        super( estatus );
    }

    public ProductoPresentacion(){
        super();
    }

    @Override
    public String toString() {
        return "ProductoPresentacion{" +
                "_producto=" + _producto +
                ", _presentacion=" + _presentacion +
                '}';
    }
}
