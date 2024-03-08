import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OpcjeKategoriiWindow implements ActionListener {

    JScrollPane tableJScrollPane;
    JFrame window = new JFrame("Opcje kategorii produktow");
    JFrame dodajKategorieWindow, usunKategorieWindow, wyszukajKategorieWindow;
    JButton wyszukajKategorieButton, dodajKategorieButton, usunKategorieButton, powrotButton, dodajButton, usunButton, wyszukajButton;
    JLabel nazwaKategoriiLabel, idKategoriiLabel;
    JTextField nazwaKategoriiTextField, idKategoriiTextField;


    JTable categoryTable;
    public OpcjeKategoriiWindow(){

        window.setSize(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setLocationRelativeTo(null); //makes sure that new window is on the screen centre

        wyszukajKategorieButton = new JButton("Wyszukaj kategorie produktow");
        wyszukajKategorieButton.setBounds(150, 20, 220, 20);
        wyszukajKategorieButton.addActionListener(this);
        window.add(wyszukajKategorieButton);

        dodajKategorieButton = new JButton("Dodaj kategorie produktow");
        dodajKategorieButton.setBounds(150, 45, 220, 20);
        dodajKategorieButton.addActionListener(this);
        window.add(dodajKategorieButton);

        usunKategorieButton = new JButton("Usun kategorie produktow");
        usunKategorieButton.setBounds(150, 70, 220, 20);
        usunKategorieButton.addActionListener(this);
        window.add(usunKategorieButton);


        powrotButton = new JButton("Powrot do menu glownego");
        powrotButton.setBounds(150, 95, 220, 20);
        powrotButton.addActionListener(this);
        window.add(powrotButton);

        ArrayList<Category> allCategories = DataBaseApi.getAllCategories();
        String[][] data = new String[allCategories.size()][2];

        for(int i = 0; i<allCategories.size(); i++){
            data[i] = new String[]{"" + allCategories.get(i).getId(), allCategories.get(i).getName()};
        }


        // Column Names
        String[] columnNames = { "Indeks kategorii", "Nazwa kategorii" };

        categoryTable = new JTable(data, columnNames);
        categoryTable.setBounds(110, 120, 300, 200);
        tableJScrollPane = new JScrollPane(categoryTable);
        tableJScrollPane.setBounds(110, 120, 300, 200);

        window.add(tableJScrollPane);

        window.setVisible(true);

    }

    private void setTable(List<Category> categories)
    {
        window.remove(tableJScrollPane);

        String[][] data = new String[categories.size()][6];

        for(int i = 0; i<categories.size(); i++){
            data[i] = new String[]{"" + categories.get(i).getId(), categories.get(i).getName()

            };
        }

        String[] columnNames = { "Indeks kategorii", "Nazwa kategorii" };

        categoryTable = new JTable(data, columnNames);
        categoryTable.setBounds(110, 160, 300, 200);
        tableJScrollPane = new JScrollPane(categoryTable);
        tableJScrollPane.setBounds(110, 160, 300, 200);

        window.add(tableJScrollPane);

        window.invalidate();
        window.validate();
        window.revalidate();
        window.repaint();
    }
    private void dodajKategorie(){
        dodajKategorieWindow = new JFrame("Dodaj kategorie");
        dodajKategorieWindow.setSize(300,200);
        dodajKategorieWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dodajKategorieWindow.setLayout(null);
        dodajKategorieWindow.setLocationRelativeTo(null);

        nazwaKategoriiLabel = new JLabel("Nazwa kategorii");
        nazwaKategoriiLabel.setBounds(100, 20, 100, 20);
        dodajKategorieWindow.add(nazwaKategoriiLabel);

        nazwaKategoriiTextField = new JTextField("");
        nazwaKategoriiTextField.setBounds(70, 40, 130, 20);
        dodajKategorieWindow.add(nazwaKategoriiTextField);

        dodajButton = new JButton("Dodaj");
        dodajButton.setBounds(70, 95, 100, 20);
        dodajButton.addActionListener(this);
        dodajKategorieWindow.add(dodajButton);



        dodajKategorieWindow.setVisible(true);
    }



    private void usunKategorie(){
        usunKategorieWindow = new JFrame("Usun kategorie");
        usunKategorieWindow.setSize(300,200);
        usunKategorieWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usunKategorieWindow.setLayout(null);
        usunKategorieWindow.setLocationRelativeTo(null);

        idKategoriiLabel = new JLabel("Wpisz id kategorii do usuniecia");
        idKategoriiLabel.setBounds(50, 20, 200, 20);
        usunKategorieWindow.add(idKategoriiLabel);

        idKategoriiTextField = new JTextField();
        idKategoriiTextField.setBounds(100, 50, 100, 20);
        usunKategorieWindow.add(idKategoriiTextField);

        usunButton = new JButton("Usun");
        usunButton.setBounds(110, 80, 80, 20);
        usunButton.addActionListener(this);
        usunKategorieWindow.add(usunButton);

        usunKategorieWindow.setVisible(true);

    }

    private void wyszukajKategorieWindow(){
        wyszukajKategorieWindow = new JFrame("Wyszukaj kategorie");
        wyszukajKategorieWindow.setSize(300,200);
        wyszukajKategorieWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wyszukajKategorieWindow.setLayout(null);
        wyszukajKategorieWindow.setLocationRelativeTo(null);

        nazwaKategoriiLabel = new JLabel("Wpisz nazwe kategorii");
        nazwaKategoriiLabel.setBounds(70, 20, 200, 20);
        wyszukajKategorieWindow.add(nazwaKategoriiLabel);

        nazwaKategoriiTextField = new JTextField();
        nazwaKategoriiTextField.setBounds(70, 50, 120, 20);
        wyszukajKategorieWindow.add(nazwaKategoriiTextField);



        wyszukajButton = new JButton("Wyszukaj");
        wyszukajButton.setBounds(90, 100, 100, 20);
        wyszukajButton.addActionListener(this);
        wyszukajKategorieWindow.add(wyszukajButton);

        wyszukajKategorieWindow.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == dodajKategorieButton){
            dodajKategorie();
        }
        if(e.getSource() == dodajButton){
            if(!nazwaKategoriiTextField.getText().isEmpty()){
                DataBaseApi.addCategory(nazwaKategoriiTextField.getText());
                dodajKategorieWindow.dispose();
                setTable(DataBaseApi.getAllCategories());
            }
            else {JOptionPane.showMessageDialog(dodajKategorieWindow, "Pole nie moze byc puste!");}

        }

        if(e.getSource() == usunKategorieButton){
            usunKategorie();
        }
        if(e.getSource() == usunButton) {
            if(DataBaseApi.getAllCategories()
                    .stream()
                    .anyMatch(w -> w.getId() == Integer.parseInt(idKategoriiTextField.getText()))){
                DataBaseApi.deleteCategory(Integer.parseInt(idKategoriiTextField.getText()));
                usunKategorieWindow.dispose();
                setTable(DataBaseApi.getAllCategories());
            }
            else {JOptionPane.showMessageDialog(usunKategorieWindow, "Produkt o podanym indeksie nie istnieje!");}
        }

        if(e.getSource() == wyszukajKategorieButton){
            wyszukajKategorieWindow();
        }
        if(e.getSource() == wyszukajButton){
            DataBaseApi.findCategory(nazwaKategoriiTextField.getText());
            wyszukajKategorieWindow.dispose();
            setTable(DataBaseApi.findCategory(nazwaKategoriiTextField.getText()));
        }

        if(e.getSource() == powrotButton){
            window.dispose();
        }

    }
}
