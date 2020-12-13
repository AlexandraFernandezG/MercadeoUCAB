package ucab.dsw.dtos;

import java.util.Date;

public class SolicitudEstudioDto extends DtoBase{

    private String _descripcion;
    private String _genero;
    private int _edadMinima;
    private int _edadMaxima;
    private String _estadoCivil;
    private String _disponibilidadEnLinea;
    private int _cantidadPersonas;
    private int _cantidadHijos;
    private String _generoHijos;
    private int _edadMinimaHijos;
    private int _edadMaximaHijos;
    private UsuarioDto _usuarioDto;
    private NivelEconomicoDto _nivelEconomicoDto;
    private ProductoDto _productoDto;
    private OcupacionDto _ocupacionDto;
    private NivelAcademicoDto _nivelAcademicoDto;
    
    //Getters, Setters, y otros metodos.

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String descripcion) {
        this._descripcion = descripcion;
    }

    public String getGenero() {
        return _genero;
    }

    public void setGenero(String genero) {
        this._genero = genero;
    }

    public int getEdadMinima() {
        return _edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this._edadMinima = edadMinima;
    }

    public int getEdadMaxima() {
        return _edadMaxima;
    }

    public void setEdadMaxima(int edadMaxima) {
        this._edadMaxima = edadMaxima;
    }

    public String getEstadoCivil() {
        return _estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this._estadoCivil = estadoCivil;
    }

    public String getDisponibilidadEnLinea() {
        return _disponibilidadEnLinea;
    }

    public void setDisponibilidadEnLinea(String disponibilidadEnLinea) {
        this._disponibilidadEnLinea = disponibilidadEnLinea;
    }

    public int getCantidadPersonas() {
        return _cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this._cantidadPersonas = cantidadPersonas;
    }

    public int getCantidadHijos() {
        return _cantidadHijos;
    }

    public void setCantidadHijos(int cantidadHijos) {
        this._cantidadHijos = cantidadHijos;
    }

    public String getGeneroHijos() {
        return _generoHijos;
    }

    public void setGeneroHijos(String generoHijos) {
        this._generoHijos = generoHijos;
    }

    public int getEdadMinimaHijos() {
        return _edadMinimaHijos;
    }

    public void setEdadMinimaHijos(int edadMinimaHijos) {
        this._edadMinimaHijos = edadMinimaHijos;
    }

    public int getEdadMaximaHijos() {
        return _edadMaximaHijos;
    }

    public void setEdadMaximaHijos(int edadMaximaHijos) {
        this._edadMaximaHijos = edadMaximaHijos;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this._usuarioDto = usuarioDto;
    }

    public NivelEconomicoDto getNivelEconomicoDto() {
        return _nivelEconomicoDto;
    }

    public void setNivelEconomicoDto(NivelEconomicoDto nivelEconomicoDto) {
        this._nivelEconomicoDto = nivelEconomicoDto;
    }

    public ProductoDto getProductoDto() {
        return _productoDto;
    }

    public void setProductoDto(ProductoDto productoDto) {
        this._productoDto = productoDto;
    }

    public OcupacionDto getOcupacionDto() {
        return _ocupacionDto;
    }

    public void setOcupacionDto(OcupacionDto ocupacionDto) {
        this._ocupacionDto = ocupacionDto;
    }

    public NivelAcademicoDto getNivelAcademicoDto() {
        return _nivelAcademicoDto;
    }

    public void setNivelAcademicoDto(NivelAcademicoDto nivelAcademicoDto) {
        this._nivelAcademicoDto = nivelAcademicoDto;
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
