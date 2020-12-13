package ucab.dsw.dtos;

import java.util.Date;

public class HijoDto extends DtoBase{
    private Date _fechaNacimiento;
    private String _genero;
    private InformacionDto _informacionDto;

    public Date getFechaNacimiento() {
        return _fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this._fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return _genero;
    }

    public void setGenero(String genero) {
        this._genero = genero;
    }

    public InformacionDto getInformacionDto() {
        return _informacionDto;
    }

    public void setInformacionDto(InformacionDto informacionDto) {
        this._informacionDto = informacionDto;
    }

    public HijoDto (long id) throws Exception{
        super(id);
    }

    public HijoDto (String estatus) throws Exception {
        super(estatus);
    }

    public HijoDto (){
        super();
    }
}