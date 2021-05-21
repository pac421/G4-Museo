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
                "Label"
        };

        DAO<Category> categoryDAO = new DAOFactory().getCategoryDAO();
        ArrayList<Category> categories = categoryDAO.findAll(new HashMap<>());
        int categoriesLength = categories.size();

        Object[][] data = new Object[categoriesLength][];
        for (int i = 0; i < categoriesLength; i++) {
            data[i] = new Object[]{
                    categories.get(i).getLabel()
            };
        }

        final Class[] columnClass = new Class[] {
                String.class
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
