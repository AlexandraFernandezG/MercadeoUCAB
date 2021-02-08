package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EstudiosResponse {

    private long idEstudio;
    private String nombreEstudio;
    private String tipoInstrumentoEstudio;
    private String observacionesEstudio;
    private String fechaInicioEstudio;
    private String fechaFinEstudio;
    private String estadoEstudio;
    private String estatusEstudio;

    public EstudiosResponse(){
        super();
    }

    public EstudiosResponse(long idEstudio, String nombreEstudio, String tipoInstrumentoEstudio, String observacionesEstudio, String fechaInicioEstudio, String fechaFinEstudio, String estadoEstudio, String estatusEstudio) {
        this.idEstudio = idEstudio;
        this.nombreEstudio = nombreEstudio;
        this.tipoInstrumentoEstudio = tipoInstrumentoEstudio;
        this.observacionesEstudio = observacionesEstudio;
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

    public String getFechaInicioEstudio() {
        return fechaInicioEstudio;
    }

    public void setFechaInicioEstudio(String fechaInicioEstudio) {
        this.fechaInicioEstudio = fechaInicioEstudio;
    }

    public String getFechaFinEstudio() {
        return fechaFinEstudio;
    }

    public void setFechaFinEstudio(String fechaFinEstudio) {
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

    public String getObservacionesEstudio() {
        return observacionesEstudio;
    }

    public void setObservacionesEstudio(String observacionesEstudio) {
        this.observacionesEstudio = observacionesEstudio;
    }
}
