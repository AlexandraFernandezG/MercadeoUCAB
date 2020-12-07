import org.junit.Test;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.UsuarioDto;

public class DirectorioActivo_Test {
    @Test
    public void createUserLDAP()
    {
        UsuarioDto user = new UsuarioDto();
        user.setCorreo( "greggspinetti@gmail.com" );
        user.setContrasena( "1234abcd" );
        user.set_estatus("Activo");
        RolDto rol = new RolDto();
        rol.set_nombre("Administrador");
        user.setRol(rol);
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.addEntryToLdap( user );
    }

    @Test
    public void deleteUserLDAP()
    {
        UsuarioDto user = new UsuarioDto();
        user.setCorreo( "greggspinetti@gmail.com" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.deleteEntry( user );
    }

    @Test
    public void getUserLDAP()
    {
        UsuarioDto user = new UsuarioDto();
        user.setCorreo( "greggspinetti@gmail.com" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.getEntry( user );
    }

    @Test
    public void changePassword()
    {
        UsuarioDto user = new UsuarioDto();
        user.setCorreo( "greggspinetti@gmail.com" );
        user.setContrasena( "otraclave" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.changePassword( user );
    }

    @Test
    public void userAuthentication()
    {
        UsuarioDto user = new UsuarioDto();
        user.setCorreo( "greggspinetti@gmail.com" );
        user.setContrasena( "otraclave" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.userAuthentication( user );
    }

    @Test
    public void userRole()
    {
        UsuarioDto user = new UsuarioDto();
        user.setCorreo( "greggspinetti@gmail.com" );
        user.setContrasena( "otraclave" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.getEntryRole( user );
    }
}
