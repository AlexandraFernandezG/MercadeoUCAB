package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.Response.UsuarioResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ListarSolicitudEncuestadosComando extends ComandoBase {

    public JsonArrayBuilder encuestados = Json.createArrayBuilder();
    public long id;

    public ListarSolicitudEncuestadosComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws PruebaExcepcion {

        List<UsuarioResponse> listaEncuestadosSolicitud = new ArrayList<>();
        DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
        DaoInformacion daoInformacion = Fabrica.crear(DaoInformacion.class);
        ucab.dsw.comando.Funciones.FuncionesComando servicio = new ucab.dsw.comando.Funciones.FuncionesComando();

        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        //Listar todos los usuarios encuestados
        List<Object[]> listaUsuariosEncuestados = daoUsuario.listarEncuestadosEstudio();
        List<UsuarioResponse> listaUsuariosEncuestadosResult = new ArrayList<>(listaUsuariosEncuestados.size());
        List<Informacion> listaInformacion = daoInformacion.findAll(Informacion.class);
        JsonObject usuario;

        for (Object[] user : listaUsuariosEncuestados) {

            listaUsuariosEncuestadosResult.add(new UsuarioResponse((long) user[0], (String) user[1], (String) user[2], (String) user[3], (String) user[4]));
        }

        //Recorremos la lista de encuestados y hacemos el match
        for (UsuarioResponse usuarioEncuestado : listaUsuariosEncuestadosResult) {

            for (Informacion informacion : listaInformacion) {

                if (solicitudEstudio.get_genero().equals(informacion.get_genero()) && solicitudEstudio.get_estadoCivil().equals(informacion.get_estadoCivil()) &&
                        solicitudEstudio.get_cantidadPersonas() == informacion.get_cantidadPersonas() && informacion.get_usuario().get_id() == usuarioEncuestado.getId()
                 && servicio.devolverEdad(informacion.get_fechaNacimiento()) > solicitudEstudio.get_edadMinima() && servicio.devolverEdad(informacion.get_fechaNacimiento()) < solicitudEstudio.get_edadMaxima()) {

                    listaEncuestadosSolicitud.add(usuarioEncuestado);
                }
            }
        }

        for (UsuarioResponse obj : listaEncuestadosSolicitud) {

            if (obj.getCodigoRecuperacion() != null && obj.getNombre() != null &&
                    obj.getCorreo() != null && obj.getEstatus() != null) {

                usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("codigoRecuperacion", obj.getCodigoRecuperacion())
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus()).build();


                encuestados.add(usuario);

            } else if (obj.getCodigoRecuperacion() == null && obj.getNombre() != null &&
                    obj.getCorreo() != null && obj.getEstatus() != null) {

                usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("codigoRecuperacion", "")
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus()).build();


                encuestados.add(usuario);

            } else if (obj.getCodigoRecuperacion() != null && obj.getNombre() == null &&
                    obj.getCorreo() != null && obj.getEstatus() != null) {

                usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", "")
                        .add("codigoRecuperacion", obj.getCodigoRecuperacion())
                        .add("correo", obj.getCorreo())
                        .add("estatus", obj.getEstatus()).build();


                encuestados.add(usuario);

            } else if (obj.getCodigoRecuperacion() != null && obj.getNombre() != null &&
                    obj.getCorreo() == null && obj.getEstatus() != null) {

                usuario = Json.createObjectBuilder().add("id", obj.getId())
                        .add("nombre", obj.getNombre())
                        .add("codigoRecuperacion", obj.getCodigoRecuperacion())
                        .add("correo", "")
                        .add("estatus", obj.getEstatus()).build();


                encuestados.add(usuario);
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
