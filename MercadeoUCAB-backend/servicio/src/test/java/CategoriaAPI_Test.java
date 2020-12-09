/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.*;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;

public class CategoriaAPI_Test {

    // Esta prueba permite insertar una categoria a la BD
    @Test
    public void pruebaInsertarCategoria() {

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        CategoriaDto categoriaDto = new CategoriaDto();
        
        categoriaDto.setNombre("Comida rapida");
        categoriaDto.set_descripcion("Alimentos poco nutritivos");
        categoriaDto.set_estatus("Activo");
        Categoria resultado = servicio.addCategoria(categoriaDto);
        Assert.assertNotEquals( resultado.get_id(), 0 );
    }

    //Modificar estatus de la categoria
    @Test
    public void pruebaModificarEstatusCategoria() throws Exception {

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.set_estatus("Activo");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarEstatusCategoria(1L,categoriaDto);
    }

    // Este prueba permite modificar los campos de un registro
    @Test
    public void pruebaModificarCategoria() throws Exception {

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNombre("Comida");
        categoriaDto.set_descripcion("Perros calientes de arandanos");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarCategoria(1L,categoriaDto);

    }

    // Esta prueba permite eliminar una categoria
    @Test
    public void pruebaEliminarCategoria() throws Exception {

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        // Recuerden que deben ver los id de los registros en la BD
        servicio.eliminarCategoria(1L);
    }
}
