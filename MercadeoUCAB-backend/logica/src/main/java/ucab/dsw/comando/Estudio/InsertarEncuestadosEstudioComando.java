package ucab.dsw.comando.Estudio;

import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.dtos.UsuarioEstudioDto;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.response.UsuarioResponse;

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

        ucab.dsw.servicio.UsuarioEstudioServicio servicio = new ucab.dsw.servicio.UsuarioEstudioServicio();

        //Insertar encuestados al estudio
        UsuarioEstudioDto usuarioEstudioDto = Fabrica.crear(UsuarioEstudioDto.class);

        //Recorremos la lista de encuestados y insertamos
        for (UsuarioResponse usuarioEncuestado: listaEncuestados) {

            usuarioEstudioDto.setEstatus("En proceso");
            EstudioDto idEstudio = new EstudioDto(id);
            usuarioEstudioDto.setEstudioDto(idEstudio);
            UsuarioDto idUsuario = new UsuarioDto(usuarioEncuestado.getId());
            usuarioEstudioDto.setUsuarioDto(idUsuario);
            servicio.addUsuarioEstudio(usuarioEstudioDto);

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
