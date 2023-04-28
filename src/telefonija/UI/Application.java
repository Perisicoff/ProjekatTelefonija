package telefonija.UI;

import java.sql.Connection;
import java.sql.DriverManager;

import telefonija.DAO.PretplataDAO;
import telefonija.DAO.TarifaDAO;
import telefonija.databaseDAO.DatabasePretplataDAO;
import telefonija.databaseDAO.DatabaseTarifaDAO;
import telefonija.util.Meni;
import telefonija.util.Meni.FunkcionalnaStavkaMenija;
import telefonija.util.Meni.IzlaznaStavkaMenija;
import telefonija.util.Meni.StavkaMenija;

public class Application {
	
	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/telefonija?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
				"root", "root");
		
		PretplataDAO pretplataDAO = new DatabasePretplataDAO(conn);
		TarifaDAO tarifaDAO = new DatabaseTarifaDAO(conn);
		
		PretplataUI.setPretplataDAO(pretplataDAO);
		TarifaUI.setTarifaDAO(tarifaDAO);
		IzvestajUI.setPretplataDAO(pretplataDAO);
		IzvestajUI.setTarifaDAO(tarifaDAO);

	}

	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");

			System.exit(1);
		}
	}

	public static void main(String[] args) throws Exception {
		Meni.pokreni("Telefonija",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Izlaz"), new FunkcionalnaStavkaMenija("Ispis tarifa") {

					@Override
					public void izvrsi() {
						TarifaUI.ispisSvihTarifa();
					}
				}, new FunkcionalnaStavkaMenija("Ispis pretplata") { 

					@Override
					public void izvrsi() { PretplataUI.ispisSvihPretplata();
					}

				}, new FunkcionalnaStavkaMenija("Dodavanje pretplate") {

					@Override
					public void izvrsi() { PretplataUI.dodavanjePretplate();
					}

				}, new FunkcionalnaStavkaMenija("Izvestaj") {

					@Override
					public void izvrsi() { IzvestajUI.izvestaj();
					}

				} });
	}
}
