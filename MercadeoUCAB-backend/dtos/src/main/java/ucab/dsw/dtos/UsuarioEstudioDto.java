package ucab.dsw.dtos;

public class UsuarioEstudioDto extends DtoBase {

    private UsuarioDto _usuarioDto;
    private EstudioDto _estudioDto;

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto _usuarioDto) {
        this._usuarioDto = _usuarioDto;
    }

    public EstudioDto getEstudioDto() {
        return _estudioDto;
    }

    public void setEstudioDto(EstudioDto _estudioDto) {
        this._estudioDto = _estudioDto;
    }

    public UsuarioEstudioDto(long id) throws Exception{
        super(id);
    }

    public UsuarioEstudioDto(String estatus) throws Exception {
        super(estatus);
    }

    public UsuarioEstudioDto(){
        super();
    }
}