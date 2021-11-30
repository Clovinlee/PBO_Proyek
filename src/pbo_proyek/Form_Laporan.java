/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pbo_proyek;

import javax.swing.JPanel;
import ExternalCode.JTableEdit;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.text.SimpleDateFormat;  
import java.lang.*;
import javax.swing.JOptionPane;
/**
 *
 * @author chris
 */
public class Form_Laporan extends javax.swing.JFrame {
    
    /**
     * Creates new form Form_History
     */
    public Form_Laporan() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("ComboBox.selectionBackground",new ColorUIResource(Palette.getTableDark1()));
        } catch (Exception e) {
        }
        initComponents();
        styleDgv();
        loadDgv();
    }
    public JPanel getPl() {
        return pl;
    }
    public void styleDgv(){
        JTableHeader header = tb_Htrans.getTableHeader();
        jScrollPane1.getViewport().setBackground(Palette.getDark4());
            
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setOpaque(false);
        
        //JTableEdit.setJTableColumnsWidth(dgv_stok, 375, 10,20,40,15,15);
        
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        tb_Htrans.getTableHeader().setBackground(Palette.getDark1());
        tb_Htrans.getTableHeader().setForeground(Palette.getSilver1());
        
        header = tb_Dtrans.getTableHeader();
        jScrollPane2.getViewport().setBackground(Palette.getDark4());
            
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setOpaque(false);
        
        //JTableEdit.setJTableColumnsWidth(dgv_stok, 375, 10,20,40,15,15);
        
        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        tb_Dtrans.getTableHeader().setBackground(Palette.getDark1());
        tb_Dtrans.getTableHeader().setForeground(Palette.getSilver1());
    }
    
    DefaultTableModel tbl;
    DefaultTableModel tb2;
    ArrayList<String[]> listHtrans;
    ArrayList<String[]> listdiskon;
    ArrayList<String[]> listkaryawan;
    ArrayList<String[]> listDtrans;
    ArrayList<String[]> listbarang;
    int selectedidx = -1;
    int granddtotal ;
    public void loadDgv(){
        granddtotal = 0;
        listHtrans = DB.query("SELECT * FROM h_trans");
        tbl = new DefaultTableModel(new Object[] {"Nomor Nota","Tanggal Transaksi","Promo","Kode Karyawan","Grand Total"}, 0);
        tb2 = new DefaultTableModel(new Object[] {"Kode Barang","Nama Barang","Harga Barang","Subtotal"}, 0);
        tb_Htrans.setDefaultEditor(Object.class, null);
        listdiskon = DB.query("SELECT * FROM diskon");
        for (String[] s :listdiskon) {
            cb_diskon.addItem(s[1]);
        }
        listkaryawan = DB.query("SELECT * FROM karyawan");
        listbarang = DB.query("SELECT * FROM barang");
        listDtrans = DB.query("SELECT * FROM d_trans");
        
        int ctr = 1;
        String diskon="";
        String karyawan="";
        for (String[] s : listHtrans) {
            for(String[] j:listdiskon){
                if(s[3].equals(j[0])){
                    diskon = j[1];
                }
                else if(diskon.equals("-")){
                    diskon = "-";
                }
            }
            for(String[] k:listkaryawan){
                if(s[4].equals(k[0])){
                    karyawan = k[1];
                }
            }
            granddtotal += Integer.parseInt(s[2]);
            tbl.addRow(new Object[] {s[0],s[1],diskon,karyawan,s[2]});
            diskon = "-";
            ctr++;
        }
        tb_Htrans.setModel(tbl);
        lbl_grandtotal.setText("Grand Total: Rp."+String.valueOf(granddtotal));
    }
    
    public void search(){
        granddtotal = 0;
        tbl = new DefaultTableModel(new Object[] {"Nomor Nota","Tanggal Transaksi","Promo","Kode Karyawan","Grand Total"}, 0);
        tb_Htrans.setDefaultEditor(Object.class, null);
        String diskon="";
        String karyawan="";
        for (String[] s : listHtrans) {
            for(String[] j:listdiskon){
                if(s[3].equals(j[0])){
                    diskon = j[1];
                }
                else if(diskon.equals("-")){
                    diskon = "-";
                }
            }
            for(String[] k:listkaryawan){
                if(s[4].equals(k[0])){
                    karyawan = k[1];
                }
            }
            String cbpilih ;
            if (cb_diskon.getSelectedItem().toString().equals("Tanpa Promo")){
                cbpilih = "-";
            }
            else{
                cbpilih = cb_diskon.getSelectedItem().toString();
            }
            if(validSearch(tb_nota.getText().toLowerCase(),tb_karyawan.getText().toLowerCase(),dp_tanggal.getDate(),dp_tanggal1.getDate(),karyawan,s,cbpilih,diskon)){
                tbl.addRow(new Object[] {s[0],s[1],diskon,karyawan,s[2]});
                
                granddtotal += Integer.parseInt(s[2]);
            }
            diskon = "-";
        }
        tb_Htrans.setModel(tbl);
        lbl_grandtotal.setText("Grand Total: Rp."+String.valueOf(granddtotal));
    }
    public boolean validSearch(String nota, String kodekaryawan, Date tanggalawal, Date tanggalakhir, String karyawan, String[] data, String selecteddiskon, String diskon){
        System.out.println(selecteddiskon);
        System.out.println(diskon);
        if(data[0].toLowerCase().contains(nota.toLowerCase())){
            if(karyawan.toLowerCase().contains(kodekaryawan)){
                if(selecteddiskon.equalsIgnoreCase("Semua") || selecteddiskon.equalsIgnoreCase(diskon)){
                Date tgltrans;
                
                try {
                tgltrans = new SimpleDateFormat("yyyy-MM-dd").parse(data[1]);
                if (tgltrans.after(tanggalawal) && tgltrans.before(tanggalakhir)){
                        return true;
                }
                }
                catch(Exception e){
                    
                }
                }
                
                else{
                    if (selecteddiskon.toLowerCase().equals(diskon)){
                        Date tgltrans;
                        return true;
//                        try {
//                        tgltrans = new SimpleDateFormat("yyyy-MM-dd").parse(data[1]);
//                        if (tgltrans.after(tanggalawal) && tgltrans.before(tanggalakhir)){
//                                return true;
//                        }
//                        }
//                        catch(Exception e){
//
//                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pl = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tb_nota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tb_karyawan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Htrans = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Dtrans = new javax.swing.JTable();
        dp_tanggal = new com.toedter.calendar.JDateChooser();
        btn_export = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        dp_tanggal1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        lbl_grandtotal = new javax.swing.JLabel();
        cb_diskon = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btn_export1 = new javax.swing.JButton();
        btn_find = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(848, 540));

        pl.setBackground(new java.awt.Color(84, 84, 96));
        pl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pl.setPreferredSize(new java.awt.Dimension(790, 540));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(222, 222, 222));
        jLabel2.setText("Nomor Nota");

        tb_nota.setBackground(new java.awt.Color(58, 58, 58));
        tb_nota.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_nota.setForeground(new java.awt.Color(222, 222, 222));
        tb_nota.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_nota.setCaretColor(new java.awt.Color(222, 222, 222));
        tb_nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_notaKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(222, 222, 222));
        jLabel3.setText("Tanggal Transaksi");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(222, 222, 222));
        jLabel5.setText("Promo");

        tb_karyawan.setBackground(new java.awt.Color(58, 58, 58));
        tb_karyawan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_karyawan.setForeground(new java.awt.Color(222, 222, 222));
        tb_karyawan.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_karyawan.setCaretColor(new java.awt.Color(222, 222, 222));
        tb_karyawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_karyawanKeyReleased(evt);
            }
        });

        tb_Htrans.setBackground(new java.awt.Color(58, 58, 58));
        tb_Htrans.setForeground(new java.awt.Color(222, 222, 222));
        tb_Htrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor Nota", "Tanggal Transaksi", "Diskon", "Kode Karyawan", "Grand Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Htrans.setGridColor(new java.awt.Color(255, 255, 255));
        tb_Htrans.setRowHeight(25);
        tb_Htrans.setSelectionBackground(new java.awt.Color(90, 90, 90));
        tb_Htrans.setSelectionForeground(new java.awt.Color(222, 222, 222));
        tb_Htrans.setShowVerticalLines(false);
        tb_Htrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_HtransMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Htrans);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(222, 222, 222));
        jLabel7.setText("Detail Transaksi");

        tb_Dtrans.setBackground(new java.awt.Color(58, 58, 58));
        tb_Dtrans.setForeground(new java.awt.Color(222, 222, 222));
        tb_Dtrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Barang", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Dtrans.setGridColor(new java.awt.Color(255, 255, 255));
        tb_Dtrans.setRowHeight(25);
        tb_Dtrans.setSelectionBackground(new java.awt.Color(90, 90, 90));
        tb_Dtrans.setSelectionForeground(new java.awt.Color(222, 222, 222));
        tb_Dtrans.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tb_Dtrans);

        btn_export.setBackground(new java.awt.Color(58, 58, 58));
        btn_export.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_export.setForeground(new java.awt.Color(222, 222, 222));
        btn_export.setText("Laporan Transaksi");
        btn_export.setBorder(null);
        btn_export.setContentAreaFilled(false);
        btn_export.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_export.setFocusPainted(false);
        btn_export.setOpaque(true);
        btn_export.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_exportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_exportMouseExited(evt);
            }
        });
        btn_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportActionPerformed(evt);
            }
        });

        btn_refresh.setBackground(new java.awt.Color(58, 58, 58));
        btn_refresh.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_refresh.setForeground(new java.awt.Color(222, 222, 222));
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/redo-alt-solid.png"))); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.setBorder(null);
        btn_refresh.setContentAreaFilled(false);
        btn_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_refresh.setFocusPainted(false);
        btn_refresh.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_refresh.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_refresh.setOpaque(true);
        btn_refresh.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_refreshMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_refreshMouseExited(evt);
            }
        });
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(222, 222, 222));
        jLabel4.setText("-");

        lbl_grandtotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_grandtotal.setForeground(new java.awt.Color(222, 222, 222));
        lbl_grandtotal.setText("Grand Total : Rp0");

        cb_diskon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Tanpa Promo" }));
        cb_diskon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_diskonItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(222, 222, 222));
        jLabel6.setText("Kode Karyawan");

        btn_export1.setBackground(new java.awt.Color(58, 58, 58));
        btn_export1.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_export1.setForeground(new java.awt.Color(222, 222, 222));
        btn_export1.setText("Laporan Stok");
        btn_export1.setBorder(null);
        btn_export1.setContentAreaFilled(false);
        btn_export1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_export1.setFocusPainted(false);
        btn_export1.setOpaque(true);
        btn_export1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_export1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_export1MouseExited(evt);
            }
        });
        btn_export1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_export1ActionPerformed(evt);
            }
        });

        btn_find.setBackground(new java.awt.Color(84, 84, 96));
        btn_find.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_find.setForeground(new java.awt.Color(222, 222, 222));
        btn_find.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/search-solid.png"))); // NOI18N
        btn_find.setBorder(null);
        btn_find.setContentAreaFilled(false);
        btn_find.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_find.setFocusPainted(false);
        btn_find.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_find.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_find.setOpaque(true);
        btn_find.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_find.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_findMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_findMouseExited(evt);
            }
        });
        btn_find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_findActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plLayout = new javax.swing.GroupLayout(pl);
        pl.setLayout(plLayout);
        plLayout.setHorizontalGroup(
            plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_grandtotal)
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plLayout.createSequentialGroup()
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, plLayout.createSequentialGroup()
                                .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btn_export1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plLayout.createSequentialGroup()
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tb_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tb_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cb_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(dp_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dp_tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_find, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        plLayout.setVerticalGroup(
            plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(plLayout.createSequentialGroup()
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tb_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tb_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6))
                    .addComponent(dp_tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(dp_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_find, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lbl_grandtotal))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_export1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pl, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exportMouseEntered
        // TODO add your handling code here:
        btn_export.setBackground(Palette.getButtonSelectedColor());
    }//GEN-LAST:event_btn_exportMouseEntered

    private void btn_exportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exportMouseExited
        // TODO add your handling code here:
        btn_export.setBackground(Palette.getTableDark1());
    }//GEN-LAST:event_btn_exportMouseExited

    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
        // TODO add your handling code here:
        String simpan = "";
        simpan += 
        "╔═════════════════════════════════════════╗\n" +
        "║                                         ║\n" +
        "║              C O M P U F Y              ║\n" +
        "║                                         ║\n" +
        "╠═════════════════════════════════════════╣\n" +
        "║            LAPORAN TRANSAKSI            ║\n" +
        "║   -----------------------------------   ║\n"
        ;
        for (int i = 0;i < tbl.getRowCount();i++){
            String tanggallapor = tb_Htrans.getModel().getValueAt(i, 1).toString();
            tanggallapor = tanggallapor.substring(0,10);
            String karyawanlapor = tb_Htrans.getModel().getValueAt(i, 3).toString();
            String totallapor = tb_Htrans.getModel().getValueAt(i, 4).toString();
            String nomorrnota = tb_Htrans.getModel().getValueAt(i, 0).toString();
            String diskonlapor = tb_Htrans.getModel().getValueAt(i, 2).toString();
            if (diskonlapor.equals("-")){
                diskonlapor = "-";
            }
            String hargadiskon = "";
            if (!diskonlapor.equals("-")){
                for (String[] l : listdiskon){
                if (diskonlapor.equalsIgnoreCase(l[1])){
                    hargadiskon = l[2];
                }
            }
            }
            simpan += "║   "+nomorrnota+"                      ║\n";
            simpan += "║     Tanggal Transaksi: "+tanggallapor+"       ║\n";
            simpan += "║     Kode Karyawan    : "+karyawanlapor+"         ║\n";
            simpan += "║     Promo            : "+diskonlapor;
            for (int n = 0 ; n < 17-diskonlapor.chars().count();n++){
                simpan += " ";
            }
            simpan += "║\n";
            if (diskonlapor.equals("-")){
                simpan += "║     Potongan Promo   : Rp 0             ║\n";
            }
            else{
                int angkadiskon = Integer.parseInt(hargadiskon);
                hargadiskon = String.format("%,d", angkadiskon);
                simpan += "║     Potongan Promo   : Rp "+hargadiskon;
                for(int o = 0; o < 14-hargadiskon.chars().count();o++){
                    simpan += " ";
                }
                simpan += "║\n";
            }
            int angkalapor = Integer.parseInt(totallapor);
            totallapor = String.format("%,d", angkalapor);
            simpan += "║     Total Transaksi  : Rp "+totallapor;
            for (int j = 0;j< 14-totallapor.chars().count();j++){
                simpan+= " ";
            }
            simpan += "║\n";
            simpan += "║   -----------------------------------   ║\n";
            
        }
        simpan += "║                                         ║\n";
        simpan += "║";
        int angkagrand = Integer.parseInt(lbl_grandtotal.getText().substring(16));
        String tulisangrand = String.format("%,d", angkagrand);
        for (int m = 0; m<25-tulisangrand.chars().count();m++){
            simpan += " ";
        }
        
        simpan += "Grand Total: Rp "+ tulisangrand+"║\n";
        simpan += "╚═════════════════════════════════════════╝";
        
        try {
            FileWriter fout = new FileWriter("Laporan/laporan_transaksi.txt");
                BufferedWriter bw = new BufferedWriter(fout);
                
                bw.write(simpan);
                                bw.close();
                fout.close();
                JOptionPane.showMessageDialog(null, "Sukses Export Laporan!","Sukses",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_exportActionPerformed

    private void btn_refreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseEntered
        // TODO add your handling code here:
        btn_refresh.setBackground(Palette.getButtonSelectedColor());
    }//GEN-LAST:event_btn_refreshMouseEntered

    private void btn_refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseExited
        // TODO add your handling code here:
        btn_refresh.setBackground(Palette.getTableDark1());
    }//GEN-LAST:event_btn_refreshMouseExited

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        loadDgv();
        cb_diskon.setSelectedIndex(0);
        tb_karyawan.setText("");
        tb_nota.setText("");
        selectedidx = -1;
        tb2.setRowCount(0);
        tb_Dtrans.setModel(tb2);
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void tb_notaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_notaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_notaKeyReleased

    private void tb_karyawanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_karyawanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_karyawanKeyReleased

    private void cb_diskonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_diskonItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_diskonItemStateChanged

    private void btn_export1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_export1MouseEntered
        // TODO add your handling code here:
        btn_export1.setBackground(Palette.getButtonSelectedColor());
    }//GEN-LAST:event_btn_export1MouseEntered

    private void btn_export1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_export1MouseExited
        // TODO add your handling code here:
        btn_export1.setBackground(Palette.getTableDark1());
    }//GEN-LAST:event_btn_export1MouseExited

    private void btn_export1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export1ActionPerformed
        // TODO add your handling code here:
        String simpan1 = "";
        simpan1 += "╔═════════════════════════════════════════╗\n" +
        "║                                         ║\n" +
        "║              C O M P U F Y              ║\n" +
        "║                                         ║\n" +
        "╠═════════════════════════════════════════╣\n" +
        "║               LAPORAN STOK              ║\n"
        ;
        for (int i = 0; i<tbl.getRowCount();i++){
            simpan1 += "║   -----------------------------------   ║\n";
            String nomorrnota = tb_Htrans.getModel().getValueAt(i, 0).toString();
            simpan1 += "║   "+nomorrnota+"                      ║\n";
            for (String[] j : listDtrans){
                int ctrspasi = 0;
                if (j[0].equals(nomorrnota)){
                    
                    for (String[] k: listbarang){
                        if (k[0].equals(j[1])){
                            
                            if (ctrspasi != 0){
                                simpan1 += "║                                         ║\n";
                            }
                            String namabarng = k[2];
                            
                            for (int l = 0; l<Math.ceil(namabarng.chars().count()/17)+1;l++){
                                
                                if (l != Math.ceil(namabarng.chars().count()/17)){
                                    if (l == 0){
                                       simpan1+= "║     "+namabarng.substring(0,17)+"                   ║\n"; 
                                    }
                                    else{
                                      simpan1 += "║     "+namabarng.substring((17*(l)),((17*(l+1))))+"                   ║\n";   
                                    }
                                }
                                else{
                                    simpan1+= "║     "+namabarng.substring(17*(l));
                                    for (int m = 0; m < 17-namabarng.substring(17*(l)).chars().count();m++){
                                        simpan1+= " ";
                                    }
                                    simpan1 += "  ";
                                    for (int n = 0; n < 3-j[2].chars().count();n++){
                                        simpan1+= " ";
                                    }
                                    simpan1 += j[2]+"  "+k[1]+"    ║\n";
                                    
                                }
                            }
                            ctrspasi += 1;
                        }
                    }
                    
                    
                }
            }
            
        }
        simpan1 += "║   -----------------------------------   ║\n";
        simpan1 += "║   TOTAL PENGELUARAN BARANG              ║\n";
        simpan1 += "║   -----------------------------------   ║\n";
        int totalbarang = 0;
        int ctrspasi = 0;
        for (String[] o : listbarang){
            int simpanbarang = 0;
            for (String[] p : listDtrans){
                for (int q = 0; q < tbl.getRowCount();q++){
                    String nomorrnota = tb_Htrans.getModel().getValueAt(q, 0).toString();
                    if (nomorrnota.equals(p[0])){
                        if (p[1].equals(o[0])){
                            simpanbarang += Integer.parseInt(p[2]);
                            totalbarang += Integer.parseInt(p[2]);
                        }
                    }
                }
                
            }
            
           
            String namabarng = o[2];
            boolean chckbarang = false;
            for (String[] v : listDtrans){
                for (int e = 0; e<tbl.getRowCount();e++){
                    String nomorrnota = tb_Htrans.getModel().getValueAt(e, 0).toString();
                    if(v[0].equals(nomorrnota)){
                        if (o[0].equals(v[1])){
                            chckbarang = true;
                        }
                    }
                }
                
            }
            if (chckbarang == true){
                
                if (ctrspasi != 0){
                        simpan1 += "║                                         ║\n";
                }
                ctrspasi += 1;
                for (int r = 0; r<Math.ceil(namabarng.chars().count()/17)+1;r++){ 
                    
                if (r != Math.ceil(namabarng.chars().count()/17)){
                    if (r == 0){
                        simpan1+= "║     "+namabarng.substring(0,17)+"                   ║\n"; 
                    }
                    else{
                      simpan1+= "║     "+namabarng.substring(17*(r),((17*(r+1))))+"                   ║\n";   
                    }
                    
                }
                else{
                    simpan1+= "║     "+namabarng.substring(17*(r));
                    for (int m = 0; m < 17-namabarng.substring(17*(r)).chars().count();m++){
                        simpan1+= " ";
                    }
                    simpan1 += "  ";
                    for (int n = 0; n < 3-String.valueOf(simpanbarang).chars().count();n++){
                        simpan1+= " ";
                    }
                    simpan1 += simpanbarang+"  "+o[1]+"    ║\n";   
                    
                }
                
            }
//                simpan1 += "║                                         ║\n";
            }
            
            
        }
        simpan1 += "║   -----------------------------------   ║\n";
        simpan1 += "║                     TOTAL BARANG ";
        for (int a = 0; a < 4-String.valueOf(totalbarang).chars().count();a++){
            simpan1 += " ";
        }
        simpan1 += String.valueOf(totalbarang)+"   ║\n";
        simpan1 += "║                                         ║\n";
        simpan1 += "╚═════════════════════════════════════════╝";
        
        try {
            FileWriter fout = new FileWriter("Laporan/laporan_stok.txt");
                BufferedWriter bw = new BufferedWriter(fout);
                
                bw.write(simpan1);
                                bw.close();
                fout.close();
                
                JOptionPane.showMessageDialog(null, "Sukses Export Laporan!","Sukses",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_export1ActionPerformed

    private void btn_findMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_findMouseEntered
        // TODO add your handling code here:
        btn_find.setBackground(new Color(106,106,115));
    }//GEN-LAST:event_btn_findMouseEntered

    private void btn_findMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_findMouseExited
        // TODO add your handling code here:
        btn_find.setBackground(Palette.getDark3());
    }//GEN-LAST:event_btn_findMouseExited

    private void btn_findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_findActionPerformed
        // TODO add your handling code here:
        search();
        // CALL SEARCH EVERY THIS BUTTON PRESSED
    }//GEN-LAST:event_btn_findActionPerformed

    private void tb_HtransMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HtransMousePressed
        // TODO add your handling code here:
        selectedidx = tb_Htrans.getSelectedRow();
        if (selectedidx != -1){
            String nonota = tb_Htrans.getModel().getValueAt(selectedidx, 0).toString();
            
            
            tb2 = new DefaultTableModel(new Object[] {"Kode Barang","Nama Barang","Harga Barang","Subtotal"}, 0);
            tb_Dtrans.setDefaultEditor(Object.class, null);
            String barang="";
            String harga="";
            String kode="";
            for (String[] s : listDtrans) {
                if (s[0].equals(nonota)){
                    for(String[] j : listbarang){
                        if(s[1].equals(j[0])){
                            barang = j[2];
                            harga = j[4];
                            kode = j[1];
                        }
                    }
                    tb2.addRow(new Object[] {kode,barang,harga,s[4]});
                }
                
            }
            tb_Dtrans.setModel(tb2);
        }
        else{

        }
    }//GEN-LAST:event_tb_HtransMousePressed

    private void dp_tanggalPropertyChange(java.beans.PropertyChangeEvent evt) {                                          
        // TODO add your handling code here:
        
    }                                         
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_export;
    private javax.swing.JButton btn_export1;
    private javax.swing.JButton btn_find;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JComboBox<String> cb_diskon;
    private com.toedter.calendar.JDateChooser dp_tanggal;
    private com.toedter.calendar.JDateChooser dp_tanggal1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_grandtotal;
    private javax.swing.JPanel pl;
    private javax.swing.JTable tb_Dtrans;
    private javax.swing.JTable tb_Htrans;
    private javax.swing.JTextField tb_karyawan;
    private javax.swing.JTextField tb_nota;
    // End of variables declaration//GEN-END:variables
}
