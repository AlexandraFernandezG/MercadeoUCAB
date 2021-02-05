package ucab.dsw.comando.Sugerencias;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.PreguntaEstudio;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class InsertarEstudioRecomendadoComando extends ComandoBase {

    public long idER;
    public long idE;
    public JsonObject dataObject;

    public InsertarEstudioRecomendadoComando(long idER, long idE) {
        this.idER = idER;
        this.idE = idE;
    }

    /**
     * Este metodo debe ser revisado o cambiado por los dtos.
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);

        //Obtener las preguntas del estudio recomendado
        DaoPreguntaEstudio daoPreguntaEstudio = Fabrica.crear(DaoPreguntaEstudio.class);
        List<PreguntaEstudio> listaEstudioPregunta = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
        List<PreguntaEncuesta> listaPreguntasEstudio = new ArrayList<PreguntaEncuesta>();

        for (PreguntaEstudio preguntaEstudio : listaEstudioPregunta) {

            if (preguntaEstudio.get_estudio().get_id() == idER) {

                long idFk = preguntaEstudio.get_preguntaEncuesta().get_id();
                DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);
                PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(idFk, PreguntaEncuesta.class);
                listaPreguntasEstudio.add(preguntaEncuesta);
            }
        }

        //Insertar las preguntas recomendadas en el nuevo estudio
        ucab.dsw.servicio.PreguntasEstudioServicio servicio = new ucab.dsw.servicio.PreguntasEstudioServicio();

        for (PreguntaEncuesta preguntaEncuesta: listaPreguntasEstudio){

            long idPR = preguntaEncuesta.get_id();
            servicio.insertarPreguntasEstudioRecomendado(idPR, idE);
        }

        dataObject = Json.createObjectBuilder()
                .add("mensaje", "Se han insertado las preguntas correctamente")
                .add("codigo", 200).build();

    }

    @Override
    public JsonObject getResult() {

        return dataObject;
    }
}
