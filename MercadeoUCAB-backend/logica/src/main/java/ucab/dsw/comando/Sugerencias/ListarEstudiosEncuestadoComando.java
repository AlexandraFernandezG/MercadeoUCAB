package ucab.dsw.comando.Sugerencias;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.Response.EstudiosResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListarEstudiosEncuestadoComando extends ComandoBase {

    public long id;
    public JsonArrayBuilder estudios = Json.createArrayBuilder();

    public ListarEstudiosEncuestadoComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoInformacion daoInformacion = Fabrica.crear(DaoInformacion.class);
        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        JsonObject estudioJson;
        String genero = null;
        Date fechaNacimiento = null;
        String estadoCivil = null;
        int cantidadPersonas = 0;
        int edad = 0;

        List<Informacion> listaInformacion = daoInformacion.findAll(Informacion.class);
        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);

        for (Informacion informacion: listaInformacion) {

            if(informacion.get_usuario().get_id() == id) {

                genero = informacion.get_genero();
                fechaNacimiento = informacion.get_fechaNacimiento();
                estadoCivil = informacion.get_estadoCivil();
                cantidadPersonas = informacion.get_cantidadPersonas();

                //Primero pasamos la fecha de nacimiento a string
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fecha_nac = sdf.format(fechaNacimiento);

                //Formato de la fecha para la operacion de la edad
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                //Parseamos la fecha y obtener la fecha actual.
                LocalDate fechaNac = LocalDate.parse(fecha_nac, fmt);
                LocalDate ahora = LocalDate.now();

                //Calcular la edad
                Period periodo = Period.between(fechaNac, ahora);

                //Edad de la persona
                edad = periodo.getYears();

            }
        }

        List<Object[]> listaEstudios = daoEstudio.listarEstudiosEncuestado(genero, estadoCivil, cantidadPersonas, edad);

        List<EstudiosResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

        for (Object[] est : listaEstudios) {

            listaEstudiosRecomendados.add(new EstudiosResponse((long)est[0], (String)est[1], (String)est[2], (String)est[3], servicio.devolverFecha((Date)est[4]), servicio.devolverFecha((Date)est[5]), (String)est[6], (String)est[7]));
        }

        for (EstudiosResponse obj : listaEstudiosRecomendados) {

            if (obj.getObservacionesEstudio() != null) {

                estudioJson = Json.createObjectBuilder().add("id", obj.getIdEstudio())
                        .add("nombre", obj.getNombreEstudio())
                        .add("tipoInstrumento", obj.getTipoInstrumentoEstudio())
                        .add("observaciones", obj.getObservacionesEstudio())
                        .add("fechaInicio", obj.getFechaInicioEstudio())
                        .add("fechaFin", obj.getFechaFinEstudio())
                        .add("estado", obj.getEstadoEstudio())
                        .add("estatus", obj.getEstatusEstudio()).build();

                estudios.add(estudioJson);

            } else {

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
