/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.*;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;

import java.util.List;

public class CategoriaAPI_Test {

    //Esta prueba permite obtener una lista de categorias solamente activas.
    @Test
    public void pruebaListarActivos(){

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        List<Categoria> listaCategoriasActivas = servicio.categoriasActivas();
        List<Categoria> listaCategorias = servicio.listarCategorias();
        Assert.assertThat(listaCategorias, IsNot.not(IsEqual.equalTo(listaCategoriasActivas)));
    }

    // Esta prueba permite insertar una categoria a la BD
    @Test
    public void pruebaInsertarCategoria() {

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        CategoriaDto categoriaDto = new CategoriaDto();
        
        categoriaDto.setNombre("Cosmeticos");
        categoriaDto.set_descripcion("Pintura labial redfashion");
        categoriaDto.set_estatus("Activo");
        Categoria resultado = servicio.addCategoria(categoriaDto);
        Assert.assertNotEquals( resultado.get_id(), 0 );
    }

    // Este prueba permite modificar los campos de un registro
    @Test
    public void pruebaModificarCategoria() throws Exception {

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNombre("Comida");
        categoriaDto.set_descripcion("Perros calientes de arandanos");
        categoriaDto.set_estatus("Inactivo");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarCategoria(5L,categoriaDto);

    }

    // Esta prueba permite eliminar una categoria
    @Test
    public void pruebaEliminarCategoria() throws Exception {

        ucab.dsw.servicio.CategoriaAPI servicio = new ucab.dsw.servicio.CategoriaAPI();
        // Recuerden que deben ver los id de los registros en la BD
        servicio.eliminarCategoria(3L);
    }
}
