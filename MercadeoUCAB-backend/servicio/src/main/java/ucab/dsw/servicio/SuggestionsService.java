package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.PreguntaEncuesta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Este servicio puede ser mejorado.
 */

@Path( "/suggestions" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SuggestionsService extends AplicacionBase{

    //Obtener estudios recomentados en base a una solicitud
    @GET
    @Path("/suggestionsEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Estudio> listarEstudiosRecomendados(SolicitudEstudioDto solicitudEstudioDto){

        /**
         * Este método filtra los estudios que tengan coinciendcia con la
         * descripción de una solicitud
         *
         * NOTA: Funciona, pero puede ser mejorado.
         */

        try {

            DaoEstudio daoEstudio = new DaoEstudio();
            List<Estudio> listarEstudios = daoEstudio.findAll(Estudio.class);
            List<Estudio> listarEstudiosSugeridos = new ArrayList<Estudio>();

            String descripcion_solicitud = solicitudEstudioDto.getDescripcion();

            //Extraer cada plabra de la cadena de string
            String[] palabras_solicitud = descripcion_solicitud.split("\\s+");

            for (Estudio estudio : listarEstudios) {

                String nombre_estudio = estudio.get_nombre();
                String[] palabras_estudio = nombre_estudio.split("\\s+");

                for (int i = 0; i < palabras_solicitud.length; i++) {

                    for (int j = 0; j < palabras_estudio.length; j++) {

                        if (palabras_solicitud[i].toLowerCase().equals(palabras_estudio[j].toLowerCase())) {

                            listarEstudiosSugeridos.add(estudio);
                        }
                    }
                }
            }

            List<Estudio> Noduplicados = listarEstudiosSugeridos.stream().distinct().collect(Collectors.toList());

            return Noduplicados;

        } catch (Exception ex) {

        String problema = ex.getMessage();
        System.out.print(problema);
        return null;

        }

    }

    //Listar preguntas acorde a una subcategoria
    @GET
    @Path("/suggestionsPregunta")
    @Produces( MediaType.APPLICATION_JSON )
    public List<PreguntaEncuesta> listarPreguntasRecomendadas(SubcategoriaDto subcategoriaDto){

        /**
         * Este método filtra las preguntas que tengan coinciendcia con el
         * nombre de una subcategoria en especifico.
         *
         * NOTA: Funciona, pero puede ser mejorado.
         */

        try {

            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
            List<PreguntaEncuesta> listarPreguntas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);
            List<PreguntaEncuesta> listaPreguntasSugeridas = new ArrayList<PreguntaEncuesta>();

            String nombre_subcategoria = subcategoriaDto.getNombre();

            //Extraer cada plabra de la cadena de string
            String[] palabras_subcategoria = nombre_subcategoria.split("\\s+");

            for (PreguntaEncuesta preguntaEncuesta : listarPreguntas) {

                String descripcion_pregunta = preguntaEncuesta.get_descripcion();
                String[] palabras_pregunta = descripcion_pregunta.split("\\s+");

                for (int i = 0; i < palabras_subcategoria.length; i++) {

                    for (int j = 0; j < palabras_pregunta.length; j++) {

                        if (palabras_subcategoria[i].toLowerCase().equals(palabras_pregunta[j].toLowerCase())) {

                            listaPreguntasSugeridas.add(preguntaEncuesta);
                        }
                    }
                }
            }

            List<PreguntaEncuesta> Noduplicados = listaPreguntasSugeridas.stream().distinct().collect(Collectors.toList());

            return Noduplicados;

        } catch (Exception ex) {

            String problema = ex.getMessage();
            System.out.print(problema);
            return null;

        }
    }

    //Listar encuesta recomendada para el cliente.

}
