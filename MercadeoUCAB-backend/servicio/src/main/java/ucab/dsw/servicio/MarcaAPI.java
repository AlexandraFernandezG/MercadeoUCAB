package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**@author valen
 * */

@Stateless
@Path("/marca")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class MarcaAPI extends AplicacionBase{
	
	@GET
	@Path("/allMarcas")
	public List<Marca> listarMarcas() throws NullPointerException  {
		/* Obtiene TODAS las marcas existentes en la base de datos
		* y las guarda en una lista.
		* */
		DaoMarca daoMarca = new DaoMarca();
		return daoMarca.findAll(Marca.class);
	}
	
	@GET
	@Path("/consultarMarcas/{id}")
	public Marca consultarMarca(@PathParam("id") long id) throws NullPointerException  {
		DaoMarca daoMarca = new DaoMarca();
		
		try {
			return daoMarca.find(id, Marca.class);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	@GET
	@Path("/allMarcasActivas")
	public List<Marca> listarMarcasActivas() throws NullPointerException{
		DaoMarca daoMarca = new DaoMarca();
		List<Marca> listaMarcas = daoMarca.findAll(Marca.class);
		List<Marca> listaMarcasActivas = new ArrayList<>();
		
		if (listaMarcas != null) {
			for (Marca marca : listaMarcas) {
				if ("Activo".equals(marca.get_estatus())) {
					listaMarcasActivas.add(marca);
				}
			}
		}
		
		return listaMarcasActivas;
	}
	
	@POST
	@Path("/addMarca")
	public Marca agregarMarca(MarcaDto marcaDto) {
		DaoMarca daoMarca = new DaoMarca();
		Marca marcaNueva = new Marca(marcaDto.getId());
		
		marcaNueva.set_nombre(marcaDto.getNombre());
		marcaNueva.set_descripcion(marcaDto.getDescripcion());
		marcaNueva.set_estatus(marcaDto.get_estatus());
		
		daoMarca.insert(marcaNueva);
		
		return marcaNueva;
	}
	
	@PUT
	@Path("/updateMarca/{id}")
	public Response modificarMarca(@PathParam("id") long id, MarcaDto marcaDto) throws NullPointerException {
		DaoMarca daoMarca = new DaoMarca();
		Marca marcaParaModificar = daoMarca.find(id, Marca.class);
		
		marcaParaModificar.set_nombre(marcaDto.getNombre());
		marcaParaModificar.set_descripcion(marcaDto.getDescripcion());
		marcaParaModificar.set_estatus(marcaDto.get_estatus());
		
		try {
			daoMarca.update(marcaParaModificar);
		} catch (Exception e) {
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
		
		return Response.ok().entity(marcaParaModificar).build();
	}
	
	@DELETE
	@Path("/deleteMarca/{id}")
	public Response eliminarMarca(@PathParam("id") long id) {
		DaoMarca daoMarca = new DaoMarca();
		Marca marcaParaEliminar = daoMarca.find(id, Marca.class);
		
		if (marcaParaEliminar == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		try {
			daoMarca.delete(marcaParaEliminar);
		} catch (Exception e) {
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
		
		return Response.ok().entity(marcaParaEliminar).build();
	}
}
