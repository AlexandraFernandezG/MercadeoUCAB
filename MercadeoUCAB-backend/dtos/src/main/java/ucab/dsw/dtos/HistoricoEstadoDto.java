package ucab.dsw.dtos;
import java.util.Date;

public class HistoricoEstadoDto extends DtoBase {
    private Date _fechaInicio;
    private Date _fechaFin;
    private UsuarioDto _usuarioDto;

    public Date getFechaFin() {
        return _fechaFin;
    }

    public void setFechaFin(Date _fechaFin) {
        this._fechaFin = _fechaFin;
    }

    public Date getFechaInicio() {
        return _fechaInicio;
    }

    public void setFechaInicio(Date _fechaInicio) {
        this._fechaInicio = _fechaInicio;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto _usuarioDto) {
        this._usuarioDto = _usuarioDto;
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