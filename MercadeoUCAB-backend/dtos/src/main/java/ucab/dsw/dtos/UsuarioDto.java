package ucab.dsw.dtos;

public class UsuarioDto extends DtoBase{
    private String _nombreUsuario;
    private String _correo;
    private String _codigoRecuperacion;
    private RolDto _rol;
    private String _contrasena;

    public String getContrasena() {
        return _contrasena;
    }

    public void setContrasena(String _contrasena) {
        this._contrasena = _contrasena;
    }

    public RolDto getRol() {
        return _rol;
    }

    public void setRol(RolDto _rol) {
        this._rol = _rol;
    }

    public String getCodigoRecuperacion() {
        return _codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String _codigoRecuperacion) {
        this._codigoRecuperacion = _codigoRecuperacion;
    }

    public String getCorreo() {
        return _correo;
    }

    public void setCorreo(String _correo) {
        this._correo = _correo;
    }

    public String getNombreUsuario() {
        return _nombreUsuario;
    }

    public void setNombreUsuario(String _nombreUsuario) {
        this._nombreUsuario = _nombreUsuario;
    }

    public UsuarioDto (long id) throws Exception{
        super(id);
    }

    public UsuarioDto (String estatus) throws Exception {
        super(estatus);
    }


    public String getNombreRol() {
        return _rol.getNombre();
    }

    public UsuarioDto (){
        super();
    }


}

