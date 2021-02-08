package ucab.dsw.fabrica;

import ucab.dsw.entidades.EntidadBase;
import ucab.dsw.Response.PreguntasResponse;
import ucab.dsw.Response.UsuarioResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Fabrica<T> {

    /**
     * Esta clase fabrica permitira realizar las instancias para poder crear los objetos
     * necesarias y efectuar las operaciones de comando.
     * @author Emanuel Di Cristofaro
     */

    private Class<T> tipo;

    public Fabrica(Class<T> tipo) {

        this.tipo= tipo;
    }

    public T getInstancia() {
        try {
            return tipo.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T crear(Class<T> tipo) {
        return new Fabrica<T>(tipo).getInstancia();
    }

    public static <T> T crearComandoConEntity(Class<T> tipo, EntidadBase parametro) throws  IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(parametro);
    }

    public static <T> T crearComandoConId(Class<T> tipo, long parametro) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(parametro);
    }

    public static <T> T crearComandoBoth(Class<T> tipo, long id, EntidadBase parametro) throws IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException {
        return (T) tipo.getConstructors()[0].newInstance(id, parametro);
    }

    /**
     * Estos metodos son para instanciar aquellos servicios que utilicen listas de tipo
     * UsuarioResponse para su funcionamiento.
     * @author Emanuel Di Cristofaro
     */
    public static <T> T crearComandoListIdUsuarioResponse(Class<T> tipo, long id, List<UsuarioResponse> listBase) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(id, listBase);
    }

    public static <T> T crearComandoListUsuarioResponse(Class<T> tipo, List<UsuarioResponse> listBase) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(listBase);
    }

    /**
     * Estos metodos son para instanciar aquellos servicios que utilicen listas de tipo
     * PreguntasResponse para su funcionamiento.
     * @author Emanuel Di Cristofaro
     */
    public static <T> T crearComandoListIdPreguntasResponse(Class<T> tipo, long id, List<PreguntasResponse> listBase) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(id, listBase);
    }

    public static <T> T crearComandoListPreguntasResponse(Class<T> tipo, List<PreguntasResponse> listBase) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(listBase);
    }

    /**
     * Este metodo particular para el funcionamiento de un metodo en sugerencias
     * @author Emanuel Di Cristofaro
     */
    public static <T> T crearComandoDosId(Class<T> tipo, long idER, long idE) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(idER, idE);
    }

    /**
     * Este metodo particular para el funcionamiento de un metodo en usuario
     * @author Emanuel Di Cristofaro
     */
    public static <T> T crearComandoCorreo(Class<T> tipo, String parameter) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(parameter);
    }

}
