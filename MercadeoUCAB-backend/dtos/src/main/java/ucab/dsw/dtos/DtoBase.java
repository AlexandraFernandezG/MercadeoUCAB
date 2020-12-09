package ucab.dsw.dtos;
import ucab.dsw.excepciones.PruebaExcepcion;

public class DtoBase
{
    private long _id;
    private String _estatus;

    public DtoBase(long id ) throws Exception
    {
        setId( id );
    }

    public DtoBase(String estatus) throws Exception{
        set_estatus( estatus );
    }

    public DtoBase(){}

    public long getId()
    {
        return _id;
    }

    public void setId( long id ) throws PruebaExcepcion
    {
        if ( id >= 0 )
        {
            _id = id;
        }
        else
        {
            throw new PruebaExcepcion( );
        }
    }

    public String get_estatus() {
        return _estatus;
    }

    public void set_estatus(String _estatus) {
        this._estatus = _estatus;
    }
}
