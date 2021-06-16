import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        UIManager.put("TabbedPane.selected", Color.decode("#0081CC"));
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new LineBorder(Color.white, 0, true));

        JPanel workPanel, artistPanel, categoryPanel, collectionPanel, userPanel;
        workPanel = new WorkPanel();
        artistPanel = new ArtistPanel();
        categoryPanel = new CategoryPanel();
        collectionPanel = new CollectionPanel();
        userPanel = new UserPanel();

        tabbedPane.addTab("Œuvres", workPanel);
        tabbedPane.addTab("Artistes", artistPanel);
        tabbedPane.addTab("Catégories", categoryPanel);
        tabbedPane.addTab("Collections", collectionPanel);
        tabbedPane.addTab("Utilisateurs", userPanel);

        tabbedPane.setBackground(Color.decode("#38B6FF"));
        tabbedPane.setForeground(Color.white);
        tabbedPane.setFont(new Font("Montserrat", Font.BOLD, 22));

        this.add(tabbedPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(1400,800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}