package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;

public class MarcaServicio_Test {

    @Test
    void testListarMarcas() {
        MarcaServicio listar = new MarcaServicio();

        try {
            Assertions.assertTrue(listar.listarMarcas().size() > 0);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testConsultarMarcas() {
        MarcaServicio encontrar = new MarcaServicio();
        Marca foundedMarca;

        foundedMarca = encontrar.consultarMarca(1);

        try {
            Assertions.assertEquals(1, foundedMarca.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testConsultarMarca_Fails() {
        MarcaServicio servicio = new MarcaServicio();
        Marca marcaParaModificar;

        try {
            // No existe marca con ID=5, por lo que debería dar fallar
            marcaParaModificar = servicio.consultarMarca(5);
        } catch (Exception e) {
            System.out.println("Marca no encontrada");
            Assertions.assertTrue(true);
        }
    }

    @Test
    void testListarMarcasActivas() {
        try {
            Assertions.assertNotNull(new MarcaServicio().marcasActivas());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    @Test
    void testModificarMarca_Nombre() {
        MarcaDto marcaDto = new MarcaDto();
        MarcaServicio servicio = new MarcaServicio();

        Marca marcaParaModificar = null;
        try {
            marcaParaModificar = servicio.consultarMarca(1);
        } catch (NullPointerException e) {
            System.out.println("Marca no encontrada");
        }

        if (marcaParaModificar != null) {
            marcaDto.setNombre("Kris - Robel");
            marcaDto.setDescripcion(marcaParaModificar.get_descripcion());
            marcaDto.setEstatus(marcaParaModificar.get_estatus());

            try {
                servicio.updateMarca(marcaParaModificar.get_id(), marcaDto);
            } catch (Exception e) {
                Assertions.fail(e.getMessage(), e.getCause());
            }
        }
    }

    @Test
    void testModificarMarca_Descripcion() {
        MarcaDto marcaDto = new MarcaDto();
        MarcaServicio servicio = new MarcaServicio();

        Marca marcaParaModificar = null;
        try {
            marcaParaModificar = servicio.consultarMarca(3);
        } catch (NullPointerException e) {
            System.out.println("Marca no encontrada");
        }

        if (marcaParaModificar != null) {
            marcaDto.setNombre(marcaParaModificar.get_nombre());
            marcaDto.setDescripcion("Eius molestiae sed autem illo.");
            marcaDto.setEstatus(marcaParaModificar.get_estatus());

            try {
                servicio.updateMarca(marcaParaModificar.get_id(), marcaDto);
            } catch (Exception e) {
                Assertions.fail(e.getMessage(), e.getCause());
            }
        }
    }

    @Test
    void testModificarMarca_Status() {
        MarcaDto marcaDto = new MarcaDto();
        MarcaServicio servicio = new MarcaServicio();

        Marca marcaParaModificar = null;
        try {
            marcaParaModificar = servicio.consultarMarca(5);
        } catch (NullPointerException e) {
            System.out.println("Marca no encontrada");
        }

        if (marcaParaModificar != null) {
            marcaDto.setNombre(marcaParaModificar.get_nombre());
            marcaDto.setDescripcion(marcaParaModificar.get_descripcion());
            marcaDto.setEstatus("Inactivo");

            try {
                servicio.updateMarca(marcaParaModificar.get_id(), marcaDto);
            } catch (Exception e) {
                Assertions.fail(e.getMessage(), e.getCause());
            }
        }
    }

    @Test
    void testModificarMarca_Todo() {
        MarcaDto marcaDto = new MarcaDto();
        MarcaServicio servicio = new MarcaServicio();
        Marca marcaParaModificar = null;

        try {
            marcaParaModificar = servicio.consultarMarca(4);
        } catch (NullPointerException e) {
            System.out.println("Marca no encontrada");
        }

        marcaDto.setNombre("Conroy, Ortiz and Ruecker");
        marcaDto.setDescripcion("Et eligendi officia consequatur.");
        marcaDto.setEstatus("Activo");

        try {
            if (marcaParaModificar != null) {
                servicio.updateMarca(marcaParaModificar.get_id(), marcaDto);
            }
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    @Test
    void testEliminarMarca() {
        MarcaServicio servicio = new MarcaServicio();
        Marca marcaParaEliminar = null;

        try {
            marcaParaEliminar = servicio.consultarMarca(1);
        } catch (NullPointerException e) {
            System.out.println("Marca no encontrada");
        }

        try {
            if (marcaParaEliminar != null) {
                servicio.deleteMarca(marcaParaEliminar.get_id());
            }
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    @Test
    void testAgregarMarca(){
        MarcaDto marcaDto = new MarcaDto();
        MarcaServicio servicio = new MarcaServicio();
        MarcaDto marcaNueva = null;

        marcaDto.setNombre("Kub - Koss");
        marcaDto.setDescripcion("Sunt nesciunt ratione vel doloremque placeat.");
        marcaDto.setEstatus("Activo");

        try {
            marcaNueva = servicio.addMarca(marcaDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (marcaNueva != null) {
            Marca marcaEncontrada = servicio.consultarMarca(
                    marcaNueva.getId());
            Assertions.assertNotNull(marcaEncontrada);
        } else {
            Assertions.fail();
        }
    }
}
