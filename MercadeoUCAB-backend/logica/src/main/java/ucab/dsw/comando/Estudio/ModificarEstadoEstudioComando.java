package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class ModificarEstadoEstudioComando extends ComandoBase {

    public long id;
    public JsonObject estudioObj;

    public ModificarEstadoEstudioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        ucab.dsw.servicio.SugerenciasServicio servicio = new ucab.dsw.servicio.SugerenciasServicio();

        Estudio estudio_modificar = daoEstudio.find(id, Estudio.class);

        if (estudio_modificar.get_estado().equals("En espera"))
            estudio_modificar.set_estado("En proceso");
        else if (estudio_modificar.get_estado().equals("En proceso"))
            estudio_modificar.set_estado("En Ejecución");
        else if (estudio_modificar.get_estado().equals("En Ejecución"))
            estudio_modificar.set_estado("Finalizado");

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
                .add("mensaje","Modificado el estado del estudio escogido")
                .add("Estudio", estudioObj).build();

        return resultado;

    }
}
