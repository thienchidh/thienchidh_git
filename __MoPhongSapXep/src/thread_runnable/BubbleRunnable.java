
package thread_runnable;

import java.util.List;

import getobj.GetFeature;
import model.Column;
import model.ISort;

public class BubbleRunnable implements MyRunnable {

    private boolean      asc;
    private List<Column> columns;
    private ISort        sort;

    public BubbleRunnable() {

        sort = GetFeature.getSort();
    }

    public BubbleRunnable(List<Column> columns) {

        this.columns = columns;
        sort = GetFeature.getSort();
    }

    public BubbleRunnable(List<Column> columns, boolean asc) {

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
            sort.bubbleSort(columns, asc);
        } catch(InterruptedException e) {
            System.out.println((new StringBuilder("----Stopped!----")).append(this).append("\t").append(e.getMessage()).toString());
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

        return "Bubble Sort";
    }
}
