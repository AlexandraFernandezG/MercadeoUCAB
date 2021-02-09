package ucab.dsw.comando.SolicitudEstudio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperSolicitudEstudio;

import javax.json.Json;
import javax.json.JsonObject;

public class AddSolicitudEstudioComando extends ComandoBase {

    public SolicitudEstudio solicitudEstudio;
    public JsonObject solicitudestudioObj;

    public AddSolicitudEstudioComando(SolicitudEstudio solicitudEstudio) {
        this.solicitudEstudio = solicitudEstudio;
    }

    @Override
    public void execute() throws Exception {

        DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
        SolicitudEstudio solicitudEstudioInsertar = Fabrica.crear(SolicitudEstudio.class);
        DaoNivelEconomico daoNivelEconomico = Fabrica.crear(DaoNivelEconomico.class);
        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
        DaoProducto daoProducto = Fabrica.crear(DaoProducto.class);
        DaoOcupacion daoOcupacion = Fabrica.crear(DaoOcupacion.class);
        DaoNivelAcademico daoNivelAcademico = Fabrica.crear(DaoNivelAcademico.class);

        solicitudEstudioInsertar.set_descripcion(solicitudEstudio.get_descripcion());
        solicitudEstudioInsertar.set_genero(solicitudEstudio.get_genero());
        solicitudEstudioInsertar.set_edadMaxima(solicitudEstudio.get_edadMaxima());
        solicitudEstudioInsertar.set_edadMinima(solicitudEstudio.get_edadMinima());
        solicitudEstudioInsertar.set_estadoCivil(solicitudEstudio.get_estadoCivil());
        solicitudEstudioInsertar.set_disponibilidadEnLinea(solicitudEstudio.get_disponibilidadEnLinea());
        solicitudEstudioInsertar.set_cantidadPersonas(solicitudEstudio.get_cantidadPersonas());
        solicitudEstudioInsertar.set_cantidadHijos(solicitudEstudio.get_cantidadHijos());
        solicitudEstudioInsertar.set_generoHijos(solicitudEstudio.get_generoHijos());
        solicitudEstudioInsertar.set_edadMinimaHijos(solicitudEstudio.get_edadMinimaHijos());
        solicitudEstudioInsertar.set_edadMaximaHijos(solicitudEstudio.get_edadMaximaHijos());
        solicitudEstudioInsertar.set_estado(solicitudEstudio.get_estado());
        solicitudEstudioInsertar.set_estatus(solicitudEstudio.get_estatus());
        NivelEconomico nivelEconomico = daoNivelEconomico.find(solicitudEstudio.get_nivelEconomico().get_id(), NivelEconomico.class);
        Usuario usuario = daoUsuario.find(solicitudEstudio.get_usuario().get_id(), Usuario.class);
        Producto producto = daoProducto.find(solicitudEstudio.get_producto().get_id(), Producto.class);
        Ocupacion ocupacion = daoOcupacion.find(solicitudEstudio.get_ocupacion().get_id(), Ocupacion.class);
        NivelAcademico nivelAcademico = daoNivelAcademico.find(solicitudEstudio.get_nivelAcademico().get_id(), NivelAcademico.class);
        solicitudEstudioInsertar.set_nivelEconomico(nivelEconomico);
        solicitudEstudioInsertar.set_usuario(usuario);
        solicitudEstudioInsertar.set_producto(producto);
        solicitudEstudioInsertar.set_ocupacion(ocupacion);
        solicitudEstudioInsertar.set_nivelAcademico(nivelAcademico);
        SolicitudEstudio resul = daoSolicitudEstudio.insert(solicitudEstudioInsertar);

        SolicitudEstudioDto resultado = MapperSolicitudEstudio.mapEntityToDto(resul);

        solicitudestudioObj = Json.createObjectBuilder().add("id", resultado.getId()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Su solicitud ha sido creada con éxito espere a que pase a ser procesada por el sistema")
                .add("id", solicitudestudioObj).build();

        return resultado;
    }
}
