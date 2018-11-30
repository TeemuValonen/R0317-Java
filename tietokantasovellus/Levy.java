package tietokantasovellus;

public class Levy {
	private String levynNimi;
	private String artisti;
	private int julkaisuVuosi;
	
	
	public String toString() {
		return "Levy [levynNimi=" + levynNimi + ", artisti=" + artisti + ", julkaisuVuosi=" + julkaisuVuosi + "]";
	}
	public String getLevynNimi() {
		return levynNimi;
	}
	public void setLevynNimi(String leynNimi) {
		this.levynNimi = leynNimi;
	}
	public String getArtisti() {
		return artisti;
	}
	public void setArtisti(String artisti) {
		this.artisti = artisti;
	}
	public int getJulkaisuVuosi() {
		return julkaisuVuosi;
	}
	public void setJulkaisuVuosi(int julkaisuVuosi) {
		this.julkaisuVuosi = julkaisuVuosi;
	}
	
	public Levy(String levynNimi, String artisti, int julkaisuVuosi) {
		this.levynNimi = levynNimi;
		this.artisti = artisti;
		this.julkaisuVuosi = julkaisuVuosi;
	}
	
}
