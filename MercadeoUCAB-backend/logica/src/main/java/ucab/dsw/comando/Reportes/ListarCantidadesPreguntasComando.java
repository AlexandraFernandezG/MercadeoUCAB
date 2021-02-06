package ucab.dsw.comando.Reportes;

import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoRespuestaPregunta;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.PreguntaEstudio;
import ucab.dsw.entidades.RespuestaPregunta;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ListarCantidadesPreguntasComando extends ComandoBase {

    public long id;
    JsonObject dataObject;
    public JsonArrayBuilder builder = Json.createArrayBuilder();

    public ListarCantidadesPreguntasComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        JsonArrayBuilder cantidades =Json.createArrayBuilder();
        long cantidad_opcion = 0;
        long verdadero_result = 0;
        long falso_result = 0;
        long escala_uno = 0;
        long escala_dos = 0;
        long escala_tres = 0;
        long escala_cuatro = 0;
        long escala_cinco = 0;

        //Listar todas las preguntas del estudio seleccionado
        DaoPreguntaEstudio daoPreguntaEstudio = Fabrica.crear(DaoPreguntaEstudio.class);
        List<PreguntaEstudio> listaEstudioPregunta = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
        List<PreguntaEncuesta> listaPreguntasEstudio = new ArrayList<PreguntaEncuesta>();

        for (PreguntaEstudio preguntaEstudio : listaEstudioPregunta) {

            if (preguntaEstudio.get_estudio().get_id() == id) {

                long idFk = preguntaEstudio.get_preguntaEncuesta().get_id();
                DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);
                PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(idFk, PreguntaEncuesta.class);
                listaPreguntasEstudio.add(preguntaEncuesta);
            }
        }

        //Recorrer cada pregunta del estudio y obtener las cantidades
        DaoRespuesta daoRespuesta = Fabrica.crear(DaoRespuesta.class);
        DaoRespuestaPregunta daoRespuestaPregunta = Fabrica.crear(DaoRespuestaPregunta.class);
        List<RespuestaPregunta> listaRespuestasPregunta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);
        List<Long> cantidadOpcion = new ArrayList<>();
        List<Long> cantidadOpcion2 = new ArrayList<>();
        for (PreguntaEncuesta preguntaEncuestaEstudio: listaPreguntasEstudio){


            if(preguntaEncuestaEstudio.get_tipoPregunta().equals("Verdadero o Falso")){

                List<Long> verdaderos = daoRespuesta.cantidadVerdaderosPregunta(preguntaEncuestaEstudio.get_id());
                List<Long> falsos = daoRespuesta.cantidadFalsosPregunta(preguntaEncuestaEstudio.get_id());
                verdadero_result = verdaderos.get(0);
                falso_result = falsos.get(0);
                cantidades.add(Json.createArrayBuilder().add("Verdadero").add(verdadero_result).add("Falso").add(falso_result));
                verdaderos.clear();
                falsos.clear();

            }
            else if (preguntaEncuestaEstudio.get_tipoPregunta().equals("Escala")){

                List<Long> uno = daoRespuesta.cantidadEscalaUno(preguntaEncuestaEstudio.get_id());
                List<Long> dos = daoRespuesta.cantidadEscalaDos(preguntaEncuestaEstudio.get_id());
                List<Long> tres = daoRespuesta.cantidadEscalaTres(preguntaEncuestaEstudio.get_id());
                List<Long> cuatro = daoRespuesta.cantidadEscalaCuatro(preguntaEncuestaEstudio.get_id());
                List<Long> cinco = daoRespuesta.cantidadEscalaCinco(preguntaEncuestaEstudio.get_id());

                escala_uno = uno.get(0);
                escala_dos = dos.get(0);
                escala_tres = tres.get(0);
                escala_cuatro = cuatro.get(0);
                escala_cinco = cinco.get(0);

                cantidades.add(Json.createArrayBuilder().add("1").add(escala_uno).add("2").add(escala_dos)
                        .add("3").add(escala_tres).add("4").add(escala_cuatro).add("5").add(escala_cinco));

                uno.clear();
                dos.clear();
                tres.clear();
                cuatro.clear();
                cinco.clear();

            }

            else if (preguntaEncuestaEstudio.get_tipoPregunta().equals("Selección Simple") || preguntaEncuestaEstudio.get_tipoPregunta().equals("Selección Múltiple")){

                for (RespuestaPregunta respuestaPregunta: listaRespuestasPregunta){

                    if(respuestaPregunta.get_preguntaEncuesta().get_id() == preguntaEncuestaEstudio.get_id() && preguntaEncuestaEstudio.get_tipoPregunta().equals("Selección Simple")){

                        cantidadOpcion = daoRespuesta.cantidadSimple(preguntaEncuestaEstudio.get_id(), respuestaPregunta.get_nombre());
                        cantidad_opcion = cantidadOpcion.get(0);
                        cantidades.add(Json.createArrayBuilder().add(respuestaPregunta.get_nombre()).add(cantidad_opcion));
                        cantidadOpcion.clear();

                    } else if(respuestaPregunta.get_preguntaEncuesta().get_id() == preguntaEncuestaEstudio.get_id() && preguntaEncuestaEstudio.get_tipoPregunta().equals("Selección Múltiple")){

                        cantidadOpcion2 = daoRespuesta.cantidadMultiple(preguntaEncuestaEstudio.get_id(), respuestaPregunta.get_nombre());
                        cantidad_opcion = cantidadOpcion2.get(0);
                        cantidades.add(Json.createArrayBuilder().add(respuestaPregunta.get_nombre()).add(cantidad_opcion));
                        cantidadOpcion2.clear();
                    }

                }

            }

            JsonObject pregunta = Json.createObjectBuilder()
                    .add("pregunta", preguntaEncuestaEstudio.get_descripcion())
                    .add("tipo_pregunta", preguntaEncuestaEstudio.get_tipoPregunta())
                    .add("resultado", cantidades).build();

            builder.add(pregunta);

        }

    }

    @Override
    public JsonObject getResult() {

        dataObject = Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Lista de cantidades por pregunta")
                .add("Preguntas", builder).build();

        return dataObject;
    }
}
