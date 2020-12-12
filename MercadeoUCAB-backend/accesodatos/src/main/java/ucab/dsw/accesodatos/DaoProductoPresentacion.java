package ucab.dsw.accesodatos;


import ucab.dsw.entidades.ProductoPresentacion;
import javax.persistence.EntityManager;

public class DaoProductoPresentacion extends Dao<ProductoPresentacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoProductoPresentacion (){
        super (_handler);
    }
}
