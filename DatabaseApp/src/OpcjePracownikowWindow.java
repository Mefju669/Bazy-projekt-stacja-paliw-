import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OpcjePracownikowWindow implements ActionListener {

    JScrollPane tableJScrollPane;
    JFrame window = new JFrame("Opcje pracownikow"), dodajPracownikaWindow, usunPracownikaWindow, edytujPracownikaWindow, edytujPracownikaWindow2;
    JFrame wyszukajPoNazwiskuWindow;
    JButton dodajPracownikaButton, edytujPracownikaButton, wyszukajPracownikaPoNazwiskuButton, zwolnijPracownikaButton, powrotButton, dodajButton, usunButton, edytujButton, zapiszButton, wyszukajButton;

    JLabel imieLabel, wyborIdLabel, nazwiskoLabel, idLabel, zarobkiLabel, emailLabel, stanowiskoLabel, idLabel2, wyszukajPoNazwiskuLabel;
    JTextField idTextField2, imieTextField, wyborIdTextField, nazwiskoTextField, idTextField, zarobkiTextField, emailTextField, stanowiskoTextField, wyszukajPoNazwiskuTextField;
    JTable workersTable;
    OpcjePracownikowWindow(){
        window.setSize(600,700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setLocationRelativeTo(null); //makes sure that new window is on the screen centre

        dodajPracownikaButton = new JButton("Dodaj pracownika");
        dodajPracownikaButton.setBounds(210, 20, 180, 20);
        dodajPracownikaButton.addActionListener(this);
        window.add(dodajPracownikaButton);

        edytujPracownikaButton = new JButton("Edytuj pracownika");
        edytujPracownikaButton.setBounds(210, 45, 180, 20);
        edytujPracownikaButton.addActionListener(this);
        window.add(edytujPracownikaButton);

        wyszukajPracownikaPoNazwiskuButton = new JButton("Wyszukaj pracownika (po nazwisku)");
        wyszukajPracownikaPoNazwiskuButton.setBounds(175, 70, 250, 20);
        wyszukajPracownikaPoNazwiskuButton.addActionListener(this);
        window.add(wyszukajPracownikaPoNazwiskuButton);

        zwolnijPracownikaButton = new JButton("Zwolnij pracownika");
        zwolnijPracownikaButton.setBounds(210, 95, 180, 20);
        zwolnijPracownikaButton.addActionListener(this);
        window.add(zwolnijPracownikaButton);

        powrotButton = new JButton("Powrot do menu glownego");
        powrotButton.setBounds(200, 120, 200, 20);
        powrotButton.addActionListener(this);
        window.add(powrotButton);

        ArrayList<Worker> allWorkers = DataBaseApi.getAllWorkers();
        String[][] data = new String[allWorkers.size()][6];

        for(int i = 0; i<allWorkers.size(); i++){
            data[i] = new String[]{allWorkers.get(i).getName(), allWorkers.get(i).getSurname(),
                    allWorkers.get(i).getEmail(), allWorkers.get(i).getPosition(),"" + allWorkers.get(i).getId(),
                    "" + allWorkers.get(i).getSalary()};
        }


        // Column Names
        String[] columnNames = { "Imie", "Nazwisko", "Email", "Stanowisko", "Indeks", "Zarobki"  };

        workersTable = new JTable(data, columnNames);
        workersTable.setBounds(30, 150, 540, 400);
        tableJScrollPane = new JScrollPane(workersTable);
        tableJScrollPane.setBounds(30, 150, 540, 400);

        window.add(tableJScrollPane);


        window.setVisible(true);

    }

    private void setTable(List<Worker> workers)
    {
        window.remove(tableJScrollPane);

        String[][] data = new String[workers.size()][6];

        for(int i = 0; i<workers.size(); i++){
            data[i] = new String[]{"" + workers.get(i).getName(), workers.get(i).getSurname(),
                    workers.get(i).getEmail(), workers.get(i).getPosition(), "" + workers.get(i).getId(),
                    "" + workers.get(i).getSalary()
            };
        }

        String[] columnNames = { "Imie", "Nazwisko", "Email", "Stanowisko", "Indeks", "Zarobki"  };

        workersTable = new JTable(data, columnNames);
        workersTable.setBounds(30, 160, 540, 400);
        tableJScrollPane = new JScrollPane(workersTable);
        tableJScrollPane.setBounds(30, 160, 540, 400);

        window.add(tableJScrollPane);

        window.invalidate();
        window.validate();
        window.revalidate();
        window.repaint();
    }
    private void dodajPracownika(){
        dodajPracownikaWindow = new JFrame("Dodaj pracownika");
        dodajPracownikaWindow.setSize(300,400);
        dodajPracownikaWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dodajPracownikaWindow.setLayout(null);
        dodajPracownikaWindow.setLocationRelativeTo(null);

        imieLabel = new JLabel("Imie: ");
        imieLabel.setBounds(100, 20, 100, 20);
        dodajPracownikaWindow.add(imieLabel);

        imieTextField = new JTextField("");
        imieTextField.setBounds(100, 40, 100, 20);
        dodajPracownikaWindow.add(imieTextField);

        nazwiskoLabel = new JLabel("Nazwisko: ");
        nazwiskoLabel.setBounds(100, 60, 100, 20);
        dodajPracownikaWindow.add(nazwiskoLabel);

        nazwiskoTextField = new JTextField();
        nazwiskoTextField.setBounds(100, 80, 100, 20);
        dodajPracownikaWindow.add(nazwiskoTextField);

        zarobkiLabel = new JLabel("Zarobki miesieczne: ");
        zarobkiLabel.setBounds(100, 100, 150, 20);
        dodajPracownikaWindow.add(zarobkiLabel);

        zarobkiTextField = new JTextField();
        zarobkiTextField.setBounds(100, 120, 100, 20);
        dodajPracownikaWindow.add(zarobkiTextField);

        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(100, 140, 100, 20);
        dodajPracownikaWindow.add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(100, 160, 100, 20);
        dodajPracownikaWindow.add(emailTextField);


        stanowiskoLabel = new JLabel("Stanowisko: ");
        stanowiskoLabel.setBounds(100, 180, 100, 20);
        dodajPracownikaWindow.add(stanowiskoLabel);

        stanowiskoTextField = new JTextField();
        stanowiskoTextField.setBounds(100, 200, 100, 20);
        dodajPracownikaWindow.add(stanowiskoTextField);



        dodajButton = new JButton("Dodaj");
        dodajButton.setBounds(100, 220, 100, 20);
        dodajButton.addActionListener(this);
        dodajPracownikaWindow.add(dodajButton);

        dodajPracownikaWindow.setVisible(true);
    }

    private void usunPracownika(){
        usunPracownikaWindow = new JFrame("Usun pracownika");
        usunPracownikaWindow.setSize(300,200);
        usunPracownikaWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usunPracownikaWindow.setLayout(null);
        usunPracownikaWindow.setLocationRelativeTo(null);

        idLabel = new JLabel("Id pracownika:");
        idLabel.setBounds(100, 30, 100, 20);
        usunPracownikaWindow.add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(100, 50, 100, 20);
        usunPracownikaWindow.add(idTextField);

        usunButton = new JButton("Zwolnij");
        usunButton.setBounds(100, 70, 100, 20);
        usunPracownikaWindow.add(usunButton);
        usunButton.addActionListener(this);

        usunPracownikaWindow.setVisible(true);

    }

    private void wyborDoEdytujPracownikaWindow(){

        edytujPracownikaWindow = new JFrame("Edytuj pracownika");
        edytujPracownikaWindow.setSize(400, 200);
        edytujPracownikaWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        edytujPracownikaWindow.setLayout(null);
        edytujPracownikaWindow.setLocationRelativeTo(null);


        wyborIdLabel = new JLabel("Wybierz id pracownika do edycji");
        wyborIdLabel.setBounds(100, 20, 200, 20);
        edytujPracownikaWindow.add(wyborIdLabel);

        wyborIdTextField = new JTextField();
        wyborIdTextField.setBounds(150, 50, 100, 20);
        edytujPracownikaWindow.add(wyborIdTextField);


        edytujButton = new JButton("Edytuj");
        edytujButton.setBounds(150, 80, 100, 20);
        edytujPracownikaWindow.add(edytujButton);
        edytujButton.addActionListener(this);

        edytujPracownikaWindow.setVisible(true);

    }

    private void edytujPracownikaWindow(){

        edytujPracownikaWindow2 = new JFrame("Edytuj pracownika");
        edytujPracownikaWindow2.setSize(300, 500);
        edytujPracownikaWindow2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        edytujPracownikaWindow2.setLayout(null);
        edytujPracownikaWindow2.setLocationRelativeTo(null);

        int chosenId = Integer.parseInt(wyborIdTextField.getText());
        Worker worker = DataBaseApi.getAllWorkers().stream().filter(x -> x.getId() == chosenId).findFirst().get();

        imieLabel = new JLabel("Imie");
        imieLabel.setBounds(100, 20, 100, 20);
        edytujPracownikaWindow2.add(imieLabel);

        imieTextField = new JTextField(worker.getName());
        imieTextField.setBounds(100, 50, 100, 20);
        edytujPracownikaWindow2.add(imieTextField);

        nazwiskoLabel = new JLabel("Nazwisko");
        nazwiskoLabel.setBounds(100, 80, 100, 20);
        edytujPracownikaWindow2.add(nazwiskoLabel);

       nazwiskoTextField = new JTextField(worker.getSurname());
       nazwiskoTextField.setBounds(100, 110, 100, 20);
       edytujPracownikaWindow2.add(nazwiskoTextField);

        zarobkiLabel = new JLabel("Pensja");
        zarobkiLabel.setBounds(100, 140, 100, 20);
        edytujPracownikaWindow2.add(zarobkiLabel);

        zarobkiTextField = new JTextField("" + worker.getSalary());
        zarobkiTextField.setBounds(100, 170, 100, 20);
        edytujPracownikaWindow2.add(zarobkiTextField);

        stanowiskoLabel = new JLabel("Stanowisko");
        stanowiskoLabel.setBounds(100, 200, 100, 20);
        edytujPracownikaWindow2.add(stanowiskoLabel);

        stanowiskoTextField = new JTextField(worker.getPosition());
        stanowiskoTextField.setBounds(100, 230, 100, 20);
        edytujPracownikaWindow2.add(stanowiskoTextField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(100, 260, 100, 20);
        edytujPracownikaWindow2.add(emailLabel);

        emailTextField = new JTextField(worker.getEmail());
        emailTextField.setBounds(100, 290, 100, 20);
        edytujPracownikaWindow2.add(emailTextField);

        idLabel2 = new JLabel("Id");
        idLabel2.setBounds(100, 320, 100, 20);
        edytujPracownikaWindow2.add(idLabel2);

        idTextField2 = new JTextField("" + worker.getId());
        idTextField2.setBounds(100, 350, 100, 20);
        edytujPracownikaWindow2.add(idTextField2);

        zapiszButton = new JButton("Zapisz zmiany");
        zapiszButton.addActionListener(this);
        zapiszButton.setBounds(75, 380, 150, 20);
        edytujPracownikaWindow2.add(zapiszButton);



        edytujPracownikaWindow2.setVisible(true);
    }

    private void wyszukajPracownikaPoNazwiskuWindow(){
        wyszukajPoNazwiskuWindow = new JFrame("Wyszukaj po nazwie");
        wyszukajPoNazwiskuWindow.setSize(300, 200);
        wyszukajPoNazwiskuWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wyszukajPoNazwiskuWindow.setLocationRelativeTo(null);
        wyszukajPoNazwiskuWindow.setLayout(null);

        wyszukajPoNazwiskuLabel = new JLabel("Wyszukaj po nazwie:");
        wyszukajPoNazwiskuLabel.setBounds(75, 20, 200, 20);
        wyszukajPoNazwiskuWindow.add(wyszukajPoNazwiskuLabel);

        wyszukajPoNazwiskuTextField = new JTextField();
        wyszukajPoNazwiskuTextField.setBounds(50, 50, 180, 20);
        wyszukajPoNazwiskuWindow.add(wyszukajPoNazwiskuTextField);

        wyszukajButton = new JButton("Wyszukaj");
        wyszukajButton.setBounds(75, 80, 100, 20);
        wyszukajButton.addActionListener(this);
        wyszukajPoNazwiskuWindow.add(wyszukajButton);

        wyszukajPoNazwiskuWindow.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == dodajPracownikaButton){
            dodajPracownika();
        }
        if(e.getSource() == dodajButton){
            if(!imieTextField.getText().isEmpty() && !nazwiskoTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !stanowiskoTextField.getText().isEmpty() && !zarobkiTextField.getText().isEmpty()){
            DataBaseApi.addWorker(imieTextField.getText(), nazwiskoTextField.getText(),
                    emailTextField.getText(), stanowiskoTextField.getText(), zarobkiTextField.getText());
            setTable(DataBaseApi.getAllWorkers());
            dodajPracownikaWindow.dispose();}
            else {JOptionPane.showMessageDialog(dodajPracownikaWindow, "Wprowadz wszystkie dane!");}
        }
        if(e.getSource() == zwolnijPracownikaButton){
            usunPracownika();
        }
        if(e.getSource() == usunButton){
            if(DataBaseApi.getAllWorkers()
                    .stream()
                    .anyMatch(w -> w.getId() == Integer.parseInt(idTextField.getText()))){
                DataBaseApi.deleteWorker(Integer.parseInt(idTextField.getText()));
                usunPracownikaWindow.dispose();
                setTable(DataBaseApi.getAllWorkers());}
            else JOptionPane.showMessageDialog(usunPracownikaWindow, "Pracownik o podanym indeksie nie istnieje");

        }

        if(e.getSource() == edytujPracownikaButton){
            wyborDoEdytujPracownikaWindow();
        }

        if(e.getSource() == edytujButton){
            edytujPracownikaWindow.dispose();
            edytujPracownikaWindow();
        }

        if(e.getSource() == zapiszButton){
            if(!idTextField2.getText().isEmpty() && !imieTextField.getText().isEmpty() && !nazwiskoTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !stanowiskoTextField.getText().isEmpty() && !zarobkiTextField.getText().isEmpty()) {
                DataBaseApi.editWorker(Integer.parseInt(idTextField2.getText()), imieTextField.getText(), nazwiskoTextField.getText(), emailTextField.getText(), stanowiskoTextField.getText(), Double.parseDouble(zarobkiTextField.getText()));
                edytujPracownikaWindow2.dispose();
                setTable(DataBaseApi.getAllWorkers());
            }
            else{
                JOptionPane.showMessageDialog(edytujPracownikaWindow2, "Uzupelnij wszystkie dane!");
            }
        }
        if(e.getSource() == wyszukajPracownikaPoNazwiskuButton){
            wyszukajPracownikaPoNazwiskuWindow();

        }

        if(e.getSource() == wyszukajButton){
            var workers = DataBaseApi.findWorker(wyszukajPoNazwiskuTextField.getText());
            setTable(workers);
            wyszukajPoNazwiskuWindow.dispose();
        }

        if(e.getSource() == powrotButton){
            window.dispose();
        }
    }
}
