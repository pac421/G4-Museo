import bean.Artist;
import bean.Category;
import bean.Collection;
import bean.Work;
import dao.ArtistDAO;
import dao.DAO;
import dao.DAOFactory;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class ArtistForm extends JPanel {

    public ArtistForm(JTable table, JButton del_btn, JButton clear_btn) {
        JLabel lastNameLabel = new JLabel("Nom");
        JTextField lastNameField = new JTextField(20);

        JLabel nameLabel = new JLabel("Prénom");
        JTextField nameField = new JTextField(20);

        JLabel periodLabel = new JLabel("Période");
        JTextField periodField = new JTextField(20);

        this.setBorder(BorderFactory.createTitledBorder("Ajouter une oeuvre"));


        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(lastNameLabel)
                .addComponent(nameLabel)
                .addComponent(periodLabel)
        );
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(lastNameField)
                .addComponent(nameField)
                .addComponent(periodField)
        );
        groupLayout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = groupLayout.createSequentialGroup();
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lastNameLabel).addComponent(lastNameField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nameLabel).addComponent(nameField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(periodLabel).addComponent(periodField));
        groupLayout.setVerticalGroup(verticalGroup);

        DAO<Artist> artistDAO = new DAOFactory().getArtistDAO();

       table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1){
                String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                System.out.println("new selected_item_id : "+id);

                del_btn.setVisible(true);
                clear_btn.setVisible(true);

                Artist artist = artistDAO.find(id);
                lastNameField.setText(artist.getLastname());
                nameField.setText(artist.getFirstname());
                periodField.setText(artist.getPeriod());
            }
        });

        clear_btn.addActionListener(e -> {
            System.out.println("clear selection");

            del_btn.setVisible(false);
            clear_btn.setVisible(false);

            lastNameField.setText("");
            nameField.setText("");
            periodField.setText("");
        });

        del_btn.addActionListener(e -> {
            String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            System.out.println("delete selected_item_id : " + id);
            Artist artist = artistDAO.find(id);

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous supprimer la ligne séléctionnée ?","Confirmer la suppression",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                artistDAO.delete(artist);
                table.revalidate();
                del_btn.setVisible(false);
                clear_btn.setVisible(false);
            }

        });
        }
}