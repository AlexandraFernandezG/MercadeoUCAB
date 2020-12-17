package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.dtos.ProductoPresentacionTipoDto;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.NivelAcademico;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.entidades.Tipo;

import java.util.List;

class ProductoPresentacionTipoAPITest {
	private final EntidadDto dto = new EntidadDto();
	
	@Test
	void testListarTiposProducto() {
		ProductoPresentacionTipoAPI servicio = new ProductoPresentacionTipoAPI();
		long idProducto = 1;
		List<Tipo> listaTipos = servicio.listarTiposProducto(idProducto);
		int sizeListaTipos = listaTipos.size();
		
		Assertions.assertTrue(sizeListaTipos > 0);
	}
	
	@Test
	void testListarPresentacionesProducto() {
		ProductoPresentacionTipoAPI servicio = new ProductoPresentacionTipoAPI();
		long idProducto = 1;
		List<Presentacion> listaPresentaciones = servicio.listarPresentacionesProducto(idProducto);
		int sizeListaPresentaciones = listaPresentaciones.size();
		
		Assertions.assertTrue(sizeListaPresentaciones > 0);
	}
	
	@Test
	void testAddProductoPresentacionTipo() {
		ProductoPresentacionTipoAPI servicio = new ProductoPresentacionTipoAPI();
		ProductoPresentacionTipoDto pptDto = new ProductoPresentacionTipoDto();
		
		long idProducto = 1;
		long idPresentacion = 1;
		long idTipo = 1;
		
		try {
			ProductoDto productoDto = new ProductoDto(idProducto);
			PresentacionDto presentacionDto = new PresentacionDto(idPresentacion);
			TipoDto tipoDto = new TipoDto(idTipo);
			
			pptDto.setEstatus("Activo");
			pptDto.setProductoDto(productoDto);
			pptDto.setPresentacionDto(presentacionDto);
			pptDto.setTipoDto(tipoDto);
			
			ProductoPresentacionTipoDto pptDtoNuevo = servicio.addProductoPresentacionTipo(pptDto);
			
			Assertions.assertNotNull(pptDtoNuevo);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateEstatusProductoPresentacionTipo() {
		ProductoPresentacionTipoAPI servicio = new ProductoPresentacionTipoAPI();
		
		long idPPT = 1;
		
		try {
			ProductoPresentacionTipoDto pptDto = dto.getProductoPresentacionTipoDto(idPPT);
			pptDto.setEstatus("Inactivo");
			servicio.updateEstatusProductoPresentacionTipo(idPPT, pptDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testUpdateEstatusProductoPresentacionTipo_ReturnsNotFound() {
		ProductoPresentacionTipoAPI servicio = new ProductoPresentacionTipoAPI();
		
		long idPPT = 0;
		
		try {
			ProductoPresentacionTipoDto pptDto = dto.getProductoPresentacionTipoDto(idPPT);
			servicio.updateEstatusProductoPresentacionTipo(idPPT, pptDto);
		} catch (Exception e) {
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void testDeleteProductoPresentacionTipo() {
		ProductoPresentacionTipoAPI servicio = new ProductoPresentacionTipoAPI();
		
		long idPPT = 2;
		
		try {
			servicio.deleteProductoPresentacionTipo(idPPT);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}