package ucab.dsw.dtos;


public class PreguntaEncuestaDto extends DtoBase {

    private String descripcion;
    private String tipoPregunta;
    private UsuarioDto usuarioDto;
    private SubcategoriaDto subcategoriaDto;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public SubcategoriaDto getSubcategoriaDto() {
        return subcategoriaDto;
    }

    public void setSubcategoriaDto(SubcategoriaDto subcategoriaDto) {
        this.subcategoriaDto = subcategoriaDto;
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
