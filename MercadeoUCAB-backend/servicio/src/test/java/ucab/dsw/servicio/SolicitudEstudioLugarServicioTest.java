package ucab.dsw.servicio;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.SolicitudEstudioLugarDto;

import javax.ws.rs.core.Response;

class SolicitudEstudioLugarServicioTest {

	private final EntidadDto dto = new EntidadDto();

	/**
	 * Prueba unitaria para verificar el funcionamiento del método ListarLugaresSolicitudEstudio
	 * @author Valentina Figueroa y Emanuel Di Cristofaro
	 */
	@Test
	void testListarLugaresSolicitudEstudio() {
		
		SolicitudEstudioLugarServicio servicio = new SolicitudEstudioLugarServicio();
		Response respuesta = servicio.listarLugaresEstudio(1);
		Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método addSolicitudEstudioLugar
	 * @author Valentina Figueroa y Emanuel Di Cristofaro
	 */
	@Test
	void testAddSolicitudEstudioLugar() {

		
		SolicitudEstudioLugarServicio servicio = new SolicitudEstudioLugarServicio();
		SolicitudEstudioLugarDto elDto = new SolicitudEstudioLugarDto();
		
		// Definición del nuevo registro de Estudio_Lugar.
		elDto.setEstatus("Activo");
		
		try {
			elDto.setLugarDto(dto.getLugarDtoMunicipio(3));
			elDto.setSolicitudestudioDto(dto.getSolicitudEstudioDto(1));
			Response respuesta = servicio.addEstudioLugar(elDto);
			Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());


		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método UpdateEstatusSolicitudEstudioLugar
	 * @author Valentina Figueroa y Emanuel Di Cristofaro
	 */
	@Test
	void testUpdateEstatusSolicitudEstudioLugar() {
		
		SolicitudEstudioLugarServicio servicio = new SolicitudEstudioLugarServicio();
		
		try {
			SolicitudEstudioLugarDto solicitudEstudioLugarDto = dto.getEstudioLugarDto(1);
			
			solicitudEstudioLugarDto.setEstatus("Inactivo");
			
			Response respuesta = servicio.updateEstatusEstudioLugar(solicitudEstudioLugarDto.getId(), solicitudEstudioLugarDto);
			Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método DeleteSolicitudEstudioLugar
	 * @author Valentina Figueroa y Emanuel Di Cristofaro
	 */
	@Test
	void testDeleteSolicitudEstudioLugar() {

		
		SolicitudEstudioLugarServicio servicio = new SolicitudEstudioLugarServicio();
		
		try {

			Response respuesta = servicio.deleteEstudiaLugar(2);
			Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}