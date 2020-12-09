package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

class TipoAPITest {
	
	@Test
	void testListarTipos() {
		TipoAPI servicio = new TipoAPI();
		
		try {
			Assertions.assertTrue(servicio.listarTipos().size() > 0);
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testConsultarTipo() {
		TipoAPI servicio = new TipoAPI();
		Tipo tipoBuscado = servicio.consultarTipo(1);
		
		try {
			Assertions.assertEquals(1, tipoBuscado.get_id());
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testConsultarTipo_Fails() {
		TipoAPI servicio = new TipoAPI();
		Tipo tipoBuscado = servicio.consultarTipo(0);
		
		try {
			Assertions.assertEquals(0, tipoBuscado.get_id());
		} catch (Exception e) {
			System.out.println("Tipo no encontrado");
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void testListarTiposActivos() {
		try {
			Assertions.assertTrue(new TipoAPI().listarTiposActivos().size() > 0);
		} catch (NullPointerException e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testAgregarTipo() {
		TipoAPI servicio = new TipoAPI();
		ProductoDto productoDto = null;
		try {
			productoDto = new ProductoDto(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (productoDto != null) {
			TipoDto tipoDto = new TipoDto();
			tipoDto.set_nombre("est");
			tipoDto.set_descripcion("Ipsum est aliquam et quo ut");
			tipoDto.set_estatus("Activo");
			tipoDto.set_productoDto(productoDto);
			
			try {
				Tipo tipoNuevo = servicio.agregarTipo(tipoDto);
				Assertions.assertNotNull(tipoNuevo);
			} catch (Exception e) {
				Assertions.fail(e.getMessage(), e.getCause());
			}
		} else {
			Assertions.fail();
		}
	}
	
	private UsuarioDto getUsuario(Usuario usuario) throws PruebaExcepcion {
		UsuarioDto usuarioDto = new UsuarioDto();
		
		usuarioDto.setId(usuario.get_id());
		usuarioDto.setNombreUsuario(usuario.getNombre());
		usuarioDto.setCorreo(usuario.getCorreoelectronico());
		usuarioDto.setCodigoRecuperacion(usuario.getCodigoRecuperacion());
		usuarioDto.set_estatus(usuario.get_estatus());
		
		return usuarioDto;
	}
	
	private MarcaDto getMarcaDto(Marca marca) throws PruebaExcepcion {
		MarcaDto marcaDto = new MarcaDto();
		
		marcaDto.setId(marca.get_id());
		marcaDto.setNombre(marca.get_nombre());
		marcaDto.setDescripcion(marca.get_descripcion());
		marcaDto.set_estatus(marca.get_estatus());
		
		return marcaDto;
	}
	
	private CategoriaDto getCategoriaDto(Categoria categoria) throws PruebaExcepcion {
		CategoriaDto categoriaDto = new CategoriaDto();
		
		categoriaDto.setId(categoria.get_id());
		categoriaDto.setNombre(categoria.getNombre());
		categoriaDto.set_descripcion(categoria.get_descripcion());
		categoriaDto.set_estatus(categoria.get_estatus());
		
		return categoriaDto;
	}
	
	private SubcategoriaDto getSubcategoriaDto(Subcategoria subcategoria) throws PruebaExcepcion {
		SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
		
		subcategoriaDto.setId(subcategoria.get_id());
		subcategoriaDto.setNombre(subcategoria.getNombre());
		subcategoriaDto.setDescripcion(subcategoria.getDescripcion());
		subcategoriaDto.set_estatus(subcategoria.get_estatus());
		subcategoriaDto.setCategoriaDto(getCategoriaDto(subcategoria.getCategoria()));
		
		return subcategoriaDto;
	}
	
	private ProductoDto getProductoDto(Producto producto) throws PruebaExcepcion {
		ProductoDto productoDto = new ProductoDto();
		
		productoDto.setId(producto.get_id());
		productoDto.setNombre(producto.getNombre());
		productoDto.setDescripcion(producto.getDescripcion());
		productoDto.set_estatus(producto.get_estatus());
		productoDto.setUsuario(getUsuario(producto.getUsuario()));
		productoDto.setMarca(getMarcaDto(producto.getMarca()));
		productoDto.setSubcategoria(getSubcategoriaDto(producto.getSubcategoria()));
		
		return productoDto;
	}
	
	private TipoDto getTipoDto(Tipo tipoParaModificar) {
		TipoDto tipoDto = new TipoDto();
		
		tipoDto.set_nombre(tipoParaModificar.getNombre());
		tipoDto.set_descripcion(tipoParaModificar.getDescripcion());
		tipoDto.set_estatus(tipoParaModificar.get_estatus());
		
		try {
			tipoDto.set_productoDto(getProductoDto(tipoParaModificar.getProducto()));
		} catch (PruebaExcepcion pruebaExcepcion) {
			pruebaExcepcion.printStackTrace();
		}
		
		return tipoDto;
	}
	
	@Test
	void testModificarTipo_Producto() {
		TipoAPI servicio = new TipoAPI();
		Tipo tipoParaModificar;
		TipoDto tipoDto;
		
		try {
			tipoParaModificar = servicio.consultarTipo(1);
			if (tipoParaModificar != null) {
				DaoProducto daoProducto = new DaoProducto();
				Producto productoNuevo = daoProducto.find(2L,
					Producto.class);
				
				tipoDto = getTipoDto(tipoParaModificar);
				tipoDto.set_productoDto(getProductoDto(productoNuevo));
				
				servicio.modificarTipo(tipoParaModificar.get_id(),
					tipoDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testModificarTipo_Nombre() {
		TipoAPI servicio = new TipoAPI();
		Tipo tipoParaModificar;
		TipoDto tipoDto;
		
		try {
			tipoParaModificar = servicio.consultarTipo(2);
			if (tipoParaModificar != null) {
				tipoDto = getTipoDto(tipoParaModificar);
				
				tipoDto.set_nombre("Chinook");
				
				servicio.modificarTipo(tipoParaModificar.get_id(),
					tipoDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testModificarTipo_Descripcion() {
		TipoAPI servicio = new TipoAPI();
		Tipo tipoParaModificar;
		TipoDto tipoDto;
		
		try {
			tipoParaModificar = servicio.consultarTipo(1);
			if (tipoParaModificar != null) {
				tipoDto = getTipoDto(tipoParaModificar);
				tipoDto.set_descripcion("[vel, qui, at]");
				servicio.modificarTipo(tipoParaModificar.get_id(),
					tipoDto);
			}
		} catch (NullPointerException e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testModificarTipo_Estatus() {
		TipoAPI servicio = new TipoAPI();
		Tipo tipoParaModificar;
		TipoDto tipoDto;
		
		try {
			tipoParaModificar = servicio.consultarTipo(2);
			if (tipoParaModificar != null) {
				tipoDto = getTipoDto(tipoParaModificar);
				
				tipoDto.set_estatus("Inactivo");
				
				servicio.modificarTipo(tipoParaModificar.get_id(),
					tipoDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testModificarTipo_Todo() {
		TipoAPI servicio = new TipoAPI();
		Tipo tipoParaModificar;
		TipoDto tipoDto = new TipoDto();
		
		try {
			tipoParaModificar = servicio.consultarTipo(3);
			if (tipoParaModificar != null) {
				DaoProducto daoProducto = new DaoProducto();
				Producto productoNuevo = daoProducto.find(2L,
					Producto.class);
				
				tipoDto.setId(tipoParaModificar.get_id());
				tipoDto.set_nombre("voluptatem");
				tipoDto.set_descripcion("Eum voluptatem rerum nulla eius eum.");
				tipoDto.set_estatus("Inactivo");
				tipoDto.set_productoDto(getProductoDto(productoNuevo));
				
				servicio.modificarTipo(tipoParaModificar.get_id(),
					tipoDto);
			}
		} catch (Exception e) {
			Assertions.fail(e.getMessage(), e.getCause());
		}
	}
	
	@Test
	void testEliminarTipo() {
		TipoAPI servicio = new TipoAPI();
	}
}