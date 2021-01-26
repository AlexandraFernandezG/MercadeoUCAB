package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.MedioComunicacionDto;
import ucab.dsw.entidades.MedioComunicacion;

import javax.ws.rs.core.Response;

class MedioComunicacionServicioTest {
	private final EntidadDto dto = new EntidadDto();
	@Test
	void testListarMedioComunicacion() {
		/** Prueba Unitaria para listar registros de Medio de Comunicación.
		 *
		 * Verifica si la cantidad de registros devueltos del método listar,
		 * en la API de MedioComunicacion, es mayor a cero (0); es decir, que
		 * devuelve los registros.
		 *
		 * ATENCIÓN: debe haber, al menos, un (1) registro en la BD, de
		 * Telefono, para que funcione.
		 * */
		
		MedioComunicacionServicio servicio = new MedioComunicacionServicio();
		
		try {
			Assertions.assertNotNull(servicio.listarMedioComunicacion());
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testConsultarMedioComunicacion() {
		/** Prueba Unitaria para consultar un registro de Medio_Comunicacion,
		 * en específico.
		 *
		 * Verifica si el ID del registro deseado (1), corresponde al valor 1.
		 * */
		
		MedioComunicacionServicio servicio = new MedioComunicacionServicio();
		
		try {
			Assertions.assertNotNull(servicio.consultarMedioComunicacion(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testAddMedioComunicacion() {
		/** Prueba unitaria para agregar un nuevo registro de Medio de Comunicación
		 * dentro de la BD.
		 *
		 * Crea un DTO de Medio de Comunicación y asigna sus respectivos valores.
		 * Luego, llama al método encargado de insertar el registro.
		 * Finalmente, pasará la prueba si no ocurre ningún error durante
		 * la operación.
		 * */
		
		MedioComunicacionServicio servicio = new MedioComunicacionServicio();
		MedioComunicacionDto mcDto = new MedioComunicacionDto();
		
		// Definición del nuevo registro de Medio_Comunicacion.
		mcDto.setEstatus("Activo");
		mcDto.setTipoDeMedio("numquam");
		
		try {
			mcDto.setInformacionDto(dto.getInformacionDto(1L));
			mcDto.setSolicitudEstudioDto(dto.getSolicitudEstudioDto(1L));
			servicio.addMedioComunicacion(mcDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateMedioComunicacion_TipoDeMedio() {
		/** Prueba unitaria para actualizar todos los atributos de
		 * un registro de Medio_Comunicacion.
		 *
		 * Busca el registro deseado para modificar, llama al método
		 * getMedioComunicacionDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el
		 * registro con el nuevo tipo de medio.
		 * */
		
		MedioComunicacionServicio servicio = new MedioComunicacionServicio();
		Response mc = servicio.consultarMedioComunicacion(1L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (mc != null) {
				MedioComunicacionDto mcDto = dto.getMedioComunicacionDto(1);
				
				mcDto.setTipoDeMedio("qui");
				
				servicio.updateMedioComunicacion(mcDto.getId(), mcDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateMedioComunicacion_Estatus() {
		/** Prueba unitaria para actualizar todos los atributos de
		 * un registro de Medio_Comunicacion.
		 *
		 * Busca el registro deseado para modificar, llama al método
		 * getMedioComunicacionDto para generar el DTO que será pasado
		 * por parámetros al método de actualización, y actualiza el
		 * registro con el nuevo estatus.
		 * */
		
		MedioComunicacionServicio servicio = new MedioComunicacionServicio();
		Response mc = servicio.consultarMedioComunicacion(2L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (mc != null) {
				MedioComunicacionDto mcDto = dto.getMedioComunicacionDto(2);
				
				mcDto.setEstatus("Inactivo");
				
				servicio.updateMedioComunicacion(mcDto.getId(), mcDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateMedioComunicacion_Todo() {
		/** Prueba unitaria para actualizar todos los atributos de
		 * un registro de Medio_Comunicacion.
		 *
		 * Busca el registro deseado para modificar y actualiza el
		 * registro con los nuevos datos.
		 * */
		
		MedioComunicacionServicio servicio = new MedioComunicacionServicio();
		Response mc = servicio.consultarMedioComunicacion(3L);
		
		try {
			// Solo actualizará un registro que exista en la BD.
			if (mc != null) {
				MedioComunicacionDto mcDto = dto.getMedioComunicacionDto(3);
				
				mcDto.setTipoDeMedio("quia");
				mcDto.setEstatus("Inactivo");
				
				servicio.updateMedioComunicacion(mcDto.getId(), mcDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testEliminarMedioComunicacion() {
		/** Prueba unitaria para eliminar un registro de Telefono,
		 *  de la BD.
		 * */
		
		MedioComunicacionServicio servicio = new MedioComunicacionServicio();
		Response mc = servicio.consultarMedioComunicacion(4L);
		
		try {
			// Solo eliminará un registro que exista en la BD.
			if (mc != null) {
				servicio.eliminarMedioComunicacion(4);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}