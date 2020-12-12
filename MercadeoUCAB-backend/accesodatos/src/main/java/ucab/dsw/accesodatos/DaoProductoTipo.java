package ucab.dsw.accesodatos;

import ucab.dsw.entidades.ProductoTipo;
import javax.persistence.EntityManager;

public class DaoProductoTipo extends Dao<ProductoTipo>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoProductoTipo (){
        super (_handler);
    }
}
