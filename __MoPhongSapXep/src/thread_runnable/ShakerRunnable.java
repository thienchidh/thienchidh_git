
package thread_runnable;

import java.util.List;

import getobj.GetFeature;
import model.Column;
import model.ISort;

public class ShakerRunnable implements MyRunnable {

    private boolean      asc;
    private List<Column> columns;
    private ISort        sort;

    public ShakerRunnable() {

        sort = GetFeature.getSort();
    }

    public ShakerRunnable(List<Column> columns) {

        this.columns = columns;
        sort = GetFeature.getSort();
    }

    public ShakerRunnable(List<Column> columns, boolean asc) {

        this.columns = columns;
        this.asc = asc;
        sort = GetFeature.getSort();
    }

    @Override
    public boolean isAsc() {

        return asc;
    }

    @Override
    public void run() {

        try {
            sort.shakerSort(columns, asc);
        } catch(InterruptedException e) {
            System.err.println((new StringBuilder("----Stopped!----")).append(this).append("\t").append(e.getMessage()).toString());
        }
    }

    @Override
    public void setAsc(boolean asc) {

        this.asc = asc;
    }

    @Override
    public void setColumns(List<Column> columns) {

        this.columns = columns;
    }

    public void setSort(ISort sort) {

        this.sort = sort;
    }

    @Override
    public String toString() {

        return "Shaker Sort";
    }
}
