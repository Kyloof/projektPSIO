package MainPage;

import Kursy.Kursy;
import Osoba.Osoba;
import Pracownik.PracownikNaukowoDydaktyczny;
import Pracownik.PracownikUczelni;
import Program.BazaDanych;
import Program.Serializacja;
import Student.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Frontend extends JFrame implements ActionListener {

    JButton saveButton = new JButton("Zapisz");
    JButton loadButton = new JButton("Odczytaj");
    JButton wyszukajStudentaButton = new JButton("Wyszukaj Studenta");
    JButton wyszukajPracownikowButton = new JButton("Wyszukaj Pracowników");
    JButton wyszukajPracownikaAdminButton = new JButton("Wyszukaj Pracownika Administracyjnego");


    CardLayout cardLayout = new CardLayout();
    JPanel mainPage = new JPanel(cardLayout);
    JList<String> listaOsobJList = new JList<>();
    JPanel functionalityPage = new JPanel();
    JPanel showPeoplePage = new JPanel();
    JPanel addPersonPage = new JPanel();
    JPanel pokazOsobyPanel = new JPanel();


    JButton addPersonButton = new JButton("Dodaj osobę");
    JButton showPeopleButton = new JButton("Wyswietl osoby");
    JButton functionalityButton = new JButton("Wejdź do ");
    JTextField studentIndexField = new JTextField("Wprowadź indeks");
    JTextField employeeJobField = new JTextField("Wprowadź pracę");
    JTextField paycheckField = new JTextField("Wprowadź zarobki");

    public Frontend() {
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        JPanel saveLoadPanel = new JPanel();
        saveLoadPanel.add(saveButton);
        saveLoadPanel.add(loadButton);

        wyszukajStudentaButton.addActionListener(this);
        wyszukajPracownikowButton.addActionListener(this);
        wyszukajPracownikaAdminButton.addActionListener(this);


        addPersonPage.setLayout(new FlowLayout());
        addPersonPage.add(paycheckField);

        showPeoplePage.setLayout(new FlowLayout());
        showPeoplePage.add(employeeJobField);
        showPeoplePage.add(paycheckField);

        functionalityPage.setLayout(new BoxLayout(functionalityPage, BoxLayout.Y_AXIS));

        JPanel studentIndexSearchPanel = new JPanel();
        studentIndexSearchPanel.setLayout(new BoxLayout(studentIndexSearchPanel, BoxLayout.X_AXIS));
        studentIndexSearchPanel.add(wyszukajStudentaButton);
        studentIndexSearchPanel.add(studentIndexField);

        JPanel pracownikZarobkiSearch = new JPanel();
        pracownikZarobkiSearch.setLayout(new BoxLayout(pracownikZarobkiSearch, BoxLayout.X_AXIS));
        pracownikZarobkiSearch.add(wyszukajPracownikowButton);
        pracownikZarobkiSearch.add(paycheckField);

        JPanel pracownikStanowiskoSearch = new JPanel();
        pracownikStanowiskoSearch.setLayout(new BoxLayout(pracownikStanowiskoSearch, BoxLayout.X_AXIS));
        pracownikStanowiskoSearch.add(wyszukajPracownikaAdminButton);
        pracownikStanowiskoSearch.add(employeeJobField);

        functionalityPage.add(studentIndexSearchPanel);
        functionalityPage.add(pracownikStanowiskoSearch);
        functionalityPage.add(pracownikZarobkiSearch);

        mainPage.add(addPersonPage, "Dodaj osobę");
        mainPage.add(showPeoplePage, "Wyswietl osoby");
        mainPage.add(functionalityPage, "Wejdź do funkcji");


        addPersonButton.addActionListener(this);
        showPeopleButton.addActionListener(this);
        functionalityButton.addActionListener(this);



        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveLoadPanel);
        buttonPanel.add(addPersonButton);
        buttonPanel.add(showPeopleButton);
        buttonPanel.add(functionalityButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(mainPage, BorderLayout.CENTER);
        stronaDodajOsobe addPersonPage = new stronaDodajOsobe(this);
        mainPage.add(addPersonPage, "DodajOsobe");

        mainPage.add(pokazOsobyPanel,"PokazOsoby");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500,500));
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    public void Start() {
        cardLayout.show(mainPage, "Strona Glowna");
    }
    private void pokazListeOsob() {
        ArrayList<Osoba> listaOsob = BazaDanych.getListaOsob();
        StringBuilder stringBuilder = new StringBuilder("<html><body>");

        for (Osoba osoba : listaOsob) {
            if (osoba instanceof Student)
                stringBuilder.append(((Student) osoba).toString()).append("<br>");
            else if (osoba instanceof PracownikUczelni)
                stringBuilder.append(((PracownikUczelni) osoba).toString()).append("<br>");
            else if(osoba instanceof PracownikNaukowoDydaktyczny)
                stringBuilder.append(((PracownikNaukowoDydaktyczny) osoba).toString()).append("<br>");
            else
                stringBuilder.append(osoba.toString()).append("<br>");


        }

        stringBuilder.append("</body></html>");
        JLabel label = new JLabel(stringBuilder.toString());
        pokazOsobyPanel.removeAll();
        pokazOsobyPanel.add(label);
        pokazOsobyPanel.revalidate();
        pokazOsobyPanel.repaint();
    }

    private void wyswietlWynikiWyszukiwania(String wyniki) {
        JLabel label = new JLabel(wyniki);
        pokazOsobyPanel.removeAll();
        pokazOsobyPanel.add(label);
        pokazOsobyPanel.revalidate();
        pokazOsobyPanel.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            Serializacja.zapis(BazaDanych.getListaOsob(), "baza.ser");
            Serializacja.zapisKurs(BazaDanych.getListaKursow(), "bazaKursow.ser");

        } else if (e.getSource() == loadButton) {
            Serializacja.odczyt("baza.ser");
            Serializacja.odczytKurs("bazaKursow.ser");
        }
        if (e.getSource() == wyszukajStudentaButton) {
            int numerIndeksu = Integer.parseInt(studentIndexField.getText());
            String result = BazaDanych.wyszukajStudentaPoNumerzeIndeksu(numerIndeksu);
            wyswietlWynikiWyszukiwania(result);
            cardLayout.show(mainPage, "PokazOsoby");
        }

        if (e.getSource() == wyszukajPracownikowButton) {
            double zarobki = Double.parseDouble(paycheckField.getText());
            String result = BazaDanych.wyszukajPracownikowUczelniPoZarobkach(zarobki);
            wyswietlWynikiWyszukiwania(result);
            cardLayout.show(mainPage, "PokazOsoby");
        }

        if (e.getSource() == wyszukajPracownikaAdminButton) {
            String stanowisko = employeeJobField.getText();
            String result = BazaDanych.wyszukajPracownikaAdministracyjnegoPoStanowisku(stanowisko);
            wyswietlWynikiWyszukiwania(result);
            cardLayout.show(mainPage, "PokazOsoby");
        }

        if (e.getSource() == addPersonButton) {
            cardLayout.show(mainPage, "DodajOsobe");
        }

        if (e.getSource() == showPeopleButton) {
            pokazListeOsob();
            cardLayout.show(mainPage,"PokazOsoby");
        }

        if (e.getSource() == functionalityButton) {
            cardLayout.show(mainPage, "Wejdź do funkcji");
        }
    }
}


class stronaDodajOsobe extends JPanel implements ActionListener {
    private ArrayList<Kursy> dostepneKursy = new ArrayList<Kursy>();

    private JList<Kursy> listaKursowJList = new JList<>();
    private JButton dodajKursDoStudentaButton = new JButton("Dodaj wybrane kursy do studenta");
    private Frontend frontend;

    private JPanel kursyPanel = new JPanel();
    private JTextField nazwaKursuField = new JTextField("Wprowadź nazwę kursu");
    private JTextField wykladowcaField = new JTextField("WWprowadź wykładowcę");
    private JButton dodajKursButton = new JButton("Dodaj kurs");
    private CardLayout cardLayout = new CardLayout();

    private JPanel wiekPanel = new JPanel();

    private JPanel imiePanel = new JPanel();
    private JPanel nazwiskoPanel = new JPanel();
    private JPanel kierunekPanel = new JPanel();
    private JPanel peselPanel = new JPanel();
    private JPanel indeksPanel = new JPanel();
    private JPanel semestrPanel = new JPanel();
    private JPanel wynagrodzeniePanel = new JPanel();
    private JPanel stanowiskoPanel = new JPanel();
    private JPanel tytulNaukowyPanel = new JPanel();
    private JPanel stazPanel = new JPanel();

    private JPanel studentPrzyciskPanel = new JPanel();
    private JPanel PUPrzyciskPanel = new JPanel();
    private JPanel PNDPrzyciskPanel = new JPanel();

    private JPanel contentPanel = new JPanel();
    private JPanel studentContentPanel = new JPanel();
    private JPanel PNDContentPanel = new JPanel();
    private JPanel PUContentPanel = new JPanel();
    private JLabel dodajOsobeTytul = new JLabel("Kogo chcesz dodać?");

    private JButton dodajStudentaPrzycisk = new JButton("Dodaj Studenta");
    private JButton dodajPNDPrzycisk = new JButton("Dodaj Pracownika Naukowo-Dydaktycznego");
    private JButton dodajPUPrzycisk = new JButton("Dodaj Pracownika Uczelni");
    private JButton StudentZatwierdzDodaniePrzycisk = new JButton("Zatwierdź");
    private JButton PUZatwierdzDodaniePrzycisk = new JButton("Zatwierdź");

    private JButton PNDZatwierdzDodaniePrzycisk = new JButton("Zatwierdź");

    private JButton wrocDoStronyGlownejPrzycisk = new JButton("Wróć");

    JTextField cwiczeniowiecField = new JTextField("Wprowadz Nauczyciela od cwiczen");

    private JLabel labelWiek = new JLabel("Wiek: ");
    private JLabel labelImie = new JLabel("Imię: ");
    private JLabel labelNazwisko = new JLabel("Nazwisko: ");
    private JLabel labelPesel = new JLabel("Pesel: ");
    private JLabel labelIndeks = new JLabel("Indeks: ");
    private JLabel labelKierunek = new JLabel("Kierunek: ");
    private JLabel labelSemestr = new JLabel("Semestr: ");
    private JLabel labelWynagrodzenie = new JLabel("Wynagrodzenie: ");
    private JLabel labelStaz = new JLabel("Staż: ");
    private JLabel labelTytulNaukowy = new JLabel("Tytuł naukowy: ");
    private JLabel labelStanowisko = new JLabel("Stanowisko: ");


    private JTextField textFieldWiek = new JTextField();
    private DefaultListModel<Kursy> modelListyKursow = new DefaultListModel<>();

    private JTextField textFieldImie = new JTextField();
    private JTextField textFieldNazwisko = new JTextField();
    private JTextField textFieldPesel = new JTextField();
    private JTextField textFieldIndeks = new JTextField();
    private JTextField textFieldKierunek = new JTextField();
    private JTextField textFieldSemestr = new JTextField();
    private JTextField textFieldWynagrodzenie = new JTextField();
    private JTextField textFieldStaz = new JTextField();
    private JTextField textFieldTytulNaukowy = new JTextField();
    private JTextField textFieldStanowisko = new JTextField();

    private JList<Kursy> selectedKursyJList = new JList<>(modelListyKursow);


    public stronaDodajOsobe(Frontend frontend) {

        setLayout(cardLayout);
        this.frontend = frontend;
        zaladujDostepneKursy();


        dodajKursDoStudentaButton.addActionListener(this);
        StudentZatwierdzDodaniePrzycisk.addActionListener(this);
        PUZatwierdzDodaniePrzycisk.addActionListener(this);
        PNDZatwierdzDodaniePrzycisk.addActionListener(this);
        dodajKursButton.addActionListener(this);
        wrocDoStronyGlownejPrzycisk.addActionListener(this);


        studentPrzyciskPanel.setLayout(new BoxLayout(studentPrzyciskPanel, BoxLayout.X_AXIS));
        studentPrzyciskPanel.add(StudentZatwierdzDodaniePrzycisk);
        studentPrzyciskPanel.add(wrocDoStronyGlownejPrzycisk);

        studentPrzyciskPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout for horizontal arrangement
        studentPrzyciskPanel.add(StudentZatwierdzDodaniePrzycisk);
        studentPrzyciskPanel.add(wrocDoStronyGlownejPrzycisk);

        PUPrzyciskPanel.setLayout(new BoxLayout(PUPrzyciskPanel, BoxLayout.X_AXIS));
        PUPrzyciskPanel.add(PUZatwierdzDodaniePrzycisk);
        PUPrzyciskPanel.add(wrocDoStronyGlownejPrzycisk);

        PNDPrzyciskPanel.setLayout(new BoxLayout(PNDPrzyciskPanel, BoxLayout.X_AXIS));
        PNDPrzyciskPanel.add(PNDZatwierdzDodaniePrzycisk);
        PNDPrzyciskPanel.add(wrocDoStronyGlownejPrzycisk);

        wiekPanel.setLayout(new BoxLayout(wiekPanel, BoxLayout.X_AXIS));
        wiekPanel.add(labelWiek);
        wiekPanel.add(textFieldWiek);


        imiePanel.setLayout(new BoxLayout(imiePanel, BoxLayout.X_AXIS));
        imiePanel.add(labelImie);
        imiePanel.add(textFieldImie);

        nazwiskoPanel.setLayout(new BoxLayout(nazwiskoPanel, BoxLayout.X_AXIS));
        nazwiskoPanel.add(labelNazwisko);
        nazwiskoPanel.add(textFieldNazwisko);

        peselPanel.setLayout(new BoxLayout(peselPanel, BoxLayout.X_AXIS));
        peselPanel.add(labelPesel);
        peselPanel.add(textFieldPesel);

        indeksPanel.setLayout(new BoxLayout(indeksPanel, BoxLayout.X_AXIS));
        indeksPanel.add(labelIndeks);
        indeksPanel.add(textFieldIndeks);

        kierunekPanel.setLayout(new BoxLayout(kierunekPanel, BoxLayout.X_AXIS));
        kierunekPanel.add(labelKierunek);
        kierunekPanel.add(textFieldKierunek);

        semestrPanel.setLayout(new BoxLayout(semestrPanel, BoxLayout.X_AXIS));
        semestrPanel.add(labelSemestr);
        semestrPanel.add(textFieldSemestr);

        wynagrodzeniePanel.setLayout(new BoxLayout(wynagrodzeniePanel, BoxLayout.X_AXIS));
        wynagrodzeniePanel.add(labelWynagrodzenie);
        wynagrodzeniePanel.add(textFieldWynagrodzenie);

        stazPanel.setLayout(new BoxLayout(stazPanel, BoxLayout.X_AXIS));
        stazPanel.add(labelStaz);
        stazPanel.add(textFieldStaz);

        stanowiskoPanel.setLayout(new BoxLayout(stanowiskoPanel, BoxLayout.X_AXIS));
        stanowiskoPanel.add(labelStanowisko);
        stanowiskoPanel.add(textFieldStanowisko);

        tytulNaukowyPanel.setLayout(new BoxLayout(tytulNaukowyPanel, BoxLayout.X_AXIS));
        tytulNaukowyPanel.add(labelTytulNaukowy);
        tytulNaukowyPanel.add(textFieldTytulNaukowy);

        studentContentPanel.setLayout(new BoxLayout(studentContentPanel, BoxLayout.Y_AXIS));
        PNDContentPanel.setLayout(new BoxLayout(PNDContentPanel, BoxLayout.Y_AXIS));
        PUContentPanel.setLayout(new BoxLayout(PUContentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(dodajOsobeTytul);
        contentPanel.add(dodajStudentaPrzycisk);
        contentPanel.add(dodajPNDPrzycisk);
        contentPanel.add(dodajPUPrzycisk);

        dodajStudentaPrzycisk.addActionListener(this);
        dodajPNDPrzycisk.addActionListener(this);
        dodajPUPrzycisk.addActionListener(this);

        kursyPanel.setLayout(new BoxLayout(kursyPanel, BoxLayout.X_AXIS));
        kursyPanel.add(new JLabel("Kursy:"));
        kursyPanel.add(nazwaKursuField);
        kursyPanel.add(wykladowcaField);
        kursyPanel.add(cwiczeniowiecField);
        kursyPanel.add(dodajKursButton);

        add(contentPanel, "stronaGlowna");
        add(studentContentPanel, "stronaStudenta");
        add(PNDContentPanel, "stronaPND");
        add(PUContentPanel, "stronaPU");
    }

    public void zaladujDostepneKursy() {

        ArrayList<Kursy> listaKursow = BazaDanych.getKursy();
        modelListyKursow.clear();
        for (Kursy kurs : listaKursow) {
            modelListyKursow.addElement(kurs);
        }

    }

    private void aktualizujListeKursowStudenta() {
        listaKursowJList.setModel(modelListyKursow);
        revalidate();
        repaint();
        cardLayout.show(this, "stronaStudenta");
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dodajKursButton) {
            Kursy kurs = new Kursy(nazwaKursuField.getText(), wykladowcaField.getText(), cwiczeniowiecField.getText());
            BazaDanych.dodajKurs(kurs);
            zaladujDostepneKursy();
            aktualizujListeKursowStudenta();
            getParent().revalidate();
            getParent().repaint();
        }
        if (e.getSource() == StudentZatwierdzDodaniePrzycisk) {
            int wiek = Integer. parseInt(textFieldWiek.getText());
            String imie = textFieldImie.getText();
            String nazwisko = textFieldNazwisko.getText();
            String pesel = textFieldPesel.getText();
            String kierunek = textFieldKierunek.getText();
            int semestr = Integer.parseInt(textFieldSemestr.getText());
            int indeks = Integer.parseInt(textFieldIndeks.getText());




            Student student = new Student(wiek, imie, nazwisko, pesel, indeks, kierunek, semestr);
            ArrayList<Kursy> kursyDodane = new ArrayList<>();
            kursyDodane.addAll(listaKursowJList.getSelectedValuesList());

            for (Kursy kurs: kursyDodane){
                student.dodajKurs(kurs);
            }

            BazaDanych.dodajStudenta(student);

            cardLayout.show(this, "stronaGlowna");


        }



        if (e.getSource() == PUZatwierdzDodaniePrzycisk) {
            int wiek = Integer.parseInt(textFieldWiek.getText());
            String imie = textFieldImie.getText();
            String nazwisko = textFieldNazwisko.getText();
            String pesel = textFieldPesel.getText();
            String stanowsiko = textFieldStanowisko.getText();
            int wynagrodzenie = Integer.parseInt(textFieldWynagrodzenie.getText());
            int staz = Integer.parseInt(textFieldStaz.getText());

            PracownikUczelni pU = new PracownikUczelni(wiek, imie, nazwisko, pesel, wynagrodzenie, staz, stanowsiko);
            BazaDanych.dodajPU(pU);
            cardLayout.show(this, "stronaGlowna");


        }
        if (e.getSource() == PNDZatwierdzDodaniePrzycisk) {
            int wiek = Integer.parseInt(textFieldWiek.getText());
            String imie = textFieldImie.getText();
            String nazwisko = textFieldNazwisko.getText();
            String pesel = textFieldPesel.getText();
            String tytulNaukowy = textFieldTytulNaukowy.getText();
            int wynagrodzenie = Integer.parseInt(textFieldWynagrodzenie.getText());
            int staz = Integer.parseInt(textFieldStaz.getText());

            PracownikNaukowoDydaktyczny PND = new PracownikNaukowoDydaktyczny(wiek, imie, nazwisko, pesel, wynagrodzenie, staz, tytulNaukowy);
            BazaDanych.dodajPND(PND);
            cardLayout.show(this, "stronaGlowna");


        }

        if (e.getSource() == dodajStudentaPrzycisk) {
            PUContentPanel.removeAll();
            PNDContentPanel.removeAll();
            studentContentPanel.removeAll();
            studentContentPanel.setLayout(new BoxLayout(studentContentPanel, BoxLayout.Y_AXIS));
            studentContentPanel.add(wiekPanel);
            studentContentPanel.add(imiePanel);
            studentContentPanel.add(nazwiskoPanel);
            studentContentPanel.add(peselPanel);
            studentContentPanel.add(indeksPanel);
            studentContentPanel.add(kierunekPanel);
            studentContentPanel.add(semestrPanel);
            studentContentPanel.add(studentPrzyciskPanel);
            studentContentPanel.add(kursyPanel);
            studentContentPanel.add(new JScrollPane(listaKursowJList));
            studentContentPanel.add(wrocDoStronyGlownejPrzycisk);





            cardLayout.show(this, "stronaStudenta");


            aktualizujListeKursowStudenta();
        }
        if (e.getSource() == dodajPNDPrzycisk) {
            PUContentPanel.removeAll();
            PNDContentPanel.removeAll();
            studentContentPanel.removeAll();

            PNDContentPanel.setLayout(new BoxLayout(PNDContentPanel, BoxLayout.Y_AXIS));
            PNDContentPanel.add(wiekPanel);
            PNDContentPanel.add(imiePanel);
            PNDContentPanel.add(nazwiskoPanel);
            PNDContentPanel.add(peselPanel);
            PNDContentPanel.add(wynagrodzeniePanel);
            PNDContentPanel.add(stazPanel);
            PNDContentPanel.add(tytulNaukowyPanel);
            PNDContentPanel.add(PNDPrzyciskPanel);

            cardLayout.show(this, "stronaPND");
        }
        if (e.getSource() == dodajPUPrzycisk) {
            PUContentPanel.removeAll();
            PNDContentPanel.removeAll();
            studentContentPanel.removeAll();


            PUContentPanel.add(wiekPanel);
            PUContentPanel.add(imiePanel);
            PUContentPanel.add(nazwiskoPanel);
            PUContentPanel.add(peselPanel);
            PUContentPanel.add(wynagrodzeniePanel);
            PUContentPanel.add(stazPanel);
            PUContentPanel.add(stanowiskoPanel);
            PUContentPanel.add(PUPrzyciskPanel);

            cardLayout.show(this, "stronaPU");
        }
        if (e.getSource() == wrocDoStronyGlownejPrzycisk) {
            cardLayout.show(this, "stronaGlowna");
        }
    }
}



