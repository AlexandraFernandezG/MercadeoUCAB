package ucab.dsw.servicio;
import java.util.List;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import javax.ws.rs.core.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Emanuel
 */

@Path( "/subcategoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SubCategoriaAPI extends AplicacionBase{

    @GET
    @Path("/allSubCategoria")
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
    @Path("/mostrarSubcategoriasCategoria/{id}")
    public List<Subcategoria> listarSubcategoriasDeCategoria(@PathParam("id") long id){

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);
        List<Subcategoria> listaSubcategoriasCategoria = null;

        for (Subcategoria subcategoria: listaSubcategorias){

            if(subcategoria.getCategoria().get_id() == id){
                listaSubcategoriasCategoria.add(subcategoria);
            }
        }
        return listaSubcategoriasCategoria;
    }

    @GET
    @Path ("/mostrarSubCategoriasActivas")
    public List<Subcategoria> subcategoriasActivas(){

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);
        List<Subcategoria> listaSubCategoriasActivas = null;

        for (Subcategoria subcategoria: listaSubcategorias){

            if (subcategoria.get_estatus() == "Activo"){
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

            subcategoria.setNombre(subcategoriaDto.getNombre());
            subcategoria.setDescripcion(subcategoriaDto.getDescripcion());
            subcategoria.set_estatus(subcategoriaDto.get_estatus());
            Categoria categoria = new Categoria(subcategoriaDto.getCategoriaDto().getId());
            subcategoria.setCategoria(categoria);
            dao.insert(subcategoria);

        return  subcategoria;
    }

    @PUT
    @Path("/updateSubCategoria/{id}")
    public Response modificarSubCategoria(@PathParam("id") long id, SubcategoriaDto subcategoriaDto){

        DaoSubcategoria dao = new DaoSubcategoria();
        Subcategoria subcategoria_modificar = dao.find(id, Subcategoria.class);

        if (subcategoria_modificar != null) {

            subcategoria_modificar.setNombre(subcategoriaDto.getNombre());
            subcategoria_modificar.setDescripcion(subcategoriaDto.getDescripcion());
            subcategoria_modificar.set_estatus(subcategoriaDto.get_estatus());
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


