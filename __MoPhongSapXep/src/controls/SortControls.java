
package controls;

import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import getobj.GetFeature;
import model.Column;
import model.IConstants;
import model.ISort;
import model.ISortDone;

public class SortControls implements ISort {

    private boolean       flagAccecptAnimation;
    private boolean       flagPause;
    private boolean       flagStep;
    private JList<String> lstCode;
    public int            delay;

    public SortControls() {

        flagAccecptAnimation = true;
        flagPause = false;
        flagStep = false;
        delay = 0;
        lstCode = GetFeature.getLstCode();
    }

    private void bubbleSort(List<Column> columns, int l, int r, boolean asc) throws InterruptedException {

        highLight(0);
        highLight(1);
        highLight(2);
        for(int i = l; i < r; i++) {
            highLight(2);
            Column c0 = columns.get(i);
            highLight(3);
            for(int j = r; j > i; j--) {
                highLight(3);
                Column c1 = columns.get(j - 1);
                Column c2 = columns.get(j);
                float v1 = c1.getValue();
                float v2 = c2.getValue();
                highLight(4);
                if(asc) {
                    if(v1 > v2) {
                        highLight(5);
                        swapWithAnimation(c1, c2);
                        Thread.sleep(delay);
                    }
                } else if(v1 < v2) {
                    highLight(5);
                    swapWithAnimation(c1, c2);
                    Thread.sleep(delay);
                }
                checkPause(true);
                IConstants.ColorDefault(c2);
                IConstants.ColorDefault(c1);
                Thread.sleep(delay);
            }

            checkPause(true);
            IConstants.ColorDone(c0);
            Thread.sleep(delay);
        }

        highLight(6);
        Thread.sleep(delay);
    }

    private void checkPause(boolean isStep) throws InterruptedException {

        if(flagStep && isStep) {
            synchronized(this) {
                wait();
            }
            return;
        }
        if(flagPause) {
            synchronized(this) {
                wait();
            }
        }
    }

    private void createHeap(List<Column> columns, boolean asc) throws InterruptedException {

        int n = columns.size();
        for(int i = (n / 2) - 1; i >= 0; i--) {
            heapify(columns, n, i, asc);
            Thread.sleep(delay);
        }

    }

    private void heapify(List<Column> columns, int n, int vt, boolean asc) throws InterruptedException {

        while(vt <= ((n / 2) - 1)) {
            int child1 = (2 * vt) + 1;
            int child2 = (2 * vt) + 2;
            int lc = child1;
            Column cLc = columns.get(lc);
            Column cVt = columns.get(vt);
            IConstants.ColorOther(cLc);
            IConstants.ColorDone(cVt);
            if(child2 < n) {
                Column cChild2 = columns.get(child2);
                IConstants.ColorProccess(cChild2);
                if(asc) {
                    if(cChild2.getValue() > cLc.getValue()) {
                        lc = child2;
                    }
                } else if(cChild2.getValue() < cLc.getValue()) {
                    lc = child2;
                }
            }
            cLc = columns.get(lc);
            IConstants.ColorOther(cLc);
            float vVt = cVt.getValue();
            float vLc = cLc.getValue();
            if(asc) {
                if(vVt < vLc) {
                    IConstants.ColorOther(cLc);
                    IConstants.ColorDone(cVt);
                    swapWithAnimation(cVt, cLc);
                }
            } else if(vVt > vLc) {
                IConstants.ColorOther(cLc);
                IConstants.ColorDone(cVt);
                swapWithAnimation(cVt, cLc);
            }
            vt = lc;
            IConstants.ColorOther(cLc);
            Thread.sleep(delay);
            checkPause(true);
        }
    }

    private void insertSort(List<Column> columns, int l, int r, boolean asc) throws InterruptedException {

        highLight(0);
        highLight(1);
        highLight(2);
        highLight(4);
        for(int i = l + 1; i <= r; i++) {
            highLight(4);
            highLight(5);
            highLight(6);
            Column c1 = columns.get(i);
            IConstants.ColorOther(c1);
            float v1 = c1.getValue();
            highLight(7);
            int j = i - 1;
            highLight(9);
            if(asc) {
                while(j >= l) {
                    if(columns.get(j).getValue() <= v1) {
                        break;
                    }
                    highLight(9);
                    highLight(10);
                    Column c3 = columns.get(j + 1);
                    Column c4 = columns.get(j);
                    highLight(11);
                    IConstants.ColorPicking(c3);
                    c3.setValue(c4.getValue());
                    c3.alginColumn();
                    IConstants.ColorDefault(c3);
                    IConstants.refresh(c3);
                    highLight(12);
                    j--;
                    highLight(13);
                    Thread.sleep(delay);
                    checkPause(true);
                }
            } else {
                for(; (j >= l) && (columns.get(j).getValue() < v1); checkPause(true)) {
                    highLight(9);
                    highLight(10);
                    Column c3 = columns.get(j + 1);
                    Column c4 = columns.get(j);
                    highLight(11);
                    IConstants.ColorPicking(c3);
                    c3.setValue(c4.getValue());
                    c3.alginColumn();
                    IConstants.ColorDefault(c3);
                    IConstants.refresh(c3);
                    highLight(12);
                    j--;
                    highLight(13);
                    Thread.sleep(delay);
                }

            }
            IConstants.ColorDefault(c1);
            Column c5 = columns.get(j + 1);
            highLight(15);
            IConstants.ColorOther(c5);
            c5.setValue(v1);
            c5.alginColumn();
            IConstants.ColorDefault(c5);
            IConstants.refresh(c1);
            IConstants.refresh(c5);
            highLight(16);
            Thread.sleep(delay);
            checkPause(true);
        }

        highLight(17);
    }

    private void interchangeSort(List<Column> columns, int l, int r, boolean asc) throws InterruptedException {

        highLight(0);
        highLight(1);
        highLight(2);
        for(int i = l; i < r; i++) {
            highLight(2);
            Column c1 = columns.get(i);
            highLight(3);
            for(int j = i + 1; j <= r; j++) {
                highLight(3);
                Column c2 = columns.get(j);
                float value1 = c1.getValue();
                float value2 = c2.getValue();
                highLight(4);
                if(asc) {
                    if(value1 > value2) {
                        swapWithAnimation(c2, c1);
                        highLight(5);
                        Thread.sleep(delay);
                    }
                } else if(value1 < value2) {
                    swapWithAnimation(c2, c1);
                    highLight(5);
                    Thread.sleep(delay);
                }
                Thread.sleep(delay);
                checkPause(true);
            }

            Thread.sleep(delay);
            checkPause(true);
        }

        highLight(6);
        Thread.sleep(delay);
    }

    private void merge(List<Column> columns, int l, int m, int r, boolean asc) throws InterruptedException {

        int len1 = (m - l) + 1;
        int len2 = r - m;
        float left[] = new float[len1];
        float right[] = new float[len2];
        int i;
        for(i = 0; i < len1; i++) {
            Column c1 = columns.get(l + i);
            IConstants.ColorPicking(c1);
            left[i] = c1.getValue();
            Thread.sleep(delay);
            IConstants.ColorDefault(c1);
            checkPause(false);
        }

        for(i = 0; i < len2; i++) {
            Column c2 = columns.get(m + 1 + i);
            IConstants.ColorPicking(c2);
            right[i] = c2.getValue();
            Thread.sleep(delay);
            IConstants.ColorDefault(c2);
            checkPause(false);
        }

        i = 0;
        int j = 0;
        int k = l;
        if(asc) {
            while((i < len1) && (j < len2)) {
                if(left[i] <= right[j]) {
                    Column c = columns.get(k);
                    IConstants.ColorOther(c);
                    c.setValue(left[i]);
                    c.alginColumn();
                    IConstants.refresh(c);
                    i++;
                    Thread.sleep(delay);
                    IConstants.ColorDefault(c);
                    checkPause(true);
                } else {
                    Column c = columns.get(k);
                    IConstants.ColorOther(c);
                    c.setValue(right[j]);
                    c.alginColumn();
                    IConstants.refresh(c);
                    j++;
                    Thread.sleep(delay);
                    IConstants.ColorDefault(c);
                    checkPause(true);
                }
                k++;
            }
        } else {
            while((i < len1) && (j < len2)) {
                if(left[i] >= right[j]) {
                    Column c = columns.get(k);
                    IConstants.ColorOther(c);
                    c.setValue(left[i]);
                    c.alginColumn();
                    IConstants.refresh(c);
                    i++;
                    Thread.sleep(delay);
                    IConstants.ColorDefault(c);
                    checkPause(true);
                } else {
                    Column c = columns.get(k);
                    IConstants.ColorOther(c);
                    c.setValue(right[j]);
                    c.alginColumn();
                    IConstants.refresh(c);
                    j++;
                    Thread.sleep(delay);
                    IConstants.ColorDefault(c);
                    checkPause(true);
                }
                k++;
            }
        }
        while(i < len1) {
            Column c = columns.get(k);
            IConstants.ColorOther(c);
            c.setValue(left[i]);
            c.alginColumn();
            IConstants.refresh(c);
            k++;
            i++;
            Thread.sleep(delay);
            IConstants.ColorDefault(c);
            checkPause(true);
        }
        while(j < len2) {
            Column c = columns.get(k);
            IConstants.ColorOther(c);
            c.setValue(right[j]);
            c.alginColumn();
            IConstants.refresh(c);
            k++;
            j++;
            Thread.sleep(delay);
            IConstants.ColorDefault(c);
            checkPause(true);
        }
    }

    private void mergeSort(List<Column> columns, int l, int r, boolean asc) throws InterruptedException {

        if(l < r) {
            int mid = (l + r) / 2;
            mergeSort(columns, l, mid, asc);
            checkPause(true);
            mergeSort(columns, mid + 1, r, asc);
            checkPause(true);
            merge(columns, l, mid, r, asc);
            checkPause(true);
            Thread.sleep(delay);
        }
    }

    private int min(int a, int b) {

        return a >= b ? b : a;
    }

    private void quickSort(List<Column> columns, int l, int r, boolean asc) throws InterruptedException {

        if(l < r) {
            int i = l;
            int j = r;
            int mid = (l + r) / 2;
            Column cL = columns.get(l);
            IConstants.ColorLevel(cL);
            Column cR = columns.get(r);
            IConstants.ColorLevel(cR);
            Column cMid = columns.get(mid);
            IConstants.ColorLevel(cMid);
            float pivot = cMid.getValue();
            while(i < j) {
                Column cI = columns.get(i);
                Column cJ = columns.get(j);
                if(asc) {
                    IConstants.ColorPicking(cI);
                    for(; cI.getValue() < pivot; checkPause(false)) {
                        IConstants.ColorDefault(cI);
                        i++;
                        cI = columns.get(i);
                        IConstants.ColorPicking(cI);
                        IConstants.refresh(cI);
                        Thread.sleep(delay);
                    }

                    IConstants.ColorDefault(cI);
                    IConstants.ColorPicking(cJ);
                    for(; cJ.getValue() > pivot; checkPause(false)) {
                        IConstants.ColorDefault(cJ);
                        j--;
                        cJ = columns.get(j);
                        IConstants.ColorPicking(cJ);
                        IConstants.refresh(cJ);
                        Thread.sleep(delay);
                    }

                    IConstants.ColorDefault(cJ);
                } else {
                    IConstants.ColorPicking(cI);
                    for(; cI.getValue() > pivot; checkPause(false)) {
                        IConstants.ColorDefault(cI);
                        i++;
                        cI = columns.get(i);
                        IConstants.ColorPicking(cI);
                        IConstants.refresh(cI);
                        Thread.sleep(delay);
                    }

                    IConstants.ColorDefault(cI);
                    IConstants.ColorPicking(cJ);
                    for(; cJ.getValue() < pivot; checkPause(false)) {
                        IConstants.ColorDefault(cJ);
                        j--;
                        cJ = columns.get(j);
                        IConstants.ColorPicking(cJ);
                        IConstants.refresh(cJ);
                        Thread.sleep(delay);
                    }

                    IConstants.ColorDefault(cJ);
                }
                if(i <= j) {
                    cI = columns.get(i);
                    cJ = columns.get(j);
                    swapWithAnimation(cI, cJ);
                    i++;
                    j--;
                    Thread.sleep(delay);
                }
            }
            IConstants.ColorDefault(cL);
            IConstants.ColorDefault(cR);
            IConstants.ColorDefault(cMid);
            Thread.sleep(delay);
            checkPause(true);
            if(l < j) {
                quickSort(columns, l, j, asc);
            }
            if(i < r) {
                quickSort(columns, i, r, asc);
            }
        }
    }

    private void selectionSort(List<Column> columns, int l, int r, boolean asc) throws InterruptedException {

        for(int i = l; i <= r; i++) {
            int min = i;
            Column c1 = columns.get(min);
            IConstants.ColorOther(c1);
            Column c3 = columns.get(i);
            IConstants.ColorLevel(c3);
            for(int j = i + 1; j <= r; j++) {
                Column c2 = columns.get(j);
                IConstants.ColorPicking(c2);
                IConstants.ColorPicking(c1);
                IConstants.refresh(c1);
                IConstants.refresh(c2);
                if(asc) {
                    if(c1.getValue() > c2.getValue()) {
                        min = j;
                        IConstants.ColorDefault(c1);
                        IConstants.ColorDefault(c2);
                        c1 = columns.get(min);
                        IConstants.ColorOther(c1);
                        Thread.sleep(delay);
                    }
                } else if(c1.getValue() < c2.getValue()) {
                    min = j;
                    IConstants.ColorDefault(c1);
                    IConstants.ColorDefault(c2);
                    c1 = columns.get(min);
                    IConstants.ColorOther(c1);
                    Thread.sleep(delay);
                }
                IConstants.ColorDefault(c1);
                IConstants.ColorDefault(c2);
                Thread.sleep(delay);
                checkPause(true);
            }

            swapWithAnimation(c1, c3);
            IConstants.ColorDefault(c1);
            IConstants.ColorDefault(c3);
            Thread.sleep(delay);
            checkPause(true);
        }

    }

    private void shakerSort(List<Column> columns, int l, int r, boolean asc) throws InterruptedException {

        int k = 0;
        while(l < r) {
            int tmpl = l;
            int tmpr = r;
            IConstants.ColorLevel(columns.get(tmpl));
            IConstants.ColorLevel(columns.get(tmpr));
            for(int i = l; i < r; i++) {
                Column c1 = columns.get(i);
                Column c2 = columns.get(i + 1);
                float v1 = c1.getValue();
                float v2 = c2.getValue();
                if(asc) {
                    if(v1 > v2) {
                        swapWithAnimation(c1, c2);
                        k = i;
                    }
                } else if(v1 < v2) {
                    swapWithAnimation(c1, c2);
                    k = i;
                }
            }

            r = k;
            for(int i = r; i > l; i--) {
                Column c1 = columns.get(i);
                Column c2 = columns.get(i - 1);
                float v1 = c1.getValue();
                float v2 = c2.getValue();
                if(asc) {
                    if(v1 < v2) {
                        swapWithAnimation(c1, c2);
                        k = i;
                    }
                } else if(v1 > v2) {
                    swapWithAnimation(c1, c2);
                    k = i;
                }
                Thread.sleep(delay);
                checkPause(true);
            }

            l = k;
            IConstants.ColorDefault(columns.get(tmpl));
            IConstants.ColorDefault(columns.get(tmpr));
            Thread.sleep(delay);
            checkPause(true);
        }
    }

    private void shellSort(List<Column> columns, int l, int r, boolean asc) throws InterruptedException {

        for(int gap = ((r - l) + 1) / 2; gap > 0; gap /= 2) {
            for(int i = l + gap; i <= r; i++) {
                Column cI = columns.get(i);
                IConstants.ColorPicking(cI);
                IConstants.refresh(cI);
                float tmp = cI.getValue();
                int j = i;
                Column cJGap = columns.get(j - gap);
                Column cJ = columns.get(j);
                IConstants.ColorPicking(cJ);
                if(asc) {
                    for(; j >= (gap + l); j -= gap) {
                        cJGap = columns.get(j - gap);
                        IConstants.ColorPicking(cJGap);
                        if(cJGap.getValue() <= tmp) {
                            IConstants.ColorDefault(cJGap);
                            break;
                        }
                        IConstants.ColorDefault(cJGap);
                        IConstants.ColorDefault(cJ);
                        cJ = columns.get(j);
                        IConstants.ColorPicking(cJ);
                        cJ.setValue(cJGap.getValue());
                        cJ.alginColumn();
                        IConstants.refresh(cJ);
                        Thread.sleep(delay);
                        checkPause(false);
                        IConstants.ColorDefault(cJ);
                        IConstants.ColorDefault(cJGap);
                    }

                } else {
                    for(; j >= (gap + l); j -= gap) {
                        cJGap = columns.get(j - gap);
                        IConstants.ColorPicking(cJGap);
                        if(cJGap.getValue() >= tmp) {
                            IConstants.ColorDefault(cJGap);
                            break;
                        }
                        IConstants.ColorDefault(cJGap);
                        IConstants.ColorDefault(cJ);
                        cJ = columns.get(j);
                        IConstants.ColorPicking(cJ);
                        cJ.setValue(cJGap.getValue());
                        cJ.alginColumn();
                        IConstants.refresh(cJ);
                        Thread.sleep(delay);
                        checkPause(false);
                        IConstants.ColorDefault(cJ);
                        IConstants.ColorDefault(cJGap);
                    }

                }
                IConstants.ColorDefault(cJ);
                cJ = columns.get(j);
                IConstants.ColorDefault(cJ);
                cJ.setValue(tmp);
                IConstants.ColorDefault(cI);
                cJ.alginColumn();
                Thread.sleep(delay);
                checkPause(true);
            }

            Thread.sleep(delay);
            checkPause(true);
        }

    }

    private void sortDone(List<Column> columns) throws InterruptedException {

        Column column;
        for(Iterator<Column> iterator = columns.iterator(); iterator.hasNext(); IConstants.ColorDone(column)) {
            column = iterator.next();
            column.alginColumn();
        }

        ISortDone ui = GetFeature.getISortDone();
        ui.sortDone();
    }

    private void swapWithAnimation(Column c1, Column c2) throws InterruptedException {

        if(!flagAccecptAnimation) {
            float tmp = c1.getValue();
            c1.setValue(c2.getValue());
            c2.setValue(tmp);
            c1.alginColumn();
            c2.alginColumn();
            IConstants.refresh(c1);
            IConstants.refresh(c2);
            Thread.sleep(delay);
            return;
        }
        JPanel pn1 = c1.getPnColumn();
        JPanel pn2 = c2.getPnColumn();
        java.awt.Point p1 = pn1.getLocation();
        java.awt.Point p2 = pn2.getLocation();
        int x1 = (int)p1.getX();
        int x2 = (int)p2.getX();
        int y = 0;
        IConstants.ColorPicking(c1);
        IConstants.ColorPicking(c2);
        int n = 5;
        if(x1 > x2) {
            for(int x2Tmp = x2; (x1 - n) > x2Tmp;) {
                x1 -= n;
                x2 += n;
                pn1.setLocation(x1, y);
                pn2.setLocation(x2, y);
                Thread.sleep(delay);
                checkPause(false);
            }

        } else if(x1 < x2) {
            for(int x2Tmp = x2; (x1 + n) < x2Tmp;) {
                x1 += n;
                x2 -= n;
                pn1.setLocation(x1, y);
                pn2.setLocation(x2, y);
                Thread.sleep(delay);
                checkPause(false);
            }

        }
        pn1.setLocation(p1);
        pn2.setLocation(p2);
        float tmp = c1.getValue();
        c1.setValue(c2.getValue());
        c2.setValue(tmp);
        JLabel lbl1 = c1.getLblValue();
        JLabel lbl2 = c2.getLblValue();
        java.awt.Dimension sizelbl1 = lbl1.getSize();
        java.awt.Dimension sizelbl2 = lbl2.getSize();
        lbl1.setSize(sizelbl2);
        lbl2.setSize(sizelbl1);
        lbl1.setPreferredSize(sizelbl2);
        lbl2.setPreferredSize(sizelbl1);
        IConstants.refresh(c1);
        IConstants.refresh(c2);
        IConstants.ColorDefault(c1);
        IConstants.ColorDefault(c2);
    }

    void swap(Object t1, Object t2) {

        Object tmp = t1;
        t1 = t2;
        t2 = tmp;
    }

    @Override
    public void bubbleSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        bubbleSort(columns, 0, length - 1, asc);
        sortDone(columns);
    }

    @Override
    public boolean continues() {

        flagPause = false;
        flagStep = false;

        try {
            synchronized(this) {
                notify();
            }
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getDelay() {

        return delay;
    }

    @Override
    public void heapSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        createHeap(columns, asc);
        Column c0 = columns.get(0);
        for(int i = length - 1; i >= 0; i--) {
            Column ci = columns.get(i);
            swapWithAnimation(c0, ci);
            IConstants.ColorDefault(ci);
            IConstants.ColorDefault(c0);
            heapify(columns, i, 0, asc);
            Thread.sleep(delay);
            checkPause(true);
        }

        sortDone(columns);
    }

    @Override
    public void highLight(int row) throws InterruptedException {

        if(!flagAccecptAnimation || (row < 0) || (row >= lstCode.getModel().getSize())) {
            return;
        } else {
            lstCode.setSelectedIndex(row);
            lstCode.ensureIndexIsVisible(row);
            IConstants.refresh(lstCode);
            Thread.sleep(delay);
            return;
        }
    }

    public void highLight(String s) {

        lstCode.setSelectedValue(s, true);
    }

    @Override
    public void insertSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        int l = 0;
        int r = length - 1;
        insertSort(columns, l, r, asc);
        sortDone(columns);
    }

    @Override
    public void interchangeSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        interchangeSort(columns, 0, length - 1, asc);
        sortDone(columns);
    }

    @Override
    public void mergeSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        int l = 0;
        int r = length - 1;
        mergeSort(columns, l, r, asc);
        sortDone(columns);
    }

    @Override
    public boolean pause() {

        flagPause = true;
        return true;
    }

    @Override
    public void quickSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        int l = 0;
        int r = length - 1;
        quickSort(columns, l, r, asc);
        sortDone(columns);
    }

    @Override
    public void selectionSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        selectionSort(columns, 0, length - 1, asc);
        sortDone(columns);
    }

    @Override
    public void setDelay(int delay) {

        this.delay = delay;
    }

    @Override
    public void shakerSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        int l = 0;
        int r = length - 1;
        shakerSort(columns, l, r, asc);
        sortDone(columns);
    }

    @Override
    public void shellSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        shellSort(columns, 0, length - 1, asc);
        sortDone(columns);
    }

    @Override
    public void step() throws InterruptedException {

        int tmp = delay;
        setDelay(0);
        boolean animation = flagAccecptAnimation;
        flagAccecptAnimation = false;
        flagPause = false;
        synchronized(this) {
            notify();
        }
        flagStep = true;
        Thread.sleep(delay);
        setDelay(tmp);
        flagAccecptAnimation = animation;
    }

    @Override
    public void timSort(List<Column> columns, boolean asc) throws InterruptedException {

        int length = columns.size();
        if(length > 200) {
            flagAccecptAnimation = false;
        }
        int run = 64;
        for(int i = 0; i < length; i += run) {
            shellSort(columns, i, min(i + run, length - 1), asc);
            checkPause(true);
        }

        for(int size = run; size < length; size *= 2) {
            for(int left = 0; left < length; left += 2 * size) {
                int mid = min((left + size) - 1, length - 1);
                int right = min((left + (2 * size)) - 1, length - 1);
                merge(columns, left, mid, right, asc);
                checkPause(true);
            }

        }

        sortDone(columns);
    }
}
