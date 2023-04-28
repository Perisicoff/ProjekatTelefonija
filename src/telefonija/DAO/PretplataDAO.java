package telefonija.DAO;

import java.util.Collection;

import telefonija.model.Pretplata;

public interface PretplataDAO {

	public Collection<Pretplata> getAll() throws Exception;
	public void add(Pretplata pretplata) throws Exception;
}
