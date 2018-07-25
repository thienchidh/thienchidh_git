
package controls;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import model.Column;
import model.IRandom;

public class RandomColumn implements IRandom {

    public RandomColumn() {}

    private void randIndex(List<Column> columns) {

        Random r = new Random();
        int lengh = columns.size();
        for(int i = 0; i < lengh; i++) {
            swap(columns.get(r.nextInt(lengh)), columns.get(r.nextInt(lengh)));
            swap(columns.get(r.nextInt(lengh)), columns.get(r.nextInt(lengh)));
        }

    }

    private void swap(Column c1, Column c2) {

        float tmp = c1.getValue();
        c1.setValue(c2.getValue());
        c2.setValue(tmp);
    }

    @Override
    public List<Column> random(int soPhanTu) {

        if(soPhanTu > 25) {
            Column.setShowNumber(false);
        } else {
            Column.setShowNumber(true);
        }
        List<Column> columns = new Vector<>();
        for(int i = 0; i < soPhanTu; i++) {
            Column column = new Column();
            column.setValue(i + 1);
            columns.add(column);
        }

        randIndex(columns);
        return columns;
    }
}
