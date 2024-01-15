package Student;

import Osoba.Osoba;
import Kursy.Kursy;
import java.util.ArrayList;

public class Student extends Osoba {
    private int indeks;
    private String kierunek;
    private int ktorySemestr;
    private ArrayList<Kursy> listaKursow = new ArrayList<Kursy>();


    public Student(int wiek, String imie, String nazwisko, String pesel, int indeks, String kierunek, int ktorySemestr) {
        super(wiek, imie, nazwisko, pesel);
        this.indeks = indeks;
        this.kierunek = kierunek;
        this.ktorySemestr = ktorySemestr;

    }


    public void dodajKurs(Kursy kurs) {
        Kursy nowyKurs = new Kursy(kurs.getNazwaKursu(), kurs.getWykladowca(), kurs.getCwiczeniowiec());
        getListaKursow().add(nowyKurs);
    }




    @Override
    public String toString(){
        return (super.toString() + "indeks: " + getIndeks() + " " + getKierunek() + "Semestr: " + getKtorySemestr()+ '\n'+ wyswietlKursy());
    }



    public String wyswietlKursy() {
        String kursy = "";
        for (int i = 0; i < getListaKursow().size(); i++) {
            kursy += "Kurs: " + getListaKursow().get(i).getNazwaKursu() + " Wykładowca: " + getListaKursow().get(i).getWykladowca() + " Cwiczeniowiec: " + getListaKursow().get(i).getCwiczeniowiec() + " ";
        }
        return kursy;

    }

    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) {
        this.indeks = indeks;
    }

    public String getKierunek() {
        return kierunek;
    }

    public void setKierunek(String kierunek) {
        this.kierunek = kierunek;
    }

    public int getKtorySemestr() {
        return ktorySemestr;
    }

    public void setKtorySemestr(int ktorySemestr) {
        this.ktorySemestr = ktorySemestr;
    }

    public ArrayList<Kursy> getListaKursow() {
        return listaKursow;
    }
}
