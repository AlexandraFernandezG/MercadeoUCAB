package ucab.dsw.dtos;

public class UsuarioDto extends DtoBase{

    private String nombreUsuario;
    private String correo;
    private String codigoRecuperacion;
    private RolDto rolDto;
    private String contrasena;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigoRecuperacion() {
        return codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String codigoRecuperacion) {
        this.codigoRecuperacion = codigoRecuperacion;
    }

    public RolDto getRolDto() {
        return rolDto;
    }

    public void setRolDto(RolDto rolDto) {
        this.rolDto = rolDto;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

