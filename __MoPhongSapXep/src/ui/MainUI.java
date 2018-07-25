
package ui;

import static model.IConstants.continues;
import static model.IConstants.normal;
import static model.IConstants.pause;
import static model.IConstants.starting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controls.Columns;
import getobj.GetFeature;
import getobj.GetListModelCode;
import getobj.GetUI;
import model.Column;
import model.IConstants;
import model.IFile;
import model.IList;
import model.IRandom;
import model.ISort;
import model.ISortDone;
import model.IText;
import thread_runnable.MyRunnable;
import thread_runnable.MyThread;

public class MainUI extends JFrame implements IList, ISortDone {

    private JButton                          btnBatDau;
    private JButton                          btnDung;
    private JButton                          btnNhapFile;
    private JButton                          btnNhapTay;
    private JButton                          btnRandom;
    private JButton                          btnStep;
    private JButton                          btnTamDung;
    private JButton                          btnTiepTuc;
    private JButton                          btnXoa;
    private JButton                          btnXuatFile;
    private JComboBox<String>                cboSapXep;
    private JComboBox<MyRunnable>            cboThuatToan;
    private List<Column>                     columns;
    private DefaultComboBoxModel<MyRunnable> dcbModel;
    private DefaultComboBoxModel<String>     dcbSapXep;
    private JList<String>                    lstCode;
    private JMenuItem                        mnuFileExit;
    private JMenuItem                        mnuHelpAbout;
    private JMenuItem                        mnuHelpHelp;
    private JPanel                           pnColumns;
    private JSlider                          slSpeed;
    private JSlider                          slZoom;
    private int                              soPhanTu;
    private JSpinner                         spSoPhanTu;
    private MyThread                         threadStart;

    public MainUI(String title) {

        super(title);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setExtendedState(6);
        addControls();
        addEvents();
        addMyRunnable(dcbModel);
        setShowHideButton(normal);
    }

    private void addControls() {

        Dimension sizebtn = IConstants.sizeBtn;
        Dimension sizeLbl = IConstants.sizeLbl;
        Dimension sizeCbo = IConstants.sizeCbo;
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu mnuFile = new JMenu("File");
        menuBar.add(mnuFile);
        JSeparator separator = new JSeparator();
        mnuFile.add(separator);
        mnuFileExit = new JMenuItem("Exit");
        mnuFileExit.setIcon(new ImageIcon("imgs/exit.png"));
        mnuFile.add(mnuFileExit);
        JMenu mnuHelp = new JMenu("Help");
        menuBar.add(mnuHelp);
        mnuHelpHelp = new JMenuItem("Help");
        mnuHelpHelp.setIcon(new ImageIcon("imgs/help.png"));
        mnuHelp.add(mnuHelpHelp);
        mnuHelpAbout = new JMenuItem("About");
        mnuHelpAbout.setIcon(new ImageIcon("imgs/about.png"));
        mnuHelp.add(mnuHelpAbout);
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        JPanel pnBot = new JPanel();
        con.add(pnBot, "South");
        pnBot.setLayout(new BoxLayout(pnBot, 0));
        JScrollPane sc = new JScrollPane();
        pnBot.add(sc);
        JPanel pnNhap = new JPanel();
        sc.setViewportView(pnNhap);
        pnNhap.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)), "Nh\u1EADp", 1, 2, null, null));
        pnNhap.setLayout(new BoxLayout(pnNhap, 1));
        JPanel pnSoPhanTu = new JPanel();
        pnNhap.add(pnSoPhanTu);
        pnSoPhanTu.setLayout(new FlowLayout(1, 5, 5));
        JLabel lblSPhnT = new JLabel("S\u1ED1 Ph\u1EA7n T\u1EED:");
        lblSPhnT.setPreferredSize(sizeLbl);
        pnSoPhanTu.add(lblSPhnT);
        spSoPhanTu = new JSpinner();
        spSoPhanTu.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
        spSoPhanTu.setPreferredSize(sizeCbo);
        pnSoPhanTu.add(spSoPhanTu);
        JPanel pnTay = new JPanel();
        pnNhap.add(pnTay);
        pnTay.setLayout(new FlowLayout(1, 5, 5));
        btnRandom = new JButton("Random");
        btnRandom.setPreferredSize(sizebtn);
        pnTay.add(btnRandom);
        btnNhapTay = new JButton("Nh\u1EADp Tay");
        pnTay.add(btnNhapTay);
        btnNhapTay.setPreferredSize(sizebtn);
        JPanel pnFile = new JPanel();
        pnNhap.add(pnFile);
        pnFile.setLayout(new FlowLayout(1, 5, 5));
        btnXuatFile = new JButton("Xu\u1EA5t File");
        btnXuatFile.setPreferredSize(sizebtn);
        pnFile.add(btnXuatFile);
        btnNhapFile = new JButton("Nh\u1EADp File");
        pnFile.add(btnNhapFile);
        btnNhapFile.setPreferredSize(sizebtn);
        JScrollPane sc1 = new JScrollPane();
        pnBot.add(sc1);
        JPanel pnCode = new JPanel();
        sc1.setViewportView(pnCode);
        pnCode.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)), "Code", 1, 2, null, null));
        pnCode.setLayout(new BoxLayout(pnCode, 0));
        JPanel pnCodex = new JPanel();
        pnCode.add(pnCodex);
        pnCodex.setLayout(new BoxLayout(pnCodex, 1));
        JScrollPane scList = new JScrollPane();
        pnCodex.add(scList);
        lstCode = GetFeature.getLstCode();
        lstCode.setModel(GetListModelCode.getDlmBubble());
        lstCode.setFont(new Font("Consolas", 1, 12));
        lstCode.setSelectionMode(0);
        scList.setViewportView(lstCode);
        JPanel pnZoom = new JPanel();
        pnCode.add(pnZoom);
        pnZoom.setLayout(new BoxLayout(pnZoom, 0));
        slZoom = new JSlider();
        slZoom.setMinimum(10);
        slZoom.setValue(12);
        slZoom.setMaximum(13);
        slZoom.setPreferredSize(new Dimension(16, 150));
        pnZoom.add(slZoom);
        slZoom.setOrientation(1);
        JScrollPane sc2 = new JScrollPane();
        pnBot.add(sc2);
        JPanel pnAlgorithm = new JPanel();
        sc2.setViewportView(pnAlgorithm);
        pnAlgorithm.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)), "Thu\u1EADt To\341n", 4, 2, null, null));
        pnAlgorithm.setLayout(new BoxLayout(pnAlgorithm, 0));
        JPanel pnAlgorithmLeft = new JPanel();
        pnAlgorithm.add(pnAlgorithmLeft);
        pnAlgorithmLeft.setLayout(new BoxLayout(pnAlgorithmLeft, 1));
        JPanel pnThuatToan = new JPanel();
        pnAlgorithmLeft.add(pnThuatToan);
        pnThuatToan.setLayout(new FlowLayout(1, 5, 5));
        JLabel lblThuatToan = new JLabel("Thu\u1EADt to\341n:");
        lblThuatToan.setPreferredSize(sizeLbl);
        pnThuatToan.add(lblThuatToan);
        dcbModel = new DefaultComboBoxModel<>();
        cboThuatToan = new JComboBox<>(dcbModel);
        cboThuatToan.setPreferredSize(sizeCbo);
        pnThuatToan.add(cboThuatToan);
        JPanel pnSapXep = new JPanel();
        pnAlgorithmLeft.add(pnSapXep);
        pnSapXep.setLayout(new FlowLayout(1, 5, 5));
        JLabel lblSapXep = new JLabel("S\u1EAFp x\u1EBFp:");
        lblSapXep.setPreferredSize(sizeLbl);
        pnSapXep.add(lblSapXep);
        dcbSapXep = new DefaultComboBoxModel<>();
        dcbSapXep.addElement("T\u0103ng d\u1EA7n");
        dcbSapXep.addElement("Gi\u1EA3m d\u1EA7n");
        cboSapXep = new JComboBox<>(dcbSapXep);
        cboSapXep.setPreferredSize(sizeCbo);
        pnSapXep.add(cboSapXep);
        JPanel pnBtn = new JPanel();
        pnAlgorithmLeft.add(pnBtn);
        btnXoa = new JButton("Xo\341");
        btnXoa.setPreferredSize(sizebtn);
        btnXoa.setToolTipText("X\363a m\340n h\354nh");
        pnBtn.add(btnXoa);
        btnTamDung = new JButton("T\u1EA1m d\u1EEBng");
        btnTamDung.setPreferredSize(sizebtn);
        btnTamDung.setToolTipText("T\u1EA1m d\u1EEBng ch\u01B0\u01A1ng tr\354nh");
        pnBtn.add(btnTamDung);
        btnTiepTuc = new JButton("Ti\u1EBFp t\u1EE5c");
        btnTiepTuc.setVisible(false);
        btnTiepTuc.setToolTipText("Ti\u1EBFp t\u1EE5c ch\u01B0\u01A1ng tr\354nh");
        btnTiepTuc.setPreferredSize(new Dimension(100, 35));
        pnBtn.add(btnTiepTuc);
        JPanel pnStart = new JPanel();
        pnAlgorithmLeft.add(pnStart);
        btnStep = new JButton("Step");
        btnStep.setPreferredSize(sizebtn);
        btnStep.setToolTipText("T\u1EEBng b\u01B0\u1EDBc");
        pnStart.add(btnStep);
        btnBatDau = new JButton("B\u1EAFt \u0111\u1EA7u");
        btnBatDau.setPreferredSize(sizebtn);
        btnBatDau.setToolTipText("B\u1EAFt \u0111\u1EA7u m\364 ph\u1ECFng.");
        pnStart.add(btnBatDau);
        btnDung = new JButton("D\u1EEBng");
        btnDung.setVisible(false);
        btnDung.setToolTipText("D\u1EEBng m\364 ph\u1ECFng.");
        btnDung.setPreferredSize(new Dimension(100, 35));
        pnStart.add(btnDung);
        JPanel pnSpeed = new JPanel();
        pnAlgorithm.add(pnSpeed);
        pnSpeed.setLayout(new BoxLayout(pnSpeed, 0));
        slSpeed = new JSlider();
        slSpeed.setValue(3);
        slSpeed.setPreferredSize(new Dimension(16, 150));
        slSpeed.setOrientation(1);
        pnSpeed.add(slSpeed);
        JPanel pnMid = new JPanel();
        con.add(pnMid, "Center");
        pnMid.setLayout(new BorderLayout(0, 0));
        pnColumns = new JPanel();
        pnMid.add(pnColumns, "Center");
        pnColumns.setLayout(new GridLayout(1, 0, 0, 0));
        JPanel pnTilte = new JPanel();
        con.add(pnTilte, "North");
        JLabel lblTitle = new JLabel("M\364 Ph\u1ECFng S\u1EAFp X\u1EBFp");
        lblTitle.setFont(new Font("Dialog", 1, 30));
        lblTitle.setForeground(Color.RED);
        pnTilte.add(lblTitle);
        JPanel pnColumnsVar = new JPanel();
        pnMid.add(pnColumnsVar, "South");
    }

    private void addEvents() {

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                xuLyThoat();
            }

        });
        mnuFileExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                xuLyThoat();
            }

        });
        btnNhapTay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                xuLyNhapTay();
            }

        });
        mnuHelpHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                xuLyHelp();
            }

        });
        mnuHelpAbout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                xuLyAbout();
            }

        });
        btnBatDau.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    xuLyBatDau();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                    setShowHideButton(normal);
                }
            }

        });
        btnDung.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    xuLyDung();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                    setShowHideButton(normal);
                }
            }

        });
        spSoPhanTu.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                soPhanTu = Integer.parseInt((new StringBuilder()).append(spSoPhanTu.getValue()).toString());
            }

        });
        btnRandom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    xuLyRandom();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                    setShowHideButton(normal);
                }
            }
        });
        btnXuatFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    xuLyXuatFile();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNhapFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    xuLyNhapFile();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                    setShowHideButton(normal);
                }
            }

        });
        btnXoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                xuLyXoa();
            }

        });
        slSpeed.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                xuLyThayDoiTocDo();
            }

        });
        btnTamDung.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    xuLyTamDung();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        });
        btnTiepTuc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    xuLyTiepTuc();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        });
        cboThuatToan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                xuLyThayDoiThuatToan();
            }

        });
        slZoom.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                xuLyZoom();
            }

        });
        btnStep.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    xuLyTamDung();
                    xuLyStep();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

    private void addMyRunnable(ComboBoxModel<MyRunnable> dcbModel2) {

        MyRunnable bubbleRunnable = GetFeature.getBubbleRunnable();
        MyRunnable interchangeRunnable = GetFeature.getInterchangeRunnable();
        MyRunnable quickRunnable = GetFeature.getQuickRunnable();
        MyRunnable selectionRunnable = GetFeature.getSelectionRunnable();
        MyRunnable mergeRunnable = GetFeature.getMergeRunnable();
        MyRunnable insertRunnable = GetFeature.getInsertRunnable();
        MyRunnable shakerRunnable = GetFeature.getShakerRunnable();
        MyRunnable shellRunnable = GetFeature.getShellRunnable();
        MyRunnable heapRunnable = GetFeature.getHeapRunnable();
        MyRunnable timRunnable = GetFeature.getTimRunnable();
        dcbModel.addElement(bubbleRunnable);
        dcbModel.addElement(heapRunnable);
        dcbModel.addElement(mergeRunnable);
        dcbModel.addElement(quickRunnable);
        dcbModel.addElement(insertRunnable);
        dcbModel.addElement(interchangeRunnable);
        dcbModel.addElement(selectionRunnable);
        dcbModel.addElement(shakerRunnable);
        dcbModel.addElement(shellRunnable);
        dcbModel.addElement(timRunnable);
    }

    private void hienThiTatCaCacCot() throws InterruptedException {

        setShowHideButton(normal);
        pnColumns.removeAll();
        if((columns == null) || columns.isEmpty()) { return; }
        Columns.optimization(columns, pnColumns);
        Column column;
        for(Iterator<Column> iterator = columns.iterator(); iterator.hasNext(); pnColumns.add(column.getPnColumn())) {
            column = iterator.next();
        }

        try {
            IConstants.refresh(pnColumns);
        } catch(InterruptedException e) {
            e.printStackTrace();
            setShowHideButton(normal);
        }
    }

    private void setShowHideButton(byte mode) {

        boolean flag = true;
        switch(mode) {
        case normal:
            btnDung.setVisible(!flag);
            btnBatDau.setVisible(flag);
            btnStep.setEnabled(!flag);
            btnNhapFile.setEnabled(flag);
            btnNhapTay.setEnabled(flag);
            btnRandom.setEnabled(flag);
            btnXoa.setEnabled(flag);
            spSoPhanTu.setEnabled(flag);
            cboSapXep.setEnabled(flag);
            cboThuatToan.setEnabled(flag);
            break;

        case starting:
            btnDung.setVisible(flag);
            btnBatDau.setVisible(!flag);
            btnStep.setEnabled(flag);
            btnNhapFile.setEnabled(!flag);
            btnNhapTay.setEnabled(!flag);
            btnRandom.setEnabled(!flag);
            btnXoa.setEnabled(!flag);
            spSoPhanTu.setEnabled(!flag);
            cboSapXep.setEnabled(!flag);
            cboThuatToan.setEnabled(!flag);
            break;

        case continues:
            btnTamDung.setVisible(!flag);
            btnTiepTuc.setVisible(flag);
            break;

        case pause:
            btnTiepTuc.setVisible(!flag);
            btnTamDung.setVisible(flag);
            break;
        }
    }

    protected void xuLyAbout() {

        InforUI ui = GetUI.getInforUI();
        ui.showWindow();
    }

    protected void xuLyBatDau() throws InterruptedException {

        int value = slSpeed.getValue();
        slSpeed.setValue(++value);
        slSpeed.setValue(--value);
        hienThiTatCaCacCot();
        MyRunnable runnable = (MyRunnable)cboThuatToan.getSelectedItem();
        runnable.setColumns(columns);
        if((threadStart != null) && threadStart.isAlive()) {
            return;
        } else {
            threadStart = new MyThread(runnable);
            int index = cboSapXep.getSelectedIndex();
            boolean asc = index == 0;
            runnable.setAsc(asc);
            threadStart.start();
            setShowHideButton(starting);
            return;
        }
    }

    protected void xuLyDung() throws InterruptedException {

        if((threadStart == null) || !threadStart.isAlive()) { return; }
        xuLyTamDung();
        int ret = IText.showConfirm("D\u1EEBng ?", 0);
        if(ret != 0) {
            return;
        } else {
            threadStart.endThread();
            xuLyXoa();
            xuLyTiepTuc();
            setShowHideButton(normal);
            return;
        }
    }

    protected void xuLyHelp() {

        HelpUI ui = GetUI.getHelpUI();
        ui.showWindow();
    }

    protected void xuLyNhapFile() throws InterruptedException {

        IFile file = GetFeature.getFile();
        List<Column> list = file.openFile(soPhanTu);
        if(list != null) {
            columns = list;
        }
        hienThiTatCaCacCot();
    }

    protected void xuLyNhapTay() {

        InputUI ui = GetUI.getInputUI();
        ui.nhanThongDiep(columns);
        ui.showWindow();
    }

    protected void xuLyRandom() throws InterruptedException {

        IRandom random = GetFeature.getRandom();
        columns = random.random(soPhanTu);
        hienThiTatCaCacCot();
    }

    protected void xuLyStep() throws InterruptedException {

        threadStart.step();
    }

    protected void xuLyTamDung() throws InterruptedException {

        if((threadStart != null) && threadStart.pause()) {
            setShowHideButton(continues);
        }
    }

    protected void xuLyThayDoiThuatToan() {

        String s = cboThuatToan.getSelectedItem().toString();
        DefaultListModel<String> model = null;
        String s1;
        switch((s1 = s.toLowerCase()).hashCode()) {
        default:
            break;

        case -2140158938:
            if(s1.equals("tim sort")) {
                model = GetListModelCode.getDlmTim();
            }
            break;

        case -1705846350:
            if(s1.equals("interchange sort")) {
                model = GetListModelCode.getDlmInterChange();
            }
            break;

        case -1149295482:
            if(s1.equals("merge sort")) {
                model = GetListModelCode.getDlmMerge();
            }
            break;

        case -161354075:
            if(s1.equals("insert sort")) {
                model = GetListModelCode.getDlmInsert();
            }
            break;

        case 451982:
            if(s1.equals("shell sort")) {
                model = GetListModelCode.getDlmShell();
            }
            break;

        case 66579282:
            if(s1.equals("heap sort")) {
                model = GetListModelCode.getDlmHeap();
            }
            break;

        case 720775346:
            if(s1.equals("bubble sort")) {
                model = GetListModelCode.getDlmBubble();
            }
            break;

        case 1559547666:
            if(s1.equals("shaker sort")) {
                model = GetListModelCode.getDlmShaker();
            }
            break;

        case 1621453809:
            if(s1.equals("quick sort")) {
                model = GetListModelCode.getDlmQuick();
            }
            break;

        case 2110098354:
            if(s1.equals("selection sort")) {
                model = GetListModelCode.getDlmSelection();
            }
            break;
        }
        lstCode.setModel(model);
    }

    protected void xuLyThayDoiTocDo() {

        int curValue = slSpeed.getValue();
        int maxValue = slSpeed.getMaximum();
        int delay = maxValue - curValue;
        ISort sort = GetFeature.getSort();
        sort.setDelay(delay);
    }

    protected void xuLyThoat() {

        try {
            xuLyTamDung();
        } catch(InterruptedException e) {
            e.printStackTrace();
            setShowHideButton(normal);
        }
        int ret = IText.showConfirm("Xác Nhận Thoát?", 0);
        if(ret == 0) {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(1);
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }

    protected void xuLyTiepTuc() throws InterruptedException {

        if(threadStart.continues()) {
            setShowHideButton(pause);
        }
    }

    protected void xuLyXoa() {

        pnColumns.removeAll();
        pnColumns.revalidate();
        pnColumns.repaint();
    }

    protected void xuLyXuatFile() throws InterruptedException {

        xuLyTamDung();
        IFile file = GetFeature.getFile();
        file.saveFile(columns);
    }

    protected void xuLyZoom() {

        Font font = lstCode.getFont();
        lstCode.setFont(new Font(font.getFontName(), font.getStyle(), slZoom.getValue()));
    }

    @Override
    public void nhanThongDiep(List<Column> columns) {

        if(columns == null) { return; }
        spSoPhanTu.setValue(Integer.valueOf(columns.size()));
        this.columns = columns;
        try {
            hienThiTatCaCacCot();
        } catch(InterruptedException e) {
            e.printStackTrace();
            setShowHideButton(normal);
        }
    }

    public void showWindow() {

        setVisible(true);
    }

    @Override
    public void sortDone() {

        setShowHideButton(normal);
        setShowHideButton(pause);
        IText.showMessage("\u0110\343 S\u1EAFp X\u1EBFp Xong!.", 1);
    }

}
