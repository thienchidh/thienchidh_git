
package model;

import java.util.List;

public interface ISort extends IConstants {

    public abstract void bubbleSort(List<Column> columns, boolean flag) throws InterruptedException;

    public abstract boolean continues();

    public abstract int getDelay();

    public abstract void heapSort(List<Column> columns, boolean flag) throws InterruptedException;

    public abstract void highLight(int i) throws InterruptedException;

    public abstract void insertSort(List<Column> columns, boolean flag) throws InterruptedException;

    public abstract void interchangeSort(List<Column> columns, boolean flag) throws InterruptedException;

    public abstract void mergeSort(List<Column> columns, boolean flag) throws InterruptedException;

    public abstract boolean pause();

    public abstract void quickSort(List<Column> columns, boolean flag) throws InterruptedException;

    public abstract void selectionSort(List<Column> columns, boolean flag) throws InterruptedException;

    public abstract void setDelay(int i);

    public abstract void shakerSort(List<Column> columns, boolean flag) throws InterruptedException;

    public abstract void shellSort(List<Column> columns, boolean flag) throws InterruptedException;

    public abstract void step() throws InterruptedException;

    public abstract void timSort(List<Column> columns, boolean flag) throws InterruptedException;
}
