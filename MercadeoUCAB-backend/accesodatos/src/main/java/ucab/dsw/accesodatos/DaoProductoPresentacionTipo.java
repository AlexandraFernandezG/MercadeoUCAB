package ucab.dsw.accesodatos;

import ucab.dsw.entidades.ProductoPresentacionTipo;
import javax.persistence.EntityManager;

public class DaoProductoPresentacionTipo extends Dao<ProductoPresentacionTipo> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoProductoPresentacionTipo (){
        super(_handler);
    }

}
