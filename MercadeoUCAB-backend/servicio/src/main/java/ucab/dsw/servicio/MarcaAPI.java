package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**@author valen
 * */

@Stateless
@Path("/marca")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class MarcaAPI extends AplicacionBase{
	
//	private DaoMarca daoMarca = new DaoMarca();
	
	@GET
	@Path("/all")
	public List<Marca> listarMarcas() {
		/* Obtiene TODAS las marcas existentes en la base de datos
		* y las guarda en una lista.
		* */
		DaoMarca daoMarca = new DaoMarca();
		return daoMarca.findAll(Marca.class);
	}
	
	@GET
	@Path("/consultar/{id}")
	public Marca consultarMarca(@PathParam("id") long id) {
		DaoMarca daoMarca = new DaoMarca();
		return daoMarca.find(id, Marca.class);
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
}
