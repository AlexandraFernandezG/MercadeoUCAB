package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Producto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoProducto extends Dao<Producto>{
    
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();
    
    public DaoProducto (){
        super(_handler);
    }

    public List<Object[]> listarProductosCliente(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT p._id as idProducto, p._nombre as nombre, p._descripcion as descripcion, p._estatus as estatus " +
                "FROM Producto as p, Usuario as u, Rol as r " +
                "WHERE p._usuario._id = u._id and u._rol._id = r._id and r._nombre = 'Cliente' and u._id = :id ";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<Object[]> listaProductos = query.getResultList();

        return listaProductos;

    }
    
}
