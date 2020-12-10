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
    
    @GET
    @Path("/allCategoria")
    public List<Categoria> listarCategorias(){
        
        DaoCategoria categoriaDao = new DaoCategoria();
        return categoriaDao.findAll(Categoria.class);
    }

    @GET
    @Path ("/consultarCategoria/{id}")
    public Categoria consultarCategoria(@PathParam("id") long id){

        DaoCategoria categoriaDao = new DaoCategoria();
        return categoriaDao.find(id, Categoria.class);
    }


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

    @GET
    @Path("/mostrarSubcategoriasCategoria/{id}")
    public List<Subcategoria> listarSubcategoriasDeCategoria(@PathParam("id") long id){

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);
        List<Subcategoria> listaSubcategoriasCategoria = new ArrayList<Subcategoria>();

        for (Subcategoria subcategoria: listaSubcategorias){

            if(subcategoria.get_categoria().get_id() == id){
                listaSubcategoriasCategoria.add(subcategoria);
            }
        }
        return listaSubcategoriasCategoria;
    }

    //Agregar una categoria
    @POST
    @Path("/addCategoria")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public CategoriaDto addCategoria(CategoriaDto categoriaDto)
    {
        CategoriaDto resultado = new CategoriaDto();
        try {

            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = new Categoria();

            categoria.set_nombre(categoriaDto.getNombre());
            categoria.set_descripcion(categoriaDto.getDescripcion());
            categoria.set_estatus(categoriaDto.getEstatus());
            Categoria resul = dao.insert(categoria);
            resultado.setId(resul.get_id());
        }
        catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return resultado;
    }
    
    @PUT
    @Path("/updateCategoria/{id}")
    public Response modificarCategoria(@PathParam("id") long id, CategoriaDto categoriaDto){
        
        DaoCategoria dao = new DaoCategoria();
        Categoria categoria_modificar = dao.find(id, Categoria.class);
        
        if (categoria_modificar != null){

            categoria_modificar.set_nombre(categoriaDto.getNombre());
            categoria_modificar.set_descripcion(categoriaDto.getDescripcion());
            categoria_modificar.set_estatus(categoriaDto.getEstatus());
            dao.update(categoria_modificar);
            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();

            if (categoria_modificar.get_estatus() == "Inactivo") {

                List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

                for (Subcategoria subcategoria : listaSubcategorias) {

                    if (subcategoria.get_categoria().get_id() == id) {
                        subcategoria.set_estatus("Inactivo");
                        daoSubcategoria.update(subcategoria);
                    }
                }
            } else if (categoria_modificar.get_estatus() == "Activo"){

                List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

                for (Subcategoria subcategoria : listaSubcategorias) {

                    if (subcategoria.get_categoria().get_id() == id) {
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

    @DELETE
    @Path("/deleteCategoria/{id}")
    public Response eliminarCategoria(@PathParam("id") long id){
        
        DaoCategoria dao = new DaoCategoria();
        Categoria categoria_eliminar = dao.find(id, Categoria.class);

        if (categoria_eliminar != null){

            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

            for (Subcategoria subcategoria : listaSubcategorias) {

                if (subcategoria.get_categoria().get_id() == id) {
                    daoSubcategoria.delete(subcategoria);
                }
            }

            dao.delete(categoria_eliminar);
            return Response.ok().entity(categoria_eliminar).build();

        }
        else {
            
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
   
