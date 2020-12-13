package ucab.dsw.dtos;

public class RespuestaDto extends DtoBase {
    private String respuestaAbierta;
    private String escala;
    private String verdaderoFalso;
    private String respuestaSimple;
    private String respuestaMultiple;
    private PreguntaEstudioDto preguntaEstudioDto;
    private UsuarioDto usuarioDto;

    public String getRespuestaAbierta() {
        return respuestaAbierta;
    }

    public void setRespuestaAbierta(String respuestaAbierta) {
        this.respuestaAbierta = respuestaAbierta;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getVerdaderoFalso() {
        return verdaderoFalso;
    }

    public void setVerdaderoFalso(String verdaderoFalso) {
        this.verdaderoFalso = verdaderoFalso;
    }

    public String getRespuestaSimple() {
        return respuestaSimple;
    }

    public void setRespuestaSimple(String respuestaSimple) {
        this.respuestaSimple = respuestaSimple;
    }

    public String getRespuestaMultiple() {
        return respuestaMultiple;
    }

    public void setRespuestaMultiple(String respuestaMultiple) {
        this.respuestaMultiple = respuestaMultiple;
    }

    public PreguntaEstudioDto getPreguntaEstudioDto() {
        return preguntaEstudioDto;
    }

    public void setPreguntaEstudioDto(PreguntaEstudioDto preguntaEstudioDto) {
        this.preguntaEstudioDto = preguntaEstudioDto;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
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