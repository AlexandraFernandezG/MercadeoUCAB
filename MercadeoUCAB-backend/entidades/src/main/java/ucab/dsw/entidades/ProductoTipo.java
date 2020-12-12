package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "producto_tipo" )
public class ProductoTipo extends EntidadBase{

    //Relaciones

    @ManyToOne
    @JoinColumn(name="fk_producto")
    private Producto _producto;

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

    public Tipo get_tipo() {
        return _tipo;
    }

    public void set_tipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public ProductoTipo (long id){
        super(id);
    }

    public ProductoTipo (String estatus) {
        super(estatus);
    }

    public ProductoTipo (){
        super();
    }

    @Override
    public String toString() {
        return "ProductoTipo{" +
                "_producto=" + _producto +
                ", _tipo=" + _tipo +
                '}';
    }
}
