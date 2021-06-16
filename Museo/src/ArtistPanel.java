import bean.ArtistWork;
import bean.Lend;
import bean.Property;
import bean.Artist;
import dao.DAO;
import dao.DAOFactory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ArtistPanel extends JPanel {
    public ArtistPanel() {
        String[] columns = new String[] {
               "ID", "Prénom", "Nom", "Dates"
        };

        DAO<Artist> artistDAO = new DAOFactory().getArtistDAO();
        ArrayList<Artist> artists = artistDAO.findAll(new HashMap<>());
        int artistsLength = artists.size();

        Object[][] data = new Object[artistsLength][];
        for (int i = 0; i < artistsLength; i++) {
            data[i] = new Object[]{
                    artists.get(i).getId(),
                    artists.get(i).getFirstname(),
                    artists.get(i).getLastname(),
                    artists.get(i).getPeriod()
            };
        }

        final Class[] columnClass = new Class[] {
               String.class, String.class, String.class, String.class,
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

        JButton add_edit_btn = new JButton("Ajouter");
        JButton del_btn = new JButton("Supprimer");
        JButton clear_btn = new JButton("Effacer la sélection");
            del_btn.setVisible(false);
            clear_btn.setVisible(false);

        JPanel buttonPanel = new JPanel();
            buttonPanel.add(add_edit_btn);
            buttonPanel.add(del_btn);
            buttonPanel.add(clear_btn);

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(new JScrollPane(table));
            this.add(new ArtistForm(table, del_btn, clear_btn));
            this.add(buttonPanel);
        }
}
