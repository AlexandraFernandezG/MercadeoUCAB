package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class ColocarObservacionAnalistaComando extends ComandoBase {

    public long id;
    public String observacion;
    JsonObject dataObject;

    public ColocarObservacionAnalistaComando(long id, String observacion) {
        this.id = id;
        this.observacion = observacion;
    }

    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        Estudio estudioObservacion = daoEstudio.find(id, Estudio.class);
        estudioObservacion.set_observaciones(observacion);
        daoEstudio.update(estudioObservacion);

        dataObject = Json.createObjectBuilder()
                .add("estado", 200)
                .add("Mensaje", "Operacion realizada con exito").build();

    }

    @Override
    public JsonObject getResult() {

        return dataObject;
    }
}
