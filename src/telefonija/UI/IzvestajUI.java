package telefonija.UI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import telefonija.DAO.PretplataDAO;
import telefonija.DAO.TarifaDAO;
import telefonija.model.Pretplata;
import telefonija.model.StavkaIzvestaja;
import telefonija.model.Tarifa;
import telefonija.util.Konzola;

public class IzvestajUI {

	private static PretplataDAO pretplataDAO;
	private static TarifaDAO tarifaDAO;

	public static void setPretplataDAO(PretplataDAO pretplataDAO) {
		IzvestajUI.pretplataDAO = pretplataDAO;
	}

	public static void setTarifaDAO(TarifaDAO tarifaDAO) {
		IzvestajUI.tarifaDAO = tarifaDAO;
	}

	public static void izvestaj() {

		LocalDateTime pocetniDatum = Konzola.ocitajDateTime("Unesite pocetni datum pretrage: ");
		LocalDateTime krajnjiDatum = Konzola.ocitajDateTime("Unesite krajnji datum pretrage: ");

		try {
			List<String> naziviTarifa = new ArrayList<>();
			Collection<Pretplata> pretplate = pretplataDAO.getAll();
			Collection<Tarifa> tarife = tarifaDAO.getAll();

			for (Tarifa tarifa : tarife) {
				naziviTarifa.add(tarifa.getNaziv());
			}
			List<StavkaIzvestaja> stavke = new ArrayList<>();
			for (String naziv : naziviTarifa) {
				double prihod = 0;
				int duzinaUgovora = 0;
				for (Pretplata pretplata : pretplate) {
					if (pretplata.isDatumUopsegu(pocetniDatum, krajnjiDatum)) {
						if (pretplata.getTarifa().getNaziv().equals(naziv)) {
							prihod += pretplata.getTarifa().getCena() * pretplata.getTrajanjeUgovora();
							if (pretplata.getTrajanjeUgovora() > duzinaUgovora) {
								duzinaUgovora = pretplata.getTrajanjeUgovora();
							}
						}

					}
				}
				StavkaIzvestaja stavka = new StavkaIzvestaja(naziv, prihod, duzinaUgovora);
				stavke.add(stavka);
			}
			stavke.sort(StavkaIzvestaja::comparePrihod);
			try {
				// Kolekcija
				String Headher = String.format("%-20s %-15s %-15s", "Naziv tarife", "Prihod", "Najduzi ugovor");
				System.out.println(Headher);
				System.out.println("==================== =============== ===============");
				for (StavkaIzvestaja stavkaIzvestaja : stavke) {
					String foother = String.format("%-20s %-15s %-15s", stavkaIzvestaja.getNazivTarife(),
							stavkaIzvestaja.getPrihod(), stavkaIzvestaja.getDuzinaUgovora());
					System.out.println(foother);
					System.out.println("-------------------- --------------- ---------------");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
