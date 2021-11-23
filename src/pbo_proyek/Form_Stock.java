/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package pbo_proyek;


import ExternalCode.JTableEdit;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author chris
 */
public class Form_Stock extends javax.swing.JFrame {
    
    /** Creates new form Form_Stock */
    public Form_Stock() {
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
        JTableHeader header = dgv_barang.getTableHeader();
        jScrollPane1.getViewport().setBackground(Palette.getDark4());
            
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setOpaque(false);
        
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        dgv_barang.getTableHeader().setBackground(Palette.getDark1());
        dgv_barang.getTableHeader().setForeground(Palette.getSilver1());

    }
        DefaultTableModel tbl;
        ArrayList<String[]> listStok;
        ArrayList<String[]> listJenis;
    public void loadDgv(){
        
        listStok = DB.query("SELECT * FROM barang where status = 1");
        tbl = new DefaultTableModel(new Object[] {"No","Kode","Nama","Stok","Harga","Jenis"}, 0);
        dgv_barang.setDefaultEditor(Object.class, null);
        listJenis = DB.query("SELECT * FROM JENIS_BARANG");
        
        for (String[] s :listJenis) {
            cb_jenisbarang.addItem(s[1]);
        }
        
        int ctr = 1;
        String jenis="";
        for (String[] s : listStok) {
            for(String[] j:listJenis){
                if(s[6].equals(j[0])){
                    jenis = j[1];
                }
            }                
            tbl.addRow(new Object[] {ctr,s[1],s[2],s[3],s[4],jenis});
            ctr++;
        }
        dgv_barang.setModel(tbl);
        
    }
    public void search(){    
        tbl = new DefaultTableModel(new Object[] {"No","Kode","Nama","Stok","Harga","Jenis"}, 0);
        dgv_barang.setDefaultEditor(Object.class, null);
        String jns="";
  
        int ctr = 1;
        for(String [] s: listStok){
            jns ="Semua";
            if(s[6].equals("1")){            
                jns = "Monitor";
            }else if(s[6].equals("2")){
                jns = "CPU";
            }else if(s[6].equals("3")){
                jns = "Mouse";
            }else if(s[6].equals("4")){
                jns = "Keyboard";
            }else if(s[6].equals("5")){
                jns = "Ram";
            }else if(s[6].equals("6")){
                jns = "Hardisk";
            }else if(s[6].equals("7")){
                jns = "Printer";
            }
            if(validSearch(tb_kode.getText(),tb_namabarang.getText(),tb_harga1.getText(),tb_harga2.getText(),cb_jenisbarang.getSelectedItem().toString(),jns,s)){               
               tbl.addRow(new Object[] {ctr,s[1],s[2],s[3],s[4],jns});
                ctr++; 
            }
        }
        dgv_barang.setModel(tbl);   
    }
    public boolean validSearch(String kode, String nama, String hrgawal, String hrgakhir, String jenis,String jns, String[] data){
        int start=0;
        int end=999999999;
        if(data[1].toLowerCase().contains(kode.toLowerCase())){
            if(data[2].toLowerCase().contains(nama.toLowerCase())){
                if(!hrgawal.equals("")){start = Integer.parseInt(hrgawal);}
                if(!hrgakhir.equals("")){end = Integer.parseInt(hrgakhir);}    
                if(Integer.parseInt(data[4])>=start && Integer.parseInt(data[4])<=end){
                    if(jenis.equalsIgnoreCase(jns) || jenis.equalsIgnoreCase("Semua")){
                        return true;
                    }  
                }               
            }
        }
        return false;
    }
     public int getIdx(int selected_idx){
        int temp_idx = -1;
        String kode_search = dgv_barang.getValueAt(selected_idx, 1).toString();
        for(int i = 0; i < listStok.size(); i++){
            if(listStok.get(i)[1].equalsIgnoreCase(kode_search)){
                temp_idx = i;
            }
        }
        return temp_idx;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pl = new javax.swing.JPanel();
        pl1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgv_barang = new javax.swing.JTable();
        btn_detail = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tb_kode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tb_harga1 = new javax.swing.JTextField();
        tb_namabarang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cb_jenisbarang = new javax.swing.JComboBox<>();
        btn_refresh = new javax.swing.JButton();
        btn_addbarang = new javax.swing.JButton();
        tb_harga2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(772, 496));

        pl.setBackground(new java.awt.Color(84, 84, 96));

        pl1.setBackground(new java.awt.Color(84, 84, 96));

        dgv_barang.setBackground(new java.awt.Color(58, 58, 58));
        dgv_barang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        dgv_barang.setForeground(new java.awt.Color(222, 222, 222));
        dgv_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "CABE0001", "Caca Bernerd", "cacab", "Perempuan", null},
                {"2", "BEBE0002", "Belinda Bergusawada", "bebeca", "Laki - Laki", null},
                {"3", "KAKA0001", "Kalvin Kawasaki Nintendo Bugisoka", "kal", "Laki - Laki", null}
            },
            new String [] {
                "No", "Kode", "Nama", "Stok Barang", "Harga", "Jenis Barang"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgv_barang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        dgv_barang.setGridColor(new java.awt.Color(255, 255, 255));
        dgv_barang.setOpaque(false);
        dgv_barang.setRowHeight(25);
        dgv_barang.setSelectionBackground(new java.awt.Color(90, 90, 90));
        dgv_barang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dgv_barang.setShowVerticalLines(false);
        dgv_barang.getTableHeader().setReorderingAllowed(false);
        dgv_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dgv_barangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(dgv_barang);
        if (dgv_barang.getColumnModel().getColumnCount() > 0) {
            dgv_barang.getColumnModel().getColumn(0).setResizable(false);
            dgv_barang.getColumnModel().getColumn(0).setPreferredWidth(40);
        }

        btn_detail.setBackground(new java.awt.Color(58, 58, 58));
        btn_detail.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_detail.setForeground(new java.awt.Color(222, 222, 222));
        btn_detail.setText("Details");
        btn_detail.setBorder(null);
        btn_detail.setContentAreaFilled(false);
        btn_detail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_detail.setFocusPainted(false);
        btn_detail.setOpaque(true);
        btn_detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_detailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_detailMouseExited(evt);
            }
        });
        btn_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detailActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(222, 222, 222));
        jLabel1.setText("Jenis Barang");

        tb_kode.setBackground(new java.awt.Color(58, 58, 58));
        tb_kode.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_kode.setForeground(new java.awt.Color(222, 222, 222));
        tb_kode.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_kode.setCaretColor(new java.awt.Color(222, 222, 222));
        tb_kode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_kodeKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(222, 222, 222));
        jLabel2.setText("Kode");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(222, 222, 222));
        jLabel3.setText("Range Harga");

        tb_harga1.setBackground(new java.awt.Color(58, 58, 58));
        tb_harga1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_harga1.setForeground(new java.awt.Color(222, 222, 222));
        tb_harga1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_harga1.setCaretColor(new java.awt.Color(222, 222, 222));
        tb_harga1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_harga1KeyReleased(evt);
            }
        });

        tb_namabarang.setBackground(new java.awt.Color(58, 58, 58));
        tb_namabarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_namabarang.setForeground(new java.awt.Color(222, 222, 222));
        tb_namabarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_namabarang.setCaretColor(new java.awt.Color(222, 222, 222));
        tb_namabarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_namabarangKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(222, 222, 222));
        jLabel4.setText("Nama Barang");

        cb_jenisbarang.setBackground(new java.awt.Color(222, 222, 222));
        cb_jenisbarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cb_jenisbarang.setForeground(new java.awt.Color(58, 58, 58));
        cb_jenisbarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua" }));
        cb_jenisbarang.setOpaque(false);
        cb_jenisbarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_jenisbarangItemStateChanged(evt);
            }
        });

        btn_refresh.setBackground(new java.awt.Color(84, 84, 96));
        btn_refresh.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
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

        btn_addbarang.setBackground(new java.awt.Color(58, 58, 58));
        btn_addbarang.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_addbarang.setForeground(new java.awt.Color(222, 222, 222));
        btn_addbarang.setText("Add Barang");
        btn_addbarang.setBorder(null);
        btn_addbarang.setContentAreaFilled(false);
        btn_addbarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_addbarang.setFocusPainted(false);
        btn_addbarang.setOpaque(true);
        btn_addbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_addbarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_addbarangMouseExited(evt);
            }
        });

        tb_harga2.setBackground(new java.awt.Color(58, 58, 58));
        tb_harga2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_harga2.setForeground(new java.awt.Color(222, 222, 222));
        tb_harga2.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_harga2.setCaretColor(new java.awt.Color(222, 222, 222));
        tb_harga2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_harga2KeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(222, 222, 222));
        jLabel5.setText("-");

        javax.swing.GroupLayout pl1Layout = new javax.swing.GroupLayout(pl1);
        pl1.setLayout(pl1Layout);
        pl1Layout.setHorizontalGroup(
            pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pl1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pl1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(19, 19, 19))
                    .addGroup(pl1Layout.createSequentialGroup()
                        .addGroup(pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(tb_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(tb_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(pl1Layout.createSequentialGroup()
                                .addComponent(tb_harga1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tb_harga2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pl1Layout.createSequentialGroup()
                                .addComponent(cb_jenisbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(0, 64, Short.MAX_VALUE))
                    .addGroup(pl1Layout.createSequentialGroup()
                        .addComponent(btn_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_addbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pl1Layout.setVerticalGroup(
            pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pl1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pl1Layout.createSequentialGroup()
                        .addGroup(pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tb_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tb_harga1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tb_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_jenisbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tb_harga2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout plLayout = new javax.swing.GroupLayout(pl);
        pl.setLayout(plLayout);
        plLayout.setHorizontalGroup(
            plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plLayout.setVerticalGroup(
            plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    int idx = -1;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
    
    private void dgv_barangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgv_barangMousePressed
        // TODO add your handling code here:
        idx = dgv_barang.getSelectedRow();
    }//GEN-LAST:event_dgv_barangMousePressed

    private void btn_detailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_detailMouseEntered
        // TODO add your handling code here:
        btn_detail.setBackground(Palette.getButtonSelectedColor());
    }//GEN-LAST:event_btn_detailMouseEntered

    private void btn_detailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_detailMouseExited
        // TODO add your handling code here:
        btn_detail.setBackground(Palette.getTableDark1());
    }//GEN-LAST:event_btn_detailMouseExited

    private void btn_refreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseEntered
        // TODO add your handling code here:
        btn_refresh.setBackground(new Color(106,106,115));
    }//GEN-LAST:event_btn_refreshMouseEntered

    private void btn_refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseExited
        // TODO add your handling code here:
        btn_refresh.setBackground(Palette.getDark3());
    }//GEN-LAST:event_btn_refreshMouseExited

    private void btn_addbarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addbarangMouseEntered
        // TODO add your handling code here:
        btn_addbarang.setBackground(Palette.getButtonSelectedColor());
    }//GEN-LAST:event_btn_addbarangMouseEntered

    private void btn_addbarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addbarangMouseExited
        // TODO add your handling code here:
        btn_addbarang.setBackground(Palette.getTableDark1());
    }//GEN-LAST:event_btn_addbarangMouseExited

    private void tb_kodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_kodeKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tb_kodeKeyReleased

    private void tb_namabarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_namabarangKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tb_namabarangKeyReleased

    private void tb_harga1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_harga1KeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tb_harga1KeyReleased

    private void tb_harga2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_harga2KeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tb_harga2KeyReleased

    private void cb_jenisbarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_jenisbarangItemStateChanged
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cb_jenisbarangItemStateChanged

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        tb_namabarang.setText("");
        tb_kode.setText("");
        tb_harga1.setText("");
        tb_harga2.setText("");
        cb_jenisbarang.setSelectedIndex(0);
        search();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detailActionPerformed
        // TODO add your handling code here:
        if(idx == -1){
            JOptionPane.showMessageDialog(null, "No item selected","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            idx = getIdx(idx);
            String[] data = listStok.get(idx);
            DetailStok_Form ds_frm = new DetailStok_Form(data);
            ds_frm.setFrm_stc(this);
            ds_frm.setVisible(true);
            idx = -1;
        }
    }//GEN-LAST:event_btn_detailActionPerformed
    
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
            java.util.logging.Logger.getLogger(Form_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addbarang;
    private javax.swing.JButton btn_detail;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JComboBox<String> cb_jenisbarang;
    private javax.swing.JTable dgv_barang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pl;
    private javax.swing.JPanel pl1;
    private javax.swing.JTextField tb_harga1;
    private javax.swing.JTextField tb_harga2;
    private javax.swing.JTextField tb_kode;
    private javax.swing.JTextField tb_namabarang;
    // End of variables declaration//GEN-END:variables
    
}
