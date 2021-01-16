import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.servicio.SubCategoriaServicio;

import javax.ws.rs.core.Response;

public class SubCategoriaServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarSubcategorias
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarSubcategorias(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();
        Response respuesta = servicio.listarSubCategorias();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarSubcategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaConsultarSubcategoria(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();
        Response respuesta = servicio.consultarSubCategoria(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarSubcategoriasActivas
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarSubcategoriasActivas(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();
        Response respuesta = servicio.subcategoriasActivas();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarSubcategoriasPreguntas
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarSubcategoriasPreguntas(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();
        Response respuesta = servicio.listarPreguntasSubcategoria(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarSubCategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarSubCategoria() throws Exception{

        SubCategoriaServicio servicio = new SubCategoriaServicio();

        try {
            SubcategoriaDto subcategoriaDto = new SubcategoriaDto();

            subcategoriaDto.setNombre("Jabones perfumados grasientos plus");
            subcategoriaDto.setDescripcion("Mas olor exquisito a grasa");
            subcategoriaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            CategoriaDto categoriaDto = new CategoriaDto(1);
            subcategoriaDto.setCategoriaDto(categoriaDto);
            Response respuesta = servicio.addSubCategoria(subcategoriaDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarSubCategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarSubCategoria(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();

        try {
            SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
            subcategoriaDto.setNombre("Juguetes");
            subcategoriaDto.setDescripcion("Para diversion de los chicos");
            subcategoriaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            Response respuesta = servicio.modificarSubCategoria(3, subcategoriaDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarSubCategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarSubCategoria(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            Response respuesta = servicio.eliminarSubCategoria(1);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }
}
