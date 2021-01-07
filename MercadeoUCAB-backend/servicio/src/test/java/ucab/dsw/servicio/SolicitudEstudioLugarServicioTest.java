package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.SolicitudEstudioLugarDto;

class SolicitudEstudioLugarServicioTest {
	private final EntidadDto dto = new EntidadDto();
	
	@Test
	void testListarLugaresEstudio() {
		/** Prueba Unitaria para listar registros de Estudio_Lugar,
		 * para el Estudio con ID = 1.
		 *
		 * Verifica si la cantidad de registros devueltos del método listar,
		 * en la API de Estudio_Lugar, es mayor a cero (0); es decir, que
		 * devuelve los registros.
		 *
		 * ATENCIÓN: debe haber, al menos, un (1) registro en la BD, de
		 * Estudio_Lugar, para que funcione.
		 * */
		
		SolicitudEstudioLugarServicio servicio = new SolicitudEstudioLugarServicio();
		
		try {
			Assertions.assertTrue(servicio.listarLugaresEstudio(1L).size() > 0);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testAddEstudioLugar() {
		/** Prueba unitaria para agregar un nuevo registro de Estudio_Lugar
		 * dentro de la BD.
		 *
		 * Crea un DTO de Estudio_Lugar y asigna sus respectivos valores.
		 * Luego, llama al método encargado de insertar el registro.
		 * Finalmente, pasará la prueba si no ocurre ningún error durante
		 * la operación.
		 * */
		
		SolicitudEstudioLugarServicio servicio = new SolicitudEstudioLugarServicio();
		SolicitudEstudioLugarDto elDto = new SolicitudEstudioLugarDto();
		
		// Definición del nuevo registro de Estudio_Lugar.
		elDto.setEstatus("Activo");
		
		try {
			elDto.setLugarDto(dto.getLugarDtoMunicipio(3L));
			elDto.setSolicitudestudioDto(dto.getSolicitudEstudioDto(1L));
			servicio.addEstudioLugar(elDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateEstatusEstudioLugar() {
		/** Prueba unitaria para actualizar todos los atributos de
		 * un registro de Estudio_Lugar.
		 *
		 * Busca el registro deseado para modificar y actualiza el
		 * registro con los nuevos datos.
		 * */
		
		SolicitudEstudioLugarServicio servicio = new SolicitudEstudioLugarServicio();
		
		try {
			SolicitudEstudioLugarDto solicitudEstudioLugarDto = dto.getEstudioLugarDto(1L);
			
			solicitudEstudioLugarDto.setEstatus("Inactivo");
			
			servicio.updateEstatusEstudioLugar(solicitudEstudioLugarDto.getId(), solicitudEstudioLugarDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testDeleteEstudiaLugar() {
		/** Prueba unitaria para eliminar un registro de Estudio_Lugar,
		 *  de la BD.
		 * */
		
		SolicitudEstudioLugarServicio servicio = new SolicitudEstudioLugarServicio();
		
		try {
			servicio.deleteEstudiaLugar(2L);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}