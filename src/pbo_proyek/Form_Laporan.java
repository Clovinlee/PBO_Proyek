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
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.text.SimpleDateFormat;  
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
        opened = 1;
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
    int selectedidx = -1;
    public void loadDgv(){
        
        listHtrans = DB.query("SELECT * FROM h_trans");
        tbl = new DefaultTableModel(new Object[] {"Nomor Nota","Tanggal Transaksi","Diskon","Kode Karyawan","Grand Total"}, 0);
        tb_Htrans.setDefaultEditor(Object.class, null);
        listdiskon = DB.query("SELECT * FROM diskon");
        for (String[] s :listdiskon) {
            cb_diskon.addItem(s[1]);
        }
        listkaryawan = DB.query("SELECT * FROM karyawan");
        
        
        int ctr = 1;
        String diskon="";
        String karyawan="";
        for (String[] s : listHtrans) {
            for(String[] j:listdiskon){
                if(s[3].equals(j[0])){
                    diskon = j[1];
                }
            }
            for(String[] k:listkaryawan){
                if(s[4].equals(k[0])){
                    karyawan = k[1];
                }
            }
            tbl.addRow(new Object[] {s[0],s[1],diskon,karyawan,s[2]});
            ctr++;
        }
        tb_Htrans.setModel(tbl);
        
    }
    
    public void search(){
        tbl = new DefaultTableModel(new Object[] {"Nomor Nota","Tanggal Transaksi","Diskon","Kode Karyawan","Grand Total"}, 0);
        tb_Htrans.setDefaultEditor(Object.class, null);
        String diskon="";
        String karyawan="";
        for (String[] s : listHtrans) {
            for(String[] j:listdiskon){
                if(s[3].equals(j[0])){
                    diskon = j[1];
                }
            }
            for(String[] k:listkaryawan){
                if(s[4].equals(k[0])){
                    karyawan = k[1];
                }
            }
            if(validSearch(tb_nota.getText().toLowerCase(),tb_karyawan.getText().toLowerCase(),dp_tanggal.getDate(),dp_tanggal1.getDate(),karyawan,s,cb_diskon.getSelectedItem().toString(),diskon)){
                tbl.addRow(new Object[] {s[0],s[1],diskon,karyawan,s[2]});
                
            }
        }
        tb_Htrans.setModel(tbl);
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
        jLabel5.setText("Diskon");

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

<<<<<<< HEAD
        dp_tanggal.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                dp_tanggalInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
=======
>>>>>>> 8d616116c7b98fbd4cf9464272b2c48877d9ebd2
        dp_tanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dp_tanggalPropertyChange(evt);
            }
        });

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

        btn_refresh.setBackground(new java.awt.Color(84, 84, 96));
        btn_refresh.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_refresh.setForeground(new java.awt.Color(222, 222, 222));
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/redo-alt-solid.png"))); // NOI18N
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

        dp_tanggal1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                dp_tanggal1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        dp_tanggal1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dp_tanggal1PropertyChange(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(222, 222, 222));
        jLabel4.setText("-");

        lbl_grandtotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_grandtotal.setForeground(new java.awt.Color(222, 222, 222));
        lbl_grandtotal.setText("Grand Total : Rp0");

        cb_diskon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua" }));
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
                            .addGroup(plLayout.createSequentialGroup()
                                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tb_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(12, 12, 12)
                                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tb_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(plLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cb_diskon, 0, 118, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(plLayout.createSequentialGroup()
                                        .addComponent(dp_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dp_tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, plLayout.createSequentialGroup()
                                .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btn_export1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(16, 16, 16))))
        );
        plLayout.setVerticalGroup(
            plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(plLayout.createSequentialGroup()
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2))
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(plLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tb_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tb_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(plLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(dp_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6))
                    .addComponent(dp_tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lbl_grandtotal))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_export1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pl, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
        
    }//GEN-LAST:event_btn_exportActionPerformed

    private void btn_refreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseEntered
        // TODO add your handling code here:
        btn_refresh.setBackground(new Color(106,106,115));
    }//GEN-LAST:event_btn_refreshMouseEntered

    private void btn_refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseExited
        // TODO add your handling code here:
        btn_refresh.setBackground(Palette.getDark3());
    }//GEN-LAST:event_btn_refreshMouseExited

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        loadDgv();
        cb_diskon.setSelectedIndex(0);
        tb_karyawan.setText("");
        tb_nota.setText("");
        selectedidx = -1;
        tb_Dtrans.removeAll();
    }//GEN-LAST:event_btn_refreshActionPerformed

<<<<<<< HEAD
    private void tb_notaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_notaKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tb_notaKeyReleased

    private void tb_karyawanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_karyawanKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tb_karyawanKeyReleased

    private void cb_diskonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_diskonItemStateChanged
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cb_diskonItemStateChanged

    private void dp_tanggalInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dp_tanggalInputMethodTextChanged
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_dp_tanggalInputMethodTextChanged

    private void dp_tanggal1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dp_tanggal1InputMethodTextChanged
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_dp_tanggal1InputMethodTextChanged

    private void btn_export1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_export1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_export1MouseEntered

    private void btn_export1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_export1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_export1MouseExited

    private void btn_export1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_export1ActionPerformed

    private void dp_tanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dp_tanggalPropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_dp_tanggalPropertyChange

    private void dp_tanggal1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dp_tanggal1PropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_dp_tanggal1PropertyChange
=======
    private int opened = 0;
    
    private void dp_tanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dp_tanggalPropertyChange
        // TODO add your handling code here:
        if(opened == 1){
            System.out.println(dp_tanggal.getDate());
        }
    }//GEN-LAST:event_dp_tanggalPropertyChange
>>>>>>> 8d616116c7b98fbd4cf9464272b2c48877d9ebd2
    
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
