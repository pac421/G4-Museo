import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        JTabbedPane tabbedPane = new JTabbedPane();

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