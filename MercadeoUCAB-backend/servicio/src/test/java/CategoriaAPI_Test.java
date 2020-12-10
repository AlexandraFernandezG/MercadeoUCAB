/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Tipo;

public class CategoriaAPI_Test {

    //Listar Categorias (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarCategorias(){

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();

        try {
            Assertions.assertTrue(servicio.listarCategorias().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Listar subcategorias de categoria (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarSubcategoriasCategoria(){

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();

        try {
            Assertions.assertTrue(servicio.listarSubcategoriasDeCategoria(1L).size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar una Categoria (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaConsultarCategoria(){

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        Categoria categoria_buscar = servicio.consultarCategoria(1L);

        try {
            Assertions.assertEquals(1, categoria_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar categorias activas
    @Test
    public void pruebaListarTiposActivos(){

        try {
            Assertions.assertNotNull(new ucab.dsw.servicio.CategoriaAPI().categoriasActivas());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Esta prueba permite insertar una categoria a la BD
    @Test
    public void pruebaInsertarCategoria() {
        /** Prueba Unitaria sobre la inserción de una categoría en la BD.
         *
         * Crea un DTO de la entidad categoría con la información de lo que
         * se desea insertar en la base de datos, para lo cual se usa el método
         * addCategoria.
         * */

        CategoriaAPI servicio = new CategoriaAPI();
        CategoriaDto categoriaDto = new CategoriaDto();
        
        categoriaDto.setNombre("Comida rapida");
        categoriaDto.set_descripcion("Alimentos poco nutritivos");
        categoriaDto.set_estatus("Activo");
    
        try {
            Categoria resultado = servicio.addCategoria(categoriaDto);
            Assert.assertNotEquals(0, resultado.get_id() );
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Modificar estatus de la categoria
    @Test
    public void pruebaModificarEstatusCategoria() throws Exception {

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.set_estatus("Inactivo");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarEstatusCategoria(1L,categoriaDto);
    }

    // Este prueba permite modificar los campos de un registro
    @Test
    public void pruebaModificarCategoria() throws Exception {
        /** Prueba unitaria sobre la actualización o modificación de un
         * registro Categoría en la BD.
         *
         * Modifica
         * */
        CategoriaAPI servicio = new CategoriaAPI();
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
