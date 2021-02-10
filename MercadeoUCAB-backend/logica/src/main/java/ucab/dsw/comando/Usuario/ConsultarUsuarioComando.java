package ucab.dsw.comando.Usuario;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarUsuarioComando extends ComandoBase {

    public long id;
    public JsonObject usuarioObj;

    public ConsultarUsuarioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
        Usuario usuario_consultado = daoUsuario.find(id, Usuario.class);

        if(usuario_consultado.get_token() != null) {

            usuarioObj = Json.createObjectBuilder().add("id", usuario_consultado.get_id())
                    .add("nombre", usuario_consultado.get_nombre())
                    .add("token", usuario_consultado.get_token())
                    .add("correo", usuario_consultado.get_correoelectronico())
                    .add("estatus", usuario_consultado.get_estatus()).build();

        } else {

            usuarioObj = Json.createObjectBuilder().add("id", usuario_consultado.get_id())
                    .add("nombre", usuario_consultado.get_nombre())
                    .add("token", "null")
                    .add("correo", usuario_consultado.get_correoelectronico())
                    .add("estatus", usuario_consultado.get_estatus()).build();

        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Usuario", usuarioObj).build();

        return resultado;
    }
}
