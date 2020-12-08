package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "respuesta_pregunta" )
public class RespuestaPregunta extends EntidadBase{
    
    //Columnas
    
    @Column( name = "nombre" )
    private String _nombre;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_pregunta_encuesta")
    private PreguntaEncuesta _preguntaEncuesta;
    
    //Getters, Setters, y otros metodos.

    public String getNombre() {
        return _nombre;
    }

    public PreguntaEncuesta getPreguntaEncuesta() {
        return _preguntaEncuesta;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setPreguntaEncuesta(PreguntaEncuesta _preguntaEncuesta) {
        this._preguntaEncuesta = _preguntaEncuesta;
    }
    
    public RespuestaPregunta(long id){
        super(id);
    }

    public RespuestaPregunta (String estatus) {
        super(estatus);
    }
    
    public RespuestaPregunta (){
        super();
    }

    @Override
    public String toString() {
        return "RespuestaPregunta{" +
                "_nombre='" + _nombre + '\'' +
                ", _preguntaEncuesta=" + _preguntaEncuesta +
                '}';
    }
}
