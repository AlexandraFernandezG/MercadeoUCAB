package ucab.dsw.comando;

import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.JsonObject;

public abstract class ComandoBase {

    public abstract void execute() throws Exception;

    public abstract JsonObject getResult();
}
