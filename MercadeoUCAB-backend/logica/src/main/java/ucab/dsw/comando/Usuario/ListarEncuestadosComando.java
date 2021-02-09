package ucab.dsw.comando.Usuario;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.Response.UsuarioResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ListarEncuestadosComando extends ComandoBase {

    public JsonArrayBuilder encuestados = Json.createArrayBuilder();

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);

        List<Object[]> listaEncuestados = daoUsuario.listarEncuestadosEstudio();
        List<UsuarioResponse> listaEncuestadosResponse = new ArrayList<>();

        for (Object[] enc: listaEncuestados){

            listaEncuestadosResponse.add(new UsuarioResponse((long)enc[0], (String)enc[1], (String)enc[2], (String)enc[3], (String)enc[4]));
        }

        for(UsuarioResponse obj: listaEncuestadosResponse){

            if(obj.getToken() != null) {

                JsonObject usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("token", obj.getToken())
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus()).build();

                encuestados.add(usuario);

            } else {

                JsonObject usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("token", "null")
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus()).build();

                encuestados.add(usuario);
            }

        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Usuarios", encuestados).build();

        return resultado;
    }
}
