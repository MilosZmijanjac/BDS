import java.time.LocalDate;
import java.time.LocalTime;

public abstract class RasporedInfo implements Comparable<RasporedInfo>{
 /*Datum i vrijeme pocetka i zavrsetka stavke na rasporedu*/
        private LocalDate datumPocetka;
        private LocalTime vrijemePocetka;
        private LocalTime vrijemeZavrsetka;

        //Konstruktori
    public RasporedInfo(LocalDate date, LocalTime start, LocalTime end){
            datumPocetka = date;
            vrijemePocetka = start;
            vrijemeZavrsetka = end;
        }
      //Geteri i seteri
        public LocalDate getDatumPocetka() {
            return datumPocetka;
        }

        public void setDatumPocetka(LocalDate startDate) {
            this.datumPocetka = startDate;
        }

        public LocalTime getVrijemePocetka() {
            return vrijemePocetka;
        }

        public void setVrijemePocetka(LocalTime startTime) {
            this.vrijemePocetka = startTime;
        }

        public LocalTime getVrijemeZavrsetka() {
            return vrijemeZavrsetka;
        }

        public void setVrijemeZavrsetka(LocalTime endTime) {
            this.vrijemeZavrsetka = endTime;
        }

        //Apstraktne metode
        @Override
        public abstract String toString();

        /*
         Apstraktna metoda koja sluzi za upoređivanje objekata
         od klase koja naslijedjuje klasu RasporedInfo.
         Nece biti potrebe za ovom metodom ako iz baze podataka
         selektujemo sortirano
        */
        @Override
        public abstract int compareTo(RasporedInfo entry);


}
