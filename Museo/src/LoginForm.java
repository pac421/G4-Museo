import dao.MysqlConnect;

import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm {
    private static JFrame frame;
    private JPanel panel1;
    private JLabel errorLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton connexionButton;

    public static void main(String[] args) {
        frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().panel1);
        frame.setTitle("Museo - Connexion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(320, 220);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public LoginForm() {
        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if(email.isEmpty()) {
                    errorLabel.setText("Entrer un email");
                } else if(password.isEmpty()) {
                    errorLabel.setText("Entrer un mot de passe");
                } else {

                    try {
                        String sql = "SELECT * FROM `USER` WHERE email=? LIMIT 1";
                        PreparedStatement statement = MysqlConnect.connect().prepareStatement(sql);
                        statement.setString(1, email);

                        ResultSet result = statement.executeQuery();
                        if(result.next()) {
                            String hashed_password = result.getString("password");
                            if(hashed_password.equals(password)) {
                                new TableForm();
                                frame.setVisible(false);
                                frame.dispose();
                            } else {
                                errorLabel.setText("Mot de passe invalide");
                            }
                        } else {
                            errorLabel.setText("Email invalide");
                        }

                        result.close();
                        statement.close();
                        MysqlConnect.disconnect();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    } finally {
                        MysqlConnect.disconnect();
                    }
                }
            }
        });
    }
}
