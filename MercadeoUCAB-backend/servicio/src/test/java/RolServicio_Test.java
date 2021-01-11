
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.servicio.RolServicio;

public class RolServicio_Test {

    //Listar Roles
    @Test
    public void pruebaListarRoles(){

        RolServicio servicio = new RolServicio();

        try {
            Assertions.assertTrue(servicio.listarRoles().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un rol
    @Test
    public void pruebaConsultarRol(){

        RolServicio servicio = new RolServicio();
        Rol rol_buscar = servicio.consultarRol(1);

        try {
            Assertions.assertEquals(1, rol_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Permite insertar un rol
    @Test
    public void pruebaInsertarRol(){

        RolServicio servicio = new RolServicio();

        try {

            RolDto rolDto = new RolDto();
            rolDto.setNombre("Administrador");
            rolDto.setEstatus("Activo");
            RolDto resultado = servicio.addRol(rolDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Modificar rol
    @Test
    public void pruebaModificarRol(){

        RolServicio servicio = new RolServicio();

        try {

            long id = 1;
            RolDto rolDto = new RolDto();
            rolDto.setNombre("Administrador");
            rolDto.setEstatus("Activo");
            servicio.updateRol(1, rolDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Eliminar un rol
    @Test
    public void pruebaEliminarRol(){

        RolServicio servicio = new RolServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            servicio.deleteRol(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }


}
