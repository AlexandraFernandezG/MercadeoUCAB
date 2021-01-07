/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.servicio.CategoriaServicio;

public class CategoriaServicio_Test {

    //Listar Categorias
    @Test
    public void pruebaListarCategorias(){

        CategoriaServicio servicio = new CategoriaServicio();

        try {
            Assertions.assertTrue(servicio.listarCategorias().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Listar subcategorias de categoria
    @Test
    public void pruebaListarSubcategoriasCategoria(){

        CategoriaServicio servicio = new CategoriaServicio();

        try {
            Assertions.assertTrue(servicio.listarSubcategoriasDeCategoria(1).size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar una Categoria
    @Test
    public void pruebaConsultarCategoria(){

        CategoriaServicio servicio = new CategoriaServicio();
        Categoria categoria_buscar = servicio.consultarCategoria(1);

        try {
            Assertions.assertEquals(1, categoria_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar categorias activas
    @Test
    public void pruebaListarCategoriasActivas(){

        try {
            Assertions.assertNotNull(new CategoriaServicio().categoriasActivas());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Esta prueba permite insertar una categoria a la BD
    @Test
    public void pruebaInsertarCategoria() {

        CategoriaServicio servicio = new CategoriaServicio();

        try {
            CategoriaDto categoriaDto = new CategoriaDto();

            categoriaDto.setNombre("Jabones express");
            categoriaDto.setDescripcion("Jabones rapidos");
            categoriaDto.setEstatus("Inactivo");
            CategoriaDto resultado = servicio.addCategoria(categoriaDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Modificar estatus de la categoria
    @Test
    public void pruebaModificarEstatusCategoria() throws Exception {

        CategoriaServicio servicio = new CategoriaServicio();

        try {

            CategoriaDto categoriaDto = new CategoriaDto();
            categoriaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            servicio.modificarEstatusCategoria(1, categoriaDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Este prueba permite modificar los campos de un registro
    @Test
    public void pruebaModificarCategoria() throws Exception {

        CategoriaServicio servicio = new CategoriaServicio();

        try {
            CategoriaDto categoriaDto = new CategoriaDto();
            categoriaDto.setNombre("Comida");
            categoriaDto.setDescripcion("Perros calientes de arandanos");
            categoriaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            servicio.modificarCategoria(1, categoriaDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    // Esta prueba permite eliminar una categoria
    @Test
    public void pruebaEliminarCategoria() throws Exception {

        CategoriaServicio servicio = new CategoriaServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            servicio.eliminarCategoria(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}
