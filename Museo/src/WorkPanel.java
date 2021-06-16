import bean.ArtistWork;
import bean.Lend;
import bean.Property;
import bean.Work;
import customUI.ForcedListSelectionModel;
import dao.DAO;
import dao.DAOFactory;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class WorkPanel extends JPanel {

    public WorkPanel() {
        this.setBorder(new EmptyBorder(20, 20, 10, 20));
        this.setBackground(Color.decode("#C2EAFF"));
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
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Color.decode("#FFDE59"));
        table.getTableHeader().setFont(new Font("Montserrat", Font.PLAIN, 16));
        table.setFont(new Font("Montserrat", Font.PLAIN, 14));

        JButton add_btn = new JButton("Ajouter");
        add_btn.setForeground(Color.white);
        add_btn.setBackground(Color.decode("#38B6FF"));
        add_btn.setFont(new Font("Montserrat", Font.BOLD, 20));

        JButton edit_btn = new JButton("Éditer");
        edit_btn.setForeground(Color.white);
        edit_btn.setBackground(Color.decode("#38B6FF"));
        edit_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        edit_btn.setVisible(false);

        JButton del_btn = new JButton("Supprimer");
        del_btn.setForeground(Color.white);
        del_btn.setBackground(Color.decode("#38B6FF"));
        del_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        del_btn.setVisible(false);

        JButton clear_btn = new JButton("Effacer la sélection");
        clear_btn.setForeground(Color.white);
        clear_btn.setBackground(Color.decode("#38B6FF"));
        clear_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        clear_btn.setVisible(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add_btn);
        buttonPanel.add(edit_btn);
        buttonPanel.add(del_btn);
        buttonPanel.add(clear_btn);
        buttonPanel.setBackground(Color.decode("#C2EAFF"));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setBorder(new LineBorder(Color.black, 2, true));

        this.add(scrollPane);
        this.add(new WorkForm(table, add_btn, edit_btn, del_btn, clear_btn));
        this.add(buttonPanel);
    }
}