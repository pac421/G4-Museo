import bean.Artist;
import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ArtistPanel extends JPanel {
    public ArtistPanel() {
        String[] columns = new String[] {
                "Prénom", "Nom", "Dates"
        };

        DAO<Artist> artistDAO = new DAOFactory().getArtistDAO();
        ArrayList<Artist> artists = artistDAO.findAll();
        int artistsLength = artists.size();

        Object[][] data = new Object[artistsLength][];
        for (int i = 0; i < artistsLength; i++) {
            data[i] = new Object[]{
                    artists.get(i).getFirstname(),
                    artists.get(i).getLastname(),
                    artists.get(i).getPeriod()
            };
        }

        final Class[] columnClass = new Class[] {
                String.class, String.class, String.class,
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
        this.add(new JScrollPane(table));
        this.setLayout(new GridLayout(0, 1));
    }
}
