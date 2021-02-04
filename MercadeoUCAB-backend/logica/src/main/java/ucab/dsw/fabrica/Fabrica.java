package ucab.dsw.fabrica;

import ucab.dsw.dtos.DtoBase;
import ucab.dsw.entidades.EntidadBase;

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

    //Estos ultimos estan en revision
    public static <T> T crearComandoListId(Class<T> tipo, long id, List<T> listBase) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(id, listBase);
    }

    public static <T> T crearComandoList(Class<T> tipo, List<T> listBase) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(listBase);
    }
    //////////////////////////////////
}
