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
@Table( name = "informacion" )
public class Informacion extends EntidadBase {
    
    //Columnas
    
    @Column( name = "cedula" )
    private int _cedula;
    
    @Column( name = "primerNombre" )
    private String _primerNombre;
    
    @Column( name = "segundoNombre" )
    private String _segundoNombre;
    
    @Column( name = "primerApellido" )
    private String _primerApellido;
    
    @Column( name = "segundoApellido" )
    private String _segundoApellido;
    
    @Column( name = "genero" )
    private String _genero;
    
    @Column( name = "fechaNacimiento" )
    private Date _fechaNacimiento;
    
    @Column( name = "estadoCivil" )
    private String _estadoCivil;
    
    @Column( name = "disponibilidadEnLinea" )
    private String _disponibilidadEnLinea;
    
    @Column( name = "cantidadPersonas" )
    private int _cantidadPersonas;
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_lugar")
    private Lugar _lugar;
    
    @ManyToOne
    @JoinColumn(name="fk_nivel_academico")
    private NivelAcademico _nivelAcademico;
    
    @ManyToOne
    @JoinColumn(name="fk_ocupacion")
    private Ocupacion _ocupacion;
    
    @ManyToOne
    @JoinColumn(name="fk_nivelEconomico")
    private NivelEconomico _nivelEconomico;

    @OneToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario _usuario;
    
    //Getters, Setters, y otros metodos.

    public int getCedula() {
        return _cedula;
    }

    public String getPrimerNombre() {
        return _primerNombre;
    }

    public String getSegundoNombre() {
        return _segundoNombre;
    }

    public String getPrimerApellido() {
        return _primerApellido;
    }

    public String getSegundoApellido() {
        return _segundoApellido;
    }

    public String getGenero() {
        return _genero;
    }

    public Date getFechaNacimiento() {
        return _fechaNacimiento;
    }

    public String getEstadoCivil() {
        return _estadoCivil;
    }

    public String getDisponibilidadEnLinea() {
        return _disponibilidadEnLinea;
    }

    public int getCantidadPersonas() {
        return _cantidadPersonas;
    }

    public Lugar getLugar() {
        return _lugar;
    }

    public NivelAcademico getNivelAcademico() {
        return _nivelAcademico;
    }

    public Ocupacion getOcupacion() {
        return _ocupacion;
    }

    public NivelEconomico getNivelEconomico() {
        return _nivelEconomico;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void setCedula(int _cedula) {
        this._cedula = _cedula;
    }

    public void setPrimerNombre(String _primerNombre) {
        this._primerNombre = _primerNombre;
    }

    public void setSegundoNombre(String _segundoNombre) {
        this._segundoNombre = _segundoNombre;
    }

    public void setPrimerApellido(String _primerApellido) {
        this._primerApellido = _primerApellido;
    }

    public void setSegundoApellido(String _segundoApellido) {
        this._segundoApellido = _segundoApellido;
    }

    public void setGenero(String _genero) {
        this._genero = _genero;
    }

    public void setFechaNacimiento(Date _fechaNacimiento) {
        this._fechaNacimiento = _fechaNacimiento;
    }

    public void setEstadoCivil(String _estadoCivil) {
        this._estadoCivil = _estadoCivil;
    }

    public void setDisponibilidadEnLinea(String _disponibilidadEnLinea) {
        this._disponibilidadEnLinea = _disponibilidadEnLinea;
    }

    public void setCantidadPersonas(int _cantidadPersonas) {
        this._cantidadPersonas = _cantidadPersonas;
    }

    public void setLugar(Lugar _lugar) {
        this._lugar = _lugar;
    }

    public void setNivelAcademico(NivelAcademico _nivelAcademico) {
        this._nivelAcademico = _nivelAcademico;
    }

    public void setOcupacion(Ocupacion _ocupacion) {
        this._ocupacion = _ocupacion;
    }

    public void setNivelEconomico(NivelEconomico _nivelEconomico) {
        this._nivelEconomico = _nivelEconomico;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public Informacion (String estatus) {
        super(estatus);
    }
    
    public Informacion( long id){
        super (id);
    }
    
    public Informacion(){
        super();
    }

    @Override
    public String toString() {
        return "Informacion{" +
                "_cedula=" + _cedula +
                ", _primerNombre='" + _primerNombre + '\'' +
                ", _segundoNombre='" + _segundoNombre + '\'' +
                ", _primerApellido='" + _primerApellido + '\'' +
                ", _segundoApellido='" + _segundoApellido + '\'' +
                ", _genero='" + _genero + '\'' +
                ", _fechaNacimiento=" + _fechaNacimiento +
                ", _estadoCivil='" + _estadoCivil + '\'' +
                ", _disponibilidadEnLinea='" + _disponibilidadEnLinea + '\'' +
                ", _cantidadPersonas=" + _cantidadPersonas +
                ", _lugar=" + _lugar +
                ", _nivelAcademico=" + _nivelAcademico +
                ", _ocupacion=" + _ocupacion +
                ", _nivelEconomico=" + _nivelEconomico +
                '}';
    }
}
