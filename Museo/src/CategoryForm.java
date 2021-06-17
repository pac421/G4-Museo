import bean.Category;

import bean.Collection;
import bean.Work;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoryForm extends JPanel {

    public CategoryForm(JTable table, JButton add_btn, JButton edit_btn, JButton del_btn, JButton clear_btn) {

        this.setBorder(new EmptyBorder(20, 0, 10, 0));
        this.setBackground(Color.white);

        Font labelFont = new Font("Montserrat", Font.PLAIN, 16);
        Font fieldFont = new Font("Montserrat", Font.PLAIN, 14);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(labelFont);
        JTextField nameField = new JTextField(20);
        nameField.setFont(fieldFont);



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

                add_btn.setVisible(false);
                del_btn.setVisible(true);
                clear_btn.setVisible(true);
                edit_btn.setVisible(true);

                Category category = categoryDAO.find(id);
                nameField.setText(category.getLabel());
        }
        });
        clear_btn.addActionListener(e -> {
            System.out.println("clear selection");

            table.getSelectionModel().clearSelection();

            add_btn.setVisible(true);
            edit_btn.setVisible(false);
            del_btn.setVisible(false);
            clear_btn.setVisible(false);

            nameField.setText("");

        });

        del_btn.addActionListener(e -> {
            String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            System.out.println("delete selected_item_id : " + id);
            Category category = categoryDAO.find(id);

            DAO<Work> workDAO = new DAOFactory().getWorkDAO();
            HashMap<String, String> filters = new HashMap<>();
            filters.put("W.category_id", category.getId());
            ArrayList<Work> works = workDAO.findAll(filters);

            if(works.size() == 0) {
                Object[] options = {"Oui", "Non"};
                int dialogResult = JOptionPane.showOptionDialog(null, "Voulez-vous supprimer la ligne séléctionnée ?", "Confirmer la suppression", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    categoryDAO.delete(category);

                    del_btn.setVisible(false);
                    clear_btn.setVisible(false);
                    edit_btn.setVisible(false);
                    add_btn.setVisible(true);

                    nameField.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Cette catégorie a encore des oeuvres qui lui sont attirbuées, suppression annulée.");
            }
        });

        edit_btn.addActionListener(e -> {
            String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            System.out.println("edit selected_item_id : " + id);
            Category category = categoryDAO.find(id);
            category.setLabel(nameField.getText());

            Object[] options = {"Oui", "Non"};
            int dialogResult = JOptionPane.showOptionDialog(null,"Voulez-vous modifier la ligne séléctionnée ?","Confirmer la modification", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(dialogResult == JOptionPane.YES_OPTION){
                categoryDAO.update(category);

                del_btn.setVisible(false);
                clear_btn.setVisible(false);
                edit_btn.setVisible(false);
                add_btn.setVisible(true);

                nameField.setText("");
            }
        });

        add_btn.addActionListener(e -> {
            Object[] options = {"Oui", "Non"};
            int dialogResult = JOptionPane.showOptionDialog(null,"Voulez-vous ajout une collection ?","Confirmer l'ajout", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(dialogResult == JOptionPane.YES_OPTION) {
                Category category = new Category(nameField.getText());
                categoryDAO.create(category);
            }
        });

    }

    static class Item {
        private final String id;
        private final String description;

        public Item(String id, String description) {
            this.id = id;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

}
