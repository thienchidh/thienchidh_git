
package controls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;

import model.Column;
import model.IFile;

public class IOFile implements IFile {

    private JFileChooser  chooser;
    private DecimalFormat decimalFormat;

    public IOFile() {}

    @Override
    public synchronized List<Column> openFile(int soPhanTu) {

        List<Column> arr;
        arr = new Vector<>();
        if(chooser == null) {
            chooser = new JFileChooser();
        }
        if(soPhanTu > 25) {
            Column.setShowNumber(false);
        } else {
            Column.setShowNumber(true);
        }
        if(chooser.showOpenDialog(null) != 0) { return null; }
        File f;
        f = chooser.getSelectedFile();
        System.out.println(f.getAbsolutePath());
        if(!f.getAbsolutePath().endsWith(".txt")) { return null; }
        try {
            Scanner sc = new Scanner(f);
            for(int count = 0; sc.hasNextFloat() && (count < soPhanTu); count++) {
                Column c = new Column();
                c.setValue(sc.nextFloat());
                arr.add(c);
            }

            sc.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    @Override
    public synchronized boolean saveFile(List<Column> arr) {

        if(decimalFormat == null) {
            decimalFormat = new DecimalFormat("#0.00");
        }
        if(chooser == null) {
            chooser = new JFileChooser();
        }
        if(chooser.showSaveDialog(null) != 0) { return false; }
        try {
            File file = chooser.getSelectedFile();
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bfw = new BufferedWriter(osw);
            Iterator<Column> iterator = arr.iterator();
            StringBuilder stringBuilder = new StringBuilder();
            for(; iterator.hasNext(); stringBuilder.setLength(0)) {
                bfw.write(stringBuilder.append(decimalFormat.format(iterator.next().getValue())).append(" ").toString());
            }

            bfw.close();
            osw.close();
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
