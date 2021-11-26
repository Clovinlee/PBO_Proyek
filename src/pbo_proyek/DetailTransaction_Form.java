/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pbo_proyek;

import java.awt.Color;
import java.awt.Cursor;
import java.util.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class DetailTransaction_Form extends javax.swing.JFrame {
    
    /**
     * Creates new form DetailTransaction_Form
     */
    public DetailTransaction_Form() {
        initComponents();
    }
    
    private Color grn = new Color(43,167,73);
    private Color gry = new Color(102,102,102);
    
    private Form_Menu frm_menu;
    private Form_Transaction frm_trans;
    
    public DetailTransaction_Form(int grand_total, String kode, int promo, ArrayList<String[]> listCart, Form_Menu frm_menu){
        this();
        this.grand_total = grand_total;
        this.kode = kode;
        this.promo = promo;
        this.listCart = listCart;
        this.frm_menu = frm_menu;
        lbl_grandtotal.setText("Grand Total : Rp"+String.format("%,d",grand_total));
    }
    
    private int grand_total = 0;
    private String kode = "";
    private int promo = -1;
    private ArrayList<String[]> listCart;
    private int bayar = 0;
    private int kembalian = 0;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tb_totalbayar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_cancel = new javax.swing.JButton();
        btn_confirm = new javax.swing.JButton();
        lbl_grandtotal = new javax.swing.JLabel();
        lbl_kembalian = new javax.swing.JLabel();
        lbl_bayar = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pl_titlebar = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();
        lbl_minimize = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(58, 58, 58));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(222, 222, 222));
        jLabel2.setText("PEMBAYARAN");

        tb_totalbayar.setBackground(new java.awt.Color(244, 244, 244));
        tb_totalbayar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tb_totalbayar.setForeground(new java.awt.Color(58, 58, 58));
        tb_totalbayar.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        tb_totalbayar.setCaretColor(new java.awt.Color(58, 58, 58));
        tb_totalbayar.setMaximumSize(new java.awt.Dimension(6, 22));
        tb_totalbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_totalbayarKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(222, 222, 222));
        jLabel5.setText("Total Bayar");

        btn_cancel.setBackground(new java.awt.Color(238, 50, 54));
        btn_cancel.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_cancel.setForeground(new java.awt.Color(244, 244, 244));
        btn_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/times-circle-solid.png"))); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.setBorder(null);
        btn_cancel.setContentAreaFilled(false);
        btn_cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cancel.setFocusPainted(false);
        btn_cancel.setOpaque(true);
        btn_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cancelMouseExited(evt);
            }
        });
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_confirm.setBackground(new java.awt.Color(102, 102, 102));
        btn_confirm.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_confirm.setForeground(new java.awt.Color(244, 244, 244));
        btn_confirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/check-circle-solid.png"))); // NOI18N
        btn_confirm.setText("Confirm");
        btn_confirm.setBorder(null);
        btn_confirm.setContentAreaFilled(false);
        btn_confirm.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_confirm.setFocusPainted(false);
        btn_confirm.setOpaque(true);
        btn_confirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_confirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_confirmMouseExited(evt);
            }
        });
        btn_confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmActionPerformed(evt);
            }
        });

        lbl_grandtotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_grandtotal.setForeground(new java.awt.Color(222, 222, 222));
        lbl_grandtotal.setText("Grand Total : Rp0");

        lbl_kembalian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_kembalian.setForeground(new java.awt.Color(222, 222, 222));
        lbl_kembalian.setText("Kembalian : Rp0");

        lbl_bayar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_bayar.setForeground(new java.awt.Color(222, 222, 222));
        lbl_bayar.setText("Bayar : Rp0");

        jSeparator2.setForeground(new java.awt.Color(244, 244, 244));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lbl_grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btn_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel2)
                                .addComponent(jLabel5)
                                .addComponent(tb_totalbayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tb_totalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_grandtotal)
                .addGap(0, 0, 0)
                .addComponent(lbl_bayar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_kembalian))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pl_titlebar.setBackground(new java.awt.Color(58, 58, 58));
        pl_titlebar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pl_titlebarMouseDragged(evt);
            }
        });
        pl_titlebar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pl_titlebarMousePressed(evt);
            }
        });

        lbl_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/times-solid.png"))); // NOI18N
        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_closeMouseClicked(evt);
            }
        });

        lbl_minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/window-minimize-solid.png"))); // NOI18N
        lbl_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_minimizeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pl_titlebarLayout = new javax.swing.GroupLayout(pl_titlebar);
        pl_titlebar.setLayout(pl_titlebarLayout);
        pl_titlebarLayout.setHorizontalGroup(
            pl_titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pl_titlebarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_minimize)
                .addGap(18, 18, 18)
                .addComponent(lbl_close)
                .addContainerGap())
        );
        pl_titlebarLayout.setVerticalGroup(
            pl_titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pl_titlebarLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lbl_close))
            .addComponent(lbl_minimize)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pl_titlebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pl_titlebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        frm_menu.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmActionPerformed
        // TODO add your handling code here:
        // TODO : Use trans
        if(valid == 1){
            String promo_q = "NULL";
            if(promo > 0){
                promo_q = String.valueOf(promo);
            }
            boolean valid = DB.trans("INSERT INTO h_trans VALUES(?, ?, ?, ?, ?)", new Object[] {kode,new Date(),grand_total, promo_q, User.getUser_login().getId()},listCart,kode);
            if(valid == false){
                JOptionPane.showMessageDialog(null, "Transaksi Gagal!","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Transaksi Sukses!","Sukses",JOptionPane.INFORMATION_MESSAGE);
            }
            frm_menu.setEnabled(true);
            frm_trans.clearCart();
            this.dispose();
        }
    }//GEN-LAST:event_btn_confirmActionPerformed

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        // TODO add your handling code here:
        frm_menu.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseClicked
        // TODO add your handling code here:
        setState(this.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizeMouseClicked

    public Form_Transaction getFrm_trans() {
        return frm_trans;
    }

    public void setFrm_trans(Form_Transaction frm_trans) {
        this.frm_trans = frm_trans;
    }
    
    int x,y;
    private void pl_titlebarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pl_titlebarMouseDragged
        // TODO add your handling code here:
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_pl_titlebarMouseDragged

    private void pl_titlebarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pl_titlebarMousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pl_titlebarMousePressed
    
    private int valid = -1;
    
    private void btn_confirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_confirmMouseEntered
        // TODO add your handling code here:
        if(valid == 1){
            btn_confirm.setBackground(new Color(49,211,88));
        }
    }//GEN-LAST:event_btn_confirmMouseEntered

    private void btn_confirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_confirmMouseExited
        // TODO add your handling code here:
        if(valid == 1){
            btn_confirm.setBackground(grn);
        }
    }//GEN-LAST:event_btn_confirmMouseExited

    private void btn_cancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelMouseExited
        // TODO add your handling code here:
        btn_cancel.setBackground(new Color(238,50,54));
    }//GEN-LAST:event_btn_cancelMouseExited

    private void btn_cancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelMouseEntered
        // TODO add your handling code here:
        btn_cancel.setBackground(new Color(242,68,72));
    }//GEN-LAST:event_btn_cancelMouseEntered

    private void tb_totalbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_totalbayarKeyReleased
        // TODO add your handling code here:
        try{
            bayar = Integer.valueOf(tb_totalbayar.getText());
            if(bayar >= grand_total){
                valid = 1;
            }else{
                valid = -1;
            }
        }catch(Exception ex){
            valid = -1;
        }
        if(valid == 1){
            btn_confirm.setBackground(grn);
            kembalian = bayar - grand_total;
            btn_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }else{
            btn_confirm.setBackground(gry);
            btn_confirm.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            bayar = 0;
            kembalian = 0;
        }
        lbl_bayar.setText("Bayar : Rp"+String.format("%,d", bayar));
        lbl_kembalian.setText("Kembalian : Rp"+String.format("%,d", kembalian));
    }//GEN-LAST:event_tb_totalbayarKeyReleased
    
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
            java.util.logging.Logger.getLogger(DetailTransaction_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailTransaction_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailTransaction_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailTransaction_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailTransaction_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_confirm;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl_bayar;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_grandtotal;
    private javax.swing.JLabel lbl_kembalian;
    private javax.swing.JLabel lbl_minimize;
    private javax.swing.JPanel pl_titlebar;
    private javax.swing.JTextField tb_totalbayar;
    // End of variables declaration//GEN-END:variables
}
