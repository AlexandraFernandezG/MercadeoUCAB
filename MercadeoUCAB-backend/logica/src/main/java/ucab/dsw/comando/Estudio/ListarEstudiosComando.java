package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ListarEstudiosComando extends ComandoBase {

    public JsonObject estudioJson;
    public JsonArrayBuilder estudios = Json.createArrayBuilder();

    @Override
    public void execute() throws PruebaExcepcion {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        List<Estudio> listaEstudios = daoEstudio.findAll(Estudio.class);
        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);
        JsonObject estudio;

        for(Estudio obj: listaEstudios){

            if (obj.get_observaciones() != null && obj.get_fechaFin() != null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.get_id())
                        .add("nombre", obj.get_nombre())
                        .add("tipoInstrumento", obj.get_tipoInstrumento())
                        .add("observaciones", obj.get_observaciones())
                        .add("fechaInicio", servicio.devolverFecha(obj.get_fechaInicio()))
                        .add("fechaFin", servicio.devolverFecha(obj.get_fechaFin()))
                        .add("estado", obj.get_estado())
                        .add("estatus", obj.get_estatus()).build();

                estudios.add(estudioJson);

            } else if (obj.get_observaciones() == null && obj.get_fechaFin() == null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.get_id())
                        .add("nombre", obj.get_nombre())
                        .add("tipoInstrumento", "")
                        .add("observaciones", obj.get_observaciones())
                        .add("fechaInicio", servicio.devolverFecha(obj.get_fechaInicio()))
                        .add("fechaFin", "")
                        .add("estado", obj.get_estado())
                        .add("estatus", obj.get_estatus()).build();

                estudios.add(estudioJson);

            } else if (obj.get_observaciones() != null && obj.get_fechaFin() == null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.get_id())
                        .add("nombre", obj.get_nombre())
                        .add("tipoInstrumento", obj.get_tipoInstrumento())
                        .add("observaciones", obj.get_observaciones())
                        .add("fechaInicio", servicio.devolverFecha(obj.get_fechaInicio()))
                        .add("fechaFin", "")
                        .add("estado", obj.get_estado())
                        .add("estatus", obj.get_estatus()).build();

                estudios.add(estudioJson);

            } else if (obj.get_observaciones() == null && obj.get_fechaFin() != null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.get_id())
                        .add("nombre", obj.get_nombre())
                        .add("tipoInstrumento", obj.get_tipoInstrumento())
                        .add("observaciones", "")
                        .add("fechaInicio", servicio.devolverFecha(obj.get_fechaInicio()))
                        .add("fechaFin", servicio.devolverFecha(obj.get_fechaFin()))
                        .add("estado", obj.get_estado())
                        .add("estatus", obj.get_estatus()).build();

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
