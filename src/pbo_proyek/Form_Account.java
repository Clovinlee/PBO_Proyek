/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pbo_proyek;

import ExternalCode.JTableEdit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author chris
 */
public class Form_Account extends javax.swing.JFrame {
    
    /**
     * Creates new form Form_Account
     */
    
    private Form_Menu frm_menu;

    public Form_Menu getFrm_menu() {
        return frm_menu;
    }

    public void setFrm_menu(Form_Menu frm_menu) {
        this.frm_menu = frm_menu;
    }
    
    public Form_Account() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("ComboBox.selectionBackground",new ColorUIResource(Palette.getTableDark1()));
        } catch (Exception e) {
        }
        initComponents();
        loadDgv();
        styleDgv();
        loadCmb();
        
        
        tb_kode.setBackground(Palette.getTableDark1());
        tb_nama.setBackground(Palette.getTableDark1());
        tb_username.setBackground(Palette.getTableDark1());
    }
    
    DefaultTableModel tbl;
    ArrayList<String[]> listUser;
    ArrayList<String[]> listJabatan;
    
    public void loadCmb(){
        listJabatan= DB.query("SELECT * FROM jabatan");
        
        for (String[] s : listJabatan) {
            cb_jabatan.addItem(s[1]);
        }
    }
    
    public void loadDgv(){
        listUser = DB.query("SELECT * FROM karyawan where status = 1 ORDER BY id DESC");
        tbl = new DefaultTableModel(new Object[] {"No","Kode","Nama","Username","Jabatan"}, 0);
        dgv_account.setDefaultEditor(Object.class, null);
        
        int ctr = 1;
        for (String[] s : listUser) {
            String jbtn = "Manager";
            if(s[11].equalsIgnoreCase("2")){
                jbtn = "Kasir";
            }else if(s[11].equalsIgnoreCase("3")){
                jbtn = "Marketing";
            }
            tbl.addRow(new Object[] {ctr,s[1],s[4],s[2],jbtn});
            ctr++;
        }
        dgv_account.setModel(tbl);
    }
    
    public JPanel getPl() {
        return pl;
    }
    
    public void styleDgv(){
        JTableHeader header = dgv_account.getTableHeader();
        jScrollPane1.getViewport().setBackground(Palette.getDark4());
        
        
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setOpaque(false);
        
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        dgv_account.getTableHeader().setBackground(Palette.getDark1());
        dgv_account.getTableHeader().setForeground(Palette.getSilver1());
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        dgv_account = new javax.swing.JTable();
        btn_detail = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tb_kode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tb_nama = new javax.swing.JTextField();
        tb_username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cb_jabatan = new javax.swing.JComboBox<>();
        btn_refresh = new javax.swing.JButton();
        btn_adduser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(790, 540));

        pl.setBackground(new java.awt.Color(84, 84, 96));

        dgv_account.setBackground(new java.awt.Color(58, 58, 58));
        dgv_account.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        dgv_account.setForeground(new java.awt.Color(222, 222, 222));
        dgv_account.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "CABE0001", "Caca Bernerd", "cacab", "Perempuan"},
                {"2", "BEBE0002", "Belinda Bergusawada", "bebeca", "Laki - Laki"},
                {"3", "KAKA0001", "Kalvin Kawasaki Nintendo Bugisoka", "kal", "Laki - Laki"}
            },
            new String [] {
                "No", "Kode", "Nama", "Username", "Jabatan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgv_account.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        dgv_account.setGridColor(new java.awt.Color(255, 255, 255));
        dgv_account.setOpaque(false);
        dgv_account.setRowHeight(25);
        dgv_account.setSelectionBackground(new java.awt.Color(90, 90, 90));
        dgv_account.setSelectionForeground(new java.awt.Color(222, 222, 222));
        dgv_account.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dgv_account.setShowVerticalLines(false);
        dgv_account.getTableHeader().setReorderingAllowed(false);
        dgv_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dgv_accountMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(dgv_account);
        if (dgv_account.getColumnModel().getColumnCount() > 0) {
            dgv_account.getColumnModel().getColumn(0).setResizable(false);
            dgv_account.getColumnModel().getColumn(0).setPreferredWidth(40);
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
        jLabel1.setText("Jabatan");

        tb_kode.setBackground(new java.awt.Color(58, 58, 58));
        tb_kode.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_kode.setForeground(new java.awt.Color(222, 222, 222));
        tb_kode.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_kode.setCaretColor(new java.awt.Color(222, 222, 222));
        tb_kode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_search(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(222, 222, 222));
        jLabel2.setText("Kode");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(222, 222, 222));
        jLabel3.setText("Nama");

        tb_nama.setBackground(new java.awt.Color(58, 58, 58));
        tb_nama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_nama.setForeground(new java.awt.Color(222, 222, 222));
        tb_nama.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_nama.setCaretColor(new java.awt.Color(222, 222, 222));
        tb_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_search(evt);
            }
        });

        tb_username.setBackground(new java.awt.Color(58, 58, 58));
        tb_username.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_username.setForeground(new java.awt.Color(222, 222, 222));
        tb_username.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_username.setCaretColor(new java.awt.Color(222, 222, 222));
        tb_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_search(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(222, 222, 222));
        jLabel4.setText("Username");

        cb_jabatan.setBackground(new java.awt.Color(222, 222, 222));
        cb_jabatan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cb_jabatan.setForeground(new java.awt.Color(58, 58, 58));
        cb_jabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua" }));
        cb_jabatan.setBorder(null);
        cb_jabatan.setOpaque(false);
        cb_jabatan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_jabatanItemStateChanged(evt);
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

        btn_adduser.setBackground(new java.awt.Color(58, 58, 58));
        btn_adduser.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_adduser.setForeground(new java.awt.Color(222, 222, 222));
        btn_adduser.setText("Add User");
        btn_adduser.setBorder(null);
        btn_adduser.setContentAreaFilled(false);
        btn_adduser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_adduser.setFocusPainted(false);
        btn_adduser.setOpaque(true);
        btn_adduser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_adduserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_adduserMouseExited(evt);
            }
        });
        btn_adduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adduserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plLayout = new javax.swing.GroupLayout(pl);
        pl.setLayout(plLayout);
        plLayout.setHorizontalGroup(
            plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(19, 19, 19))
                    .addGroup(plLayout.createSequentialGroup()
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tb_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(tb_username, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(plLayout.createSequentialGroup()
                                .addComponent(cb_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(btn_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_adduser, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        plLayout.setVerticalGroup(
            plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(plLayout.createSequentialGroup()
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tb_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tb_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_adduser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
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

    private void btn_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detailActionPerformed
        // TODO add your handling code here:
        if(idx == -1){
            JOptionPane.showMessageDialog(null, "No user selected","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            idx = getIdx(idx);
            String[] data = listUser.get(idx);
            DetailAccount_Form da_frm = new DetailAccount_Form(data);
            da_frm.setFrm_acc(this);
            da_frm.setVisible(true);
        }
    }//GEN-LAST:event_btn_detailActionPerformed
    
    public void setIdx(int idx){
        this.idx = idx;
    }
    
    public int getIdx(int selected_idx){
        int temp_idx = -1;
        String kode_search = dgv_account.getValueAt(selected_idx, 1).toString();
        for(int i = 0; i < listUser.size(); i++){
            if(listUser.get(i)[1].equalsIgnoreCase(kode_search)){
                temp_idx = i;
            }
        }
        return temp_idx;
    }
    
    public void search(){
        tbl = new DefaultTableModel(new Object[] {"No","Kode","Nama","Username","Gender"}, 0);
        
        int ctr = 1;
        for (String[] s : listUser) {
            String jbtn  = DB.query("SELECT nama_jabatan FROM jabatan WHERE id = "+s[11]).get(0)[0];
            if(validSearch(tb_kode.getText(), tb_nama.getText(), tb_username.getText(), cb_jabatan.getSelectedItem().toString(), jbtn, s)){
                tbl.addRow(new Object[] {ctr,s[1],s[4],s[2],jbtn});
                ctr++;
            }
        }
        dgv_account.setModel(tbl);
    }
    
    public boolean validSearch(String kode, String nama, String username, String jabatan, String jbtn, String[] data){
        if(data[1].toLowerCase().contains(kode.toLowerCase())){
            if(data[4].toLowerCase().contains(nama.toLowerCase())){
                if(data[2].toLowerCase().contains(username.toLowerCase())){
                    if(jabatan.equalsIgnoreCase(jbtn) || jabatan.equalsIgnoreCase("Semua")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
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

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        idx = -1;
        tb_kode.setText("");
        tb_nama.setText("");
        tb_username.setText("");
        cb_jabatan.setSelectedIndex(0);
        search();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_adduserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_adduserMouseEntered
        // TODO add your handling code here:
        btn_adduser.setBackground(Palette.getButtonSelectedColor());
    }//GEN-LAST:event_btn_adduserMouseEntered

    private void btn_adduserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_adduserMouseExited
        // TODO add your handling code here:
        btn_adduser.setBackground(Palette.getTableDark1());
    }//GEN-LAST:event_btn_adduserMouseExited

    private void btn_adduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adduserActionPerformed
        // TODO add your handling code here:
        InsertAccount_Form add_form = new InsertAccount_Form();
        add_form.setFrm_acc(this);
        add_form.setVisible(true);
    }//GEN-LAST:event_btn_adduserActionPerformed
    
    int idx = -1;
    private void dgv_accountMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgv_accountMousePressed
        // TODO add your handling code here:
        idx = dgv_account.getSelectedRow();
    }//GEN-LAST:event_dgv_accountMousePressed

    private void cb_jabatanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_jabatanItemStateChanged
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cb_jabatanItemStateChanged

    private void tb_search(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_search
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tb_search
    
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
            java.util.logging.Logger.getLogger(Form_Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Account().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adduser;
    private javax.swing.JButton btn_detail;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JComboBox<String> cb_jabatan;
    private javax.swing.JTable dgv_account;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pl;
    private javax.swing.JTextField tb_kode;
    private javax.swing.JTextField tb_nama;
    private javax.swing.JTextField tb_username;
    // End of variables declaration//GEN-END:variables
}
