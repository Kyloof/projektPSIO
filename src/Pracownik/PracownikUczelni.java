package Pracownik;

public class PracownikUczelni extends Pracownik{
    private String stanowisko;

    public PracownikUczelni(int wiek, String imie, String nazwisko, String pesel, int wynagrodzenie, int ileLatPrzepracowane, String stanowisko) {
        super(wiek, imie, nazwisko, pesel, wynagrodzenie, ileLatPrzepracowane);
        this.stanowisko = stanowisko;
    }
    @Override
    public String toString(){
        return (super.toString() + getStanowisko());
    }
    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }
}
