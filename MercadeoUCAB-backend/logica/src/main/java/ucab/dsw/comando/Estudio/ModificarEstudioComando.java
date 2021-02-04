package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class ModificarEstudioComando extends ComandoBase {

    public long id;
    public Estudio estudio;
    public JsonObject estudioObj;

    public ModificarEstudioComando(long id, Estudio estudio) {

        this.id = id;
        this.estudio = estudio;
    }

    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        ucab.dsw.servicio.SugerenciasServicio servicio = new ucab.dsw.servicio.SugerenciasServicio();

        Estudio estudio_modificar = daoEstudio.find(id, Estudio.class);

        estudio_modificar.set_nombre(estudio.get_nombre());
        estudio_modificar.set_tipoInstrumento(estudio.get_tipoInstrumento());
        estudio_modificar.set_observaciones(estudio.get_observaciones());
        estudio_modificar.set_fechaInicio(estudio.get_fechaInicio());
        estudio_modificar.set_fechaFin(estudio.get_fechaFin());
        estudio_modificar.set_estado(estudio.get_estado());
        estudio_modificar.set_estatus(estudio.get_estatus());
        daoEstudio.update(estudio_modificar);

        estudioObj = Json.createObjectBuilder().add("id",estudio_modificar.get_id())
                .add("nombre", estudio_modificar.get_nombre())
                .add("tipoInstrumento", estudio_modificar.get_tipoInstrumento())
                .add("observaciones", estudio_modificar.get_observaciones())
                .add("fechaInicio", servicio.devolverFecha(estudio_modificar.get_fechaInicio()))
                .add("fechaFin", servicio.devolverFecha(estudio_modificar.get_fechaFin()))
                .add("estado", estudio_modificar.get_estado())
                .add("estatus", estudio_modificar.get_estatus()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Modificado el estudio escogido")
                .add("Estudio", estudioObj).build();

        return resultado;
    }
}
