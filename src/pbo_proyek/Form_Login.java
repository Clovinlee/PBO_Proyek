/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pbo_proyek;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ExternalCode.ComponentResizer;
import java.awt.Dimension;

/**
 *
 * @author chris
 */
public class Form_Login extends javax.swing.JFrame {
    
    /**
     * Creates new form Form_Login
     */
    public Form_Login() {
        initComponents();
        this.getRootPane().setDefaultButton(btn_login);
        ComponentResizer cr = new ComponentResizer();
        cr.registerComponent(this);
        cr.setMinimumSize(new Dimension(669, 377));
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
        jLabel1 = new javax.swing.JLabel();
        tb_username = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_eye = new javax.swing.JLabel();
        btn_login = new javax.swing.JButton();
        tb_password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pl_titlebar = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();
        lbl_maximize = new javax.swing.JLabel();
        lbl_minimize = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(669, 377));
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        pl.setBackground(new java.awt.Color(41, 40, 48));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(222, 222, 222));
        jLabel1.setText("Login");

        tb_username.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        tb_username.setForeground(new java.awt.Color(115, 114, 117));
        tb_username.setText("Username ");
        tb_username.setBorder(null);
        tb_username.setCaretColor(new java.awt.Color(255, 255, 255));
        tb_username.setOpaque(false);
        tb_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tb_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tb_usernameFocusLost(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/user-regular.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/key-solid.png"))); // NOI18N

        lbl_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/eye-slash-solid.png"))); // NOI18N
        lbl_eye.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_eye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_eyeMouseClicked(evt);
            }
        });

        btn_login.setBackground(new java.awt.Color(43, 167, 73));
        btn_login.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btn_login.setForeground(new java.awt.Color(222, 222, 222));
        btn_login.setText("Login");
        btn_login.setBorder(null);
        btn_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_login.setFocusPainted(false);
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        tb_password.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        tb_password.setForeground(new java.awt.Color(115, 114, 117));
        tb_password.setText("Password ");
        tb_password.setBorder(null);
        tb_password.setCaretColor(new java.awt.Color(255, 255, 255));
        tb_password.setOpaque(false);
        tb_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tb_passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tb_passwordFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(222, 222, 222));
        jLabel2.setText("Username");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(222, 222, 222));
        jLabel5.setText("Password");

        jSeparator3.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/logo_compufy_250.png"))); // NOI18N

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        pl_titlebar.setBackground(new java.awt.Color(41, 40, 48));
        pl_titlebar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pl_titlebarMouseDragged(evt);
            }
        });
        pl_titlebar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pl_titlebarMouseClicked(evt);
            }
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

        lbl_maximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/window-maximize-solid.png"))); // NOI18N
        lbl_maximize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_maximizeMouseClicked(evt);
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
                .addGap(26, 26, 26)
                .addComponent(lbl_maximize)
                .addGap(18, 18, 18)
                .addComponent(lbl_close)
                .addContainerGap())
        );
        pl_titlebarLayout.setVerticalGroup(
            pl_titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pl_titlebarLayout.createSequentialGroup()
                .addGroup(pl_titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pl_titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbl_maximize)
                        .addGroup(pl_titlebarLayout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(lbl_close)))
                    .addComponent(lbl_minimize))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout plLayout = new javax.swing.GroupLayout(pl);
        pl.setLayout(plLayout);
        plLayout.setHorizontalGroup(
            plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(plLayout.createSequentialGroup()
                                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tb_password, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_eye))
                            .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(tb_username)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))))
                .addGap(44, 44, 44)
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addGap(31, 31, 31))
            .addComponent(pl_titlebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plLayout.setVerticalGroup(
            plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(pl_titlebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(plLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(plLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(plLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(tb_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(plLayout.createSequentialGroup()
                                        .addComponent(lbl_eye)
                                        .addGap(3, 3, 3)))
                                .addGap(1, 1, 1)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(plLayout.createSequentialGroup()
                                .addComponent(tb_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(plLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel6)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    int pass_mode = -1; //1 show, -1 hide
    private void lbl_eyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_eyeMouseClicked
        // TODO add your handling code here:
        pass_mode *= -1;
        if(pass_mode == 1){
            lbl_eye.setIcon(new ImageIcon(getClass().getResource("Images/eye-solid.png")));
            tb_password.setEchoChar((char)0);
        }else{
            lbl_eye.setIcon(new ImageIcon(getClass().getResource("Images/eye-slash-solid.png")));
            tb_password.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_lbl_eyeMouseClicked
    
    private void tb_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tb_usernameFocusGained
        // TODO add your handling code here:
        if(tb_username.getText().equals("Username ")){
            tb_username.setText("");
            tb_username.setForeground(Palette.getSilver1());
        }
    }//GEN-LAST:event_tb_usernameFocusGained
    
    //69 73 74
    
    private void tb_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tb_usernameFocusLost
        // TODO add your handling code here:
        if(tb_username.getText().equalsIgnoreCase("")){
            tb_username.setText("Username ");
            tb_username.setForeground(Palette.getDarkGrey1());
        }
    }//GEN-LAST:event_tb_usernameFocusLost

    private void tb_passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tb_passwordFocusGained
        String pw = new String(tb_password.getPassword());
        if(pw.equals("Password ")){
            tb_password.setText("");
            tb_password.setForeground(Palette.getSilver1());
        }
    }//GEN-LAST:event_tb_passwordFocusGained

    private void tb_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tb_passwordFocusLost
        // TODO add your handling code here:
        String pw = new String(tb_password.getPassword());
        if(pw.equals("")){
            tb_password.setText("Password ");
            tb_password.setForeground(Palette.getDarkGrey1());
        }
    }//GEN-LAST:event_tb_passwordFocusLost

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "yey");
        Form_Menu frm_menu = new Form_Menu();
        frm_menu.setVisible(true);
        frm_menu.setFrm_login(this);
        this.setVisible(false);
        tb_password.setText("Password ");
        tb_password.setForeground(Palette.getDarkGrey1());
        tb_username.setText("Username ");
        tb_username.setForeground(Palette.getDarkGrey1());
    }//GEN-LAST:event_btn_loginActionPerformed
    
    private int x,y;
    private void pl_titlebarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pl_titlebarMousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pl_titlebarMousePressed

    private void pl_titlebarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pl_titlebarMouseDragged
        // TODO add your handling code here:
        if(state_mode == 1){
            Dimension temp = this.getSize();
            lbl_maximize.setIcon(new ImageIcon(getClass().getResource("Images/window-maximize-solid.png")));
            state_mode *= -1;
            this.setExtendedState(this.NORMAL);
            this.setSize(temp);
        }
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
        
    }//GEN-LAST:event_pl_titlebarMouseDragged

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseClicked
        // TODO add your handling code here:
        setState(this.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizeMouseClicked
    
    int state_mode = -1; //-1 not full, 1 full screen
    
    public void screenChange(){
        state_mode *= -1;
        if(state_mode == 1){
            this.setExtendedState(this.MAXIMIZED_BOTH);
            lbl_maximize.setIcon(new ImageIcon(getClass().getResource("Images/window-restore-regular.png")));
        }else{
            this.setExtendedState(this.NORMAL);
            lbl_maximize.setIcon(new ImageIcon(getClass().getResource("Images/window-maximize-solid.png")));
        }
    }
    
    private void lbl_maximizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_maximizeMouseClicked
        // TODO add your handling code here:
        screenChange();
    }//GEN-LAST:event_lbl_maximizeMouseClicked

    private void pl_titlebarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pl_titlebarMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            screenChange();
        }
    }//GEN-LAST:event_pl_titlebarMouseClicked

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formComponentResized
    
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
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_eye;
    private javax.swing.JLabel lbl_maximize;
    private javax.swing.JLabel lbl_minimize;
    private javax.swing.JPanel pl;
    private javax.swing.JPanel pl_titlebar;
    private javax.swing.JPasswordField tb_password;
    private javax.swing.JTextField tb_username;
    // End of variables declaration//GEN-END:variables
}
