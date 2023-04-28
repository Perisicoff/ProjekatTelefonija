package telefonija.DAO;

import java.util.Collection;

import telefonija.model.Tarifa;

public interface TarifaDAO {

	public Tarifa get(long id) throws Exception;
	public Collection<Tarifa> getAll() throws Exception;
}
