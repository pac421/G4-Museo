import dao.MysqlConnect;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        JLabel emailLabel = new JLabel("E-mail");
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Mot de passe");
        JPasswordField passwordField = new JPasswordField(20);
        JCheckBox rememberMeCheckbox = new JCheckBox("Sauvegarder mes identifiants sur ce poste");
        JLabel errorLabel = new JLabel("");
        JButton connexionButton = new JButton("Connexion");

        JPanel panel = new JPanel();
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(rememberMeCheckbox);
        panel.add(errorLabel);
        panel.add(connexionButton);

        connexionButton.addActionListener(event -> {
            String email = emailField.getText();
            String password = String.valueOf(passwordField.getPassword());
            Boolean rememberMe = rememberMeCheckbox.isSelected();

            String response = MysqlConnect.tryAuthentication(email, password, rememberMe);
            errorLabel.setText(response);

            if(response.equals("success")) {
                new MainFrame();
                this.setVisible(false);
                this.dispose();
            }
        });

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(340,180);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }
}