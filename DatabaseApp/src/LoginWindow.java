import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow implements ActionListener {

    JFrame window = new JFrame();
    JLabel loginLabel, passwordLabel;
    JTextField loginTextField, passwordTextField;
    JButton loginButton;


    public LoginWindow(){
        window.setSize(300,250);
        window.setTitle("Baza danych stacji benzynowej");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setLocationRelativeTo(null); //makes sure that new window is on the screen centre

        loginLabel = new JLabel("Wpisz login: ");
        loginLabel.setBounds(100, 20, 100, 20);
        window.add(loginLabel);

        loginTextField = new JTextField("");
        loginTextField.setBounds(70, 40, 130, 20);
        window.add(loginTextField);

        passwordLabel = new JLabel("Wpisz haslo:");
        passwordLabel.setBounds(100, 80, 100, 20);
        window.add(passwordLabel);

        passwordTextField = new JTextField("");
        passwordTextField.setBounds(70, 100, 130, 20);
        window.add(passwordTextField);

        loginButton = new JButton("Zaloguj");
        loginButton.setBounds(70, 130, 130, 20);
        window.add(loginButton);
        loginButton.addActionListener(this);


        loginTextField.setText("system");
        passwordTextField.setText("bazy");

        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            var isSuccess = DataBaseApi.LoginIntoDatabase(loginTextField.getText(), passwordTextField.getText());
            if (isSuccess) {
                window.dispose();
                new OptionsWindow();
            } else {
                JOptionPane.showMessageDialog(window, "Nieprawidlowy login lub haslo. Sprobuj ponownie.");
            }
        }

    }
}
