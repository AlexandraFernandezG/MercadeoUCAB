package ucab.dsw.dtos;

import java.util.Date;

public class SolicitudEstudioDto extends DtoBase{

    private String descripcion;
    private String genero;
    private int edadMinima;
    private int edadMaxima;
    private String estadoCivil;
    private String disponibilidadEnLinea;
    private int cantidadPersonas;
    private int cantidadHijos;
    private String generoHijos;
    private int edadMinimaHijos;
    private int edadMaximaHijos;
    private UsuarioDto usuarioDto;
    private NivelEconomicoDto nivelEconomicoDto;
    private ProductoDto productoDto;
    private OcupacionDto ocupacionDto;
    private NivelAcademicoDto nivelAcademicoDto;
    
    //Getters, Setters, y otros metodos.

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(int edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDisponibilidadEnLinea() {
        return disponibilidadEnLinea;
    }

    public void setDisponibilidadEnLinea(String disponibilidadEnLinea) {
        this.disponibilidadEnLinea = disponibilidadEnLinea;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public int getCantidadHijos() {
        return cantidadHijos;
    }

    public void setCantidadHijos(int cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    public String getGeneroHijos() {
        return generoHijos;
    }

    public void setGeneroHijos(String generoHijos) {
        this.generoHijos = generoHijos;
    }

    public int getEdadMinimaHijos() {
        return edadMinimaHijos;
    }

    public void setEdadMinimaHijos(int edadMinimaHijos) {
        this.edadMinimaHijos = edadMinimaHijos;
    }

    public int getEdadMaximaHijos() {
        return edadMaximaHijos;
    }

    public void setEdadMaximaHijos(int edadMaximaHijos) {
        this.edadMaximaHijos = edadMaximaHijos;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public NivelEconomicoDto getNivelEconomicoDto() {
        return nivelEconomicoDto;
    }

    public void setNivelEconomicoDto(NivelEconomicoDto nivelEconomicoDto) {
        this.nivelEconomicoDto = nivelEconomicoDto;
    }

    public ProductoDto getProductoDto() {
        return productoDto;
    }

    public void setProductoDto(ProductoDto productoDto) {
        this.productoDto = productoDto;
    }

    public OcupacionDto getOcupacionDto() {
        return ocupacionDto;
    }

    public void setOcupacionDto(OcupacionDto ocupacionDto) {
        this.ocupacionDto = ocupacionDto;
    }

    public NivelAcademicoDto getNivelAcademicoDto() {
        return nivelAcademicoDto;
    }

    public void setNivelAcademicoDto(NivelAcademicoDto nivelAcademicoDto) {
        this.nivelAcademicoDto = nivelAcademicoDto;
    }

    public SolicitudEstudioDto (long id) throws Exception{
        super(id);
    }

    public SolicitudEstudioDto (String estatus) throws Exception {
        super(estatus);
    }

    public SolicitudEstudioDto (){
        super();
    }
    
}
