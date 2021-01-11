import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.servicio.SubCategoriaServicio;

public class SubCategoriaServicio_Test {

    //Listar todos las subcategorias
    @Test
    public void pruebaListarSubcategorias(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();

        try {
            Assertions.assertTrue(servicio.listarSubCategorias().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar una subcategoria
    @Test
    public void pruebaConsultarSubcategoria(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();
        Subcategoria subcategoria_buscar = servicio.consultarSubCategoria(1);

        try {
            Assertions.assertEquals(1, subcategoria_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar subcategorias activas
    @Test
    public void pruebaListarSubcategoriasActivas(){

        try {
            Assertions.assertNotNull(new SubCategoriaServicio().subcategoriasActivas());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Esta prueba permite insertar una subcategoria
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
            SubcategoriaDto resultado = servicio.addSubCategoria(subcategoriaDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Esta prueba permite modificar una subcategoria
    @Test
    public void pruebaModificarSubCategoria(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();

        try {
            SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
            subcategoriaDto.setNombre("Juguetes");
            subcategoriaDto.setDescripcion("Para diversion de los chicos");
            subcategoriaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            servicio.modificarSubCategoria(3, subcategoriaDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    //Esta prueba permite eliminar una subcategoria
    @Test
    public void pruebaEliminarSubCategoria(){

        SubCategoriaServicio servicio = new SubCategoriaServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            servicio.eliminarSubCategoria(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }
}
