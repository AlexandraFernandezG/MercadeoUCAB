package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "pregunta_encuesta" )
public class PreguntaEncuesta extends EntidadBase{
    
    //Columnas
    
    @Column( name = "descripcion" )
    private String _descripcion;
    
    @Column( name = "tipoPregunta" )
    private String _tipoPregunta;
     
   //Relaciones
    
   @ManyToOne
   @JoinColumn(name="fk_usuario")
   private Usuario _usuario;
   
   @ManyToOne
   @JoinColumn(name="fk_subcategoria")
   private Subcategoria _subcategoria;
   
   //Getters, Setters, y otros metodos.

    public String getDescripcion() {
        return _descripcion;
    }

    public String getTipoPregunta() {
        return _tipoPregunta;
    }

    public Usuario getUsuario() {
        return _usuario;
    }

    public Subcategoria getSubcategoria() {
        return _subcategoria;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public void setTipoPregunta(String _tipoPregunta) {
        this._tipoPregunta = _tipoPregunta;
    }

    public void setUsuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public void setSubcategoria(Subcategoria _subcategoria) {
        this._subcategoria = _subcategoria;
    }
   
    public PreguntaEncuesta(long id){
       super(id);
   }

    public PreguntaEncuesta (String estatus) {
        super(estatus);
    }
   
    public PreguntaEncuesta (){
        super();
    }

    @Override
    public String toString() {
        return "PreguntaEncuesta{" +
                "_descripcion='" + _descripcion + '\'' +
                ", _tipoPregunta='" + _tipoPregunta + '\'' +
                ", _usuario=" + _usuario +
                ", _subcategoria=" + _subcategoria +
                '}';
    }
}
