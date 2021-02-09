package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class EliminarEstudioComando extends ComandoBase {

    public long id;
    public JsonObject estudioObj;

    public EliminarEstudioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);

        Estudio estudio_eliminar = daoEstudio.find(id, Estudio.class);
        daoEstudio.delete(estudio_eliminar);

         estudioObj = Json.createObjectBuilder().add("id",estudio_eliminar.get_id())
                .add("nombre", estudio_eliminar.get_nombre())
                .add("tipoInstrumento", estudio_eliminar.get_tipoInstrumento())
                .add("observaciones", estudio_eliminar.get_observaciones())
                .add("fechaInicio", servicio.devolverFecha(estudio_eliminar.get_fechaInicio()))
                .add("fechaFin", servicio.devolverFecha(estudio_eliminar.get_fechaFin()))
                .add("estado", estudio_eliminar.get_estado())
                .add("estatus", estudio_eliminar.get_estatus()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Eliminado el estudio escogido")
                .add("Eliminado", estudioObj).build();

        return resultado;
    }
}
