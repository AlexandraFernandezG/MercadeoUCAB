package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.HistoricoEstadoDto;
import ucab.dsw.entidades.HistoricoEstado;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

class HistoricoEstadoAPITest {
	EntidadDto dto = new EntidadDto();
	@Test
	void testListarHistoricos() {
		HistoricoEstadoAPI servicio = new HistoricoEstadoAPI();
		
		try {
			List<HistoricoEstado> listaHE = servicio.listarHistoricos();
			int sizeListaHE = listaHE.size();
			Assertions.assertTrue(sizeListaHE > 0);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testConsultarHistorico() {
		HistoricoEstadoAPI servicio = new HistoricoEstadoAPI();
		long idHE = 1;
		
		try {
			HistoricoEstado historicoEstado = servicio.consultarHistorico(idHE);
			Assertions.assertNotNull(historicoEstado);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testHistoricosActivos() {
		HistoricoEstadoAPI servicio = new HistoricoEstadoAPI();
		
		try {
			List<HistoricoEstado> listaHEActivos = servicio.historicosActivos();
			int sizeListaHEActivos = listaHEActivos.size();
			Assertions.assertTrue(sizeListaHEActivos > 0);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testAddHistoricoEstado() {
		HistoricoEstadoAPI servicio = new HistoricoEstadoAPI();
		HistoricoEstadoDto heDto = new HistoricoEstadoDto();
		HistoricoEstadoDto heDtoNuevo;
		HistoricoEstado heEncontrado;
		long idUsuario = 1;
		Date fechaInicio = new Date(2020 - 1900, Calendar.JANUARY, 2);
		Date fechaFin = new Date(2020 - 1900, Calendar.SEPTEMBER, 2);
		
		try {
			heDto.setFechaInicio(fechaInicio);
			heDto.setFechaFin(fechaFin);
			heDto.setEstatus("Activo");
			heDto.setUsuarioDto(dto.getUsuarioDto(idUsuario));
			
			heDtoNuevo = servicio.addHistoricoEstado(heDto);
			
			heEncontrado = servicio.consultarHistorico(heDtoNuevo.getId());
			
			Assertions.assertNotNull(heEncontrado);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testModificarEstatusHistorico() {
		HistoricoEstadoAPI servicio = new HistoricoEstadoAPI();
		long idHistoricoEstado = 1;
		
		try {
			HistoricoEstadoDto historicoEstadoDto = dto.getHistoricoEstadoDto(idHistoricoEstado);
			historicoEstadoDto.setEstatus("Inactivo");
			
			servicio.modificarEstatusHistorico(historicoEstadoDto.getId(), historicoEstadoDto);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testModificarEstatusHistorico_ReturnsNotFound() {
		HistoricoEstadoAPI servicio = new HistoricoEstadoAPI();
		long idHistoricoEstado = 0;
		
		try {
			HistoricoEstadoDto historicoEstadoDto = new HistoricoEstadoDto(idHistoricoEstado);
			servicio.modificarEstatusHistorico(historicoEstadoDto.getId(), historicoEstadoDto);
		} catch (Exception e) {
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void testEliminarHistoricoEstado() {
		HistoricoEstadoAPI servicio = new HistoricoEstadoAPI();
		long idHistoricoEstado = 2;
		
		try {
			servicio.eliminarHistoricoEstado(idHistoricoEstado);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testEliminarHistoricoEstado_ReturnsNotFound() {
		HistoricoEstadoAPI servicio = new HistoricoEstadoAPI();
		long idHistoricoEstado = 0;
		
		try {
			servicio.eliminarHistoricoEstado(idHistoricoEstado);
		} catch (Exception e) {
			Assertions.assertTrue(true);
		}
	}
}