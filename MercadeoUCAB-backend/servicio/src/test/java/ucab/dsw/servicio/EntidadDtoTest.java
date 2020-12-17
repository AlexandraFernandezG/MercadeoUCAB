package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntidadDtoTest {
	EntidadDto dto = new EntidadDto();
	
	@Test
	void testGetInformacionDto() {
		try {
			Assertions.assertNotNull(dto.getInformacionDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetOcupacionDto() {
		try {
			Assertions.assertNotNull(dto.getOcupacionDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void estGetUsuarioDto() {
		try {
			Assertions.assertNotNull(dto.getUsuarioDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetLugarDtoMunicipio() {
		try {
			Assertions.assertNotNull(dto.getLugarDtoMunicipio(3));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetLugarDtoEstado() {
		try {
			Assertions.assertNotNull(dto.getLugarDtoEstado(2));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetLugarDtoPais() {
		try {
			Assertions.assertNotNull(dto.getLugarDtoPais(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetNivelAcademicoDto() {
		try {
			Assertions.assertNotNull(dto.getNivelAcademicoDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetNivelEconomicoDto() {
		try {
			Assertions.assertNotNull(dto.getNivelEconomicoDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetMarcaDto() {
		try {
			Assertions.assertNotNull(dto.getMarcaDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetCategoriaDto() {
		try {
			Assertions.assertNotNull(dto.getCategoriaDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetSubcategoriaDto() {
		try {
			Assertions.assertNotNull(dto.getSubcategoriaDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetProductoDto() {
		try {
			Assertions.assertNotNull(dto.getProductoDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetTipoDto() {
		try {
			Assertions.assertNotNull(dto.getTipoDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetEstudioDto() {
		try {
			Assertions.assertNotNull(dto.getEstudioDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetEstudioLugarDto() {
		try {
			Assertions.assertNotNull(dto.getEstudioLugarDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetHijoDto() {
		try {
			Assertions.assertNotNull(dto.getHijoDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetHistoricoEstadoDto() {
		try {
			Assertions.assertNotNull(dto.getHistoricoEstadoDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetMedioComunicacionDto() {
		try {
			Assertions.assertNotNull(dto.getMedioComunicacionDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetPreguntaEncuestaDto() {
		try {
			Assertions.assertNotNull(dto.getPreguntaEncuestaDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetPreguntaEstudioDto() {
		try {
			Assertions.assertNotNull(dto.getPreguntaEstudioDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetPresentacionDto() {
		try {
			Assertions.assertNotNull(dto.getHistoricoEstadoDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetRespuestaDto() {
		try {
			Assertions.assertNotNull(dto.getRespuestaDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetRespuestaPreguntaDto() {
		try {
			Assertions.assertNotNull(dto.getRespuestaPreguntaDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetRolDto() {
		try {
			Assertions.assertNotNull(dto.getRolDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetSolicitudEstudioDto() {
		try {
			Assertions.assertNotNull(dto.getSolicitudEstudioDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testGetTelefonoDto() {
		try {
			Assertions.assertNotNull(dto.getTelefonoDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void getProductoPresentacionTipoDto() {
		try {
			Assertions.assertNotNull(dto.getProductoPresentacionTipoDto(1));
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
}