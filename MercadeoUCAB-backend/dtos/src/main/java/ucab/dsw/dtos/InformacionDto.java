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

    public void setCedula(int cedula) {
        this._cedula = cedula;
    }

    public String getPrimerNombre() {
        return _primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this._primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return _segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this._segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return _primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this._primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return _segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this._segundoApellido = segundoApellido;
    }

    public String getGenero() {
        return _genero;
    }

    public void setGenero(String genero) {
        this._genero = genero;
    }

    public Date getFechaNacimiento() {
        return _fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this._fechaNacimiento = fechaNacimiento;
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

    public NivelEconomicoDto getNivelEconomicoDto() {
        return _nivelEconomicoDto;
    }

    public void setNivelEconomicoDto(NivelEconomicoDto nivelEconomicoDto) {
        this._nivelEconomicoDto = nivelEconomicoDto;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this._usuarioDto = usuarioDto;
    }

    public LugarDto getLugarDto() {
        return _lugarDto;
    }

    public void setLugarDto(LugarDto lugarDto) {
        this._lugarDto = lugarDto;
    }

    public NivelAcademicoDto getNivelAcademicoDto() {
        return _nivelAcademicoDto;
    }

    public void setNivelAcademicoDto(NivelAcademicoDto nivelAcademicoDto) {
        this._nivelAcademicoDto = nivelAcademicoDto;
    }

    public OcupacionDto getOcupacionDto() {
        return _ocupacionDto;
    }

    public void setOcupacionDto(OcupacionDto ocupacionDto) {
        this._ocupacionDto = ocupacionDto;
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
