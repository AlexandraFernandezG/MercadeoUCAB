package ucab.dsw.servicio;
import java.util.ArrayList;
import java.util.List;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;

import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/subcategoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SubCategoriaAPI extends AplicacionBase{

    // Listar subcategorias
    @GET
    @Path("/allSubcategoria")
    public List<Subcategoria> listarSubCategorias(){

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        return daoSubcategoria.findAll(Subcategoria.class);
    }

    // Consultar una subcategoria
    @GET
    @Path ("/consultarSubCategoria/{id}")
    public Subcategoria consultarSubCategoria(@PathParam("id") long id){

            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            return daoSubcategoria.find(id, Subcategoria.class);
    }

    //Mostrar subcategorias activas
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

    //Agregar una subcategoria
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

    //Actualizar estatus de subcategoria
    @PUT
    @Path("/estatusSubcategoria/{id}")
    public Response modificarEstatusSubcategoria(@PathParam("id") long id, SubcategoriaDto subcategoriaDto){

        DaoSubcategoria dao = new DaoSubcategoria();
        Subcategoria subcategoria_modificar = dao.find(id, Subcategoria.class);

        if (subcategoria_modificar != null) {

            subcategoria_modificar.set_estatus(subcategoriaDto.get_estatus());
            dao.update(subcategoria_modificar);
            return Response.ok().entity(subcategoria_modificar).build();

        }

        else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //Actualizar una subcategoria
    @PUT
    @Path("/updateSubCategoria/{id}")
    public Response modificarSubCategoria(@PathParam("id") long id, SubcategoriaDto subcategoriaDto){

        DaoSubcategoria dao = new DaoSubcategoria();
        Subcategoria subcategoria_modificar = dao.find(id, Subcategoria.class);

        if (subcategoria_modificar != null) {

            subcategoria_modificar.setNombre(subcategoriaDto.getNombre());
            subcategoria_modificar.setDescripcion(subcategoriaDto.getDescripcion());
            dao.update(subcategoria_modificar);
            return Response.ok().entity(subcategoria_modificar).build();

        }

        else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //Eliminar una subcategoria
    @DELETE
    @Path("/deleteSubCategoria/{id}")
    public Response eliminarSubCategoria(@PathParam("id") long id){

        DaoSubcategoria dao = new DaoSubcategoria();
        Subcategoria subcategoria_eliminar = dao.find(id, Subcategoria.class);

        if (subcategoria_eliminar != null){

                ucab.dsw.servicio.ProductoAPI servicio_1 = new ucab.dsw.servicio.ProductoAPI();
                ucab.dsw.servicio.PreguntaEncuestaAPI servicio_2 = new ucab.dsw.servicio.PreguntaEncuestaAPI();
                servicio_1.deleteProducto(id);
                servicio_2.eliminarPreguntaEncuesta(id);
                dao.delete(subcategoria_eliminar);
        }
        else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(subcategoria_eliminar).build();
    }
}


