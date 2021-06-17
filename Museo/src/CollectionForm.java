import bean.Artist;
import bean.ArtistWork;
import bean.Collection;
import bean.Work;
import dao.CollectionDAO;
import dao.DAO;
import dao.DAOFactory;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CollectionForm extends JPanel {

    public CollectionForm(JTable table, JButton add_btn, JButton del_btn, JButton edit_btn, JButton clear_btn) {

        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font labelFont = new Font("Montserrat", Font.PLAIN, 16);
        Font fieldFont = new Font("Montserrat", Font.PLAIN, 14);

        JLabel labelLabel = new JLabel("Label");
        labelLabel.setFont(labelFont);
        JTextField labelField = new JTextField(20);
        labelField.setFont(fieldFont);

        JLabel periodLabel = new JLabel("Période");
        periodLabel.setFont(labelFont);
        JTextField periodField = new JTextField(20);
        periodField.setFont(fieldFont);

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
                edit_btn.setVisible(true);
                add_btn.setVisible(false);

                Collection collection = collectionDAO.find(id);
                labelField.setText(collection.getLabel());
                periodField.setText(collection.getPeriod());
            }
        });

        clear_btn.addActionListener(e -> {
            System.out.println("clear selection");

            del_btn.setVisible(false);
            clear_btn.setVisible(false);
            edit_btn.setVisible(false);
            add_btn.setVisible(true);

            labelField.setText("");
            periodField.setText("");
        });

        del_btn.addActionListener(e -> {
            String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            System.out.println("delete selected_item_id : " + id);
            Collection collection = collectionDAO.find(id);

            DAO<Work> workDAO = new DAOFactory().getWorkDAO();
            HashMap<String, String> filters = new HashMap<>();
            filters.put("W.collection_id", collection.getId());
            ArrayList<Work> works = workDAO.findAll(filters);

            if(works.size() == 0) {
                Object[] options = {"Oui", "Non"};
                int dialogResult = JOptionPane.showOptionDialog(null, "Voulez-vous supprimer la ligne séléctionnée ?", "Confirmer la suppression", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    collectionDAO.delete(collection);

                    del_btn.setVisible(false);
                    clear_btn.setVisible(false);
                    edit_btn.setVisible(false);
                    add_btn.setVisible(true);

                    labelField.setText("");
                    periodField.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Cette collection a encore des oeuvres qui lui sont attirbuées, suppression annulée.");
            }
        });

        edit_btn.addActionListener(e -> {
            String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            System.out.println("edit selected_item_id : " + id);
            Collection collection = collectionDAO.find(id);
            collection.setLabel(labelField.getText());
            collection.setPeriod(periodField.getText());


            Object[] options = {"Oui", "Non"};
            int dialogResult = JOptionPane.showOptionDialog(null,"Voulez-vous modifier la ligne séléctionnée ?","Confirmer la modification", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(dialogResult == JOptionPane.YES_OPTION){
                collectionDAO.update(collection);

                del_btn.setVisible(false);
                clear_btn.setVisible(false);
                edit_btn.setVisible(false);
                add_btn.setVisible(true);

                labelField.setText("");
                periodField.setText("");
            }
        });

        add_btn.addActionListener(e -> {
            Object[] options = {"Oui", "Non"};
            int dialogResult = JOptionPane.showOptionDialog(null,"Voulez-vous ajout une collection ?","Confirmer l'ajout", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(dialogResult == JOptionPane.YES_OPTION) {
                Collection collection = new Collection(labelField.getText(), periodField.getText());
                collectionDAO.create(collection);
            }
        });

    }
}
