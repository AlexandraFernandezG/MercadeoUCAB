package ucab.dsw.dtos;

import ucab.dsw.dtos.DtoBase;
import ucab.dsw.dtos.InformacionDto;

public class TelefonoDto extends DtoBase {

    private int numero;
    private InformacionDto informacionDto;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public InformacionDto getInformacionDto() {
        return informacionDto;
    }

    public void setInformacionDto(InformacionDto informacionDto) {
        this.informacionDto = informacionDto;
    }

    public TelefonoDto (long id) throws Exception{
        super(id);
    }

    public TelefonoDto (String estatus) throws Exception {
        super(estatus);
    }

    public TelefonoDto (){
        super();
    }
}