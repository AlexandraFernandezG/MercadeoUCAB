package ucab.dsw.dtos;

public class EstudioLugarDto extends DtoBase{

    private EstudioDto _estudioDto;
    private LugarDto _lugarDto;

    public EstudioDto getEstudioDto() {
        return _estudioDto;
    }

    public void setEstudioDto(EstudioDto _estudioDto) {
        this._estudioDto = _estudioDto;
    }

    public LugarDto getLugarDto() {
        return _lugarDto;
    }

    public void setLugarDto(LugarDto _lugarDto) {
        this._lugarDto = _lugarDto;
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
