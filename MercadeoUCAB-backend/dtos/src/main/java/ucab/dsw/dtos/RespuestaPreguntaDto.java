package ucab.dsw.dtos;

public class RespuestaPreguntaDto extends DtoBase {

    private String _nombre;
    private PreguntaEncuestaDto _preguntaEncuestaDto;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public PreguntaEncuestaDto getPreguntaEncuestaDto() {
        return _preguntaEncuestaDto;
    }

    public void setPreguntaEncuestaDto(PreguntaEncuestaDto preguntaEncuestaDto) {
        this._preguntaEncuestaDto = preguntaEncuestaDto;
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