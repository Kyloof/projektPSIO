package Program;
import java.io.IOException;

import BazaDanych.Serializacja;
import MainPage.Frontend;


public class Main {

    public static void main(String[] args) throws IOException {
        Serializacja.odczyt("baza.ser");
        Serializacja.odczytKurs("bazaKursow.ser");

        Frontend mainPage = new Frontend();
        }
    }



