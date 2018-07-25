
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class InforUI extends JDialog {

    private JButton btnOk;

    public InforUI(String title) {

        setTitle(title);
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
        FlowLayout fl_pnSouth = (FlowLayout)pnSouth.getLayout();
        fl_pnSouth.setAlignment(2);
        getContentPane().add(pnSouth, "South");
        btnOk = new JButton("Ok");
        pnSouth.add(btnOk);
        JPanel pnCenter = new JPanel();
        pnCenter.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "Th\364ng Tin Chi Ti\u1EBFt", 1, 2, null, new Color(0, 0, 0)));
        getContentPane().add(pnCenter, "Center");
        pnCenter.setLayout(new BorderLayout(0, 0));
        JScrollPane sc = new JScrollPane();
        pnCenter.add(sc, "Center");
        JTextArea txtInfor = new JTextArea();
        txtInfor.setText("M\364 Ph\u1ECFng S\u1EAFp X\u1EBFp - QuickSort, BubbleSort, MergeSort\r\n----\r\n" + "Author: Tr\u1EA7n Thi\u1EC7n Ch\355\r\n----");
        txtInfor.setWrapStyleWord(true);
        txtInfor.setLineWrap(true);
        txtInfor.setEditable(false);
        sc.setViewportView(txtInfor);
    }

    private void addEvents() {

        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }

        });
    }

    public void showWindow() {

        setVisible(true);
    }
}
