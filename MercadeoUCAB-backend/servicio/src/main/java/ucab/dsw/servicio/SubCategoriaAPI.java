package ucab.dsw.servicio;
import java.util.ArrayList;
import java.util.List;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/subcategoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SubCategoriaAPI extends AplicacionBase{

    @GET
    @Path("/allSubcategoria")
    public List<Subcategoria> listarSubCategorias(){

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        return daoSubcategoria.findAll(Subcategoria.class);
    }

    @GET
    @Path ("/consultarSubCategoria/{id}")
    public Subcategoria consultarSubCategoria(@PathParam("id") long id){

            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            return daoSubcategoria.find(id, Subcategoria.class);
    }

    @GET
    @Path("/mostrarSubCategoriasActivas")
    public List<Subcategoria> subcategoriasActivas(){

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);
        List<Subcategoria> listaSubCategoriasActivas = new ArrayList<Subcategoria>();

        for (Subcategoria subcategoria: listaSubcategorias){

            if (subcategoria.get_estatus().equals("Activo")){
                listaSubCategoriasActivas.add(subcategoria);
            }
        }

        return listaSubCategoriasActivas;

    }

    @POST
    @Path("/addSubCategoria")
    public Subcategoria addSubCategoria(SubcategoriaDto subcategoriaDto)
    {
        DaoSubcategoria dao = new DaoSubcategoria();
        Subcategoria subcategoria = new Subcategoria();

            subcategoria.set_nombre(subcategoriaDto.getNombre());
            subcategoria.set_descripcion(subcategoriaDto.getDescripcion());
            subcategoria.set_estatus(subcategoriaDto.getEstatus());
            Categoria categoria = new Categoria(subcategoriaDto.getCategoriaDto().getId());
            subcategoria.set_categoria(categoria);
            dao.insert(subcategoria);

        return  subcategoria;
    }

    @PUT
    @Path("/updateSubCategoria/{id}")
    public Response modificarSubCategoria(@PathParam("id") long id, SubcategoriaDto subcategoriaDto){

        DaoSubcategoria dao = new DaoSubcategoria();
        Subcategoria subcategoria_modificar = dao.find(id, Subcategoria.class);

        if (subcategoria_modificar != null) {

            subcategoria_modificar.set_nombre(subcategoriaDto.getNombre());
            subcategoria_modificar.set_descripcion(subcategoriaDto.getDescripcion());
            subcategoria_modificar.set_estatus(subcategoriaDto.getEstatus());
            dao.update(subcategoria_modificar);
            return Response.ok().entity(subcategoria_modificar).build();

        }

        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteSubCategoria/{id}")
    public Response eliminarSubCategoria(@PathParam("id") long id){

        DaoSubcategoria dao = new DaoSubcategoria();
        Subcategoria subcategoria_eliminar = dao.find(id, Subcategoria.class);

        if (subcategoria_eliminar != null){

                dao.delete(subcategoria_eliminar);
        }
        else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(subcategoria_eliminar).build();
    }
}


