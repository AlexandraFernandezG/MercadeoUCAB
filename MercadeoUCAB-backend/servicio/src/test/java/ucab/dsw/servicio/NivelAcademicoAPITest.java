package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.NivelAcademicoDto;
import ucab.dsw.entidades.NivelAcademico;
import ucab.dsw.excepciones.PruebaExcepcion;

class NivelAcademicoAPITest {
	
	@Test
	void testListarNivelAcademico() {
		/** Prueba Unitaria para listar registros de Nivel Académicos.
		 *
		 * Verifica si la cantidad de registros devueltos del método listar,
		 * en la API de Nivel Académico, es mayor a uno (1); es decir, que
		 * devuelve los registros.
		 *
		 * ATENCIÓN: debe haber, al menos, un (1) registro en la BD, de
		 * Nivel Académico, para que funcione.
		 * */
		
		NivelAcademicoAPI servicio = new NivelAcademicoAPI();
		
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
		
		NivelAcademicoAPI servicio = new NivelAcademicoAPI();
		
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
		 * Finalmente, verifica si el ID que tendría el nuevo registro es
		 * igual a la cantidad de registros que hay en la tabla Nivel Académico,
		 * luego de la inserción.
		 * */
		
		NivelAcademicoAPI servicio = new NivelAcademicoAPI();
		NivelAcademicoDto naDto = new NivelAcademicoDto();
		
		// Definición del nuevo registro de Nivel Académico.
		naDto.set_descripcion("Distinctio cum consectetur eum mollitia sint voluptatem reiciendis.");
		naDto.set_estatus("Activo");
		
		try {
			// ID del registro a insertar:
			int id = servicio.listarNivelAcademico().size() + 1;
			
			servicio.addNivelAcademico(naDto);
			
			// Cantidad de registros de Nivel Académico, en la BD:
			int cantRegistros = servicio.listarNivelAcademico().size();
			
			Assertions.assertEquals(id, cantRegistros);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	private NivelAcademicoDto getNivelAcademicoDto(NivelAcademico na) throws PruebaExcepcion {
		/** Método que retorna un DTO de Nivel Académico, a partir del objeto de tipo NivelAcadémico pasado por parámetros.
		 *
		 * Su objetivo es crear un DTO con las mismas propiedades del objeto NivelAcademico,
		 * para que así, en el método (la prueba) en el que es llamado, solo asigne los cambios
		 * a realizar.
		 *
		 * @param na Registro de Nivel Académico que se desea modificar.
		 * */
		
		NivelAcademicoDto naDto = new NivelAcademicoDto();
		
		naDto.setId(na.get_id());
		naDto.set_descripcion(na.getDescripcion());
		naDto.set_estatus(na.get_estatus());
		
		return naDto;
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
		
		NivelAcademicoAPI servicio = new NivelAcademicoAPI();
		NivelAcademico nivelAcademico = servicio.consultarNivelAcademico(1L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (nivelAcademico != null) {
				NivelAcademicoDto naDto = getNivelAcademicoDto(nivelAcademico);
				naDto.set_descripcion("Neque ut illum eaque maxime enim quod molestias.");
				
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
		
		NivelAcademicoAPI servicio = new NivelAcademicoAPI();
		NivelAcademico nivelAcademico = servicio.consultarNivelAcademico(2L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (nivelAcademico != null) {
				NivelAcademicoDto naDto = getNivelAcademicoDto(nivelAcademico);
				naDto.set_estatus("Inactivo");
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
		
		NivelAcademicoAPI servicio = new NivelAcademicoAPI();
		NivelAcademico nivelAcademico = servicio.consultarNivelAcademico(1L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (nivelAcademico != null) {
				NivelAcademicoDto naDto = new NivelAcademicoDto();
				
				naDto.setId(nivelAcademico.get_id());
				naDto.set_descripcion("Tempora quis minima delectus.");
				naDto.set_estatus("Inactivo");
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
		
		NivelAcademicoAPI servicio = new NivelAcademicoAPI();
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