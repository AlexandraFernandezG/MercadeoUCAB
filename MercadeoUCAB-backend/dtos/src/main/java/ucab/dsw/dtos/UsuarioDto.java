package ucab.dsw.dtos;

public class UsuarioDto extends DtoBase{

    private String _nombreUsuario;
    private String _correo;
    private String _codigoRecuperacion;
    private RolDto _rolDto;
    private String _contrasena;

    public String getNombreUsuario() {
        return _nombreUsuario;
    }

    public void setNombreUsuario(String _nombreUsuario) {
        this._nombreUsuario = _nombreUsuario;
    }

    public String getCorreo() {
        return _correo;
    }

    public void setCorreo(String _correo) {
        this._correo = _correo;
    }

    public String getCodigoRecuperacion() {
        return _codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String _codigoRecuperacion) {
        this._codigoRecuperacion = _codigoRecuperacion;
    }

    public RolDto getRolDto() {
        return _rolDto;
    }

    public void setRolDto(RolDto _rolDto) {
        this._rolDto = _rolDto;
    }

    public String getContrasena() {
        return _contrasena;
    }

    public void setContrasena(String _contrasena) {
        this._contrasena = _contrasena;
    }

    public String getNombreRol() {
        RolDto rol = this._rolDto;
        return rol.getNombre();
    }

    public UsuarioDto (long id) throws Exception{
        super(id);
    }

    public UsuarioDto (String estatus) throws Exception {
        super(estatus);
    }

    public UsuarioDto (){
        super();
    }

}

