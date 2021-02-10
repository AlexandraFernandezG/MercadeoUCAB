package ucab.dsw.comando.Usuario;

import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DetallesEncuestadosComando extends ComandoBase {

    public long id;
    public JsonObject dataObject;
    public JsonArrayBuilder detalles = Json.createArrayBuilder();

    public DetallesEncuestadosComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoLugar daoLugar = Fabrica.crear(DaoLugar.class);
        DaoTelefono daoTelefono = Fabrica.crear(DaoTelefono.class);
        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);

        String estado = "";
        String municipio = "";
        long id_municipio = 0;
        long id_estado = 0;

        List<Long> id_estadoQuery = daoLugar.lugarEstado(id);
        List<Long> id_municipioQuery = daoLugar.lugarMunicipio(id);
        List<String> telefonoQuery = daoTelefono.telefonosEncuestado(id);
        List<Object[]> detallesEncuestados = daoUsuario.detallesEncuestados(id);

        id_municipio = id_municipioQuery.get(0);
        id_estado = id_estadoQuery.get(0);

        List<String> nombreEstado = daoLugar.nombreLugar(id_estado);
        List<String> nombreMunicipio = daoLugar.nombreLugar(id_municipio);

        estado = nombreEstado.get(0);
        municipio = nombreMunicipio.get(0);
        int contador = 0;
        int edad = 0;

        //Obtener telefono
        String telefono = telefonoQuery.get(0);

        //Recorrer los detalles y guardar.
        for(Object[] obj: detallesEncuestados){

            Date fechaNacimientoEdad = (Date)obj[5];
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            JsonObject cedula = Json.createObjectBuilder()
                    .add("cedula", (int)obj[0]).build();
            detalles.add(cedula);

            if (obj[2] != null) {

                JsonObject nombreCompleto = Json.createObjectBuilder()
                        .add("nombreCompleto", (String) obj[1] + " " + (String) obj[2] + " " + (String) obj[3] + " " +
                                (String) obj[4]).build();
                detalles.add(nombreCompleto);

            } else {

                JsonObject nombreCompleto = Json.createObjectBuilder()
                        .add("nombreCompleto", (String) obj[1] + " " + (String) obj[3] + " " +
                                (String) obj[4]).build();
                detalles.add(nombreCompleto);

            }
            JsonObject fechaNacimiento = Json.createObjectBuilder()
                    .add("fechaNacimiento", sdf.format(fechaNacimientoEdad)).build();
            detalles.add(fechaNacimiento);

            //Calcular la edad
            //Primero pasamos la fecha de nacimiento a string
            String fecha_nac = sdf.format(fechaNacimientoEdad);

            //Formato de la fecha para la operacion de la edad
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            //Parseamos la fecha y obtener la fecha actual.
            LocalDate fechaNac = LocalDate.parse(fecha_nac, fmt);
            LocalDate ahora = LocalDate.now();

            //Calcular la edad
            Period periodo = Period.between(fechaNac, ahora);

            //Edad de la persona
            edad = periodo.getYears();

            JsonObject edadJson = Json.createObjectBuilder()
                    .add("edad", edad).build();
            detalles.add(edadJson);
            JsonObject genero = Json.createObjectBuilder()
                    .add("genero", (String)obj[6]).build();
            detalles.add(genero);
            JsonObject estadoCivil = Json.createObjectBuilder()
                    .add("estadoCivil", (String)obj[7]).build();
            detalles.add(estadoCivil);
            JsonObject disponibilidadEnLinea = Json.createObjectBuilder()
                    .add("disponibilidadEnLinea", (String)obj[8]).build();
            detalles.add(disponibilidadEnLinea);
            JsonObject descripcionAcademico = Json.createObjectBuilder()
                    .add("descripcionNivelAcademico", (String)obj[9]).build();
            detalles.add(descripcionAcademico);
            JsonObject descripcionEconomico = Json.createObjectBuilder()
                    .add("descripcionNivelEconomico", (String)obj[10]).build();
            detalles.add(descripcionEconomico);
            JsonObject ocupacion = Json.createObjectBuilder()
                    .add("ocupacion", (String)obj[11]).build();
            detalles.add(ocupacion);

        }

        JsonObject estadoEstudio = Json.createObjectBuilder()
                .add("estado", estado).build();
        detalles.add(estadoEstudio);
        JsonObject municipioEstudio = Json.createObjectBuilder()
                .add("municipio", municipio).build();
        detalles.add(municipioEstudio);
        JsonObject listaTelefono = Json.createObjectBuilder()
                .add("telefono", telefono).build();
        detalles.add(listaTelefono);

    }

    @Override
    public JsonObject getResult() {

        return dataObject = Json.createObjectBuilder()
                .add("ListaDetalles", detalles).build();
    }
}
