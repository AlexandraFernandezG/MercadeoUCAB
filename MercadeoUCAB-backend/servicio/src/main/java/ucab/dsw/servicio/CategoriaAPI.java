package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/categoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class CategoriaAPI extends AplicacionBase {

    // Listar todas las categorias disponibles
    @GET
    @Path("/allCategoria")
    public List<Categoria> listarCategorias(){
        
        DaoCategoria categoriaDao = new DaoCategoria();
        return categoriaDao.findAll(Categoria.class);
    }

    //Consultar una categoria en especifico
    @GET
    @Path ("/consultarCategoria/{id}")
    public Categoria consultarCategoria(@PathParam("id") long id){

        DaoCategoria categoriaDao = new DaoCategoria();
        return categoriaDao.find(id, Categoria.class);
    }

    // Mostrar una lista de todas las categorias de estatus activas
    @GET
    @Path ("/mostrarCategoriasActivas")
    public List<Categoria> categoriasActivas(){

        DaoCategoria daoCategoria = new DaoCategoria();
        List<Categoria> listaCategorias = daoCategoria.findAll(Categoria.class);
        List<Categoria> listaCategoriasActivas = new ArrayList<Categoria>();

        for (Categoria categoria: listaCategorias){

            if (categoria.get_estatus().equals("Activo")) {
                listaCategoriasActivas.add(categoria);
            }
        }
        return listaCategoriasActivas;
    }

    // Mostrar las subcategorias de una categoria
    @GET
    @Path("/mostrarSubcategoriasCategoria/{id}")
    public List<Subcategoria> listarSubcategoriasDeCategoria(@PathParam("id") long id){

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);
        List<Subcategoria> listaSubcategoriasCategoria = new ArrayList<Subcategoria>();

        for (Subcategoria subcategoria: listaSubcategorias){

            if(subcategoria.getCategoria().get_id() == id){
                listaSubcategoriasCategoria.add(subcategoria);
            }
        }
        return listaSubcategoriasCategoria;
    }

    @GET
    @Path("/verificarNombre")
    public Boolean verificarNombreCategoria(CategoriaDto categoriaDto){

        DaoCategoria daoCategoria = new DaoCategoria();
        List<Categoria> listaCategoria = daoCategoria.findAll(Categoria.class);

        for (Categoria categoria: listaCategoria){

            if(categoria.getNombre().equals(categoriaDto.getNombre())){
                System.out.println("No se puede insertar esta categoria");
                return true;
            }
        }

        addCategoria(categoriaDto);
        return false;
    }

    //Agregar una categoria
    @POST
    @Path("/addCategoria")
    public Categoria addCategoria(CategoriaDto categoriaDto)
    {      
        DaoCategoria dao = new DaoCategoria();
        Categoria categoria = new Categoria();

            categoria.setNombre(categoriaDto.getNombre());
            categoria.set_descripcion(categoriaDto.get_descripcion());
            categoria.set_estatus(categoriaDto.get_estatus());
            dao.insert(categoria);

        return  categoria;
    }

    //Actualizar el estatus de categoria
    @PUT
    @Path("/estatusCategoria/{id}")
    public Response modificarEstatusCategoria(@PathParam("id") long id, CategoriaDto categoriaDto){

        DaoCategoria daoCategoria = new DaoCategoria();
        Categoria categoria_modificar = daoCategoria.find(id, Categoria.class);

        if (categoria_modificar != null){

            categoria_modificar.set_estatus(categoriaDto.get_estatus());
            daoCategoria.update(categoria_modificar);
            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();

            if (categoria_modificar.get_estatus() == "Inactivo") {

                List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

                for (Subcategoria subcategoria : listaSubcategorias) {

                    if (subcategoria.getCategoria().get_id() == id) {
                        subcategoria.set_estatus("Inactivo");
                        daoSubcategoria.update(subcategoria);
                    }
                }
            } else if (categoria_modificar.get_estatus() == "Activo"){

                List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

                for (Subcategoria subcategoria : listaSubcategorias) {

                    if (subcategoria.getCategoria().get_id() == id) {
                        subcategoria.set_estatus("Activo");
                        daoSubcategoria.update(subcategoria);
                    }
                }
            }

            return Response.ok().entity(categoria_modificar).build();

        }
        else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Modificar una categoria
    @PUT
    @Path("/updateCategoria/{id}")
    public Response modificarCategoria(@PathParam("id") long id, CategoriaDto categoriaDto){
        
        DaoCategoria dao = new DaoCategoria();
        Categoria categoria_modificar = dao.find(id, Categoria.class);
        
        if (categoria_modificar != null){

            categoria_modificar.setNombre(categoriaDto.getNombre());
            categoria_modificar.set_descripcion(categoriaDto.get_descripcion());
            dao.update(categoria_modificar);
            return Response.ok().entity(categoria_modificar).build();
        }
        else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //Eliminar una categoria
    @DELETE
    @Path("/deleteCategoria/{id}")
    public Response eliminarCategoria(@PathParam("id") long id){
        
        DaoCategoria dao = new DaoCategoria();
        Categoria categoria_eliminar = dao.find(id, Categoria.class);

        if (categoria_eliminar != null){

            dao.delete(categoria_eliminar);
            return Response.ok().entity(categoria_eliminar).build();

        }
        else {
            
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
   
