package ucab.dsw.entidades;


import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "historico_estado" )
public class HistoricoEstado extends EntidadBase{
    
    //Columnas
    
    @Column( name = "fechaInicio" )
    private Date _fechaInicio;
    
    @Column( name = "fechaFin" )
    private Date _fechaFin;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_usuario")
    private Usuario _usuario;
    
    //Getters, Setters, y otros metodos.

    public Date getFechaInicio() {
        return _fechaInicio;
    }

    public Date getFechaFin() {
        return _fechaFin;
    }

    public Usuario getUsuario() {
        return _usuario;
    }

    public void setFechaInicio(Date _fechaInicio) {
        this._fechaInicio = _fechaInicio;
    }

    public void setFechaFin(Date _fechaFin) {
        this._fechaFin = _fechaFin;
    }

    public void setUsuario(Usuario _usuario) {
        this._usuario = _usuario;
    }
    
    public HistoricoEstado(long id){
        super(id);
    }

    public HistoricoEstado (String estatus) {
        super(estatus);
    }

    @Override
    public String toString() {
        return "HistoricoEstado{" +
                "_fechaInicio=" + _fechaInicio +
                ", _fechaFin=" + _fechaFin +
                ", _usuario=" + _usuario +
                '}';
    }

    public HistoricoEstado(){
        super();
    }
   
}
