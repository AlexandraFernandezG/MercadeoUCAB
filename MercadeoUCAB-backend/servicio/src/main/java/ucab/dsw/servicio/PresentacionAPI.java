package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.entidades.Producto;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**@author valen
 * */

@Stateless
@Path("/presentacion")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class PresentacionAPI extends AplicacionBase{
	
	@GET
	@Path("/allPresentaciones")
	public List<Presentacion> listarPresentaciones() throws NullPointerException  {
		/* Obtiene TODAS las presentaciones existentes en la base de datos
		* y las guarda en una lista.
		* */
		DaoPresentacion daoPresentacion = new DaoPresentacion();
		return daoPresentacion.findAll(Presentacion.class);
	}
	
	@GET
	@Path("/consultarPresentaciones/{id}")
	public Presentacion consultarPresentacion(@PathParam("id") long id) throws NullPointerException  {
		DaoPresentacion daoPresentacion = new DaoPresentacion();
		
		try {
			return daoPresentacion.find(id, Presentacion.class);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	@GET
	@Path("/allPresentacionesActivas")
	public List<Presentacion> listarPresentacionesActivas() throws NullPointerException{
		DaoPresentacion daoPresentacion = new DaoPresentacion();
		List<Presentacion> listaPresentaciones = daoPresentacion.findAll(Presentacion.class);
		List<Presentacion> listaPresentacionesActivas = new ArrayList<>();
		
		if (listaPresentaciones != null) {
			for (Presentacion presentacion : listaPresentaciones) {
				if ("Activo".equals(presentacion.get_estatus())) {
					listaPresentacionesActivas.add(presentacion);
				}
			}
		}
		
		return listaPresentacionesActivas;
	}
	
	@POST
	@Path("/addPresentacion")
	public Presentacion agregarPresentacion(PresentacionDto presentacionDto) {
		DaoPresentacion daoPresentacion = new DaoPresentacion();
		Presentacion presentacionNueva = null;
		
		DaoProducto daoProducto = new DaoProducto();
		ProductoDto productoDto = presentacionDto.get_productoDto();
		Producto producto;
		producto = daoProducto.find(productoDto.getId(), Producto.class);
		
		if (producto != null) {
			presentacionNueva = new Presentacion(presentacionDto.getId());
			presentacionNueva.setNombre(presentacionDto.get_nombre());
			presentacionNueva.setCaracteristicas(presentacionDto.get_caracteristicas());
			presentacionNueva.set_estatus(presentacionDto.get_estatus());
			presentacionNueva.setProducto(producto);
			
			daoPresentacion.insert(presentacionNueva);
		}
		
		return presentacionNueva;
	}
	
	@PUT
	@Path("/updatePresentacion/{id}")
	public Response modificarPresentacion(@PathParam("id") long id, PresentacionDto presentacionDto) throws NullPointerException {
		DaoPresentacion daoPresentacion = new DaoPresentacion();
		Presentacion presentacionParaModificar = null;
		
		DaoProducto daoProducto = new DaoProducto();
		ProductoDto productoDto = presentacionDto.get_productoDto();
		Producto producto;
		producto = daoProducto.find(productoDto.getId(), Producto.class);
		
		if (producto != null) {
			presentacionParaModificar = daoPresentacion.find(id, Presentacion.class);
			presentacionParaModificar.setNombre(presentacionDto.get_nombre());
			presentacionParaModificar.setCaracteristicas(presentacionDto.get_caracteristicas());
			presentacionParaModificar.set_estatus(presentacionDto.get_estatus());
			presentacionParaModificar.setProducto(producto);
		}
		
		try {
			daoPresentacion.update(presentacionParaModificar);
		} catch (Exception e) {
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
		
		return Response.ok().entity(presentacionParaModificar).build();
	}
	
	@DELETE
	@Path("/deletePresentacion/{id}")
	public Response eliminarPresentacion(@PathParam("id") long id) {
		DaoPresentacion daoPresentacion = new DaoPresentacion();
		Presentacion presentacionParaEliminar = daoPresentacion.find(id, Presentacion.class);
		
		if (presentacionParaEliminar == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		try {
			daoPresentacion.delete(presentacionParaEliminar);
		} catch (Exception e) {
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
		
		return Response.ok().entity(presentacionParaEliminar).build();
	}
}
