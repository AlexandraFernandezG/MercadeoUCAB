package ucab.dsw.servicio;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Telefono;

import javax.ws.rs.core.Response;

class TelefonoServicioTest {

	private final EntidadDto dto = new EntidadDto();

	/**
	 * Prueba unitaria para verificar el funcionamiento del método ListarTelefonos
	 * @author Valentina Figueroa y Emanuel Di Cristofaro
	 */
	@Test
	void testListarTelefonos() {
		
		TelefonoServicio servicio = new TelefonoServicio();
		Response respuesta = servicio.listarTelefonos();
		Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método ConsultarTelefono
	 * @author Valentina Figueroa y Emanuel Di Cristofaro
	 */
	@Test
	void testConsultarTelefono() {

		
		TelefonoServicio servicio = new TelefonoServicio();
		Response respuesta = servicio.consultarTelefono(1);
		Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método AddTelefono
	 * @author Valentina Figueroa y Emanuel Di Cristofaro
	 */
	@Test
	void testAddTelefono() {
		
		TelefonoServicio servicio = new TelefonoServicio();
		TelefonoDto telefonoDto = new TelefonoDto();
		
		// Definición del nuevo registro de Teléfono.
		telefonoDto.setNumero(41714);
		telefonoDto.setEstatus("Activo");
		
		try {

			telefonoDto.setInformacion(dto.getInformacionDto(1));
			Response respuesta = servicio.addTelefono(telefonoDto);
			Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

		} catch (Exception e) {

			Assertions.fail(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método UpdateTelefono
	 * @author Valentina Figueroa y Emanuel Di Cristofaro
	 */
	@Test
	void testUpdateTelefono() {

		TelefonoServicio servicio = new TelefonoServicio();
		
		try {
			// Solo actualizará un registro que exista en la BD.

				TelefonoDto telefonoDto = new TelefonoDto();

				telefonoDto.setNumero(1379994);
				telefonoDto.setEstatus("Activo");
				InformacionDto informacionDto = new InformacionDto(1);
				telefonoDto.setInformacion(informacionDto);

				Response respuesta = servicio.updateTelefono(1, telefonoDto);
				Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método EliminarTelefono
	 * @author Valentina Figueroa y Emanuel Di Cristofaro
	 */
	@Test
	void testEliminarTelefono() {
		
		TelefonoServicio servicio = new TelefonoServicio();
		
		try {
			// Solo eliminará un registro que exista en la BD.

			Response respuesta = servicio.eliminarTelefono(1);
			Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}