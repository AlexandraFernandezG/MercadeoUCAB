package ucab.dsw.dtos;
import java.util.Date;

public class InformacionDto extends DtoBase {

    private int _cedula;
    private String _primerNombre;
    private String _segundoNombre;
    private String _primerApellido;
    private String _segundoApellido;
    private String _genero;
    private Date _fechaNacimiento;
    private String _estadoCivil;
    private String _disponibilidadEnLinea;
    private int _cantidadPersonas;
    private NivelEconomicoDto _nivelEconomicoDto;
    private UsuarioDto _usuarioDto;
    private LugarDto _lugarDto;
    private NivelAcademicoDto _nivelAcademicoDto;
    private OcupacionDto _ocupacionDto;

    public int getCedula() {
        return _cedula;
    }

    public void setCedula(int _cedula) {
        this._cedula = _cedula;
    }

    public String getPrimerNombre() {
        return _primerNombre;
    }

    public void setPrimerNombre(String _primerNombre) {
        this._primerNombre = _primerNombre;
    }

    public String getSegundoNombre() {
        return _segundoNombre;
    }

    public void setSegundoNombre(String _segundoNombre) {
        this._segundoNombre = _segundoNombre;
    }

    public String getPrimerApellido() {
        return _primerApellido;
    }

    public void setPrimerApellido(String _primerApellido) {
        this._primerApellido = _primerApellido;
    }

    public String getSegundoApellido() {
        return _segundoApellido;
    }

    public void setSegundoApellido(String _segundoApellido) {
        this._segundoApellido = _segundoApellido;
    }

    public String getGenero() {
        return _genero;
    }

    public void setGenero(String _genero) {
        this._genero = _genero;
    }

    public Date getFechaNacimiento() {
        return _fechaNacimiento;
    }

    public void setFechaNacimiento(Date _fechaNacimiento) {
        this._fechaNacimiento = _fechaNacimiento;
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

    public NivelEconomicoDto getNivelEconomicoDto() {
        return _nivelEconomicoDto;
    }

    public void setNivelEconomicoDto(NivelEconomicoDto _nivelEconomicoDto) {
        this._nivelEconomicoDto = _nivelEconomicoDto;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto _usuarioDto) {
        this._usuarioDto = _usuarioDto;
    }

    public LugarDto getLugarDto() {
        return _lugarDto;
    }

    public void setLugarDto(LugarDto _lugarDto) {
        this._lugarDto = _lugarDto;
    }

    public NivelAcademicoDto getNivelAcademicoDto() {
        return _nivelAcademicoDto;
    }

    public void setNivelAcademicoDto(NivelAcademicoDto _nivelAcademicoDto) {
        this._nivelAcademicoDto = _nivelAcademicoDto;
    }

    public OcupacionDto getOcupacionDto() {
        return _ocupacionDto;
    }

    public void setOcupacionDto(OcupacionDto _ocupacionDto) {
        this._ocupacionDto = _ocupacionDto;
    }

    public InformacionDto (long id) throws Exception{
        super(id);
    }

    public InformacionDto (String estatus) throws Exception {
        super(estatus);
    }

    public InformacionDto (){
        super();
    }
}
