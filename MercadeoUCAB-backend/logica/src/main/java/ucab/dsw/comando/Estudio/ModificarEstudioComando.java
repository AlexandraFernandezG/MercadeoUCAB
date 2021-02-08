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
        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);

        Estudio estudio_modificar = daoEstudio.find(id, Estudio.class);

        estudio_modificar.set_nombre(estudio.get_nombre());
        estudio_modificar.set_tipoInstrumento(estudio.get_tipoInstrumento());
        estudio_modificar.set_observaciones(estudio.get_observaciones());
        estudio_modificar.set_fechaInicio(estudio.get_fechaInicio());
        estudio_modificar.set_fechaFin(estudio.get_fechaFin());
        estudio_modificar.set_estado(estudio.get_estado());
        estudio_modificar.set_estatus(estudio.get_estatus());
        daoEstudio.update(estudio_modificar);

        estudioObj = Json.createObjectBuilder().add("id",estudio_modificar.get_id()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Modificado el estudio escogido")
                .add("Id del estudio modificado", estudioObj).build();

        return resultado;
    }
}
