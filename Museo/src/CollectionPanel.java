import bean.Collection;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CollectionPanel extends JPanel {
    public CollectionPanel() {
        String[] columns = new String[] {
                "Label", "Période"
        };

        DAO<Collection> collectionDAO = new DAOFactory().getCollectionDAO();
        ArrayList<Collection> collections = collectionDAO.findAll();
        int collectionsLength = collections.size();

        Object[][] data = new Object[collectionsLength][];
        for (int i = 0; i < collectionsLength; i++) {
            data[i] = new Object[]{
                    collections.get(i).getLabel(),
                    collections.get(i).getPeriod(),
            };
        }

        final Class[] columnClass = new Class[] {
                String.class, String.class
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
