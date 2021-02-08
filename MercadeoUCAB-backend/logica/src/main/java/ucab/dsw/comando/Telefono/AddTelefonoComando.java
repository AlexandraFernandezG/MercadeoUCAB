package ucab.dsw.comando.Telefono;

import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperTelefono;

import javax.json.Json;
import javax.json.JsonObject;

public class AddTelefonoComando extends ComandoBase {

    public Telefono telefono;
    public JsonObject telefonoObj;

    public AddTelefonoComando(Telefono telefono) {
        this.telefono = telefono;
    }

    @Override
    public void execute() throws Exception {

        DaoTelefono daoTelefono = Fabrica.crear(DaoTelefono.class);
        Telefono telefonoInsertar = Fabrica.crear(Telefono.class);
        DaoInformacion daoInformacion = Fabrica.crear(DaoInformacion.class);

        telefonoInsertar.set_numero(telefono.get_numero());
        telefonoInsertar.set_estatus(telefono.get_estatus());
        Informacion informacion = daoInformacion.find(telefono.get_informacion().get_id(), Informacion.class);
        telefonoInsertar.set_informacion(informacion);
        Telefono resul = daoTelefono.insert(telefonoInsertar);

        TelefonoDto resultado = MapperTelefono.mapEntityToDto(resul);

        telefonoObj = Json.createObjectBuilder().add("id", resultado.getId()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Telefono insertado exitosamente")
                .add("Id del telefono", telefonoObj).build();

        return resultado;
    }
}
