package telefonija.UI;

import java.time.LocalDateTime;
import java.util.Collection;

import telefonija.DAO.PretplataDAO;
import telefonija.model.Pretplata;
import telefonija.model.Tarifa;
import telefonija.util.Konzola;

public class PretplataUI {

	private static PretplataDAO pretplataDAO;

	public static void setPretplataDAO(PretplataDAO pretplataDAO) {
		PretplataUI.pretplataDAO = pretplataDAO;
	}
	
	public static void ispisSvihPretplata() {
		try {
			Collection<Pretplata> pretplate = pretplataDAO.getAll();
			for (Pretplata pretplata : pretplate) {
				System.out.println(pretplata);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void dodavanjePretplate() {
		TarifaUI.ispisSvihTarifa();
		long id = Konzola.ocitajLong("Unesite id ponudjenih tarifa: ");
		Tarifa tarifa = TarifaUI.pretragaTarife(id);
		if (tarifa == null) {
			return;
		}
		String broj = Konzola.ocitajString("Unesite broj telefona: ");
		boolean provera = proveraPostojanjaTarife(broj);
		if (provera == false) {
			return;
		} else {
			LocalDateTime datum = Konzola.ocitajDateTime("Unesite datum pocetka pretplate: ");
			int trajanjeUgovora = 0;
			while (!proveraTrajanjaUgovora(trajanjeUgovora)) {
				trajanjeUgovora = Konzola.ocitajInt("Unesite trajanje ugovora: ");
			}
			Pretplata pretplata = new Pretplata(tarifa, broj, datum, trajanjeUgovora);
			pretplata.setTarifa(tarifa);
			try {
				pretplataDAO.add(pretplata);
				System.out.println("Uspesno ste dodali novu pretplatu!");
			} catch (Exception e) {
				System.out.println("Doslo je do greske!");
				e.printStackTrace();
			}

		}

	}
	
	public static boolean proveraTrajanjaUgovora(int broj) {
		if (broj == 12 || broj == 24 || broj == 36) {
			return true;
		} else {
			System.out.println("Trajanje ugovora mora bit 12/24/36 meseci,unesite ponovo!");
		}
		
		return false;
		
	}
	
	public static boolean proveraPostojanjaTarife(String broj) {
		try {
			Collection<Pretplata> pretplate = pretplataDAO.getAll();
			for (Pretplata pretplata : pretplate) {
				if (pretplata.getBroj().equals(broj)) {
					if (pretplata.getDatumPocetka().plusMonths(pretplata.getTrajanjeUgovora()) != null) {
						System.out.println("Za zadati broj vec postoji pretplata!");
						return false;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
