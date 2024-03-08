import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OpcjeRaportyWindow implements ActionListener {

    JScrollPane tableJScrollPane;
    JFrame window = new JFrame("Opcje raportow");
    JFrame wyszukajPoIdPracownikaWindow, usunRaportWindow, wyswietlRaportWindow, dodajRaportWindow;

    JLabel wyszukajPoIdPracownikaLabel, nazwaLabel, idAutoraLabel, trescLabel, idLabel;
    JTextField wyszukajPoIdPracownikaTextField, nazwaTextField, idAutoraTextField, trescTextField, idTextField;
    JButton wyszukajRaportPoIdPracownikaButton, wyswietlRaportButton, dodajRaportButton, usunRaportButton, powrotButton, wyszukajButton, dodajButton, usunButton;
    JTable raportsTable;
    public OpcjeRaportyWindow(){
        window.setSize(360,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setLocationRelativeTo(null);

        wyszukajRaportPoIdPracownikaButton = new JButton("Wyszukaj raport po id pracownika");
        wyszukajRaportPoIdPracownikaButton.setBounds(65, 20, 230, 20);
        wyszukajRaportPoIdPracownikaButton.addActionListener(this);
        window.add(wyszukajRaportPoIdPracownikaButton);


        dodajRaportButton = new JButton("Dodaj raport");
        dodajRaportButton.setBounds(90, 50, 180, 20);
        dodajRaportButton.addActionListener(this);
        window.add(dodajRaportButton);

        usunRaportButton = new JButton("Usun raport");
        usunRaportButton.setBounds(90, 80, 180, 20);
        usunRaportButton.addActionListener(this);
        window.add(usunRaportButton);

        powrotButton = new JButton("Powrot do menu glownego");
        powrotButton.setBounds(80, 110, 200, 20);
        powrotButton.addActionListener(this);
        window.add(powrotButton);

        ArrayList<Report> allReports = DataBaseApi.getAllReports();
        String[][] data = new String[allReports.size()][4];

        for(int i = 0; i<allReports.size(); i++){
            data[i] = new String[]{ "" + allReports.get(i).getRaportID(),"" + allReports.get(i).getDataWykonania(),
                    allReports.get(i).getRaportNazwa(), "" + allReports.get(i).getAutorID()};
        }

        String[] columnNames = { "Indeks", "Data wykonania", "Nazwa", "Indeks autora"  };

        raportsTable = new JTable(data, columnNames);
        raportsTable.setBounds(30, 140, 300, 400);
        tableJScrollPane = new JScrollPane(raportsTable);
        tableJScrollPane.setBounds(30, 140, 300, 400);

        window.add(tableJScrollPane);

        window.setVisible(true);
    }

    private void setTable(List<Report> reports)
    {
        window.remove(tableJScrollPane);

        String[][] data = new String[reports.size()][6];

        for(int i = 0; i<reports.size(); i++){
            data[i] = new String[]{"" + reports.get(i).getRaportID(), "" + reports.get(i).getDataWykonania(),
                    reports.get(i).getRaportNazwa(), "" + reports.get(i).getAutorID()
            };
        }

        String[] columnNames = { "Indeks", "Data wykonania", "Nazwa", "Indeks autora"   };

        raportsTable = new JTable(data, columnNames);
        raportsTable.setBounds(30, 160, 300, 400);
        tableJScrollPane = new JScrollPane(raportsTable);
        tableJScrollPane.setBounds(30, 160, 300, 400);

        window.add(tableJScrollPane);

        window.invalidate();
        window.validate();
        window.revalidate();
        window.repaint();
    }

    private void wyszukajPoIdPracownikaWindow(){

        wyszukajPoIdPracownikaWindow = new JFrame("Wyszukaj raport");
        wyszukajPoIdPracownikaWindow.setLayout(null);
        wyszukajPoIdPracownikaWindow.setLocationRelativeTo(null);
        wyszukajPoIdPracownikaWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wyszukajPoIdPracownikaWindow.setSize(300, 200);

        wyszukajPoIdPracownikaLabel = new JLabel("Wpisz ID pracownika:");
        wyszukajPoIdPracownikaLabel.setBounds(80, 20, 140, 20);
        wyszukajPoIdPracownikaWindow.add(wyszukajPoIdPracownikaLabel);

        wyszukajPoIdPracownikaTextField = new JTextField();
        wyszukajPoIdPracownikaTextField.setBounds(100, 50, 100, 20);
        wyszukajPoIdPracownikaWindow.add(wyszukajPoIdPracownikaTextField);

        wyszukajButton = new JButton("Wyszukaj");
        wyszukajButton.addActionListener(this);
        wyszukajButton.setBounds(100, 80, 100, 20);
        wyszukajPoIdPracownikaWindow.add(wyszukajButton);

        wyszukajPoIdPracownikaWindow.setVisible(true);

    }

    private void dodajRaportWindow(){
        dodajRaportWindow = new JFrame("Dodaj raport");
        dodajRaportWindow.setLocationRelativeTo(null);
        dodajRaportWindow.setLayout(null);
        dodajRaportWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dodajRaportWindow.setSize(300, 500);

        //nazwa, idautora, tresc
        nazwaLabel = new JLabel("Nazwa: ");
        nazwaLabel.setBounds(100, 20, 100, 20);
        dodajRaportWindow.add(nazwaLabel);

        nazwaTextField = new JTextField();
        nazwaTextField.setBounds(100, 50, 100, 20);
        dodajRaportWindow.add(nazwaTextField);

        idAutoraLabel = new JLabel("ID autora: ");
        idAutoraLabel.setBounds(100, 80, 100, 20);
        dodajRaportWindow.add(idAutoraLabel);

        idAutoraTextField = new JTextField();
        idAutoraTextField.setBounds(100, 110, 100, 20);
        dodajRaportWindow.add(idAutoraTextField);

        trescLabel = new JLabel("Tresc:");
        trescLabel.setBounds(100, 140, 100, 20);
        dodajRaportWindow.add(trescLabel);

        trescTextField = new JTextField();
        trescTextField.setBounds(100, 170, 100, 20);
        dodajRaportWindow.add(trescTextField);



        dodajButton = new JButton("Dodaj");
        dodajButton.addActionListener(this);
        dodajButton.setBounds(100, 200, 100, 20);
        dodajRaportWindow.add(dodajButton);

        dodajRaportWindow.setVisible(true);

    }

    private void usunRaportWindow(){
        usunRaportWindow = new JFrame("Usun raport");
        usunRaportWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usunRaportWindow.setLayout(null);
        usunRaportWindow.setLocationRelativeTo(null);
        usunRaportWindow.setSize(300, 200);

        idLabel = new JLabel("Podaj ID raportu:");
        idLabel.setBounds(100, 20, 100, 20);
        usunRaportWindow.add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(100, 50, 100, 20);
        usunRaportWindow.add(idTextField);


        usunButton = new JButton("Usun");
        usunButton.addActionListener(this);
        usunButton.setBounds(100, 80, 100, 20);
        usunRaportWindow.add(usunButton);

        usunRaportWindow.setVisible(true);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == wyszukajRaportPoIdPracownikaButton){
            wyszukajPoIdPracownikaWindow();
        }
        if(e.getSource() == wyszukajButton){
            if(DataBaseApi.getAllReports()
                    .stream()
                    .anyMatch(w -> w.getAutorID() == Integer.parseInt(wyszukajPoIdPracownikaTextField.getText()))){
                DataBaseApi.findReportByAutorId(Integer.parseInt(wyszukajPoIdPracownikaTextField.getText()));
                wyszukajPoIdPracownikaWindow.dispose();
                setTable(DataBaseApi.findReportByAutorId(Integer.parseInt(wyszukajPoIdPracownikaTextField.getText())));
            }
            else {
                JOptionPane.showMessageDialog(wyszukajPoIdPracownikaWindow, "Nie ma raportu o podanym ID autora!");
            }
        }
        if(e.getSource() == dodajRaportButton){
            dodajRaportWindow();
        }
        if(e.getSource() == dodajButton){

            if(!nazwaTextField.getText().isEmpty() && !idAutoraTextField.getText().isEmpty() && !trescTextField.getText().isEmpty()){
                if(DataBaseApi.getAllWorkers()
                        .stream()
                        .anyMatch(w -> w.getId() == Integer.parseInt(idAutoraTextField.getText())))
                {
                    DataBaseApi.addReport(nazwaTextField.getText(), Integer.parseInt(idAutoraTextField.getText()), trescTextField.getText());
                    dodajRaportWindow.dispose();
                    setTable(DataBaseApi.getAllReports());
                }
                else
                {
                    JOptionPane.showMessageDialog(dodajRaportWindow, "Nie ma pracownika o podanym indeksie!");
                }
            }
            else {JOptionPane.showMessageDialog(dodajRaportButton, "Uzupelnij wszystkie dane!");}
        }
        if(e.getSource() == usunRaportButton){
            usunRaportWindow();
        }

        if(e.getSource() == usunButton){
            DataBaseApi.deleteReport(Integer.parseInt(idTextField.getText()));
            usunRaportWindow.dispose();
            setTable(DataBaseApi.getAllReports());
        }

        if (e.getSource() == powrotButton){
            window.dispose();
        }

    }
}
