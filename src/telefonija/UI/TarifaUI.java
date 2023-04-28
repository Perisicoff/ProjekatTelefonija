package telefonija.UI;

import java.util.Collection;
import telefonija.DAO.TarifaDAO;
import telefonija.model.Tarifa;

public class TarifaUI {

	private static TarifaDAO tarifaDAO;

	public static void setTarifaDAO(TarifaDAO tarifaDAO) {
		TarifaUI.tarifaDAO = tarifaDAO;
	}

	public static void ispisSvihTarifa() {

		try {
			Collection<Tarifa> tarife = tarifaDAO.getAll();
			String Headher = String.format("%-10s %-15s %-75s %-10s", "ID", "Naziv", "Opis", "Cena");
			System.out.println(Headher);
			System.out.println(
					"========== =============== =========================================================================== ==========");
			for (Tarifa tarifa : tarife) {
				String foother = String.format("%-10s %-15s %-75s %-10s", tarifa.getId(), tarifa.getNaziv(),
						tarifa.getOpis(), tarifa.getCena());
				System.out.println(foother);
				System.out.println(
						"---------- --------------- --------------------------------------------------------------------------- ----------");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Tarifa pretragaTarife(long id) {
		Tarifa tarifa = null;
		try {
			tarifa = tarifaDAO.get(id);
			if (tarifa == null) {
				System.out.println("ID " + id + " ne postoji u nasoj bazi!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tarifa;

	}
}
