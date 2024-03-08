import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OpcjeProduktowWindow implements ActionListener {

    JComboBox<String> comboBox;
    JFrame window = new JFrame("Opcje produktow");
    JFrame dodajProduktWindow, usunProduktWindow, edytujProduktWindow, wyborProduktuDoEdycjiWindow,
            wyszukajPoNazwieWindow, wyszukajPoCenieWindow, wyszukajPoIlosciWindow;
    JButton dodajProduktButton, edytujProduktButton, usunProduktButton, wyszukajProduktButton,
             powrotButton, dodajButton, usunButton, edytujButton, edytuj2Button, wyszukajPoNazwieButton,
            wyszukajPoCenieButton, wyszukajPoIlosciButton;

    JLabel nazwaLabel, cenaLabel, iloscLabel, idKategoriiLabel, idLabel,
            wyszukajPoNazwieLabel, wyszukajPoCenieNajnizszaLabel,
            wyszukajPoCenieNajwyzszaLabel, wyszukajPoIlosciNajmniejLabel, wyszukajPoIlosciNajwiecejLabel;
    JTextField nazwaTextField, cenaTextField, iloscTextField, idKategoriiTextField, idTextField, idTextField2,
            wyszukajPoNazwieTextField, wyszukajPoIlosciTextField,
            wyszukajPoCenieNajnizszaTextField, wyszukajPoCenieNajwyzszaTextField,
            wyszukajPoIlosciNajmniejTextField, wyszukajPoIlosciNajwiecejTextField;
    JTable productsTable;

    JScrollPane tableJScrollPane;

    public OpcjeProduktowWindow(){
        window.setSize(600,680);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setLocationRelativeTo(null); //makes sure that new window is on the screen centre

        dodajProduktButton = new JButton("Dodaj produkt");
        dodajProduktButton.setBounds(210, 20, 180, 20);
        dodajProduktButton.addActionListener(this);
        window.add(dodajProduktButton);

        edytujProduktButton = new JButton("Edytuj produkt");
        edytujProduktButton.setBounds(210, 45, 180, 20);
        edytujProduktButton.addActionListener(this);
        window.add(edytujProduktButton);

        usunProduktButton = new JButton("Usun produkt");
        usunProduktButton.setBounds(210, 70, 180, 20);
        usunProduktButton.addActionListener(this);
        window.add(usunProduktButton);

        wyszukajProduktButton = new JButton("Wyszukaj");

        String[] actions = {"Wyszukaj produkt po nazwie", "Wyszukaj produkt po cenie", "Wyszukaj produkt po ilosci"};
        comboBox = new JComboBox<>(actions);
        comboBox.addActionListener(this);
        comboBox.setBounds(200, 100, 200, 20);
        window.add(comboBox);


        powrotButton = new JButton("Powrot do menu glownego");
        powrotButton.setBounds(200, 130, 200, 20);
        powrotButton.addActionListener(this);
        window.add(powrotButton);

        ArrayList<Product> allProducts = DataBaseApi.getAllProducts();
        String[][] data = new String[allProducts.size()][6];

        for(int i = 0; i<allProducts.size(); i++){
            data[i] = new String[]{"" + allProducts.get(i).getId(), allProducts.get(i).getNazwa(),
                    "" + allProducts.get(i).getCena(),"" + allProducts.get(i).getIlosc(),
                    "" + allProducts.get(i).getIdKategorii()
                };
        }

        String[] columnNames = { "Indeks", "Nazwa", "Cena", "Ilość", "Kategoria"  };

        //productsTable = new JTable(data, columnNames);
        //productsTable.revalidate();
        //productsTable.repaint();

        productsTable = new JTable(data, columnNames);
        productsTable.setBounds(30, 160, 540, 400);
        tableJScrollPane = new JScrollPane(productsTable);
        tableJScrollPane.setBounds(30, 160, 540, 400);

        window.add(tableJScrollPane);

        window.setVisible(true);
    }

    //dodaj, edytuj, usun, wyszukaj po nazwie, cenie, ilosci
    //id, nazwa, cena, ilosc, id kategorii

    private void setTable(List<Product> products)
    {
        window.remove(tableJScrollPane);

        String[][] data = new String[products.size()][6];

        for(int i = 0; i<products.size(); i++){
            data[i] = new String[]{"" + products.get(i).getId(), products.get(i).getNazwa(),
                    "" + products.get(i).getCena(),"" + products.get(i).getIlosc(),
                    "" + products.get(i).getIdKategorii()
            };
        }

        String[] columnNames = { "Indeks", "Nazwa", "Cena", "Ilość", "Kategoria"  };

        productsTable = new JTable(data, columnNames);
        productsTable.setBounds(30, 160, 540, 400);
        tableJScrollPane = new JScrollPane(productsTable);
        tableJScrollPane.setBounds(30, 160, 540, 400);

        window.add(tableJScrollPane);

        window.invalidate();
        window.validate();
        window.revalidate();
        window.repaint();
    }

    private void dodajProduktWindow(){
        dodajProduktWindow = new JFrame("Dodaj produkt");
        dodajProduktWindow.setSize(300,350);
        dodajProduktWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dodajProduktWindow.setLayout(null);
        dodajProduktWindow.setLocationRelativeTo(null);

        nazwaLabel = new JLabel("Nazwa: ");
        nazwaLabel.setBounds(100, 20, 100, 20);
        dodajProduktWindow.add(nazwaLabel);

        nazwaTextField = new JTextField();
        nazwaTextField.setBounds(100, 50, 100, 20);
        dodajProduktWindow.add(nazwaTextField);

        cenaLabel = new JLabel("Cena: ");
        cenaLabel.setBounds(100, 80, 100, 20);
        dodajProduktWindow.add(cenaLabel);

        cenaTextField = new JTextField();
        cenaTextField.setBounds(100, 110, 100, 20);
        dodajProduktWindow.add(cenaTextField);

        iloscLabel = new JLabel("Ilosc na stanie:");
        iloscLabel.setBounds(100, 140, 100, 20);
        dodajProduktWindow.add(iloscLabel);

        iloscTextField = new JTextField();
        iloscTextField.setBounds(100, 170, 100, 20);
        dodajProduktWindow.add(iloscTextField);

        idKategoriiLabel = new JLabel("Id kategorii");
        idKategoriiLabel.setBounds(100, 200, 100, 20);
        dodajProduktWindow.add(idKategoriiLabel);

        idKategoriiTextField = new JTextField();
        idKategoriiTextField.setBounds(100, 230, 100, 20);
        dodajProduktWindow.add(idKategoriiTextField);

        dodajButton = new JButton("Dodaj");
        dodajButton.setBounds(100, 260, 100, 20);
        dodajButton.addActionListener(this);
        dodajProduktWindow.add(dodajButton);


        dodajProduktWindow.setVisible(true);

    }

    private void wyborProduktuDoEdycjiWindow(){
        wyborProduktuDoEdycjiWindow = new JFrame("Edytuj produkt");
        wyborProduktuDoEdycjiWindow.setSize(300, 200);
        wyborProduktuDoEdycjiWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wyborProduktuDoEdycjiWindow.setLayout(null);
        wyborProduktuDoEdycjiWindow.setLocationRelativeTo(null);

        idLabel = new JLabel("Wpisz id produktu do edycji");
        idLabel.setBounds(70, 20, 180, 20);
        wyborProduktuDoEdycjiWindow.add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(50, 50, 200, 20);
        wyborProduktuDoEdycjiWindow.add(idTextField);

        edytujButton = new JButton("Edytuj");
        edytujButton.addActionListener(this);
        edytujButton.setBounds(100, 80, 100, 20);
        wyborProduktuDoEdycjiWindow.add(edytujButton);

        wyborProduktuDoEdycjiWindow.setVisible(true);


    }

    private void edytujProduktWindow(){
        edytujProduktWindow = new JFrame("Edytuj produkt");
        edytujProduktWindow.setSize(300, 390);
        edytujProduktWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        edytujProduktWindow.setLayout(null);
        edytujProduktWindow.setLocationRelativeTo(null);

        //id, nazwa, cena, ilosc, id kategorii
        idLabel = new JLabel("Id produktu: ");
        idLabel.setBounds(100, 20, 100, 20);
        edytujProduktWindow.add(idLabel);

        int chosenId = Integer.parseInt(idTextField.getText());
        Product product = DataBaseApi.getAllProducts().stream().filter(x -> x.getId() == chosenId).findFirst().get();
        idTextField2 = new JTextField("" + product.getId());
        idTextField2.setBounds(100, 50, 100, 20);
        edytujProduktWindow.add(idTextField2);

        nazwaLabel = new JLabel("Nazwa: ");
        nazwaLabel.setBounds(100, 80, 100, 20);
        edytujProduktWindow.add(nazwaLabel);

        nazwaTextField = new JTextField(product.getNazwa());
        nazwaTextField.setBounds(100, 110, 100, 20);
        edytujProduktWindow.add(nazwaTextField);

        cenaLabel = new JLabel("Cena: ");
        cenaLabel.setBounds(100, 140, 100, 20);
        edytujProduktWindow.add(cenaLabel);

        cenaTextField = new JTextField("" + product.getCena());
        cenaTextField.setBounds(100, 170, 100, 20);
        edytujProduktWindow.add(cenaTextField);

        iloscLabel = new JLabel("Ilosc: ");
        iloscLabel.setBounds(100, 200, 100, 20);
        edytujProduktWindow.add(iloscLabel);

        iloscTextField = new JTextField("" + product.getIlosc());
        iloscTextField.setBounds(100, 230, 100, 20);
        edytujProduktWindow.add(iloscTextField);

        idKategoriiLabel = new JLabel("ID kategorii: ");
        idKategoriiLabel.setBounds(100, 260, 100, 20);
        edytujProduktWindow.add(idKategoriiLabel);

        idKategoriiTextField = new JTextField("" + product.getIdKategorii());
        idKategoriiTextField.setBounds(100, 290, 100, 20);
        edytujProduktWindow.add(idKategoriiTextField);

        edytuj2Button = new JButton("Edytuj");
        edytuj2Button.addActionListener(this);
        edytuj2Button.setBounds(100, 320, 100, 20);
        edytujProduktWindow.add(edytuj2Button);



        edytujProduktWindow.setVisible(true);
    }

    private void usunProduktWindow(){
        usunProduktWindow = new JFrame("Usun produkt");
        usunProduktWindow.setSize(300,200);
        usunProduktWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usunProduktWindow.setLayout(null);
        usunProduktWindow.setLocationRelativeTo(null);

        idLabel = new JLabel("Podaj id produktu, ktory chcesz usunac");
        idLabel.setBounds(20, 20, 240, 20);
        usunProduktWindow.add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(50, 50, 200, 20);
        usunProduktWindow.add(idTextField);

        usunButton = new JButton("Usun");
        usunButton.setBounds(100, 80, 100, 20);
        usunProduktWindow.add(usunButton);
        usunButton.addActionListener(this);

        usunProduktWindow.setVisible(true);
    }

    private void wyszukajPoNazwieWindow(){
        wyszukajPoNazwieWindow = new JFrame("Wyszukaj po nazwie");
        wyszukajPoNazwieWindow.setSize(200, 200);
        wyszukajPoNazwieWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wyszukajPoNazwieWindow.setLocationRelativeTo(null);
        wyszukajPoNazwieWindow.setLayout(null);

        wyszukajPoNazwieLabel = new JLabel("Wyszukaj po nazwie:");
        wyszukajPoNazwieLabel.setBounds(50, 20, 100, 20);
        wyszukajPoNazwieWindow.add(wyszukajPoNazwieLabel);

        wyszukajPoNazwieTextField = new JTextField();
        wyszukajPoNazwieTextField.setBounds(50, 50, 100, 20);
        wyszukajPoNazwieWindow.add(wyszukajPoNazwieTextField);

        wyszukajPoNazwieButton = new JButton("Wyszukaj");
        wyszukajPoNazwieButton.setBounds(50, 80, 100, 20);
        wyszukajPoNazwieButton.addActionListener(this);
        wyszukajPoNazwieWindow.add(wyszukajPoNazwieButton);

        wyszukajPoNazwieWindow.setVisible(true);
    }
    private void wyszukajPoCenieWindow(){
        wyszukajPoCenieWindow = new JFrame("Wyszukaj po cenie");
        wyszukajPoCenieWindow.setSize(200, 220);
        wyszukajPoCenieWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wyszukajPoCenieWindow.setLocationRelativeTo(null);
        wyszukajPoCenieWindow.setLayout(null);

        wyszukajPoCenieNajnizszaLabel = new JLabel("Najnizsza cena: ");
        wyszukajPoCenieNajnizszaLabel.setBounds(50, 20, 100, 20);
        wyszukajPoCenieWindow.add(wyszukajPoCenieNajnizszaLabel);

        wyszukajPoCenieNajnizszaTextField = new JTextField();
        wyszukajPoCenieNajnizszaTextField.setBounds(50, 50, 100, 20);
        wyszukajPoCenieWindow.add(wyszukajPoCenieNajnizszaTextField);

        wyszukajPoCenieNajwyzszaLabel = new JLabel("Najwyzsza cena: ");
        wyszukajPoCenieNajwyzszaLabel.setBounds(50, 80, 100, 20);
        wyszukajPoCenieWindow.add(wyszukajPoCenieNajwyzszaLabel);

        wyszukajPoCenieNajwyzszaTextField = new JTextField();
        wyszukajPoCenieNajwyzszaTextField.setBounds(50, 110, 100, 20);
        wyszukajPoCenieWindow.add(wyszukajPoCenieNajwyzszaTextField);

        wyszukajPoCenieButton = new JButton("Wyszukaj");
        wyszukajPoCenieButton.setBounds(50, 140, 100, 20);
        wyszukajPoCenieButton.addActionListener(this);
        wyszukajPoCenieWindow.add(wyszukajPoCenieButton);


        wyszukajPoCenieWindow.setVisible(true);
    }

    private void wyszukajPoIlosciWindow(){
        wyszukajPoIlosciWindow = new JFrame("Wyszukaj po ilosci");
        wyszukajPoIlosciWindow.setSize(200, 260);
        wyszukajPoIlosciWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wyszukajPoIlosciWindow.setLocationRelativeTo(null);
        wyszukajPoIlosciWindow.setLayout(null);

        wyszukajPoIlosciNajmniejLabel = new JLabel("Wpisz najmniejsza ilosc: ");
        wyszukajPoIlosciNajmniejLabel.setBounds(50, 20, 100, 20);
        wyszukajPoIlosciWindow.add(wyszukajPoIlosciNajmniejLabel);

        wyszukajPoIlosciNajmniejTextField = new JTextField();
        wyszukajPoIlosciNajmniejTextField.setBounds(50, 50, 100, 20);
        wyszukajPoIlosciWindow.add(wyszukajPoIlosciNajmniejTextField);

        wyszukajPoIlosciNajwiecejLabel = new JLabel("Wpisz najwieksza ilosc: ");
        wyszukajPoIlosciNajwiecejLabel.setBounds(50, 80, 100, 20);
        wyszukajPoIlosciWindow.add(wyszukajPoIlosciNajwiecejLabel);

        wyszukajPoIlosciNajwiecejTextField = new JTextField();
        wyszukajPoIlosciNajwiecejTextField.setBounds(50, 110, 100, 20);
        wyszukajPoIlosciWindow.add(wyszukajPoIlosciNajwiecejTextField);

        wyszukajPoIlosciButton = new JButton("Wyszukaj");
        wyszukajPoIlosciButton.setBounds(50, 140, 100, 20);
        wyszukajPoIlosciButton.addActionListener(this);
        wyszukajPoIlosciWindow.add(wyszukajPoIlosciButton);

        wyszukajPoIlosciWindow.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        String selectedAction = (String) comboBox.getSelectedItem();

        if(e.getSource() == dodajProduktButton){
            dodajProduktWindow();
        }
        else if(e.getSource() == dodajButton){
            if(!nazwaTextField.getText().isEmpty() && !cenaTextField.getText().isEmpty() && !iloscTextField.getText().isEmpty() && !idKategoriiTextField.getText().isEmpty()){
            DataBaseApi.addProduct(nazwaTextField.getText(), cenaTextField.getText(), Integer.parseInt(iloscTextField.getText()), Integer.parseInt(idKategoriiTextField.getText()));
            dodajProduktWindow.dispose();
            setTable(DataBaseApi.getAllProducts());}
            else{JOptionPane.showMessageDialog(edytujProduktWindow, "Uzupelnij wszystkie dane!");}
        }
        else if(e.getSource() == usunProduktButton){
            usunProduktWindow();
        }
        else if(e.getSource() == usunButton){
            if(DataBaseApi.getAllProducts()
                    .stream()
                    .anyMatch(w -> w.getId() == Integer.parseInt(idTextField.getText()))){
                DataBaseApi.deleteProduct(Integer.parseInt(idTextField.getText()));
                usunProduktWindow.dispose();
                setTable(DataBaseApi.getAllProducts());
            }
            else {JOptionPane.showMessageDialog(usunProduktWindow, "Produkt o podanym indeksie nie istnieje!");}

        }
        else if(e.getSource() == edytujProduktButton){
            wyborProduktuDoEdycjiWindow();
        }
        else if(e.getSource() == edytujButton){
            wyborProduktuDoEdycjiWindow.dispose();
            edytujProduktWindow();
        }
        else if(e.getSource() == edytuj2Button){
            if(!idTextField.getText().isEmpty() &&
                    !nazwaTextField.getText().isEmpty() &&
                    !cenaTextField.getText().isEmpty() &&
                    !iloscTextField.getText().isEmpty() &&
                    !idKategoriiTextField.getText().isEmpty()) {
                DataBaseApi.editProduct(Integer.parseInt(idTextField.getText()), nazwaTextField.getText(), Double.parseDouble(cenaTextField.getText()), Integer.parseInt(iloscTextField.getText()), Integer.parseInt(idKategoriiTextField.getText()));
                edytujProduktWindow.dispose();
                setTable(DataBaseApi.getAllProducts());
            }else {

                JOptionPane.showMessageDialog(edytujProduktWindow, "Uzupelnij wszystkie dane!");}
        }
        else if(e.getSource() == wyszukajPoCenieButton){
            if(!wyszukajPoCenieNajnizszaTextField.getText().isEmpty() && !wyszukajPoCenieNajwyzszaTextField.getText().isEmpty() && Integer.parseInt(wyszukajPoCenieNajnizszaTextField.getText()) <= Integer.parseInt(wyszukajPoCenieNajwyzszaTextField.getText())){
                var products = DataBaseApi.findProductByPrice(wyszukajPoCenieNajnizszaTextField.getText(), wyszukajPoCenieNajwyzszaTextField.getText());
                setTable(products);
                wyszukajPoCenieWindow.dispose();}
            else {JOptionPane.showMessageDialog(wyszukajPoCenieWindow, "Wartosc najnizsza musi byc mniejsza od najwyzszej oraz zadna nie moze byc pusta");}
        }
        else if (e.getSource() == wyszukajPoIlosciButton){
            if(!wyszukajPoIlosciNajmniejTextField.getText().isEmpty() && !wyszukajPoIlosciNajwiecejTextField.getText().isEmpty() && Integer.parseInt(wyszukajPoIlosciNajmniejTextField.getText()) <= Integer.parseInt(wyszukajPoIlosciNajwiecejTextField.getText())){
                var products = DataBaseApi.findProductByAmount(Integer.parseInt(wyszukajPoIlosciNajmniejTextField.getText()), Integer.parseInt(wyszukajPoIlosciNajwiecejTextField.getText()));
                 setTable(products);
                 wyszukajPoIlosciWindow.dispose();}
            else {JOptionPane.showMessageDialog(wyszukajPoIlosciWindow, "Wartosc najnizsza musi byc mniejsza od najwyzszej oraz zadna nie moze byc pusta");}
        }
        else if(e.getSource() == wyszukajPoNazwieButton){
            var products = DataBaseApi.findProductByName(wyszukajPoNazwieTextField.getText());
            setTable(products);
            wyszukajPoNazwieWindow.dispose();

        }
        else if(e.getSource() == powrotButton){
            window.dispose();
        }
        else if(selectedAction == "Wyszukaj produkt po nazwie" ){
            wyszukajPoNazwieWindow();
        }
        else if(selectedAction == "Wyszukaj produkt po cenie" ){
            wyszukajPoCenieWindow();
        }
        else if(selectedAction == "Wyszukaj produkt po ilosci" ){
            wyszukajPoIlosciWindow();
        }




    }
}
