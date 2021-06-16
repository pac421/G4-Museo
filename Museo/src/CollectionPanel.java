import bean.Collection;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CollectionPanel extends JPanel {
    public CollectionPanel() {
        String[] columns = new String[] {
                "ID", "Label", "Période"
        };

        DAO<Collection> collectionDAO = new DAOFactory().getCollectionDAO();
        ArrayList<Collection> collections = collectionDAO.findAll(new HashMap<>());
        int collectionsLength = collections.size();

        Object[][] data = new Object[collectionsLength][];
        for (int i = 0; i < collectionsLength; i++) {
            data[i] = new Object[]{
                    collections.get(i).getId(),
                    collections.get(i).getLabel(),
                    collections.get(i).getPeriod(),
            };
        }

        final Class[] columnClass = new Class[] {
                String.class, String.class, String.class
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

        JButton add_edit_btn = new JButton("Ajouter");
        JButton del_btn = new JButton("Supprimer");
        JButton clear_btn = new JButton("Effacer la sélection");
        del_btn.setVisible(false);
        clear_btn.setVisible(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add_edit_btn);
        buttonPanel.add(del_btn);
        buttonPanel.add(clear_btn);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JScrollPane(table));
        this.add(new CollectionForm(table, del_btn, clear_btn));
        this.add(buttonPanel);
    }
}
