package ucab.dsw.dtos;


import java.util.Date;

public class EstudioDto extends DtoBase {

    private String nombre;
    private String tipoInstrumento;
    private Date fechaInicio;
    private Date fechaFin;
    private SolicitudEstudioDto solicitudEstudioDto;
    private UsuarioDto usuarioDto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    public void setTipoInstrumento(String tipoInstrumento) {
        this.tipoInstrumento = tipoInstrumento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public SolicitudEstudioDto getSolicitudEstudioDto() {
        return solicitudEstudioDto;
    }

    public void setSolicitudEstudioDto(SolicitudEstudioDto solicitudEstudioDto) {
        this.solicitudEstudioDto = solicitudEstudioDto;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
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
