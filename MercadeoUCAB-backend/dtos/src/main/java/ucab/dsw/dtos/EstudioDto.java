package ucab.dsw.dtos;


import java.util.Date;

public class EstudioDto extends DtoBase {

    private String _nombre;
    private String _tipoInstrumento;
    private Date _fechaInicio;
    private Date _fechaFin;
    private SolicitudEstudioDto _solicitudEstudioDto;
    private UsuarioDto _usuarioDto;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public String getTipoInstrumento() {
        return _tipoInstrumento;
    }

    public void setTipoInstrumento(String tipoInstrumento) {
        this._tipoInstrumento = tipoInstrumento;
    }

    public Date getFechaInicio() {
        return _fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this._fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return _fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this._fechaFin = fechaFin;
    }

    public SolicitudEstudioDto getSolicitudEstudioDto() {
        return _solicitudEstudioDto;
    }

    public void setSolicitudEstudioDto(SolicitudEstudioDto solicitudEstudioDto) {
        this._solicitudEstudioDto = solicitudEstudioDto;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this._usuarioDto = usuarioDto;
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
