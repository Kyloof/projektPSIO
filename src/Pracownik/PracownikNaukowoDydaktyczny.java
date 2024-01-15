package Pracownik;

public class PracownikNaukowoDydaktyczny extends Pracownik{
    private String tytulNaukowy;


    public PracownikNaukowoDydaktyczny(int wiek, String imie, String nazwisko, String pesel, int wynagrodzenie, int ileLatPrzepracowane, String tytulNaukowy) {
        super(wiek, imie, nazwisko, pesel, wynagrodzenie, ileLatPrzepracowane);
        this.tytulNaukowy = tytulNaukowy;

    }

    @Override
    public String toString(){
        return (super.toString() + "Tytul naukowy: " + getTytulNaukowy());
    }


    public String getTytulNaukowy() {
        return tytulNaukowy;
    }

    public void setTytulNaukowy(String tytulNaukowy) {
        this.tytulNaukowy = tytulNaukowy;
    }




}
