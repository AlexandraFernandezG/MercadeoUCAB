package ucab.dsw.dtos;

public class OcupacionDto extends DtoBase{

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public OcupacionDto (long id) throws Exception{
        super(id);
    }

    public OcupacionDto (String estatus) throws Exception {
        super(estatus);
    }

    public OcupacionDto (){
        super();
    }

}