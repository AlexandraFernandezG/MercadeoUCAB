package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "producto" )
public class Producto extends EntidadBase{
    
    //Columnas
    
    @Column( name = "nombre" )
    private String _nombre;
    
    @Column( name = "descripcion" )
    private String _descripcion;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_usuario")
    private Usuario _usuario;
    
    @ManyToOne
    @JoinColumn(name="fk_subcategoria")
    private Subcategoria _subcategoria;
    
    @ManyToOne
    @JoinColumn(name="fk_marca")
    private Marca _marca;
    
    //Getters, Setters, y otros metodos.

    public String getNombre() {
        return _nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public Usuario getUsuario() {
        return _usuario;
    }

    public Subcategoria getSubcategoria() {
        return _subcategoria;
    }

    public Marca getMarca() {
        return _marca;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public void setUsuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public void setSubcategoria(Subcategoria _subcategoria) {
        this._subcategoria = _subcategoria;
    }

    public void setMarca(Marca _marca) {
        this._marca = _marca;
    }
    
    public Producto(long id){
        super(id);
    }

    public Producto (String estatus) {
        super(estatus);
    }
    
    public Producto(){
        super();
    }

    @Override
    public String toString() {
        return "Producto{" +
                "_nombre='" + _nombre + '\'' +
                ", _descripcion='" + _descripcion + '\'' +
                ", _usuario=" + _usuario +
                ", _subcategoria=" + _subcategoria +
                ", _marca=" + _marca +
                '}';
    }
}
