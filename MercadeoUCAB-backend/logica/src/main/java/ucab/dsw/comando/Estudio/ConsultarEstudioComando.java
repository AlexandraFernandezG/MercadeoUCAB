package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ConsultarEstudioComando extends ComandoBase {

    public JsonObject estudioObj;
    public long id;

    public ConsultarEstudioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws PruebaExcepcion {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        Estudio estudio_consultado = daoEstudio.find(id, Estudio.class);
        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);

        if(estudio_consultado.get_observaciones() != null) {

            estudioObj = Json.createObjectBuilder().add("id", estudio_consultado.get_id())
                    .add("nombre", estudio_consultado.get_nombre())
                    .add("tipoInstrumento", estudio_consultado.get_tipoInstrumento())
                    .add("observaciones", estudio_consultado.get_observaciones())
                    .add("fechaInicio", servicio.devolverFecha(estudio_consultado.get_fechaInicio()))
                    .add("fechaFin", servicio.devolverFecha(estudio_consultado.get_fechaFin()))
                    .add("estado", estudio_consultado.get_estado())
                    .add("estatus", estudio_consultado.get_estatus()).build();

        } else {

            estudioObj = Json.createObjectBuilder().add("id", estudio_consultado.get_id())
                    .add("nombre", estudio_consultado.get_nombre())
                    .add("tipoInstrumento", estudio_consultado.get_tipoInstrumento())
                    .add("observaciones", "")
                    .add("fechaInicio", servicio.devolverFecha(estudio_consultado.get_fechaInicio()))
                    .add("fechaFin", servicio.devolverFecha(estudio_consultado.get_fechaFin()))
                    .add("estado", estudio_consultado.get_estado())
                    .add("estatus", estudio_consultado.get_estatus()).build();
        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("EstudioConsultado", estudioObj).build();

        return resultado;
    }
}
