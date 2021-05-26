import bean.Category;
import bean.Collection;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.util.ArrayList;
import java.util.HashMap;

public class WorkForm extends JPanel {

    public WorkForm() {
        JLabel titleLabel = new JLabel("Titre");
        JTextField titleField = new JTextField(20);

        JLabel descriptionLabel = new JLabel("Description");
        JTextField descriptionField = new JTextField(20);

        JLabel periodLabel = new JLabel("Période");
        JTextField periodField = new JTextField(20);

        JLabel heightLabel = new JLabel("Hauteur (cm)");
        JTextField heightField = new JTextField(20);

        JLabel widthLabel = new JLabel("Largeur (cm)");
        JTextField widthField = new JTextField(20);

        JLabel depthLabel = new JLabel("Profondeur (cm)");
        JTextField depthField = new JTextField(20);

        JLabel weightLabel = new JLabel("Poids (kg)");
        JTextField weightField = new JTextField(20);

        JComboBox<Object> categoryList = new JComboBox<>();
        DAO<Category> categoryDAO = new DAOFactory().getCategoryDAO();
        ArrayList<Category> categories = categoryDAO.findAll(new HashMap<>());
        JLabel categoryLabel = new JLabel("Catégorie");
        for (Category category : categories) {
            categoryList.addItem(new Item(category.getId(), category.getLabel()));
        }

        JComboBox<Object> collectionList = new JComboBox<>();
        DAO<Collection> collectionDAO = new DAOFactory().getCollectionDAO();
        ArrayList<Collection> collections = collectionDAO.findAll(new HashMap<>());
        JLabel collectionLabel = new JLabel("Collection");
        for (Collection collection : collections) {
            collectionList.addItem(new Item(collection.getId(), collection.getLabel()));
        }

        this.setBorder(BorderFactory.createTitledBorder("Ajouter une oeuvre"));



        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(titleLabel)
                .addComponent(descriptionLabel)
                .addComponent(periodLabel)
                .addComponent(heightLabel)
                .addComponent(widthLabel)
                .addComponent(depthLabel)
                .addComponent(weightLabel)
                .addComponent(categoryLabel)
                .addComponent(collectionLabel)
        );
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(titleField)
                .addComponent(descriptionField)
                .addComponent(periodField)
                .addComponent(heightField)
                .addComponent(widthField)
                .addComponent(depthField)
                .addComponent(weightField)
                .addComponent(categoryList)
                .addComponent(collectionList)
        );
        groupLayout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = groupLayout.createSequentialGroup();
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(titleLabel).addComponent(titleField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(descriptionLabel).addComponent(descriptionField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(periodLabel).addComponent(periodField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(heightLabel).addComponent(heightField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(widthLabel).addComponent(widthField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(depthLabel).addComponent(depthField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(weightLabel).addComponent(weightField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(categoryLabel).addComponent(categoryList));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(collectionLabel).addComponent(collectionList));
        groupLayout.setVerticalGroup(verticalGroup);
    }

    static class ItemRenderer extends BasicComboBoxRenderer {
        @Override
        public ItemRenderer getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null) {
                Item item = (Item) value;
                setText(item.getDescription().toUpperCase());
            }
            if (index == -1) {
                Item item = (Item) value;
                setText("" + item.getId());
            }
            return this;
        }
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