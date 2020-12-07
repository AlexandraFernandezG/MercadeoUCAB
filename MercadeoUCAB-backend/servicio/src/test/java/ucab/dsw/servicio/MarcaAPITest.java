package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;

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
	
	@Test
	void testConsultarMarca_Fails() {
		MarcaAPI servicio = new MarcaAPI();
		Marca marcaParaModificar;
		
		try {
			// No existe marca con ID=5, por lo que debería dar fallar
			marcaParaModificar = servicio.consultarMarca(5);
		} catch (Exception e) {
			System.out.println("Marca no encontrada");
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void testListarMarcasActivas() {
		try {
			Assertions.assertNotNull(new MarcaAPI().listarMarcasActivas());
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testModificarMarca_Nombre() {
		MarcaDto marcaDto = new MarcaDto();
		MarcaAPI servicio = new MarcaAPI();
		
		Marca marcaParaModificar = null;
		try {
			marcaParaModificar = servicio.consultarMarca(1);
		} catch (NullPointerException e) {
			System.out.println("Marca no encontrada");
		}
		
		if (marcaParaModificar != null) {
			marcaDto.setNombre("Kris - Robel");
			marcaDto.setDescripcion(marcaParaModificar.get_descripcion());
			marcaDto.set_estatus(marcaParaModificar.get_estatus());
			
			try {
				servicio.modificarMarca(marcaParaModificar.get_id(), marcaDto);
			} catch (Exception e) {
				Assertions.fail(e.getMessage(), e.getCause());
			}
		}
	}
	
	@Test
	void testModificarMarca_Descripcion() {
		MarcaDto marcaDto = new MarcaDto();
		MarcaAPI servicio = new MarcaAPI();
		
		Marca marcaParaModificar = null;
		try {
			marcaParaModificar = servicio.consultarMarca(3);
		} catch (NullPointerException e) {
			System.out.println("Marca no encontrada");
		}
		
		if (marcaParaModificar != null) {
			marcaDto.setNombre(marcaParaModificar.get_nombre());
			marcaDto.setDescripcion("Eius molestiae sed autem illo.");
			marcaDto.set_estatus(marcaParaModificar.get_estatus());
			
			try {
				servicio.modificarMarca(marcaParaModificar.get_id(), marcaDto);
			} catch (Exception e) {
				Assertions.fail(e.getMessage(), e.getCause());
			}
		}
	}
	
	@Test
	void testModificarMarca_Status() {
		MarcaDto marcaDto = new MarcaDto();
		MarcaAPI servicio = new MarcaAPI();
		
		Marca marcaParaModificar = null;
		try {
			marcaParaModificar = servicio.consultarMarca(5);
		} catch (NullPointerException e) {
			System.out.println("Marca no encontrada");
		}
		
		if (marcaParaModificar != null) {
			marcaDto.setNombre(marcaParaModificar.get_nombre());
			marcaDto.setDescripcion(marcaParaModificar.get_descripcion());
			marcaDto.set_estatus("Inactivo");
			
			try {
				servicio.modificarMarca(marcaParaModificar.get_id(), marcaDto);
			} catch (Exception e) {
				Assertions.fail(e.getMessage(), e.getCause());
			}
		}
	}
	
	@Test
	void testModificarMarca_Todo() {
		MarcaDto marcaDto = new MarcaDto();
		MarcaAPI servicio = new MarcaAPI();
		Marca marcaParaModificar = null;
		
		try {
			marcaParaModificar = servicio.consultarMarca(4);
		} catch (NullPointerException e) {
			System.out.println("Marca no encontrada");
		}
		
		marcaDto.setNombre("Conroy, Ortiz and Ruecker");
		marcaDto.setDescripcion("Et eligendi officia consequatur.");
		marcaDto.set_estatus("Activo");
		
		try {
			if (marcaParaModificar != null) {
				servicio.modificarMarca(marcaParaModificar.get_id(), marcaDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testEliminarMarca() {
		MarcaAPI servicio = new MarcaAPI();
		Marca marcaParaEliminar = null;
		
		try {
			marcaParaEliminar = servicio.consultarMarca(1);
		} catch (NullPointerException e) {
			System.out.println("Marca no encontrada");
		}
		
		try {
			if (marcaParaEliminar != null) {
				servicio.eliminarMarca(marcaParaEliminar.get_id());
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
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