package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.accesodatos.DaoEstudioLugar;
import ucab.dsw.dtos.EstudioLugarDto;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.EstudioLugar;
import ucab.dsw.entidades.Telefono;

import java.util.List;

class EstudioLugarAPITest {
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
		
		EstudioLugarAPI servicio = new EstudioLugarAPI();
		
		try {
			Assertions.assertTrue(servicio.listarLugaresEstudio(1).size() > 0);
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
		
		EstudioLugarAPI servicio = new EstudioLugarAPI();
		EstudioLugarDto elDto = new EstudioLugarDto();
		
		// Definición del nuevo registro de Estudio_Lugar.
		elDto.setEstatus("Activo");
		
		try {
			LugarDto lugarDto = new LugarDto(3);
			elDto.setLugarDto(lugarDto);
			
			elDto.setEstudioDto(dto.getEstudioDto(1));
			
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
		
		EstudioLugarAPI servicio = new EstudioLugarAPI();
		
		try {
			EstudioLugarDto estudioLugarDto = dto.getEstudioLugarDto(1);
			
			estudioLugarDto.setEstatus("Inactivo");
			
			servicio.updateEstatusEstudioLugar(estudioLugarDto.getId(), estudioLugarDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testDeleteEstudiaLugar() {
		/** Prueba unitaria para eliminar un registro de Estudio_Lugar,
		 *  de la BD.
		 * */
		
		EstudioLugarAPI servicio = new EstudioLugarAPI();
		
		try {
			servicio.deleteEstudiaLugar(2);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}