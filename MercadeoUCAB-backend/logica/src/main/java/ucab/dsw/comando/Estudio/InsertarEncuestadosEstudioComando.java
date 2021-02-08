package ucab.dsw.comando.Estudio;

import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.Response.UsuarioResponse;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class InsertarEncuestadosEstudioComando extends ComandoBase {

    public JsonObject dataObject;
    public long id;
    public List<UsuarioResponse> listaEncuestados;

    public InsertarEncuestadosEstudioComando(long id, List<UsuarioResponse> listaEncuestados) {
        this.id = id;
        this.listaEncuestados = listaEncuestados;
    }

    @Override
    public void execute() throws Exception {

        ucab.dsw.comando.Funciones.FuncionesComando servicio = Fabrica.crear(ucab.dsw.comando.Funciones.FuncionesComando.class);

        //Recorremos la lista de encuestados y insertamos
        for (UsuarioResponse usuarioEncuestado: listaEncuestados) {

            servicio.addUsuarioEstudio(id, usuarioEncuestado.getId());

        }

        dataObject = Json.createObjectBuilder()
                .add("mensaje", "Se han insertado los encuestados correctamente")
                .add("codigo", 200).build();

    }

    @Override
    public JsonObject getResult() {

        return dataObject;
    }
}
