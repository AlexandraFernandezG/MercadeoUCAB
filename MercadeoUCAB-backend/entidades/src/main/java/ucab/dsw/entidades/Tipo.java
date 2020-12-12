package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "tipo" )
public class Tipo extends EntidadBase{
    
    //Columnas
    
    @Column( name = "nombre" )
    private String _nombre;
    
    @Column( name = "descripcion" )
    private String _descripcion;
    
    //Getters, Setters, y otros metodos.

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public Tipo (String estatus) {
        super(estatus);
    }

    public Tipo (long id){
        super(id);
    }
    
    public Tipo (){
        super();
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "_nombre='" + _nombre + '\'' +
                ", _descripcion='" + _descripcion + '\'' +
                '}';
    }
}
