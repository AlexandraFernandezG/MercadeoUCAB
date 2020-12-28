package ucab.dsw.servicio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
         * Este método filtra los estudios que tengan coincidencia con la
         * descripción de una solicitud
         *
         * NOTA: Funciona, pero puede ser mejorado.
         */

        try {

            DaoEstudio daoEstudio = new DaoEstudio();
            List<Estudio> listarEstudios = daoEstudio.findAll(Estudio.class);
            List<Estudio> listarEstudiosSugeridos = new ArrayList<Estudio>();

            String descripcion_solicitud = solicitudEstudioDto.getDescripcion();

            //Extraer cada palabra de la cadena de string
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
    @GET
    @Path("/suggestionsEstudiosEncuestado")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Estudio> listarEstudiosEncuestado(InformacionDto informacionDto){

        /**
         * Este método filtra los estudios que hagan referencia a la persona
         * que vaya a realizar una encuesta (Estudios recomendados).
         *
         * NOTA: Funciona, pero puede ser mejorado.
         */

        //Lista del return
        List<Estudio> listaEstudiosRecomendados = new ArrayList<Estudio>();

        String genero = informacionDto.getGenero();
        Date fecha_nacimiento = informacionDto.getFechaNacimiento();
        String estadoCivil = informacionDto.getEstadoCivil();
        String disponibilidadLinea = informacionDto.getDisponibilidadEnLinea();
        int cantidad_personas = informacionDto.getCantidadPersonas();

        try {

            //Primero pasamos la fecha de nacimiento a string
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha_nac = sdf.format(fecha_nacimiento);

            //Formato de la fecha para la operacion de la edad
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            //Parseamos la fecha y obtener la fecha actual.
            LocalDate fechaNac = LocalDate.parse(fecha_nac, fmt);
            LocalDate ahora = LocalDate.now();

            //Calcular la edad
            Period periodo = Period.between(fechaNac, ahora);

            //Edad de la persona
            int edad = periodo.getYears();

            //Realizar el match con la tabla solicitudEstudio

            DaoEstudio daoEstudio = new DaoEstudio();
            DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
            List<Estudio> listaEstudio = daoEstudio.findAll(Estudio.class);

            for (Estudio estudio: listaEstudio){

                long id_fk = estudio.get_solicitudEstudio().get_id();
                SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id_fk, SolicitudEstudio.class);

                if (solicitudEstudio.get_genero() != null) {

                    if (solicitudEstudio.get_genero().equals(genero)) {

                        listaEstudiosRecomendados.add(estudio);
                    }
                }

                if(solicitudEstudio.get_edadMinima() != 0 && solicitudEstudio.get_edadMaxima() != 0) {

                    if (edad > solicitudEstudio.get_edadMinima() && edad < solicitudEstudio.get_edadMaxima()) {

                        listaEstudiosRecomendados.add(estudio);
                    }
                }

                if (solicitudEstudio.get_estadoCivil() != null) {

                    if (solicitudEstudio.get_estadoCivil().equals(estadoCivil)) {

                        listaEstudiosRecomendados.add(estudio);
                    }
                }

                if(solicitudEstudio.get_disponibilidadEnLinea() != null) {

                    if (solicitudEstudio.get_disponibilidadEnLinea().equals(disponibilidadLinea)) {

                        listaEstudiosRecomendados.add(estudio);
                    }
                }

                if (solicitudEstudio.get_cantidadPersonas() == cantidad_personas){

                    listaEstudiosRecomendados.add(estudio);
                }

                else {

                    return null;
                }
            }

            return listaEstudiosRecomendados;

        } catch (Exception ex) {

            String problema = ex.getMessage();
            System.out.print(problema);
            return null;

        }
    }

}
