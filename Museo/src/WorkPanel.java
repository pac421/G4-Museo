import bean.ArtistWork;
import bean.Lend;
import bean.Property;
import bean.Work;
import customUX.ForcedListSelectionModel;
import dao.DAO;
import dao.DAOFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class WorkPanel extends JPanel {
    public WorkPanel() {this.load();}

    public void load() {
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] columns = new String[] {
                "ID", "Titre", "Description", "Période", "Hauteur (cm)", "Largeur (cm)", "Profondeur (cm)", "Poids (kg)", "Catégorie", "Collection", "Artistes", "Statut", "Date d'acquisition", "Date de rente", "Nom du cessionnaire", "Prix (€)"
        };

        DAO<Work> workDAO = new DAOFactory().getWorkDAO();
        DAO<ArtistWork> artistWorkDAO = new DAOFactory().getArtistWorkDAO();
        DAO<Property> propertyDAO = new DAOFactory().getPropertyDAO();
        DAO<Lend> lendDAO = new DAOFactory().getLendDAO();

        ArrayList<Work> works = workDAO.findAll(new HashMap<>());
        int works_length = works.size();

        Object[][] data = new Object[works_length][];
        for (int i = 0; i < works_length; i++) {

            HashMap<String, String> filters = new HashMap<>();
            filters.put("work_id", works.get(i).getId());
            ArrayList<ArtistWork> artistWorks = artistWorkDAO.findAll(filters);

            int j = 1;
            StringBuilder artistsStr = new StringBuilder();
            for(ArtistWork artistWork : artistWorks) {
                artistsStr.append(artistWork.getArtist().getFirstname()).append(" ").append(artistWork.getArtist().getLastname());
                if(j < artistWorks.size()) {
                    artistsStr.append(", ");
                }
                j++;
            }

            Property property = propertyDAO.find(works.get(i).getId());
            Lend lend = lendDAO.find(works.get(i).getId());

            data[i] = new Object[]{
                    works.get(i).getId(),
                    works.get(i).getLabel(),
                    works.get(i).getDescription(),
                    works.get(i).getPeriod(),
                    works.get(i).getHeight(),
                    works.get(i).getWidth(),
                    works.get(i).getDepth(),
                    works.get(i).getWeight(),
                    works.get(i).getCategory().getLabel(),
                    works.get(i).getCollection().getLabel(),
                    artistsStr,
                    property != null ? "Propriété" : "Prêt",
                    property != null ? property.getOwnedAt() : lend.getStart(),
                    lend != null ? lend.getEnd() : null,
                    property != null ? property.getOwnedFrom() : lend.getLender(),
                    property != null ? property.getPrice() : null
            };
        }

        final Class[] columnClass = new Class[] {
                String.class, String.class, String.class, String.class, Double.class, Double.class, Double.class, Double.class, String.class, String.class, String.class, String.class, Date.class, Date.class, String.class, Double.class
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };

        JTable table = new JTable(model);
        table.removeColumn(table.getColumn("ID"));
        table.setSelectionBackground(Color.decode("#FFEFAD"));
        table.setSelectionModel(new ForcedListSelectionModel());
        table.setFont(new Font("Montserrat", Font.PLAIN, 14));
        table.setBorder(new LineBorder(Color.black, 1, false));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Color.decode("#FFDE59"));
        table.getTableHeader().setFont(new Font("Montserrat", Font.PLAIN, 16));
        table.getTableHeader().setBorder(new LineBorder(Color.black, 2, false));
        table.getTableHeader().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black, 1),
                BorderFactory.createLineBorder(Color.decode("#FFDE59"), 2))
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);

        JButton add_btn = new JButton("Ajouter");
        add_btn.setForeground(Color.white);
        add_btn.setBackground(Color.decode("#38B6FF"));
        add_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        add_btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0081CC"), 2),
                BorderFactory.createLineBorder(Color.decode("#38B6FF"), 15))
        );
        buttonPanel.add(add_btn);

        JButton edit_btn = new JButton("Éditer");
        edit_btn.setForeground(Color.white);
        edit_btn.setBackground(Color.decode("#38B6FF"));
        edit_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        edit_btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0081CC"), 2),
                BorderFactory.createLineBorder(Color.decode("#38B6FF"), 15))
        );
        edit_btn.setVisible(false);
        buttonPanel.add(edit_btn);

        JButton del_btn = new JButton("Supprimer");
        del_btn.setForeground(Color.white);
        del_btn.setBackground(Color.decode("#38B6FF"));
        del_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        del_btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0081CC"), 2),
                BorderFactory.createLineBorder(Color.decode("#38B6FF"), 15))
        );
        del_btn.setVisible(false);
        buttonPanel.add(del_btn);

        JButton clear_btn = new JButton("Effacer la sélection");
        clear_btn.setForeground(Color.white);
        clear_btn.setBackground(Color.decode("#38B6FF"));
        clear_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        clear_btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0081CC"), 2),
                BorderFactory.createLineBorder(Color.decode("#38B6FF"), 15))
        );
        clear_btn.setVisible(false);
        buttonPanel.add(clear_btn);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setBorder(new LineBorder(Color.white, 3, true));
        this.add(scrollPane);

        JPanel workPicturePanel = new JPanel();
        workPicturePanel.setBackground(Color.white);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.white);
        contentPanel.add(new WorkForm(table, add_btn, edit_btn, del_btn, clear_btn, workPicturePanel, this));
        contentPanel.add(workPicturePanel, BorderLayout.EAST);

        this.add(contentPanel);
        this.add(buttonPanel);
    }

    public void reload() {
        this.removeAll();
        this.revalidate();
        this.repaint();
        this.load();
    }
}