import bean.Category;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoryPanel extends JPanel {

    public CategoryPanel() {
        String[] columns = new String[] {
                "ID",
                "Label"
        };

        DAO<Category> categoryDAO = new DAOFactory().getCategoryDAO();
        ArrayList<Category> categories = categoryDAO.findAll(new HashMap<>());
        int categoriesLength = categories.size();

        Object[][] data = new Object[categoriesLength][];
        for (int i = 0; i < categoriesLength; i++) {
            data[i] = new Object[]{
                    categories.get(i).getId(),
                    categories.get(i).getLabel()
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
        this.add(new CategoryForm(table,del_btn,clear_btn));
        this.add(buttonPanel);


    }
}
