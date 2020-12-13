package ucab.dsw.dtos;

public class RolDto extends DtoBase{

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RolDto (long id) throws Exception{
        super(id);
    }

    public RolDto (String estatus) throws Exception {
        super(estatus);
    }

    public RolDto (){
        super();
    }
}
