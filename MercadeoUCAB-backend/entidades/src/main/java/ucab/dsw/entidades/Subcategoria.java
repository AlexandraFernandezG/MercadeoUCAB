package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "subcategoria" )
public class Subcategoria extends EntidadBase{
    
    //Columnas
    
    @Column( name = "nombre" )
    private String _nombre;
    
    @Column( name = "descripcion" )
    private String _descripcion;
    
    //Relaciones

    @ManyToOne
    @JoinColumn(name="fk_categoria")
    private Categoria _categoria;

    //Getters, Setters, y otros metodos.

    public String getNombre() {
        return _nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public Categoria getCategoria() {
        return _categoria;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public void setCategoria(Categoria _categoria) {
        this._categoria = _categoria;
    }
    
    public Subcategoria (long id){
        super(id);
    }

    public Subcategoria (String estatus) {
        super(estatus);
    }
    
    public Subcategoria (){
        super();
    }

    @Override
    public String toString() {
        return "Subcategoria{" +
                "_nombre='" + _nombre + '\'' +
                ", _descripcion='" + _descripcion + '\'' +
                ", _categoria=" + _categoria +
                '}';
    }
}
