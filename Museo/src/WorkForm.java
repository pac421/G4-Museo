import bean.*;
import dao.DAO;
import dao.DAOFactory;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WorkForm extends JPanel {
    JLabel ownedAtLabel, ownedFromLabel, priceLabel, startLabel, endLabel, lenderLabel;
    JTextField ownedAtField, ownedFromField, priceField, startField, endField, lenderField;

    public WorkForm(JTable table, JButton add_btn, JButton edit_btn, JButton del_btn, JButton clear_btn) {
        this.setBorder(new EmptyBorder(20, 0, 10, 0));
        this.setBackground(Color.white);

        Font labelFont = new Font("Montserrat", Font.PLAIN, 16);
        Font fieldFont = new Font("Montserrat", Font.PLAIN, 14);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(labelFont);
        JPanel typeField = new JPanel();
        typeField.setBackground(Color.white);
        ButtonGroup bg = new ButtonGroup();

        JRadioButton rbProperty = new JRadioButton("Propriété");
        rbProperty.setBackground(Color.white);
        rbProperty.setSelected(true);
        rbProperty.setFont(fieldFont);
        bg.add(rbProperty);
        typeField.add(rbProperty);

        JRadioButton rbLend = new JRadioButton("Prêt");
        rbLend.setBackground(Color.white);
        rbLend.setFont(fieldFont);
        bg.add(rbLend);
        typeField.add(rbLend);

        JLabel titleLabel = new JLabel("Titre");
        titleLabel.setFont(labelFont);
        JTextField titleField = new JTextField(20);
        titleField.setFont(fieldFont);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setFont(labelFont);
        JTextField descriptionField = new JTextField(20);
        descriptionField.setFont(fieldFont);

        JLabel periodLabel = new JLabel("Période");
        periodLabel.setFont(labelFont);
        JTextField periodField = new JTextField(20);
        periodField.setFont(fieldFont);

        JLabel heightLabel = new JLabel("Hauteur (cm)");
        heightLabel.setFont(labelFont);
        JTextField heightField = new JTextField(20);
        heightField.setFont(fieldFont);

        JLabel widthLabel = new JLabel("Largeur (cm)");
        widthLabel.setFont(labelFont);
        JTextField widthField = new JTextField(20);
        widthField.setFont(fieldFont);

        JLabel depthLabel = new JLabel("Profondeur (cm)");
        depthLabel.setFont(labelFont);
        JTextField depthField = new JTextField(20);
        depthField.setFont(fieldFont);

        JLabel weightLabel = new JLabel("Poids (kg)");
        weightLabel.setFont(labelFont);
        JTextField weightField = new JTextField(20);
        weightField.setFont(fieldFont);

        JComboBox<Object> categoryList = new JComboBox<>();
        categoryList.setFont(fieldFont);

        DAO<Category> categoryDAO = new DAOFactory().getCategoryDAO();
        ArrayList<Category> categories = categoryDAO.findAll(new HashMap<>());

        JLabel categoryLabel = new JLabel("Catégorie");
        categoryLabel.setFont(labelFont);
        for (Category category : categories) {
            categoryList.addItem(new Item(category.getId(), category.getLabel()));
        }
        categoryList.getModel().setSelectedItem("");

        JComboBox<Object> collectionList = new JComboBox<>();
        collectionList.setFont(fieldFont);

        DAO<Collection> collectionDAO = new DAOFactory().getCollectionDAO();
        ArrayList<Collection> collections = collectionDAO.findAll(new HashMap<>());

        JLabel collectionLabel = new JLabel("Collection");
        collectionLabel.setFont(labelFont);
        for (Collection collection : collections) {
            collectionList.addItem(new Item(collection.getId(), collection.getLabel()));
        }
        collectionList.getModel().setSelectedItem("");

        ownedAtLabel = new JLabel("Date d'acquisition");
        ownedAtLabel.setFont(labelFont);
        ownedAtField = new JTextField(20);
        ownedAtField.setFont(fieldFont);

        ownedFromLabel = new JLabel("Nom du cessionnaire");
        ownedFromLabel.setFont(labelFont);
        ownedFromField = new JTextField(20);
        ownedFromField.setFont(fieldFont);

        priceLabel = new JLabel("Prix (€)");
        priceLabel.setFont(labelFont);
        priceField = new JTextField(20);
        priceField.setFont(fieldFont);

        startLabel = new JLabel("Date d'acquisition");
        startLabel.setFont(labelFont);
        startLabel.setVisible(false);
        startField = new JTextField(20);
        startField.setFont(fieldFont);
        startField.setVisible(false);

        endLabel = new JLabel("Date de rente");
        endLabel.setFont(labelFont);
        endLabel.setVisible(false);
        endField = new JTextField(20);
        endField.setFont(fieldFont);
        endField.setVisible(false);

        lenderLabel = new JLabel("Nom du cessionnaire");
        lenderLabel.setFont(labelFont);
        lenderLabel.setVisible(false);
        lenderField = new JTextField(20);
        lenderField.setFont(fieldFont);
        lenderField.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(typeLabel)
                .addComponent(titleLabel)
                .addComponent(descriptionLabel)
                .addComponent(periodLabel)
                .addComponent(heightLabel)
                .addComponent(widthLabel)
                .addComponent(depthLabel)
                .addComponent(weightLabel)
                .addComponent(categoryLabel)
                .addComponent(collectionLabel)
                .addComponent(ownedAtLabel)
                .addComponent(ownedFromLabel)
                .addComponent(priceLabel)
                .addComponent(startLabel)
                .addComponent(endLabel)
                .addComponent(lenderLabel)
        );
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(typeField)
                .addComponent(titleField)
                .addComponent(descriptionField)
                .addComponent(periodField)
                .addComponent(heightField)
                .addComponent(widthField)
                .addComponent(depthField)
                .addComponent(weightField)
                .addComponent(categoryList)
                .addComponent(collectionList)
                .addComponent(ownedAtField)
                .addComponent(ownedFromField)
                .addComponent(priceField)
                .addComponent(startField)
                .addComponent(endField)
                .addComponent(lenderField)
        );
        groupLayout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = groupLayout.createSequentialGroup();
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(typeLabel).addComponent(typeField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(titleLabel).addComponent(titleField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(descriptionLabel).addComponent(descriptionField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(periodLabel).addComponent(periodField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(heightLabel).addComponent(heightField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(widthLabel).addComponent(widthField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(depthLabel).addComponent(depthField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(weightLabel).addComponent(weightField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(categoryLabel).addComponent(categoryList));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(collectionLabel).addComponent(collectionList));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(ownedAtLabel).addComponent(ownedAtField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(ownedFromLabel).addComponent(ownedFromField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(priceLabel).addComponent(priceField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(startLabel).addComponent(startField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(endLabel).addComponent(endField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lenderLabel).addComponent(lenderField));
        groupLayout.setVerticalGroup(verticalGroup);

        DAO<Work> workDAO = new DAOFactory().getWorkDAO();
        DAO<Property> propertyDAO = new DAOFactory().getPropertyDAO();
        DAO<Lend> lendDAO = new DAOFactory().getLendDAO();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1){
                String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                System.out.println("new selected_item_id : "+id);

                add_btn.setVisible(false);
                edit_btn.setVisible(true);
                del_btn.setVisible(true);
                clear_btn.setVisible(true);

                Work work = workDAO.find(id);

                titleField.setText(work.getLabel());
                descriptionField.setText(work.getDescription());
                periodField.setText(work.getPeriod());
                heightField.setText(String.valueOf(work.getHeight()));
                widthField.setText(String.valueOf(work.getWidth()));
                depthField.setText(String.valueOf(work.getDepth()));
                weightField.setText(String.valueOf(work.getWeight()));
                categoryList.getModel().setSelectedItem(work.getCategory().getLabel());
                collectionList.getModel().setSelectedItem(work.getCollection().getLabel());

                Property property = propertyDAO.find(id);
                Lend lend = lendDAO.find(id);

                if (property != null) {
                    rbProperty.setSelected(true);
                    rbLend.setSelected(false);
                    showPropertyForm(true);

                    ownedAtField.setText(String.valueOf(property.getOwnedAt()));
                    ownedFromField.setText(property.getOwnedFrom());
                    priceField.setText(String.valueOf(property.getPrice()));
                } else {
                    rbProperty.setSelected(false);
                    rbLend.setSelected(true);
                    showPropertyForm(false);

                    startField.setText(String.valueOf(lend.getStart()));
                    endField.setText(String.valueOf(lend.getEnd()));
                    lenderField.setText(lend.getLender());
                }
            }
        });

        clear_btn.addActionListener(e -> {
            System.out.println("clear selection");

            table.getSelectionModel().clearSelection();

            add_btn.setVisible(true);
            edit_btn.setVisible(false);
            del_btn.setVisible(false);
            clear_btn.setVisible(false);

            titleField.setText("");
            descriptionField.setText("");
            periodField.setText("");
            heightField.setText("");
            widthField.setText("");
            depthField.setText("");
            weightField.setText("");
            categoryList.getModel().setSelectedItem("");
            collectionList.getModel().setSelectedItem("");

            ownedAtField.setText("");
            ownedFromField.setText("");
            priceField.setText("");
            startField.setText("");
            endField.setText("");
            lenderField.setText("");
        });

        rbProperty.addActionListener(e -> {
            System.out.println("property selected");
            showPropertyForm(true);
        });

        rbLend.addActionListener(e -> {
            System.out.println("lend selected");
            showPropertyForm(false);
        });
    }

    private void showPropertyForm(Boolean state) {
        ownedAtLabel.setVisible(state);
        ownedAtField.setVisible(state);
        ownedFromLabel.setVisible(state);
        ownedFromField.setVisible(state);
        priceLabel.setVisible(state);
        priceField.setVisible(state);

        startLabel.setVisible(!state);
        startField.setVisible(!state);
        endLabel.setVisible(!state);
        endField.setVisible(!state);
        lenderLabel.setVisible(!state);
        lenderField.setVisible(!state);
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