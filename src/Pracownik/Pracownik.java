package Pracownik;

import Osoba.Osoba;

public abstract class Pracownik extends Osoba {
    private int wynagrodzenie;
    private int ileLatPrzepracowane;

    public Pracownik(int wiek, String imie, String nazwisko, String pesel, int wynagrodzenie, int ileLatPrzepracowane) {
        super(wiek, imie, nazwisko, pesel);
        this.wynagrodzenie = wynagrodzenie;
        this.ileLatPrzepracowane = ileLatPrzepracowane;
    }

    @Override
    public String toString(){
        return (super.toString() + "Wynagrodzenie: " + getWynagrodzenie() + " Długość stażu: " + getIleLatPrzepracowane()+ " ");
    }



    public int getIleLatPrzepracowane() {
        return ileLatPrzepracowane;
    }

    public void setIleLatPrzepracowane(int ileLatPrzepracowane) {
        this.ileLatPrzepracowane = ileLatPrzepracowane;
    }

    public int getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(int wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }
}
