/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucab.dsw.entidades;

/**
 *
 * @author Emanuel
 */

import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "estudio" )
public class Estudio extends EntidadBase{

    //Columnas
    
    @Column( name = "nombre" )
    private String _nombre;
    
    @Column( name = "tipoInstrumento" )
    private String _tipoInstrumento;
    
    @Column( name = "fechaInicio" )
    private Date _fechaInicio;
    
    @Column( name = "fechaFin" )
    private Date _fechaFin;

    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_usuario")
    private Usuario _usuario;
    
    @ManyToOne
    @JoinColumn(name="fk_solicitud_estudio")
    private SolicitudEstudio _solicitudEstudio;
    
    //Getters, Setters, y otros metodos.

    public String getNombre() {
        return _nombre;
    }

    public String getTipoInstrumento() {
        return _tipoInstrumento;
    }

    public Date getFechaInicio() {
        return _fechaInicio;
    }

    public Date getFechaFin() {
        return _fechaFin;
    }

    public Usuario getUsuario() {
        return _usuario;
    }

    public SolicitudEstudio getSolicitudEstudio() {
        return _solicitudEstudio;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setTipoInstrumento(String _tipoInstrumento) {
        this._tipoInstrumento = _tipoInstrumento;
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

    public void setSolicitudEstudio(SolicitudEstudio _solicitudEstudio) {
        this._solicitudEstudio = _solicitudEstudio;
    }
    
    public Estudio(long id){
        super(id);
    }

    public Estudio (String estatus) {
        super(estatus);
    }

    @Override
    public String toString() {
        return "Estudio{" +
                "_nombre='" + _nombre + '\'' +
                ", _tipoInstrumento='" + _tipoInstrumento + '\'' +
                ", _fechaInicio=" + _fechaInicio +
                ", _fechaFin=" + _fechaFin +
                ", _usuario=" + _usuario +
                ", _solicitudEstudio=" + _solicitudEstudio +
                '}';
    }

    public Estudio(){
        super();
    } 
}
