package ucab.dsw.dtos;

import java.util.Date;

public class SolicitudEstudioDto extends DtoBase{
    
    private String _descripcionSolicitud;
    private String _generoSolicitud;
    private Date _fechaSolicitud;
    private String _regionSolicitud;
    private String _edadMaximaPoblacion;
    private String _edadMinimaPoblacion;
    private UsuarioDto _usuarioDto;
    private NivelEconomicoDto _nivelEconomicoDto;
    private ProductoDto _productoDto;
    
    //Getters, Setters, y otros metodos.

    public String getDescripcionSolicitud() {
        return _descripcionSolicitud;
    }

    public String getGeneroSolicitud() {
        return _generoSolicitud;
    }

    public Date getFechaSolicitud() {
        return _fechaSolicitud;
    }

    public String getRegionSolicitud() {
        return _regionSolicitud;
    }

    public String getEdadMinimaPoblacion() {
        return _edadMinimaPoblacion;
    }

    public String getEdadMaximaPoblacion() {
        return _edadMaximaPoblacion;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public NivelEconomicoDto getNivelEconomicoDto() {
        return _nivelEconomicoDto;
    }

    public ProductoDto getProductoDto() {
        return _productoDto;
    }

    public void setDescripcionSolicitud(String _descripcionSolicitud) {
        this._descripcionSolicitud = _descripcionSolicitud;
    }

    public void setGeneroSolicitud(String _generoSolicitud) {
        this._generoSolicitud = _generoSolicitud;
    }

    public void setFechaSolicitud(Date _fechaSolicitud) {
        this._fechaSolicitud = _fechaSolicitud;
    }

    public void setRegionSolicitud(String _regionSolicitud) {
        this._regionSolicitud = _regionSolicitud;
    }

    public void setEdadMinimaPoblacion(String _edadMinimaPoblacion) {
        this._edadMinimaPoblacion = _edadMinimaPoblacion;
    }

    public void setEdadMaximaPoblacion(String _edadMaximaPoblacion) {
        this._edadMaximaPoblacion = _edadMaximaPoblacion;
    }

    public void setUsuarioDto(UsuarioDto _usuarioDto) {
        this._usuarioDto = _usuarioDto;
    }

    public void setNivelEconomicoDto(NivelEconomicoDto _nivelEconomicoDto) {
        this._nivelEconomicoDto = _nivelEconomicoDto;
    }

    public void setProductoDto(ProductoDto _productoDto) {
        this._productoDto = _productoDto;
    }

    public SolicitudEstudioDto (long id) throws Exception{
        super(id);
    }

    public SolicitudEstudioDto (String estatus) throws Exception {
        super(estatus);
    }

    public SolicitudEstudioDto (){
        super();
    }
    
}
