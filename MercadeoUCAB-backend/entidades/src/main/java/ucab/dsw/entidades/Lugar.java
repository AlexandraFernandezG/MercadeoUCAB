
package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "lugar" )
public class Lugar extends EntidadBase{
    
    //Columnas
    
    @Column( name = "nombre" )
    private String _nombre;
    
    @Column( name = "tipo" )
    private String _tipo;
    
    @Column( name = "categoriaSocioEconomica" )
    private String _categoriaSocioEconomica;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_lugar")
    private Lugar _lugar;
    
    //Getters, Setters, y otros metodos.

    public String getNombre() {
        return _nombre;
    }

    public String getTipo() {
        return _tipo;
    }

    public String getCategoriaSocioEconomica() {
        return _categoriaSocioEconomica;
    }

    public Lugar getLugar() {
        return _lugar;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setTipo(String _tipo) {
        this._tipo = _tipo;
    }

    public void setCategoriaSocioEconomica(String _categoriaSocioEconomica) {
        this._categoriaSocioEconomica = _categoriaSocioEconomica;
    }

    public void setLugar(Lugar _lugar) {
        this._lugar = _lugar;
    }
    
    public Lugar (long id){
        super(id);
    }

    public Lugar (String estatus) {
        super(estatus);
    }
    
    public Lugar (){
        super();
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "_nombre='" + _nombre + '\'' +
                ", _tipo='" + _tipo + '\'' +
                ", _categoriaSocioEconomica='" + _categoriaSocioEconomica + '\'' +
                ", _lugar=" + _lugar +
                '}';
    }
}
