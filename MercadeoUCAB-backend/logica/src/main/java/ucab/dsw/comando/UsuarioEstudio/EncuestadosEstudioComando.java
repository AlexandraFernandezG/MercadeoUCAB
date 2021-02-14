package ucab.dsw.comando.UsuarioEstudio;

import ucab.dsw.Response.EncuestadosEstudioETResponse;
import ucab.dsw.Response.UsuarioResponse;
import ucab.dsw.accesodatos.DaoUsuarioEstudio;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class EncuestadosEstudioComando extends ComandoBase {

    public long id;
    public JsonArrayBuilder encuestados = Json.createArrayBuilder();
    public JsonObject encuestadoJson;

    public EncuestadosEstudioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoUsuarioEstudio daoUsuarioEstudio = Fabrica.crear(DaoUsuarioEstudio.class);
        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);
        List<Object[]> listaUsuarioEstudios = daoUsuarioEstudio.listarEncuestadosEstudio(id);

        List<EncuestadosEstudioETResponse> listaEncuestadosEstudio = new ArrayList<>(listaUsuarioEstudios.size());

        for (Object[] user : listaUsuarioEstudios) {

            listaEncuestadosEstudio.add(new EncuestadosEstudioETResponse((long)user[0], (String)user[1], (String)user[2], (String)user[3], (String)user[4], (String)user[5]));
        }

        for (EncuestadosEstudioETResponse obj: listaEncuestadosEstudio){

            if(obj.getCodigoRecuperacion() != null) {

                encuestadoJson = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("codigoRecuperacion", obj.getCodigoRecuperacion())
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus())
                        .add("estado", obj.getEstado())
                        .add("telefono", servicio.devolverTelefono(obj.getId())).build();

                encuestados.add(encuestadoJson);

            } else {

                encuestadoJson = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("codigoRecuperacion", "")
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus())
                        .add("estado", obj.getEstado())
                        .add("telefono", servicio.devolverTelefono(obj.getId())).build();

                encuestados.add(encuestadoJson);
            }
        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Encuestados", encuestados).build();

        return resultado;
    }
}
