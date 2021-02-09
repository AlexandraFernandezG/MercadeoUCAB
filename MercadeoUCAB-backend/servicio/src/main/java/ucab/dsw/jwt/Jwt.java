package ucab.dsw.jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;

import java.security.Key;
import java.util.Date;

public class Jwt {
    private static Key KEY= MacProvider.generateKey();


    public String generarToken(long id){


        try{
            String token=Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256,KEY)
                    .setSubject(String.valueOf(id))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+3000000))
                    .compact();

            return token;

        }catch(Exception ex){
            return ex.getMessage();
        }

    }


    public static boolean validarToken(String token){

        Jws<Claims> jwt;
        try{

            jwt= Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            long token_subject_web= Long.parseLong(jwt.getBody().getSubject());
            DaoUsuario dao=new DaoUsuario();
            Usuario usuario=dao.find(token_subject_web,Usuario.class);
            long token_subject_bd = Long.parseLong(Jwts.parser().setSigningKey(KEY).parseClaimsJws(usuario.get_token()).getBody().getSubject());

            if(token_subject_bd==token_subject_web){
                return true;
            }
            else{
                return false;
            }


        }catch(MalformedJwtException ex){
            return false;
        }
        catch (ExpiredJwtException ex){
            return false;
        }
        catch (SignatureException ex){
            return false;
        }
    }
}