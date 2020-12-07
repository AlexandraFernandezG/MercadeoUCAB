package ucab.dsw.servicio;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.sql.Statement;

class MarcaAPITest {
	@Test
	void testListarMarcas() {
		MarcaAPI listar = new MarcaAPI();
		
		try {
			Assertions.assertNotNull(listar.listarMarcas());
		} catch (Exception e) {
			Assertions.fail(e.getMessage());
		}
	}
	
	@Test
	void testConsultarMarcas() {
		MarcaAPI encontrar = new MarcaAPI();
		Marca foundedMarca;
		
		foundedMarca = encontrar.consultarMarca(1);
		
		try {
			Assertions.assertEquals(1, foundedMarca.get_id());
		} catch (Exception e) {
			Assertions.fail(e.getMessage());
		}
	}
	
/*	@Test
	void testAgregarMarca() throws PruebaExcepcion {
		MarcaDto marcaDto = new MarcaDto();
		ucab.dsw.servicio.MarcaAPI marcaAPI = new ucab.dsw.servicio.MarcaAPI();
		
//		marcaDto.setId();
		marcaDto.setNombre("Kub - Koss");
		marcaDto.setDescripcion("Sunt nesciunt ratione vel doloremque placeat.");
		marcaDto.set_estatus("Activo");
		
		try {
			Marca obtainedMarca = marcaAPI.agregarMarca(marcaDto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}*/
}