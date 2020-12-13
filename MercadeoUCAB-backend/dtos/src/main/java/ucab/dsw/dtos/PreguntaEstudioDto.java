package ucab.dsw.dtos;

public class PreguntaEstudioDto extends DtoBase{

    private PreguntaEncuestaDto preguntaEncuestaDto;
    private EstudioDto estudioDto;

    public PreguntaEncuestaDto getPreguntaEncuestaDto() {
        return preguntaEncuestaDto;
    }

    public void setPreguntaEncuestaDto(PreguntaEncuestaDto preguntaEncuestaDto) {
        this.preguntaEncuestaDto = preguntaEncuestaDto;
    }

    public EstudioDto getEstudioDto() {
        return estudioDto;
    }

    public void setEstudioDto(EstudioDto estudioDto) {
        this.estudioDto = estudioDto;
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