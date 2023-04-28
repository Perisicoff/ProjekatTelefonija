package telefonija.model;

public class StavkaIzvestaja {
	
	private String nazivTarife = "";
	private double prihod;
	private int duzinaUgovora;
	
	public StavkaIzvestaja(String nazivTarife, double prihod, int duzinaUgovora) {
		super();
		this.nazivTarife = nazivTarife;
		this.prihod = prihod;
		this.duzinaUgovora = duzinaUgovora;
	}

	@Override
	public String toString() {
		return "StavkaIzvestaja [nazivTarife=" + nazivTarife + ", prihod=" + prihod + ", duzinaUgovora=" + duzinaUgovora
				+ "]";
	}
	
	public static int comparePrihod(StavkaIzvestaja stavka1, StavkaIzvestaja stavka2) {
		return -Double.compare(stavka1.prihod, stavka2.prihod);
	}

	public String getNazivTarife() {
		return nazivTarife;
	}

	public double getPrihod() {
		return prihod;
	}

	public int getDuzinaUgovora() {
		return duzinaUgovora;
	}

	
}
