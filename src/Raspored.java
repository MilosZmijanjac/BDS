import java.time.LocalDate;
import java.time.LocalTime;

public class Raspored extends RasporedInfo {

    private String imePredavaca;
    private String prezimePredavaca;
    private String kategorija;
    private String tip;
    private int rasporedID;

    //Konstruktori
    public Raspored(LocalDate date, LocalTime start, LocalTime end, String name,
                    String surname, String type, String category, int rasporedID) {
        super(date, start, end);
        imePredavaca=name;
        prezimePredavaca=surname;
        kategorija=category;
        tip=type;
        this.rasporedID = rasporedID;
    }

    public Raspored(){
        this(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1),
                "Ime","Prezime","Tip","Kategorija", 0);
    }
    //Geteri i seteri

    public String getImePredavaca() {
        return imePredavaca;
    }

    public void setImePredavaca(String imePredavaca) {
        this.imePredavaca = imePredavaca;
    }

    public String getPrezimePredavaca() {
        return prezimePredavaca;
    }

    public void setPrezimePredavaca(String prezimePredavaca) {
        this.prezimePredavaca = prezimePredavaca;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getTip() {
        return tip;
    }

    public int getRasporedID(){return rasporedID;}

    public void setTip(String tip) {
        this.tip = tip;
    }
    //Ostale metode
    @Override
    public String toString() {
        int startHour = getVrijemePocetka().getHour();
        String AMPM = (startHour <= 11 ? "AM" : "PM");
        if (startHour > 11) startHour -= 12;
        if (startHour == 0) startHour = 12;

        return (startHour + " " + AMPM + ": " + getTip()+getKategorija());
    }

    public String toStringVerbose(){
        return "Datum: " + getDatumPocetka().toString() +
                "\nVrijeme pocetka: " + getVrijemePocetka().toString() +
                "\nVrijeme zavrsetka: " + getVrijemeZavrsetka().toString() +
                "\nIme i prezime: " + getImePredavaca() + " "+getPrezimePredavaca()+
                "\nOpis: " + getTip()+" "+getKategorija()+" kategorija";
    }

    @Override
    public int compareTo(RasporedInfo entry) {
        if (this.getDatumPocetka().isBefore(entry.getDatumPocetka())){
            return -1;
        }
        else if (this.getDatumPocetka().isAfter(entry.getDatumPocetka())){
            return 1;
        }
        else{
            if (this.getVrijemePocetka().isBefore(entry.getVrijemePocetka())){
                return -1;
            }
            else if (this.getVrijemePocetka().isAfter(entry.getVrijemePocetka())){
                return 1;
            }
            else{
                return 0;
            }
        }
    }
}
