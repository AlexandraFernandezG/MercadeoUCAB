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

    public JsonArrayBuilder estudios = Json.createArrayBuilder();

    @Override
    public void execute() throws PruebaExcepcion {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        List<Estudio> listaEstudios = daoEstudio.findAll(Estudio.class);
        ucab.dsw.servicio.SugerenciasServicio servicio = new ucab.dsw.servicio.SugerenciasServicio();
        JsonObject estudio;

        for(Estudio obj: listaEstudios){

            if (obj.get_observaciones() != null) {

                estudio = Json.createObjectBuilder().add("id", obj.get_id())
                        .add("nombre", obj.get_nombre())
                        .add("tipoInstrumento", obj.get_tipoInstrumento())
                        .add("observaciones", obj.get_observaciones())
                        .add("fechaInicio", servicio.devolverFecha(obj.get_fechaInicio()))
                        .add("fechaFin", servicio.devolverFecha(obj.get_fechaFin()))
                        .add("estado", obj.get_estado())
                        .add("estatus", obj.get_estatus()).build();

                estudios.add(estudio);

            } else {

                estudio = Json.createObjectBuilder().add("id", obj.get_id())
                        .add("nombre", obj.get_nombre())
                        .add("tipoInstrumento", obj.get_tipoInstrumento())
                        .add("observaciones", "")
                        .add("fechaInicio", servicio.devolverFecha(obj.get_fechaInicio()))
                        .add("fechaFin", servicio.devolverFecha(obj.get_fechaFin()))
                        .add("estado", obj.get_estado())
                        .add("estatus", obj.get_estatus()).build();

                estudios.add(estudio);

            }
        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder().add("mensaje","Todos los estudios listados")
                .add("estado",200)
                .add("Estudios", estudios).build();

        return resultado;
    }
}
