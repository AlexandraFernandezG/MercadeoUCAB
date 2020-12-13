package ucab.dsw.dtos;

public class LugarDto extends DtoBase{

    private String nombre;
    private String tipo;
    private String categoriaSocioEconomica;
    private LugarDto lugar;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoriaSocioEconomica() {
        return categoriaSocioEconomica;
    }

    public void setCategoriaSocioEconomica(String categoriaSocioEconomica) {
        this.categoriaSocioEconomica = categoriaSocioEconomica;
    }

    public LugarDto getLugar() {
        return lugar;
    }

    public void setLugar(LugarDto lugar) {
        this.lugar = lugar;
    }

    public LugarDto (long id) throws Exception{
        super(id);
    }

    public LugarDto (String estatus) throws Exception {
        super(estatus);
    }

    public LugarDto (){
        super();
    }
}