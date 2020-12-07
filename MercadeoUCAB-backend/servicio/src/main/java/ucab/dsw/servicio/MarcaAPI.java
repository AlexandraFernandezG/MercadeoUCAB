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
		return daoMarca.find(id, Marca.class);
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
	
/*	@POST
	@Path("/add")
	public Marca agregarMarca(MarcaDto marcaDto) throws PruebaExcepcion {
		try {
			DaoMarca daoMarca = new DaoMarca();
			Marca marca = new Marca(marcaDto.getId());
//			marca.se
			marca.set_nombre(marcaDto.getNombre());
			marca.set_descripcion(marcaDto.getDescripcion());
			marca.set_estatus(marcaDto.get_estatus());
			System.out.println("ID: " + marca.get_id());
			daoMarca.insert(marca);
			return marca;
//			return null;
		} catch (Exception e) {
//			String error = e.getMessage();
			throw e;
		}
	}*/
	
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
