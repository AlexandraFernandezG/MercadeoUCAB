package ucab.dsw.comando.PreguntasEncuesta;

import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperPreguntaEncuesta;

import javax.json.Json;
import javax.json.JsonObject;

public class AddPreguntaEncuestaComando extends ComandoBase {

    public PreguntaEncuesta preguntaEncuesta;
    public JsonObject preguntaEncuestaObj;

    public AddPreguntaEncuestaComando(PreguntaEncuesta preguntaEncuesta) {
        this.preguntaEncuesta = preguntaEncuesta;
    }

    @Override
    public void execute() throws Exception {

        DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);
        PreguntaEncuesta preguntaEncuestaInsertar = Fabrica.crear(PreguntaEncuesta.class);
        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
        DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);

        preguntaEncuestaInsertar.set_descripcion(preguntaEncuesta.get_descripcion());
        preguntaEncuestaInsertar.set_tipoPregunta(preguntaEncuesta.get_tipoPregunta());
        preguntaEncuestaInsertar.set_estatus(preguntaEncuesta.get_estatus());
        Usuario usuario = daoUsuario.find(preguntaEncuesta.get_usuario().get_id(), Usuario.class);
        preguntaEncuestaInsertar.set_usuario(usuario);
        Subcategoria subcategoria = daoSubcategoria.find(preguntaEncuesta.get_subcategoria().get_id(), Subcategoria.class);
        preguntaEncuestaInsertar.set_subcategoria(subcategoria);
        PreguntaEncuesta resul = daoPreguntaEncuesta.insert(preguntaEncuestaInsertar);

        PreguntaEncuestaDto resultado = MapperPreguntaEncuesta.mapEntityToDto(resul);

        preguntaEncuestaObj = Json.createObjectBuilder()
                .add("id", resultado.getId())
                .add("descripcion", resultado.getDescripcion())
                .add("tipoPregunta", resultado.getTipoPregunta())
                .add("estatus", resultado.getEstatus())
                .add("nombreSubcategoria", subcategoria.get_nombre()).build();

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado= Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","La pregunta se ha registrado exitosamente en el sistema")
                .add("Pregunta", preguntaEncuestaObj).build();

        return resultado;
    }
}
