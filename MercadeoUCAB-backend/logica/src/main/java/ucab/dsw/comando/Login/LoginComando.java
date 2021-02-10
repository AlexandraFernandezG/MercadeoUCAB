package ucab.dsw.comando.Login;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.jwt.Jwt;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class LoginComando extends ComandoBase {
    public UsuarioDto usuarioDto;
    public JsonObject data;
    public String token;

    public LoginComando (UsuarioDto usuarioDto){
        this.usuarioDto = usuarioDto;

    }

    @Override
    public void execute() throws Exception {
        try
        {
            DirectorioActivo ldap = new DirectorioActivo();
            long resultado = ldap.userAuthentication( this.usuarioDto );

            if(resultado==1){
                DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
                Usuario usuario = daoUsuario.find(Long.parseLong(ldap.getEntryId(this.usuarioDto)), Usuario.class);


                Jwt jwt = new Jwt();
                this.token = jwt.generarToken(Long.parseLong(ldap.getEntryId(this.usuarioDto)));
                usuario.set_token(this.token);

                Usuario resul = daoUsuario.update(usuario);

                data= Json.createObjectBuilder()
                        .add("estado","success")
                        .add("codigo",200)
                        .add("token",this.token)
                        .add("rol", ldap.getEntryRole(this.usuarioDto))
                        .add("id", Long.parseLong(ldap.getEntryId(this.usuarioDto))).build();

                System.out.println(data);


            }else{
                data= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("codigo",401).build();
                System.out.println(data);

            }

        }catch(Exception ex){
            data= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("codigo",401).build();
        }

    }

    @Override
    public JsonObject getResult() {
        try {
            return this.data;
        } catch (NullPointerException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
