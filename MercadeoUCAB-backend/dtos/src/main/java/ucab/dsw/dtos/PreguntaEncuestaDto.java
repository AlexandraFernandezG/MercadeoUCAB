package ucab.dsw.dtos;


public class PreguntaEncuestaDto extends DtoBase {

    private String _descripcion;
    private String _tipoPregunta;
    private UsuarioDto _usuarioDto;
    private SubcategoriaDto _subcategoriaDto;

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String descripcion) {
        this._descripcion = descripcion;
    }

    public String getTipoPregunta() {
        return _tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this._tipoPregunta = tipoPregunta;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this._usuarioDto = usuarioDto;
    }

    public SubcategoriaDto getSubcategoriaDto() {
        return _subcategoriaDto;
    }

    public void setSubcategoriaDto(SubcategoriaDto subcategoriaDto) {
        this._subcategoriaDto = subcategoriaDto;
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
