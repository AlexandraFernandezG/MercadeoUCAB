package ucab.dsw.dtos;

public class LugarDto extends DtoBase{
    private String _nombre;
    private String _tipo;
    private String _categoriaSocioEconomica;
    private LugarDto _lugar;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getCategoriaSocioEconomica() {
        return _categoriaSocioEconomica;
    }

    public void setCategoriaSocioEconomica(String _categoriaSocioEconomica) {
        this._categoriaSocioEconomica = _categoriaSocioEconomica;
    }

    public LugarDto getLugar() {
        return _lugar;
    }

    public void setLugar(LugarDto _lugar) {
        this._lugar = _lugar;
    }

    public String getTipo() {
        return _tipo;
    }

    public void setTipo(String _tipo) {
        this._tipo = _tipo;
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