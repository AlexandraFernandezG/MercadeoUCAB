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

    public RolDto get_rol() {
        return rol;
    }

    public void set_rol(RolDto _rol) {
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

    public RolDto getRol() {
        return rol;
    }

    public void setRol(RolDto rol) {
        this.rol = rol;
    }

    public String getNombreRol() {
        return this.rol.get_nombre();
    }

    public UsuarioDto (){
        super();
    }


}

