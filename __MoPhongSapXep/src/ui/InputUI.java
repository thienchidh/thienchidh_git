
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import getobj.GetUI;
import model.Column;
import model.IList;
import model.KeyActions;
import thread_runnable.MyAutoInput;

public class InputUI extends JDialog implements IList {

    private JToggleButton btnAutoInput;
    private JButton       btnOk;
    private JButton       btnXoa;
    private int           maxRand;
    private JSlider       slSpeed;
    private JSpinner      spMax;
    private MyAutoInput   threadAutoInput;
    private JTextArea     txtInput;

    public InputUI(String title) {

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                if(btnAutoInput.isSelected()) {
                    btnAutoInput.doClick();
                }
            }

        });
        setTitle(title);
        setModal(true);
        setSize(430, 270);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        addControls();
        addEvents();
    }

    private void addControls() {

        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        JPanel pnSouth = new JPanel();
        getContentPane().add(pnSouth, "South");
        pnSouth.setLayout(new BoxLayout(pnSouth, 0));
        JPanel pnChange = new JPanel();
        pnSouth.add(pnChange);
        pnChange.setLayout(new BoxLayout(pnChange, 0));
        JPanel pnChangeSpeed = new JPanel();
        pnChangeSpeed.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 255)), "Speed", 1, 2, null, null));
        pnChange.add(pnChangeSpeed);
        pnChangeSpeed.setLayout(new BorderLayout(0, 0));
        slSpeed = new JSlider();
        slSpeed.setMinimum(5);
        pnChangeSpeed.add(slSpeed);
        slSpeed.setValue(100);
        slSpeed.setMaximum(1000);
        JPanel pnChangeMaxValue = new JPanel();
        pnChangeMaxValue.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Max Value", 4, 2, null, new Color(51, 51, 51)));
        pnChange.add(pnChangeMaxValue);
        pnChangeMaxValue.setLayout(new BorderLayout(0, 0));
        spMax = new JSpinner();
        spMax.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        pnChangeMaxValue.add(spMax);
        JPanel pnAuto = new JPanel();
        pnSouth.add(pnAuto);
        pnAuto.setLayout(new BorderLayout(0, 0));
        btnAutoInput = new JToggleButton("Auto");
        pnAuto.add(btnAutoInput);
        JPanel pnDelete = new JPanel();
        pnSouth.add(pnDelete);
        pnDelete.setLayout(new BorderLayout(0, 0));
        btnXoa = new JButton("X\363a");
        pnDelete.add(btnXoa);
        JPanel pnOk = new JPanel();
        pnSouth.add(pnOk);
        pnOk.setLayout(new BorderLayout(0, 0));
        btnOk = new JButton("Ok");
        pnOk.add(btnOk);
        JPanel pnCenter = new JPanel();
        pnCenter.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "Nh\u1EADp d\u1EEF li\u1EC7u", 1, 2, null, new Color(0, 0, 0)));
        getContentPane().add(pnCenter, "Center");
        pnCenter.setLayout(new BorderLayout(0, 0));
        JScrollPane sc = new JScrollPane();
        pnCenter.add(sc, "Center");
        txtInput = new JTextArea();
        txtInput.setWrapStyleWord(true);
        txtInput.setLineWrap(true);
        sc.setViewportView(txtInput);
    }

    private void addEvents() {

        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                xuLyOk();
            }

        });
        btnXoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                txtInput.setText("");
                txtInput.requestFocus();
            }

        });
        btnAutoInput.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    xuLyTuDongNhap();
                } catch(InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

        });
        slSpeed.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                try {
                    xuLyThayDoiTocDo();
                } catch(Exception e1) {
                    e1.printStackTrace();
                }
            }

        });
        spMax.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                xuLyThayDoiMucRand();
            }

        });
        txtInput.addKeyListener(new KeyActions(txtInput));
    }

    protected void xuLyOk() {

        List<Column> columns = new Vector<>();
        String text = txtInput.getText();
        String textNumber[] = text.split(" ");
        for(int i = 0; i < textNumber.length; i++) {
            try {
                String sNumber = textNumber[i].trim();
                float number = Float.valueOf(sNumber.trim()).floatValue();
                Column column = new Column();
                column.setValue(number);
                columns.add(column);
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }
        }

        if(columns.size() > 25) {
            Column.setShowNumber(false);
            Column column;
            for(Iterator<Column> iterator = columns.iterator(); iterator.hasNext(); column.updateLbString()) {
                column = iterator.next();
            }

        } else {
            Column.setShowNumber(true);
        }
        IList thongDiep = GetUI.getMainUI();
        thongDiep.nhanThongDiep(columns);
        if(btnAutoInput.isSelected()) {
            btnAutoInput.doClick();
        }
        setVisible(false);
    }

    protected void xuLyThayDoiMucRand() {

        maxRand = Integer.parseInt((new StringBuilder()).append(spMax.getValue()).toString());
        if(threadAutoInput != null) {
            threadAutoInput.setMaxRand(maxRand);
        }
    }

    protected void xuLyThayDoiTocDo() throws Exception {

        int curValue = slSpeed.getValue();
        if((threadAutoInput != null) && threadAutoInput.isAlive()) {
            threadAutoInput.setDelay(curValue);
        }
    }

    protected synchronized void xuLyTuDongNhap() throws InterruptedException {

        int value = ((Integer)spMax.getValue()).intValue();
        spMax.setValue(Integer.valueOf(++value));
        spMax.setValue(Integer.valueOf(--value));
        if(btnAutoInput.isSelected()) {
            if(threadAutoInput == null) {
                threadAutoInput = new MyAutoInput(txtInput);
                threadAutoInput.setMaxRand(maxRand);
                threadAutoInput.start();
            }
            threadAutoInput.continues();
        } else {
            threadAutoInput.pause();
        }
    }

    @Override
    public void nhanThongDiep(List<Column> columns) {

        if(columns == null) { return; }
        txtInput.setText("");
        Column column;
        for(Iterator<Column> iterator = columns.iterator(); iterator.hasNext(); txtInput.append((new StringBuilder(String.valueOf(column.getValue()))).append(" ").toString())) {
            column = iterator.next();
        }

    }

    public void showWindow() {

        setVisible(true);
    }

}
