import bean.Category;
import bean.Collection;
import bean.Role;
import bean.User;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserForm extends JPanel {

    public UserForm(JTable table, JButton add_btn, JButton edit_btn, JButton del_btn, JButton clear_btn) {

        this.setBorder(new EmptyBorder(20, 0, 10, 0));
        this.setBackground(Color.white);

        Font labelFont = new Font("Montserrat", Font.PLAIN, 16);
        Font fieldFont = new Font("Montserrat", Font.PLAIN, 14);

        JLabel firstNameLabel = new JLabel("Prénom");
        firstNameLabel.setFont(labelFont);
        JTextField firstNameField = new JTextField(20);
        firstNameField.setFont(fieldFont);

        JLabel lastNameLabel = new JLabel("Nom");
        lastNameLabel.setFont(labelFont);
        JTextField lastNameField = new JTextField(20);
        lastNameField.setFont(fieldFont);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(labelFont);
        JTextField emailField = new JTextField(20);
        emailField.setFont(fieldFont);

        JComboBox<Object> roleList = new JComboBox<>();
        roleList.setFont(fieldFont);
        DAO<Role> roleDAO = new DAOFactory().getRoleDAO();
        ArrayList<Role> roles = roleDAO.findAll(new HashMap<>());

        JLabel roleLabel = new JLabel("Rôle");
        roleLabel.setFont(labelFont);
        for (Role role : roles) {
            roleList.addItem(new Item(role.getId(), role.getLabel()));
        }
        roleList.getModel().setSelectedItem("");

        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(firstNameLabel)
                .addComponent(lastNameLabel)
                .addComponent(emailLabel)
                .addComponent(roleLabel)
        );
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(firstNameField)
                .addComponent(lastNameField)
                .addComponent(emailField)
                .addComponent(roleList)

        );
        groupLayout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = groupLayout.createSequentialGroup();
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(firstNameLabel).addComponent(firstNameField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lastNameLabel).addComponent(lastNameField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(emailLabel).addComponent(emailField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(roleLabel).addComponent(roleList));
        groupLayout.setVerticalGroup(verticalGroup);

        DAO<User> userDAO = new DAOFactory().getUserDAO();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1){
                String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                System.out.println("new selected_item_id : "+id);

                add_btn.setVisible(false);
                del_btn.setVisible(true);
                clear_btn.setVisible(true);
                edit_btn.setVisible(true);

                User user = userDAO.find(id);
                firstNameField.setText(user.getFirstname());
                lastNameField.setText(user.getLastname());
                emailField.setText(user.getEmail());
                roleList.getModel().setSelectedItem(user.getRole());

            }
        });

        clear_btn.addActionListener(e -> {
            System.out.println("clear selection");

            table.getSelectionModel().clearSelection();

            add_btn.setVisible(true);
            edit_btn.setVisible(false);
            del_btn.setVisible(false);
            clear_btn.setVisible(false);

            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            roleList.getModel().setSelectedItem("");

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
