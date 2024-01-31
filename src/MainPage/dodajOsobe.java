package MainPage;

import Kursy.Kursy;
import Pracownik.PracownikNaukowoDydaktyczny;
import Pracownik.PracownikUczelni;
import BazaDanych.BazaDanych;
import Student.Student;
import wynagrodzenieStrategy.Wynagrodzenie;
import wynagrodzenieStrategy.obliczWynagrodzeniePoStazu;
import wynagrodzenieStrategy.obliczWynagrodzeniePoTytyleNaukowym;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class dodajOsobe extends JPanel implements ActionListener {
    private ArrayList<Kursy> dostepneKursy = new ArrayList<Kursy>();
    private Wynagrodzenie obliczWynagrodzenieStrategy;
    private JList<Kursy> listaKursowJList = new JList<>();
    private JButton dodajKursDoStudentaButton = new JButton("Dodaj wybrane kursy do studenta");
    private Frontend frontend;

    private JPanel kursyPanel = new JPanel();
    private JTextField nazwaKursuField = new JTextField("Wprowadź nazwę kursu");
    private JTextField wykladowcaField = new JTextField("WWprowadź wykładowcę");
    private JButton dodajKursButton = new JButton("Dodaj kurs");
    private CardLayout cardLayout = new CardLayout();

    private JPanel wiekPanel = new JPanel();

    private JPanel sredniaPanel = new JPanel();
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

    private JButton wrocDoStronyGlownejPrzycisk = new JButton("Wroc");

    JTextField cwiczeniowiecField = new JTextField("Wprowadz Nauczyciela od cwiczen");

    private JPanel StrategiaPanel = new JPanel();
    JCheckBox StrategieCheckBox = new JCheckBox();
    JCheckBoxMenuItem poStazu = new JCheckBoxMenuItem("Oblicz wynagrodzenie po stażu");
    JCheckBoxMenuItem poTytule = new JCheckBoxMenuItem("Oblicz wynagrodzenie po tytule");



    private JLabel labelWiek = new JLabel("Wiek: ");
    private JLabel labelImie = new JLabel("Imię: ");
    private JLabel labelNazwisko = new JLabel("Nazwisko: ");
    private JLabel labelPesel = new JLabel("Pesel: ");
    private JLabel labelIndeks = new JLabel("Indeks: ");
    private JLabel labelKierunek = new JLabel("Kierunek: ");
    private JLabel labelSemestr = new JLabel("Semestr: ");
    private JLabel sredniaOcen = new JLabel("Średnia ocen: ");
    private JLabel labelWynagrodzenie = new JLabel("Wynagrodzenie: ");
    private JLabel labelStaz = new JLabel("Staż: ");
    private JLabel labelTytulNaukowy = new JLabel("Tytuł naukowy: ");
    private JLabel labelStanowisko = new JLabel("Stanowisko: ");


    private JTextField textFieldWiek = new JTextField();
    private DefaultListModel<Kursy> modelListyKursow = new DefaultListModel<>();

    private JTextField textFieldImie = new JTextField();
    private JTextField textFieldSrednia = new JTextField();
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


    public dodajOsobe(Frontend frontend) {

        setLayout(cardLayout);
        this.frontend = frontend;
        zaladujDostepneKursy();


        dodajKursDoStudentaButton.addActionListener(this);
        StudentZatwierdzDodaniePrzycisk.addActionListener(this);
        PUZatwierdzDodaniePrzycisk.addActionListener(this);
        PNDZatwierdzDodaniePrzycisk.addActionListener(this);
        dodajKursButton.addActionListener(this);
        wrocDoStronyGlownejPrzycisk.addActionListener(this);
        StrategieCheckBox.addActionListener(this);

        StrategiaPanel.add(StrategieCheckBox);
        StrategieCheckBox.add(poStazu);
        StrategiaPanel.add(poTytule);


        studentPrzyciskPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
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

        sredniaPanel.setLayout(new BoxLayout(sredniaPanel, BoxLayout.X_AXIS));
        sredniaPanel.add(sredniaOcen);
        sredniaPanel.add(textFieldSrednia);

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
            float sredniaOcen = Float.parseFloat(textFieldSrednia.getText());




            Student student = new Student(wiek, imie, nazwisko, pesel, indeks, kierunek, semestr, sredniaOcen);
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
            int staz = Integer.parseInt(textFieldStaz.getText());
            obliczWynagrodzenieStrategy = new obliczWynagrodzeniePoStazu(staz);
            PracownikUczelni pU = new PracownikUczelni(wiek, imie, nazwisko, pesel, staz, stanowsiko, obliczWynagrodzenieStrategy);
            BazaDanych.dodajPU(pU);
            cardLayout.show(this, "stronaGlowna");


        }
        if (e.getSource() == PNDZatwierdzDodaniePrzycisk) {
            int wiek = Integer.parseInt(textFieldWiek.getText());
            String imie = textFieldImie.getText();
            String nazwisko = textFieldNazwisko.getText();
            String pesel = textFieldPesel.getText();
            String tytulNaukowy = textFieldTytulNaukowy.getText();
            int staz = Integer.parseInt(textFieldStaz.getText());

            if (poStazu.isSelected()) {
                    obliczWynagrodzenieStrategy = new obliczWynagrodzeniePoStazu(staz);
            } else if (poTytule.isSelected()) {
                    obliczWynagrodzenieStrategy = new obliczWynagrodzeniePoTytyleNaukowym(tytulNaukowy);
            }



            PracownikNaukowoDydaktyczny PND = new PracownikNaukowoDydaktyczny(wiek, imie, nazwisko, pesel, staz, tytulNaukowy, obliczWynagrodzenieStrategy);
            BazaDanych.dodajPND(PND);
            cardLayout.show(this, "stronaGlowna");


        }

        if (e.getSource() == dodajStudentaPrzycisk) {
            PUContentPanel.removeAll();
            PNDContentPanel.removeAll();
            studentContentPanel.removeAll();
            studentContentPanel.setLayout(new BoxLayout(studentContentPanel, BoxLayout.Y_AXIS));
            studentContentPanel.add(sredniaPanel);
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
            PNDContentPanel.add(stazPanel);
            PNDContentPanel.add(tytulNaukowyPanel);
            PNDContentPanel.add(PNDPrzyciskPanel);
            PNDContentPanel.add(StrategiaPanel);
            PNDContentPanel.add(wrocDoStronyGlownejPrzycisk);


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
            PUContentPanel.add(stazPanel);
            PUContentPanel.add(stanowiskoPanel);
            PUContentPanel.add(PUPrzyciskPanel);
            PUContentPanel.add(wrocDoStronyGlownejPrzycisk);


            cardLayout.show(this, "stronaPU");
        }
        if (e.getSource() == wrocDoStronyGlownejPrzycisk) {
            cardLayout.show(this, "stronaGlowna");
        }
    }

}



