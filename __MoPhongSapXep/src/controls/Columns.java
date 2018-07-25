
package controls;

import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import model.Column;
import model.IConstants;

public class Columns {

    public Columns() {}

    private static float timMax(List<Column> columns) {

        float max = columns.get(0).getValue();
        for(int i = 1; i < columns.size(); i++) {
            float value = columns.get(i).getValue();
            if(value > max) {
                max = value;
            }
        }

        return max;
    }

    public static void optimization(List<Column> columns, JPanel pnColumns) throws InterruptedException {

        if((columns == null) || columns.isEmpty()) { return; }
        int h = (int)pnColumns.getSize().getHeight() - 10;
        float max = timMax(columns);
        Column.setMax(max);
        Column.setHeightMax(h);
        Column i;
        for(Iterator<Column> iterator = columns.iterator(); iterator.hasNext(); IConstants.ColorDefault(i)) {
            i = iterator.next();
            i.alginColumn();
        }

    }
}
