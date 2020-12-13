package ucab.dsw.dtos;
import java.util.Date;

public class HistoricoEstadoDto extends DtoBase {
    private Date _fechaInicio;
    private Date _fechaFin;
    private UsuarioDto _usuarioDto;

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

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this._usuarioDto = usuarioDto;
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