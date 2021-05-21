import bean.ArtistWork;
import bean.Lend;
import bean.Property;
import bean.Work;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class WorkPanel extends JPanel {

    public WorkPanel() {
        String[] columns = new String[] {
                "Titre", "Description", "Période", "Hauteur (cm)", "Largeur (cm)", "Profondeur (cm)", "Poids (kg)", "Catégorie", "Collection", "Artistes", "Statut", "Date d'acquisition", "Date de rente", "Nom du cessionnaire", "Prix (€)"
        };

        DAO<Work> workDAO = new DAOFactory().getWorkDAO();
        ArrayList<Work> works = workDAO.findAll(new HashMap<>());
        int works_length = works.size();

        DAO<ArtistWork> artistWorkDAO = new DAOFactory().getArtistWorkDAO();
        DAO<Property> propertyDAO = new DAOFactory().getPropertyDAO();
        DAO<Lend> lendDAO = new DAOFactory().getLendDAO();

        Object[][] data = new Object[works_length][];
        for (int i = 0; i < works_length; i++) {

            HashMap<String, String> filters = new HashMap<>();
            filters.put("work_id", works.get(i).getId());

            ArrayList<ArtistWork> artistWorks = artistWorkDAO.findAll(filters);

            int j = 1;
            StringBuilder artistsStr = new StringBuilder();
            for(ArtistWork artistWork : artistWorks) {
                artistsStr.append(artistWork.getArtist().getFirstname()+" "+artistWork.getArtist().getLastname());
                if(j < artistWorks.size()) {
                    artistsStr.append(", ");
                }
                j++;
            }

            Property property = propertyDAO.find(works.get(i).getId());
            Lend lend = lendDAO.find(works.get(i).getId());



            data[i] = new Object[]{
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
                String.class, String.class, String.class, Double.class, Double.class, Double.class, Double.class, String.class, String.class, String.class, String.class, Date.class, Date.class, String.class, Double.class
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
        this.add(new JScrollPane(table));
        this.setLayout(new GridLayout(0, 1));
    }
}