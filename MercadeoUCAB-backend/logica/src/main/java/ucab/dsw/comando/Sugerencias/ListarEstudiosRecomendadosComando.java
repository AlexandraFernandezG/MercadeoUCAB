package ucab.dsw.comando.Sugerencias;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ListarEstudiosRecomendadosComando extends ComandoBase {

    public long id;
    public JsonArrayBuilder estudios = Json.createArrayBuilder();

    public ListarEstudiosRecomendadosComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        JsonObject estudioJson;
        ucab.dsw.servicio.SugerenciasServicio servicio = new ucab.dsw.servicio.SugerenciasServicio();
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        //Variables para poder hacer el macth
        String genero = solicitudEstudio.get_genero();
        String estadoCivil = solicitudEstudio.get_estadoCivil();
        int cantidadPersonas = solicitudEstudio.get_cantidadPersonas();
        int edadMaxima = solicitudEstudio.get_edadMaxima();
        int edadMinima = solicitudEstudio.get_edadMinima();

        //Listar todos los estudios e informaciones de los encuestados
        List<Estudio> listaEstudios = daoEstudio.findAll(Estudio.class);
        List<SolicitudEstudio> listaSolicitudes = daoSolicitudEstudio.findAll(SolicitudEstudio.class);
        List<SolicitudEstudio> listaAuxSolicitudes = new ArrayList<>();
        List<Estudio> listaEstudiosRecomendados = new ArrayList<>();

        for(SolicitudEstudio solicitudEstudioRemove: listaSolicitudes){

            if(solicitudEstudioRemove.get_id() != id){

                listaAuxSolicitudes.add(solicitudEstudioRemove);
            }
        }

        for (SolicitudEstudio solicitudEstudio_macth: listaAuxSolicitudes){

            if(genero.equals(solicitudEstudio_macth.get_genero()) && estadoCivil.equals(solicitudEstudio_macth.get_estadoCivil()) &&
                    cantidadPersonas == solicitudEstudio_macth.get_cantidadPersonas() && edadMaxima == solicitudEstudio_macth.get_edadMaxima() &&
                    edadMinima == solicitudEstudio_macth.get_edadMinima()){

                for (Estudio estudio: listaEstudios){

                    if(estudio.get_solicitudEstudio().get_id() == solicitudEstudio_macth.get_id()){

                        listaEstudiosRecomendados.add(estudio);
                    }
                }
            }
        }

        for (Estudio obj : listaEstudiosRecomendados) {

            if(obj.get_observaciones() != null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.get_id())
                        .add("nombre", obj.get_nombre())
                        .add("tipoInstrumento", obj.get_tipoInstrumento())
                        .add("observaciones", obj.get_observaciones())
                        .add("fechaInicio", servicio.devolverFecha(obj.get_fechaInicio()))
                        .add("fechaFin", servicio.devolverFecha(obj.get_fechaFin()))
                        .add("estado", obj.get_estado())
                        .add("estatus", obj.get_estatus()).build();

                estudios.add(estudioJson);

            } else {

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

        JsonObject resultado = Json.createObjectBuilder().add("mensaje","Todos los estudios recomendados")
                .add("estado",200)
                .add("Estudios", estudios).build();

        return resultado;
    }
}
