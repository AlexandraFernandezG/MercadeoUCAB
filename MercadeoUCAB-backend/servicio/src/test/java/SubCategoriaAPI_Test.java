import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.*;
import java.util.List;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;

public class SubCategoriaAPI_Test {

    //Esta prueba muestra la lista de subcategorias activas.
    @Test
    public void pruebaListarActivos(){

        ucab.dsw.servicio.SubCategoriaAPI servicio = new ucab.dsw.servicio.SubCategoriaAPI();
        List<Subcategoria> listaSubcategoriasActivas = servicio.subcategoriasActivas();
        List<Subcategoria> listaSubcategorias = servicio.listarSubCategorias();
        Assert.assertThat(listaSubcategorias, IsNot.not(IsEqual.equalTo(listaSubcategoriasActivas)));
    }

    // Esta prueba permite insertar una subcategoria
    @Test
    public void pruebaInsertarSubCategoria() throws Exception{

        ucab.dsw.servicio.SubCategoriaAPI servicio = new ucab.dsw.servicio.SubCategoriaAPI();
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto();

        subcategoriaDto.setNombre("Limpieza");
        subcategoriaDto.setDescripcion("Para la limpieza del hogar");
        subcategoriaDto.setEstatus("Inactivo");
        // Recuerden que deben ver los id de los registros en la BD
        CategoriaDto categoriaDto = new CategoriaDto(5L);
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
        subcategoriaDto.setEstatus("Inactivo");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarSubCategoria(3L,subcategoriaDto);

    }

    //Esta prueba permite eliminar una subcategoria
    @Test
    public void pruebaEliminarSubCategoria(){

        ucab.dsw.servicio.SubCategoriaAPI servicio = new ucab.dsw.servicio.SubCategoriaAPI();
        // Recuerden que deben ver los id de los registros en la BD
        servicio.eliminarSubCategoria(3l);

    }
}
