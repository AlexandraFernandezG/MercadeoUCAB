import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.RespuestaPreguntaDto;
import ucab.dsw.entidades.RespuestaPregunta;
import ucab.dsw.servicio.RespuestaPreguntaServicio;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class RespuestaPreguntaServicio_Test {

	/**
	 * Prueba unitaria para verificar el funcionamiento del método ListarRespuestaPreguntaPregunta
	 * @author Emanuel Di Cristofaro
	 */
	@Test
	public void pruebaListarRespuestaPreguntaPregunta(){
		
		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		Response respuesta = servicio.listarRespuestas();
		Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
		
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método ConsultarRespuestaPregunta
	 * @author Emanuel Di Cristofaro
	 */
	@Test
	public void pruebaConsultarRespuestaPregunta(){
		
		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		Response respuesta = servicio.encontrarRespuestaPregunta(1);
		Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
		
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método ListarRespuestaPreguntasActivas
	 * @author Emanuel Di Cristofaro
	 */
	@Test
	public void pruebaListarRespuestaPreguntasActivas(){

		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		Response respuesta = servicio.respuestasActivas();
		Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
	}


	/**
	 * Prueba unitaria para verificar el funcionamiento del método InsertarRespuestaPregunta
	 * @author Emanuel Di Cristofaro y Gregg Spinetti
	 */
	@Test
	public void pruebaInsertarRespuestaPregunta() throws Exception{
		
		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		
		try {
			// Pregunta a la que corresponde la serie de respuestas a insertar en la BD.
			long idPreguntaEncuesta = 1;
			PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto(idPreguntaEncuesta);
			
			// Lista que albergará las respuestas a la pregunta seleccionada anteriormente.
			List<RespuestaPreguntaDto> listaRespuestasPreguntaDto = new ArrayList<>();
			
			// Generación de respuestas a insertar en la BD.
			
			// Respuesta 1 a insertar.
			RespuestaPreguntaDto respuestaPreguntaDto1 = new RespuestaPreguntaDto();
			respuestaPreguntaDto1.setNombre("No muy buena, la verdad");
			respuestaPreguntaDto1.setEstatus("Inactivo");
			respuestaPreguntaDto1.setPreguntaEncuestaDto(preguntaEncuestaDto);
			
			listaRespuestasPreguntaDto.add(respuestaPreguntaDto1); // Agregando la respuesta 1 a la lista.
			
			// Respuesta 2 a insertar.
			RespuestaPreguntaDto respuestaPreguntaDto2 = new RespuestaPreguntaDto();
			respuestaPreguntaDto2.setNombre("¡La mejor!");
			respuestaPreguntaDto2.setEstatus("Activo");
			respuestaPreguntaDto2.setPreguntaEncuestaDto(preguntaEncuestaDto);
			
			listaRespuestasPreguntaDto.add(respuestaPreguntaDto2); // Agregando la respuesta 2 a la lista.
			
			// Prueba
			Response respuesta = servicio.addRespuestaPregunta(idPreguntaEncuesta, listaRespuestasPreguntaDto);
			Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
			
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método ModificarRespuestaPregunta
	 * @author Emanuel Di Cristofaro
	 */
	@Test
	public void pruebaModificarRespuestaPregunta(){
		
		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		
		try {
			RespuestaPreguntaDto respuestaPreguntaDto = new RespuestaPreguntaDto();
			respuestaPreguntaDto.setNombre("No muy buena la verdad");
			respuestaPreguntaDto.setEstatus("Activo");
			// Recuerden que deben ver los id de los registros en la BD
			Response respuesta = servicio.modificarRespuestaPregunta(1, respuestaPreguntaDto);
			Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
			
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Prueba unitaria para verificar el funcionamiento del método EliminarRespuestaPregunta
	 * @author Emanuel Di Cristofaro
	 */
	@Test
	public void pruebaEliminarRespuestaPregunta(){
		
		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		
		try {
			//Estar pendiente con el ID en Base de datos
			Response respuesta = servicio.eliminarRespuestaPregunta(1);
			Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
			
		}catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}
