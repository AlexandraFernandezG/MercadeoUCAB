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

import javax.ws.rs.core.Response;

public class CategoriaServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarCategorias
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarCategorias(){

        CategoriaServicio servicio = new CategoriaServicio();
        Response respuesta = servicio.listarCategorias();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarSubcategoriasCategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarSubcategoriasCategoria(){

        CategoriaServicio servicio = new CategoriaServicio();
        Response respuesta = servicio.listarSubcategoriasDeCategoria(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarCategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaConsultarCategoria(){

        CategoriaServicio servicio = new CategoriaServicio();
        Response respuesta = servicio.consultarCategoria(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarCategorias
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarCategoriasActivas(){

        CategoriaServicio servicio = new CategoriaServicio();
        Response respuesta = servicio.categoriasActivas();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarCategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarCategoria() {

        CategoriaServicio servicio = new CategoriaServicio();

        try {

            CategoriaDto categoriaDto = new CategoriaDto();

            categoriaDto.setNombre("Jabones express");
            categoriaDto.setDescripcion("Jabones rapidos");
            categoriaDto.setEstatus("Inactivo");
            Response respuesta = servicio.addCategoria(categoriaDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarEstatusCategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarEstatusCategoria() {

        CategoriaServicio servicio = new CategoriaServicio();

        try {

            CategoriaDto categoriaDto = new CategoriaDto();
            categoriaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            Response respuesta = servicio.modificarEstatusCategoria(2, categoriaDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarCategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarCategoria() {

        CategoriaServicio servicio = new CategoriaServicio();

        try {
            CategoriaDto categoriaDto = new CategoriaDto();
            categoriaDto.setNombre("Comida");
            categoriaDto.setDescripcion("Perros calientes de arandanos");
            categoriaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            Response respuesta = servicio.modificarCategoria(2, categoriaDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarCategoria
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarCategoria() {

        CategoriaServicio servicio = new CategoriaServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            Response respuesta = servicio.eliminarCategoria(2);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}
