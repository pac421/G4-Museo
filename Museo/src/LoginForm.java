import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm {
    private JPanel panel1;
    private JLabel errorLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton connexionButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
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

                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        String sql = "SELECT * FROM `USER` WHERE email=? LIMIT 1";
                        PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
                        statement.setString(1, email);

                        ResultSet result = statement.executeQuery();
                        if(result.next()) {
                            String hashed_password = result.getString("password");
                            if(hashed_password.equals(password)) {
                                errorLabel.setText("Connecté !");
                                // open new form here
                            } else {
                                errorLabel.setText("Mot de passe invalide");
                            }
                        } else {
                            errorLabel.setText("Email invalide");
                        }

                        result.close();
                        statement.close();
                        mysqlConnect.disconnect();
                    } catch (SQLException | IOException exception) {
                        exception.printStackTrace();
                    } finally {
                        mysqlConnect.disconnect();
                    }
                }
            }
        });
    }
}
