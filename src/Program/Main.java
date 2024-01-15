package Program;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Kursy.Kursy;
import MainPage.Frontend;
import Pracownik.*;
import Osoba.*;
import Student.*;


public class Main {

    public static void main(String[] args) throws IOException {
        Serializacja.odczyt("baza.ser");
        Serializacja.odczytKurs("bazaKursow.ser");


        /*int n = 0;
        while (true){
            System.out.println("Wybierz działanie: \n1-dodaj osobę do bazy danych, \n2-zapisz do pliku,\n3-odczytaj z pliku,\n4-wyświetl listę osób, " +
                    "\n5-wyszukaj studenta po numerze indeksu, \n6-pokaż pracowników z zarobkiem powyżej T, \n7-Wyszukaj pracownika z danym stanowiskiem, \n8-wyjdź");
            n = sc.nextInt();
            if (n==1){
                database.dodajOsobe();

            }
            else if (n==2){
                System.out.println("Podaj nazwę pliku do jakiego chcesz zapisać");
                String nazwaPliku = sc.next();
                Serializacja.zapis(database.getListaOsob(),nazwaPliku);
            }
            else if (n==3){
                System.out.println("Podaj nazwę pliku z jakiego chcesz wczytać");
                String nazwaPliku = sc.next();
                Serializacja.odczyt(nazwaPliku);
            }
            else if (n==4){
                database.wyswietlListeOsob();
            }
            else if (n==5){
                System.out.println("Podaj numer indeksu");
                int nrIndeksu = sc.nextInt();
                database.wyszukajStudentaPoNumerzeIndeksu(nrIndeksu);
            }
            else if (n==6){
                System.out.println("Podaj zarobki");
                int T = sc.nextInt();
                database.wyszukajPracownikowUczelniPoZarobkach(T);
            }
            else if (n==7){
                System.out.println("Podaj zawód");
                String zawod = sc.next();
                database.wyszukajPracownikaAdministracyjnegoPoStanowisku(zawod);
            }
            else if(n==8){
                break;
            }
            else{
                System.out.println("Podaj poprawny numer");
            }*/

        Frontend mainPage = new Frontend();

        }



    }




