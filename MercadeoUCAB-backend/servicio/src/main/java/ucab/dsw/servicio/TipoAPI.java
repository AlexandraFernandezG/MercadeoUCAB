package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Tipo;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**@author valen
 * */

@Stateless
@Path("/tipo")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class TipoAPI extends AplicacionBase{
	
	@GET
	@Path("/allTipos")
	public List<Tipo> listarTipos() throws NullPointerException  {
		/* Obtiene TODOS los tipos existentes en la base de datos
		* y las guarda en una lista.
		* */
		DaoTipo daoTipo = new DaoTipo();
		return daoTipo.findAll(Tipo.class);
	}
	
	@GET
	@Path("/consultarTipos/{id}")
	public Tipo consultarTipo(@PathParam("id") long id) throws NullPointerException  {
		DaoTipo daoTipo = new DaoTipo();
		
		try {
			return daoTipo.find(id, Tipo.class);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	@GET
	@Path("/allTiposActivos")
	public List<Tipo> listarTiposActivos() throws NullPointerException{
		DaoTipo daoTipo = new DaoTipo();
		List<Tipo> listaTipos = daoTipo.findAll(Tipo.class);
		List<Tipo> listaTiposActivos = new ArrayList<>();
		
		if (listaTipos != null) {
			for (Tipo tipo : listaTipos) {
				if ("Activo".equals(tipo.get_estatus())) {
					listaTiposActivos.add(tipo);
				}
			}
		}
		
		return listaTiposActivos;
	}
	
	@POST
	@Path("/addTipo")
	public Tipo agregarTipo(TipoDto tipoDto) {
		DaoTipo daoTipo = new DaoTipo();
		Tipo tipoNuevo = null;
		
		DaoProducto daoProducto = new DaoProducto();
		ProductoDto productoDto = tipoDto.get_productoDto();
		Producto producto;
		producto = daoProducto.find(productoDto.getId(), Producto.class);
		
		if (producto != null && producto.get_estatus().equals("Activo")) {
			tipoNuevo = new Tipo(tipoDto.getId());
			tipoNuevo.setNombre(tipoDto.get_nombre());
			tipoNuevo.setDescripcion(tipoDto.get_descripcion());
			tipoNuevo.set_estatus(tipoDto.get_estatus());
			tipoNuevo.setProducto(producto);
			
			daoTipo.insert(tipoNuevo);
		}
		
		return tipoNuevo;
	}
	
	@PUT
	@Path("/updateTipo/{id}")
	public Response modificarTipo(@PathParam("id") long id, TipoDto tipoDto) throws NullPointerException {
		DaoTipo daoTipo = new DaoTipo();
		Tipo tipoParaModificar = daoTipo.find(id, Tipo.class);
		
		DaoProducto daoProducto = new DaoProducto();
		ProductoDto productoDto = tipoDto.get_productoDto();
		Producto producto = daoProducto.find(productoDto.getId(), Producto.class);
		
		if (producto != null && producto.get_estatus().equals("Activo")) {
			tipoParaModificar.setNombre(tipoDto.get_nombre());
			tipoParaModificar.setDescripcion(tipoDto.get_descripcion());
			tipoParaModificar.set_estatus(tipoDto.get_estatus());
			tipoParaModificar.setProducto(producto);
		}
		
		try {
			daoTipo.update(tipoParaModificar);
		} catch (Exception e) {
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
		
		return Response.ok().entity(tipoParaModificar).build();
	}
	
	@DELETE
	@Path("/deleteTipo/{id}")
	public Response eliminarTipo(@PathParam("id") long id) {
		DaoTipo daoTipo = new DaoTipo();
		Tipo tipoParaEliminar = daoTipo.find(id, Tipo.class);
		
		if (tipoParaEliminar == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		try {
			daoTipo.delete(tipoParaEliminar);
		} catch (Exception e) {
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
		
		return Response.ok().entity(tipoParaEliminar).build();
	}
}
