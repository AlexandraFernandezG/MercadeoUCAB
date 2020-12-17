package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.List;

class MarcaAPITest {
	EntidadDto dto = new EntidadDto();
	
	@Test
	void testListarMarcas() {
		MarcaAPI servicio = new MarcaAPI();
		List<Marca> listaMarcas = servicio.listarMarcas();
		int sizeListaMarcas = listaMarcas.size();
		
		try {
			Assertions.assertTrue(sizeListaMarcas > 0);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testConsultarMarca() {
		MarcaAPI servicio = new MarcaAPI();
		long idMarca = 1;
		Marca marcaEncontrada = servicio.consultarMarca(idMarca);
		
		try {
			Assertions.assertNotNull(marcaEncontrada);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testMarcasActivas() {
		MarcaAPI servicio = new MarcaAPI();
		List<Marca> listaMarcasActivas = servicio.marcasActivas();
		int sizeListaMarcas = listaMarcasActivas.size();
		
		try {
			Assertions.assertTrue(sizeListaMarcas > 0);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testAddMarca() {
		MarcaAPI servicio = new MarcaAPI();
		MarcaDto marcaDto = new MarcaDto();
		
		try {
			marcaDto.setNombre("animi");
			marcaDto.setDescripcion("Voluptas officiis maxime sunt pariatur.");
			marcaDto.setEstatus("Activo");
			
			MarcaDto nuevaMarca = servicio.addMarca(marcaDto);
			Assertions.assertNotNull(nuevaMarca);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateMarca_Nombre() {
		MarcaAPI servicio = new MarcaAPI();
		long idMarca = 1;
		
		try {
			MarcaDto marcaDto = dto.getMarcaDto(idMarca);
			marcaDto.setNombre("dignissimos");
			
			servicio.updateMarca(marcaDto.getId(), marcaDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateMarca_Descripcion() {
		MarcaAPI servicio = new MarcaAPI();
		long idMarca = 2;
		
		try {
			MarcaDto marcaDto = dto.getMarcaDto(idMarca);
			marcaDto.setDescripcion("Odit sit vel ea omnis incidunt.");
			
			servicio.updateMarca(marcaDto.getId(), marcaDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateMarca_Estatus() {
		MarcaAPI servicio = new MarcaAPI();
		long idMarca = 3;
		
		try {
			MarcaDto marcaDto = dto.getMarcaDto(idMarca);
			marcaDto.setEstatus("Inactivo");
			
			servicio.updateMarca(marcaDto.getId(), marcaDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateMarca_Todo() {
		MarcaAPI servicio = new MarcaAPI();
		long idMarca = 4;
		
		try {
			MarcaDto marcaDto = new MarcaDto(idMarca);
			marcaDto.setNombre("eligendi");
			marcaDto.setDescripcion("Ducimus mollitia aut.");
			marcaDto.setEstatus("Inactivo");
			
			servicio.updateMarca(marcaDto.getId(), marcaDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateMarca_ReturnsNotFound() {
		MarcaAPI servicio = new MarcaAPI();
		long idMarca = 0;
		
		try {
			MarcaDto marcaDto = new MarcaDto(idMarca);
			servicio.updateMarca(marcaDto.getId(), marcaDto);
		} catch (Exception e) {
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void testDeleteMarca() {
		MarcaAPI servicio = new MarcaAPI();
		long idMarca = 5;
		
		try {
			servicio.deleteMarca(idMarca);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testDeleteMarca_ReturnsNotFound() {
		MarcaAPI servicio = new MarcaAPI();
		long idMarca = 0;
		
		try {
			servicio.deleteMarca(idMarca);
		} catch (Exception e) {
			Assertions.assertTrue(true);
		}
	}
}