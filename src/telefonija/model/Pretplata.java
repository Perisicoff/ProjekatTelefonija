package telefonija.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Pretplata {

	private long id;
	Tarifa tarifa;
	private String broj = "";
	private LocalDateTime datumPocetka = LocalDateTime.now();
	private int trajanjeUgovora;

	public Pretplata() {
		super();
	}

	public Pretplata(Tarifa tarifa, String broj, LocalDateTime datumPocetka, int trajanjeUgovora) {
		super();
		this.tarifa = tarifa;
		this.broj = broj;
		this.datumPocetka = datumPocetka;
		this.trajanjeUgovora = trajanjeUgovora;
	}

	public Pretplata(long id, Tarifa tarifa, String broj, LocalDateTime datumPocetka, int trajanjeUgovora) {
		super();
		this.id = id;
		this.tarifa = tarifa;
		this.broj = broj;
		this.datumPocetka = datumPocetka;
		this.trajanjeUgovora = trajanjeUgovora;
	}
	
	public boolean isDatumUopsegu(LocalDateTime pocetak, LocalDateTime kraj) {
		return datumPocetka.compareTo(pocetak) >= 0 && datumPocetka.compareTo(kraj) <= 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pretplata other = (Pretplata) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ID pretplate: " + id + "   Naziv tarife: " + tarifa.getNaziv() + "   Broj: " + broj + "   Datum pocetka: "
				+ datumPocetka + "   Trajanje ugovora:" + trajanjeUgovora + "   Ukupna cena:" + ukupnaCenaPretplate();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
		tarifa.pretplate.add(this);
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public LocalDateTime getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(LocalDateTime datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public int getTrajanjeUgovora() {
		return trajanjeUgovora;
	}

	public void setTrajanjeUgovora(int trajanjeUgovora) {
		this.trajanjeUgovora = trajanjeUgovora;
	}

	public double ukupnaCenaPretplate() {
		return tarifa.getCena() * trajanjeUgovora;
	}

}
