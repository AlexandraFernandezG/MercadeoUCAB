package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.NivelAcademicoDto;
import ucab.dsw.entidades.NivelAcademico;

class NivelAcademicoServicioTest {
	private final EntidadDto dto = new EntidadDto();
	
	@Test
	void testListarNivelAcademico() {
		/** Prueba Unitaria para listar registros de Nivel Académicos.
		 *
		 * Verifica si la cantidad de registros devueltos del método listar,
		 * en la API de Nivel Académico, es mayor a cero (0); es decir, que
		 * devuelve los registros.
		 *
		 * ATENCIÓN: debe haber, al menos, un (1) registro en la BD, de
		 * Nivel Académico, para que funcione.
		 * */
		
		NivelAcademicoServicio servicio = new NivelAcademicoServicio();
		
		try {
			Assertions.assertTrue(servicio.listarNivelAcademico().size() > 0);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testConsultarNivelAcademico() {
		/** Prueba Unitaria para consultar un registro de Nivel Académico,
		 * en específico.
		 *
		 * Verifica si el ID del registro deseado (1), corresponde al valor 1.
		 * */
		
		NivelAcademicoServicio servicio = new NivelAcademicoServicio();
		
		try {
			Assertions.assertEquals(1,
				servicio.consultarNivelAcademico(1L).get_id());
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testAddNivelAcademico() {
		/** Prueba unitaria para agregar un nuevo registro de Nivel Académico
		 * dentro de la BD.
		 *
		 * Crea un DTO de Nivel Académico y asigna sus respectivos valores.
		 * Luego, llama al método encargado de insertar el registro.
		 * Finalmente, pasará la prueba si no ocurre ningún error durante
		 * la operación.
		 * */
		
		NivelAcademicoServicio servicio = new NivelAcademicoServicio();
		NivelAcademicoDto naDto = new NivelAcademicoDto();
		
		// Definición del nuevo registro de Nivel Académico.
		naDto.setDescripcion("Distinctio cum consectetur eum mollitia sint voluptatem reiciendis.");
		naDto.setEstatus("Activo");
		
		try {
			servicio.addNivelAcademico(naDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateNivelAcademico_Descripcion() {
		/**Prueba unitaria para actualizar SOLAMENTE la descripción de un registro de Nivel Académico.
		 *
		 * Busca el registro deseado para modificar,
		 * llama al método getNivelAcademicoDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el registro con
		 * la nueva descripción.
		 * */
		
		NivelAcademicoServicio servicio = new NivelAcademicoServicio();
		try {
			NivelAcademicoDto naDto = dto.getNivelAcademicoDto(1L);
			
			// Solo actualizará un registro que exista en la BD.
			if (naDto != null) {
				naDto.setDescripcion("Neque ut illum eaque maxime enim quod molestias.");
				
				servicio.updateNivelAcademico(naDto.getId(), naDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateNivelAcademico_Estatus() {
		/**Prueba unitaria para actualizar SOLAMENTE el estatus de un registro de Nivel Académico.
		 *
		 * Busca el registro deseado para modificar,
		 * llama al método getNivelAcademicoDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el registro con
		 * el nuevo estatus.
		 * */
		
		NivelAcademicoServicio servicio = new NivelAcademicoServicio();
		
		try {
			NivelAcademicoDto naDto = dto.getNivelAcademicoDto(2L);
			// Solo actualizará un registro que exista en la BD.
			if (naDto != null) {
				naDto.setEstatus("Inactivo");
				servicio.updateNivelAcademico(naDto.getId(), naDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateNivelAcademico_Todo() {
		/**Prueba unitaria para actualizar todos los atributos de un registro de Nivel Académico.
		 *
		 * Busca el registro deseado para modificar,
		 * llama al método getNivelAcademicoDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el registro con
		 * los nuevos valores.
		 * */
		
		NivelAcademicoServicio servicio = new NivelAcademicoServicio();
		NivelAcademico nivelAcademico = servicio.consultarNivelAcademico(3L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (nivelAcademico != null) {
				NivelAcademicoDto naDto = new NivelAcademicoDto();
				
				naDto.setId(nivelAcademico.get_id());
				naDto.setDescripcion("Tempora quis minima delectus.");
				naDto.setEstatus("Inactivo");
				servicio.updateNivelAcademico(naDto.getId(), naDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testEliminarNivelAcademico() {
		/** Prueba unitaria para eliminar un registro de Nivel Académico,
		 *  de la BD.
		 * */
		
		NivelAcademicoServicio servicio = new NivelAcademicoServicio();
		NivelAcademico nivelAcademico = servicio.consultarNivelAcademico(3L);
		
		try {
			// Solo eliminará un registro que exista en la BD.
			if (nivelAcademico != null) {
				servicio.eliminarNivelAcademico(nivelAcademico.get_id());
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}