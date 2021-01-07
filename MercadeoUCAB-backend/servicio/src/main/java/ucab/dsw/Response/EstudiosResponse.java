package ucab.dsw.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EstudiosResponse {

    private long idEstudio;
    private String nombreEstudio;
    private String tipoInstrumentoEstudio;
    private Date fechaInicioEstudio;
    private Date fechaFinEstudio;
    private String estadoEstudio;
    private String estatusEstudio;

    public EstudiosResponse(long idEstudio, String nombreEstudio, String tipoInstrumentoEstudio, Date fechaInicioEstudio, Date fechaFinEstudio, String estadoEstudio, String estatusEstudio) {
        this.idEstudio = idEstudio;
        this.nombreEstudio = nombreEstudio;
        this.tipoInstrumentoEstudio = tipoInstrumentoEstudio;
        this.fechaInicioEstudio = fechaInicioEstudio;
        this.fechaFinEstudio = fechaFinEstudio;
        this.estadoEstudio = estadoEstudio;
        this.estatusEstudio = estatusEstudio;
    }

    public long getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(long idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getNombreEstudio() {
        return nombreEstudio;
    }

    public void setNombreEstudio(String nombreEstudio) {
        this.nombreEstudio = nombreEstudio;
    }

    public String getTipoInstrumentoEstudio() {
        return tipoInstrumentoEstudio;
    }

    public void setTipoInstrumentoEstudio(String tipoInstrumentoEstudio) {
        this.tipoInstrumentoEstudio = tipoInstrumentoEstudio;
    }

    public Date getFechaInicioEstudio() {
        return fechaInicioEstudio;
    }

    public void setFechaInicioEstudio(Date fechaInicioEstudio) {
        this.fechaInicioEstudio = fechaInicioEstudio;
    }

    public Date getFechaFinEstudio() {
        return fechaFinEstudio;
    }

    public void setFechaFinEstudio(Date fechaFinEstudio) {
        this.fechaFinEstudio = fechaFinEstudio;
    }

    public String getEstadoEstudio() {
        return estadoEstudio;
    }

    public void setEstadoEstudio(String estadoEstudio) {
        this.estadoEstudio = estadoEstudio;
    }

    public String getEstatusEstudio() {
        return estatusEstudio;
    }

    public void setEstatusEstudio(String estatusEstudio) {
        this.estatusEstudio = estatusEstudio;
    }
}
