package ucab.dsw.dtos;

public class RespuestaDto extends DtoBase {

    private String _respuestaAbierta;
    private String _escala;
    private String _verdaderoFalso;
    private String _respuestaSimple;
    private String _respuestaMultiple;
    private PreguntaEstudioDto _preguntaEstudioDto;
    private UsuarioDto _usuarioDto;

    public String getRespuestaAbierta() {
        return _respuestaAbierta;
    }

    public void setRespuestaAbierta(String respuestaAbierta) {
        this._respuestaAbierta = respuestaAbierta;
    }

    public String getEscala() {
        return _escala;
    }

    public void setEscala(String escala) {
        this._escala = escala;
    }

    public String getVerdaderoFalso() {
        return _verdaderoFalso;
    }

    public void setVerdaderoFalso(String verdaderoFalso) {
        this._verdaderoFalso = verdaderoFalso;
    }

    public String getRespuestaSimple() {
        return _respuestaSimple;
    }

    public void setRespuestaSimple(String respuestaSimple) {
        this._respuestaSimple = respuestaSimple;
    }

    public String getRespuestaMultiple() {
        return _respuestaMultiple;
    }

    public void setRespuestaMultiple(String respuestaMultiple) {
        this._respuestaMultiple = respuestaMultiple;
    }

    public PreguntaEstudioDto getPreguntaEstudioDto() {
        return _preguntaEstudioDto;
    }

    public void setPreguntaEstudioDto(PreguntaEstudioDto preguntaEstudioDto) {
        this._preguntaEstudioDto = preguntaEstudioDto;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this._usuarioDto = usuarioDto;
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