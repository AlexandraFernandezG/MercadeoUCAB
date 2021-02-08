package ucab.dsw.comando.Usuario;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ListarUsuariosComando extends ComandoBase {

    public JsonArrayBuilder usuarios = Json.createArrayBuilder();

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
        List<Usuario> listaUsuarios = daoUsuario.findAll(Usuario.class);

        for(Usuario obj: listaUsuarios){

            if(obj.get_codigoRecuperacion() != null) {

                JsonObject usuario = Json.createObjectBuilder().add("id", obj.get_id())
                        .add("nombre", obj.get_nombre())
                        .add("codigoRecuperacion", obj.get_codigoRecuperacion())
                        .add("correo", obj.get_correoelectronico())
                        .add("estatus", obj.get_estatus()).build();

                usuarios.add(usuario);

            } else {

                JsonObject usuario = Json.createObjectBuilder().add("id", obj.get_id())
                        .add("nombre", obj.get_nombre())
                        .add("codigoRecuperacion", "null")
                        .add("correo", obj.get_correoelectronico())
                        .add("estatus", obj.get_estatus()).build();

                usuarios.add(usuario);
            }

        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Usuarios", usuarios).build();

        return resultado;
    }
}
