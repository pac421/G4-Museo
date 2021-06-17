import dao.MysqlConnect;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        try {
            BufferedImage logo = ImageIO.read(new File("resources/logo.png"));
            JLabel logoLabel = new JLabel(new ImageIcon(logo.getScaledInstance(200, 200, Image.SCALE_FAST)));
            logoLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
            panel.add(logoLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setFont(new Font("Montserrat", Font.PLAIN, 16));
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Montserrat", Font.PLAIN, 12));
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Montserrat", Font.PLAIN, 16));
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Montserrat", Font.PLAIN, 14));
        panel.add(passwordField);

        JCheckBox rememberMeCheckbox = new JCheckBox("Sauvegarder mes identifiants sur ce poste");
        rememberMeCheckbox.setBackground(Color.white);
        rememberMeCheckbox.setFont(new Font("Montserrat", Font.PLAIN, 14));
        rememberMeCheckbox.setBorder(new EmptyBorder(20, 0, 30, 0));
        panel.add(rememberMeCheckbox);

        JLabel errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Montserrat", Font.PLAIN, 14));
        panel.add(errorLabel);

        JButton connexionButton = new JButton("Connexion");
        connexionButton.setForeground(Color.white);
        connexionButton.setBackground(Color.decode("#38B6FF"));
        connexionButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        connexionButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0081CC"), 2),
                BorderFactory.createLineBorder(Color.decode("#38B6FF"), 15))
        );
        panel.add(connexionButton);

        JButton closeButton = new JButton("Quitter");
        closeButton.setForeground(Color.black);
        closeButton.setBackground(Color.white);
        closeButton.setFont(new Font("Montserrat", Font.BOLD, 14));
        closeButton.setBorder(new EmptyBorder(40, 0, 0, 0));
        closeButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 0),
                BorderFactory.createLineBorder(Color.white, 3))
        );
        panel.add(closeButton);

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

        closeButton.addActionListener(event -> {
            this.setVisible(false);
            this.dispose();
        });

        this.add(panel);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(400,550);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}