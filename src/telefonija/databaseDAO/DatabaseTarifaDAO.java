package telefonija.databaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import telefonija.DAO.TarifaDAO;
import telefonija.model.Tarifa;

public class DatabaseTarifaDAO implements TarifaDAO {

	private final Connection conn;

	public DatabaseTarifaDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public Tarifa get(long id) throws Exception {
		Tarifa tarifa = null;
		String sql = "SELECT naziv, opis, cena FROM tarife WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int parm = 0;
			stmt.setLong(++parm, id);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					String naziv = rset.getString(++kolona);
					String opis = rset.getString(++kolona);
					double cena = rset.getDouble(++kolona);
					tarifa = new Tarifa(id, naziv, opis, cena);
				}
			}
		}
		return tarifa;
	}

	@Override
	public Collection<Tarifa> getAll() throws Exception {
		Collection<Tarifa> tarife = new ArrayList<>();
		
		String sql = "SELECT id, naziv, opis, cena FROM tarife";
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String naziv = rset.getString(++kolona);
					String opis = rset.getString(++kolona);
					Double cena = rset.getDouble(++kolona);
					
					Tarifa tarifa = new Tarifa(id, naziv, opis, cena);
					tarife.add(tarifa);		
				}
			}
		}
		return tarife;
	}


}
