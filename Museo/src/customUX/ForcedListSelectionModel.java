package customUX;

import javax.swing.*;

public class ForcedListSelectionModel extends DefaultListSelectionModel {

    public ForcedListSelectionModel () {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void removeSelectionInterval(int index0, int index1) {}
}