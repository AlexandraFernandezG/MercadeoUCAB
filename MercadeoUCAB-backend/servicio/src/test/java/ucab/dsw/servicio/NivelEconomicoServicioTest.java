package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.NivelEconomicoDto;
import ucab.dsw.entidades.NivelEconomico;

class NivelEconomicoServicioTest {
	private final EntidadDto dto = new EntidadDto();
	
	@Test
	void testListarNivelEconomico() {
		/** Prueba Unitaria para listar registros de Nivel Económico.
		 *
		 * Verifica si la cantidad de registros devueltos del método listar,
		 * en la API de Nivel Económico, es mayor a cero (0); es decir, que
		 * devuelve los registros.
		 *
		 * ATENCIÓN: debe haber, al menos, un (1) registro en la BD, de
		 * Nivel Académico, para que funcione.
		 * */
		
		NivelEconomicoServicio servicio = new NivelEconomicoServicio();
		
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
		
		NivelEconomicoServicio servicio = new NivelEconomicoServicio();
		
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
		 * Finalmente, pasará la prueba si no ocurre ningún error durante
		 * la operación.
		 * */
		
		NivelEconomicoServicio servicio = new NivelEconomicoServicio();
		NivelEconomicoDto neDto = new NivelEconomicoDto();
		
		// Definición del nuevo registro de Nivel Académico.
		neDto.setDescripcion("Distinctio cum consectetur eum mollitia sint voluptatem reiciendis.");
		neDto.setEstatus("Activo");
		
		try {
			servicio.addNivelEconomico(neDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateNivelEconomico_Descripcion() {
		/** Prueba unitaria para actualizar SOLAMENTE la descripción de
		 * un registro de Nivel Económico.
		 *
		 * Busca el registro deseado para modificar, llama al método
		 * getNivelEconomicoDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el
		 * registro con la nueva descripción.
		 * */
		
		NivelEconomicoServicio servicio = new NivelEconomicoServicio();
		
		try {
			NivelEconomicoDto neDto = dto.getNivelEconomicoDto(2L);
			
			// Solo actualizará un registro que exista en la BD.
			if (neDto != null) {
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
		 * registro con el nuevo estatus.
		 * */
		
		NivelEconomicoServicio servicio = new NivelEconomicoServicio();
		
		try {
			NivelEconomicoDto neDto = dto.getNivelEconomicoDto(1L);
			
			// Solo actualizará un registro que exista en la BD.
			if (neDto != null) {
				neDto.setEstatus("Inactivo");
				
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
		 * Busca el registro deseado para modificar, lo actualiza
		 * con los nuevos datos.
		 * */
		
		NivelEconomicoServicio servicio = new NivelEconomicoServicio();
		NivelEconomico nivelEconomico = servicio.consultarNivelEconomico(3L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (nivelEconomico != null) {
				NivelEconomicoDto neDto = new NivelEconomicoDto();
				
				neDto.setId(nivelEconomico.get_id());
				neDto.setDescripcion("Exercitationem omnis eius.");
				neDto.setEstatus("Inactivo");
				
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
		
		NivelEconomicoServicio servicio = new NivelEconomicoServicio();
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