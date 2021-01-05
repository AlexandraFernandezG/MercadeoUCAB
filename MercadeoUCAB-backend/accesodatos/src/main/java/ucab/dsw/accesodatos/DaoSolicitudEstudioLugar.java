package ucab.dsw.accesodatos;

import javax.persistence.EntityManager;
import ucab.dsw.entidades.SolicitudEstudioLugar;

public class DaoSolicitudEstudioLugar extends Dao<SolicitudEstudioLugar>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoSolicitudEstudioLugar(){
        super(_handler);
    }
}
