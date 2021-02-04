package ucab.dsw.response;

import java.util.Date;

public class EstudioInsertResponse {

    private long id;
    private String nombre;
    private String tipoInstrumento;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private String estatus;
    private long usuarioDto;
    private long solicitudEstudioDto;

    public EstudioInsertResponse(long id, String nombre, String tipoInstrumento, Date fechaInicio, Date fechaFin, String estado, String estatus, long usuarioDto, long solicitudEstudioDto) {
        this.id = id;
        this.nombre = nombre;
        this.tipoInstrumento = tipoInstrumento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.estatus = estatus;
        this.usuarioDto = usuarioDto;
        this.solicitudEstudioDto = solicitudEstudioDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public long getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(long usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public long getSolicitudEstudioDto() {
        return solicitudEstudioDto;
    }

    public void setSolicitudEstudioDto(long solicitudEstudioDto) {
        this.solicitudEstudioDto = solicitudEstudioDto;
    }
}
