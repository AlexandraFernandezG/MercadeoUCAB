package ucab.dsw.dtos;

import ucab.dsw.dtos.DtoBase;
import ucab.dsw.dtos.InformacionDto;

public class TelefonoDto extends DtoBase {

    private int _numero;
    private InformacionDto _informacionDto;

    public int getNumero() {
        return _numero;
    }

    public void setNumero(int numero) {
        this._numero = numero;
    }

    public InformacionDto getInformacionDto() {
        return _informacionDto;
    }

    public void setInformacionDto(InformacionDto informacionDto) {
        this._informacionDto = informacionDto;
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