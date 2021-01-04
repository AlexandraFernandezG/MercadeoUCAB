package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EstudiosEncuestadoResponse {

    private long idEstudio;
    private String nombreEstudio;
    private String tipoInstrumentoEstudio;
    private Date fechaInicioEstudio;
    private Date fechaFinEstudio;
    private String estatusEstudio;

    public EstudiosEncuestadoResponse(long idEstudio, String nombreEstudio, String tipoInstrumentoEstudio, Date fechaInicioEstudio, Date fechaFinEstudio, String estatusEstudio) {
        this.idEstudio = idEstudio;
        this.nombreEstudio = nombreEstudio;
        this.tipoInstrumentoEstudio = tipoInstrumentoEstudio;
        this.fechaInicioEstudio = fechaInicioEstudio;
        this.fechaFinEstudio = fechaFinEstudio;
        this.estatusEstudio = estatusEstudio;
    }
}
