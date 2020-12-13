package ucab.dsw.dtos;

public class MedioComunicacionDto extends DtoBase {

    private String _tipoDeMedio;
    private InformacionDto _informacionDto;
    private SolicitudEstudioDto _solicitudEstudioDto;

    public String getTipoDeMedio() {
        return _tipoDeMedio;
    }

    public void setTipoDeMedio(String tipoDeMedio) {
        this._tipoDeMedio = tipoDeMedio;
    }

    public InformacionDto getInformacionDto() {
        return _informacionDto;
    }

    public void setInformacionDto(InformacionDto informacionDto) {
        this._informacionDto = informacionDto;
    }

    public SolicitudEstudioDto getSolicitudEstudioDto() {
        return _solicitudEstudioDto;
    }

    public void setSolicitudEstudioDto(SolicitudEstudioDto solicitudEstudioDto) {
        this._solicitudEstudioDto = solicitudEstudioDto;
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