
package thread_runnable;

import java.util.List;

import model.Column;

public interface MyRunnable extends Runnable {

    public abstract boolean isAsc();

    public abstract void setAsc(boolean flag);

    public abstract void setColumns(List<Column> columns);
}
