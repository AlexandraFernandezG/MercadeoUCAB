package ucab.dsw.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table( name = "usuario" )
public class Usuario extends EntidadBase {

    //Columnas

    @Column( name = "nombreUsuario" )
    private String _nombre;
    
    @Column( name = "correo" )
    private String _correoelectronico;

    @Column( name = "codigoRecuperacion" )
    private String _codigoRecuperacion;

    //Relaciones
   
    @ManyToOne
    @JoinColumn( name = "fk_rol" )
    private Rol _rol;
    
    ////Getters, Setters, y otros metodos.

    public String getNombre() {
        return _nombre;
    }

    public String getCorreoelectronico() {
        return _correoelectronico;
    }

    public String getCodigoRecuperacion() {
        return _codigoRecuperacion;
    }

    public Rol getRol() {
        return _rol;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setCorreoelectronico(String _correoelectronico) {
        this._correoelectronico = _correoelectronico;
    }

    public void setCodigoRecuperacion(String _codigoRecuperacion) {
        this._codigoRecuperacion = _codigoRecuperacion;
    }

    public void setRol(Rol _rol) {
        this._rol = _rol;
    }

    public Usuario (String estatus) {
        super(estatus);
    }

    public Usuario( long id )
    {
        super( id );
    }

    public Usuario() {
        super();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "_nombre='" + _nombre + '\'' +
                ", _correoelectronico='" + _correoelectronico + '\'' +
                ", _codigoRecuperacion='" + _codigoRecuperacion + '\'' +
                ", _rol=" + _rol +
                '}';
    }
}
