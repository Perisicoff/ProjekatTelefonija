package telefonija.databaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import telefonija.DAO.PretplataDAO;
import telefonija.model.Pretplata;
import telefonija.model.Tarifa;

public class DatabasePretplataDAO implements PretplataDAO {

	private final Connection conn;

	public DatabasePretplataDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public Collection<Pretplata> getAll() throws Exception {
		Collection<Pretplata> pretplate = new ArrayList<>();
		String sql = "SELECT p.id, p.broj, p.datumIVreme, p.trajanjeUgovora, t.id, t.naziv, t.opis, t.cena FROM telefonija.pretplate p LEFT JOIN telefonija.tarife t ON p.tarifaId = t.id";
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long pId = rset.getLong(++kolona);
					String pbroj = rset.getString(++kolona);
					LocalDateTime datum = rset.getTimestamp(++kolona).toLocalDateTime();
					int pTrajanjeUgovora = rset.getInt(++kolona);
					
					Pretplata pretplata = new Pretplata(pId, null, pbroj, datum, pTrajanjeUgovora);
					
					long tId = rset.getLong(++kolona);
					if (tId != 0) {
						String tNaziv = rset.getString(++kolona);
						String tOpis = rset.getString(++kolona);
						double tCena = rset.getDouble(++kolona);
						Tarifa tarifa = new Tarifa(tId, tNaziv, tOpis, tCena);
						tarifa.addPretplatu(pretplata);
						pretplate.add(pretplata);
					}
				}
			}
		}
		return pretplate;
	}

	@Override
	public void add(Pretplata pretplata) throws Exception {

		String sql = "INSERT INTO pretplate (tarifaId, broj, datumIVreme, trajanjeUgovora) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int kolona = 0;
			stmt.setLong(++kolona, pretplata.getTarifa().getId());
			stmt.setString(++kolona, pretplata.getBroj());
			stmt.setTimestamp(++kolona, Timestamp.valueOf(pretplata.getDatumPocetka()));
			stmt.setInt(++kolona, pretplata.getTrajanjeUgovora());
			stmt.executeUpdate();

		}

	}

}
