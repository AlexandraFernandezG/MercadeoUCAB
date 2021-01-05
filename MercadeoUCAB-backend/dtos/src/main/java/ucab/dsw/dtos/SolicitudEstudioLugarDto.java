package ucab.dsw.dtos;

public class SolicitudEstudioLugarDto extends DtoBase{

    private SolicitudEstudioDto _SolicitudestudioDto;
    private LugarDto _lugarDto;

    public SolicitudEstudioDto getSolicitudestudioDto() {
        return _SolicitudestudioDto;
    }

    public void setSolicitudestudioDto(SolicitudEstudioDto _SolicitudestudioDto) {
        this._SolicitudestudioDto = _SolicitudestudioDto;
    }

    public LugarDto getLugarDto() {
        return _lugarDto;
    }

    public void setLugarDto(LugarDto _lugarDto) {
        this._lugarDto = _lugarDto;
    }

    public SolicitudEstudioLugarDto(long id) throws Exception {
        super(id);
    }

    public SolicitudEstudioLugarDto(String estatus) throws Exception {
        super(estatus);
    }

    public SolicitudEstudioLugarDto(){
        super();
    }
}
