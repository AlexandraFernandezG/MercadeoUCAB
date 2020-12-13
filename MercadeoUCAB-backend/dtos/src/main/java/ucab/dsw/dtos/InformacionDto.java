package ucab.dsw.dtos;
import java.util.Date;

public class InformacionDto extends DtoBase {

    private int cedula;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String genero;
    private Date fechaNacimiento;
    private String estadoCivil;
    private String disponibilidadEnLinea;
    private int cantidadPersonas;
    private NivelEconomicoDto nivelEconomicoDto;
    private UsuarioDto usuarioDto;
    private LugarDto lugarDto;
    private NivelAcademicoDto nivelAcademicoDto;
    private OcupacionDto ocupacionDto;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDisponibilidadEnLinea() {
        return disponibilidadEnLinea;
    }

    public void setDisponibilidadEnLinea(String disponibilidadEnLinea) {
        this.disponibilidadEnLinea = disponibilidadEnLinea;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public NivelEconomicoDto getNivelEconomicoDto() {
        return nivelEconomicoDto;
    }

    public void setNivelEconomicoDto(NivelEconomicoDto nivelEconomicoDto) {
        this.nivelEconomicoDto = nivelEconomicoDto;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public LugarDto getLugarDto() {
        return lugarDto;
    }

    public void setLugarDto(LugarDto lugarDto) {
        this.lugarDto = lugarDto;
    }

    public NivelAcademicoDto getNivelAcademicoDto() {
        return nivelAcademicoDto;
    }

    public void setNivelAcademicoDto(NivelAcademicoDto nivelAcademicoDto) {
        this.nivelAcademicoDto = nivelAcademicoDto;
    }

    public OcupacionDto getOcupacionDto() {
        return ocupacionDto;
    }

    public void setOcupacionDto(OcupacionDto ocupacionDto) {
        this.ocupacionDto = ocupacionDto;
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
