
package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "hijo" )
public class Hijo extends EntidadBase{
    
    //Columnas
    
    @Column( name = "fechaNacimiento" )
    private Date _fechaNacimiento;
    
    @Column( name = "genero" )
    private String _genero;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_informacion")
    private Informacion _informacion;
    
    ////Getters, Setters, y otros metodos.

    public Date getFechaNacimiento() {
        return _fechaNacimiento;
    }

    public String getGenero() {
        return _genero;
    }

    public Informacion getInformacion() {
        return _informacion;
    }

    public void setFechaNacimiento(Date _fechaNacimiento) {
        this._fechaNacimiento = _fechaNacimiento;
    }

    public void setGenero(String _genero) {
        this._genero = _genero;
    }

    public void setInformacion(Informacion _informacion) {
        this._informacion = _informacion;
    }
    
    public Hijo (long id){
        super(id);
    }

    public Hijo (String estatus) {
        super(estatus);
    }

    @Override
    public String toString() {
        return "Hijo{" +
                "_fechaNacimiento=" + _fechaNacimiento +
                ", _genero='" + _genero + '\'' +
                ", _informacion=" + _informacion +
                '}';
    }

    public Hijo(){
        super();
    }
}
