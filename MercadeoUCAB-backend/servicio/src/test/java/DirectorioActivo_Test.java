import org.junit.Test;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.excepciones.PruebaExcepcion;

public class DirectorioActivo_Test {
    @Test
    public void createUserLDAP() throws PruebaExcepcion {
        UsuarioDto user = new UsuarioDto();
        user.setNombreUsuario("EmanuelDriver");
        user.setCorreo( "emadicristo@hotmail.com" );
        user.setContrasena( "juventus1234" );
        user.setEstatus("Activo");
        RolDto rol = new RolDto();
        rol.setNombre("Administrador");
        user.setRol(rol);
        user.setId(1);
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
