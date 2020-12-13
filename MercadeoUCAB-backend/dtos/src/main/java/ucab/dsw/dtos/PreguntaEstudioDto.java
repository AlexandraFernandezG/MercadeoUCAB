package ucab.dsw.dtos;

public class PreguntaEstudioDto extends DtoBase{

    private PreguntaEncuestaDto _preguntaEncuestaDto;
    private EstudioDto _estudioDto;

    public PreguntaEncuestaDto getPreguntaEncuestaDto() {
        return _preguntaEncuestaDto;
    }

    public void setPreguntaEncuestaDto(PreguntaEncuestaDto _preguntaEncuestaDto) {
        this._preguntaEncuestaDto = _preguntaEncuestaDto;
    }

    public EstudioDto getEstudioDto() {
        return _estudioDto;
    }

    public void setEstudioDto(EstudioDto _estudioDto) {
        this._estudioDto = _estudioDto;
    }

    public PreguntaEstudioDto(long id) throws Exception{
        super(id);
    }

    public PreguntaEstudioDto (String estatus) throws Exception {
        super(estatus);
    }

    public PreguntaEstudioDto (){
        super();
    }
}