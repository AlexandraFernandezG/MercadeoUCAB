package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Date;
import java.util.List;

public class ConsultarEstudioComando extends ComandoBase {

    public JsonObject estudioObj;
    public long idE;
    public long idU;

    public ConsultarEstudioComando(long idE, long idU) {
        this.idE = idE;
        this.idU = idU;
    }

    @Override
    public void execute() throws PruebaExcepcion {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        List<Object[]> estudio = daoEstudio.consultarEstudioCliente(idE, idU);

        for(Object[] obj: estudio) {

            if (obj[3] != null) {

                estudioObj = Json.createObjectBuilder().add("observacion", (String)obj[3]).build();

            } else {

                estudioObj = Json.createObjectBuilder().add("observacion", "").build();
            }

        }

    }

    @Override
    public JsonObject getResult() {

        return estudioObj;
    }
}
