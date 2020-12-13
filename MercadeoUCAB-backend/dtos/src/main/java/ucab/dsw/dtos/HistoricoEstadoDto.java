package ucab.dsw.dtos;
import java.util.Date;

public class HistoricoEstadoDto extends DtoBase {
    private Date fechaInicio;
    private Date fechaFin;
    private UsuarioDto usuarioDto;

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

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public HistoricoEstadoDto (long id) throws Exception{
        super(id);
    }

    public HistoricoEstadoDto (String estatus) throws Exception {
        super(estatus);
    }

    public HistoricoEstadoDto (){
        super();
    }
}