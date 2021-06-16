import bean.Collection;
import dao.CollectionDAO;
import dao.DAO;
import dao.DAOFactory;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.util.ArrayList;
import java.util.HashMap;

public class CollectionForm extends JPanel {

    public CollectionForm(JTable table, JButton del_btn, JButton clear_btn) {
        JLabel labelLabel = new JLabel("Label");
        JTextField labelField = new JTextField(20);

        JLabel periodLabel = new JLabel("Période");
        JTextField periodField = new JTextField(20);


        this.setBorder(BorderFactory.createTitledBorder("Ajouter une oeuvre"));


        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(labelLabel)
                .addComponent(periodLabel)
        );
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(labelField)
                .addComponent(periodField)
        );
        groupLayout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = groupLayout.createSequentialGroup();
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelLabel).addComponent(labelField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(periodLabel).addComponent(periodField));
        groupLayout.setVerticalGroup(verticalGroup);

        DAO<Collection> collectionDAO = new DAOFactory().getCollectionDAO();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1){
                String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                System.out.println("new selected_item_id : "+id);

                del_btn.setVisible(true);
                clear_btn.setVisible(true);

                Collection collection = collectionDAO.find(id);
                labelField.setText(collection.getLabel());
                periodField.setText(collection.getPeriod());
            }
        });

        clear_btn.addActionListener(e -> {
            System.out.println("clear selection");

            del_btn.setVisible(false);
            clear_btn.setVisible(false);

            labelField.setText("");
            periodField.setText("");
        });
    }
}