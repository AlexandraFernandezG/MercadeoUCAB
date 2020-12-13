package ucab.dsw.dtos;

public class EstudioLugarDto extends DtoBase{

    private EstudioDto estudioDto;
    private LugarDto lugarDto;

    public EstudioDto getEstudioDto() {
        return estudioDto;
    }

    public void setEstudioDto(EstudioDto estudioDto) {
        this.estudioDto = estudioDto;
    }

    public LugarDto getLugarDto() {
        return lugarDto;
    }

    public void setLugarDto(LugarDto lugarDto) {
        this.lugarDto = lugarDto;
    }

    public EstudioLugarDto(long id) throws Exception {
        super(id);
    }

    public EstudioLugarDto (String estatus) throws Exception {
        super(estatus);
    }

    public EstudioLugarDto (){
        super();
    }
}
