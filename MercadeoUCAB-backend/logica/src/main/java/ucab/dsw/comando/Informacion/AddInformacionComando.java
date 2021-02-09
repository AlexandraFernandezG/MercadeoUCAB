package ucab.dsw.comando.Informacion;

import ucab.dsw.accesodatos.*;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.entidades.*;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperInformacion;

import javax.json.Json;
import javax.json.JsonObject;

public class AddInformacionComando extends ComandoBase {

    public Informacion informacion;
    public JsonObject informacionObj;

    public AddInformacionComando(Informacion informacion) {
        this.informacion = informacion;
    }

    @Override
    public void execute() throws Exception {

        DaoInformacion daoInformacion = Fabrica.crear(DaoInformacion.class);
        Informacion informacionInsertar = Fabrica.crear(Informacion.class);
        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
        DaoOcupacion daoOcupacion = Fabrica.crear(DaoOcupacion.class);
        DaoNivelEconomico daoNivelEconomico = Fabrica.crear(DaoNivelEconomico.class);
        DaoNivelAcademico daoNivelAcademico = Fabrica.crear(DaoNivelAcademico.class);
        DaoLugar daoLugar = Fabrica.crear(DaoLugar.class);

        informacionInsertar.set_cedula(informacion.get_cedula());
        informacionInsertar.set_primerNombre(informacion.get_primerNombre());
        informacionInsertar.set_segundoNombre(informacion.get_segundoNombre());
        informacionInsertar.set_primerApellido(informacion.get_primerApellido());
        informacionInsertar.set_segundoApellido(informacion.get_segundoApellido());
        informacionInsertar.set_genero(informacion.get_genero());
        informacionInsertar.set_fechaNacimiento(informacion.get_fechaNacimiento());
        informacionInsertar.set_estadoCivil(informacion.get_estadoCivil());
        informacionInsertar.set_disponibilidadEnLinea(informacion.get_disponibilidadEnLinea());
        informacionInsertar.set_cantidadPersonas(informacion.get_cantidadPersonas());
        informacionInsertar.set_estatus(informacion.get_estatus());
        Lugar lugar = daoLugar.find(informacion.get_lugar().get_id(), Lugar.class);
        informacionInsertar.set_lugar(lugar);
        NivelEconomico nivelEconomico = daoNivelEconomico.find(informacion.get_nivelEconomico().get_id(), NivelEconomico.class);
        informacionInsertar.set_nivelEconomico(nivelEconomico);
        NivelAcademico nivelAcademico = daoNivelAcademico.find(informacion.get_nivelAcademico().get_id(), NivelAcademico.class);
        informacionInsertar.set_nivelAcademico(nivelAcademico);
        Ocupacion ocupacion = daoOcupacion.find(informacion.get_ocupacion().get_id(), Ocupacion.class);
        informacionInsertar.set_ocupacion(ocupacion);
        Usuario usuario = daoUsuario.find(informacion.get_usuario().get_id(), Usuario.class);
        informacionInsertar.set_usuario(usuario);
        Informacion resul = daoInformacion.insert(informacionInsertar);

        InformacionDto resultado = MapperInformacion.mapEntityToDto(resul);

        informacionObj = Json.createObjectBuilder().add("id", resultado.getId()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Su informacion ha sido registrada exitosamente en el sistema")
                .add("id", informacionObj).build();

        return resultado;
    }
}
