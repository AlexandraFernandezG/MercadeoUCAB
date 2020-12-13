package ucab.dsw.dtos;

public class MedioComunicacionDto extends DtoBase {

    private String tipoDeMedio;
    private InformacionDto informacionDto;
    private SolicitudEstudioDto solicitudEstudioDto;

    public String getTipoDeMedio() {
        return tipoDeMedio;
    }

    public void setTipoDeMedio(String tipoDeMedio) {
        this.tipoDeMedio = tipoDeMedio;
    }

    public InformacionDto getInformacionDto() {
        return informacionDto;
    }

    public void setInformacionDto(InformacionDto informacionDto) {
        this.informacionDto = informacionDto;
    }

    public SolicitudEstudioDto getSolicitudEstudioDto() {
        return solicitudEstudioDto;
    }

    public void setSolicitudEstudioDto(SolicitudEstudioDto solicitudEstudioDto) {
        this.solicitudEstudioDto = solicitudEstudioDto;
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