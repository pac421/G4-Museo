import bean.User;

import dao.DAO;
import dao.DAOFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserPanel extends JPanel {

    public UserPanel() {
        String[] columns = new String[] {
                //"Prénom", "Nom", "Email", "Role"
                "Prénom", "Nom", "Email", "Role"
        };

        DAO<User> userDAO = new DAOFactory().getUserDAO();
        ArrayList<User> user = userDAO.findAll(new HashMap<>());
        int user_length = user.size();

        Object[][] data = new Object[user_length][];
        for (int i = 0; i < user_length; i++) {
            data[i] = new Object[]{
                    user.get(i).getFirstname(),
                    user.get(i).getLastname(),
                    user.get(i).getEmail(),
                    user.get(i).getRole(),
            };
        }

        final Class[] columnClass = new Class[] {
                String.class, String.class, String.class, String.class
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

