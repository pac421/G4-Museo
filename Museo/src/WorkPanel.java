import bean.Work;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class WorkPanel extends JPanel {

    public WorkPanel() {
        String[] columns = new String[] {
                //"Titre", "Description", "Période", "Hauteur", "Largeur", "Profondeur", "Poids", "Catégorie", "Artistes", "Collection", "Date d'acquisition", "Nom du vendeur/donneur", "Prix"
                "Titre", "Description", "Période", "Hauteur (cm)", "Largeur (cm)", "Profondeur (cm)", "Poids (kg)", "Catégorie", "Collection"
        };

        DAO<Work> workDAO = new DAOFactory().getWorkDAO();
        ArrayList<Work> works = workDAO.findAll();
        int works_length = works.size();

        Object[][] data = new Object[works_length][];
        for (int i = 0; i < works_length; i++) {
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
            };
        }

        final Class[] columnClass = new Class[] {
                String.class, String.class, String.class, Double.class, Double.class, Double.class, Double.class, String.class, String.class
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