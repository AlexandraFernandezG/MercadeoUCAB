package ucab.dsw.dtos;

public class RespuestaPreguntaDto extends DtoBase {

    private String _nombre;
    private PreguntaEncuestaDto _preguntaEncuestaDto;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public PreguntaEncuestaDto getPreguntaEncuestaDto() {
        return _preguntaEncuestaDto;
    }

    public void setPreguntaEncuestaDto(PreguntaEncuestaDto _preguntaEncuestaDto) {
        this._preguntaEncuestaDto = _preguntaEncuestaDto;
    }

    public RespuestaPreguntaDto (long id) throws Exception{
        super(id);
    }

    public RespuestaPreguntaDto (String estatus) throws Exception {
        super(estatus);
    }

    public RespuestaPreguntaDto (){
        super();
    }
}