package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "respuesta" )
public class Respuesta extends EntidadBase {
    
    //Columnas
    
    @Column( name = "respuestaAbierta" )
    private String _respuestaAbierta;
    
    @Column( name = "escala" )
    private String _escala;
    
    @Column( name = "verdaderoFalso" )
    private String _verdaderoFalso;
    
    @Column( name = "respuestaSimple" )
    private String _respuestaSimple;
    
    @Column( name = "respuestaMultiple" )
    private String _respuestaMultiple;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_usuario")
    private Usuario _usuario;
    
    @ManyToOne
    @JoinColumn(name="fk_preguntas_estudio")
    private PreguntaEstudio _preguntaEstudio;
    
    //Getters, Setters, y otros metodos.

    public String getRespuestaAbierta() {
        return _respuestaAbierta;
    }

    public String getEscala() {
        return _escala;
    }

    public String getVerdaderoFalso() {
        return _verdaderoFalso;
    }

    public String getRespuestaSimple() {
        return _respuestaSimple;
    }

    public String getRespuestaMultiple() {
        return _respuestaMultiple;
    }

    public Usuario getUsuario() {
        return _usuario;
    }

    public PreguntaEstudio getPreguntasEstudio() {
        return _preguntaEstudio;
    }

    public void setRespuestaAbierta(String _respuestaAbierta) {
        this._respuestaAbierta = _respuestaAbierta;
    }

    public void setEscala(String _escala) {
        this._escala = _escala;
    }

    public void setVerdaderoFalso(String _verdaderoFalso) {
        this._verdaderoFalso = _verdaderoFalso;
    }

    public void setRespuestaSimple(String _respuestaSimple) {
        this._respuestaSimple = _respuestaSimple;
    }

    public void setRespuestaMultiple(String _respuestaMultiple) {
        this._respuestaMultiple = _respuestaMultiple;
    }

    public void setUsuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public void setPreguntasEstudio(PreguntaEstudio _preguntasEstudio) {
        this._preguntaEstudio = _preguntasEstudio;
    }
    
    public Respuesta(long id){
        super(id);
    }

    public Respuesta (String estatus) {
        super(estatus);
    }
    
    public Respuesta (){
        super();
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "_respuestaAbierta='" + _respuestaAbierta + '\'' +
                ", _escala='" + _escala + '\'' +
                ", _verdaderoFalso='" + _verdaderoFalso + '\'' +
                ", _respuestaSimple='" + _respuestaSimple + '\'' +
                ", _respuestaMultiple='" + _respuestaMultiple + '\'' +
                ", _usuario=" + _usuario +
                ", _preguntaEstudio=" + _preguntaEstudio +
                '}';
    }
}
