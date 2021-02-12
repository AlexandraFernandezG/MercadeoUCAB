package ucab.dsw.comando.UsuarioEstudio;

import ucab.dsw.Response.EncuestadosEstudiosEstadoResponse;
import ucab.dsw.Response.EstudiosResponse;
import ucab.dsw.accesodatos.DaoUsuarioEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstudiosEncuestadoComando extends ComandoBase {

    public long id;
    public JsonArrayBuilder estudios = Json.createArrayBuilder();
    public JsonObject estudioJson;

    public EstudiosEncuestadoComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoUsuarioEstudio daoUsuarioEstudio = Fabrica.crear(DaoUsuarioEstudio.class);
        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);

        List<Object[]> listaUsuarioEstudios = daoUsuarioEstudio.listarEstudiosEncuestado(id);

        List<EncuestadosEstudiosEstadoResponse> listaEstudiosEncuestado = new ArrayList<>(listaUsuarioEstudios.size());

        for (Object[] est : listaUsuarioEstudios) {

            listaEstudiosEncuestado.add(new EncuestadosEstudiosEstadoResponse((long)est[0], (String)est[1], (String)est[2], (String)est[3], servicio.devolverFecha((Date)est[4]), servicio.devolverFecha((Date)est[5]), (String)est[6], (String)est[7], (String)est[8]));
        }

        for (EncuestadosEstudiosEstadoResponse obj: listaEstudiosEncuestado){

            if (obj.getObservacionesEstudio() != null && obj.getFechaFinEstudio() != null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.getIdEstudio())
                        .add("nombre", obj.getNombreEstudio())
                        .add("tipoInstrumento", obj.getTipoInstrumentoEstudio())
                        .add("observaciones", obj.getObservacionesEstudio())
                        .add("fechaInicio", obj.getFechaInicioEstudio())
                        .add("fechaFin", obj.getFechaFinEstudio())
                        .add("estado", obj.getEstadoEstudio())
                        .add("estatus", obj.getEstatusEstudio())
                        .add("EstadoEncuestado", obj.getEstadoUsuarioEstudio()).build();

                estudios.add(estudioJson);

            } else if (obj.getObservacionesEstudio() == null && obj.getFechaFinEstudio() == null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.getIdEstudio())
                        .add("nombre", obj.getNombreEstudio())
                        .add("tipoInstrumento", obj.getTipoInstrumentoEstudio())
                        .add("observaciones", "")
                        .add("fechaInicio", obj.getFechaInicioEstudio())
                        .add("fechaFin", "")
                        .add("estado", obj.getEstadoEstudio())
                        .add("estatus", obj.getEstatusEstudio())
                        .add("EstadoEncuestado", obj.getEstadoUsuarioEstudio()).build();

                estudios.add(estudioJson);

            } else if (obj.getObservacionesEstudio() != null && obj.getFechaFinEstudio() == null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.getIdEstudio())
                        .add("nombre", obj.getNombreEstudio())
                        .add("tipoInstrumento", obj.getTipoInstrumentoEstudio())
                        .add("observaciones", obj.getObservacionesEstudio())
                        .add("fechaInicio", obj.getFechaInicioEstudio())
                        .add("fechaFin", "")
                        .add("estado", obj.getEstadoEstudio())
                        .add("estatus", obj.getEstatusEstudio())
                        .add("EstadoEncuestado", obj.getEstadoUsuarioEstudio()).build();

                estudios.add(estudioJson);

            } else if (obj.getObservacionesEstudio() == null && obj.getFechaFinEstudio() != null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.getIdEstudio())
                        .add("nombre", obj.getNombreEstudio())
                        .add("tipoInstrumento", obj.getTipoInstrumentoEstudio())
                        .add("observaciones", "")
                        .add("fechaInicio", obj.getFechaInicioEstudio())
                        .add("fechaFin", obj.getFechaFinEstudio())
                        .add("estado", obj.getEstadoEstudio())
                        .add("estatus", obj.getEstatusEstudio())
                        .add("EstadoEncuestado", obj.getEstadoUsuarioEstudio()).build();

                estudios.add(estudioJson);

            }
        }
    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Estudios", estudios).build();

        return resultado;
    }
}
