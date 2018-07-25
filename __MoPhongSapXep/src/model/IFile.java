
package model;

import java.util.List;

public interface IFile {

    public abstract List<Column> openFile(int i);

    public abstract boolean saveFile(List<Column> columns);
}
