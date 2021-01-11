import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.RespuestaPreguntaDto;
import ucab.dsw.entidades.RespuestaPregunta;
import ucab.dsw.servicio.RespuestaPreguntaServicio;

import java.util.ArrayList;
import java.util.List;

public class RespuestaPreguntaServicio_Test {
	
	//Listar todos los RespuestaPregunta
	@Test
	public void pruebaListarRespuestaPreguntaPregunta(){
		
		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		
		try {
			Assertions.assertTrue(servicio.listarRespuestas().size() > 0);
			
		} catch (Exception e) {
			
			Assertions.fail(e.getMessage());
		}
		
	}
	
	//Consultar una Respuesta
	@Test
	public void pruebaConsultarRespuestaPregunta(){
		
		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		RespuestaPregunta respuestaPregunta_buscar = servicio.encontrarRespuestaPregunta(1);
		
		try {
			
			Assertions.assertEquals(1, respuestaPregunta_buscar.get_id());
			
		} catch (Exception e) {
			
			Assertions.fail(e.getMessage());
		}
		
	}
	
	//Listar Respuestas activas
	@Test
	public void pruebaListarRespuestaPreguntasActivas(){
		
		try {
			Assertions.assertNotNull(new RespuestaPreguntaServicio().respuestasActivas());
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	
	// Esta prueba permite insertar una Respuesta a la BD
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
			RespuestaPreguntaDto resultado = servicio.addRespuestaPregunta(idPreguntaEncuesta, listaRespuestasPreguntaDto);
			long idError = 0; // Valor al que no debe ser igual el ID del resultado obtenido, ya que significaría error.
			Assert.assertNotEquals(resultado.getId(), idError);
			
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	// Esta prueba permite modificar una Respuesta
	@Test
	public void pruebaModificarRespuestaPregunta(){
		
		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		
		try {
			RespuestaPreguntaDto respuestaPreguntaDto = new RespuestaPreguntaDto();
			respuestaPreguntaDto.setNombre("No muy buena la verdad");
			respuestaPreguntaDto.setEstatus("Activo");
			// Recuerden que deben ver los id de los registros en la BD
			servicio.modificarRespuestaPregunta(1, respuestaPreguntaDto);
			
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	// Esta prueba permite eliminar una Respuesta
	@Test
	public void pruebaEliminarRespuestaPregunta(){
		
		RespuestaPreguntaServicio servicio = new RespuestaPreguntaServicio();
		
		try {
			//Estar pendiente con el ID en Base de datos
			servicio.eliminarRespuestaPregunta(1);
			
		}catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}
