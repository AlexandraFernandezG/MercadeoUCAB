package ucab.dsw.entidades;


import java.util.Date;
import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "usuario_estudio" )
public class UsuarioEstudio extends EntidadBase{
    
    //Relaciones
    
    @ManyToOne
    @JoinColumn(name="fk_usuario")
    private Usuario _usuario;

    @ManyToOne
    @JoinColumn(name="fk_estudio")
    private Estudio _estudio;
    
    //Getters, Setters, y otros metodos.

    public UsuarioEstudio(long id){
        super(id);
    }

    public UsuarioEstudio(String estatus) {
        super(estatus);
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public Estudio get_estudio() {
        return _estudio;
    }

    public void set_estudio(Estudio _estudio) {
        this._estudio = _estudio;
    }

    @Override
    public String toString() {
        return "UsuarioEstudio{" +
                "_usuario=" + _usuario +
                ", _estudio=" + _estudio +
                '}';
    }

    public UsuarioEstudio(){
        super();
    }
   
}
