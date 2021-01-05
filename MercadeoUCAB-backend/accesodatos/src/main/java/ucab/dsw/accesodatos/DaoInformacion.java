package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Informacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoInformacion extends Dao<Informacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    String SQL = null;
    Query query = null;

    public DaoInformacion (){
        super (_handler);
    }

    public List<Informacion> obtenerInformacion(int cedula){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager em = emf.createEntityManager();
        SQL = "SELECT if._id, if._primerNombre, if._primerApellido FROM Informacion if WHERE if._cedula = :cedula";
        query = em.createQuery(SQL);
        query.setParameter("cedula", cedula);

        return query.getResultList();
    }
}
