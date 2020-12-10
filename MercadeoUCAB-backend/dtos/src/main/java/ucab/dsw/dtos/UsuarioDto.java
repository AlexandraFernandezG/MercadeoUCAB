package ucab.dsw.dtos;

public class UsuarioDto extends DtoBase{
    private String nombreUsuario;
    private String correo;
    private String codigoRecuperacion;
    private RolDto rol;
    private String contrasena;

    public String getCodigoRecuperacion() {
        return codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String codigoRecuperacion) {
        this.codigoRecuperacion = codigoRecuperacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public RolDto getRol() {
        return rol;
    }

    public void setRol(RolDto _rol) {
        this.rol = _rol;
    }

    public UsuarioDto (long id) throws Exception{
        super(id);
    }

    public UsuarioDto (String estatus) throws Exception {
        super(estatus);
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public RolDto getRolDto() {
        return rol;
    }

    public void setRolDto(RolDto rol) {
        this.rol = rol;
    }

    public String getNombreRol() {
        RolDto rol = this.rol;
        return rol.getNombre();
    }

    public UsuarioDto (){
        super();
    }


}

