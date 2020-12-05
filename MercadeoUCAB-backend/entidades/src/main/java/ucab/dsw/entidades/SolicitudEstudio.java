package ucab.dsw.entidades;

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "solicitud_estudio" )
public class SolicitudEstudio extends EntidadBase{
    
    //Columnas
    
    @Column( name = "descripcionSolicitud" )
    private String _descripcionSolicitud;
    
    @Column( name = "generoSolicitud" )
    private String _generoSolicitud;
    
    @Column( name = "fechaSolicitud" )
    private Date _fechaSolicitud;
    
    @Column( name = "regionSolicitud" )
    private String _regionSolicitud;
    
    @Column( name = "edadMinimaPoblacion" )
    private String _edadMinimaPoblacion;
    
    @Column( name = "edadMaximaPoblacion" )
    private String _edadMaximaPoblacion;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn( name = "fk_usuario" )
    private Usuario _usuario;
    
    @ManyToOne
    @JoinColumn( name = "fk_nivelEconomico" )
    private NivelEconomico _nivelEconomico;
    
    @ManyToOne
    @JoinColumn( name = "fk_producto" )
    private Producto _producto;
    
    //Getters, Setters, y otros metodos.

    public String getDescripcionSolicitud() {
        return _descripcionSolicitud;
    }

    public String getGeneroSolicitud() {
        return _generoSolicitud;
    }

    public Date getFechaSolicitud() {
        return _fechaSolicitud;
    }

    public String getRegionSolicitud() {
        return _regionSolicitud;
    }

    public String getEdadMinimaPoblacion() {
        return _edadMinimaPoblacion;
    }

    public String getEdadMaximaPoblacion() {
        return _edadMaximaPoblacion;
    }

    public Usuario getUsuario() {
        return _usuario;
    }

    public NivelEconomico getNivelEconomico() {
        return _nivelEconomico;
    }

    public Producto getProducto() {
        return _producto;
    }

    public void setDescripcionSolicitud(String _descripcionSolicitud) {
        this._descripcionSolicitud = _descripcionSolicitud;
    }

    public void setGeneroSolicitud(String _generoSolicitud) {
        this._generoSolicitud = _generoSolicitud;
    }

    public void setFechaSolicitud(Date _fechaSolicitud) {
        this._fechaSolicitud = _fechaSolicitud;
    }

    public void setRegionSolicitud(String _regionSolicitud) {
        this._regionSolicitud = _regionSolicitud;
    }

    public void setEdadMinimaPoblacion(String _edadMinimaPoblacion) {
        this._edadMinimaPoblacion = _edadMinimaPoblacion;
    }

    public void setEdadMaximaPoblacion(String _edadMaximaPoblacion) {
        this._edadMaximaPoblacion = _edadMaximaPoblacion;
    }

    public void setUsuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public void setNivelEconomico(NivelEconomico _nivelEconomico) {
        this._nivelEconomico = _nivelEconomico;
    }

    public void setProducto(Producto _producto) {
        this._producto = _producto;
    }
    
    public SolicitudEstudio(long id){
        super (id);
    }

    public SolicitudEstudio (String estatus) {
        super(estatus);
    }
    
    public SolicitudEstudio(){
        super();
    }

    @Override
    public String toString() {
        return "SolicitudEstudio{" +
                "_descripcionSolicitud='" + _descripcionSolicitud + '\'' +
                ", _generoSolicitud='" + _generoSolicitud + '\'' +
                ", _fechaSolicitud=" + _fechaSolicitud +
                ", _regionSolicitud='" + _regionSolicitud + '\'' +
                ", _edadMinimaPoblacion='" + _edadMinimaPoblacion + '\'' +
                ", _edadMaximaPoblacion='" + _edadMaximaPoblacion + '\'' +
                ", _usuario=" + _usuario +
                ", _nivelEconomico=" + _nivelEconomico +
                ", _producto=" + _producto +
                '}';
    }
}
