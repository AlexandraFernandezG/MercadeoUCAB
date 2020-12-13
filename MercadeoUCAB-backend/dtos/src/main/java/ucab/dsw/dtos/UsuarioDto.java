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

    public void setNombreUsuario(String nombreUsuario) {
        this._nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return _correo;
    }

    public void setCorreo(String correo) {
        this._correo = correo;
    }

    public String getCodigoRecuperacion() {
        return _codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String codigoRecuperacion) {
        this._codigoRecuperacion = codigoRecuperacion;
    }

    public RolDto getRolDto() {
        return _rolDto;
    }

    public void setRolDto(RolDto rolDto) {
        this._rolDto = rolDto;
    }

    public String getContrasena() {
        return _contrasena;
    }

    public void setContrasena(String contrasena) {
        this._contrasena = contrasena;
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

