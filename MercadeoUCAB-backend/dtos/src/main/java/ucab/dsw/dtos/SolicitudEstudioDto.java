package ucab.dsw.dtos;

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
    private String _estado;
    private UsuarioDto _usuarioDto;
    private NivelEconomicoDto _nivelEconomicoDto;
    private ProductoDto _productoDto;
    private OcupacionDto _ocupacionDto;
    private NivelAcademicoDto _nivelAcademicoDto;

    //Getters, Setters, y otros metodos.

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String getGenero() {
        return _genero;
    }

    public void setGenero(String _genero) {
        this._genero = _genero;
    }

    public int getEdadMinima() {
        return _edadMinima;
    }

    public void setEdadMinima(int _edadMinima) {
        this._edadMinima = _edadMinima;
    }

    public int getEdadMaxima() {
        return _edadMaxima;
    }

    public void setEdadMaxima(int _edadMaxima) {
        this._edadMaxima = _edadMaxima;
    }

    public String getEstadoCivil() {
        return _estadoCivil;
    }

    public void setEstadoCivil(String _estadoCivil) {
        this._estadoCivil = _estadoCivil;
    }

    public String getDisponibilidadEnLinea() {
        return _disponibilidadEnLinea;
    }

    public void setDisponibilidadEnLinea(String _disponibilidadEnLinea) {
        this._disponibilidadEnLinea = _disponibilidadEnLinea;
    }

    public int getCantidadPersonas() {
        return _cantidadPersonas;
    }

    public void setCantidadPersonas(int _cantidadPersonas) {
        this._cantidadPersonas = _cantidadPersonas;
    }

    public int getCantidadHijos() {
        return _cantidadHijos;
    }

    public void setCantidadHijos(int _cantidadHijos) {
        this._cantidadHijos = _cantidadHijos;
    }

    public String getGeneroHijos() {
        return _generoHijos;
    }

    public void setGeneroHijos(String _generoHijos) {
        this._generoHijos = _generoHijos;
    }

    public int getEdadMinimaHijos() {
        return _edadMinimaHijos;
    }

    public void setEdadMinimaHijos(int _edadMinimaHijos) {
        this._edadMinimaHijos = _edadMinimaHijos;
    }

    public int getEdadMaximaHijos() {
        return _edadMaximaHijos;
    }

    public void setEdadMaximaHijos(int _edadMaximaHijos) {
        this._edadMaximaHijos = _edadMaximaHijos;
    }

    public String getEstado() {
        return _estado;
    }

    public void setEstado(String _estado) {
        this._estado = _estado;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto _usuarioDto) {
        this._usuarioDto = _usuarioDto;
    }

    public NivelEconomicoDto getNivelEconomicoDto() {
        return _nivelEconomicoDto;
    }

    public void setNivelEconomicoDto(NivelEconomicoDto _nivelEconomicoDto) {
        this._nivelEconomicoDto = _nivelEconomicoDto;
    }

    public ProductoDto getProductoDto() {
        return _productoDto;
    }

    public void setProductoDto(ProductoDto _productoDto) {
        this._productoDto = _productoDto;
    }

    public OcupacionDto getOcupacionDto() {
        return _ocupacionDto;
    }

    public void setOcupacionDto(OcupacionDto _ocupacionDto) {
        this._ocupacionDto = _ocupacionDto;
    }

    public NivelAcademicoDto getNivelAcademicoDto() {
        return _nivelAcademicoDto;
    }

    public void setNivelAcademicoDto(NivelAcademicoDto _nivelAcademicoDto) {
        this._nivelAcademicoDto = _nivelAcademicoDto;
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
