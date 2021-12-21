/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pbo_proyek;

import com.itextpdf.text.BaseColor;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
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
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Rectangle;
import java.io.FileOutputStream;

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
    DefaultTableModel tb3;
    ArrayList<String[]> listHtrans;
    ArrayList<String[]> listdiskon;
    ArrayList<String[]> listkaryawan;
    ArrayList<String[]> listDtrans;
    ArrayList<String[]> listbarang;
    ArrayList<Integer> ArrayJumlahBarang = new ArrayList<Integer>();
    ArrayList<String> ArrayNamaBarang = new ArrayList<String>();
    ArrayList<String> ArrayNamaKode = new ArrayList<String>();
    ArrayList<String[]> listkategori;
    ArrayList<String[]> listsemuakategori;
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
        listsemuakategori = DB.query("SELECT * FROM jenis_barang");
        
        
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
        lbl_grandtotal1.setText("Total Transaksi : "+String.valueOf(tbl.getRowCount()));
        String strquer = "";
        strquer += "SELECT jenis_barang.nama_jenis, NVL(SUM(d_trans.qty),0) as jml\n" +
                "FROM jenis_barang\n" +
                "LEFT JOIN barang\n" +
                "ON barang.fk_jenis_barang = jenis_barang.id\n" +
                "LEFT JOIN d_trans\n" +
                "ON d_trans.fk_barang = barang.id\n" +
                "LEFT JOIN h_trans\n" +
                "ON h_trans.nomor_nota = d_trans.nomor_nota\n" ;
        strquer +=  "WHERE h_trans.nomor_nota IN (" ;
        
        for (int g = 0; g < tbl.getRowCount();g++){
            if (g != tbl.getRowCount()-1){
                strquer += "'"+tbl.getValueAt(g, 0)+"',";
            }
            else{
                strquer += "'"+tbl.getValueAt(g, 0)+"')\n";
            }
        }
        strquer += "GROUP BY jenis_barang.nama_jenis\n" +
                "ORDER BY 2 DESC";
        listkategori = DB.query(strquer);
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
        lbl_grandtotal1.setText("Total Transaksi : "+String.valueOf(tbl.getRowCount()));
        String strquer = "";
        strquer += "SELECT jenis_barang.nama_jenis, NVL(SUM(d_trans.qty),0) as jml\n" +
                "FROM jenis_barang\n" +
                "LEFT JOIN barang\n" +
                "ON barang.fk_jenis_barang = jenis_barang.id\n" +
                "LEFT JOIN d_trans\n" +
                "ON d_trans.fk_barang = barang.id\n" +
                "LEFT JOIN h_trans\n" +
                "ON h_trans.nomor_nota = d_trans.nomor_nota\n" ;
        strquer +=  "WHERE h_trans.nomor_nota IN (" ;
        
        for (int g = 0; g < tbl.getRowCount();g++){
            if (g != tbl.getRowCount()-1){
                strquer += "'"+tbl.getValueAt(g, 0)+"',";
            }
            else{
                strquer += "'"+tbl.getValueAt(g, 0)+"')\n";
            }
        }
        strquer += "GROUP BY jenis_barang.nama_jenis\n" +
                "ORDER BY 2 DESC";
        listkategori = DB.query(strquer);
        
    }
    public boolean validSearch(String nota, String kodekaryawan, Date tanggalawal, Date tanggalakhir, String karyawan, String[] data, String selecteddiskon, String diskon){
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
        lbl_grandtotal1 = new javax.swing.JLabel();
        btn_export3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        btn_export.setText("Laporan Penjualan");
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
        btn_export1.setText("Laporan Penjualan Stok");
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

        lbl_grandtotal1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_grandtotal1.setForeground(new java.awt.Color(222, 222, 222));
        lbl_grandtotal1.setText("Grand Total : Rp0");

        btn_export3.setBackground(new java.awt.Color(58, 58, 58));
        btn_export3.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_export3.setForeground(new java.awt.Color(222, 222, 222));
        btn_export3.setText("Laporan Jenis Barang");
        btn_export3.setBorder(null);
        btn_export3.setContentAreaFilled(false);
        btn_export3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_export3.setFocusPainted(false);
        btn_export3.setOpaque(true);
        btn_export3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_export3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_export3MouseExited(evt);
            }
        });
        btn_export3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_export3ActionPerformed(evt);
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
                        .addComponent(lbl_grandtotal1)
                        .addGap(88, 88, 88)
                        .addComponent(lbl_grandtotal)
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plLayout.createSequentialGroup()
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, plLayout.createSequentialGroup()
                                .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btn_export1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btn_export3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addContainerGap(119, Short.MAX_VALUE))))
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
                    .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_grandtotal)
                        .addComponent(lbl_grandtotal1)))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_export1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_export3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    
    public com.itextpdf.text.Font myFont(float fontSize, int type){
        com.itextpdf.text.Font df = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, fontSize, type);
        return df;
    }
    
    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
        // TODO add your handling code here:
        try {
            File f = new File(System.getProperty("user.dir")+"/Laporan/");
            f.mkdir();
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.dir")+"/Laporan/"+"laporan_penjualan.pdf"));
            doc.open();
            Paragraph p_judul = new Paragraph("COMPUFY", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 26, Font.BOLD));
            p_judul.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_judul);
            Paragraph p_subjudul = new Paragraph("Laporan Penjualan", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20, Font.BOLD));
            p_subjudul.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_subjudul);
            Paragraph p_temp = new Paragraph("PERIODE",myFont(12, Font.BOLD));
            p_temp.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_temp);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if(searched == true){
                p_temp = new Paragraph(sdf.format(dp_tanggal.getDate()) +" - "+ sdf.format(dp_tanggal1.getDate()),myFont(12, Font.BOLD));
            }else{
                p_temp = new Paragraph("Semua",myFont(12,Font.BOLD));
            }
            p_temp.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_temp);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell cl;
            
            int grandTotal = 0;
            
            for(int i = 0; i < tb_Htrans.getRowCount(); i++){
                String nomornota = tb_Htrans.getModel().getValueAt(i, 0).toString();
                cl = new PdfPCell(new Phrase(nomornota,myFont(12, Font.BOLD)));
                cl.setColspan(2);
                cl.setBackgroundColor(BaseColor.YELLOW);
                cl.setPaddingBottom(5);
                
                table.addCell(cl);
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                ArrayList<String[]> hquery = DB.query("select h.nomor_nota, h.tanggal, h.grand_total, NVL(d.nama,'-') AS namapotongan, NVL(d.potongan,0) AS potongan, h.grand_total, h.fk_karyawan from h_trans h LEFT JOIN diskon d ON h.fk_diskon = d.id where h.nomor_nota = '"+nomornota+"'ORDER BY h.tanggal ASC");
                for (String[] s : hquery) {
                    table.addCell("Tanggal Transaksi");
                    table.addCell(s[1]);
                    table.addCell("Potongan");
                    table.addCell("Rp"+String.format("%,d", Integer.parseInt(s[4])));
                    table.addCell(new Phrase("Grand Total", myFont(12, Font.BOLD)));
                    table.addCell(new Phrase("Rp"+String.format("%,d", Integer.parseInt(s[5])), myFont(12, Font.BOLD)));
                    grandTotal += Integer.parseInt(s[5]);
                }
                if(i != tb_Htrans.getRowCount()-1){
                    cl = new PdfPCell(new Phrase(" "));
                    cl.setBorder(Rectangle.OUT_TOP);
                    cl.setColspan(2);
                    table.addCell(cl);
                }
            }
            
            doc.add(table);
            
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Grand Total : Rp"+String.format("%,d",grandTotal),myFont(16, Font.BOLD)));
            
            doc.close();
            JOptionPane.showMessageDialog(null, "Sukses export laporan!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        searched = false;
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
    
    boolean searched = false;
    
    private void btn_export1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export1ActionPerformed
        // TODO add your handling code here:
        ArrayList<String> namabrg = new ArrayList<>();
        ArrayList<Integer> jmlbrg = new ArrayList<>();
        
        try {
            File f = new File(System.getProperty("user.dir")+"/Laporan/");
            f.mkdir();
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.dir")+"/Laporan/"+"laporan_stok.pdf"));
            doc.open();
            Paragraph p_judul = new Paragraph("COMPUFY", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 26, Font.BOLD));
            p_judul.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_judul);
            Paragraph p_subjudul = new Paragraph("Laporan Stok Penjualan", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20, Font.BOLD));
            p_subjudul.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_subjudul);
            Paragraph p_temp = new Paragraph("PERIODE",myFont(12,Font.BOLD));
            p_temp.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_temp);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if(searched == true){
                p_temp = new Paragraph(sdf.format(dp_tanggal.getDate()) +" - "+ sdf.format(dp_tanggal1.getDate()),myFont(12, Font.BOLD));
            }else{
                p_temp = new Paragraph("Semua",myFont(12,Font.BOLD));
            }
            p_temp.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_temp);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell cl;
            
            int grandTotal = 0;
            
            for(int i = 0; i < tb_Htrans.getRowCount(); i++){
                String nomornota = tb_Htrans.getModel().getValueAt(i, 0).toString();
                cl = new PdfPCell(new Phrase(nomornota,myFont(12, Font.BOLD)));
                cl.setColspan(2);
                cl.setBackgroundColor(BaseColor.YELLOW);
                cl.setPaddingBottom(5);
                
                table.addCell(cl);
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                ArrayList<String[]> dquery = DB.query("select d.nomor_nota, b.nama, d.qty, d.harga, d.subtotal from d_trans d join barang b on d.fk_barang = b.id WHERE d.nomor_nota = '"+nomornota+"' ORDER BY d.harga ASC");
                table.addCell(new Phrase("Nama Barang",myFont(12, Font.BOLD)));
                table.addCell(new Phrase("Qty",myFont(12, Font.BOLD)));
                for (String[] s : dquery) {
                    table.addCell(s[1]);
                    table.addCell(s[2]);
                    grandTotal += Integer.parseInt(s[2]);
                    int idx = namabrg.indexOf(s[1]);
                    if(idx == -1){
                        namabrg.add(s[1]);
                        jmlbrg.add(Integer.parseInt(s[2]));
                    }else{
                        jmlbrg.set(idx, jmlbrg.get(idx)+Integer.parseInt(s[2]));
                    }
                }
                if(i != tb_Htrans.getRowCount()-1){
                    cl = new PdfPCell(new Phrase(" "));
                    cl.setBorder(Rectangle.OUT_TOP);
                    cl.setColspan(2);
                    table.addCell(cl);
                }
            }
            
            doc.add(table);
            
            for(int i = 0; i < jmlbrg.size(); i++){
                for(int j = 0; j < jmlbrg.size()-1; j++){
                    if(jmlbrg.get(i) > jmlbrg.get(j)){
                        int temp = jmlbrg.get(i);
                        jmlbrg.set(i, jmlbrg.get(j));
                        jmlbrg.set(j, temp);
                        
                        String tempS = namabrg.get(i);
                        namabrg.set(i, namabrg.get(j));
                        namabrg.set(j, tempS);
                    }
                }
            }
            
            doc.add(new Paragraph(" "));
            table = new PdfPTable(2);
            cl = new PdfPCell(new Phrase("Rekap Jumlah Penjualan Barang", myFont(16, Font.BOLD)));
            cl.setColspan(2);
            cl.setBackgroundColor(BaseColor.YELLOW);
            cl.setHorizontalAlignment(Element.ALIGN_CENTER);
            cl.setPaddingBottom(5);
            table.addCell(cl);
            table.addCell(new Phrase("Nama Barang",myFont(12,Font.BOLD)));
            table.addCell(new Phrase("Jumlah Barang",myFont(12,Font.BOLD)));
            for(int i = 0; i < namabrg.size(); i++){
                table.addCell(namabrg.get(i));
                table.addCell(jmlbrg.get(i).toString());
            }
            table.addCell(new Phrase("Total Barang",myFont(12, Font.BOLD)));
            table.addCell(new Phrase(String.valueOf(grandTotal),myFont(12,Font.BOLD)));
            doc.add(table);
            doc.close();
            JOptionPane.showMessageDialog(null, "Sukses export laporan!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        searched = true;
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

    private void btn_export3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_export3MouseEntered
        // TODO add your handling code here:
        btn_export3.setBackground(Palette.getButtonSelectedColor());
    }//GEN-LAST:event_btn_export3MouseEntered

    private void btn_export3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_export3MouseExited
        // TODO add your handling code here:
        btn_export3.setBackground(Palette.getTableDark1());
    }//GEN-LAST:event_btn_export3MouseExited

    private void btn_export3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export3ActionPerformed
        // TODO add your handling code here:
        ArrayList<String> namajns = new ArrayList<>();
        ArrayList<Integer> jmljns = new ArrayList<>();
        ArrayList<String[]> qnama = DB.query("select nama_jenis from jenis_barang");
        for (String[] s : qnama) {
            namajns.add(s[0]);
            jmljns.add(0);
        }
        try {
            File f = new File(System.getProperty("user.dir")+"/Laporan/");
            f.mkdir();
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.dir")+"/Laporan/"+"laporan_kategori.pdf"));
            doc.open();
            Paragraph p_judul = new Paragraph("COMPUFY", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 26, Font.BOLD));
            p_judul.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_judul);
            Paragraph p_subjudul = new Paragraph("Laporan Stok Penjualan per Kategori", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20, Font.BOLD));
            p_subjudul.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_subjudul);
            Paragraph p_temp = new Paragraph("PERIODE",myFont(12,Font.BOLD));
            p_temp.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_temp);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if(searched == true){
                p_temp = new Paragraph(sdf.format(dp_tanggal.getDate()) +" - "+ sdf.format(dp_tanggal1.getDate()),myFont(12, Font.BOLD));
            }else{
                p_temp = new Paragraph("Semua",myFont(12,Font.BOLD));
            }
            p_temp.setAlignment(Element.ALIGN_CENTER);
            doc.add(p_temp);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell cl;
            
            int grandTotal = 0;
            
            for(int i = 0; i < tb_Htrans.getRowCount(); i++){
                String nomornota = tb_Htrans.getModel().getValueAt(i, 0).toString();
                cl = new PdfPCell(new Phrase(nomornota,myFont(12, Font.BOLD)));
                cl.setColspan(2);
                cl.setBackgroundColor(BaseColor.YELLOW);
                cl.setPaddingBottom(5);
                
                table.addCell(cl);
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                ArrayList<String[]> dquery = DB.query("select j.nama_jenis, NVL(sum(d.qty),0) as jml from d_trans d right join barang b on b.id = d.fk_barang right join jenis_barang j on j.id = b.fk_jenis_barang where d.nomor_nota = '"+nomornota+"' group by j.nama_jenis order by 2 asc");
                table.addCell(new Phrase("Nama Jenis",myFont(12, Font.BOLD)));
                table.addCell(new Phrase("Qty",myFont(12, Font.BOLD)));
                for (String[] s : dquery) {
                    table.addCell(s[0]);
                    table.addCell(s[1]);
                    grandTotal += Integer.parseInt(s[1]);
                    int idx = namajns.indexOf(s[0]);
                    if(idx == -1){
                        namajns.add(s[0]);
                        jmljns.add(Integer.parseInt(s[1]));
                    }else{
                        jmljns.set(idx, jmljns.get(idx)+Integer.parseInt(s[1]));
                    }
                }
                if(i != tb_Htrans.getRowCount()-1){
                    cl = new PdfPCell(new Phrase(" "));
                    cl.setBorder(Rectangle.OUT_TOP);
                    cl.setColspan(2);
                    table.addCell(cl);
                }
            }
            
            doc.add(table);
            
            for(int i = 0; i < jmljns.size(); i++){
                for(int j = 0; j < jmljns.size()-1; j++){
                    if(jmljns.get(i) > jmljns.get(j)){
                        int temp = jmljns.get(i);
                        jmljns.set(i, jmljns.get(j));
                        jmljns.set(j, temp);
                        
                        String tempS = namajns.get(i);
                        namajns.set(i, namajns.get(j));
                        namajns.set(j, tempS);
                    }
                }
            }
            
            doc.add(new Paragraph(" "));
            table = new PdfPTable(2);
            cl = new PdfPCell(new Phrase("Rekap Jumlah Penjualan Barang per Kategori", myFont(16, Font.BOLD)));
            cl.setColspan(2);
            cl.setBackgroundColor(BaseColor.YELLOW);
            cl.setHorizontalAlignment(Element.ALIGN_CENTER);
            cl.setPaddingBottom(5);
            table.addCell(cl);
            table.addCell(new Phrase("Nama Jenis",myFont(12,Font.BOLD)));
            table.addCell(new Phrase("Jumlah Barang",myFont(12,Font.BOLD)));
            for(int i = 0; i < namajns.size(); i++){
                table.addCell(namajns.get(i));
                table.addCell(jmljns.get(i).toString());
            }
            table.addCell(new Phrase("Total Barang",myFont(12, Font.BOLD)));
            table.addCell(new Phrase(String.valueOf(grandTotal),myFont(12,Font.BOLD)));
            doc.add(table);
            doc.close();
            JOptionPane.showMessageDialog(null, "Sukses export laporan!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_export3ActionPerformed
    
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
    private javax.swing.JButton btn_export3;
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
    private javax.swing.JLabel lbl_grandtotal1;
    private javax.swing.JPanel pl;
    private javax.swing.JTable tb_Dtrans;
    private javax.swing.JTable tb_Htrans;
    private javax.swing.JTextField tb_karyawan;
    private javax.swing.JTextField tb_nota;
    // End of variables declaration//GEN-END:variables
}
