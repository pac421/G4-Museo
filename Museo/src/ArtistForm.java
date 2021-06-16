import bean.Artist;
import bean.Category;
import bean.Collection;
import bean.Work;
import dao.ArtistDAO;
import dao.DAO;
import dao.DAOFactory;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class ArtistForm extends JPanel {

    public ArtistForm(JTable table, JButton add_btn, JButton del_btn, JButton edit_btn, JButton clear_btn) {

        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        Font labelFont = new Font("Montserrat", Font.PLAIN, 16);
        Font fieldFont = new Font("Montserrat", Font.PLAIN, 14);

        JLabel lastNameLabel = new JLabel("Nom");
        lastNameLabel.setFont(labelFont);
        JTextField lastNameField = new JTextField(20);
        lastNameField.setFont(fieldFont);

        JLabel firstNameLabel = new JLabel("Prénom");
        firstNameLabel.setFont(labelFont);
        JTextField firstNameField = new JTextField(20);
        firstNameField.setFont(fieldFont);

        JLabel periodLabel = new JLabel("Période");
        periodLabel.setFont(labelFont);
        JTextField periodField = new JTextField(20);
        periodField.setFont(fieldFont);

        this.setBorder(BorderFactory.createTitledBorder("Ajouter une oeuvre"));


        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(lastNameLabel)
                .addComponent(firstNameLabel)
                .addComponent(periodLabel)
        );
        horizontalGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(lastNameField)
                .addComponent(firstNameField)
                .addComponent(periodField)
        );
        groupLayout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = groupLayout.createSequentialGroup();
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lastNameLabel).addComponent(lastNameField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(firstNameLabel).addComponent(firstNameField));
        verticalGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(periodLabel).addComponent(periodField));
        groupLayout.setVerticalGroup(verticalGroup);

        DAO<Artist> artistDAO = new DAOFactory().getArtistDAO();

       table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1){
                String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                System.out.println("new selected_item_id : "+id);

                del_btn.setVisible(true);
                clear_btn.setVisible(true);
                edit_btn.setVisible(true);
                add_btn.setVisible(false);

                Artist artist = artistDAO.find(id);
                lastNameField.setText(artist.getLastname());
                firstNameField.setText(artist.getFirstname());
                periodField.setText(artist.getPeriod());
            }
        });

        clear_btn.addActionListener(e -> {
            System.out.println("clear selection");

            del_btn.setVisible(false);
            clear_btn.setVisible(false);
            edit_btn.setVisible(false);
            add_btn.setVisible(true);

            lastNameField.setText("");
            firstNameField.setText("");
            periodField.setText("");
        });

        del_btn.addActionListener(e -> {
            String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            System.out.println("delete selected_item_id : " + id);
            Artist artist = artistDAO.find(id);

            Object[] options = {"Oui", "Non"};
            int dialogResult = JOptionPane.showOptionDialog(null,"Voulez-vous supprimer la ligne séléctionnée ?","Confirmer la suppression", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(dialogResult == JOptionPane.YES_OPTION){
                artistDAO.delete(artist);

                del_btn.setVisible(false);
                clear_btn.setVisible(false);
                edit_btn.setVisible(false);
                add_btn.setVisible(true);

                lastNameField.setText("");
                firstNameField.setText("");
                periodField.setText("");

            }
        });

       edit_btn.addActionListener(e -> {
           String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
           System.out.println("delete selected_item_id : " + id);
           Artist artist = artistDAO.find(id);
           artist.setFirstname(firstNameField.getText());
           artist.setLastname(lastNameField.getText());
           artist.setPeriod(periodField.getText());
           

            Object[] options = {"Oui", "Non"};
            int dialogResult = JOptionPane.showOptionDialog(null,"Voulez-vous modifier la ligne séléctionnée ?","Confirmer la modification", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
            if(dialogResult == JOptionPane.YES_OPTION){
                artistDAO.update(artist);

                del_btn.setVisible(false);
                clear_btn.setVisible(false);
                edit_btn.setVisible(false);
                add_btn.setVisible(true);

                lastNameField.setText("");
                firstNameField.setText("");
                periodField.setText("");
            }
        });
        }
}