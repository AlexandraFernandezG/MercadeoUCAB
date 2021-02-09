package ucab.dsw.comando.Estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperEstudio;

import javax.json.Json;
import javax.json.JsonObject;

public class AddEstudioComando extends ComandoBase {

    public Estudio estudio;
    public JsonObject estudioObj;

    public AddEstudioComando(Estudio estudio) {

        this.estudio = estudio;
    }

    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
        DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);

        Estudio estudioInsertar = Fabrica.crear(Estudio.class);

        estudioInsertar.set_nombre(estudio.get_nombre());
        estudioInsertar.set_tipoInstrumento(estudio.get_tipoInstrumento());
        estudioInsertar.set_observaciones(estudio.get_observaciones());
        estudioInsertar.set_fechaInicio(estudio.get_fechaInicio());
        estudioInsertar.set_fechaFin(estudio.get_fechaFin());
        estudioInsertar.set_estado(estudio.get_estado());
        estudioInsertar.set_estatus(estudio.get_estatus());
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(estudio.get_solicitudEstudio().get_id(), SolicitudEstudio.class);
        Usuario usuario = daoUsuario.find(estudio.get_usuario().get_id(), Usuario.class);
        estudioInsertar.set_solicitudEstudio(solicitudEstudio);
        estudioInsertar.set_usuario(usuario);
        Estudio resul = daoEstudio.insert(estudioInsertar);

        EstudioDto resultado = MapperEstudio.mapEntityToDto(resul);

        estudioObj = Json.createObjectBuilder().add("id", resultado.getId()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Estudio creado exitosamente")
                .add("id", estudioObj).build();

        return resultado;
    }
}
