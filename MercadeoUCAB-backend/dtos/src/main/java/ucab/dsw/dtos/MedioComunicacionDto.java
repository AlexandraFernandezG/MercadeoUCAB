package ucab.dsw.dtos;

public class MedioComunicacionDto extends DtoBase {

    private String _tipoDeMedio;
    private InformacionDto _informacionDto;
    private SolicitudEstudioDto _solicitudEstudioDto;

    public InformacionDto getInformacionDto() {
        return _informacionDto;
    }

    public void setInformacionDto(InformacionDto _informacionDto) {
        this._informacionDto = _informacionDto;
    }

    public SolicitudEstudioDto getSolicitudEstudioDto() {
        return _solicitudEstudioDto;
    }

    public void setSolicitudEstudioDto(SolicitudEstudioDto _solicitudEstudioDto) {
        this._solicitudEstudioDto = _solicitudEstudioDto;
    }

    public String getTipoDeMedio() {
        return _tipoDeMedio;
    }

    public void setTipoDeMedio(String _tipoDeMedio) {
        this._tipoDeMedio = _tipoDeMedio;
    }

    public MedioComunicacionDto (long id) throws Exception{
        super(id);
    }

    public MedioComunicacionDto (String estatus) throws Exception {
        super(estatus);
    }

    public MedioComunicacionDto (){
        super();
    }
}