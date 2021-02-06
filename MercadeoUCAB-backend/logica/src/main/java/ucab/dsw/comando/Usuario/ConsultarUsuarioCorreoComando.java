package ucab.dsw.comando.Usuario;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.response.UsuarioResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ConsultarUsuarioCorreoComando extends ComandoBase {

    public String email;
    public JsonObject usuarioObj;
    public JsonArrayBuilder usuariosCorreo = Json.createArrayBuilder();

    public ConsultarUsuarioCorreoComando(String email) {
        this.email = email;
    }

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);

        List<Object[]> usuarioCorreo = daoUsuario.usuarioCorreo(email);
        List<UsuarioResponse> listaUsuarioDefinitiva = new ArrayList<>(usuarioCorreo.size());

        for (Object[] us: usuarioCorreo){

            listaUsuarioDefinitiva.add(new UsuarioResponse((long)us[0], (String)us[1], (String)us[2], (String)us[3], (String)us[4]));
        }

        for(UsuarioResponse obj: listaUsuarioDefinitiva){

            if(obj.getCodigoRecuperacion() != null) {

                JsonObject usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("codigoRecuperacion", obj.getCodigoRecuperacion())
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus()).build();

                usuariosCorreo.add(usuario);

            } else {

                JsonObject usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("codigoRecuperacion", "null")
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus()).build();

                usuariosCorreo.add(usuario);
            }

        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder().add("mensaje","Usuario con el correo seleccionado")
                .add("estado",200)
                .add("Usuario", usuariosCorreo).build();

        return resultado;
    }
}
