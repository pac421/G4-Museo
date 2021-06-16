import bean.Category;
import bean.Collection;
import bean.Category;
import bean.Work;
import dao.CategoryDAO;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoryForm extends JPanel {

    public CategoryForm(JTable table, JButton del_btn, JButton clear_btn) {

        JLabel nameLabel = new JLabel("Name");
        JTextField nameField = new JTextField(20);


        this.setBorder(BorderFactory.createTitledBorder("Ajouter une catégorie"));

        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(nameLabel)
        );
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(nameField)

        );
        groupLayout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = groupLayout.createSequentialGroup();
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nameLabel).addComponent(nameField));
        groupLayout.setVerticalGroup(verticalGroup);


        DAO<Category> categoryDAO = new DAOFactory().getCategoryDAO();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1){
                String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                System.out.println("new selected_item_id : "+id);

                this.setBorder(BorderFactory.createTitledBorder("Éditer une catégorie"));

                del_btn.setVisible(true);
                clear_btn.setVisible(true);

                Category category = categoryDAO.find(id);
                nameField.setText(category.getLabel());
        }
        });
        clear_btn.addActionListener(e -> {
            System.out.println("clear selection");

            this.setBorder(BorderFactory.createTitledBorder("Ajouter une catégorie"));

            del_btn.setVisible(false);
            clear_btn.setVisible(false);
            nameField.setText("");

        });

    }

}
