import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.MarcaDto;

public class MarcaAPI_Test {

    @Test
    public void pruebaInsertarMarca(){

        ucab.dsw.servicio.MarcaAPI servicio = new ucab.dsw.servicio.MarcaAPI();
        MarcaDto marcaDto = new MarcaDto();

        marcaDto.setNombre("Axe");
        marcaDto.setDescripcion("Desodorante");
        marcaDto.setEstatus("Activo");
        MarcaDto resultado = servicio.addMarca(marcaDto);
        Assert.assertNotEquals( resultado.getId(), 0 );
    }

    @Test
    public void pruebaModificarEstatusMarca(){

        ucab.dsw.servicio.MarcaAPI servicio = new ucab.dsw.servicio.MarcaAPI();
        MarcaDto marcaDto = new MarcaDto();

        marcaDto.setEstatus("Inactivo");
        servicio.modificarEstatusMarca(1, marcaDto);
    }
}
