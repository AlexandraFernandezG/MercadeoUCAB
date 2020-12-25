import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.servicio.HijoAPI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HijoAPI_Test {
	
	//Listar todos los hijos (Esta forma fue realizada por Valentina)
	@Test
	public void pruebaListarHijos(){
		
		ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();
		
		try {
			Assertions.assertTrue(servicio.listarHijos().size() > 0);
			
		} catch (Exception e) {
			
			Assertions.fail(e.getMessage());
		}
		
	}
	
	//Consultar un hijo (Esta forma fue realizada por Valentina)
	@Test
	public void pruebaConsultarHijo(){
		
		ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();
		Hijo hijo_buscar = servicio.consultarHijo(1);
		
		try {
			Assertions.assertEquals(1, hijo_buscar.get_id());
			
		} catch (Exception e) {
			
			Assertions.fail(e.getMessage());
		}
	}
	
	@Test
	public void testAddHijo() {
		HijoAPI servicio = new HijoAPI();
		
		// Establece el formato de la fecha a insertar.
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			// Busca al padre/madre.
			long idParent = 1;
			//Revisar sus registros en la base de datos.
			InformacionDto informacionDto = new InformacionDto(idParent);
			
			// Datos de Hijo
			String fecha = "2010-11-16";
			Date fechaNacimiento = formatoFecha.parse(fecha);
			
			HijoDto hijoDto = new HijoDto();
			hijoDto.setFechaNacimiento(fechaNacimiento);
			hijoDto.setGenero("masculino");
			hijoDto.setEstatus("Activo");
			hijoDto.setInformacionDto(informacionDto);
			
			Hijo hijoDtoInsertado = servicio.addHijo(hijoDto);
			
			Assertions.assertNotNull(hijoDtoInsertado);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	// Esta prueba permite insertar un hijo
	@Test
	public void pruebaInsertarVariosHijos() throws Exception {
		
		ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();
		//Lista hijo
		List<HijoDto> listaHijosDto = new ArrayList<HijoDto>();
		
		try {
			// Establece el formato de la fecha a insertar.
			DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			
			// Busca al padre/madre.
			//Revisar sus registros en la base de datos
			InformacionDto informacionDto = new InformacionDto(1);
			
			// Datos de Hijo 1
			String fechaH1 = "2013-11-16";
			Date fechaNacimientoH1 = formatoFecha.parse(fechaH1);
			
			HijoDto hijoDto1 = new HijoDto();
			hijoDto1.setFechaNacimiento(fechaNacimientoH1);
			hijoDto1.setGenero("masculino");
			hijoDto1.setEstatus("Activo");
			hijoDto1.setInformacionDto(informacionDto);
			
			// Agrega a Hijo 1 a la lista de hijos.
			listaHijosDto.add(hijoDto1);
			
			// Datos de Hijo 2
			String fechaH2 = "2015-10-15";
			Date fechaNacimientoH2 = formatoFecha.parse(fechaH2);
			
			HijoDto hijoDto2 = new HijoDto();
			hijoDto2.setFechaNacimiento(fechaNacimientoH2);
			hijoDto2.setGenero("femenino");
			hijoDto2.setEstatus("Activo");
			hijoDto2.setInformacionDto(informacionDto);
			
			// Agrega a Hijo 2, a la lista de hijos.
			listaHijosDto.add(hijoDto2);
			
			// Método para insertar los registros anteriores, en la BD.
			List<Hijo> listaHijosInsertados =  servicio.addVariosHijos(listaHijosDto);
			
			// Comprobación
			boolean condicion = listaHijosInsertados.size() > 0 // Esta no se está cumpliendo.
				                    &&
				                    listaHijosDto.size() > 0; // Esta sí se cumple.
			Assertions.assertTrue(condicion);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
		
	}
	
	//Esta prueba nos permite actualizar un hijo
	@Test
	public void pruebaModificarHijo() throws ParseException {
		
		ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();
		
		try {
			HijoDto hijoDto = new HijoDto();
			
			String date1 = "2010-12-01";
			DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = forma.parse(date1);
			hijoDto.setFechaNacimiento(myDate);
			hijoDto.setGenero("masculino");
			hijoDto.setEstatus("Activo");
			//Revisar sus registros en la base de datos
			servicio.updateHijo(1, hijoDto);
			
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
		
	}
	
	// Esta prueba permite eliminar un hijo
	@Test
	public void pruebaEliminarHijo(){
		
		ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();
		
		try {
			//Revisar sus registros en la base de datos
			servicio.deleteHijo(4);
			
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
}
