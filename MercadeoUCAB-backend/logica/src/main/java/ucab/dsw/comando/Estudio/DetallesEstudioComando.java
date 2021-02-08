package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class DetallesEstudioComando extends ComandoBase {

    public long id;
    public JsonObject dataObject;
    public JsonArrayBuilder detalles = Json.createArrayBuilder();

    public DetallesEstudioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoLugar daoLugar = Fabrica.crear(DaoLugar.class);
        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);

        String estado = "";
        String municipio = "";

        List<String> lugarEstado = daoLugar.lugarEstadoEstudio(id);
        List<String> lugarMunicipio = daoLugar.lugarMunicipioEstudio(id);

        estado = lugarEstado.get(0);
        municipio = lugarMunicipio.get(0);

        List<Object[]> detallesEstudio = daoEstudio.detallesEstudio(id);

        for(Object[] obj: detallesEstudio){

            JsonObject descripcionSolicitud = Json.createObjectBuilder()
                    .add("descripcionSolicitud", (String)obj[0]).build();
            detalles.add(descripcionSolicitud);
            JsonObject nombreProducto = Json.createObjectBuilder()
                    .add("nombreProducto", (String)obj[1]).build();
            detalles.add(nombreProducto);
            JsonObject nombreSubcategoria = Json.createObjectBuilder()
                    .add("nombreSubcategoria", (String)obj[2]).build();
            detalles.add(nombreSubcategoria);
            JsonObject edadMinima = Json.createObjectBuilder()
                    .add("edadMinima", (int)obj[3]).build();
            detalles.add(edadMinima);
            JsonObject edadMaxima = Json.createObjectBuilder()
                    .add("edadMaxima", (int)obj[4]).build();
            detalles.add(edadMaxima);
            JsonObject genero = Json.createObjectBuilder()
                    .add("genero", (String)obj[5]).build();
            detalles.add(genero);
            JsonObject estadoCivil = Json.createObjectBuilder()
                    .add("estadoCivil", (String)obj[6]).build();
            detalles.add(estadoCivil);
            JsonObject descripcionEconomico = Json.createObjectBuilder()
                    .add("descripcionNivelEconomico", (String)obj[7]).build();
            detalles.add(descripcionEconomico);
            JsonObject descripcionAcademico = Json.createObjectBuilder()
                    .add("descripcionNivelAcademico", (String)obj[8]).build();
            detalles.add(descripcionAcademico);
            JsonObject ocupacion = Json.createObjectBuilder()
                    .add("ocupacion", (String)obj[9]).build();
            detalles.add(ocupacion);
            JsonObject disponibilidadLinea = Json.createObjectBuilder()
                    .add("disponibilidadEnLinea", (String)obj[10]).build();
            detalles.add(disponibilidadLinea);
        }

        JsonObject estadoEstudio = Json.createObjectBuilder()
                .add("estado", estado).build();
        detalles.add(estadoEstudio);
        JsonObject municipioEstudio = Json.createObjectBuilder()
                .add("municipio", municipio).build();
        detalles.add(municipioEstudio);

    }

    @Override
    public JsonObject getResult() {

         return dataObject = Json.createObjectBuilder()
                .add("Lista de detalles", detalles).build();
    }
}
