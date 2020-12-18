package ucab.dsw.dtos;


import java.util.Date;

public class EstudioDto extends DtoBase {

    private String _nombre;
    private String _tipoInstrumento;
    private Date _fechaInicio;
    private Date _fechaFin;
    private SolicitudEstudioDto _solicitudEstudioDto;
    private UsuarioDto _usuarioDto;

    public Date getFechaFin() {
        return _fechaFin;
    }

    public Date getFechaInicio() {
        return _fechaInicio;
    }

    public String getNombre() {
        return _nombre;
    }

    public SolicitudEstudioDto getSolicitudEstudioDto() {
        return _solicitudEstudioDto;
    }

    public String getTipoInstrumento() {
        return _tipoInstrumento;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto _usuarioDto) {
        this._usuarioDto = _usuarioDto;
    }

    public void setTipoInstrumento(String _tipoInstrumento) {
        this._tipoInstrumento = _tipoInstrumento;
    }

    public void setSolicitudEstudioDto(SolicitudEstudioDto _solicitudEstudioDto) {
        this._solicitudEstudioDto = _solicitudEstudioDto;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setFechaInicio(Date _fechaInicio) {
        this._fechaInicio = _fechaInicio;
    }

    public void setFechaFin(Date _fechaFin) {
        this._fechaFin = _fechaFin;
    }

    public EstudioDto (long id) throws Exception{
        super(id);
    }

    public EstudioDto (String estatus) throws Exception {
        super(estatus);
    }

    public EstudioDto (){
        super();
    }
}
