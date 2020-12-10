package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.NivelEconomicoDto;
import ucab.dsw.entidades.NivelEconomico;
import ucab.dsw.excepciones.PruebaExcepcion;

class NivelEconomicoAPITest {
	
	@Test
	void testListarNivelEconomico() {
		/** Prueba Unitaria para listar registros de Nivel Económico.
		 *
		 * Verifica si la cantidad de registros devueltos del método listar,
		 * en la API de Nivel Económico, es mayor a uno (1); es decir, que
		 * devuelve los registros.
		 *
		 * ATENCIÓN: debe haber, al menos, un (1) registro en la BD, de
		 * Nivel Académico, para que funcione.
		 * */
		
		NivelEconomicoAPI servicio = new NivelEconomicoAPI();
		
		try {
			Assertions.assertTrue(servicio.listarNivelEconomico().size() > 0);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testConsultarNivelEconomico() {
		/** Prueba Unitaria para consultar un registro de Nivel Económico,
		 * en específico.
		 *
		 * Verifica si el ID del registro deseado (1), corresponde al valor 1.
		 * */
		
		NivelEconomicoAPI servicio = new NivelEconomicoAPI();
		
		try {
			Assertions.assertEquals(1,
				servicio.consultarNivelEconomico(1L).get_id());
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testAddNivelEconomico() {
		/** Prueba unitaria para agregar un nuevo registro de Nivel Económico
		 * dentro de la BD.
		 *
		 * Crea un DTO de Nivel Económico y asigna sus respectivos valores.
		 * Luego, llama al método encargado de insertar el registro.
		 * Finalmente, verifica si el ID que tendría el nuevo registro es
		 * igual a la cantidad de registros que hay en la tabla Nivel Económico,
		 * luego de la inserción.
		 * */
		
		NivelEconomicoAPI servicio = new NivelEconomicoAPI();
		NivelEconomicoDto neDto = new NivelEconomicoDto();
		
		// Definición del nuevo registro de Nivel Académico.
		neDto.setDescripcion("Distinctio cum consectetur eum mollitia sint voluptatem reiciendis.");
		neDto.set_estatus("Activo");
		
		try {
			// ID del registro a insertar:
			int id = servicio.listarNivelEconomico().size() + 1;
			
			servicio.addNivelEconomico(neDto);
			
			// Cantidad de registros de Nivel Académico, en la BD:
			int cantRegistros = servicio.listarNivelEconomico().size();
			
			Assertions.assertEquals(id, cantRegistros);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	private NivelEconomicoDto getNivelEconomicoDto(NivelEconomico ne) throws PruebaExcepcion {
		/** Método que retorna un DTO de Nivel Económico, a partir del objeto de tipo
		 * NivelEconómico pasado por parámetros.
		 *
		 * Su objetivo es crear un DTO con las mismas propiedades del objeto NivelEconómico,
		 * para que así, en el método (la prueba) en el que es llamado, solo asigne los cambios
		 * a realizar.
		 *
		 * @param ne Registro de Nivel Económico que se desea modificar.
		 * */
		
		NivelEconomicoDto neDto = new NivelEconomicoDto();
		
		neDto.setId(ne.get_id());
		neDto.setDescripcion(ne.getDescripcion());
		neDto.set_estatus(ne.get_estatus());
		
		return neDto;
	}
	
	@Test
	void testUpdateNivelEconomico_() {
		/** Prueba unitaria para actualizar SOLAMENTE la descripción de
		 * un registro de Nivel Económico.
		 *
		 * Busca el registro deseado para modificar, llama al método
		 * getNivelEconomicoDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el
		 * registro con la nueva descripción.
		 * */
		
		NivelEconomicoAPI servicio = new NivelEconomicoAPI();
		NivelEconomico nivelEconomico = servicio.consultarNivelEconomico(2L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (nivelEconomico != null) {
				NivelEconomicoDto neDto = getNivelEconomicoDto(nivelEconomico);
				neDto.setDescripcion("Neque ut illum eaque maxime enim quod molestias.");
				
				servicio.updateNivelEconomico(neDto.getId(), neDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateNivelEconomico_Estatus() {
		/** Prueba unitaria para actualizar SOLAMENTE el estatus de
		 * un registro de Nivel Económico.
		 *
		 * Busca el registro deseado para modificar, llama al método
		 * getNivelEconomicoDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el
		 * registro con la nueva descripción.
		 * */
		
		NivelEconomicoAPI servicio = new NivelEconomicoAPI();
		NivelEconomico nivelEconomico = servicio.consultarNivelEconomico(1L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (nivelEconomico != null) {
				NivelEconomicoDto neDto = getNivelEconomicoDto(nivelEconomico);
				neDto.set_estatus("Inactivo");
				
				servicio.updateNivelEconomico(neDto.getId(), neDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateNivelEconomico_Todo() {
		/** Prueba unitaria para actualizar todos los atributos de
		 * un registro de Nivel Económico.
		 *
		 * Busca el registro deseado para modificar, llama al método
		 * getNivelEconomicoDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el
		 * registro con la nueva descripción.
		 * */
		
		NivelEconomicoAPI servicio = new NivelEconomicoAPI();
		NivelEconomico nivelEconomico = servicio.consultarNivelEconomico(2L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (nivelEconomico != null) {
				NivelEconomicoDto neDto = new NivelEconomicoDto();
				
				neDto.setId(nivelEconomico.get_id());
				neDto.setDescripcion("Exercitationem omnis eius.");
				neDto.set_estatus("Inactivo");
				
				servicio.updateNivelEconomico(neDto.getId(), neDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testEliminarNivelEconomico() {
		/** Prueba unitaria para eliminar un registro de Nivel Académico,
		 * de la BD.
		 * */
		
		NivelEconomicoAPI servicio = new NivelEconomicoAPI();
		NivelEconomico nivelEconomico = servicio.consultarNivelEconomico(3L);
		
		try {
			// Solo eliminará un registro que exista en la BD.
			if (nivelEconomico != null) {
				servicio.eliminarNivelEconomico(nivelEconomico.get_id());
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}