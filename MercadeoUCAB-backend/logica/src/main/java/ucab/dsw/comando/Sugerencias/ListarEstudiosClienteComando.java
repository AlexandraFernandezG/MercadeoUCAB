package ucab.dsw.comando.Sugerencias;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.Response.EstudiosResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListarEstudiosClienteComando extends ComandoBase {

    public long id;
    public JsonArrayBuilder estudios = Json.createArrayBuilder();

    public ListarEstudiosClienteComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);
        JsonObject estudioJson;

        List<Object[]> listaEstudios = daoEstudio.listarEstudiosClientes(id);

        List<EstudiosResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

        for (Object[] est : listaEstudios) {

            listaEstudiosRecomendados.add(new EstudiosResponse((long) est[0], (String) est[1], (String) est[2], (String) est[3], servicio.devolverFecha((Date) est[4]), servicio.devolverFecha((Date) est[5]), (String) est[6], (String) est[7]));
        }

        for (EstudiosResponse obj : listaEstudiosRecomendados) {

            if (obj.getObservacionesEstudio() != null && obj.getFechaFinEstudio() != null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.getIdEstudio())
                        .add("nombre", obj.getNombreEstudio())
                        .add("tipoInstrumento", obj.getTipoInstrumentoEstudio())
                        .add("observaciones", obj.getObservacionesEstudio())
                        .add("fechaInicio", obj.getFechaInicioEstudio())
                        .add("fechaFin", obj.getFechaFinEstudio())
                        .add("estado", obj.getEstadoEstudio())
                        .add("estatus", obj.getEstatusEstudio()).build();

                estudios.add(estudioJson);

            } else if (obj.getObservacionesEstudio() == null && obj.getFechaFinEstudio() == null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.getIdEstudio())
                        .add("nombre", obj.getNombreEstudio())
                        .add("tipoInstrumento", obj.getTipoInstrumentoEstudio())
                        .add("observaciones", "")
                        .add("fechaInicio", obj.getFechaInicioEstudio())
                        .add("fechaFin", "")
                        .add("estado", obj.getEstadoEstudio())
                        .add("estatus", obj.getEstatusEstudio()).build();

                estudios.add(estudioJson);

            } else if (obj.getObservacionesEstudio() != null && obj.getFechaFinEstudio() == null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.getIdEstudio())
                        .add("nombre", obj.getNombreEstudio())
                        .add("tipoInstrumento", obj.getTipoInstrumentoEstudio())
                        .add("observaciones", obj.getObservacionesEstudio())
                        .add("fechaInicio", obj.getFechaInicioEstudio())
                        .add("fechaFin", "")
                        .add("estado", obj.getEstadoEstudio())
                        .add("estatus", obj.getEstatusEstudio()).build();

                estudios.add(estudioJson);

            } else if (obj.getObservacionesEstudio() == null && obj.getFechaFinEstudio() != null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.getIdEstudio())
                        .add("nombre", obj.getNombreEstudio())
                        .add("tipoInstrumento", obj.getTipoInstrumentoEstudio())
                        .add("observaciones", "")
                        .add("fechaInicio", obj.getFechaInicioEstudio())
                        .add("fechaFin", obj.getFechaFinEstudio())
                        .add("estado", obj.getEstadoEstudio())
                        .add("estatus", obj.getEstatusEstudio()).build();

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
