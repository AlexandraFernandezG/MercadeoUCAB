package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "solicitud_estudio_lugar")
public class SolicitudEstudioLugar extends EntidadBase {

    //Relaciones

    @ManyToOne
    @JoinColumn ( name = "fk_solicitud_estudio")
    private SolicitudEstudio _SolicitudEstudio;

    @ManyToOne
    @JoinColumn ( name = "fk_lugar")
    private Lugar _lugar;

    //Getters, setters y otros metodos

    public SolicitudEstudio get_SolicitudEstudio() {
        return _SolicitudEstudio;
    }

    public void set_SolicitudEstudio(SolicitudEstudio _SolicitudEstudio) {
        this._SolicitudEstudio = _SolicitudEstudio;
    }

    public Lugar get_lugar() {
        return _lugar;
    }

    public void set_lugar(Lugar _lugar) {
        this._lugar = _lugar;
    }

    public SolicitudEstudioLugar(long id) {
        super(id);
    }

    public SolicitudEstudioLugar(String estatus){
        super(estatus);
    }

    public SolicitudEstudioLugar(){
        super();
    }

    @Override
    public String toString() {
        return "SolicitudEstudioLugar{" +
                "_SolicitudEstudio=" + _SolicitudEstudio +
                ", _lugar=" + _lugar +
                '}';
    }
}
