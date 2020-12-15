package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Lugar;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/lugar" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LugarAPI extends AplicacionBase{

    //Obtener todos los lugares
    @GET
    @Path("/allLugares")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Lugar> listarLugares() throws NullPointerException{

        DaoLugar daoLugar = new DaoLugar();

        try {
            return daoLugar.findAll(Lugar.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }


    }

    //Buscar un lugar
    @GET
    @Path("/consultarLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Lugar consultarLugar(@PathParam("id") long id) throws NullPointerException {

        DaoLugar daoLugar = new DaoLugar();

        try {
            return daoLugar.find(id, Lugar.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }

    }

    //Listar solamente los paises
    @GET
    @Path("/lugarPais")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Lugar> listarPaises() throws NullPointerException {

        DaoLugar daoLugar = new DaoLugar();
        List<Lugar> listaLugares = daoLugar.findAll(Lugar.class);
        List<Lugar> listaPaises = new ArrayList<Lugar>();

        try {

            for (Lugar lugar : listaLugares) {

                if (lugar.get_tipo().equals("Pais")) {

                    listaPaises.add(lugar);
                }
            }

            return listaPaises;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Listar solamente los estados
    @GET
    @Path("/lugarEstado")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Lugar> listarEstados() throws NullPointerException {

        DaoLugar daoLugar = new DaoLugar();
        List<Lugar> listaLugares = daoLugar.findAll(Lugar.class);
        List<Lugar> listaEstados = new ArrayList<Lugar>();

        try {

            for (Lugar lugar : listaLugares) {

                if (lugar.get_tipo().equals("Estado")) {

                    listaEstados.add(lugar);
                }
            }

            return listaEstados;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Listar solamente las urbanizaciones
    @GET
    @Path("/lugarUrbanizacion")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Lugar> listarUrbanizaciones() throws NullPointerException {

        DaoLugar daoLugar = new DaoLugar();
        List<Lugar> listaLugares = daoLugar.findAll(Lugar.class);
        List<Lugar> listaUrbanizaciones = new ArrayList<Lugar>();

        try {

            for (Lugar lugar : listaLugares) {

                if (lugar.get_tipo().equals("Urbanizacion")) {

                    listaUrbanizaciones.add(lugar);
                }
            }

            return listaUrbanizaciones;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Listar jerarquia (Ejemplo> Mandas id pais y te devuelve sus estados)
    @GET
    @Path("/jerarquiaLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Lugar> jerarquiaLugar(@PathParam("id") long id) throws NullPointerException{

        DaoLugar daoLugar = new DaoLugar();
        List<Lugar> listaLugares = daoLugar.findAll(Lugar.class);
        List<Lugar> listaJerarquia = new ArrayList<Lugar>();

        try {

            for (Lugar lugar : listaLugares) {

            if(lugar.get_tipo().equals("Estado")) {

                if (id == lugar.get_lugar().get_id()) {

                    long idFK = lugar.get_id();
                    Lugar lugar_encontrado = daoLugar.find(idFK, Lugar.class);
                    listaJerarquia.add(lugar_encontrado);
                }

            } else if(lugar.get_tipo().equals("Urbanizacion")){

                if (id == lugar.get_lugar().get_id()) {

                    long idFK = lugar.get_id();
                    Lugar lugar_encontrado = daoLugar.find(idFK, Lugar.class);
                    listaJerarquia.add(lugar_encontrado);
                }
            }

            }

            return listaJerarquia;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Agregar un lugar
    @POST
    @Path("/addLugar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public LugarDto addLugar(LugarDto lugarDto){

        LugarDto resultado = new LugarDto();

        try {

            DaoLugar daoLugar = new DaoLugar();
            Lugar lugar = new Lugar();
            lugar.set_nombre(lugarDto.getNombre());
            lugar.set_tipo(lugarDto.getTipo());
            lugar.set_categoriaSocioEconomica(lugarDto.getCategoriaSocioEconomica());
            lugar.set_estatus(lugarDto.getEstatus());
            Lugar lugar_fk = new Lugar(lugarDto.getLugar().getId());
            lugar.set_lugar(lugar_fk);
            Lugar resul = daoLugar.insert(lugar);
            resultado.setId(resul.get_id());

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }

        return resultado;
    }

    //Actualizar Lugar
    @PUT
    @Path("/updateLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateLugar(@PathParam("id") long id, LugarDto lugarDto){

        DaoLugar daoLugar = new DaoLugar();
        Lugar lugar_modificar = daoLugar.find(id, Lugar.class);

        if(lugar_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            lugar_modificar.set_nombre(lugarDto.getNombre());
            lugar_modificar.set_tipo(lugarDto.getTipo());
            lugar_modificar.set_categoriaSocioEconomica(lugarDto.getCategoriaSocioEconomica());
            lugar_modificar.set_estatus(lugarDto.getEstatus());
            daoLugar.update(lugar_modificar);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(lugar_modificar).build();
    }

    //Eliminar lugar
    @DELETE
    @Path("/deleteLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteLugar(@PathParam("id") long id){

        DaoLugar daoLugar = new DaoLugar();
        Lugar lugar_eliminar = daoLugar.find(id, Lugar.class);

        if(lugar_eliminar== null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            daoLugar.delete(lugar_eliminar);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(lugar_eliminar).build();
    }

}
