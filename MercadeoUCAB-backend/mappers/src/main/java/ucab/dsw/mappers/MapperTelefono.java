package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.PruebaExcepcion;

public class MapperTelefono {

    /**
     * Esta clase mapper se encargara de ejecutar las tranformaciones de dto a entity
     * para las operaciones de insert, uptade de telefono
     * @author Emanuel Di Cristofaro
     */

    public static Telefono mapDtoToEntityInsert(TelefonoDto telefonoDto){

        Telefono telefonoEntity = new Telefono();
        DaoInformacion daoInformacion = new DaoInformacion();

        telefonoEntity.set_numero(telefonoDto.getNumero());
        telefonoEntity.set_estatus(telefonoDto.getEstatus());
        Informacion informacion = daoInformacion.find(telefonoDto.getInformacion().getId(), Informacion.class);
        telefonoEntity.set_informacion(informacion);

        return telefonoEntity;
    }

    public static TelefonoDto mapEntityToDto(Telefono telefonoEntity) throws PruebaExcepcion {

        TelefonoDto telefonoDto = new TelefonoDto();

        telefonoDto.setId(telefonoEntity.get_id());
        telefonoDto.setNumero(telefonoEntity.get_numero());
        telefonoDto.setEstatus(telefonoEntity.get_estatus());

        return telefonoDto;
    }
}
