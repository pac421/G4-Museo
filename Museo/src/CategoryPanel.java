import bean.Category;
import customUI.ForcedListSelectionModel;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoryPanel extends JPanel {

    public CategoryPanel() {

        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


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



        JButton add_btn = new JButton("Ajouter");
        add_btn.setForeground(Color.white);
        add_btn.setBackground(Color.decode("#38B6FF"));
        add_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        add_btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0081CC"), 2),
                BorderFactory.createLineBorder(Color.decode("#38B6FF"), 15))
        );

        JButton edit_btn = new JButton("Éditer");
        edit_btn.setForeground(Color.white);
        edit_btn.setBackground(Color.decode("#38B6FF"));
        edit_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        edit_btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0081CC"), 2),
                BorderFactory.createLineBorder(Color.decode("#38B6FF"), 15))
        );
        edit_btn.setVisible(false);

        JButton del_btn = new JButton("Supprimer");
        del_btn.setForeground(Color.white);
        del_btn.setBackground(Color.decode("#38B6FF"));
        del_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        del_btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0081CC"), 2),
                BorderFactory.createLineBorder(Color.decode("#38B6FF"), 15))
        );
        del_btn.setVisible(false);

        JButton clear_btn = new JButton("Effacer la sélection");
        clear_btn.setForeground(Color.white);
        clear_btn.setBackground(Color.decode("#38B6FF"));
        clear_btn.setFont(new Font("Montserrat", Font.BOLD, 20));
        clear_btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#0081CC"), 2),
                BorderFactory.createLineBorder(Color.decode("#38B6FF"), 15))
        );
        clear_btn.setVisible(false);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add_btn);
        buttonPanel.add(edit_btn);
        buttonPanel.add(del_btn);
        buttonPanel.add(clear_btn);
        buttonPanel.setBackground(Color.white);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setBorder(new LineBorder(Color.white, 3, true));


        this.add(scrollPane);
        this.add(new CategoryForm(table, add_btn, edit_btn, del_btn, clear_btn));
        this.add(buttonPanel);


    }
}
