import org.junit.*;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;

public class SubCategoriaAPI_Test {

    // Esta prueba permite insertar una subcategoria
    @Test
    public void pruebaInsertarSubCategoria() throws Exception{

        ucab.dsw.servicio.SubCategoriaAPI servicio = new ucab.dsw.servicio.SubCategoriaAPI();
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto();

        subcategoriaDto.setNombre("Perros calientes");
        subcategoriaDto.setDescripcion("Perros calientes con sabores raros");
        subcategoriaDto.set_estatus("Activo");
        // Recuerden que deben ver los id de los registros en la BD
        CategoriaDto categoriaDto = new CategoriaDto(1L);
        subcategoriaDto.setCategoriaDto(categoriaDto);
        Subcategoria resultado = servicio.addSubCategoria(subcategoriaDto);
        Assert.assertNotEquals( resultado.get_id(), 0 );
    }

    // Esta prueba permite modificar una subcategoria
    @Test
    public void pruebaModificarSubCategoria(){

        ucab.dsw.servicio.SubCategoriaAPI servicio = new ucab.dsw.servicio.SubCategoriaAPI();
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
        subcategoriaDto.setNombre("Juguetes");
        subcategoriaDto.setDescripcion("Para diversion de los chicos");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarSubCategoria(3L,subcategoriaDto);

    }

    //Esta prueba permite eliminar una subcategoria
    @Test
    public void pruebaEliminarSubCategoria(){

        ucab.dsw.servicio.SubCategoriaAPI servicio = new ucab.dsw.servicio.SubCategoriaAPI();
        // Recuerden que deben ver los id de los registros en la BD
        servicio.eliminarSubCategoria(1L);

    }
}
