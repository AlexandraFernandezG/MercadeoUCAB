package ucab.dsw.comando.Usuario;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.response.UsuarioResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ListarAnalistasComando extends ComandoBase {

    public JsonArrayBuilder analistas = Json.createArrayBuilder();

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);

        List<Object[]> listaAnalistas = daoUsuario.listarAnalistas();
        List<UsuarioResponse> listaAnalistaResponse = new ArrayList<>();

        for (Object[] ana: listaAnalistas){

            listaAnalistaResponse.add(new UsuarioResponse((long)ana[0], (String)ana[1], (String)ana[2], (String)ana[3], (String)ana[4]));
        }

        for(UsuarioResponse obj: listaAnalistaResponse){

            if(obj.getCodigoRecuperacion() != null) {

                JsonObject usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("codigoRecuperacion", obj.getCodigoRecuperacion())
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus()).build();

                analistas.add(usuario);

            } else {

                JsonObject usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("codigoRecuperacion", "null")
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus()).build();

                analistas.add(usuario);
            }

        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Usuarios", analistas).build();

        return resultado;
    }
}
