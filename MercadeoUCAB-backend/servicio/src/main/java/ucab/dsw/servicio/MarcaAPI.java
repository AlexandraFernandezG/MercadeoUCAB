package ucab.dsw.servicio;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Producto;

import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/marca" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class MarcaAPI extends AplicacionBase{

    @GET
    @Path("/allMarca")
    public List<Marca> listarMarcas(){
        DaoMarca daoMarca = new DaoMarca();
        return daoMarca.findAll(Marca.class);
    }

    @GET
    @Path("/consultarMarca/{id}")
    public Marca consultarMarca(@PathParam("id") long id){
        DaoMarca daoMarca = new DaoMarca();
        return daoMarca.find(id, Marca.class);
    }

    @GET
    @Path("/mostrarMarcasActivas")
    public List<Marca> marcasActivas(){

        DaoMarca daoMarca = new DaoMarca();
        List<Marca> listaMarca = daoMarca.findAll(Marca.class);
        List<Marca> listaMarcaActivas = new ArrayList<Marca>();

        for(Marca marca: listaMarca){

            if(marca.get_estatus().equals("Activo")){
                listaMarcaActivas.add(marca);
            }
        }

        return listaMarcaActivas;
    }

    @POST
    @Path("/addMarca")
    public Marca addMarca(MarcaDto marcaDto){

        DaoMarca daoMarca = new DaoMarca();
        Marca marca = new Marca();

            marca.set_nombre(marcaDto.getNombre());
            marca.set_estatus(marcaDto.get_estatus());
            marca.set_descripcion(marcaDto.get_descripcion());
            daoMarca.insert(marca);

            return marca;
    }

    @PUT
    @Path("/updateMarca/{id}")
    public Response updateMarca(@PathParam("id") long id, MarcaDto marcaDto){

        DaoMarca daoMarca = new DaoMarca();
        Marca marca_modificar = daoMarca.find(id, Marca.class);

        if (marca_modificar != null) {

            marca_modificar.set_nombre(marcaDto.getNombre());
            marca_modificar.set_estatus(marcaDto.get_estatus());
            marca_modificar.set_descripcion(marcaDto.get_descripcion());
            daoMarca.update(marca_modificar);
            return Response.ok().entity(marca_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteMarca/{id}")
    public Response deleteMarca(@PathParam("id") long id){

        DaoMarca daoMarca = new DaoMarca();
        Marca marca_eliminar = daoMarca.find(id, Marca.class);

        if(marca_eliminar != null){

            DaoProducto daoProducto = new DaoProducto();
            List<Producto> listaProducto = daoProducto.findAll(Producto.class);

            for(Producto producto: listaProducto){

                if(producto.getMarca().get_id() == id){
                    daoProducto.delete(producto);
                }
            }

            daoMarca.delete(marca_eliminar);
            return Response.ok().entity(marca_eliminar).build();

        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
