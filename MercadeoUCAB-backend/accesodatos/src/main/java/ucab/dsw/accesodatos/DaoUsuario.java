package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoUsuario extends Dao<Usuario>
{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoUsuario( )
    {
        super( _handler );
    }

    public List<Object[]> listarEncuestados(long id){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        String sqlQuery = "SELECT DISTINCT R._id AS idRespuesta, U._id AS idUsuario, U._correoelectronico AS correo," +
                " U._nombre AS nombreUsuario FROM PreguntaEstudio AS PE, Respuesta AS R, Usuario U WHERE" +
                " PE._estudio._id = R._preguntaEstudio._id AND R._usuario._id = U._id AND " +
                "U._rol._id = 4 AND PE._estudio._id =:id ";

        Query query = entitymanager.createQuery( sqlQuery);
        query.setParameter("id", id);

        List<Object[]> estudioUsuarioResponseList = query.getResultList();

        return estudioUsuarioResponseList;
    }
}