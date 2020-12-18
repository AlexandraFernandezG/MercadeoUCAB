package ucab.dsw.dtos;

public class RespuestaDto extends DtoBase {
    private String _respuestaAbierta;
    private String _escala;
    private String _verdaderoFalso;
    private String _respuestaSimple;
    private String _respuestaMultiple;
    private PreguntaEstudioDto _preguntaEstudioDto;
    private UsuarioDto _usuarioDto;

    public String getEscala() {
        return _escala;
    }

    public PreguntaEstudioDto getPreguntaEstudioDto() {
        return _preguntaEstudioDto;
    }

    public String getRespuestaAbierta() {
        return _respuestaAbierta;
    }

    public String getRespuestaMultiple() {
        return _respuestaMultiple;
    }

    public String getRespuestaSimple() {
        return _respuestaSimple;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public String getVerdaderoFalso() {
        return _verdaderoFalso;
    }

    public void setVerdaderoFalso(String _verdaderoFalso) {
        this._verdaderoFalso = _verdaderoFalso;
    }

    public void setUsuarioDto(UsuarioDto _usuarioDto) {
        this._usuarioDto = _usuarioDto;
    }

    public void setRespuestaSimple(String _respuestaSimple) {
        this._respuestaSimple = _respuestaSimple;
    }

    public void setRespuestaMultiple(String _respuestaMultiple) {
        this._respuestaMultiple = _respuestaMultiple;
    }

    public void setRespuestaAbierta(String _respuestaAbierta) {
        this._respuestaAbierta = _respuestaAbierta;
    }

    public void setPreguntaEstudioDto(PreguntaEstudioDto _preguntaEstudioDto) {
        this._preguntaEstudioDto = _preguntaEstudioDto;
    }

    public void setEscala(String _escala) {
        this._escala = _escala;
    }

    public RespuestaDto (long id) throws Exception{
        super(id);
    }

    public RespuestaDto (String estatus) throws Exception {
        super(estatus);
    }

    public RespuestaDto (){
        super();
    }
}