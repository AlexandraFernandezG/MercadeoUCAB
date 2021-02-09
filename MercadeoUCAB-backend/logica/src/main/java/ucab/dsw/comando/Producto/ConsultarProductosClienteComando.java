package ucab.dsw.comando.Producto;

import ucab.dsw.Response.ProductoResponse;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.comando.ComandoBase;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ConsultarProductosClienteComando extends ComandoBase {

    public long id;
    public JsonArrayBuilder productos = Json.createArrayBuilder();

    public ConsultarProductosClienteComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoProducto daoProducto = Fabrica.crear(DaoProducto.class);

        List<Object[]> listaProductos = daoProducto.listarProductosCliente(id);

        List<ProductoResponse> listaProductosCliente = new ArrayList<>(listaProductos.size());

        for (Object[] pc: listaProductos){

            listaProductosCliente.add(new ProductoResponse((long)pc[0], (String)pc[1], (String)pc[2], (String)pc[3]));
        }

        for(ProductoResponse obj: listaProductosCliente){

            JsonObject producto = Json.createObjectBuilder().add("id", obj.getIdProducto())
                    .add("nombre", obj.getNombreProducto())
                    .add("descripcion", obj.getDescripcionProducto())
                    .add("estatus", obj.getEstatusProducto()).build();


            productos.add(producto);
        }

    }

    @Override
    public JsonObject getResult() {

        JsonObject resultado = Json.createObjectBuilder()
                .add("Productos", productos).build();

        return resultado;
    }
}
