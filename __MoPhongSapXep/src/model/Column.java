
package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Column implements Cloneable {

    private static int     heightMax;
    private static boolean isShowNumber = true;
    private static float   max          = 0;
    private Dimension      d;
    private JLabel         lblValue;
    private JPanel         pnColumn;
    private StringBuffer   sb;
    private float          value;

    public Column() {

        sb = new StringBuffer();
        addControls();
    }

    public static int getHeightMax() {

        return heightMax;
    }

    public static float getMax() {

        return max;
    }

    public static boolean isShowNumber() {

        return isShowNumber;
    }

    public static void setHeightMax(int heightMax) {

        Column.heightMax = heightMax;
    }

    public static void setMax(float max) {

        Column.max = max;
    }

    public static void setShowNumber(boolean isShowNumber) {

        Column.isShowNumber = isShowNumber;
    }

    private void addControls() {

        pnColumn = new JPanel();
        pnColumn.setOpaque(false);
        GridBagLayout gbl_pnColumn = new GridBagLayout();
        gbl_pnColumn.columnWidths = new int[2];
        gbl_pnColumn.rowHeights = new int[3];
        gbl_pnColumn.columnWeights = (new double[]{ 1.0D, 4.9406564584124654E-324D });
        gbl_pnColumn.rowWeights = (new double[]{ 1.0D, 0.0D, 4.9406564584124654E-324D });
        pnColumn.setLayout(gbl_pnColumn);
        JPanel pnNone = new JPanel();
        pnNone.setOpaque(false);
        pnNone.setPreferredSize(new Dimension(0, 0));
        pnNone.setMinimumSize(new Dimension(0, 0));
        GridBagConstraints gbc_pnNone = new GridBagConstraints();
        gbc_pnNone.fill = 2;
        gbc_pnNone.insets = new Insets(0, 0, 5, 0);
        gbc_pnNone.gridx = 0;
        gbc_pnNone.gridy = 0;
        pnColumn.add(pnNone, gbc_pnNone);
        lblValue = new JLabel();
        lblValue.setMaximumSize(new Dimension(32767, 32767));
        lblValue.setMinimumSize(new Dimension(0, 0));
        lblValue.setOpaque(true);
        GridBagConstraints gbc_lblValue = new GridBagConstraints();
        gbc_lblValue.fill = 1;
        gbc_lblValue.gridx = 0;
        gbc_lblValue.gridy = 1;
        pnColumn.add(lblValue, gbc_lblValue);
        lblValue.setForeground(Color.DARK_GRAY);
        lblValue.setHorizontalTextPosition(0);
        lblValue.setHorizontalAlignment(0);
        lblValue.setBackground(IConstants.defAult);
        lblValue.setBorder(IConstants.borderDefault);
        d = new Dimension();
    }

    public void alginColumn() {

        int w = (int)lblValue.getSize().getWidth();
        int h = (int)((value / max) * getHeightMax());
        d.setSize(w, h);
        lblValue.setSize(d);
        lblValue.setPreferredSize(d);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

    public JLabel getLblValue() {

        return lblValue;
    }

    public JPanel getPnColumn() {

        return pnColumn;
    }

    public float getValue() {

        return value;
    }

    public void setPnColumn(JPanel pnColumn) {

        this.pnColumn = pnColumn;
    }

    public void setValue(float value) {

        if(isShowNumber()) {
            sb.setLength(0);
            sb.append(value);
            lblValue.setText(sb.toString());
        } else {
            lblValue.setText("");
        }
        this.value = value;
        if(this.value > max) {
            max = this.value;
        }
    }

    public void setValue(String value) {

        if(isShowNumber()) {
            lblValue.setText(value);
        } else {
            lblValue.setText("");
        }
        this.value = Float.parseFloat(value);
        if(this.value > max) {
            max = this.value;
        }
    }

    @Override
    public String toString() {

        sb.setLength(0);
        sb.append(value);
        return sb.toString();
    }

    public void updateLbString() {

        if(!isShowNumber()) {
            lblValue.setText("");
        } else {
            sb.setLength(0);
            sb.append(value);
            lblValue.setText(sb.toString());
        }
    }

}
