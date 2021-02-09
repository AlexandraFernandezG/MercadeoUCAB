package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Telefono;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoTelefono extends Dao<Telefono>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoTelefono(){
        super (_handler);
    }

    public List<String> telefonosEncuestado(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT tel._numero FROM Telefono as tel, Informacion as info " +
                "WHERE (:id = info._usuario._id) and (tel._informacion._id = info._id)";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<String> telefonos = query.getResultList();

        return telefonos;
    }

}
