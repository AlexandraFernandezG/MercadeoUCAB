package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "medio_comunicacion" )
public class MedioComunicacion extends EntidadBase{
    
    //Columnas
    
    @Column( name = "tipoDeMedio" )
    private String _tipoDeMedio;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_informacion")
    private Informacion _informacion;
    
    @ManyToOne
    @JoinColumn(name="fk_solicitud_estudio")
    private SolicitudEstudio _solicitudEstudio;
    
    //Getters, Setters, y otros metodos.

    public String getTipoDeMedio() {
        return _tipoDeMedio;
    }

    public Informacion getInformacion() {
        return _informacion;
    }

    public SolicitudEstudio getSolicitudEstudio() {
        return _solicitudEstudio;
    }

    public void setTipoDeMedio(String _tipoDeMedio) {
        this._tipoDeMedio = _tipoDeMedio;
    }

    public void setInformacion(Informacion _informacion) {
        this._informacion = _informacion;
    }

    public void setSolicitudEstudio(SolicitudEstudio _solicitudEstudio) {
        this._solicitudEstudio = _solicitudEstudio;
    }
    
    public MedioComunicacion (long id){
        super(id);
    }

    public MedioComunicacion (String estatus) {
        super(estatus);
    }
    
    public MedioComunicacion (){
        super();
    }

    @Override
    public String toString() {
        return "MedioComunicacion{" +
                "_tipoDeMedio='" + _tipoDeMedio + '\'' +
                ", _informacion=" + _informacion +
                ", _solicitudEstudio=" + _solicitudEstudio +
                '}';
    }
}
