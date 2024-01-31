package obliczStypendiumStrategy;

import BazaDanych.BazaDanych;
import Osoba.Osoba;
import Pracownik.PracownikUczelni;
import Student.Student;

import java.io.Serializable;

public interface Stypendium extends Serializable {

    public int obliczStypendium();

    public String toStringStypendium();

}
