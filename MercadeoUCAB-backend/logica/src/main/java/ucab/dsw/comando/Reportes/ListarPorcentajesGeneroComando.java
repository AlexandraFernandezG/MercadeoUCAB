package ucab.dsw.comando.Reportes;

import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.PreguntaEstudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ListarPorcentajesGeneroComando extends ComandoBase {

    public long id;
    JsonObject dataAnalisis;

    public ListarPorcentajesGeneroComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        //Obtener las respuestas del estudio

        DaoPreguntaEstudio daoPreguntaEstudio = Fabrica.crear(DaoPreguntaEstudio.class);
        DaoRespuesta daoRespuesta = Fabrica.crear(DaoRespuesta.class);
        List<Respuesta> allRespuesta = daoRespuesta.findAll(Respuesta.class);
        List<PreguntaEstudio> allPreguntaEstudio = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
        List<Respuesta> listaRespuestasEstudio = new ArrayList<>();

        for (PreguntaEstudio preguntaEstudio: allPreguntaEstudio){

            if (preguntaEstudio.get_estudio().get_id() == id){

                long idPE = preguntaEstudio.get_id();

                for (Respuesta respuesta: allRespuesta){

                    if (respuesta.get_preguntaEstudio().get_id() == idPE){

                        listaRespuestasEstudio.add(respuesta);
                    }
                }

            }
        }

        //Ahora calculamos la cantidad de personas por genero con las respuestas

        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
        DaoInformacion daoInformacion = Fabrica.crear(DaoInformacion.class);
        List<Usuario> allUsuario = daoUsuario.findAll(Usuario.class);
        List<Informacion> allInformacion = daoInformacion.findAll(Informacion.class);
        int contador_masculino = 0;
        int contador_femenino = 0;

        for (Respuesta respuesta: listaRespuestasEstudio){

            long idU = respuesta.get_usuario().get_id();

            for(Usuario usuario: allUsuario){

                if(usuario.get_id() == idU){

                    for(Informacion informacion: allInformacion){

                        if(informacion.get_usuario().get_id() == idU){

                            if(informacion.get_genero().equals("Masculino")){

                                contador_masculino = contador_masculino + 1;
                            }
                            else if (informacion.get_genero().equals("Femenino")){

                                contador_femenino = contador_femenino + 1;
                            }
                        }
                    }
                }
            }
        }

        dataAnalisis = Json.createObjectBuilder()
                .add("estado",200)
                .add("mensaje","Cantidades de hombres y mujeres por pregunta")
                .add("Porcentaje Hombres", contador_masculino)
                .add("Porcentaje Mujeres", contador_femenino).build();

    }

    @Override
    public JsonObject getResult() {

        return dataAnalisis;
    }
}
