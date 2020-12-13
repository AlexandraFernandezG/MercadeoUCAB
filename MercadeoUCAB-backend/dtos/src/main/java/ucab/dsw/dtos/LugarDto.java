package ucab.dsw.dtos;

public class LugarDto extends DtoBase{

    private String _nombre;
    private String _tipo;
    private String _categoriaSocioEconomica;
    private LugarDto _lugar;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public String getTipo() {
        return _tipo;
    }

    public void setTipo(String tipo) {
        this._tipo = tipo;
    }

    public String getCategoriaSocioEconomica() {
        return _categoriaSocioEconomica;
    }

    public void setCategoriaSocioEconomica(String categoriaSocioEconomica) {
        this._categoriaSocioEconomica = categoriaSocioEconomica;
    }

    public LugarDto getLugar() {
        return _lugar;
    }

    public void setLugar(LugarDto lugar) {
        this._lugar = lugar;
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