package ucab.dsw.dtos;

import java.util.Date;

public class HijoDto extends DtoBase{
    private Date fechaNacimiento;
    private String genero;
    private InformacionDto informacionDto;

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public InformacionDto getInformacionDto() {
        return informacionDto;
    }

    public void setInformacionDto(InformacionDto informacionDto) {
        this.informacionDto = informacionDto;
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