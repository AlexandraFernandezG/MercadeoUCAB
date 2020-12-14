package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table (name = "producto_presentacion_tipo")
public class ProductoPresentacionTipo extends EntidadBase{

    //Columnas

    //Relaciones

    @ManyToOne
    @JoinColumn(name = "fk_producto")
    private Producto _producto;

    @ManyToOne
    @JoinColumn(name = "fk_presentacion")
    private Presentacion _presentacion;

    @ManyToOne
    @JoinColumn(name = "fk_tipo")
    private Tipo _tipo;

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

    public Tipo get_tipo() {
        return _tipo;
    }

    public void set_tipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public ProductoPresentacionTipo (long id){
        super(id);
    }

    public ProductoPresentacionTipo (String estatus){
        super(estatus);
    }

    public ProductoPresentacionTipo(){
        super();
    }

    @Override
    public String toString() {
        return "ProductoPresentacionTipo{" +
                "_producto=" + _producto +
                ", _presentacion=" + _presentacion +
                ", _tipo=" + _tipo +
                '}';
    }
}
