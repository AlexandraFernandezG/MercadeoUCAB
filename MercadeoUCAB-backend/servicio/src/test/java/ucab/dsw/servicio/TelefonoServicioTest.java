package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Telefono;

class TelefonoServicioTest {
	private final EntidadDto dto = new EntidadDto();
	
	@Test
	void testListarTelefonos() {
		/** Prueba Unitaria para listar registros de Teléfonos.
		 *
		 * Verifica si la cantidad de registros devueltos del método listar,
		 * en la API de Teléfono, es mayor a cero (0); es decir, que
		 * devuelve los registros.
		 *
		 * ATENCIÓN: debe haber, al menos, un (1) registro en la BD, de
		 * Telefono, para que funcione.
		 * */
		
		TelefonoServicio servicio = new TelefonoServicio();
		
		try {
			Assertions.assertTrue(servicio.listarTelefonos().size() > 0);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testConsultarTelefono() {
		/** Prueba Unitaria para consultar un registro de Telefono,
		 * en específico.
		 *
		 * Verifica si el ID del registro deseado (1), corresponde al valor 1.
		 * */
		
		TelefonoServicio servicio = new TelefonoServicio();
		
		try {
			Assertions.assertEquals(1,
				servicio.consultarTelefono(1L).get_id());
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testAddTelefono() {
		/** Prueba unitaria para agregar un nuevo registro de Telefono
		 * dentro de la BD.
		 *
		 * Crea un DTO de Telefono y asigna sus respectivos valores.
		 * Luego, llama al método encargado de insertar el registro.
		 * Finalmente, pasará la prueba si no ocurre ningún error durante
		 * la operación.
		 * */
		
		TelefonoServicio servicio = new TelefonoServicio();
		TelefonoDto telefonoDto = new TelefonoDto();
		
		// Definición del nuevo registro de Teléfono.
		telefonoDto.setNumero(41714);
		telefonoDto.setEstatus("Activo");
		
		try {
			telefonoDto.setInformacion(dto.getInformacionDto(1L));
			servicio.addTelefono(telefonoDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateTelefono_Numero() {
		/** Prueba unitaria para actualizar todos los atributos de
		 * un registro de Telefono.
		 *
		 * Busca el registro deseado para modificar, llama al método
		 * getTelefonoDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el
		 * registro con el nuevo número.
		 * */
		
		TelefonoServicio servicio = new TelefonoServicio();
		Telefono telefono = servicio.consultarTelefono(1L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (telefono != null) {
				TelefonoDto telefonoDto = dto.getTelefonoDto(telefono.get_id());
				
				telefonoDto.setNumero(1379994);
				
				servicio.updateTelefono(telefonoDto.getId(), telefonoDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateTelefono_Estatus() {
		/** Prueba unitaria para actualizar todos los atributos de
		 * un registro de Telefono.
		 *
		 * Busca el registro deseado para modificar, llama al método
		 * getTelefonoDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el
		 * registro con el nuevo estatus.
		 * */
		
		TelefonoServicio servicio = new TelefonoServicio();
		Telefono telefono = servicio.consultarTelefono(2L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (telefono != null) {
				TelefonoDto telefonoDto = dto.getTelefonoDto(telefono.get_id());
				
				telefonoDto.setEstatus("Inactivo");
				
				servicio.updateTelefono(telefonoDto.getId(), telefonoDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateTelefono_Todo() {
		/** Prueba unitaria para actualizar todos los atributos de
		 * un registro de Telefono.
		 *
		 * Busca el registro deseado para modificar y actualiza el
		 * registro con los nuevos datos.
		 * */
		
		TelefonoServicio servicio = new TelefonoServicio();
		Telefono telefono = servicio.consultarTelefono(4L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (telefono != null) {
				TelefonoDto telefonoDto = dto.getTelefonoDto(telefono.get_id());
				
				telefonoDto.setNumero(1784483);
				telefonoDto.setEstatus("Inactivo");
				
				servicio.updateTelefono(telefonoDto.getId(), telefonoDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testEliminarTelefono() {
		/** Prueba unitaria para eliminar un registro de Telefono,
		 *  de la BD.
		 * */
		
		TelefonoServicio servicio = new TelefonoServicio();
		Telefono telefono = servicio.consultarTelefono(5L);
		
		try {
			// Solo eliminará un registro que exista en la BD.
			if (telefono != null) {
				servicio.eliminarTelefono(telefono.get_id());
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}