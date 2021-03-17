import java.time.LocalDate;

public class Zaposleni {
    String jmbg;
    String ime;
    String prezime;
    String datumRodjenja;
    String pol;
    String adresa;
    String drzava;
    Boolean dozvolaBoravak;
    Boolean dozvolaRad;
    String strucnoObr;
    String dodatnoObr;
    String tip;
    LocalDate pocetakRada;
    LocalDate ugovorORadu;
    int zaposleniRedniBroj;

    public Zaposleni(String jmbg, String ime, String prezime, String datumRodjenja,
                     String pol, String adresa, String drzava, Boolean dozvolaBoravak,
                     Boolean dozvolaRad, String strucnoObr, String dodatnoObr, String tip,
                     LocalDate pocetakRada, LocalDate ugovorORadu, int zaposleniRedniBroj) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
        this.adresa = adresa;
        this.drzava = drzava;
        this.dozvolaBoravak = dozvolaBoravak;
        this.dozvolaRad = dozvolaRad;
        this.strucnoObr = strucnoObr;
        this.dodatnoObr = dodatnoObr;
        this.tip = tip;
        this.pocetakRada = pocetakRada;
        this.ugovorORadu = ugovorORadu;
        this.zaposleniRedniBroj = zaposleniRedniBroj;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public Boolean getDozvolaBoravak() {
        return dozvolaBoravak;
    }

    public void setDozvolaBoravak(Boolean dozvolaBoravak) {
        this.dozvolaBoravak = dozvolaBoravak;
    }

    public Boolean getDozvolaRad() {
        return dozvolaRad;
    }

    public void setDozvolaRad(Boolean dozvolaRad) {
        this.dozvolaRad = dozvolaRad;
    }

    public String getStrucnoObr() {
        return strucnoObr;
    }

    public void setStrucnoObr(String strucnoObr) {
        this.strucnoObr = strucnoObr;
    }

    public String getDodatnoObr() {
        return dodatnoObr;
    }

    public void setDodatnoObr(String dodatnoObr) {
        this.dodatnoObr = dodatnoObr;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public LocalDate getPocetakRada() {
        return pocetakRada;
    }

    public void setPocetakRada(LocalDate pocetakRada) {
        this.pocetakRada = pocetakRada;
    }

    public LocalDate getUgovorORadu() {
        return ugovorORadu;
    }

    public void setUgovorORadu(LocalDate ugovorORadu) {
        this.ugovorORadu = ugovorORadu;
    }

    public int getZaposleniRedniBroj() {
        return zaposleniRedniBroj;
    }

    public void setZaposleniRedniBroj(int zaposleniRedniBroj) {
        this.zaposleniRedniBroj = zaposleniRedniBroj;
    }
}
