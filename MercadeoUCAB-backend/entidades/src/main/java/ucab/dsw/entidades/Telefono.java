package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "telefono" )
public class Telefono extends EntidadBase{
    
    //Columnas
    
    @Column( name = "numero" )
    private int _numero;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_informacion")
    private Informacion _informacion;
    
    //Getters, Setters, y otros metodos.

    public int getNumero() {
        return _numero;
    }

    public Informacion getInformacion() {
        return _informacion;
    }

    public void setNumero(int _numero) {
        this._numero = _numero;
    }

    public void setInformacion(Informacion _informacion) {
        this._informacion = _informacion;
    }
    
    public Telefono (long id){
        super(id);
    }

    public Telefono (String estatus) {
        super(estatus);
    }
    
    public Telefono (){
        super();
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "_numero=" + _numero +
                ", _informacion=" + _informacion +
                '}';
    }
}
