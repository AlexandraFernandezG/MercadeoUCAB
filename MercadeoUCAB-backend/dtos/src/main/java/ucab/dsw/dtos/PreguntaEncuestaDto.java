package ucab.dsw.dtos;


public class PreguntaEncuestaDto extends DtoBase {
    private String _descripcion;
    private String _tipoPregunta;
    private UsuarioDto _usuarioDto;
    private SubcategoriaDto _subcategoriaDto;

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public SubcategoriaDto getSubcategoriaDto() {
        return _subcategoriaDto;
    }

    public void setSubcategoriaDto(SubcategoriaDto _subcategoriaDto) {
        this._subcategoriaDto = _subcategoriaDto;
    }

    public String getTipoPregunta() {
        return _tipoPregunta;
    }

    public void setTipoPregunta(String _tipoPregunta) {
        this._tipoPregunta = _tipoPregunta;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto _usuarioDto) {
        this._usuarioDto = _usuarioDto;
    }

    public PreguntaEncuestaDto (long id) throws Exception{
        super(id);
    }

    public PreguntaEncuestaDto (String estatus) throws Exception {
        super(estatus);
    }

    public PreguntaEncuestaDto (){
        super();
    }
}
