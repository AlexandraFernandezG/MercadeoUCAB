package ucab.dsw.dtos;

public class RespuestaPreguntaDto extends DtoBase {

    private String nombre;
    private PreguntaEncuestaDto preguntaEncuestaDto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PreguntaEncuestaDto getPreguntaEncuestaDto() {
        return preguntaEncuestaDto;
    }

    public void setPreguntaEncuestaDto(PreguntaEncuestaDto preguntaEncuestaDto) {
        this.preguntaEncuestaDto = preguntaEncuestaDto;
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