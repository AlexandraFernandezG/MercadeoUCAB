package ucab.dsw.servicio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

public class EntidadDto {
	protected InformacionDto getInformacionDto(long id) throws PruebaExcepcion {
		InformacionDto informacionDto = new InformacionDto();
		DaoInformacion daoInformacion = new DaoInformacion();
		
		Informacion informacion = daoInformacion.find(id,
			Informacion.class);
		
		if (informacion != null) {
			informacionDto.setId(informacion.get_id());
			informacionDto.setCedula(informacion.get_cedula());
			informacionDto.setPrimerNombre(informacion.get_primerNombre());
			informacionDto.setSegundoNombre(informacion.get_segundoNombre());
			informacionDto.setPrimerApellido(informacion.get_primerApellido());
			informacionDto.setGenero(informacion.get_genero());
			informacionDto.setFechaNacimiento(informacion.get_fechaNacimiento());
			informacionDto.setEstadoCivil(informacion.get_estadoCivil());
			informacionDto.setDisponibilidadEnLinea(informacion.get_disponibilidadEnLinea());
			informacionDto.setCantidadPersonas(informacion.get_cantidadPersonas());
			informacionDto.setEstatus(informacion.get_estatus());
			
			informacionDto.setLugarDto(getLugarDtoMunicipio(informacion.get_lugar().get_id()));
			informacionDto.setNivelEconomicoDto(getNivelEconomicoDto(informacion.get_nivelEconomico().get_id()));
			informacionDto.setNivelAcademicoDto(getNivelAcademicoDto(informacion.get_nivelAcademico().get_id()));
			informacionDto.setOcupacionDto(getOcupacionDto(informacion.get_ocupacion().get_id()));
			informacionDto.setUsuarioDto(getUsuarioDto(informacion.get_usuario().get_id()));
		}
		
		return informacionDto;
	}
	
	protected OcupacionDto getOcupacionDto(long id) throws PruebaExcepcion {
		OcupacionDto ocupacionDto = new OcupacionDto();
		DaoOcupacion daoOcupacion = new DaoOcupacion();
		
		Ocupacion ocupacion = daoOcupacion.find(id, Ocupacion.class);
		
		if (ocupacion != null) {
			ocupacionDto.setId(ocupacion.get_id());
			ocupacionDto.setNombre(ocupacion.get_nombre());
			ocupacionDto.setEstatus(ocupacion.get_estatus());
		}
		
		return ocupacionDto;
	}
	
	protected UsuarioDto getUsuarioDto(long id) throws PruebaExcepcion {
		UsuarioDto usuarioDto = new UsuarioDto();
		DaoUsuario daoUsuario = new DaoUsuario();
		
		Usuario usuario = daoUsuario.find(id, Usuario.class);
		
		if (usuario != null) {
			usuarioDto.setId(usuario.get_id());
			usuarioDto.setNombreUsuario(usuario.get_nombre());
			usuarioDto.setCorreo(usuario.get_correoelectronico());
			usuarioDto.setCodigoRecuperacion(usuario.get_codigoRecuperacion());
			usuarioDto.setEstatus(usuario.get_estatus());
//			usuarioDto.setContrasena();
			usuarioDto.setRol(this.getRolDto(usuario.get_rol().get_id()));
		}
		
		return usuarioDto;
	}
	
	private LugarDto getLugarDtoBasico(Lugar lugar) throws PruebaExcepcion {
		LugarDto lugarDto = new LugarDto();
		
		lugarDto.setId(lugar.get_id());
		lugarDto.setNombre(lugar.get_nombre());
		lugarDto.setTipo(lugar.get_tipo());
		lugarDto.setCategoriaSocioEconomica(lugar.get_categoriaSocioEconomica());
		lugarDto.setEstatus(lugar.get_estatus());
		
		return lugarDto;
	}
	
	protected LugarDto getLugarDtoMunicipio(long id) throws PruebaExcepcion {
		DaoLugar daoLugar = new DaoLugar();
		LugarDto lugarDto = new LugarDto();
		Lugar lugar = daoLugar.find(id, Lugar.class);
		
		if (lugar != null) {
			// No tiene la fk de estado.
			LugarDto municipio = this.getLugarDtoBasico(lugar); // Le paso el lugar para tener los datos básicos.
			LugarDto estado = this.getLugarDtoEstado(lugar.get_lugar().get_id()); // Busco el estado de ese lugar (municipio)
			if (estado != null && estado.getEstatus().equals("Activo")) {
				municipio.setLugar(estado);
			}
		}
		
		return lugarDto;
	}
	
	protected LugarDto getLugarDtoEstado(long id) throws PruebaExcepcion {
		DaoLugar daoLugar = new DaoLugar();
		LugarDto estado = new LugarDto();
		Lugar lugar = daoLugar.find(id, Lugar.class);
		
		if (lugar != null) {
			estado = this.getLugarDtoBasico(lugar);
			LugarDto pais = this.getLugarDtoPais(lugar.get_lugar().get_id()); // Busco el pais de ese lugar (estado).
			
			if (pais != null && pais.getEstatus().equals("Activo")) {
				estado.setLugar(pais);
			}
		}
		
		return estado;
	}
	
	protected LugarDto getLugarDtoPais(long id) throws PruebaExcepcion {
		DaoLugar daoLugar = new DaoLugar();
		LugarDto pais = new LugarDto();
		Lugar lugar = daoLugar.find(id, Lugar.class);
		
		if (lugar != null) {
			pais = this.getLugarDtoBasico(lugar);
		}
		return pais;
	}
	
	protected NivelAcademicoDto getNivelAcademicoDto(long id) throws PruebaExcepcion {
		/** Método que retorna un DTO de Nivel Académico, a partir del objeto de tipo NivelAcadémico pasado por parámetros.
		 *
		 * Su objetivo es crear un DTO con las mismas propiedades del objeto NivelAcademico,
		 * para que así, en el método (la prueba) en el que es llamado, solo asigne los cambios
		 * a realizar.
		 *
		 * @param id ID del registro que se desea buscar y agregar.
		 * */
		
		NivelAcademicoDto naDto = new NivelAcademicoDto();
		DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
		NivelAcademico na = daoNivelAcademico.find(id,
			NivelAcademico.class);
		
		if (na != null) {
			naDto.setId(na.get_id());
			naDto.setDescripcion(na.get_descripcion());
			naDto.setEstatus(na.get_estatus());
		}
		
		return naDto;
	}
	
	protected NivelEconomicoDto getNivelEconomicoDto(long id) throws PruebaExcepcion {
		/** Método que retorna un DTO de Nivel Económico, a partir del objeto de tipo
		 * NivelEconómico pasado por parámetros.
		 *
		 * Su objetivo es crear un DTO con las mismas propiedades del objeto NivelEconómico,
		 * para que así, en el método (la prueba) en el que es llamado, solo asigne los cambios
		 * a realizar.
		 *
		 * @param id ID del registro que se desea buscar y agregar.
		 * */
		
		NivelEconomicoDto neDto = new NivelEconomicoDto();
		DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
		NivelEconomico ne = daoNivelEconomico.find(id,
			NivelEconomico.class);
		
		if (ne != null) {
			neDto.setId(ne.get_id());
			neDto.setDescripcion(ne.get_descripcion());
			neDto.setEstatus(ne.get_estatus());
		}
		
		return neDto;
	}
	
	protected MarcaDto getMarcaDto(long id) throws PruebaExcepcion {
		MarcaDto marcaDto = new MarcaDto();
		DaoMarca daoMarca = new DaoMarca();
		
		Marca marca = daoMarca.find(id, Marca.class);
		
		if (marca != null) {
			marcaDto.setId(marca.get_id());
			marcaDto.setNombre(marca.get_nombre());
			marcaDto.setDescripcion(marca.get_descripcion());
			marcaDto.setEstatus(marca.get_estatus());
		}
		
		return marcaDto;
	}
	
	protected CategoriaDto getCategoriaDto(long id) throws PruebaExcepcion {
		CategoriaDto categoriaDto = new CategoriaDto();
		DaoCategoria daoCategoria = new DaoCategoria();
		
		Categoria categoria = daoCategoria.find(id, Categoria.class);
		
		if (categoria != null) {
			categoriaDto.setId(categoria.get_id());
			categoriaDto.setNombre(categoria.get_nombre());
			categoriaDto.setDescripcion(categoria.get_descripcion());
			categoriaDto.setEstatus(categoria.get_estatus());
		}
		
		return categoriaDto;
	}
	
	protected SubcategoriaDto getSubcategoriaDto(long id) throws PruebaExcepcion {
		SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
		DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
		
		Subcategoria subcategoria = daoSubcategoria.find(id, Subcategoria.class);
		
		if (subcategoria != null) {
			subcategoriaDto.setId(subcategoria.get_id());
			subcategoriaDto.setNombre(subcategoria.get_nombre());
			subcategoriaDto.setDescripcion(subcategoria.get_descripcion());
			subcategoriaDto.setEstatus(subcategoria.get_estatus());
			subcategoriaDto.setCategoriaDto(getCategoriaDto(subcategoria.get_categoria().get_id()));
		}
		
		return subcategoriaDto;
	}
	
	protected ProductoDto getProductoDto(long id) throws PruebaExcepcion {
		ProductoDto productoDto = new ProductoDto();
		DaoProducto daoProducto = new DaoProducto();
		
		Producto producto = daoProducto.find(id, Producto.class);
		
		if (producto != null) {
			productoDto.setId(producto.get_id());
			productoDto.setNombre(producto.get_nombre());
			productoDto.setDescripcion(producto.get_descripcion());
			productoDto.setEstatus(producto.get_estatus());
			productoDto.setUsuarioDto(getUsuarioDto(producto.get_usuario().get_id()));
			productoDto.setMarcaDto(this.getMarcaDto(producto.get_marca().get_id()));
			productoDto.setSubcategoriaDto(getSubcategoriaDto(producto.get_subcategoria().get_id()));
		}
		
		return productoDto;
	}
	
	protected TipoDto getTipoDto(long id) throws PruebaExcepcion {
		TipoDto tipoDto = new TipoDto();
		DaoTipo daoTipo = new DaoTipo();
		
		Tipo tipo = daoTipo.find(id, Tipo.class);
		
		if (tipo != null) {
			tipoDto.setId(tipo.get_id());
			tipoDto.setNombre(tipo.get_nombre());
			tipoDto.setDescripcion(tipo.get_descripcion());
			tipoDto.setEstatus(tipo.get_estatus());
//			tipoDto.setProductoDto(getProductoDto(tipo.get_producto().get_id()));
		}
		
		return tipoDto;
	}
	
	protected EstudioDto getEstudioDto(long id) throws PruebaExcepcion {
		EstudioDto estudioDto = new EstudioDto();
		DaoEstudio daoEstudio = new DaoEstudio();
		
		Estudio estudio = daoEstudio.find(id, Estudio.class);
		
		if (estudio != null) {
			estudioDto.setId(estudio.get_id());
			estudioDto.setNombre(estudio.get_nombre());
			estudioDto.setEstatus(estudio.get_estatus());
			estudioDto.setTipoInstrumento(estudio.get_tipoInstrumento());
			estudioDto.setFechaInicio(estudio.get_fechaInicio());
			estudioDto.setFechaFin(estudio.get_fechaFin());
			estudioDto.setUsuarioDto(this.getUsuarioDto(estudio.get_usuario().get_id()));
			estudioDto.setSolicitudEstudioDto(this.getSolicitudEstudioDto(estudio.get_solicitudEstudio().get_id()));
		}
		
		return estudioDto;
	}
	
	protected SolicitudEstudioLugarDto getEstudioLugarDto(long id) throws PruebaExcepcion {
		SolicitudEstudioLugarDto solicitudEstudioLugarDto = new SolicitudEstudioLugarDto();
		DaoSolicitudEstudioLugar daoSolicitudEstudioLugar = new DaoSolicitudEstudioLugar();
		
		SolicitudEstudioLugar solicitudEstudioLugar = daoSolicitudEstudioLugar.find(id, SolicitudEstudioLugar.class);
		
		if (solicitudEstudioLugar != null) {
			solicitudEstudioLugarDto.setId(solicitudEstudioLugar.get_id());
			solicitudEstudioLugarDto.setEstatus(solicitudEstudioLugar.get_estatus());
			solicitudEstudioLugarDto.setSolicitudestudioDto(this.getSolicitudEstudioDto(solicitudEstudioLugar.get_SolicitudEstudio().get_id()));
			solicitudEstudioLugarDto.setLugarDto(this.getLugarDtoMunicipio(solicitudEstudioLugar.get_lugar().get_id()));
		}
		
		return solicitudEstudioLugarDto;
	}
	
	protected HijoDto getHijoDto(long id) throws PruebaExcepcion {
		HijoDto hijoDto = new HijoDto();
		DaoHijo daoHijo = new DaoHijo();
		
		Hijo hijo = daoHijo.find(id, Hijo.class);
		
		if (hijo != null) {
			hijoDto.setId(hijo.get_id());
			hijoDto.setGenero(hijo.get_genero());
			hijoDto.setFechaNacimiento(hijo.get_fechaNacimiento());
			hijoDto.setEstatus(hijo.get_estatus());
			hijoDto.setInformacionDto(this.getInformacionDto(hijo.get_informacion().get_id()));
		}
		
		return hijoDto;
	}
	
	protected UsuarioEstudioDto getHistoricoEstadoDto(long id) throws PruebaExcepcion {
		UsuarioEstudioDto heDto = new UsuarioEstudioDto();
		DaoUsuarioEstudio dao = new DaoUsuarioEstudio();
		
		UsuarioEstudio he = dao.find(id, UsuarioEstudio.class);
		
		if (he != null) {
			heDto.setId(he.get_id());
			heDto.setEstatus(he.get_estatus());
			heDto.setUsuarioDto(this.getUsuarioDto(he.get_usuario().get_id()));
		}
		
		return heDto;
	}
	
	protected MedioComunicacionDto getMedioComunicacionDto(long id) throws PruebaExcepcion {
		MedioComunicacionDto mcDto = new MedioComunicacionDto();
		DaoMedioComunicacion dao = new DaoMedioComunicacion();
		
		MedioComunicacion mc = dao.find(id, MedioComunicacion.class);
		
		if (mc != null) {
			mcDto.setId(mc.get_id());
			mcDto.setTipoDeMedio(mc.get_tipoDeMedio());
			mcDto.setEstatus(mc.get_estatus());
			mcDto.setInformacionDto(this.getInformacionDto(mc.get_informacion().get_id()));
			mcDto.setSolicitudEstudioDto(this.getSolicitudEstudioDto(mc.get_solicitudEstudio().get_id()));
		}
		
		return mcDto;
	}
	
	protected PreguntaEncuestaDto getPreguntaEncuestaDto(long id) throws PruebaExcepcion {
		PreguntaEncuestaDto peDto = new PreguntaEncuestaDto();
		DaoPreguntaEncuesta dao = new DaoPreguntaEncuesta();
		
		PreguntaEncuesta pe = dao.find(id, PreguntaEncuesta.class);
		
		if (pe != null) {
			peDto.setId(pe.get_id());
			peDto.setDescripcion(pe.get_descripcion());
			peDto.setTipoPregunta(pe.get_tipoPregunta());
			peDto.setEstatus(pe.get_estatus());
			peDto.setUsuarioDto(this.getUsuarioDto(pe.get_usuario().get_id()));
			peDto.setSubcategoriaDto(this.getSubcategoriaDto(pe.get_subcategoria().get_id()));
		}
		
		return peDto;
	}
	
	protected PreguntaEstudioDto getPreguntaEstudioDto(long id) throws PruebaExcepcion {
		PreguntaEstudioDto peDto = new PreguntaEstudioDto();
		DaoPreguntaEstudio dao = new DaoPreguntaEstudio();
		
		PreguntaEstudio pe = dao.find(id, PreguntaEstudio.class);
		
		if (pe != null) {
			peDto.setId(pe.get_id());
			peDto.setEstatus(pe.get_estatus());
			peDto.setEstudioDto(this.getEstudioDto(pe.get_estudio().get_id()));
			peDto.setPreguntaEncuestaDto(this.getPreguntaEncuestaDto(pe.get_preguntaEncuesta().get_id()));
		}
		
		return peDto;
	}
	
	protected PresentacionDto getPresentacionDto(long id) throws PruebaExcepcion {
		PresentacionDto presentacionDto = new PresentacionDto();
		DaoPresentacion daoPresentacion = new DaoPresentacion();
		
		Presentacion presentacion = daoPresentacion.find(id, Presentacion.class);
		
		if (presentacion != null) {
			presentacionDto.setId(presentacion.get_id());
			presentacionDto.setNombre(presentacion.get_nombre());
//			presentacionDto.setCaracteristicas(presentacion.get_caracteristicas());
			presentacionDto.setEstatus(presentacion.get_estatus());
//			presentacionDto.setProductoDto(this.getProductoDto(presentacion.get_producto().get_id()));
		}
		
		return presentacionDto;
	}
	
	protected RespuestaDto getRespuestaDto(long id) throws PruebaExcepcion {
		RespuestaDto respuestaDto = new RespuestaDto();
		DaoRespuesta daoRespuesta = new DaoRespuesta();
		
		Respuesta respuesta = daoRespuesta.find(id, Respuesta.class);
		
		if (respuesta != null) {
			respuestaDto.setId(respuesta.get_id());
			respuestaDto.setEstatus(respuesta.get_estatus());
			respuestaDto.setRespuestaAbierta(respuesta.get_respuestaAbierta());
			respuestaDto.setRespuestaMultiple(respuesta.get_respuestaMultiple());
			respuestaDto.setRespuestaSimple(respuesta.get_respuestaSimple());
			respuestaDto.setEscala(respuesta.get_escala());
			respuestaDto.setVerdaderoFalso(respuesta.get_verdaderoFalso());
			respuestaDto.setUsuarioDto(this.getUsuarioDto(respuesta.get_usuario().get_id()));
			respuestaDto.setPreguntaEstudioDto(this.getPreguntaEstudioDto(respuesta.get_preguntaEstudio().get_id()));
		}
		
		return respuestaDto;
	}
	
	protected RespuestaPreguntaDto getRespuestaPreguntaDto(long id) throws PruebaExcepcion {
		RespuestaPreguntaDto rpDto = new RespuestaPreguntaDto();
		DaoRespuestaPregunta dao = new DaoRespuestaPregunta();
		
		RespuestaPregunta rp = dao.find(id, RespuestaPregunta.class);
		
		if (rp != null) {
			rpDto.setId(rp.get_id());
			rpDto.setNombre(rp.get_nombre());
			rpDto.setEstatus(rp.get_estatus());
			rpDto.setPreguntaEncuestaDto(this.getPreguntaEncuestaDto(rp.get_preguntaEncuesta().get_id()));
		}
		
		return rpDto;
	}
	
	protected RolDto getRolDto(long id) throws PruebaExcepcion {
		RolDto rolDto = new RolDto();
		DaoRol daoRol = new DaoRol();
		
		Rol rol = daoRol.find(id, Rol.class);
		
		if (rol != null) {
			rolDto.setId(rol.get_id());
			rolDto.setNombre(rol.get_nombre());
			rolDto.setEstatus(rol.get_estatus());
		}
		
		return rolDto;
	}
	
	protected SolicitudEstudioDto getSolicitudEstudioDto(long id) throws PruebaExcepcion {
		SolicitudEstudioDto seDto = new SolicitudEstudioDto();
		DaoSolicitudEstudio dao = new DaoSolicitudEstudio();
		
		SolicitudEstudio se = dao.find(id, SolicitudEstudio.class);
		
		if (se != null) {
			seDto.setId(se.get_id());
			seDto.setDescripcion(se.get_descripcion());
			seDto.setGenero(se.get_genero());
			seDto.setEdadMaxima(se.get_edadMaxima());
			seDto.setEdadMinima(se.get_edadMinima());
			seDto.setEstadoCivil(se.get_estadoCivil());
			seDto.setDisponibilidadEnLinea(se.get_disponibilidadEnLinea());
			seDto.setCantidadPersonas(se.get_cantidadPersonas());
			seDto.setCantidadHijos(se.get_cantidadHijos());
			seDto.setGeneroHijos(se.get_generoHijos());
			seDto.setEdadMinimaHijos(se.get_edadMinimaHijos());
			seDto.setEdadMaximaHijos(se.get_edadMaximaHijos());
			seDto.setEstatus(se.get_estatus());
			seDto.setNivelEconomicoDto(this.getNivelEconomicoDto(se.get_nivelEconomico().get_id()));
			seDto.setUsuarioDto(this.getUsuarioDto(se.get_usuario().get_id()));
			seDto.setProductoDto(this.getProductoDto(se.get_producto().get_id()));
			seDto.setOcupacionDto(this.getOcupacionDto(se.get_ocupacion().get_id()));
			seDto.setNivelAcademicoDto(this.getNivelAcademicoDto(se.get_nivelAcademico().get_id()));
		}
		
		return seDto;
	}
	
	protected TelefonoDto getTelefonoDto(long id) throws PruebaExcepcion {
		TelefonoDto telefonoDto = new TelefonoDto();
		DaoTelefono daoTelefono = new DaoTelefono();
		
		Telefono telefono = daoTelefono.find(id, Telefono.class);
		
		if (telefono != null) {
			telefonoDto.setId(telefono.get_id());
			telefonoDto.setNumero(telefono.get_numero());
			telefonoDto.setEstatus(telefono.get_estatus());
			telefonoDto.setInformacion(this.getInformacionDto(telefono.get_informacion().get_id()));
		}
		
		return telefonoDto;
	}
	
	protected ProductoPresentacionTipoDto getProductoPresentacionTipoDto(long id) throws PruebaExcepcion {
		ProductoPresentacionTipoDto pptDto = new ProductoPresentacionTipoDto();
		DaoProductoPresentacionTipo dao = new DaoProductoPresentacionTipo();
		
		ProductoPresentacionTipo ppt = dao.find(id, ProductoPresentacionTipo.class);
		
		if (ppt != null) {
			pptDto.setId(ppt.get_id());
			pptDto.setEstatus(ppt.get_estatus());
			pptDto.setProductoDto(this.getProductoDto(ppt.get_producto().get_id()));
			pptDto.setPresentacionDto(this.getPresentacionDto(ppt.get_presentacion().get_id()));
			pptDto.setTipoDto(this.getTipoDto(ppt.get_tipo().get_id()));
		}
		
		return pptDto;
	}
}
